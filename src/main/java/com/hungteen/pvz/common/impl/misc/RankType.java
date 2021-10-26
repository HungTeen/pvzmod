package com.hungteen.pvz.common.impl.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import com.hungteen.pvz.api.types.IRankType;

import net.minecraft.item.Item;

public class RankType implements IRankType {

    private static final List<RankType> RANKS = new ArrayList<>();
	private final Supplier<Item> cardSuppiler;
	private final Supplier<Item> materialSuppiler;
	private final int enchantPoint;
	
	public RankType(int enchantPoint, Supplier<Item> sup1, Supplier<Item> sup2) {
		this.enchantPoint = enchantPoint;
		this.cardSuppiler = sup1;
		this.materialSuppiler = sup2;
		RANKS.add(this);
	}
	
	public static final List<RankType> getRanks() {
		return RANKS;
	}
	
	@Override
	public Item getTemplateCard() {
		return this.cardSuppiler.get();
	}
	
	@Override
	public Item getMaterial() {
		return this.materialSuppiler.get();
	}
	
	@Override
	public int getEnchantPoint() {
		return this.enchantPoint;
	}
	
}
