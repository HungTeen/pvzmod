package com.hungteen.pvz.common.datapack;

import com.google.gson.*;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.world.invasion.InvasionType;
import com.hungteen.pvz.common.world.invasion.SpawnType;
import com.hungteen.pvz.utils.StringUtil;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.client.resources.JsonReloadListener;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;

public class InvasionTypeLoader extends JsonReloadListener {

    public static final Map<ResourceLocation, InvasionType> INVASIONS = new HashMap<>();
    private static final Gson GSON = (new GsonBuilder()).create();
    public static final String NAME = "invasion";

    public InvasionTypeLoader() {
        super(GSON, NAME + "s");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> map, IResourceManager manager, IProfiler profiler) {
        INVASIONS.clear();

        map.forEach((res, jsonElement) -> {
            try {
                JsonObject jsonObject = JSONUtils.convertToJsonObject(jsonElement, NAME);

                final boolean isAssist = JSONUtils.getAsBoolean(jsonObject, "isAssistInvasion");
                final InvasionType invasionType = new InvasionType(res, isAssist);

                final int chance = JSONUtils.getAsInt(jsonObject, "triggerChance", 10);
                invasionType.setTriggerChance(chance);

                if(jsonObject.has("bonus")){
                    final ResourceLocation bonus = new ResourceLocation(JSONUtils.getAsString(jsonObject, "bonus"));
                    invasionType.setBonusResource(bonus);
                }

                final String color = JSONUtils.getAsString(jsonObject, "display_color", "white");
                invasionType.setDisplayColor(TextFormatting.valueOf(color.toUpperCase()));

                JsonArray array = JSONUtils.getAsJsonArray(jsonObject, "spawns", new JsonArray());
                array.forEach(e -> {
                    if(e.isJsonObject()) {
                        final JsonObject obj  = e.getAsJsonObject();

                        /* entity type */
                        final EntityType<? extends MobEntity> entityType = (EntityType<? extends MobEntity>) ForgeRegistries.ENTITIES.getValue(new ResourceLocation(JSONUtils.getAsString(obj, StringUtil.ENTITY_TYPE, "")));
                        if(entityType == null) {
                            throw new JsonSyntaxException("entity type cannot be empty or wrong format");
                        }
                        final SpawnType spawnType = new SpawnType(entityType);

                        /* nbt */
                        if(obj.has(StringUtil.ENTITY_NBT)) {
                            try {
                                final CompoundNBT nbt = JsonToNBT.parseTag(JSONUtils.convertToString(obj.get(StringUtil.ENTITY_NBT), StringUtil.ENTITY_NBT));
                                spawnType.setNbt(nbt);
                            } catch (CommandSyntaxException ee) {
                                throw new JsonSyntaxException("Invalid nbt tag: " + ee.getMessage());
                            }
                        }

                        /* invasion level */
                        final int dif = JSONUtils.getAsInt(obj, "invasion_level", 1);
                        spawnType.setInvasionLevel(dif);

                        /* spawn weight */
                        final int weight = JSONUtils.getAsInt(obj, "spawn_weight", 100);
                        spawnType.setSpawnWeight(weight);

                        final String placeType = JSONUtils.getAsString(obj, "placement", SpawnType.PlaceType.LAND.toString().toLowerCase());
                        spawnType.setPlaceType(SpawnType.PlaceType.valueOf(placeType.toUpperCase()));

                        invasionType.addSpawn(spawnType);
                    }
                });

                if(jsonObject.has("invasion_level")){
                    invasionType.setRequireDifficulty(JSONUtils.getAsInt(jsonObject, "invasion_level"));
                }

                INVASIONS.put(res, invasionType);

            } catch (IllegalArgumentException | JsonParseException e) {
                PVZMod.LOGGER.error("Parsing error loading invasion type {}: {}", res, e.getMessage());
            }
        });

        PVZMod.LOGGER.info("Loaded {} custom invasion type", INVASIONS.size());

    }
}