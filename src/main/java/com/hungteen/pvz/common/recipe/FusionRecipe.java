package com.hungteen.pvz.common.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.hungteen.pvz.utils.StringUtil;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class FusionRecipe implements ICraftingRecipe {

    public static final ResourceLocation UID = StringUtil.prefix("card_fusion");
    private final ResourceLocation id;
    private final String group;
    private final ItemStack result;
    private final NonNullList<Ingredient> ingredients;
    private final boolean isSimple;

    public FusionRecipe(ResourceLocation resourceLocation, String group, ItemStack itemStack, NonNullList<Ingredient> ingredients) {
        this.id = resourceLocation;
        this.group = group;
        this.result = itemStack;
        this.ingredients = ingredients;
        this.isSimple = ingredients.stream().allMatch(Ingredient::isSimple);
    }

    public ResourceLocation getId() {
        return this.id;
    }

    public IRecipeSerializer<?> getSerializer() {
        return RecipeRegister.FUSION_SERIALIZER.get();
    }

    @Override
    public IRecipeType<?> getType() {
        return RecipeRegister.FUSION_RECIPE_TYPE;
    }

    public String getGroup() {
        return this.group;
    }

    public ItemStack getResultItem() {
        return this.result;
    }

    public NonNullList<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public boolean matches(CraftingInventory craftingInventory, World world) {
        RecipeItemHelper recipeitemhelper = new RecipeItemHelper();
        java.util.List<ItemStack> inputs = new java.util.ArrayList<>();
        int i = 0;

        for(int j = 0; j < craftingInventory.getContainerSize(); ++j) {
            ItemStack itemstack = craftingInventory.getItem(j);
            if (!itemstack.isEmpty()) {
                ++i;
                if (isSimple)
                    recipeitemhelper.accountStack(itemstack, 1);
                else inputs.add(itemstack);
            }
        }

        return i == this.ingredients.size() && (isSimple ? recipeitemhelper.canCraft(this, (IntList)null) : net.minecraftforge.common.util.RecipeMatcher.findMatches(inputs,  this.ingredients) != null);
    }

    public ItemStack assemble(CraftingInventory p_77572_1_) {
        return this.result.copy();
    }

    public boolean canCraftInDimensions(int p_194133_1_, int p_194133_2_) {
        return p_194133_1_ * p_194133_2_ >= this.ingredients.size();
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<FusionRecipe> {

         public FusionRecipe fromJson(ResourceLocation p_199425_1_, JsonObject p_199425_2_) {
            String s = JSONUtils.getAsString(p_199425_2_, "group", "");
            NonNullList<Ingredient> nonnulllist = itemsFromJson(JSONUtils.getAsJsonArray(p_199425_2_, "ingredients"));
            if (nonnulllist.isEmpty()) {
                throw new JsonParseException("No ingredients for shapeless recipe");
            } else if (nonnulllist.size() > 9) {
                throw new JsonParseException("Too many ingredients for shapeless recipe the max is " + (9));
            } else {
                ItemStack itemstack = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(p_199425_2_, "result"));
                return new FusionRecipe(p_199425_1_, s, itemstack, nonnulllist);
            }
        }

        private static NonNullList<Ingredient> itemsFromJson(JsonArray p_199568_0_) {
            NonNullList<Ingredient> nonnulllist = NonNullList.create();

            for(int i = 0; i < p_199568_0_.size(); ++i) {
                Ingredient ingredient = Ingredient.fromJson(p_199568_0_.get(i));
                if (!ingredient.isEmpty()) {
                    nonnulllist.add(ingredient);
                }
            }

            return nonnulllist;
        }

        public FusionRecipe fromNetwork(ResourceLocation p_199426_1_, PacketBuffer p_199426_2_) {
            String s = p_199426_2_.readUtf(32767);
            int i = p_199426_2_.readVarInt();
            NonNullList<Ingredient> nonnulllist = NonNullList.withSize(i, Ingredient.EMPTY);

            for(int j = 0; j < nonnulllist.size(); ++j) {
                nonnulllist.set(j, Ingredient.fromNetwork(p_199426_2_));
            }

            ItemStack itemstack = p_199426_2_.readItem();
            return new FusionRecipe(p_199426_1_, s, itemstack, nonnulllist);
        }

        public void toNetwork(PacketBuffer p_199427_1_, FusionRecipe p_199427_2_) {
            p_199427_1_.writeUtf(p_199427_2_.group);
            p_199427_1_.writeVarInt(p_199427_2_.ingredients.size());

            for(Ingredient ingredient : p_199427_2_.ingredients) {
                ingredient.toNetwork(p_199427_1_);
            }

            p_199427_1_.writeItem(p_199427_2_.result);
        }
    }
}
