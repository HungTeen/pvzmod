package com.hungteen.pvz.common.impl;

import com.hungteen.pvz.api.types.IRankType;
import com.hungteen.pvz.common.impl.misc.RankType;
import com.hungteen.pvz.common.item.ItemRegister;

import net.minecraft.item.Items;

public class Ranks {

    public static final IRankType GRAY = new RankType(15, () -> ItemRegister.GRAY_CARD.get(), () -> Items.STONE);
	
	public static final IRankType WHITE = new RankType(14, () -> ItemRegister.WHITE_CARD.get(), () -> Items.IRON_INGOT);
	
	public static final IRankType GREEN = new RankType(12, () -> ItemRegister.GREEN_CARD.get(), () -> Items.EMERALD);
	
	public static final IRankType BLUE = new RankType(12, () -> ItemRegister.BLUE_CARD.get(), () -> Items.DIAMOND);
	
	public static final IRankType PURPLE = new RankType(10, () -> ItemRegister.PURPLE_CARD.get(), () -> ItemRegister.AMETHYST_INGOT.get());
	
	public static final IRankType GOLD = new RankType(8, () -> ItemRegister.GOLD_CARD.get(), () -> Items.GOLD_BLOCK);
	
	public static final IRankType BLACK = new RankType(5, () -> ItemRegister.BLACK_CARD.get(), () -> Items.NETHERITE_INGOT);
	
	public static final IRankType BOSS = new RankType(1, () -> ItemRegister.RED_CARD.get(), () -> Items.NETHER_STAR);
	
}
