package com.hungteen.pvz.common.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.hungteen.pvz.common.block.PVZBlocks;
import com.hungteen.pvz.utils.Util;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistryEntry;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-21 17:56
 **/
public class RadiationRecipe implements Recipe<Container> {

    protected final ResourceLocation id;
    protected final String group;
    protected final Ingredient ingredient;
    protected final ItemStack result;

    public RadiationRecipe(ResourceLocation id, String group, Ingredient ingredient, ItemStack result){
        this.id = id;
        this.group = group;
        this.ingredient = ingredient;
        this.result = result;
    }

    @Override
    public boolean matches(Container container, Level level) {
        return this.ingredient.test(container.getItem(0));
    }

    @Override
    public ItemStack assemble(Container container) {
        return this.result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return this.result;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        final NonNullList<Ingredient> list = NonNullList.create();
        list.add(ingredient);
        return list;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public String getGroup() {
        return this.group;
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(PVZBlocks.ORIGIN_BLOCK.get());
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return PVZRecipes.RADIATION_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return PVZRecipeTypes.RADIATION_RECIPE_TYPE.get();
    }

    /**
     * copy from {@link SimpleCookingSerializer}
     */
    public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<RadiationRecipe> {

        @Override
        public RadiationRecipe fromJson(ResourceLocation resourceLocation, JsonObject jsonObject) {
            String s = GsonHelper.getAsString(jsonObject, "group", "");

            JsonElement jsonelement = (JsonElement)(GsonHelper.isArrayNode(jsonObject, "ingredient") ? GsonHelper.getAsJsonArray(jsonObject, "ingredient") : GsonHelper.getAsJsonObject(jsonObject, "ingredient"));
            Ingredient ingredient = Ingredient.fromJson(jsonelement);

            //Forge: Check if primitive string to keep vanilla or a object which can contain a count field.
            if (!jsonObject.has("result")) throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
            ItemStack itemstack;
            if (jsonObject.get("result").isJsonObject()) itemstack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "result"));
            else {
                String s1 = GsonHelper.getAsString(jsonObject, "result");
                ResourceLocation resourcelocation = new ResourceLocation(s1);
                itemstack = new ItemStack(Registry.ITEM.getOptional(resourcelocation).orElseThrow(() -> {
                    return new IllegalStateException("Item: " + s1 + " does not exist");
                }));
            }

            return new RadiationRecipe(resourceLocation, s, ingredient, itemstack);
        }

        @Override
        public RadiationRecipe fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf byteBuf) {
            String s = byteBuf.readUtf();
            Ingredient ingredient = Ingredient.fromNetwork(byteBuf);
            ItemStack itemstack = byteBuf.readItem();
            return new RadiationRecipe(resourceLocation, s, ingredient, itemstack);
        }

        @Override
        public void toNetwork(FriendlyByteBuf byteBuf, RadiationRecipe recipe) {
            byteBuf.writeUtf(recipe.group);
            recipe.ingredient.toNetwork(byteBuf);
            byteBuf.writeItem(recipe.result);
        }

    }

}