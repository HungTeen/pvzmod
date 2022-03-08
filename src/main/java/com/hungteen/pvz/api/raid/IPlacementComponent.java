package com.hungteen.pvz.api.raid;

import com.google.gson.JsonElement;

import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;

public interface IPlacementComponent {

	Mth getPlacePosition(Level world, Mth origin);
	
	/**
	 * make sure constructer has no argument, 
	 * and use this method to initiate instance.
	 */
	void readJson(JsonElement json);
}
