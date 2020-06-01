package com.hungteen.pvzmod.items.food;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemAppleJuice extends ItemJuiceBase{

	public ItemAppleJuice(String name, int amount, float saturation, boolean isWolfFood) {
		super(name, amount, saturation, isWolfFood);
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		return super.onItemUseFinish(stack, worldIn, entityLiving);
	}
}
