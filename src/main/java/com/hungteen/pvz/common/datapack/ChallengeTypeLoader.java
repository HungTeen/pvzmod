package com.hungteen.pvz.common.datapack;

import com.google.common.collect.Maps;
import com.google.gson.*;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.raid.IChallengeComponent;
import com.hungteen.pvz.common.world.challenge.ChallengeManager;
import com.hungteen.pvz.utils.enums.Colors;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.resources.JsonReloadListener;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChallengeTypeLoader extends JsonReloadListener{

	public static final String NAME = "challenge";
	public static final Map<ResourceLocation, IChallengeComponent> CHALLENGE_MAP = Maps.newHashMap();
	public static final Map<IChallengeComponent, ResourceLocation> RES_MAP = Maps.newHashMap();
	public static final Map<ResourceLocation, JsonElement> JSONS = new HashMap<>();
	private static final Gson GSON = (new GsonBuilder()).create();
	
	public ChallengeTypeLoader() {
		super(GSON, NAME + "s");
	}

	@Override
	public void apply(Map<ResourceLocation, JsonElement> map, IResourceManager manager, IProfiler profiler) {
		/* refresh */
		CHALLENGE_MAP.clear();
		RES_MAP.clear();
		
		/* load */
		map.forEach((res, jsonElement) -> {
			updateResource(res, jsonElement);

			JSONS.put(res, jsonElement);
		});

		PVZMod.LOGGER.info("Loaded {} challenges", CHALLENGE_MAP.size());
	}

	public static void updateResource(ResourceLocation res, JsonElement jsonElement) {
		try {
			JsonObject jsonObject = JSONUtils.convertToJsonObject(jsonElement, NAME);
			String type = JSONUtils.getAsString(jsonObject, "type", "");
			IChallengeComponent challengeType = ChallengeManager.getChallengeComponent(type);
			if(! challengeType.readJson(jsonObject)) {
				PVZMod.LOGGER.debug("Skipping loading challenge {} as it's conditions were not met", res);
				return;
			}
			/* messages */
			{
				final List<Pair<IFormattableTextComponent, Integer>> messages = new ArrayList<>();
				final JsonArray jsonMsgs = JSONUtils.getAsJsonArray(jsonObject, "messages", null);
				if(jsonMsgs == null){//no msg, use default.
					for(int i = 0; i < 6; ++ i){
						final IFormattableTextComponent component = new TranslationTextComponent("challenge." + res.getNamespace() + "." + res.getPath() + ".msg" + (i + 1));
						messages.add(Pair.of(component, Colors.BLACK));
					}
				} else{
					for(int i = 0; i < jsonMsgs.size(); ++i) {
						final JsonElement e = jsonMsgs.get(i);
						if (e.isJsonObject()) {
							final JsonObject obj = e.getAsJsonObject();
							final String name = JSONUtils.getAsString(obj, "title", null);
							final int color = JSONUtils.getAsInt(obj, "color", Colors.BAT_BLACK);
							if (name != null) {
								messages.add(Pair.of(new TranslationTextComponent(name), color));
							}
						}
					}
				}
				challengeType.setMessages(messages);
			}
			CHALLENGE_MAP.put(res, challengeType);
			RES_MAP.put(challengeType, res);
		} catch (IllegalArgumentException | JsonParseException e) {
			PVZMod.LOGGER.error("Parsing error loading challenge {}: {}", res, e.getMessage());
		}
	}

}
