package com.hungteen.pvz.data.loot;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.hungteen.pvz.register.ItemRegister;

import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.ConstantRange;
import net.minecraft.world.storage.loot.EmptyLootEntry;
import net.minecraft.world.storage.loot.ItemLootEntry;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.LootTable.Builder;
import net.minecraft.world.storage.loot.functions.EnchantRandomly;
import net.minecraft.world.storage.loot.functions.SetCount;

public class PVZChestLootTables implements Consumer<BiConsumer<ResourceLocation, LootTable.Builder>> {

	@Override
	public void accept(BiConsumer<ResourceLocation, Builder> t) {
		t.accept(PVZLoot.DAVE_VILLA_CHEST, LootTable.builder()
				.addLootPool(LootPool.builder().rolls(RandomValueRange.of(3.0F, 5.0F))
						.addEntry(ItemLootEntry.builder(Items.DIAMOND).weight(5).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F))))
						.addEntry(ItemLootEntry.builder(Items.IRON_INGOT).weight(15).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 5.0F))))
						.addEntry(ItemLootEntry.builder(Items.GOLD_INGOT).weight(15).acceptFunction(SetCount.builder(RandomValueRange.of(2.0F, 7.0F))))
						.addEntry(ItemLootEntry.builder(Items.EMERALD).weight(15).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F))))
						.addEntry(ItemLootEntry.builder(Items.WHEAT).weight(20).acceptFunction(SetCount.builder(RandomValueRange.of(2.0F, 6.0F))))
						.addEntry(ItemLootEntry.builder(ItemRegister.PEA.get()).weight(20).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 10.0F))))
						.addEntry(ItemLootEntry.builder(Items.PORKCHOP).weight(15).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 5.0F))))
						.addEntry(ItemLootEntry.builder(ItemRegister.PEA_SHOOTER_ENJOY_CARD.get()).weight(10))
						.addEntry(ItemLootEntry.builder(ItemRegister.SUN_FLOWER_ENJOY_CARD.get()).weight(10))
						.addEntry(ItemLootEntry.builder(ItemRegister.CHERRY_BOMB_ENJOY_CARD.get()).weight(5))
						.addEntry(ItemLootEntry.builder(ItemRegister.WALL_NUT_ENJOY_CARD.get()).weight(10))
						.addEntry(ItemLootEntry.builder(ItemRegister.POTATO_MINE_ENJOY_CARD.get()).weight(8))
						.addEntry(ItemLootEntry.builder(Items.BOOK).weight(20).acceptFunction(EnchantRandomly.func_215900_c()))
						.addEntry(ItemLootEntry.builder(Items.GOLDEN_APPLE).weight(20))
						.addEntry(ItemLootEntry.builder(Items.ENCHANTED_GOLDEN_APPLE).weight(2))
						.addEntry(EmptyLootEntry.func_216167_a().weight(15)))
				.addLootPool(LootPool.builder().rolls(ConstantRange.of(3))
						.addEntry(ItemLootEntry.builder(Items.BOOK).weight(10).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 4.0F))))
						.addEntry(ItemLootEntry.builder(Items.ROTTEN_FLESH).weight(10).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 8.0F))))
						.addEntry(ItemLootEntry.builder(Items.STRING).weight(10).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 8.0F))))
				));
		t.accept(PVZLoot.BUCKET_HOUSE_CHEST, LootTable.builder()
				.addLootPool(LootPool.builder().rolls(RandomValueRange.of(2.0F, 4.0F))
						.addEntry(ItemLootEntry.builder(Items.DIAMOND).weight(5).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F))))
						.addEntry(ItemLootEntry.builder(Items.IRON_INGOT).weight(15).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 5.0F))))
						.addEntry(ItemLootEntry.builder(Items.GOLD_INGOT).weight(15).acceptFunction(SetCount.builder(RandomValueRange.of(2.0F, 7.0F))))
						.addEntry(ItemLootEntry.builder(Items.EMERALD).weight(15).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F))))
						.addEntry(ItemLootEntry.builder(Items.BONE).weight(15).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 6.0F))))
						.addEntry(ItemLootEntry.builder(Items.SPIDER_EYE).weight(15).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F))))
						.addEntry(ItemLootEntry.builder(Items.ROTTEN_FLESH).weight(15).acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 7.0F))))
						.addEntry(ItemLootEntry.builder(ItemRegister.PEA_SHOOTER_ENJOY_CARD.get()).weight(6).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))
						.addEntry(ItemLootEntry.builder(ItemRegister.SUN_FLOWER_ENJOY_CARD.get()).weight(6).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))
						.addEntry(ItemLootEntry.builder(ItemRegister.CHERRY_BOMB_ENJOY_CARD.get()).weight(21).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))
						.addEntry(ItemLootEntry.builder(ItemRegister.WALL_NUT_ENJOY_CARD.get()).weight(6).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))
						.addEntry(ItemLootEntry.builder(ItemRegister.POTATO_MINE_ENJOY_CARD.get()).weight(6).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))
						.addEntry(ItemLootEntry.builder(ItemRegister.SNOW_PEA_ENJOY_CARD.get()).weight(6).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))
						.addEntry(ItemLootEntry.builder(ItemRegister.CHOMPER_ENJOY_CARD.get()).weight(12).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))
						.addEntry(ItemLootEntry.builder(ItemRegister.REPEATER_ENJOY_CARD.get()).weight(6).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))
						.addEntry(ItemLootEntry.builder(Items.BOOK).weight(20).acceptFunction(EnchantRandomly.func_215900_c()))
						.addEntry(ItemLootEntry.builder(Items.GOLDEN_APPLE).weight(20))
						.addEntry(ItemLootEntry.builder(Items.ENCHANTED_GOLDEN_APPLE).weight(2))
						.addEntry(EmptyLootEntry.func_216167_a().weight(15)))
				.addLootPool(LootPool.builder().rolls(ConstantRange.of(4))
						.addEntry(ItemLootEntry.builder(Items.BONE).weight(10).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 8.0F))))
						.addEntry(ItemLootEntry.builder(Items.GUNPOWDER).weight(10).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 8.0F))))
						.addEntry(ItemLootEntry.builder(Items.ROTTEN_FLESH).weight(10).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 8.0F))))
						.addEntry(ItemLootEntry.builder(Items.STRING).weight(10).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 8.0F))))
				));
		t.accept(PVZLoot.DOLPHIN_HOUSE_CHEST, LootTable.builder()
				.addLootPool(LootPool.builder().rolls(RandomValueRange.of(2.0F, 4.0F))
						.addEntry(ItemLootEntry.builder(Items.DIAMOND).weight(5).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F))))
						.addEntry(ItemLootEntry.builder(Items.IRON_INGOT).weight(15).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 5.0F))))
						.addEntry(ItemLootEntry.builder(Items.GOLD_INGOT).weight(15).acceptFunction(SetCount.builder(RandomValueRange.of(2.0F, 7.0F))))
						.addEntry(ItemLootEntry.builder(Items.EMERALD).weight(15).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F))))
						.addEntry(ItemLootEntry.builder(Items.BONE).weight(15).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 6.0F))))
						.addEntry(ItemLootEntry.builder(Items.SPIDER_EYE).weight(15).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F))))
						.addEntry(ItemLootEntry.builder(Items.ROTTEN_FLESH).weight(15).acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 7.0F))))
						.addEntry(ItemLootEntry.builder(ItemRegister.LILY_PAD_ENJOY_CARD.get()).weight(6).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))
						.addEntry(ItemLootEntry.builder(ItemRegister.SQUASH_ENJOY_CARD.get()).weight(21).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))
						.addEntry(ItemLootEntry.builder(ItemRegister.THREE_PEATER_ENJOY_CARD.get()).weight(6).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))
						.addEntry(ItemLootEntry.builder(ItemRegister.TANGLE_KELP_ENJOY_CARD.get()).weight(6).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))
						.addEntry(ItemLootEntry.builder(ItemRegister.JALAPENO_ENJOY_CARD.get()).weight(12).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))
						.addEntry(ItemLootEntry.builder(ItemRegister.SPIKE_WEED_ENJOY_CARD.get()).weight(6).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))
						.addEntry(ItemLootEntry.builder(ItemRegister.TORCH_WOOD_ENJOY_CARD.get()).weight(6).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))
						.addEntry(ItemLootEntry.builder(ItemRegister.TALL_NUT_ENJOY_CARD.get()).weight(6).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))
						.addEntry(ItemLootEntry.builder(Items.BOOK).weight(20).acceptFunction(EnchantRandomly.func_215900_c()))
						.addEntry(ItemLootEntry.builder(Items.GOLDEN_APPLE).weight(20))
						.addEntry(ItemLootEntry.builder(Items.ENCHANTED_GOLDEN_APPLE).weight(2))
						.addEntry(EmptyLootEntry.func_216167_a().weight(15)))
				.addLootPool(LootPool.builder().rolls(ConstantRange.of(4))
						.addEntry(ItemLootEntry.builder(Items.BONE).weight(10).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 8.0F))))
						.addEntry(ItemLootEntry.builder(Items.GUNPOWDER).weight(10).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 8.0F))))
						.addEntry(ItemLootEntry.builder(Items.ROTTEN_FLESH).weight(10).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 8.0F))))
						.addEntry(ItemLootEntry.builder(Items.STRING).weight(10).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 8.0F))))
				));
		t.accept(PVZLoot.GRAVE_YARD_CHEST, LootTable.builder()
				.addLootPool(LootPool.builder().rolls(RandomValueRange.of(2.0F, 4.0F))
						.addEntry(ItemLootEntry.builder(Items.DIAMOND).weight(5).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F))))
						.addEntry(ItemLootEntry.builder(Items.IRON_INGOT).weight(15).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 5.0F))))
						.addEntry(ItemLootEntry.builder(Items.GOLD_INGOT).weight(20).acceptFunction(SetCount.builder(RandomValueRange.of(2.0F, 7.0F))))
						.addEntry(ItemLootEntry.builder(Items.EMERALD).weight(15).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F))))
						.addEntry(ItemLootEntry.builder(Items.BONE).weight(15).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 6.0F))))
						.addEntry(ItemLootEntry.builder(Items.SPIDER_EYE).weight(15).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F))))
						.addEntry(ItemLootEntry.builder(Items.ROTTEN_FLESH).weight(15).acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 7.0F))))
						.addEntry(ItemLootEntry.builder(ItemRegister.PUFF_SHROOM_ENJOY_CARD.get()).weight(15).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))
						.addEntry(ItemLootEntry.builder(ItemRegister.SUN_SHROOM_ENJOY_CARD.get()).weight(15).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))
						.addEntry(ItemLootEntry.builder(ItemRegister.FUME_SHROOM_ENJOY_CARD.get()).weight(20).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))
						.addEntry(ItemLootEntry.builder(ItemRegister.GRAVE_BUSTER_ENJOY_CARD.get()).weight(6).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))
						.addEntry(ItemLootEntry.builder(ItemRegister.HYPNO_SHROOM_ENJOY_CARD.get()).weight(6).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))
						.addEntry(ItemLootEntry.builder(ItemRegister.SCAREDY_SHROOM_ENJOY_CARD.get()).weight(15).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))
						.addEntry(ItemLootEntry.builder(ItemRegister.ICE_SHROOM_ENJOY_CARD.get()).weight(6).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))
						.addEntry(ItemLootEntry.builder(ItemRegister.DOOM_SHROOM_ENJOY_CARD.get()).weight(20).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))
						.addEntry(ItemLootEntry.builder(Items.WITHER_ROSE).weight(2).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))
						.addEntry(ItemLootEntry.builder(Items.BOOK).weight(20).acceptFunction(EnchantRandomly.func_215900_c()))
						.addEntry(ItemLootEntry.builder(Items.GOLDEN_APPLE).weight(20))
						.addEntry(ItemLootEntry.builder(Items.ENCHANTED_GOLDEN_APPLE).weight(2))
						.addEntry(EmptyLootEntry.func_216167_a().weight(15)))
				.addLootPool(LootPool.builder().rolls(ConstantRange.of(4))
						.addEntry(ItemLootEntry.builder(Items.BONE).weight(20).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 8.0F))))
						.addEntry(ItemLootEntry.builder(Items.ROTTEN_FLESH).weight(10).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 8.0F))))
						.addEntry(ItemLootEntry.builder(Items.STRING).weight(10).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 8.0F))))
						.addEntry(ItemLootEntry.builder(Blocks.GRAVEL).weight(10).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 8.0F))))
				));
		t.accept(PVZLoot.YETI_HOUSE_CHEST, LootTable.builder()
				.addLootPool(LootPool.builder().rolls(RandomValueRange.of(2.0F, 4.0F))
						.addEntry(ItemLootEntry.builder(Items.DIAMOND).weight(5).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F))))
						.addEntry(ItemLootEntry.builder(Items.IRON_INGOT).weight(15).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 5.0F))))
						.addEntry(ItemLootEntry.builder(Items.GOLD_INGOT).weight(15).acceptFunction(SetCount.builder(RandomValueRange.of(2.0F, 7.0F))))
						.addEntry(ItemLootEntry.builder(Items.BLUE_ICE).weight(15).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))
						.addEntry(ItemLootEntry.builder(Items.BONE).weight(15).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 6.0F))))
						.addEntry(ItemLootEntry.builder(Items.SPIDER_EYE).weight(15).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F))))
						.addEntry(ItemLootEntry.builder(Items.ROTTEN_FLESH).weight(15).acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 7.0F))))
						.addEntry(ItemLootEntry.builder(ItemRegister.SEA_SHROOM_ENJOY_CARD.get()).weight(15).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))
						.addEntry(ItemLootEntry.builder(ItemRegister.PLANTERN_ENJOY_CARD.get()).weight(10).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))
						.addEntry(ItemLootEntry.builder(ItemRegister.CACTUS_ENJOY_CARD.get()).weight(6).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))
						.addEntry(ItemLootEntry.builder(ItemRegister.BLOVER_ENJOY_CARD.get()).weight(10).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))
						.addEntry(ItemLootEntry.builder(ItemRegister.SPLIT_PEA_ENJOY_CARD.get()).weight(6).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))
						.addEntry(ItemLootEntry.builder(ItemRegister.STAR_FRUIT_ENJOY_CARD.get()).weight(15).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))
						.addEntry(ItemLootEntry.builder(ItemRegister.PUMPKIN_ENJOY_CARD.get()).weight(6).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))
						.addEntry(ItemLootEntry.builder(ItemRegister.MAGNET_SHROOM_ENJOY_CARD.get()).weight(20).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))
						.addEntry(ItemLootEntry.builder(Items.WITHER_ROSE).weight(2).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))
						.addEntry(ItemLootEntry.builder(Items.BOOK).weight(20).acceptFunction(EnchantRandomly.func_215900_c()))
						.addEntry(ItemLootEntry.builder(Items.GOLDEN_APPLE).weight(20))
						.addEntry(ItemLootEntry.builder(Items.ENCHANTED_GOLDEN_APPLE).weight(2))
						.addEntry(EmptyLootEntry.func_216167_a().weight(15)))
				.addLootPool(LootPool.builder().rolls(ConstantRange.of(4))
						.addEntry(ItemLootEntry.builder(Items.BONE).weight(20).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 8.0F))))
						.addEntry(ItemLootEntry.builder(Items.ROTTEN_FLESH).weight(10).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 8.0F))))
						.addEntry(ItemLootEntry.builder(Items.STRING).weight(10).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 8.0F))))
						.addEntry(ItemLootEntry.builder(Blocks.GRAVEL).weight(10).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 8.0F))))
				));
	}

}
