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
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraftforge.common.data.ForgeRecipeProvider;

public class RecipeGenerator extends ForgeRecipeProvider{

	public RecipeGenerator(DataGenerator generatorIn) {
		super(generatorIn);
	}

	@Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
		//1-1
		registerCommonCard(consumer, ItemRegister.PEA_SHOOTER_CARD.get(), PVZItemTags.PEAS);
		registerCommonCard(consumer, ItemRegister.SUN_FLOWER_CARD.get(), Items.SUNFLOWER);
		registerCommonCard(consumer, ItemRegister.WALL_NUT_CARD.get(), PVZItemTags.NUTS);
		registerCommonCard(consumer, ItemRegister.POTATO_MINE_CARD.get(), Items.POTATO);
		registerCommonCard(consumer, ItemRegister.SNOW_PEA_CARD.get(), ItemRegister.SNOW_PEA.get());
		registerCommonCard(consumer, ItemRegister.CHOMPER_CARD.get(), BlockRegister.CHOMPER.get().asItem());
		//2-1
		registerCommonCard(consumer, ItemRegister.SUN_SHROOM_CARD.get(), ItemRegister.SPORE.get());
		registerCommonCard(consumer, ItemRegister.FUME_SHROOM_CARD.get(), ItemRegister.SPORE.get());
		registerCommonCard(consumer, ItemRegister.GRAVE_BUSTER_CARD.get(), Items.VINE);
		registerCommonCard(consumer, ItemRegister.HYPNO_SHROOM_CARD.get(), Items.RED_MUSHROOM);
		registerCommonCard(consumer, ItemRegister.SCAREDY_SHROOM_CARD.get(), ItemRegister.SPORE.get());
		registerCommonCard(consumer, ItemRegister.ICE_SHROOM_CARD.get(), Items.BROWN_MUSHROOM);
		registerCommonCard(consumer, ItemRegister.DOOM_SHROOM_CARD.get(), ItemRegister.SPORE.get());
		//3-1
		registerCommonCard(consumer, ItemRegister.LILY_PAD_CARD.get(), Items.LILY_PAD);
		registerCommonCard(consumer, ItemRegister.TANGLE_KELP_CARD.get(), Items.SEAGRASS);
		registerCommonCard(consumer, ItemRegister.JALAPENO_CARD.get(), ItemRegister.PEPPER.get());
		registerCommonCard(consumer, ItemRegister.SPIKE_WEED_CARD.get(), Items.GRASS);
		registerCommonCard(consumer, ItemRegister.TORCH_WOOD_CARD.get(), ItemTags.LOGS);
		//4-1
		registerCommonCard(consumer, ItemRegister.PLANTERN_CARD.get(), BlockRegister.LANTERN.get().asItem());
		registerCommonCard(consumer, ItemRegister.CACTUS_CARD.get(), Items.CACTUS);
		registerCommonCard(consumer, ItemRegister.BLOVER_CARD.get(), Items.GRASS);
		registerCommonCard(consumer, ItemRegister.PUMPKIN_CARD.get(), Items.PUMPKIN);
		registerCommonCard(consumer, ItemRegister.MAGNET_SHROOM_CARD.get(), ItemRegister.SPORE.get());
		//5-1
		registerCommonCard(consumer, ItemRegister.CABBAGE_PULT_CARD.get(), PVZItemTags.CABBAGES);
		registerCommonCard(consumer, ItemRegister.FLOWER_POT_CARD.get(), Items.FLOWER_POT);
		registerCommonCard(consumer, ItemRegister.KERNEL_PULT_CARD.get(), PVZItemTags.CORNS);
		registerCommonCard(consumer, ItemRegister.COFFEE_BEAN_CARD.get(), Items.COCOA_BEANS);
		registerCommonCard(consumer, ItemRegister.UMBRELLA_LEAF_CARD.get(), Items.CARROT);
		registerCommonCard(consumer, ItemRegister.MARIGOLD_CARD.get(), Items.OXEYE_DAISY);
		registerCommonCard(consumer, ItemRegister.MELON_PULT_CARD.get(), Items.MELON);
		//other
		registerCommonCard(consumer, ItemRegister.BAMBOO_LORD_CARD.get(), Items.BAMBOO);
		registerCommonCard(consumer, ItemRegister.ICEBERG_LETTUCE_CARD.get(), PVZItemTags.CABBAGES);
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
