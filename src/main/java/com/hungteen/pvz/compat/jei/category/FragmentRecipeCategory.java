package com.hungteen.pvz.compat.jei.category;

import com.hungteen.pvz.common.block.BlockRegister;
import com.hungteen.pvz.common.recipe.FragmentRecipe;
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

public class FragmentRecipeCategory implements IRecipeCategory<FragmentRecipe> {

    private final IDrawable slotDraw;
    private final IDrawable bgDraw;
    private final IDrawable iconDraw;
    private final IDrawable arrowDraw;

    public FragmentRecipeCategory(IGuiHelper helper) {
        this.slotDraw = helper.getSlotDrawable();
        this.bgDraw = helper.createBlankDrawable(180, 120);
        this.arrowDraw = helper.drawableBuilder(StringUtil.WIDGETS, 44, 64, 22, 15).build();
        this.iconDraw = helper.createDrawableIngredient(new ItemStack(BlockRegister.FRAGMENT_SPLICE.get()));
    }

    @Override
    public void draw(FragmentRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
    	matrixStack.pushPose();
        this.arrowDraw.draw(matrixStack, 110, 52);
    	matrixStack.popPose();
    }

    @Override
    public ResourceLocation getUid() {
        return FragmentRecipe.UID;
    }

    @Override
    public Class<? extends FragmentRecipe> getRecipeClass() {
        return FragmentRecipe.class;
    }

    @Override
    public String getTitle() {
        return new TranslationTextComponent("block.pvz.fragment_splice").getString();
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
    public void setIngredients(FragmentRecipe recipe, IIngredients ingredients) {
        ingredients.setInputIngredients(recipe.getIngredients());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, FragmentRecipe recipe, IIngredients ingredients) {
        final IGuiIngredientGroup<ItemStack> guiIngredientGroup = recipeLayout.getIngredientsGroup(VanillaTypes.ITEM);
        for(int i = 0; i < 5; ++ i){
            for(int j = 0; j < 5; ++ j){
                final int slotId = i * 5 + j;
                guiIngredientGroup.init(slotId, true, i * 20 + 10, j * 20 + 10);
                guiIngredientGroup.set(slotId, Arrays.asList(recipe.getIngredients().get(slotId).getItems()));
                guiIngredientGroup.setBackground(slotId, this.slotDraw);
            }
        }

        guiIngredientGroup.init(25, false, 150, 50);
        guiIngredientGroup.set(25, recipe.getResultItem());
        guiIngredientGroup.setBackground(25, this.slotDraw);

        guiIngredientGroup.set(ingredients);
    }
}
