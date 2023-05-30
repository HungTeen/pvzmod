package com.hungteen.pvz.common.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

import java.util.function.Supplier;

public enum PVZItemTier implements IItemTier {
    
	ORIGIN(3, 666, 7.0F, 3.0F, 18, () -> {
		return Ingredient.of(ItemRegister.ORIGIN_INGOT.get());
	});

	private final int harvestLevel;
	private final int maxUses;
	private final float efficiency;
	private final float attackDamage;
	private final int enchantability;
	private final LazyValue<Ingredient> repairMaterial;

	private PVZItemTier(int harvestLevelIn, int maxUsesIn, float efficiencyIn, float attackDamageIn,
			int enchantabilityIn, Supplier<Ingredient> repairMaterialIn) {
		this.harvestLevel = harvestLevelIn;
		this.maxUses = maxUsesIn;
		this.efficiency = efficiencyIn;
		this.attackDamage = attackDamageIn;
		this.enchantability = enchantabilityIn;
		this.repairMaterial = new LazyValue<>(repairMaterialIn);
	}

	public int getUses() {
		return this.maxUses;
	}

	public float getSpeed() {
		return this.efficiency;
	}

	public float getAttackDamageBonus() {
		return this.attackDamage;
	}

	public int getLevel() {
		return this.harvestLevel;
	}

	public int getEnchantmentValue() {
		return this.enchantability;
	}

	public Ingredient getRepairIngredient() {
		return this.repairMaterial.get();
	}
}
