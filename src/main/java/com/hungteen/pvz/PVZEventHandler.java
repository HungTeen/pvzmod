package com.hungteen.pvz;

import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.item.tool.card.PlantCardItem;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class PVZEventHandler {

	public static void onPlantEntitySummonByPlayer(PlayerEntity player, PVZPlantEntity plant, PlantCardItem card, ItemStack stack, int lvl) {
		plant.onSpawnedByPlayer(player, lvl);
		PlantCardItem.onUsePlantCard(player, stack, card, lvl);
		
	}
	
}
