package com.hungteen.pvz.common.recipe.fusion;

import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class FusionRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<FusionRecipe> {
    @Override
    public FusionRecipe fromJson(ResourceLocation resourceLocation, JsonObject jsonObject) {
        final Ingredient essence = Ingredient.fromJson(JSONUtils.getAsJsonObject(jsonObject, "essence"));
        final Ingredient enjoyCard = Ingredient.fromJson(JSONUtils.getAsJsonObject(jsonObject, "enjoy_card"));
        final Ingredient template = Ingredient.fromJson(JSONUtils.getAsJsonObject(jsonObject, "template"));
        final ItemStack resultCard = CraftingHelper.getItemStack(JSONUtils.getAsJsonObject(jsonObject, "result"), true);
        return new FusionRecipe(resourceLocation, essence, enjoyCard, template, resultCard);
    }

    @Nullable
    @Override
    public FusionRecipe fromNetwork(ResourceLocation resourceLocation, PacketBuffer packetBuffer) {
        final Ingredient essence = Ingredient.fromNetwork(packetBuffer);
        final Ingredient enjoyCard = Ingredient.fromNetwork(packetBuffer);
        final Ingredient template = Ingredient.fromNetwork(packetBuffer);
        final ItemStack resultCard = packetBuffer.readItem();
        return new FusionRecipe(resourceLocation, essence, enjoyCard, template, resultCard);
    }

    @Override
    public void toNetwork(PacketBuffer packetBuffer, FusionRecipe fragmentRecipe) {
        for(int i = 0; i < fragmentRecipe.getIngredients().size(); ++ i){
            fragmentRecipe.getIngredients().get(i).toNetwork(packetBuffer);
        }
        packetBuffer.writeItem(fragmentRecipe.getResultItem());
    }
}
