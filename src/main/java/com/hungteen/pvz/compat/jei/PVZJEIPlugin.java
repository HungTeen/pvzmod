package com.hungteen.pvz.compat.jei;

import com.hungteen.pvz.common.block.PVZBlocks;
import com.hungteen.pvz.common.recipe.PVZRecipeTypes;
import com.hungteen.pvz.common.recipe.PVZRecipes;
import com.hungteen.pvz.compat.jei.category.RadiationRecipeCategory;
import com.hungteen.pvz.utils.Util;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-21 21:12
 **/
@JeiPlugin
public class PVZJEIPlugin implements IModPlugin {

    public static final ResourceLocation ID = Util.prefix("jei");

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(PVZJEIHandler.RADIATION_RECIPE, PVZJEIHandler.getRecipeManager().getAllRecipesFor(PVZRecipeTypes.RADIATION_RECIPE_TYPE.get()));
//        registration.addRecipes(PVZJEIHandler.getRecipeManager().getAllRecipesFor(RecipeRegister.FRAGMENT_RECIPE_TYPE), FragmentRecipe.UID);
//        registration.addRecipes(PVZJEIHandler.getRecipeManager().getAllRecipesFor(RecipeRegister.FUSION_RECIPE_TYPE), FusionRecipe.UID);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new RadiationRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
//        registration.addRecipeCategories(new FragmentRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
//        registration.addRecipeCategories(new FusionRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(PVZBlocks.ORIGIN_BLOCK.get()), PVZJEIHandler.RADIATION_RECIPE);
//        registration.addRecipeCatalyst(BlockRegister.FRAGMENT_SPLICE.get().asItem().getDefaultInstance(), FragmentRecipe.UID);
//        registration.addRecipeCatalyst(BlockRegister.CARD_FUSION_TABLE.get().asItem().getDefaultInstance(), FusionRecipe.UID);
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
//        registration.addRecipeTransferHandler(FragmentSpliceContainer.class, FragmentRecipe.UID, 2, 25, 27, 36);
//        registration.addRecipeTransferHandler(CardFusionContainer.class, FusionRecipe.UID, 3, 9, 12, 36);
    }

    @Override
    public ResourceLocation getPluginUid() {
        return ID;
    }

}
