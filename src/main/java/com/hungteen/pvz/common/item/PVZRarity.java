package com.hungteen.pvz.common.item;

import com.hungteen.pvz.api.types.IRankType;
import com.hungteen.pvz.common.impl.RankTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Rarity;

/**
 * @program: pvzmod-1.16.5
 * @author: HungTeen
 * @create: 2022-01-22 22:20
 **/
public class PVZRarity{

    public static final Rarity GRAY = Rarity.create("gray", ChatFormatting.GRAY);
    public static final Rarity WHITE = Rarity.create("white", ChatFormatting.WHITE);
    public static final Rarity GREEN = Rarity.create("green", ChatFormatting.GREEN);
    public static final Rarity BLUE = Rarity.create("blue", ChatFormatting.BLUE);
    public static final Rarity PURPLE = Rarity.create("purple", ChatFormatting.LIGHT_PURPLE);
    public static final Rarity GOLD = Rarity.create("gold", ChatFormatting.GOLD);
    public static final Rarity RED = Rarity.create("red", ChatFormatting.RED);
    public static final Rarity BLACK = Rarity.create("black", ChatFormatting.BLACK);

    public static Rarity getRarityByRank(IRankType rankType){
        return (rankType == RankTypes.GRAY) ? GRAY : 
        	(rankType == RankTypes.WHITE) ? WHITE : 
        		(rankType == RankTypes.GREEN) ? GREEN : 
        			(rankType == RankTypes.BLUE) ? BLUE : 
        				(rankType == RankTypes.PURPLE) ? PURPLE : 
        					(rankType == RankTypes.GOLD) ? GOLD : 
        						(rankType == RankTypes.RED) ? RED : 
        							(rankType == RankTypes.BLACK) ? BLACK : 
        								Rarity.COMMON;
    }

}
