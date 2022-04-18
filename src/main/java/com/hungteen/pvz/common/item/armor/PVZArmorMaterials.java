package com.hungteen.pvz.common.item.armor;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-18 13:22
 **/
public enum PVZArmorMaterials implements ArmorMaterial {

    BUCKET("bucket", 100, new int[]{3, 6, 8, 3}, 15, SoundEvents.ARMOR_EQUIP_IRON, 0F, 0.1F, () -> Ingredient.of(Items.IRON_INGOT), true),

    FOOTBALL("football", 50, new int[] {3, 6, 8 ,3}, 15, SoundEvents.ARMOR_EQUIP_IRON, 0.5F, 0.1F, () -> {
        return Ingredient.of(Items.IRON_INGOT);
    }),

    GIGA("giga", 80, new int[] {3, 6, 8 ,3}, 20, SoundEvents.ARMOR_EQUIP_IRON, 2F, 0.2F, () -> {
        return Ingredient.of(Items.NETHERITE_INGOT);
    });

    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;
    private final boolean sameDurability;

    PVZArmorMaterials(String name, int durability, int[] protections, int enchantPoint, SoundEvent soundEvent, float toughness, float kbValue, Supplier<Ingredient> ingredientSupplier) {
        this(name, durability, protections, enchantPoint, soundEvent, toughness, kbValue, ingredientSupplier, false);
    }

    PVZArmorMaterials(String name, int durability, int[] protections, int enchantPoint, SoundEvent soundEvent, float toughness, float kbValue, Supplier<Ingredient> ingredientSupplier, boolean sameDurability) {
        this.name = name;
        this.durabilityMultiplier = durability;
        this.slotProtections = protections;
        this.enchantmentValue = enchantPoint;
        this.sound = soundEvent;
        this.toughness = toughness;
        this.knockbackResistance = kbValue;
        this.repairIngredient = ingredientSupplier;
        this.sameDurability = sameDurability;
    }

    public int getDurabilityForSlot(EquipmentSlot slot) {
        return this.sameDurability ? durabilityMultiplier : HEALTH_PER_SLOT[slot.getIndex()] * this.durabilityMultiplier;
    }

    public int getDefenseForSlot(EquipmentSlot slot) {
        return this.slotProtections[slot.getIndex()];
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public SoundEvent getEquipSound() {
        return this.sound;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    public String getName() {
        return this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
