package com.hungteen.pvz.data.recipe;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.hungteen.pvz.common.recipe.PVZRecipes;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.Registry;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-22 10:16
 *
 * TODO auto gen radiation recipes.
 **/
public class RadiationRecipeBuilder {

//    private final ItemLike result;
//    private Ingredient ingredient;
//    private final Advancement.Builder advancement = Advancement.Builder.advancement();
//    private String group;
//
//    public RadiationRecipeBuilder(ItemLike itemProvider) {
//        this.result = itemProvider;
//    }
//
//    public RadiationRecipeBuilder requires(TagKey<Item> itemITag) {
//        return this.requires(Ingredient.of(itemITag));
//    }
//
//    public RadiationRecipeBuilder requires(ItemLike itemProvider) {
//        return this.requires(itemProvider, 1);
//    }
//
//    public RadiationRecipeBuilder requires(Ingredient ingredient) {
//        return this.requires(ingredient);
//    }
//
//    public RadiationRecipeBuilder unlockedBy(String p_200483_1_, CriterionTriggerInstance p_200483_2_) {
//        this.advancement.addCriterion(p_200483_1_, p_200483_2_);
//        return this;
//    }
//
//    public RadiationRecipeBuilder group(String p_200490_1_) {
//        this.group = p_200490_1_;
//        return this;
//    }
//
//    public void save(Consumer<FinishedRecipe> p_200482_1_) {
//        this.save(p_200482_1_, ForgeRegistries.ITEMS.getKey(this.result.asItem()));
//    }
//
//    public void save(Consumer<FinishedRecipe> p_200484_1_, String p_200484_2_) {
//        ResourceLocation resourcelocation =  ForgeRegistries.ITEMS.getKey(this.result.asItem());
//        if ((new ResourceLocation(p_200484_2_)).equals(resourcelocation)) {
//            throw new IllegalStateException("Shapeless Recipe " + p_200484_2_ + " should remove its 'save' argument");
//        } else {
//            this.save(p_200484_1_, new ResourceLocation(p_200484_2_));
//        }
//    }
//
//    public void save(Consumer<FinishedRecipe> p_200485_1_, ResourceLocation p_200485_2_) {
//        this.ensureValid(p_200485_2_);
//        this.advancement.parent(new ResourceLocation("recipes/root")).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(p_200485_2_)).rewards(AdvancementRewards.Builder.recipe(p_200485_2_)).requirements(IRequirementsStrategy.OR);
//        p_200485_1_.accept(new RadiationRecipeBuilder.Result(p_200485_2_, this.result, this.group == null ? "" : this.group, this.ingredient, this.advancement, new ResourceLocation(p_200485_2_.getNamespace(), "recipes/" + this.result.getItemCategory().getRecipeFolderName() + "/" + p_200485_2_.getPath())));
//    }
//
//    private void ensureValid(ResourceLocation p_200481_1_) {
////        if (this.advancement.getCriteria().isEmpty()) {
////            throw new IllegalStateException("No way of obtaining recipe " + p_200481_1_);
////        }
//    }
//
//    public static class Result implements FinishedRecipe {
//        private final ResourceLocation id;
//        private final Item result;
//        private final String group;
//        private final Ingredient ingredient;
//        private final Advancement.Builder advancement;
//        private final ResourceLocation advancementId;
//
//        public Result(ResourceLocation p_i48268_1_, Item p_i48268_2_, String p_i48268_4_, Ingredient p_i48268_5_, Advancement.Builder p_i48268_6_, ResourceLocation p_i48268_7_) {
//            this.id = p_i48268_1_;
//            this.result = p_i48268_2_;
//            this.group = p_i48268_4_;
//            this.ingredient = p_i48268_5_;
//            this.advancement = p_i48268_6_;
//            this.advancementId = p_i48268_7_;
//        }
//
//        public void serializeRecipeData(JsonObject p_218610_1_) {
//            if (!this.group.isEmpty()) {
//                p_218610_1_.addProperty("group", this.group);
//            }
//
//            p_218610_1_.add("ingredient", this.ingredient.toJson());
//
//            JsonObject jsonobject = new JsonObject();
//            jsonobject.addProperty("item", Registry.ITEM.getKey(this.result).toString());
//            if (this.count > 1) {
//                jsonobject.addProperty("count", this.count);
//            }
//
//            p_218610_1_.add("result", jsonobject);
//        }
//
//        public RecipeSerializer<?> getType() {
//            return PVZRecipes.RADIATION_SERIALIZER.get();
//        }
//
//        public ResourceLocation getId() {
//            return this.id;
//        }
//
//        @Nullable
//        public JsonObject serializeAdvancement() {
//            return this.advancement.serializeToJson();
//        }
//
//        @Nullable
//        public ResourceLocation getAdvancementId() {
//            return this.advancementId;
//        }
//    }

}
