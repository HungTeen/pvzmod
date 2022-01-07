package com.hungteen.pvz.common.impl.raid.placement;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hungteen.pvz.api.raid.IPlacementComponent;
import com.hungteen.pvz.utils.MathUtil;
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
		final int dx = (world.getRandom().nextDouble() < 0.5 ? 1 : -1) * MathUtil.getRandomMinMax(world.getRandom(), this.min, this.max);
		final int dz = (world.getRandom().nextDouble() < 0.5 ? 1 : -1) * MathUtil.getRandomMinMax(world.getRandom(), this.min, this.max);
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
