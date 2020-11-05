package com.hungteen.pvz.item.tool;

import java.util.List;

import com.hungteen.pvz.register.GroupRegister;
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
		super(new Item.Properties().maxStackSize(1).maxDamage(1600).group(GroupRegister.PVZ_MISC));
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 144000;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
	}
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == ItemRegister.STEEL_INGOT.get();
	}
	
	@Override
	public boolean isShield(ItemStack stack, LivingEntity entity) {
		return true;
	}
	
}
