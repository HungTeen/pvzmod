package com.hungteen.pvz.common.impl.challenge;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.raid.IAmountComponent;
import com.hungteen.pvz.api.raid.IPlacementComponent;
import com.hungteen.pvz.api.raid.ISpawnComponent;
import com.hungteen.pvz.common.impl.challenge.amount.ConstantAmount;
import com.hungteen.pvz.common.world.challenge.ChallengeManager;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map.Entry;

public class SpawnComponent implements ISpawnComponent {
	
	public static final String NAME = "default";
	private EntityType<?> entityType;
	private IAmountComponent spawnAmount = new ConstantAmount();
	private CompoundNBT nbt = new CompoundNBT();
	private IPlacementComponent placement;
	private int spawnTick;
	
	@Override
	public boolean readJson(JsonObject json) {
		
		/* entity type */
		this.entityType = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(JSONUtils.getAsString(json, "entity_type", "")));
		if(this.entityType == null) {
			throw new JsonSyntaxException("entity type cannot be empty or wrong format");
		}
		
		/* spawn amount */
		{
			JsonObject obj = JSONUtils.getAsJsonObject(json, "spawn_amount");
	        if(obj != null && ! obj.entrySet().isEmpty()) {
	    	    for(Entry<String, JsonElement> entry : obj.entrySet()) {
	    		    final IAmountComponent tmp = ChallengeManager.getAmountComponent(entry.getKey());
	    		    if(tmp != null) {
	    			    tmp.readJson(entry.getValue());
	    			    this.spawnAmount = tmp;
	    		    } else {
	    			    PVZMod.LOGGER.warn("Amount Component : Read Spawn Amount Wrongly");
	    		    }
	    		    break;
	    	    }
	        }
		}
	    
	    /* spawn placement */
		this.placement = ChallengeManager.readPlacement(json, false);
		
		/* spawn tick */
		this.spawnTick = JSONUtils.getAsInt(json, "spawn_tick", 0);
		
		/* nbt */
		if(json.has("nbt")) {
			try {
			    nbt = JsonToNBT.parseTag(JSONUtils.convertToString(json.get("nbt"), "nbt"));
		    } catch (CommandSyntaxException e) {
			    throw new JsonSyntaxException("Invalid nbt tag: " + e.getMessage());
		    }
		}
		
		return true;
	}
	
	@Override
	public int getSpawnTick() {
		return this.spawnTick;
	}
	
	@Override
	public int getSpawnAmount() {
		return this.spawnAmount.getSpawnAmount();
	}
	
	@Override
	public IPlacementComponent getPlacement() {
		return this.placement;
	}
	
	@Override
	public CompoundNBT getNBT() {
		return this.nbt;
	}
	
	@Override
	public EntityType<?> getSpawnType() {
		return this.entityType;
	}
	
}
