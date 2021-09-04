package com.hungteen.pvz.common.item.tool;

import java.util.List;

import com.hungteen.pvz.common.item.PVZItemGroups;
import com.hungteen.pvz.register.ItemRegister;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class ScreenDoorItem extends ShieldItem{

	public ScreenDoorItem() {
		super(new Item.Properties().stacksTo(1).durability(1600).tab(PVZItemGroups.PVZ_MISC));
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 144000;
	}
	
	@Override
	public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
	}
	
	@Override
	public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == ItemRegister.STEEL_INGOT.get();
	}
	
	@Override
	public boolean isShield(ItemStack stack, LivingEntity entity) {
		return true;
	}
	
}
