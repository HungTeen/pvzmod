package com.hungteen.pvz.event.handler;

import com.hungteen.pvz.capability.CapabilityHandler;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;

public class PlayerEventHandler {

	/**
	 * run when player right click plantEntity with shovel.
	 */
	public static void onPlantShovelByPlayer(PlayerEntity player, PVZPlantEntity plant, ItemStack stack) {
		if(player.abilities.isCreativeMode || player.getUniqueID().equals(plant.getOwnerUUID()) || ! EntityUtil.checkCanEntityAttack(plant, player)) {
//			Plants type = plant.getPlantEnumName();
//			player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l) -> {
//				int lvl = l.getPlayerData().getPlantStats().getPlantLevel(type);
//				int sunCost
//			});
			EntityUtil.playSound(plant, SoundEvents.BLOCK_GRASS_BREAK);
			plant.remove();
			stack.damageItem(3, player, (p) -> {p.sendBreakAnimation(Hand.MAIN_HAND);});
		}
	}
	
}
