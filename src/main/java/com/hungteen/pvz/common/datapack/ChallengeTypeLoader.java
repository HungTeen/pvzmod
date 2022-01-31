package com.hungteen.pvz.common.datapack;

import com.google.common.collect.Maps;
import com.google.gson.*;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.raid.IChallengeComponent;
import com.hungteen.pvz.common.world.challenge.ChallengeManager;
import net.minecraft.client.resources.JsonReloadListener;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;

import java.util.Map;

public class ChallengeTypeLoader extends JsonReloadListener{

	public static final String NAME = "challenge";
	public static final Map<ResourceLocation, IChallengeComponent> CHALLENGE_MAP = Maps.newHashMap();
	private static final Gson GSON = (new GsonBuilder()).create();
	
	public ChallengeTypeLoader() {
		super(GSON, NAME + "s");
	}

	@Override
	public void apply(Map<ResourceLocation, JsonElement> map, IResourceManager manager, IProfiler profiler) {
		/* refresh */
		CHALLENGE_MAP.clear();
		
		/* load */
		map.forEach((res, jsonElement) -> {
			try {
	            JsonObject jsonObject = JSONUtils.convertToJsonObject(jsonElement, NAME);
	            String type = JSONUtils.getAsString(jsonObject, "type", "");
	            IChallengeComponent challengeType = ChallengeManager.getChallengeComponent(type);
	            if(! challengeType.readJson(jsonObject)) {
	            	PVZMod.LOGGER.debug("Skipping loading challenge {} as it's conditions were not met", res);
	            	return;
	            }
	            CHALLENGE_MAP.put(res, challengeType);
	         } catch (IllegalArgumentException | JsonParseException e) {
				PVZMod.LOGGER.error("Parsing error loading challenge {}: {}", res, e.getMessage());
	         }
		});

		PVZMod.LOGGER.info("Loaded {} challenges", CHALLENGE_MAP.size());
	}

}
