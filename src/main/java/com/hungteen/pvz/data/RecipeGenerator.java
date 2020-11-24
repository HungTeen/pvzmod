package com.hungteen.pvz.data;

import java.util.function.Consumer;

import com.hungteen.pvz.item.tool.card.PlantCardItem;
import com.hungteen.pvz.register.ItemRegister;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.enums.Essences;
import com.hungteen.pvz.utils.enums.Ranks;

import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.common.data.ForgeRecipeProvider;

public class RecipeGenerator extends ForgeRecipeProvider{

	public RecipeGenerator(DataGenerator generatorIn) {
		super(generatorIn);
	}

	@Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
		registerCommonCard(consumer, ItemRegister.PEA_SHOOTER_CARD.get(), ItemRegister.PEA.get());
        ShapedRecipeBuilder.shapedRecipe(ItemRegister.STEEL_INGOT.get())
                .patternLine("III")
                .patternLine(" G ")
                .patternLine(" G ")
                .key('I', Items.IRON_INGOT)
                .key('G', Items.GOLD_INGOT)
                .addCriterion("iron_ingot", InventoryChangeTrigger.Instance.forItems(Items.IRON_INGOT, Items.GOLD_INGOT))
                .build(consumer);
    }
	
	private void registerCommonCard(Consumer<IFinishedRecipe> consumer, PlantCardItem result, Item crop) {
		Item essence = Essences.getEssenceItem(PlantUtil.getPlantEssenceType(result.getPlant()));
		Item rankCard = Ranks.getRankCardItem(PlantUtil.getPlantRankByName(result.getPlant()));
		ShapedRecipeBuilder.shapedRecipe(result).patternLine("AAA").patternLine("ABA").patternLine("ACA")
		.key('A', essence).key('B', crop).key('C', rankCard).addCriterion("has_essence", this.hasItem(essence)).build(consumer);
	}
	
}
