package com.hungteen.pvz.common.impl;

import com.hungteen.pvz.common.core.RankType;
import com.hungteen.pvz.register.ItemRegister;

import net.minecraft.item.Items;

public class Ranks {

    public static final RankType GRAY = new RankType(15, () -> ItemRegister.GRAY_CARD.get(), () -> Items.STONE);
	
	public static final RankType WHITE = new RankType(14, () -> ItemRegister.WHITE_CARD.get(), () -> Items.IRON_INGOT);
	
	public static final RankType GREEN = new RankType(12, () -> ItemRegister.GREEN_CARD.get(), () -> Items.EMERALD);
	
	public static final RankType BLUE = new RankType(12, () -> ItemRegister.BLUE_CARD.get(), () -> Items.DIAMOND);
	
	public static final RankType PURPLE = new RankType(10, () -> ItemRegister.PURPLE_CARD.get(), () -> ItemRegister.AMETHYST_INGOT.get());
	
	public static final RankType GOLD = new RankType(8, () -> ItemRegister.GOLD_CARD.get(), () -> Items.GOLD_BLOCK);
	
	public static final RankType BLACK = new RankType(5, () -> ItemRegister.BLACK_CARD.get(), () -> Items.NETHERITE_INGOT);
	
	public static final RankType BOSS = new RankType(1, () -> ItemRegister.RED_CARD.get(), () -> Items.NETHER_STAR);
	
}
