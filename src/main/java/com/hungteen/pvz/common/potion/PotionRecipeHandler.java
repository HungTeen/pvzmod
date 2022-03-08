package com.hungteen.pvz.common.potion;

import com.hungteen.pvz.common.item.ItemRegister;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.effect.Potion;
import net.minecraft.world.effect.PotionUtils;
import net.minecraft.world.effect.Potions;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

public class PotionRecipeHandler {

	public static final Ingredient REDSTONE = Ingredient.of(Items.REDSTONE);
	public static final Ingredient GLOWSTONE = Ingredient.of(Items.GLOWSTONE_DUST);
	public static final ItemStack AWKWARD_POTION = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD);
	public static final ItemStack EXCITE_POTION_1 = PotionUtils.setPotion(new ItemStack(Items.POTION), PotionRegister.EXCITE_POTION_1.get());
	public static final ItemStack EXCITE_POTION_2 = PotionUtils.setPotion(new ItemStack(Items.POTION), PotionRegister.EXCITE_POTION_2.get());
	public static final ItemStack EXCITE_POTION_3 = PotionUtils.setPotion(new ItemStack(Items.POTION), PotionRegister.EXCITE_POTION_3.get());
	public static final ItemStack LIGHT_EYE_POTION_1 = PotionUtils.setPotion(new ItemStack(Items.POTION), PotionRegister.LIGHT_EYE_POTION_1.get());
	public static final ItemStack LIGHT_EYE_POTION_2 = PotionUtils.setPotion(new ItemStack(Items.POTION), PotionRegister.LIGHT_EYE_POTION_2.get());
	
	/**
	 * register potion recipes.
	 */
	public static void registerPotionRecipes(){
		add(Ingredient.of(AWKWARD_POTION), Ingredient.of(ItemRegister.CHOCOLATE.get()), EXCITE_POTION_1);
		add(Ingredient.of(EXCITE_POTION_1), GLOWSTONE, EXCITE_POTION_2);
		add(Ingredient.of(EXCITE_POTION_1), REDSTONE, EXCITE_POTION_3);
	}
	
	private static void add(Ingredient a, Ingredient b, ItemStack c) {
		BrewingRecipeRegistry.addRecipe(new PVZBrewRecipe(a, b, c));
	}
	
	public static ItemStack getPotionItemStack(Potion p) {
		return PotionUtils.setPotion(new ItemStack(Items.POTION), p);
	}
	
}
