package com.hungteen.pvz.data.recipe;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.api.types.IRankType;
import com.hungteen.pvz.common.block.BlockRegister;
import com.hungteen.pvz.common.impl.RankTypes;
import com.hungteen.pvz.common.item.ItemRegister;
import com.hungteen.pvz.common.item.spawn.card.PlantCardItem;
import com.hungteen.pvz.common.misc.tag.PVZItemTags;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.IRecipeSerializer;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.data.ForgeRecipeProvider;

public class RecipeGenerator extends ForgeRecipeProvider{

	public RecipeGenerator(DataGenerator generatorIn) {
		super(generatorIn);
	}

	@Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
		//template cards.
		RankTypes.getRanks().forEach(type -> {
			registerTemplateCard(consumer, type);
		});
		//summon card
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
		registerCommonCard(consumer, ItemRegister.BONK_CHOY_CARD.get(), PVZItemTags.CABBAGES);
		
		//smelt
		registerStoneSmelting(consumer, BlockRegister.AMETHYST_ORE.get(), ItemRegister.AMETHYST_INGOT.get(), 1.4F, 250, "amethyst_ingot");
		registerFoodSmelting(consumer, ItemRegister.FAKE_BRAIN.get(), ItemRegister.COOKED_BRAIN.get(), 0.4F, 200, "cooked_brain");

		//fragment splice
		PVZAPI.get().getPlants().forEach(p -> {
			registerFragment(consumer, p);
		});

		//fusion recipe
		registerFusion(consumer, Arrays.asList(
				ItemRegister.PEA_SHOOTER_CARD.get(),
				ItemRegister.PEA_SHOOTER_CARD.get()
				), ItemRegister.REPEATER_CARD.get());

		registerFusion(consumer, Arrays.asList(
				ItemRegister.PEA_SHOOTER_CARD.get(),
				ItemRegister.PEA_SHOOTER_CARD.get(),
				ItemRegister.PEA_SHOOTER_CARD.get()
		), ItemRegister.THREE_PEATER_CARD.get());

		registerFusion(consumer, Arrays.asList(
				ItemRegister.PEA_SHOOTER_CARD.get(),
				ItemRegister.REPEATER_CARD.get()
		), ItemRegister.SPLIT_PEA_CARD.get());

		registerFusion(consumer, Arrays.asList(
				ItemRegister.LILY_PAD_CARD.get(),
				ItemRegister.WALL_NUT_CARD.get()
		), ItemRegister.WATER_GUARD_CARD.get());

		registerFusion(consumer, Arrays.asList(
				ItemRegister.FUME_SHROOM_CARD.get(),
				ItemRegister.SCAREDY_SHROOM_CARD.get()
		), ItemRegister.PUFF_SHROOM_CARD.get());

		registerFusion(consumer, Arrays.asList(
				ItemRegister.WALL_NUT_CARD.get(),
				ItemRegister.WALL_NUT_CARD.get()
		), ItemRegister.TALL_NUT_CARD.get());

