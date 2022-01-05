package com.hungteen.pvz.common.recipe.fusion;

import com.hungteen.pvz.utils.StringUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class FusionRecipe implements IFusionRecipe {

    public static ResourceLocation TYPE = StringUtil.prefix("fragment_splice");
    private final ResourceLocation resourceLocation;
    private final Ingredient essence;
    private final Ingredient enjoyCard;
    private final Ingredient template;
    private final ItemStack resultCard;

    public FusionRecipe(ResourceLocation id, Ingredient essence, Ingredient enjoyCard, Ingredient template, ItemStack resultCard){
        this.resourceLocation = id;
        this.essence = essence;
        this.enjoyCard = enjoyCard;
        this.template = template;
        this.resultCard = resultCard;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.of(this.essence, this.enjoyCard, this.template);
    }

    @Override
    public boolean matches(RecipeWrapper recipeWrapper, World world) {
        if(this.essence.test(recipeWrapper.getItem(0))){
            if(this.enjoyCard.test(recipeWrapper.getItem(1))){
                if(this.template.test(recipeWrapper.getItem(2))){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public ItemStack assemble(RecipeWrapper recipeWrapper) {
        return this.resultCard.copy();
    }

    @Override
    public ItemStack getResultItem() {
        return this.resultCard;
    }

    @Override
    public ResourceLocation getId() {
        return this.resourceLocation;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        //TODO 融合台
//        return RecipeRegister.FUSION_SERIALIZER.get();
        return null;
    }

    @Override
    public IRecipeType<?> getType() {
        return IRecipeType.CRAFTING;
    }
}
