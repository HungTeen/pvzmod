package com.hungteen.pvz.event.handler;

import com.hungteen.pvz.enchantment.EnchantmentUtil;
import com.hungteen.pvz.entity.drop.SunEntity;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

public class PlayerEventHandler {
	
	/**
	 * run when player right click plantEntity with shovel.
	 */
	public static void onPlantShovelByPlayer(PlayerEntity player, PVZPlantEntity plant, ItemStack stack) {
		if(player.abilities.isCreativeMode || player.getUniqueID().equals(plant.getOwnerUUID()) || ! EntityUtil.checkCanEntityAttack(plant, player)) {
			if(plant.getOuterPlantType().isPresent()) {//has outer plant, shovel outer plant.
				SunEntity.spawnSunsByAmount(player.world, plant.getPosition(), EnchantmentUtil.getSunShovelAmount(stack, plant.outerSunCost));
				plant.removeOuterPlant();
			} else {
				SunEntity.spawnSunsByAmount(player.world, plant.getPosition(), EnchantmentUtil.getSunShovelAmount(stack, plant.plantSunCost));
				plant.remove();
			}
			EntityUtil.playSound(plant, SoundRegister.PLANT_ON_GROUND.get());
			stack.damageItem(3, player, (p) -> {p.sendBreakAnimation(Hand.MAIN_HAND);});
		}
	}
	
}
