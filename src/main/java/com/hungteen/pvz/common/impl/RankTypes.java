package com.hungteen.pvz.common.impl;

import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.types.IRankType;
import com.hungteen.pvz.common.item.ItemRegister;
import com.hungteen.pvz.common.misc.tag.PVZItemTags;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class RankTypes {

	private static final List<IRankType> RANK_TYPES = new ArrayList<>();

    public static final IRankType GRAY = new RankType("gray", 15, 10, 1, () -> ItemRegister.GRAY_CARD.get(), () -> PVZItemTags.GRAY_CARDS, () -> PVZItemTags.GRAY_MATERIALS);
	
	public static final IRankType WHITE = new RankType("white", 14, 10, 2, () -> ItemRegister.WHITE_CARD.get(), () -> PVZItemTags.WHITE_CARDS, () -> PVZItemTags.WHITE_MATERIALS);
	
	public static final IRankType GREEN = new RankType("green", 12, 9, 2, () -> ItemRegister.GREEN_CARD.get(), () -> PVZItemTags.GREEN_CARDS, () -> PVZItemTags.GREEN_MATERIALS);
	
	public static final IRankType BLUE = new RankType("blue", 12, 9, 3, () -> ItemRegister.BLUE_CARD.get(), () -> PVZItemTags.BLUE_CARDS, () -> PVZItemTags.BLUE_MATERIALS);
	
	public static final IRankType PURPLE = new RankType("purple", 10, 8, 3, () -> ItemRegister.PURPLE_CARD.get(), () -> PVZItemTags.PURPLE_CARDS, () -> PVZItemTags.PURPLE_MATERIALS);
	
	public static final IRankType GOLD = new RankType("gold", 10, 7, 3, () -> ItemRegister.GOLD_CARD.get(), () -> PVZItemTags.GOLD_CARDS, () -> PVZItemTags.GOLD_MATERIALS);
	
	public static final IRankType RED = new RankType("red", 8, 6, 4, () -> ItemRegister.RED_CARD.get(), () -> PVZItemTags.RED_CARDS, () -> PVZItemTags.RED_MATERIALS);
	
	public static final IRankType BLACK = new RankType("black", 6, 5, 5, () -> ItemRegister.BLACK_CARD.get(), () -> PVZItemTags.BLACK_CARDS, () -> PVZItemTags.BLACK_MATERIALS);

	public static final IRankType MEGA = new RankType("mega", 0, 1, 10, () -> ItemRegister.MEGA_CARD.get(), () -> null, () -> null);

	public static void registerRankType(IRankType type){
		if(! RANK_TYPES.contains(type)){
			RANK_TYPES.add(type);
		}
	}

	public static final List<IRankType> getRanks() {
		return RANK_TYPES;
	}

	public static class RankType implements IRankType {

		private static final List<IRankType> RANKS = new ArrayList<>();
		private final String name;
		private final Supplier<Item> cardSuppiler;
		private final Supplier<ITag.INamedTag<Item>> cardTagSuppiler;
		private final Supplier<ITag.INamedTag<Item>> materialSuppiler;
		private final int enchantPoint;
		private final int weight;
		private final int value;

		public RankType(String name, int enchantPoint, int weight, int value, Supplier<Item> sup, Supplier<ITag.INamedTag<Item>> sup1, Supplier<ITag.INamedTag<Item>> sup2) {
			this.name = name;
			this.enchantPoint = enchantPoint;
			this.weight = weight;
			this.value = value;
			this.cardSuppiler = sup;
			this.cardTagSuppiler = sup1;
			this.materialSuppiler = sup2;
			RANKS.add(this);
		}

		public static void register(){
			RANKS.forEach(rank -> PVZAPI.get().registerRankType(rank));
		}

		@Override
		public Item getTemplateCard() {
			return this.cardSuppiler.get();
		}

		@Override
		public ITag.INamedTag<Item> getCardTag() {
			return this.cardTagSuppiler.get();
		}

		@Override
		public ITag.INamedTag<Item> getMaterial() {
			return this.materialSuppiler.get();
		}

		@Override
		public int getEnchantPoint() {
			return this.enchantPoint;
		}

		@Override
		public int getWeight() {
			return weight;
		}

		@Override
		public int getValue() {
			return value;
		}

		@Override
		public String getName() {
			return name;
		}
	}
}
