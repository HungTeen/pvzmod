package com.hungteen.pvz.common.impl.challenge;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.hungteen.pvz.api.raid.IPlacementComponent;
import com.hungteen.pvz.api.raid.ISpawnComponent;
import com.hungteen.pvz.api.raid.IWaveComponent;
import com.hungteen.pvz.common.world.challenge.ChallengeManager;
import com.hungteen.pvz.utils.StringUtil;
import net.minecraft.util.JSONUtils;

import java.util.ArrayList;
import java.util.List;

public class WaveComponent implements IWaveComponent {

	public static final String NAME = "default";
	private List<ISpawnComponent> spawns = new ArrayList<>();
	private IPlacementComponent placement;
	private int duration;
	private int preCD;
	
	@Override
	public boolean readJson(JsonObject json) {
		
		/* duration */
		this.duration = JSONUtils.getAsInt(json, "duration", 0);
		if(this.duration == 0) {
			throw new JsonSyntaxException("Wave duration cannot be empty or zero");
		}
		
		/* pre tick */
		this.preCD = JSONUtils.getAsInt(json, "pre_tick", 100);
		
		/* spawn placement */
		this.placement = ChallengeManager.readPlacement(json, false);
		
		/* spawn list */
		JsonArray jsonSpawns = JSONUtils.getAsJsonArray(json, StringUtil.SPAWNS, new JsonArray());
		for(int i = 0; i < jsonSpawns.size(); ++ i) {
			JsonObject obj = jsonSpawns.get(i).getAsJsonObject();
		    if(obj != null) {
		    	String type = JSONUtils.getAsString(obj, "type", "");
	            ISpawnComponent spawn = ChallengeManager.getSpawnComponent(type);
	            if(! spawn.readJson(obj)) {
	            	return false;
	            }
			    this.spawns.add(spawn);
		    }
		}
		if(this.spawns.isEmpty()) {
			throw new JsonSyntaxException("Spawn list cannot be empty");
		}
		
		return true;
	}
	
	@Override
	public List<ISpawnComponent> getSpawns(){
		return this.spawns;
	}
	
	@Override
	public IPlacementComponent getPlacement() {
		return this.placement;
	}
	
	@Override
	public int getPrepareCD() {
		return this.preCD;
	}
	
	@Override
	public int getLastDuration() {
		return this.duration;
	}
	
}
