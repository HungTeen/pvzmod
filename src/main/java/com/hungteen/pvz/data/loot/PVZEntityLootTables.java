package com.hungteen.pvz.data.loot;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.hungteen.pvz.register.BlockRegister;
import com.hungteen.pvz.register.ItemRegister;

import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.ConstantRange;
import net.minecraft.world.storage.loot.ItemLootEntry;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTable.Builder;
import net.minecraft.world.storage.loot.conditions.RandomChanceWithLooting;

public class PVZEntityLootTables implements Consumer<BiConsumer<ResourceLocation, LootTable.Builder>> {

	@Override
	public void accept(BiConsumer<ResourceLocation, Builder> t) {
		t.accept(PVZLoot.NORMAL_ZOMBIE, getZombieLootTable());
		t.accept(PVZLoot.FLAG_ZOMBIE, getZombieLootTable().addLootPool(LootPool.builder().rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(ItemRegister.ZOMBIE_FLAG.get()))
				.acceptCondition(RandomChanceWithLooting.builder(0.01F, 0.01F))
				));
		t.accept(PVZLoot.CONEHEAD_ZOMBIE, getZombieLootTable().addLootPool(LootPool.builder().rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(ItemRegister.CONE_HEAD.get()))
				.acceptCondition(RandomChanceWithLooting.builder(0.01F, 0.01F))
				));
		t.accept(PVZLoot.BUCKETHEAD_ZOMBIE, getZombieLootTable().addLootPool(LootPool.builder().rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(ItemRegister.BUCKET_HEAD.get()))
				.acceptCondition(RandomChanceWithLooting.builder(0.01F, 0.01F))
				));
		t.accept(PVZLoot.SCREENDOOR_ZOMBIE, getZombieLootTable().addLootPool(LootPool.builder().rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(ItemRegister.SCREEN_DOOR.get()))
				.acceptCondition(RandomChanceWithLooting.builder(0.01F, 0.01F))
				));
		t.accept(PVZLoot.FOOTBALL_ZOMBIE, getZombieLootTable().addLootPool(LootPool.builder().rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(ItemRegister.FOOTBALL_HELMET.get()))
				.addEntry(ItemLootEntry.builder(ItemRegister.FOOTBALL_CHESTPLATE.get()))
				.addEntry(ItemLootEntry.builder(ItemRegister.FOOTBALL_LEGGINGS.get()))
				.addEntry(ItemLootEntry.builder(ItemRegister.FOOTBALL_BOOTS.get()))
				.acceptCondition(RandomChanceWithLooting.builder(0.03F, 0.01F))
				));
		t.accept(PVZLoot.GIGA_FOOTBALL_ZOMBIE, getZombieLootTable().addLootPool(LootPool.builder().rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(ItemRegister.GIGA_HELMET.get()))
				.addEntry(ItemLootEntry.builder(ItemRegister.GIGA_CHESTPLATE.get()))
				.addEntry(ItemLootEntry.builder(ItemRegister.GIGA_LEGGINGS.get()))
				.addEntry(ItemLootEntry.builder(ItemRegister.GIGA_BOOTS.get()))
				.acceptCondition(RandomChanceWithLooting.builder(0.03F, 0.01F))
				));
		t.accept(PVZLoot.NOBLE_ZOMBIE, getZombieLootTable().addLootPool(LootPool.builder().rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(ItemRegister.TIME_KEY_TO_DEEP.get()))
				.acceptCondition(RandomChanceWithLooting.builder(1F, 0.01F))
				));
		t.accept(PVZLoot.MOURNER_ZOMBIE, getZombieLootTable().addLootPool(LootPool.builder().rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(ItemRegister.SPORE.get()))
				.acceptCondition(RandomChanceWithLooting.builder(0.2F, 0.01F))
				));
		t.accept(PVZLoot.BOBSLE_TEAM, getZombieLootTable().addLootPool(LootPool.builder().rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(ItemRegister.BOBSLE_CAR.get()))
				.acceptCondition(RandomChanceWithLooting.builder(0.02F, 0.01F))
				));
		t.accept(PVZLoot.ZOMBIE_DOLPHIN, getRottenFleshLootTable());
		t.accept(PVZLoot.FOODIE_ZOMBIE, getRottenFleshLootTable().addLootPool(LootPool.builder().rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(ItemRegister.REAL_BRAIN.get()))
				.acceptCondition(RandomChanceWithLooting.builder(0.1F, 0.01F))
				));
		t.accept(PVZLoot.LAVA_ZOMBIE, getRottenFleshLootTable().addLootPool(LootPool.builder().rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(ItemRegister.PEPPER.get()))
				.acceptCondition(RandomChanceWithLooting.builder(0.05F, 0.01F))
				));
		t.accept(PVZLoot.PUMPKIN_ZOMBIE, getRottenFleshLootTable().addLootPool(LootPool.builder().rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(ItemRegister.CANDY.get()))
				.acceptCondition(RandomChanceWithLooting.builder(0.1F, 0.01F))
				).addLootPool(LootPool.builder().rolls(ConstantRange.of(1))
						.addEntry(ItemLootEntry.builder(ItemRegister.PUMPKIN_ENJOY_CARD.get()))
						.acceptCondition(RandomChanceWithLooting.builder(0.01F, 0.01F))
						));
		t.accept(PVZLoot.TRICK_ZOMBIE, getLootTable().addLootPool(LootPool.builder().rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(ItemRegister.CANDY.get()))
				.acceptCondition(RandomChanceWithLooting.builder(0.1F, 0.01F))
				));
		t.accept(PVZLoot.JACK_IN_BOX_ZOMBIE, getLootTable().addLootPool(LootPool.builder().rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(ItemRegister.JACK_BOX.get()))
				.acceptCondition(RandomChanceWithLooting.builder(0.02F, 0.01F))
				));
		t.accept(PVZLoot.BALLOON_ZOMBIE, getLootTable().addLootPool(LootPool.builder().rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(ItemRegister.BALLOON.get()))
				.acceptCondition(RandomChanceWithLooting.builder(0.01F, 0.01F))
				));
		t.accept(PVZLoot.DIGGER_ZOMBIE, getLootTable().addLootPool(LootPool.builder().rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(ItemRegister.STEEL_PICKAXE.get()))
				.addEntry(ItemLootEntry.builder(Items.DIAMOND_PICKAXE))
				.acceptCondition(RandomChanceWithLooting.builder(0.03F, 0.01F))
				));
		t.accept(PVZLoot.RA_ZOMBIE, getLootTable().addLootPool(LootPool.builder().rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(ItemRegister.SUN_COLLECTOR.get()))
				.acceptCondition(RandomChanceWithLooting.builder(0.01F, 0.01F))
				));
		t.accept(PVZLoot.BUNGEE_ZOMBIE, getLootTable().addLootPool(LootPool.builder().rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(ItemRegister.TARGET_ARROW.get()))
				.acceptCondition(RandomChanceWithLooting.builder(0.02F, 0.01F))
				));
		t.accept(PVZLoot.LADDER_ZOMBIE, getLootTable().addLootPool(LootPool.builder().rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(BlockRegister.STEEL_LADDER.get()))
				.addEntry(ItemLootEntry.builder(ItemRegister.STEEL_INGOT.get()))
				.acceptCondition(RandomChanceWithLooting.builder(0.03F, 0.01F))
				));
		t.accept(PVZLoot.GARGANTUAR, getLootTable().addLootPool(LootPool.builder().rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(ItemRegister.POLE.get()))
				.addEntry(ItemLootEntry.builder(ItemRegister.ZOMBIE_DOLL.get()))
				.addEntry(ItemLootEntry.builder(ItemRegister.WARNING_SIGN.get()))
				.acceptCondition(RandomChanceWithLooting.builder(0.04F, 0.01F))
				));
		t.accept(PVZLoot.SAD_GARGANTUAR, getLootTable().addLootPool(LootPool.builder().rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(ItemRegister.POLE.get()))
				.addEntry(ItemLootEntry.builder(ItemRegister.ZOMBIE_DOLL.get()))
				.addEntry(ItemLootEntry.builder(ItemRegister.WARNING_SIGN.get()))
				.acceptCondition(RandomChanceWithLooting.builder(0.1F, 0.01F))
				));
		t.accept(PVZLoot.ZOMBOSS, getLootTable().addLootPool(LootPool.builder().rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(BlockRegister.SILVER_SUNFLOWER_TROPHY.get()))
				.acceptCondition(RandomChanceWithLooting.builder(1F, 0.01F))
				).addLootPool(LootPool.builder().rolls(ConstantRange.of(1))
						.addEntry(ItemLootEntry.builder(ItemRegister.ZOMBIE_ON_YOUR_LAWN.get()))
						.acceptCondition(RandomChanceWithLooting.builder(1F, 0.01F))
						).addLootPool(LootPool.builder().rolls(ConstantRange.of(1))
						.addEntry(ItemLootEntry.builder(ItemRegister.TIME_SOURCE.get()))
						.acceptCondition(RandomChanceWithLooting.builder(0.6F, 0.01F))
						));
		t.accept(PVZLoot.PEASHOOTER_ZOMBIE, getLootTable().addLootPool(LootPool.builder().rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(ItemRegister.PEA_SHOOTER_ENJOY_CARD.get()))
				.acceptCondition(RandomChanceWithLooting.builder(0.01F, 0.01F))
				));
		t.accept(PVZLoot.WALLNUT_ZOMBIE, getLootTable().addLootPool(LootPool.builder().rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(ItemRegister.WALL_NUT_ENJOY_CARD.get()))
				.acceptCondition(RandomChanceWithLooting.builder(0.01F, 0.01F))
				));
		t.accept(PVZLoot.GATLINGPEA_ZOMBIE, getLootTable().addLootPool(LootPool.builder().rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(ItemRegister.GATLING_PEA_ENJOY_CARD.get()))
				.acceptCondition(RandomChanceWithLooting.builder(0.01F, 0.01F))
				));
		t.accept(PVZLoot.TALLNUT_ZOMBIE, getLootTable().addLootPool(LootPool.builder().rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(ItemRegister.TALL_NUT_ENJOY_CARD.get()))
				.acceptCondition(RandomChanceWithLooting.builder(0.01F, 0.01F))
				));
		t.accept(PVZLoot.SQUASH_ZOMBIE, getLootTable().addLootPool(LootPool.builder().rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(ItemRegister.SQUASH_ENJOY_CARD.get()))
				.acceptCondition(RandomChanceWithLooting.builder(0.015F, 0.01F))
				));
		t.accept(PVZLoot.JALAPENO_ZOMBIE, getLootTable().addLootPool(LootPool.builder().rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(ItemRegister.JALAPENO_ENJOY_CARD.get()))
				.acceptCondition(RandomChanceWithLooting.builder(0.01F, 0.01F))
				));
	}
	
	private static LootTable.Builder getLootTable() {
		return LootTable.builder();
	}
	
	private static LootTable.Builder getRottenFleshLootTable() {
		return LootTable.builder()
				.addLootPool(LootPool.builder().rolls(ConstantRange.of(1))
						.addEntry(ItemLootEntry.builder(Items.ROTTEN_FLESH)
						.acceptCondition(RandomChanceWithLooting.builder(0.125F, 0.01F))));
	}
	
	private static LootTable.Builder getZombieLootTable() {
		return LootTable.builder()
		.addLootPool(LootPool.builder().rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(Items.ROTTEN_FLESH)
				.acceptCondition(RandomChanceWithLooting.builder(0.125F, 0.01F))))
		.addLootPool(LootPool.builder().rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(ItemRegister.ORIGIN_ESSENCE.get()))
				.addEntry(ItemLootEntry.builder(ItemRegister.CORN_SEEDS.get()))
				.addEntry(ItemLootEntry.builder(Items.SUNFLOWER))
				.addEntry(ItemLootEntry.builder(ItemRegister.PEA.get()))
				.addEntry(ItemLootEntry.builder(ItemRegister.NUT.get()))
				.acceptCondition(RandomChanceWithLooting.builder(0.01F, 0.01F)));
	}

}
