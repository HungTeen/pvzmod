package com.hungteen.pvz.common.recipe;

import com.hungteen.pvz.PVZMod;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-21 17:57
 **/
public class PVZRecipes {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, PVZMod.MOD_ID);

    static {
        //allow larger square crafting table.
        ShapedRecipe.setCraftingSize(5, 5);
    }

    //serializer.
    public static final RegistryObject<RadiationRecipe.Serializer> RADIATION_SERIALIZER = RECIPE_SERIALIZERS.register("radiation", RadiationRecipe.Serializer::new);
//    public static final RegistryObject<FusionRecipe.Serializer> FUSION_SERIALIZER = RECIPE_SERIALIZERS.register("card_fusion", FusionRecipe.Serializer::new);
//    public static final RegistryObject<FragmentRecipe.Serializer> FRAGMENT_SERIALIZER = RECIPE_SERIALIZERS.register("fragment_splice", FragmentRecipe.Serializer::new);

}
