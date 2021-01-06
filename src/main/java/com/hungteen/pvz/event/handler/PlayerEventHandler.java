package com.hungteen.pvz.event.handler;

import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;

public class PlayerEventHandler {

	/**
	 * player use plant card.
	 * 1. ground use(peashooter).
	 * 2. water use(seashroom).
	 * 3. nut heal use(wallnut).
	 * 4. pumpkin use(pumpkin).
	 * 5. specific entity use(coffeebean).
	 * 6. specific block use(lilypad).
	 * 7. upgrade plant use(gatlingpea)
	 */
	
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
