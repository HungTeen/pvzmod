package com.hungteen.pvz.common.advancement.predicate;

import com.google.gson.JsonElement;
import net.minecraft.entity.player.ServerPlayerEntity;

import javax.annotation.Nullable;

public class StringPredicate {

	public static final StringPredicate ANY = new StringPredicate();
	private final String s;

	public StringPredicate() {
		s = "";
	}

	public StringPredicate(String s) {
		this.s = s;
	}

	public boolean test(ServerPlayerEntity player, String ss) {
		if(this == ANY) return true;
		return this.s.equals(ss);
	}

	public static StringPredicate deserialize(@Nullable JsonElement element) {
		if (element != null && element.isJsonPrimitive()) {
			return new StringPredicate(element.getAsString());
		} else {
			return ANY;
		}
	}

	public String serialize() {
		return this.s;
	}

}