		registerFusion(consumer, Arrays.asList(
				ItemRegister.SCAREDY_SHROOM_CARD.get(),
				ItemRegister.TANGLE_KELP_CARD.get()
		), ItemRegister.SEA_SHROOM_CARD.get());
	}

	private void registerFusion(Consumer<IFinishedRecipe> consumer, List<IItemProvider> list, Item result) {
		final ItemStack stack = new ItemStack(result);
		final FusionRecipeBuilder builder = FusionRecipeBuilder.shapeless(result);
		list.forEach(i -> builder.requires(i));
		if(result instanceof PlantCardItem){
			builder.requires(((PlantCardItem) result).plantType.getRank().getCardTag());
		}
		builder.save(consumer, StringUtil.prefix("card_fusion/" + result.getRegistryName().getPath()));
	}

	private void registerFragment(Consumer<IFinishedRecipe> consumer, IPlantType type) {
		type.getSummonCard().ifPresent(card -> {
			if(type.getEnjoyCard().isPresent()) {
				FragmentRecipeBuilder.shaped(card)
					.pattern("AAAAA")
					.pattern("ABBBA")
					.pattern("ABCBA")
					.pattern("ABBBA")
					.pattern("AAAAA")
					.define('A', type.getEnjoyCard().get())
					.define('B', type.getEssence().getEssenceItem())
					.define('C', type.getRank().getCardTag())
					.unlockedBy("has_essence", has(type.getEssence().getEssenceItem()))
					.save(consumer, StringUtil.prefix("fragment_splice/" + type.toString() + "_card"));
			}
			
		});
	}
	
	private void registerStoneSmelting(Consumer<IFinishedRecipe> consumer, IItemProvider input, IItemProvider item, float xp, int time, String name) {
		CookingRecipeBuilder.smelting(Ingredient.of(input), item, xp, time).unlockedBy("has_input", has(input)).save(consumer, StringUtil.prefix("smelting/" + name + "_from_smelting"));
		CookingRecipeBuilder.blasting(Ingredient.of(input), item, xp, time).unlockedBy("has_input", has(input)).save(consumer, StringUtil.prefix("smelting/" + name + "_from_blasting"));
	}
	
	private void registerFoodSmelting(Consumer<IFinishedRecipe> consumer, IItemProvider input, IItemProvider item, float xp, int time, String name) {
		CookingRecipeBuilder.smelting(Ingredient.of(input), item, xp, time).unlockedBy("has_input", has(input)).save(consumer, StringUtil.prefix("smelting/" + name));
		CookingRecipeBuilder.cooking(Ingredient.of(input), item, xp, time, IRecipeSerializer.SMOKING_RECIPE).unlockedBy("has_input", has(input)).save(consumer, StringUtil.prefix("smelting/" + name + "_from_smoking"));
		CookingRecipeBuilder.cooking(Ingredient.of(input), item, xp, time, IRecipeSerializer.CAMPFIRE_COOKING_RECIPE).unlockedBy("has_input", has(input)).save(consumer, StringUtil.prefix("smelting/" + name + "_from_campfire_cooking"));
	}
	
	private void registerCommonCard(Consumer<IFinishedRecipe> consumer, PlantCardItem result, Item crop) {
		final Item essence = result.plantType.getEssence().getEssenceItem();
		final ITag.INamedTag<Item> rankCard = result.plantType.getRank().getCardTag();
	    ShapedRecipeBuilder.shaped(result)
				.pattern("AAA")
				.pattern("ABA")
				.pattern("ACA")
				.define('A', essence)
				.define('B', crop)
				.define('C', rankCard)
				.unlockedBy("has_essence", has(essence))
				.save(consumer, StringUtil.prefix("card/" + result.plantType.toString().toLowerCase() + "_card"));
	}
	
	private void registerCommonCard(Consumer<IFinishedRecipe> consumer, PlantCardItem result, ITag.INamedTag<Item> crop) {
		final Item essence = result.plantType.getEssence().getEssenceItem();
		final ITag.INamedTag<Item> rankCard = result.plantType.getRank().getCardTag();
		if(rankCard != null){
			ShapedRecipeBuilder.shaped(result)
					.pattern("AAA")
					.pattern("ABA")
					.pattern("ACA")
					.define('A', essence)
					.define('B', crop)
					.define('C', rankCard)
					.unlockedBy("has_essence", has(essence))
					.save(consumer, StringUtil.prefix("card/" + result.plantType.toString().toLowerCase() + "_card"));
		}
	}

	private void registerTemplateCard(Consumer<IFinishedRecipe> consumer, IRankType type) {
		final Item rankCard = type.getTemplateCard();
		final ITag.INamedTag<Item> material = type.getMaterial();
		final Item origin = ItemRegister.ORIGIN_ESSENCE.get();
		if (material != null) {
			ShapedRecipeBuilder.shaped(rankCard)
					.pattern("AAA")
					.pattern("ABA")
					.pattern("AAA")
					.define('A', material)
					.define('B', origin)
					.unlockedBy("has_origin", has(origin))
					.save(consumer, StringUtil.prefix("card/template/" + type.getName() + "_card"));
		}
	}
	
}
