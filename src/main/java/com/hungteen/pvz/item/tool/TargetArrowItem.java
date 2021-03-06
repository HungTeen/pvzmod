package com.hungteen.pvz.item.tool;

import com.hungteen.pvz.entity.bullet.TargetArrowEntity;
import com.hungteen.pvz.register.GroupRegister;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class TargetArrowItem extends ArrowItem {

	public TargetArrowItem() {
		super(new Item.Properties().tab(GroupRegister.PVZ_MISC));
	}

	@Override
	public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
		TargetArrowEntity arrowentity = new TargetArrowEntity(worldIn, shooter);
		return arrowentity;
	}
	
	@Override
	public boolean isInfinite(ItemStack stack, ItemStack bow, PlayerEntity player) {
		return false;
	}

}
