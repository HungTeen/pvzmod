package com.hungteen.pvz.data;

import java.util.function.Consumer;

import com.hungteen.pvz.register.ItemRegister;

import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraftforge.common.data.ForgeRecipeProvider;

public class RecipeGenerator extends ForgeRecipeProvider{

	public RecipeGenerator(DataGenerator generatorIn) {
		super(generatorIn);
	}

	@Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(ItemRegister.STEEL_INGOT.get())
                .patternLine("III")
                .patternLine(" G ")
                .patternLine(" G ")
                .key('I', Items.IRON_INGOT)
                .key('G', Items.GOLD_INGOT)
                .addCriterion("iron_ingot", InventoryChangeTrigger.Instance.forItems(Items.IRON_INGOT, Items.GOLD_INGOT))
                .build(consumer);
    }
}
