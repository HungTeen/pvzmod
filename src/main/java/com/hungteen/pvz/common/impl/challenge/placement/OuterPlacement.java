package com.hungteen.pvz.common.impl.challenge.placement;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hungteen.pvz.api.raid.IPlacementComponent;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;

public class OuterPlacement implements IPlacementComponent {
	
	public static final String NAME = "outer";
	private boolean onSurface;
	private int min;
	private int max;
	
	@Override
	public BlockPos getPlacePosition(World world, BlockPos origin) {
		this.max = Math.max(this.max, this.min);//prevent wrong json by others.
		final double radius = world.getRandom().nextDouble() * (this.max - this.min) + this.min;
		final double delta = world.getRandom().nextDouble() * 2 * 3.14159;
		final int dx = (int) (radius * Math.sin(delta));
		final int dz = (int) (radius * Math.cos(delta));
		final int height = this.onSurface ? world.getHeight(Heightmap.Type.WORLD_SURFACE, origin.getX() + dx, origin.getZ() + dz) : origin.getY();
		return new BlockPos(origin.getX() + dx, height, origin.getZ() + dz);
	}
	
	@Override
	public void readJson(JsonElement json) {
		JsonObject obj = json.getAsJsonObject();
		if(obj != null) {
			this.min = JSONUtils.getAsInt(obj, "min");
			this.max = JSONUtils.getAsInt(obj, "max");
			this.onSurface = JSONUtils.getAsBoolean(obj, "ground", true);
		}
	}

}
