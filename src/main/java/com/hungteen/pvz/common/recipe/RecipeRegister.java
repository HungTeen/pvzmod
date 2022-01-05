package com.hungteen.pvz.common.recipe;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.recipe.fragment.FragmentRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RecipeRegister {

    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, PVZMod.MOD_ID);

    static {
        ShapedRecipe.setCraftingSize(5, 5);
    }
    //recipe type.
//    public static final IRecipeType<FusionRecipe> FUSION_RECIPE_TYPE = registerType(FusionRecipe.TYPE);
    public static final IRecipeType<FragmentRecipe> FRAGMENT_RECIPE_TYPE = IRecipeType.register("pvz:fragment_splice");
//    registerType(FragmentRecipe.TYPE);

    //serializer.
//    public static final RegistryObject<FusionRecipeSerializer> FUSION_SERIALIZER = RECIPE_SERIALIZERS.register("fusion", FusionRecipeSerializer::new);
    public static final RegistryObject<FragmentRecipe.Serializer> FRAGMENT_SERIALIZER = RECIPE_SERIALIZERS.register("fragment_splice", FragmentRecipe.Serializer::new);

    public static class RecipeType<T extends IRecipe<?>> implements IRecipeType<T> {
        @Override
        public String toString() {
            return Registry.RECIPE_TYPE.getKey(this).toString();
        }
    }

    public static <T extends IRecipe<?>> T registerType(ResourceLocation resourceLocation){
//        IRecipeType
        return (T) Registry.register(Registry.RECIPE_TYPE, resourceLocation, new RecipeType<>());
    }

}
