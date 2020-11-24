package com.hungteen.pvz.utils.enums;

import java.util.EnumMap;

import com.hungteen.pvz.register.ItemRegister;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public enum Ranks {

	GRAY,  
	WHITE, 
	GREEN, 
	BLUE,  
	PURPLE,
	GOLD,  
	MEGA;
	
    public static final EnumMap<Ranks, RegistryObject<? extends Item>> RANK_CARDS = new EnumMap<>(Ranks.class);
	
	static {
		RANK_CARDS.put(Ranks.GRAY, ItemRegister.GRAY_CARD);
		RANK_CARDS.put(Ranks.WHITE, ItemRegister.WHITE_CARD);
		RANK_CARDS.put(Ranks.GREEN, ItemRegister.GREEN_CARD);
		RANK_CARDS.put(Ranks.BLUE, ItemRegister.BLUE_CARD);
		RANK_CARDS.put(Ranks.PURPLE, ItemRegister.PURPLE_CARD);
		RANK_CARDS.put(Ranks.GOLD, ItemRegister.GOLD_CARD);
	}
	
	public static Item getRankCardItem(Ranks rank) {
		if(RANK_CARDS.containsKey(rank)) {
			return RANK_CARDS.get(rank).get();
		}
		System.out.println("Error: get Ranks card item !");
		return null;
	}
}
