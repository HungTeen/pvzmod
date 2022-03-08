package com.hungteen.pvz.common.item.tool.zombie;

import com.hungteen.pvz.common.item.PVZItemGroups;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;

import java.util.List;

public class ScreenDoorItem extends ShieldItem{

	public ScreenDoorItem() {
		super(new Item.Properties().stacksTo(1).durability(1600).tab(PVZItemGroups.PVZ_USEFUL));
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 144000;
	}
	
	@Override
	public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
	}
	
	@Override
	public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == Items.IRON_INGOT;
	}
	
	@Override
	public boolean isShield(ItemStack stack, LivingEntity entity) {
		return true;
	}
	
}
