package com.hungteen.pvz.common.recipe.fusion;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public interface IFusionRecipe extends IRecipe<RecipeWrapper> {

    @Override
    default boolean canCraftInDimensions(int w, int h) {
        return false;
    }
}
