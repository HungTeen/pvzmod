package com.hungteen.pvz.common.impl;

import java.util.List;

import com.hungteen.pvz.common.core.CardRank;
import com.hungteen.pvz.register.ItemRegister;

import net.minecraft.item.Items;

public class Ranks {

    public static final CardRank GRAY = new CardRank(15, () -> ItemRegister.GRAY_CARD.get(), () -> Items.STONE);
	
	public static final CardRank WHITE = new CardRank(14, () -> ItemRegister.WHITE_CARD.get(), () -> Items.IRON_INGOT);
	
	public static final CardRank GREEN = new CardRank(12, () -> ItemRegister.GREEN_CARD.get(), () -> Items.EMERALD);
	
	public static final CardRank BLUE = new CardRank(12, () -> ItemRegister.BLUE_CARD.get(), () -> Items.DIAMOND);
	
	public static final CardRank PURPLE = new CardRank(10, () -> ItemRegister.PURPLE_CARD.get(), () -> ItemRegister.AMETHYST_INGOT.get());
	
	public static final CardRank GOLD = new CardRank(8, () -> ItemRegister.GOLD_CARD.get(), () -> Items.GOLD_BLOCK);
	
	public static final CardRank BLACK = new CardRank(5, () -> ItemRegister.BLACK_CARD.get(), () -> Items.NETHERITE_INGOT);
	
	public static List<CardRank> getRanks(){
		return CardRank.RANKS;
	}
	
}
