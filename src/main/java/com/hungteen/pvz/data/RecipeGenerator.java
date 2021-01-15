package com.hungteen.pvz.data;

import java.util.function.Consumer;

import com.hungteen.pvz.item.tool.card.PlantCardItem;
import com.hungteen.pvz.misc.tag.PVZItemTags;
import com.hungteen.pvz.register.BlockRegister;
import com.hungteen.pvz.register.ItemRegister;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.enums.Essences;
import com.hungteen.pvz.utils.enums.Ranks;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.Tag;
import net.minecraftforge.common.data.ForgeRecipeProvider;

public class RecipeGenerator extends ForgeRecipeProvider{

	public RecipeGenerator(DataGenerator generatorIn) {
		super(generatorIn);
	}

	@Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
		registerCommonCard(consumer, ItemRegister.PEA_SHOOTER_CARD.get(), PVZItemTags.PEAS);
		registerCommonCard(consumer, ItemRegister.GRAVE_BUSTER_CARD.get(), Items.VINE);
		registerCommonCard(consumer, ItemRegister.HYPNO_SHROOM_CARD.get(), Items.RED_MUSHROOM);
		registerCommonCard(consumer, ItemRegister.ICE_SHROOM_CARD.get(), Items.BROWN_MUSHROOM);
		registerCommonCard(consumer, ItemRegister.DOOM_SHROOM_CARD.get(), Items.BROWN_MUSHROOM);
		registerCommonCard(consumer, ItemRegister.PUMPKIN_CARD.get(), Items.PUMPKIN);
		registerCommonCard(consumer, ItemRegister.COFFEE_BEAN_CARD.get(), Items.COCOA_BEANS);
		registerCommonCard(consumer, ItemRegister.MARIGOLD_CARD.get(), Items.OXEYE_DAISY);
		registerCommonCard(consumer, ItemRegister.WATER_GUARD_CARD.get(), Items.LILY_PAD);
		registerCommonCard(consumer, ItemRegister.PLANTERN_CARD.get(), BlockRegister.LANTERN.get().asItem());
		registerCommonCard(consumer, ItemRegister.CACTUS_CARD.get(), Items.CACTUS);
		registerCommonCard(consumer, ItemRegister.FLOWER_POT_CARD.get(), Items.FLOWER_POT);
    }
	
	private void registerCommonCard(Consumer<IFinishedRecipe> consumer, PlantCardItem result, Item crop) {
		Item essence = Essences.getEssenceItem(PlantUtil.getPlantEssenceType(result.plantType));
		Item rankCard = Ranks.getRankCardItem(PlantUtil.getPlantRankByName(result.plantType));
		ShapedRecipeBuilder.shapedRecipe(result).patternLine("AAA").patternLine("ABA").patternLine("ACA")
		.key('A', essence).key('B', crop).key('C', rankCard).addCriterion("has_essence", this.hasItem(essence)).build(consumer);
	}
	
	private void registerCommonCard(Consumer<IFinishedRecipe> consumer, PlantCardItem result, Tag<Item> crop) {
		Item essence = Essences.getEssenceItem(PlantUtil.getPlantEssenceType(result.plantType));
		Item rankCard = Ranks.getRankCardItem(PlantUtil.getPlantRankByName(result.plantType));
		ShapedRecipeBuilder.shapedRecipe(result).patternLine("AAA").patternLine("ABA").patternLine("ACA")
		.key('A', essence).key('B', crop).key('C', rankCard).addCriterion("has_essence", this.hasItem(essence)).build(consumer);
	}
	
}
