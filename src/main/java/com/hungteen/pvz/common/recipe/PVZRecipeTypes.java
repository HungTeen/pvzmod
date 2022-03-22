package com.hungteen.pvz.common.recipe;

import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.common.util.LazyOptional;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-21 23:14
 **/
public class PVZRecipeTypes {

    //recipe type.
    public static final Lazy<RecipeType<RadiationRecipe>> RADIATION_RECIPE_TYPE = Lazy.of(() -> RecipeType.register("pvz:radiation"));

//    public static final IRecipeType<FusionRecipe> FUSION_RECIPE_TYPE = RecipeType.register("pvz:card_fusion");
//    public static final IRecipeType<FragmentRecipe> FRAGMENT_RECIPE_TYPE = RecipeType.register("pvz:fragment_splice");
//    registerType(FragmentRecipe.TYPE);

    public static void registerRecipeTypes(){
        RADIATION_RECIPE_TYPE.get();
    }

}
