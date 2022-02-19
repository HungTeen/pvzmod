package com.hungteen.pvz.common.datapack;

import com.google.gson.*;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.raid.IAmountComponent;
import com.hungteen.pvz.common.entity.npc.AbstractDaveEntity;
import com.hungteen.pvz.common.world.challenge.ChallengeManager;
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

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: pvzmod-1.16.5
 * @author: HungTeen
 * @create: 2022-02-07 11:19
 **/
public class TransactionTypeLoader extends JsonReloadListener {

    public static final Map<ResourceLocation, AbstractDaveEntity.TransactionType> TRANSACTIONS = new HashMap<>();
    public static final Map<ResourceLocation, JsonElement> JSONS = new HashMap<>();
    private static final Gson GSON = (new GsonBuilder()).create();
    public static final String NAME = "transaction";


    public TransactionTypeLoader() {
        super(GSON, NAME + "s");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> map, IResourceManager manager, IProfiler profiler) {
        TRANSACTIONS.clear();

        map.forEach((res, jsonElement) -> {
            updateResource(res, jsonElement);

            JSONS.put(res, jsonElement);
        });

        PVZMod.LOGGER.info("Loaded {} custom transaction type", TRANSACTIONS.size());

    }

    public static void updateResource(ResourceLocation res, JsonElement jsonElement) {
        try {
            JsonObject jsonObject = JSONUtils.convertToJsonObject(jsonElement, NAME);

            final AbstractDaveEntity.TransactionType transactionType = new AbstractDaveEntity.TransactionType(res);

            /* amount */
            {
                JsonObject obj = JSONUtils.getAsJsonObject(jsonObject, "good_count");
                if (obj != null && !obj.entrySet().isEmpty()) {
                    for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
                        final IAmountComponent tmp = ChallengeManager.getAmountComponent(entry.getKey());
                        if (tmp != null) {
                            tmp.readJson(entry.getValue());
                            transactionType.setGoodCount(tmp);
                        } else {
                            PVZMod.LOGGER.warn("Amount Component : Read Spawn Amount Wrongly");
                        }
                        break;
                    }
                }
            }

            transactionType.setEnvelope(JSONUtils.getAsBoolean(jsonObject, "has_envelope", false));
            transactionType.setSlotMachine(JSONUtils.getAsBoolean(jsonObject, "has_slot_machine", false));
            transactionType.setEnjoyCard(JSONUtils.getAsBoolean(jsonObject, "has_enjoy_card", false));


            JsonArray array = JSONUtils.getAsJsonArray(jsonObject, "goods", new JsonArray());
            array.forEach(e -> {
                if(e.isJsonObject()) {
                    final JsonObject obj  = e.getAsJsonObject();

                    final String string = JSONUtils.getAsString(obj, "type", "item");
                    final AbstractDaveEntity.GoodTypes type = AbstractDaveEntity.GoodTypes.valueOf(string.toUpperCase());

                    ItemStack stack = ItemStack.EMPTY;
                    if(type == AbstractDaveEntity.GoodTypes.ITEM){
                        Item item = JSONUtils.getAsItem(obj, "item");
                        if(obj.has("data")) {
                            throw new JsonParseException("Disallowed data tag found");
                        } else {
                            stack = new ItemStack(item);
                            if(obj.has("nbt")) {
                                try {
                                    CompoundNBT compoundnbt = JsonToNBT.parseTag(JSONUtils.convertToString(obj.get("nbt"), "nbt"));
                                    stack.setTag(compoundnbt);
                                } catch (CommandSyntaxException commandsyntaxexception) {
                                    throw new JsonSyntaxException("Invalid nbt tag: " + commandsyntaxexception.getMessage());
                                }
                            }
                        }
                    }

                    final int price = JSONUtils.getAsInt(obj, "price", 1000);

                    final int weight = JSONUtils.getAsInt(obj, "weight", 100);

                    final int limit = JSONUtils.getAsInt(obj, "limit", 10);

                    final boolean must = JSONUtils.getAsBoolean(obj, "must", false);

                    AbstractDaveEntity.GoodType goodType = new AbstractDaveEntity.GoodType(type, stack, price, weight, limit, must);

                    transactionType.addGood(goodType);
                }
            });

            TRANSACTIONS.put(res, transactionType);

        } catch (IllegalArgumentException | JsonParseException e) {
            PVZMod.LOGGER.error("Parsing error loading transaction type {}: {}", res, e.getMessage());
        }
    }

    @Nullable
    public static AbstractDaveEntity.TransactionType getTransactionByRes(ResourceLocation res){
        return TRANSACTIONS.get(res);
    }

}
