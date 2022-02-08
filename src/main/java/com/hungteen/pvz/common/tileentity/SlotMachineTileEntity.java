package com.hungteen.pvz.common.tileentity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import javax.annotation.Nullable;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.events.LotteryEvent;
import com.hungteen.pvz.common.advancement.trigger.SlotMachineTrigger;
import com.hungteen.pvz.common.container.SlotMachineContainer;
import com.hungteen.pvz.common.datapack.LotteryTypeLoader;
import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.common.entity.misc.drop.JewelEntity;
import com.hungteen.pvz.common.entity.misc.drop.SunEntity;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.enums.Resources;
import com.hungteen.pvz.utils.others.WeightList;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.INameable;
import net.minecraft.util.IntArray;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.Constants;

/**
 * 1. get resource of lottery type. 2. press button to choose fast start or slow
 * start. 3. randomly get a short list from the default list to form a weight
 * list. 4.
 */
public class SlotMachineTileEntity extends PVZTileEntity implements ITickableTileEntity, INamedContainerProvider, INameable {

//	public final ItemStackHandler handler = new ItemStackHandler(3);
	/*
	 * 0 - 11 : slot types. 12 : change tick. 13 : current pos. 14 : running or not.
	 * 15 : change cd.
	 */
	public final IIntArray array = new IntArray(16);
//	public static final SlotType EMPTY = new SlotType(SlotTypes.EMPTY);
	public final SlotType[][] SlotOptions = new SlotType[4][3];
	protected final List<SlotType> List = new ArrayList<>();
	private Map<SlotType, Integer> optionMap;
	protected final Random rand = new Random();
	protected ResourceLocation resource;
	private LotteryType lotteryType;
	public int currentPos = 1;
	private final int minChangeCnt = 12;
	private final int maxChangeCnt = 24;
	private int changeCnt;
	private int changeTick = 0;
	private boolean isRunning = false;
	private PlayerEntity player;
	private ITextComponent name;

	public SlotMachineTileEntity() {
		super(TileEntityRegister.SLOT_MACHINE.get());
	}

	public void fastStart(PlayerEntity player) {
		this.onStart(player);
		this.refreshOptionList();
		this.genAll();
		this.checkResult();

		// sync.
		for (int i = 0; i < 4; ++i) {
			for (int j = 0; j < 3; ++j) {
				int id = i * 3 + j;
				this.array.set(id, this.getOptionMap().get(this.SlotOptions[i][j]));
			}
		}
		this.level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
	}

	public void slowStart(PlayerEntity player) {
		this.onStart(player);
		this.refreshOptionList();
		this.genNextRow();
		this.changeCnt = level.random.nextInt(this.maxChangeCnt - this.minChangeCnt + 1) + this.minChangeCnt;

	}

