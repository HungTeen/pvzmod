package com.hungteen.pvz.potion;

import com.hungteen.pvz.register.ItemRegister;
import com.hungteen.pvz.register.PotionRegister;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

public class PotionRecipeHandler {

	public static final Ingredient REDSTONE = Ingredient.fromItems(Items.REDSTONE);
	public static final Ingredient GLOWSTONE = Ingredient.fromItems(Items.GLOWSTONE_DUST);
	public static final ItemStack AWKWARD_POTION = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.AWKWARD);
	public static final ItemStack EXCITE_POTION_1 = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionRegister.EXCITE_POTION_1.get());
	public static final ItemStack EXCITE_POTION_2 = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionRegister.EXCITE_POTION_2.get());
	public static final ItemStack EXCITE_POTION_3 = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionRegister.EXCITE_POTION_3.get());
	
	public static void init(){
//		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(EMPTY_POTION), Ingredient.fromItems(ItemRegister.CHOCOLATE.get()), EXCITE_POTION_1);
//		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(EXCITE_POTION_1), GLOWSTONE, EXCITE_POTION_2);
//		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(EXCITE_POTION_1), REDSTONE, EXCITE_POTION_3);
		add(Ingredient.fromStacks(AWKWARD_POTION), Ingredient.fromItems(ItemRegister.CHOCOLATE.get()), EXCITE_POTION_1);
		add(Ingredient.fromStacks(EXCITE_POTION_1), GLOWSTONE, EXCITE_POTION_2);
		add(Ingredient.fromStacks(EXCITE_POTION_1), REDSTONE, EXCITE_POTION_3);
	}
	
	public static void add(Ingredient a, Ingredient b, ItemStack c) {
		BrewingRecipeRegistry.addRecipe(new PVZBrewRecipe(a, b, c));
	}
}
