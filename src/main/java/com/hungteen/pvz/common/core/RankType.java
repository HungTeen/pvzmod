package com.hungteen.pvz.common.core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import net.minecraft.item.Item;

public class RankType {

    private static final List<RankType> RANKS = new ArrayList<>();
	private final Supplier<Item> cardSuppiler;
	private final Supplier<Item> materialSuppiler;
	public final int enchantPoint;
	
	public RankType(int enchantPoint, Supplier<Item> sup1, Supplier<Item> sup2) {
		this.enchantPoint = enchantPoint;
		this.cardSuppiler = sup1;
		this.materialSuppiler = sup2;
		RANKS.add(this);
	}
	
	public static final List<RankType> getRanks() {
		return RANKS;
	}
	
	public Item getTemplateCard() {
		return this.cardSuppiler.get();
	}
	
	public Item getMaterialCard() {
		return this.materialSuppiler.get();
	}
	
}
