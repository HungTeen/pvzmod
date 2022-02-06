package com.hungteen.pvz.common.item.tool.zombie;

import com.hungteen.pvz.common.entity.bullet.TargetArrowEntity;
import com.hungteen.pvz.common.item.PVZItemGroups;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class TargetArrowItem extends ArrowItem {

	public TargetArrowItem() {
		super(new Item.Properties().tab(PVZItemGroups.PVZ_USEFUL));
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
