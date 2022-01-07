package com.hungteen.pvz.common.impl.raid.placement;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hungteen.pvz.api.raid.IPlacementComponent;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class OffsetPlacement implements IPlacementComponent {
	
	public static final String NAME = "offset";
	private BlockPos center = null;
	private BlockPos offset = BlockPos.ZERO;
	
	@Override
	public BlockPos getPlacePosition(World world, BlockPos origin) {
		final BlockPos now = this.center == null ? origin : this.center;
		return now.offset(offset.getX(), offset.getY(), offset.getZ());
	}
	
	@Override
	public void readJson(JsonElement json) {
		JsonObject obj = json.getAsJsonObject();
		if(obj != null) {
			if(obj.has("x") && obj.has("y") && obj.has("z")) {
				this.center = new BlockPos(JSONUtils.getAsInt(obj, "x"), JSONUtils.getAsInt(obj, "y"), JSONUtils.getAsInt(obj, "z"));
			}
			if(obj.has("dx") && obj.has("dy") && obj.has("dz")) {
				this.offset = new BlockPos(JSONUtils.getAsInt(obj, "dx"), JSONUtils.getAsInt(obj, "dy"), JSONUtils.getAsInt(obj, "dz"));
			}
		}
	}

}
