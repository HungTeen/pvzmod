package com.hungteen.pvz.common.impl.challenge.placement;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hungteen.pvz.api.raid.IPlacementComponent;
import com.hungteen.pvz.utils.MathUtil;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.gen.Heightmap;

public class CenterPlacement implements IPlacementComponent {
	
	public static final String NAME = "center";
	private boolean onSurface;
	private int radius;
	
	@Override
	public Mth getPlacePosition(Level world, Mth origin) {
		final int dx = MathUtil.getRandomInRange(world.getRandom(), this.radius);
		final int dz = MathUtil.getRandomInRange(world.getRandom(), this.radius);
		final int height = this.onSurface ? world.getHeight(Heightmap.Type.WORLD_SURFACE, origin.getX() + dx, origin.getZ() + dz) : origin.getY();
		return new Mth(origin.getX() + dx, height, origin.getZ() + dz);
	}
	
	@Override
	public void readJson(JsonElement json) {
		JsonObject obj = json.getAsJsonObject();
		if(obj != null) {
			this.radius = JSONUtils.getAsInt(obj, "radius", 1);
			this.onSurface = JSONUtils.getAsBoolean(obj, "ground", true);
		}
	}

}
