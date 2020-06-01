package com.hungteen.pvzmod.util;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AIUtil {

	public static float getBreakSpeed(EntityLivingBase entity, World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		ItemStack heldItem = entity.getHeldItem(EnumHand.MAIN_HAND);

		float f = heldItem.isEmpty() ? 1F : heldItem.getDestroySpeed(state);

		if (f > 1.0F) {
			int i = EnchantmentHelper.getEfficiencyModifier(entity);

			if (i > 0 && !heldItem.isEmpty()) {
				f += (float) (i * i + 1);
			}
		}

		if (entity.isPotionActive(MobEffects.HASTE)) {
			f *= 1.0F + (float) (entity.getActivePotionEffect(MobEffects.HASTE).getAmplifier() + 1) * 0.2F;
		}

		if (entity.isPotionActive(MobEffects.MINING_FATIGUE)) {
			float f1;

			switch (entity.getActivePotionEffect(MobEffects.MINING_FATIGUE).getAmplifier()) {
			case 0:
				f1 = 0.3F;
				break;
			case 1:
				f1 = 0.09F;
				break;
			case 2:
				f1 = 0.0027F;
				break;
			case 3:
			default:
				f1 = 8.1E-4F;
			}

			f *= f1;
		}

		if (entity.isInsideOfMaterial(Material.WATER) && !EnchantmentHelper.getAquaAffinityModifier(entity)) {
			f /= 5.0F;
		}

		if (!entity.onGround) {
			f /= 5.0F;
		}

		return (f < 0 ? 0 : f);
	}

	public static float getBlockStrength(EntityLivingBase entity, World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		float hardness = state.getBlockHardness(world, pos);

		if (hardness <= 0F) {
			return 0F;
		}

		ItemStack heldItem = entity.getHeldItem(EnumHand.MAIN_HAND);
		boolean canHarvest = state.getMaterial().isToolNotRequired()
				|| (!heldItem.isEmpty() && heldItem.canHarvestBlock(state));

		return getBreakSpeed(entity, world, pos) / hardness / (canHarvest ? 30F : 100F);
	}
}
