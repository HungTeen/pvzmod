package com.hungteen.pvz.common.datapack;

import com.google.gson.*;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.common.tileentity.SlotMachineTileEntity;
import com.hungteen.pvz.common.tileentity.SlotMachineTileEntity.LotteryTypes;
import com.hungteen.pvz.common.tileentity.SlotMachineTileEntity.SlotType;
import com.hungteen.pvz.common.tileentity.SlotMachineTileEntity.SlotTypes;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.client.resources.JsonReloadListener;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LotteryTypeLoader extends JsonReloadListener {

    public static final Map<ResourceLocation, SlotMachineTileEntity.LotteryType> LOTTERIES = new HashMap<>();
    public static final Map<ResourceLocation, JsonElement> JSONS = new HashMap<>();
    private static final Gson GSON = (new GsonBuilder()).create();
    public static final String NAME = "lottery";


    public LotteryTypeLoader() {
        super(GSON, NAME + "s");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> map, IResourceManager manager, IProfiler profiler) {
        LOTTERIES.clear();

        map.forEach((res, jsonElement) -> {
            updateResource(res, jsonElement);

            JSONS.put(res, jsonElement);
        });

        PVZMod.LOGGER.info("Loaded {} custom lottery type", LOTTERIES.size());
    }

    public static void updateResource(ResourceLocation res, JsonElement jsonElement) {
        try {
            JsonObject jsonObject = JSONUtils.convertToJsonObject(jsonElement, NAME);

            final SlotMachineTileEntity.LotteryType lotteryType = new SlotMachineTileEntity.LotteryType(res);
            final String str = JSONUtils.getAsString(jsonObject, "type", "normal");
            final SlotMachineTileEntity.LotteryTypes lotteryTypes = SlotMachineTileEntity.LotteryTypes.valueOf(str.toUpperCase());
            lotteryType.setLotteryTypes(lotteryTypes);

            final int sunCost = JSONUtils.getAsInt(jsonObject, "sun_cost", 25);
            lotteryType.setSunCost(sunCost);

            final int price = JSONUtils.getAsInt(jsonObject, "trade_price", 2000);
            lotteryType.setTradePrice(price);

            final int tradeWeight = JSONUtils.getAsInt(jsonObject, "trade_weight", 100);
            lotteryType.setTradeWeight(tradeWeight);

            if (lotteryTypes == LotteryTypes.ALL_PLANT_CARDS) {
                PVZAPI.get().getPlants().forEach(p -> {
                    p.getEnjoyCard().ifPresent(card -> {
                        final SlotType type = new SlotType(SlotTypes.ITEM);
                        type.setItemStack(new ItemStack(card));
                        lotteryType.addSlotType(type, 10);
                    });
                });
//				} else if(lotteryTypes == LotteryTypes.ALL_ZOMBIE_CARDS) {
//					PVZAPI.get().getZombies().forEach(p -> {
//						p.getEnjoyCard().ifPresent(card -> {
//							final SlotType type = new SlotType(SlotTypes.ITEM);
//							type.setItemStack(new ItemStack(card));
//							lotteryType.addSlotType(type, 10);
//						});
//					});
            } else if (lotteryTypes == LotteryTypes.ALL_SUMMON_CARDS) {
                PVZAPI.get().getPAZs().forEach(p -> {
                    p.getEnjoyCard().ifPresent(card -> {
                        final SlotType type = new SlotType(SlotTypes.ITEM);
                        type.setItemStack(new ItemStack(card));
                        lotteryType.addSlotType(type, 10);
                    });
                });
            }

            JsonArray array = JSONUtils.getAsJsonArray(jsonObject, "items", new JsonArray());
            array.forEach(e -> {
                if (e.isJsonObject()) {
                    final JsonObject obj = e.getAsJsonObject();

                    final String string = JSONUtils.getAsString(obj, "type", "item");
                    final SlotMachineTileEntity.SlotTypes type = SlotMachineTileEntity.SlotTypes.valueOf(string.toUpperCase());
                    final SlotMachineTileEntity.SlotType slotType = new SlotMachineTileEntity.SlotType(type);

                    if (type == SlotMachineTileEntity.SlotTypes.ITEM) {
                        Item item = JSONUtils.getAsItem(obj, "item");
                        if (obj.has("data")) {
                            throw new JsonParseException("Disallowed data tag found");
                        } else {
                            ItemStack stack = new ItemStack(item);
                            if (obj.has("nbt")) {
                                try {
                                    CompoundNBT compoundnbt = JsonToNBT.parseTag(JSONUtils.convertToString(obj.get("nbt"), "nbt"));
                                    stack.setTag(compoundnbt);
                                } catch (CommandSyntaxException commandsyntaxexception) {
                                    throw new JsonSyntaxException("Invalid nbt tag: " + commandsyntaxexception.getMessage());
                                }
                            }
                            slotType.setItemStack(stack);
                        }
                    }

                    int weight = 10;
                    if (obj.has("weight")) {
                        weight = JSONUtils.getAsInt(obj, "weight");
                    }

                    lotteryType.addSlotType(slotType, weight);
                }
            });

            final int count = JSONUtils.getAsInt(jsonObject, "slot_count", lotteryType.getSize() == 0 ? 10 : lotteryType.getSize());
            lotteryType.setSlotCount(count);

            LOTTERIES.put(res, lotteryType);

        } catch (IllegalArgumentException | JsonParseException e) {
            PVZMod.LOGGER.error("Parsing error loading lottery type {}: {}", res, e.getMessage());
        }
    }

    public static Optional<SlotMachineTileEntity.LotteryType> getLotteryType(ResourceLocation res) {
        return Optional.ofNullable(LOTTERIES.get(res));
    }

    public static Map<ResourceLocation, SlotMachineTileEntity.LotteryType> getLotteries() {
        return Collections.unmodifiableMap(LOTTERIES);
    }
}