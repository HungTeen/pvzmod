package com.hungteen.pvz.common.impl.challenge.placement;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hungteen.pvz.api.raid.IPlacementComponent;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;

public class OffsetPlacement implements IPlacementComponent {
	
	public static final String NAME = "offset";
	private Mth center = null;
	private Mth offset = Mth.ZERO;
	
	@Override
	public Mth getPlacePosition(Level world, Mth origin) {
		final Mth now = this.center == null ? origin : this.center;
		return now.offset(offset.getX(), offset.getY(), offset.getZ());
	}
	
	@Override
	public void readJson(JsonElement json) {
		JsonObject obj = json.getAsJsonObject();
		if(obj != null) {
			if(obj.has("x") && obj.has("y") && obj.has("z")) {
				this.center = new Mth(JSONUtils.getAsInt(obj, "x"), JSONUtils.getAsInt(obj, "y"), JSONUtils.getAsInt(obj, "z"));
			}
			if(obj.has("dx") && obj.has("dy") && obj.has("dz")) {
				this.offset = new Mth(JSONUtils.getAsInt(obj, "dx"), JSONUtils.getAsInt(obj, "dy"), JSONUtils.getAsInt(obj, "dz"));
			}
		}
	}

}
