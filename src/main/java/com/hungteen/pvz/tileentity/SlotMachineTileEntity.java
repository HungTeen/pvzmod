package com.hungteen.pvz.tileentity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import com.hungteen.pvz.entity.drop.JewelEntity;
import com.hungteen.pvz.entity.drop.SunEntity;
import com.hungteen.pvz.gui.container.SlotMachineContainer;
import com.hungteen.pvz.item.tool.card.PlantCardItem;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.register.TileEntityRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.Ranks;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.items.ItemStackHandler;

public class SlotMachineTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

	public static final List<SlotOptions> OPTION_LIST = new ArrayList<>();
	public static final List<Integer> WEIGHT_LIST = new ArrayList<>();
	public static final Map<SlotOptions, Integer> OPTION_MAP = new HashMap<>();
	public static int sum;
	public static final int SUN_COST = 25;
	private final List<SlotOptions> currentList = new ArrayList<>();
	public final ItemStackHandler handler = new ItemStackHandler(3);
	public final IIntArray array = new IntArray(16);
	public SlotOptions[][] slotOptions = new SlotOptions[4][3];
	public int currentPos = 1;
	private final int minChangeCnt = 15;
	private final int maxChangeCnt = 25;
	private int changeCnt;
	private int changeTick = 0;
	private boolean isRunning = false;
	@SuppressWarnings("unused")
	private PlayerEntity player;
	
	static {
		sum = 0;
		putOptionToMap(new SlotOptions(1, 2));
		putOptionToMap(new SlotOptions(2, 1));
		for(Plants plant : Plants.values()) {
			Ranks rank = PlantUtil.getPlantRankByName(plant);
			putOptionToMap(new SlotOptions(plant, (Ranks.values().length - rank.ordinal()) / 2 + 1));
		}
	}
	
	private static void putOptionToMap(SlotOptions option) {
		OPTION_MAP.put(option, OPTION_LIST.size());
		OPTION_LIST.add(option);
		sum += option.weight;
		WEIGHT_LIST.add(sum);
	}
	
	public SlotMachineTileEntity() {
		super(TileEntityRegister.SLOT_MACHINE.get());
		Random rand = new Random();
		for(int i = 0; i < 4; ++ i) {
			for(int j = 0; j < 3; ++ j) {
				slotOptions[i][j] = OPTION_LIST.get(rand.nextInt(OPTION_LIST.size()));
			}
		}
	}

	@Override
	public void tick() {
		if(! world.isRemote) {
			for(int i = 0; i < 4; ++ i) {
				for(int j = 0; j < 3; ++ j) {
					int id = i * 3 + j;
					this.array.set(id, OPTION_MAP.get(slotOptions[i][j]));//0 - 11
				}
			}
			this.array.set(12, this.changeTick); // 12
			this.array.set(13, this.currentPos); // 13
			this.array.set(14, this.canRun() ? 1 : 0); // 14
			this.array.set(15, this.getChangeTick()); // 15
			if(this.changeTick > 0) {
				if(this.currentList.isEmpty()) {
					this.changeTick = 0;
					this.changeCnt = 0;
					return ;
				}
				-- this.changeTick;
				if(this.changeTick == 0) {
					-- this.changeCnt;
					if(this.changeCnt == 0) {
						this.checkResult();
					} else {
						this.genNextRow();
					}
				}
				world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
			}
		}
	}
	
	private void checkResult() {
		this.isRunning = false;
		int leftId = OPTION_MAP.get(this.slotOptions[this.currentPos][0]);
		int midId = OPTION_MAP.get(this.slotOptions[this.currentPos][1]);
		int rightId = OPTION_MAP.get(this.slotOptions[this.currentPos][2]);
		if(leftId != midId && midId != rightId && leftId != rightId) {// nothing equal
			return ;
		} else if(leftId == midId && midId == rightId) { // all equal
			this.genBonusResult(leftId, 3);
		} else if(leftId == midId){
			this.genBonusResult(leftId, 1);
		} else if(leftId == rightId){
		 	this.genBonusResult(leftId, 1);
		} else if(rightId == midId){
			this.genBonusResult(rightId, 1);
		}
	}
	
	private void genBonusResult(int type, int num) {
		SlotOptions option = OPTION_LIST.get(type);
		if(option.isSun) {
			int cnt = (num == 1 ? 4 : 20);
			for(int i = 0; i < cnt; ++ i) {
				SunEntity sun = EntityRegister.SUN.get().create(world);
				sun.setAmount(25);
				EntityUtil.onMobEntityRandomPosSpawn(world, sun, pos, 2);
			}
			world.playSound(null, pos, SoundRegister.JEWEL_DROP.get(), SoundCategory.BLOCKS, 1F, 1F);
			return ;
		}
		for(int i = 0; i < num; ++ i) {
			if(option.isJewel) {
				JewelEntity jewel = EntityRegister.JEWEL.get().create(world);
				jewel.setAmount(1);
				EntityUtil.onMobEntityRandomPosSpawn(world, jewel, pos, 2);
			} else if(option.plantType.isPresent()){
				Plants plant = option.plantType.get();
				PlantCardItem item = PlantUtil.getPlantEnjoyCard(plant);
				this.handler.setStackInSlot(i, new ItemStack(item));
			}
		}
		world.playSound(null, pos, SoundRegister.JEWEL_DROP.get(), SoundCategory.BLOCKS, 1F, 1F);
	}
	
	private void genNextRow() {
		this.changeTick = this.getChangeTick();
		this.currentPos = (this.currentPos + 1) % 4;
		int next = (this.currentPos + 1) % 4;
		for(int i = 0; i < 3; ++ i) {
			this.slotOptions[next][i] = currentList.get(world.rand.nextInt(currentList.size())); 
//			System.out.print(this.slotOptions[next][i].plantType.isPresent() ? this.slotOptions[next][i].plantType.get() + " " : "X ");
		}
//		System.out.println("");
	}

	public void startRun(PlayerEntity player) {
		if(player == null) {
			System.out.println("Error : No player bind with Slot Machine !");
			return ;
		}
		this.player = player;
		this.isRunning = true;
		PlayerUtil.playClientSound(player, 8);
		PlayerUtil.addPlayerStats(player, Resources.SUN_NUM, - SUN_COST);
		PlayerUtil.addPlayerStats(player, Resources.LOTTERY_CHANCE, - 1);
		this.changeCnt = world.rand.nextInt(this.maxChangeCnt - this.minChangeCnt + 1) + this.minChangeCnt;
		this.refreshOptionList();
		this.genNextRow();
	}
	
	private void refreshOptionList() {
		this.currentList.clear();
		this.currentList.add(OPTION_LIST.get(0));
		int len = world.rand.nextInt(5) + 9;
		for(int i = 0; i < len; ++ i) {
			int now = world.rand.nextInt(sum);
			for(int j = 0; j < OPTION_LIST.size(); ++ j) {
				if(now < WEIGHT_LIST.get(j)) {
					this.currentList.add(OPTION_LIST.get(j));
					break;
				}
			}
		}
	}
	
	public int getChangeTick() {
		if(this.changeCnt <= 2) return 16;
		if(this.changeCnt <= 5) return 12;
		if(this.changeCnt <= 12) return 8;
		return 4;
	}
	
	private boolean canRun() {
		for(int i = 0; i < 3; ++ i) {
			if(! this.handler.getStackInSlot(i).isEmpty()) {
				return false;
			}
		}
		return ! this.isRunning;
	}
	
	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		return new SUpdateTileEntityPacket(pos, 1, getUpdateTag());
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
		handleUpdateTag(pkt.getNbtCompound());
	}
	
	@Override
	public void handleUpdateTag(CompoundNBT tag) {
		super.handleUpdateTag(tag);
		for(int i = 0; i < 16; ++ i) {
			if(tag.contains("slot_machine_" + i)){
				this.array.set(i, tag.getInt("slot_machine_" + i));
			}
		}
	}
	
	@Override
	public CompoundNBT getUpdateTag() {
		CompoundNBT compoundNBT = super.getUpdateTag();
		for(int i = 0; i < 16; ++ i) {
			compoundNBT.putInt("slot_machine_" + i, this.array.get(i));
		}
		return compoundNBT;
	}
	
	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		if(compound.contains("change_tick")) {
			this.changeTick = compound.getInt("change_tick");
		}
		if(compound.contains("change_cnt")) {
			this.changeCnt = compound.getInt("change_cnt");
		}
		if(compound.contains("is_machine_running")) {
			this.isRunning = compound.getBoolean("is_machine_running");
		}
		for(int i = 0; i < 4; ++ i) {
			for(int j = 0; j < 3; ++ j) {
				if(compound.contains("slot_option" + (i * 3 + j))){
					this.slotOptions[i][j] = OPTION_LIST.get(compound.getInt("slot_option" + (i * 3 + j)));
				}
			}
		}
		if(compound.contains("slot_machine_result")) {
			this.handler.deserializeNBT(compound.getCompound("slot_machine_result"));
		}
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.putInt("change_tick", this.changeTick);
		compound.putInt("change_cnt", this.changeCnt);
		compound.putBoolean("is_machine_running", this.isRunning);
		for(int i = 0; i < 4; ++ i) {
			for(int j = 0; j < 3; ++ j) {
				compound.putInt("slot_option" + (i * 3 + j), OPTION_MAP.get(this.slotOptions[i][j]));
			}
		}
		compound.put("slot_machine_result", this.handler.serializeNBT());
		return super.write(compound);
	}
	
	public boolean isUsableByPlayer(PlayerEntity player) {
		if (this.world.getTileEntity(this.pos) != this) {
			return false;
		}
		return player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
	}
	
	public void setPlayer(PlayerEntity player) {
		this.player = player;
	}
	
	@Override
	public Container createMenu(int id, PlayerInventory inv, PlayerEntity player) {
		return new SlotMachineContainer(id, player, this.pos);
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TranslationTextComponent("gui.pvz.slot_machine");
	}
	
	public static class SlotOptions {
		
		public final Optional<Plants> plantType;
		public final boolean isSun;
		public final boolean isJewel;
		public final int weight;
		
		public SlotOptions(int type, int weight) {
			this.plantType = Optional.empty();
			this.isSun = (type == 1);
			this.isJewel = (type == 2);
			this.weight = weight;
		}
		
		public SlotOptions(Plants plant, int weight) {
			this.plantType = Optional.of(plant);
			this.isSun = false;
			this.isJewel = false;
			this.weight = weight;
		}
		
	}

}
