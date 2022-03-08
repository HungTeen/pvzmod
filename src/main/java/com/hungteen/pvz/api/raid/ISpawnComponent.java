package com.hungteen.pvz.api.raid;

import com.google.gson.JsonObject;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;

public interface ISpawnComponent {

	/**
	 * make sure constructer has no argument, 
	 * and use this method to initiate instance.
	 */
	boolean readJson(JsonObject json);
	
	int getSpawnTick();
	
	int getSpawnAmount();
	
	IPlacementComponent getPlacement();
	
	CompoundTag getNBT();
	
	EntityType<?> getSpawnType();
	
}
