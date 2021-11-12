package com.hungteen.pvz.common.item.armor;

import java.util.function.Supplier;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public enum PVZArmorMaterial implements IArmorMaterial {
	
	FOOTBALL("football", 50, new int[] {3, 6, 8 ,3}, 15, SoundEvents.ARMOR_EQUIP_IRON, 0.5F, 0.1F, () -> {
	      return Ingredient.of(Items.IRON_INGOT);
	}),
	GIGA("giga", 80, new int[] {3, 6, 8 ,3}, 20, SoundEvents.ARMOR_EQUIP_IRON, 2F, 0.2F, () -> {
	      return Ingredient.of(Items.NETHERITE_INGOT);
	});

	private static final int[] MAX_DAMAGE_ARRAY = new int[] { 13, 15, 16, 11 };
	private final String name;
	private final int maxDamageFactor;
	private final int[] damageReductionAmountArray;
	private final int enchantability;
	private final SoundEvent soundEvent;
	private final float toughness;
	private final float knockbackResistance;
	private final LazyValue<Ingredient> repairMaterial;

	private PVZArmorMaterial(String nameIn, int maxDamageFactorIn, int[] damageReductionAmountsIn, int enchantabilityIn,
			SoundEvent equipSoundIn, float toughnessIn, float kb, Supplier<Ingredient> repairMaterialSupplier) {
		this.name = nameIn;
		this.maxDamageFactor = maxDamageFactorIn;
		this.damageReductionAmountArray = damageReductionAmountsIn;
		this.enchantability = enchantabilityIn;
		this.soundEvent = equipSoundIn;
		this.toughness = toughnessIn;
		this.knockbackResistance = kb;
		this.repairMaterial = new LazyValue<>(repairMaterialSupplier);
	}

	@Override
	public int getDurabilityForSlot(EquipmentSlotType slotIn) {
		return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
	}

	@Override
	public int getDefenseForSlot(EquipmentSlotType slotIn) {
		return this.damageReductionAmountArray[slotIn.getIndex()];
	}

	@Override
	public int getEnchantmentValue() {
		return this.enchantability;
	}

	@Override
	public SoundEvent getEquipSound() {
		return this.soundEvent;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return this.repairMaterial.get();
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public String getName() {
		return this.name;
	}

	@Override
	public float getToughness() {
		return this.toughness;
	}

	@Override
	public float getKnockbackResistance() {
		return this.knockbackResistance;
	}

}
