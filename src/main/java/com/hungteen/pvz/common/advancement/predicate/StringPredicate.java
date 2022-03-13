package com.hungteen.pvz.common.advancement.predicate;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import net.minecraft.server.level.ServerPlayer;

import javax.annotation.Nullable;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-10 17:07
 **/
public class StringPredicate {

    public static final StringPredicate ANY = new StringPredicate();
    private final String text;

    public StringPredicate() {
        text = "";
    }

    public StringPredicate(String string) {
        this.text = string;
    }

    public boolean test(ServerPlayer player, String string) {
        if(this == ANY) return true;
        return this.text.equals(string);
    }

    public static StringPredicate deserialize(@Nullable JsonElement element) {
        if (element != null && element.isJsonPrimitive()) {
            return new StringPredicate(element.getAsString());
        } else {
            return ANY;
        }
    }

    public JsonElement serialize() {
        return new JsonPrimitive(this.text);
    }

}
