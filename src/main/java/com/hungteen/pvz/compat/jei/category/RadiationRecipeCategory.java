package com.hungteen.pvz.compat.jei.category;

import com.hungteen.pvz.common.block.PVZBlocks;
import com.hungteen.pvz.common.recipe.RadiationRecipe;
import com.hungteen.pvz.compat.jei.PVZJEIHandler;
import com.hungteen.pvz.utils.Util;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-21 21:14
 **/
public class RadiationRecipeCategory implements IRecipeCategory<RadiationRecipe> {

    private final IDrawable slotDraw;
    private final IDrawable bgDraw;
    private final IDrawable iconDraw;
    private final IDrawable arrowDraw;

    public RadiationRecipeCategory(IGuiHelper helper) {
        this.slotDraw = helper.getSlotDrawable();
        this.bgDraw = helper.createBlankDrawable(160, 50);
        this.arrowDraw = helper.drawableBuilder(Util.WIDGETS, 44, 64, 22, 15).build();
        this.iconDraw = helper.createDrawableIngredient(VanillaTypes.ITEM, new ItemStack(PVZBlocks.ORIGIN_BLOCK.get()));
    }

    @Override
    public void draw(RadiationRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack matrixStack, double mouseX, double mouseY) {
        matrixStack.pushPose();
        this.arrowDraw.draw(matrixStack, 69, 17);
        matrixStack.popPose();
    }

    @Override
    public ResourceLocation getUid() {
        return Util.prefix("radiation");
    }

    @Override
    public Class<? extends RadiationRecipe> getRecipeClass() {
        return RadiationRecipe.class;
    }

    @Override
    public RecipeType<RadiationRecipe> getRecipeType() {
        return PVZJEIHandler.RADIATION_RECIPE;
    }

    @Override
    public Component getTitle() {
        return new TranslatableComponent("block.pvz.origin_block");
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
    public void setRecipe(IRecipeLayoutBuilder builder, RadiationRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 39, 17)
                .addIngredients(recipe.getIngredients().get(0))
                .setBackground(this.slotDraw, -1, -1);

        builder.addSlot(RecipeIngredientRole.OUTPUT, 105, 17)
                .addItemStack(recipe.getResultItem())
                .setBackground(this.slotDraw, -1, -1);

//        final IGuiIngredientGroup<ItemStack> guiIngredientGroup = recipeLayout.getIngredientsGroup(VanillaTypes.ITEM);
//        for(int i = 0; i < 9; ++ i){
//            final int x = i / 3;
//            final int y = i % 3;
//
//
//            guiIngredientGroup.init(i, true, x * 20 + 30, y * 20 + 30);
//            if(i < recipe.getIngredients().size()){
//                guiIngredientGroup.set(i, Arrays.asList(recipe.getIngredients().get(i).getItems()));
//            }
//            guiIngredientGroup.setBackground(i, this.slotDraw);
//        }
//
//        guiIngredientGroup.init(9, false, 140, 50);
//        guiIngredientGroup.set(9, recipe.getResultItem());
//        guiIngredientGroup.setBackground(9, this.slotDraw);
//
//        guiIngredientGroup.set(ingredients);
    }

}
