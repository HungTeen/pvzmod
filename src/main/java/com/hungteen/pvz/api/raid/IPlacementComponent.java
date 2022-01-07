package com.hungteen.pvz.api.raid;

import com.google.gson.JsonElement;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IPlacementComponent {

	BlockPos getPlacePosition(World world, BlockPos origin);
	
	/**
	 * make sure constructer has no argument, 
	 * and use this method to initiate instance.
	 */
	void readJson(JsonElement json);
}
