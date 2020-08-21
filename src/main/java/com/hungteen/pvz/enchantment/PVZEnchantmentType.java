package com.hungteen.pvz.enchantment;

import java.util.function.Predicate;

import com.hungteen.pvz.item.tool.card.PlantCardItem;
import com.hungteen.pvz.item.tool.card.SummonCardItem;

import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.item.Item;
import net.minecraftforge.common.IExtensibleEnum;

public enum PVZEnchantmentType implements IExtensibleEnum {

	SUMMON_CARD{
		@Override
		public boolean canEnchantItem(Item itemIn) {
			return itemIn instanceof SummonCardItem;
		}
	},
	PLANT_CARD{
		@Override
		public boolean canEnchantItem(Item itemIn) {
			return itemIn instanceof PlantCardItem;
		}
	};
	private Predicate<Item> delegate;

	private PVZEnchantmentType(java.util.function.Predicate<Item> delegate) {
		this.delegate = delegate;
	}

	PVZEnchantmentType() {
	}

	public static EnchantmentType create(String name, java.util.function.Predicate<Item> delegate) {
		throw new IllegalStateException("Enum not extended");
	}

	/**
	 * Return true if the item passed can be enchanted by a enchantment of this
	 * type.
	 */
	public boolean canEnchantItem(Item itemIn) {
		return this.delegate == null ? false : this.delegate.test(itemIn);
	}
}