	@Override
	public void tick() {
		if (!level.isClientSide) {
			if (this.getLotteryType() == null) {// wait for data pack sync.
				return;
			}

//			if(this.rand.nextDouble() < 0.01) {
//				for(int i = 0; i < 4; ++ i) {
//					for(int j = 0; j < 3; ++ j) {
//						System.out.print(this.SlotOptions[i][j].getStack().isPresent() ? this.SlotOptions[i][j].getStack().get() + ", " : "empty, ");
//					}
//					System.out.println();
//				}
//				System.out.println(this.currentPos);
//			}
//			
			// sync.
			for (int i = 0; i < 4; ++i) {
				for (int j = 0; j < 3; ++j) {
					final int id = i * 3 + j;
					this.array.set(id, this.getOptionMap().get(this.SlotOptions[i][j]));
				}
			}
			this.array.set(12, this.changeTick);
			this.array.set(13, this.currentPos);
			this.array.set(14, this.canRun() ? 1 : 0);
			this.array.set(15, this.getChangeTick());

			// run.
			if (this.changeTick > 0) {
				if (this.List.isEmpty()) {
					this.changeTick = 0;
					this.changeCnt = 0;
					return;
				}
				--this.changeTick;
				if (this.changeTick == 0) {
					--this.changeCnt;
					if (this.changeCnt == 0) {
						this.checkResult();
					} else {
						this.genNextRow();
					}
				}
				this.level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(),
						Constants.BlockFlags.BLOCK_UPDATE);
			}
		}
	}

	private void onStart(PlayerEntity player) {
		if (player == null) {
			System.out.println("Error : No player bind with Slot Machine !");
			return;
		}
		this.player = player;
		this.isRunning = true;
		PlayerUtil.playClientSound(player, SoundRegister.SLOT_MACHINE.get());
		PlayerUtil.addResource(player, Resources.SUN_NUM, -this.getSunCost());
		PlayerUtil.addResource(player, Resources.LOTTERY_CHANCE, -1);
	}

	protected void checkResult() {
		final int leftId = this.getOptionMap().get(this.SlotOptions[this.currentPos][0]);
		final int midId = this.getOptionMap().get(this.SlotOptions[this.currentPos][1]);
		final int rightId = this.getOptionMap().get(this.SlotOptions[this.currentPos][2]);
		this.isRunning = false;
		if (leftId != midId && midId != rightId && leftId != rightId) {// nothing equal.
			return;
		} else if (leftId == midId && midId == rightId) { // all equal.
			this.genBonusResult(leftId, 3);
		} else if (leftId == midId) {
			this.genBonusResult(leftId, 1);
		} else if (leftId == rightId) {
			this.genBonusResult(leftId, 1);
		} else if (rightId == midId) {
			this.genBonusResult(rightId, 1);
		}
	}

	private void genBonusResult(int id, int num) {
		final SlotType type = this.getLotteryType().getSlotType(id);
		
		switch (type.getSlotTypes()) {
		case ITEM: {
			for (int i = 0; i < num; ++i) {
				final ItemStack newStack = type.stack.get().copy();
				InventoryHelper.dropItemStack(level, worldPosition.getX(), worldPosition.getY() + 1, worldPosition.getZ(), newStack);
			}
			break;
		}
		case SUN: {
			final int amount = (num == 1 ? 3 : 10) * this.getSunCost();
			SunEntity.spawnSunsByAmount(this.level, worldPosition, amount, 50, 2);
			break;
		}
		case JEWEL: {
			for (int i = 0; i < (num == 1 ? 2 : 7); ++i) {
				JewelEntity jewel = EntityRegister.JEWEL.get().create(this.level);
				jewel.setAmount(1);
				EntityUtil.onEntityRandomPosSpawn(this.level, jewel, worldPosition, 3);
			}
			break;
		}
		case EVENT: {
			MinecraftForge.EVENT_BUS.post(new LotteryEvent(this, this.player, type, num));
			break;
		}
		default:
			break;
		}

		if(player instanceof ServerPlayerEntity){
			SlotMachineTrigger.INSTANCE.trigger((ServerPlayerEntity) player, num, type.getSlotTypes().toString().toLowerCase());
		}

		this.level.playSound(null, worldPosition, SoundRegister.JEWEL_DROP.get(), SoundCategory.BLOCKS, 1F, 1F);
	}

	private void genAll() {
		for (int i = 0; i < 4; ++i) {
			for (int j = 0; j < 3; ++j) {
				this.SlotOptions[i][j] = List.get(level.random.nextInt(List.size()));
			}
		}
	}

	private void genNextRow() {
		this.changeTick = this.getChangeTick();
		this.currentPos = (this.currentPos + 1) % 4;
		int next = (this.currentPos + 1) % 4;
		for (int i = 0; i < 3; ++i) {
			this.SlotOptions[next][i] = List.get(level.random.nextInt(List.size()));
		}
	}

	private void refreshOptionList() {
		this.List.clear();
		final int len = this.getLotteryType().getSlotCount();
		if (len == 0) {
			PVZMod.LOGGER.error("Slot Machine TE : Error ! Why there is a zero length ?");
			return;
		}

		for (int i = 0; i < len; ++i) {
			final SlotType type = this.getLotteryType().getSlotType(this.rand);
			this.List.add(type);
		}
	}

	public int getChangeTick() {
		if (this.changeCnt <= 2)
			return 16;
		if (this.changeCnt <= 5)
			return 12;
		if (this.changeCnt <= 12)
			return 8;
		return 4;
	}

	private boolean canRun() {
		// slot is full.
//		for (int i = 0; i < 3; ++i) {
//			if (!this.inv.getItem(i).isEmpty()) {
//				return false;
//			}
//		}
		// no enough sun or lottery chance.
		if (PlayerUtil.getResource(this.player, Resources.SUN_NUM) < this.getSunCost()
				|| PlayerUtil.getResource(this.player, Resources.LOTTERY_CHANCE) <= 0) {
			return false;
		}
		return !this.isRunning;
	}

	public void setPlayer(PlayerEntity player) {
		this.player = player;
	}

	public void init(ResourceLocation res) {
		this.resource = res;
		for (int i = 0; i < 4; ++i) {
			for (int j = 0; j < 3; ++j) {
				this.SlotOptions[i][j] = this.getLotteryType().getSlotType(this.rand);
			}
		}
		this.level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
	}

	@Override
	public Container createMenu(int id, PlayerInventory inv, PlayerEntity player) {
		return new SlotMachineContainer(id, player, this.worldPosition);
	}

	public void setCustomName(ITextComponent name) {
		this.name = name;
	}

	@Override
	public ITextComponent getDisplayName() {
		return this.getName();
	}
	
	@Override
	public ITextComponent getName() {
		return this.name != null ? this.name : this.getDefaultName();
	}

	@Nullable
	public ITextComponent getCustomName() {
		return this.name;
	}

	public ITextComponent getDefaultName() {
		return new TranslationTextComponent("block.pvz.slot_machine");
	}

	public LotteryType getLotteryType() {
		return this.lotteryType == null ? this.lotteryType = LotteryTypeLoader.LOTTERIES.get(this.resource)
				: this.lotteryType;
	}

	public Map<SlotType, Integer> getOptionMap() {
		if (this.optionMap == null) {
			this.optionMap = new HashMap<>();
			if(this.getLotteryType() != null) {
				this.getLotteryType().updateMap(this.optionMap);
			}
		}
		return this.optionMap;
	}

	public int getSunCost() {
		return this.getLotteryType().getSunCost();
	}

	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		return new SUpdateTileEntityPacket(worldPosition, 1, getUpdateTag());
	}

	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
		handleUpdateTag(this.level.getBlockState(getBlockPos()), pkt.getTag());
	}

	@Override
	public void handleUpdateTag(BlockState state, CompoundNBT tag) {
		super.handleUpdateTag(state, tag);
		for (int i = 0; i < 16; ++i) {
			if (tag.contains("slot_machine_" + i)) {
				this.array.set(i, tag.getInt("slot_machine_" + i));
			}
		}

		if (tag.contains("lottery_type")) {
			this.resource = new ResourceLocation(tag.getString("lottery_type"));
		}
	}

	@Override
	public CompoundNBT getUpdateTag() {
		CompoundNBT compoundNBT = super.getUpdateTag();
		for (int i = 0; i < 16; ++i) {
			compoundNBT.putInt("slot_machine_" + i, this.array.get(i));
		}

		if (this.resource != null) {
			compoundNBT.putString("lottery_type", this.resource.toString());
		}

		return compoundNBT;
	}

	@Override
	public void load(BlockState state, CompoundNBT compound) {
		super.load(state, compound);

		if (compound.contains("change_tick")) {
			this.changeTick = compound.getInt("change_tick");
		}

		if (compound.contains("change_cnt")) {
			this.changeCnt = compound.getInt("change_cnt");
		}

		if (compound.contains("is_machine_running")) {
			this.isRunning = compound.getBoolean("is_machine_running");
		}

		if (compound.contains("lottery_type")) {
			this.resource = new ResourceLocation(compound.getString("lottery_type"));
		}

		for (int i = 0; i < 4; ++i) {
			for (int j = 0; j < 3; ++j) {
				if (compound.contains("slot_option" + (i * 3 + j))) {
					this.SlotOptions[i][j] = this.getLotteryType()
							.getSlotType(compound.getInt("slot_option" + (i * 3 + j)));
				}
			}
		}

//		if (compound.contains("slot_machine_result")) {
//			this.inv.fromTag((ListNBT) compound.get("slot_machine_result"));
//		}
		
		if (compound.contains("CustomName", 8)) {
	         this.name = ITextComponent.Serializer.fromJson(compound.getString("CustomName"));
	      }
	}

	@Override
	public CompoundNBT save(CompoundNBT compound) {
		compound.putInt("change_tick", this.changeTick);

		compound.putInt("change_cnt", this.changeCnt);

		compound.putBoolean("is_machine_running", this.isRunning);

		if (this.resource != null) {
			compound.putString("lottery_type", this.resource.toString());
		}
		
		for (int i = 0; i < 4; ++i) {
			for (int j = 0; j < 3; ++j) {
				if(this.getOptionMap().containsKey(this.SlotOptions[i][j])) {
				    compound.putInt("slot_option" + (i * 3 + j), this.getOptionMap().get(this.SlotOptions[i][j]));
				}
			}
		}

//		compound.put("slot_machine_result", this.inv.createTag());
		
		if (this.name != null) {
			compound.putString("CustomName", ITextComponent.Serializer.toJson(this.name));
	      }

		return super.save(compound);
	}

	public static class SlotType {
		private Optional<ItemStack> stack = Optional.empty();
		private String identity = "";
		private final SlotTypes slotTypes;

		public SlotType(SlotTypes type) {
			this.slotTypes = type;
		}

		public void setItemStack(ItemStack stack) {
			this.stack = Optional.ofNullable(stack);
		}

		public SlotTypes getSlotTypes() {
			return slotTypes;
		}

		public Optional<ItemStack> getStack() {
			return stack;
		}

		public void setIdentity(String identity) {
			this.identity = identity;
		}

		public String getIdentity() {
			return identity;
		}
	}

	public static class LotteryType {

		public static final ResourceLocation ALL_PLANTS = StringUtil.prefix("all_plants");
		private final WeightList<SlotType> list = new WeightList<>();
		private LotteryTypes lotteryTypes = LotteryTypes.NORMAL;
		private final ResourceLocation res;
		private int slotCount;
		private int sunCost;
		private int tradeWeight;
		private int tradePrice;

		public LotteryType(ResourceLocation res) {
			this.res = res;
		}

		public void addSlotType(SlotType type, int w) {
			list.addItem(type, w);
		}

		public void setLotteryTypes(LotteryTypes lotteryTypes) {
			this.lotteryTypes = lotteryTypes;
		}

		public LotteryTypes getLotteryTypes() {
			return this.lotteryTypes;
		}

		public ResourceLocation getResource() {
			return this.res;
		}

		public SlotType getSlotType(int pos) {
			return this.list.getItemList().get(pos);
		}

		public void setSunCost(int cost) {
			this.sunCost = cost;
		}

		public int getSunCost() {
			return sunCost;
		}

		public void setSlotCount(int slotCount) {
			this.slotCount = slotCount;
		}

		public int getSlotCount() {
			return slotCount;
		}

		public SlotType getSlotType(Random rand) {
			return this.list.getRandomItem(rand).get();
		}

		public void updateMap(Map<SlotType, Integer> map) {
			map.clear();

			final List<SlotType> list = this.list.getItemList();
			for (int i = 0; i < list.size(); ++i) {
				map.put(list.get(i), i);
			}
		}

		public int getTradePrice() {
			return tradePrice;
		}

		public int getTradeWeight() {
			return tradeWeight;
		}

		public void setTradePrice(int tradePrice) {
			this.tradePrice = tradePrice;
		}

		public void setTradeWeight(int tradeWeight) {
			this.tradeWeight = tradeWeight;
		}

		public int getSize() {
			return this.list.getLen();
		}
	}

	public enum LotteryTypes {
		NORMAL, ALL_PLANT_CARDS, ALL_ZOMBIE_CARDS, ALL_SUMMON_CARDS
	}

	public enum SlotTypes {
//		EMPTY,//nothing
		ITEM, SUN, JEWEL, TREE_XP, COIN, EVENT // for event handler.
	}

}
