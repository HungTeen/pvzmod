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

    public static final IRankType GRAY = new RankType("gray", 15, () -> ItemRegister.GRAY_CARD.get(), () -> PVZItemTags.GRAY_CARDS, () -> PVZItemTags.GRAY_MATERIALS);
	
	public static final IRankType WHITE = new RankType("white", 14, () -> ItemRegister.WHITE_CARD.get(), () -> PVZItemTags.WHITE_CARDS, () -> PVZItemTags.WHITE_MATERIALS);
	
	public static final IRankType GREEN = new RankType("green", 12, () -> ItemRegister.GREEN_CARD.get(), () -> PVZItemTags.GREEN_CARDS, () -> PVZItemTags.GREEN_MATERIALS);
	
	public static final IRankType BLUE = new RankType("blue", 12, () -> ItemRegister.BLUE_CARD.get(), () -> PVZItemTags.BLUE_CARDS, () -> PVZItemTags.BLUE_MATERIALS);
	
	public static final IRankType PURPLE = new RankType("purple", 10, () -> ItemRegister.PURPLE_CARD.get(), () -> PVZItemTags.PURPLE_CARDS, () -> PVZItemTags.PURPLE_MATERIALS);
	
	public static final IRankType GOLD = new RankType("gold", 10, () -> ItemRegister.GOLD_CARD.get(), () -> PVZItemTags.GOLD_CARDS, () -> PVZItemTags.GOLD_MATERIALS);
	
	public static final IRankType RED = new RankType("red", 8, () -> ItemRegister.RED_CARD.get(), () -> PVZItemTags.RED_CARDS, () -> PVZItemTags.RED_MATERIALS);
	
	public static final IRankType BLACK = new RankType("black", 6, () -> ItemRegister.BLACK_CARD.get(), () -> PVZItemTags.BLACK_CARDS, () -> PVZItemTags.BLACK_MATERIALS);

	public static final IRankType MEGA = new RankType("mega", 0, () -> ItemRegister.MEGA_CARD.get(), () -> null, () -> null);

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

		public RankType(String name, int enchantPoint, Supplier<Item> sup, Supplier<ITag.INamedTag<Item>> sup1, Supplier<ITag.INamedTag<Item>> sup2) {
			this.name = name;
			this.enchantPoint = enchantPoint;
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
		public String getName() {
			return name;
		}
	}
}
