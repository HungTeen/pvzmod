package com.hungteen.pvz.common.core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import net.minecraft.item.Item;

public class CardRank {

    public static final List<CardRank> RANKS = new ArrayList<>();
	private final Supplier<Item> cardSuppiler;
	private final Supplier<Item> materialSuppiler;
	public final int enchantPoint;
	
	public CardRank(int enchantPoint, Supplier<Item> sup1, Supplier<Item> sup2) {
		this.enchantPoint = enchantPoint;
		this.cardSuppiler = sup1;
		this.materialSuppiler = sup2;
		RANKS.add(this);
	}
	
	public Item getTemplateCard() {
		return this.cardSuppiler.get();
	}
	
	public Item getMaterialCard() {
		return this.materialSuppiler.get();
	}
	
}
