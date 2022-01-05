package com.hungteen.pvz.compat.jei.category;

import com.hungteen.pvz.common.block.BlockRegister;
import com.hungteen.pvz.common.recipe.FusionRecipe;
import com.hungteen.pvz.utils.StringUtil;
import com.mojang.blaze3d.matrix.MatrixStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiIngredientGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.Arrays;

public class FusionRecipeCategory implements IRecipeCategory<FusionRecipe> {

    private final IDrawable slotDraw;
    private final IDrawable bgDraw;
    private final IDrawable iconDraw;
    private final IDrawable arrowDraw;

    public FusionRecipeCategory(IGuiHelper helper) {
        this.slotDraw = helper.getSlotDrawable();
        this.bgDraw = helper.createBlankDrawable(180, 120);
        this.arrowDraw = helper.drawableBuilder(StringUtil.WIDGETS, 44, 64, 22, 15).build();
        this.iconDraw = helper.createDrawableIngredient(new ItemStack(BlockRegister.CARD_FUSION_TABLE.get()));
    }

    @Override
    public void draw(FusionRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
    	matrixStack.pushPose();
        this.arrowDraw.draw(matrixStack, 105, 52);
    	matrixStack.popPose();
    }

    @Override
    public ResourceLocation getUid() {
        return FusionRecipe.UID;
    }

    @Override
    public Class<? extends FusionRecipe> getRecipeClass() {
        return FusionRecipe.class;
    }

    @Override
    public String getTitle() {
        return new TranslationTextComponent("block.pvz.card_fusion_table").getString();
    }

    @Override
    public IDrawable getBackground() {
        return this.bgDraw;
    }

    @Override
    public IDrawable getIcon() {
        return this.iconDraw;
    }

    @Override
    public void setIngredients(FusionRecipe recipe, IIngredients ingredients) {
        ingredients.setInputIngredients(recipe.getIngredients());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, FusionRecipe recipe, IIngredients ingredients) {
        final IGuiIngredientGroup<ItemStack> guiIngredientGroup = recipeLayout.getIngredientsGroup(VanillaTypes.ITEM);
        for(int i = 0; i < 9; ++ i){
            final int x = i / 3;
            final int y = i % 3;

            guiIngredientGroup.init(i, true, x * 20 + 30, y * 20 + 30);
            if(i < recipe.getIngredients().size()){
                guiIngredientGroup.set(i, Arrays.asList(recipe.getIngredients().get(i).getItems()));
            }
            guiIngredientGroup.setBackground(i, this.slotDraw);
        }

        guiIngredientGroup.init(9, false, 140, 50);
        guiIngredientGroup.set(9, recipe.getResultItem());
        guiIngredientGroup.setBackground(9, this.slotDraw);

        guiIngredientGroup.set(ingredients);
    }
}
