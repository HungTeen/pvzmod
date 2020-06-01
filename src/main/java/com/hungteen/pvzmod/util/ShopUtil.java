package com.hungteen.pvzmod.util;

import java.util.ArrayList;
import java.util.List;

import com.hungteen.pvzmod.registry.ItemRegister;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

public class ShopUtil {

	public static Goods[] getShopGoodsByPlayer(EntityPlayer player)
	{
		List<Goods> list=new ArrayList<>();
		for(Goods good:Goods.values()) {
			if(canGoodBeSold(player, good)) {
				list.add(good);
			}
		}
		return list.toArray(new Goods[0]);
	}
	
	public static boolean canGoodBeSold(EntityPlayer player,Goods good)
	{
		switch(good){
		default:return true;
		}
	}
	
	public static int getGoodPrice(Goods good)
	{
		switch(good) {
		case GATLING_PEA_CARD: return 5000;
		case TWIN_SUNFLOWER_CARD: return 5000;
		case CAT_TAIL_CARD: return 8000;
		case WINTER_MELON_CARD: return 20000;
		case SPIKE_ROCK_CARD: return 25000;
		default: return 1000000;
		}
	}
	
	public static Item getGoodByEnumName(Goods good)
	{
		switch(good) {
		case GATLING_PEA_CARD: return ItemRegister.GATLING_PEA_CARD;
		case TWIN_SUNFLOWER_CARD: return ItemRegister.TWIN_SUNFLOWER_CARD;
		case CAT_TAIL_CARD: return ItemRegister.CAT_TAIL_CARD;
		case WINTER_MELON_CARD: return ItemRegister.WINTER_MELON_CARD;
		case SPIKE_ROCK_CARD: return ItemRegister.SPIKE_ROCK_CARD;
		default: return null;
		}
	}
	
	public static String getGoodInfo(Goods good)
	{
		switch(good) {
		case GATLING_PEA_CARD: return "GatlingPea Card";
		case TWIN_SUNFLOWER_CARD: return "TwinSunFlower Card";
		case CAT_TAIL_CARD: return "CatTail Card";
		case WINTER_MELON_CARD: return "WinterMelon Card";
		case SPIKE_ROCK_CARD: return "SpikeRock Card";
		default: return "";
		}
	}
	
	public enum Goods
	{
		GATLING_PEA_CARD,
		TWIN_SUNFLOWER_CARD,
		CAT_TAIL_CARD,
		WINTER_MELON_CARD,
		SPIKE_ROCK_CARD
	}
}
