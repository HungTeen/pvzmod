package com.hungteen.pvz.item.tool;

import java.util.List;

import com.hungteen.pvz.capabilities.CapabilityHandler;
import com.hungteen.pvz.entity.bullet.PeaEntity;
import com.hungteen.pvz.gui.container.PeaGunContainer;
import com.hungteen.pvz.item.tool.card.PlantCardItem;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.GroupRegister;
import com.hungteen.pvz.register.ItemRegister;
import com.hungteen.pvz.utils.ItemUtil;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class PeaGunItem extends Item{

	public static final int PEA_GUN_SLOT_NUM = 28;
	public static final float PEA_SPEED = 2.1f;
	public static final double SHOOT_OFFSET = 0.5;
	public static final Plants[] PEA_PLANTS = new Plants[] {Plants.PEA_SHOOTER, Plants.SNOW_PEA, Plants.REPEATER, Plants.THREE_PEATER};
	private Inventory backpack = new Inventory(PEA_GUN_SLOT_NUM);
	
	public PeaGunItem() {
		super(new Properties().group(GroupRegister.PVZ_MISC).maxStackSize(1));
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		if(!worldIn.isRemote) {
			if(handIn == Hand.MAIN_HAND) {
				this.checkAndShootPea(worldIn, playerIn, playerIn.getHeldItemMainhand());
		    }else { // offhand open gui
		    	if(playerIn instanceof ServerPlayerEntity) {
		    		NetworkHooks.openGui((ServerPlayerEntity) playerIn, new INamedContainerProvider() {

						@Override
						public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_,
								PlayerEntity p_createMenu_3_) {
							return new PeaGunContainer(p_createMenu_1_, p_createMenu_3_);
						}

						@Override
						public ITextComponent getDisplayName() {
							return new TranslationTextComponent("gui.pvz.pea_gun.show");
						}
					});
		    	}
		    }
		}
		return ActionResult.resultPass(playerIn.getHeldItem(handIn));
	}
	
	protected void checkAndShootPea(World world, PlayerEntity player, ItemStack itemStack) {
		ItemUtil.restoreFromItemStack(itemStack, this.backpack);
		ItemStack special =this.backpack.getStackInSlot(0);
		if(special.getItem() instanceof PlantCardItem) {
			Plants plant = ((PlantCardItem) special.getItem()).getPlant();
			for(int i = 1;i < PEA_GUN_SLOT_NUM;i ++) {
				ItemStack stack = this.backpack.getStackInSlot(i);
				if(stack.isEmpty()) {
					continue;
				}
				if(this.canShoot(player, plant, stack.getItem())) {
					stack.shrink(1);
					player.playSound(SoundEvents.ENTITY_SNOW_GOLEM_SHOOT, 0.8f, 0.8f);
					setPlayerCoolDownTick(player);
				}
				break;
			}
		}
		ItemUtil.convertToItemStack(itemStack, this.backpack);
	}
	
	protected boolean canShoot(PlayerEntity player, Plants plant, Item item) {
		switch (plant) {
		case PEA_SHOOTER:
		case SNOW_PEA:{
			this.performShoot(player, plant, item, 0); //normal shoot
			return true;
		}
		case REPEATER:{
			this.performShoot(player, plant, item, 0);
			this.performShoot(player, plant, item, 1);
			return true;
		}
		case THREE_PEATER:{
			this.performShoot(player, plant, item, 0);
			this.performShoot(player, plant, item, 1);
			this.performShoot(player, plant, item, 2);
			return true;
		}
		default:{
			return false;
		}
		}
	}
	
	private void performShoot(PlayerEntity player, Plants plant, Item item, int type) {
		player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
			int plantLvl = l.getPlayerData().getPlantStats().getPlantLevel(plant);
			PeaEntity pea = new PeaEntity(EntityRegister.PEA.get(), player.world, player, getPeaType(plantLvl), getPeaState(plant, item));
		    Vec3d vec = player.getLookVec();
		    Vec3d offset = vec.scale(SHOOT_OFFSET);
		    pea.setPosition(player.getPosX() + offset.x, player.getPosY() + player.getEyeHeight() + offset.y, player.getPosZ() + offset.z);
		    if(plant == Plants.REPEATER) {
		    	if(type == 1) {
		    		offset = offset.scale(0.5);
		    		pea.setPosition(player.getPosX() + offset.x, player.getPosY() + player.getEyeHeight() + offset.y, player.getPosZ() + offset.z);
		    	}
		    }else if(plant == Plants.THREE_PEATER) {
		    	if(type == 1) {
		    		pea.setPosition(player.getPosX() + offset.x + offset.z, player.getPosY() + player.getEyeHeight() + offset.y, player.getPosZ() + offset.z - offset.x);
		    	}else if(type == 2) {
		    		pea.setPosition(player.getPosX() + offset.x - offset.z, player.getPosY() + player.getEyeHeight() + offset.y, player.getPosZ() + offset.z + offset.x);
		    	}
		    }
		    pea.setMotion(vec.scale(PEA_SPEED));
		    player.world.addEntity(pea);
		});
	}
	
	private PeaEntity.State getPeaState(Plants plant, Item item){
		if(plant == Plants.SNOW_PEA) return PeaEntity.State.ICE;
		if(item == ItemRegister.SNOW_PEA.get()) return PeaEntity.State.ICE;
		if(item == ItemRegister.FLAME_PEA.get()) return PeaEntity.State.FIRE;
		if(item == ItemRegister.BLUE_FLAME_PEA.get()) return PeaEntity.State.BLUE_FIRE;
		return PeaEntity.State.NORMAL;
	}
	
	private PeaEntity.Type getPeaType(int lvl){
		if(lvl<=6) return PeaEntity.Type.NORMAL;
		else if(lvl<=13) return PeaEntity.Type.BIG;
		return PeaEntity.Type.HUGE;
	}
	
	protected void setPlayerCoolDownTick(PlayerEntity player) {
		player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
			int lvl = l.getPlayerData().getPlayerStats().getPlayerStats(Resources.TREE_LVL);
			int now = (lvl - 1) / 10;
			int CD = 30 - 2 * now;
			if(lvl > 90) CD -= 2;
			player.getCooldownTracker().setCooldown(this, CD);
		});
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("tooltip.pvz.pea_gun").applyTextStyle(TextFormatting.GREEN));
	}
	
	@Override
	public boolean canEquip(ItemStack stack, EquipmentSlotType armorType, Entity entity) {
		return armorType == EquipmentSlotType.HEAD;
	}
	
}
