package com.hungteen.pvz.common.world.raid;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hungteen.pvz.common.impl.raid.RaidComponent;
import com.hungteen.pvz.utils.enums.Colors;
import com.mojang.datafixers.util.Pair;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PVZRaidComponent extends RaidComponent {

    public static final String NAME = "pvz";
    private static final String JSON_TITLE = "title";
    private static final String JSON_COLOR = "color";
    private final List<Pair<TranslationTextComponent, Integer>> messages = new ArrayList<>();
    private final List<IFormattableTextComponent> toolTips = new ArrayList<>();

    @Override
    public boolean readJson(JsonObject json) {
        {
            final JsonArray jsonMsgs = JSONUtils.getAsJsonArray(json, "messages", new JsonArray());
            for(int i = 0; i < Objects.requireNonNull(jsonMsgs).size(); ++i) {
                final JsonElement e = jsonMsgs.get(i);
                if (e.isJsonObject()) {
                    final JsonObject obj = e.getAsJsonObject();
                    final String name = JSONUtils.getAsString(obj, JSON_TITLE, null);
                    final int color = JSONUtils.getAsInt(obj, JSON_COLOR, Colors.BAT_BLACK);
                    if (name != null) {
                        this.messages.add(Pair.of(new TranslationTextComponent(name), color));
                    }
                }
            }
        }
        {
            final JsonArray jsonTips = JSONUtils.getAsJsonArray(json, "tips", new JsonArray());
            if (jsonTips != null) {
                for(int i = 0; i < jsonTips.size(); ++i) {
                    final JsonElement e = jsonTips.get(i);
                    if (e.isJsonObject()) {
                        final JsonObject obj = e.getAsJsonObject();
                        final String name = JSONUtils.getAsString(obj, JSON_TITLE, null);
                        final TextFormatting color = TextFormatting.valueOf(JSONUtils.getAsString(obj, JSON_COLOR, "red"));
                        if(name != null){
                            this.toolTips.add(new TranslationTextComponent(name).withStyle(color));
                        }
                    }
                }
            }
        }

        return super.readJson(json);
    }

    public List<IFormattableTextComponent> getToolTips(){
        return Collections.unmodifiableList(this.toolTips);
    }

    public List<Pair<TranslationTextComponent, Integer>> getMessages(){
        return Collections.unmodifiableList(this.messages);
    }


}
