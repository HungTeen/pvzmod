package com.hungteen.pvz.compat.jei;

import com.hungteen.pvz.common.block.BlockRegister;
import com.hungteen.pvz.common.container.CardFusionContainer;
import com.hungteen.pvz.common.container.FragmentSpliceContainer;
import com.hungteen.pvz.common.recipe.FragmentRecipe;
import com.hungteen.pvz.common.recipe.FusionRecipe;
import com.hungteen.pvz.common.recipe.RecipeRegister;
import com.hungteen.pvz.compat.jei.category.FragmentRecipeCategory;
import com.hungteen.pvz.compat.jei.category.FusionRecipeCategory;
import com.hungteen.pvz.utils.StringUtil;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class PVZJEIPlugin implements IModPlugin {

    public static final ResourceLocation ID = StringUtil.prefix("jei");

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(PVZJEIHandler.getRecipeManager().getAllRecipesFor(RecipeRegister.FRAGMENT_RECIPE_TYPE), FragmentRecipe.UID);
        registration.addRecipes(PVZJEIHandler.getRecipeManager().getAllRecipesFor(RecipeRegister.FUSION_RECIPE_TYPE), FusionRecipe.UID);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new FragmentRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new FusionRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(BlockRegister.FRAGMENT_SPLICE.get().asItem().getDefaultInstance(), FragmentRecipe.UID);
        registration.addRecipeCatalyst(BlockRegister.CARD_FUSION_TABLE.get().asItem().getDefaultInstance(), FusionRecipe.UID);
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(FragmentSpliceContainer.class, FragmentRecipe.UID, 2, 25, 27, 36);
        registration.addRecipeTransferHandler(CardFusionContainer.class, FusionRecipe.UID, 3, 9, 12, 36);
    }

    @Override
    public ResourceLocation getPluginUid() {
        return ID;
    }

}
