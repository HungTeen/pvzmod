package com.hungteen.pvz.data.loot;

import com.hungteen.pvz.common.block.BlockRegister;
import com.hungteen.pvz.common.item.ItemRegister;
import com.hungteen.pvz.common.misc.PVZLoot;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.LootTable.Builder;
import net.minecraft.loot.conditions.RandomChanceWithLooting;
import net.minecraft.loot.functions.LootingEnchantBonus;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.ResourceLocation;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class PVZEntityLootTables implements Consumer<BiConsumer<ResourceLocation, LootTable.Builder>> {

	@Override
	public void accept(BiConsumer<ResourceLocation, Builder> t) {
		t.accept(PVZLoot.NORMAL_ZOMBIE, getZombieLootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
					    .add(ItemLootEntry.lootTableItem(ItemRegister.ZOMBIE_DOLL.get()))
						.when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.001F, 0.01F))
		));
		t.accept(PVZLoot.FLAG_ZOMBIE, getZombieLootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
				        .add(ItemLootEntry.lootTableItem(ItemRegister.ZOMBIE_FLAG.get()))
				        .when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.005F, 0.01F))
		));
		t.accept(PVZLoot.CONEHEAD_ZOMBIE, getZombieLootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
				        .add(ItemLootEntry.lootTableItem(ItemRegister.CONE_HEAD.get()))
				        .when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.005F, 0.01F))
		));
		t.accept(PVZLoot.BUCKETHEAD_ZOMBIE, getZombieLootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
				        .add(ItemLootEntry.lootTableItem(ItemRegister.BUCKET_HEAD.get()))
				        .when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.005F, 0.01F))
		));
		t.accept(PVZLoot.SCREENDOOR_ZOMBIE, getZombieLootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
				        .add(ItemLootEntry.lootTableItem(ItemRegister.SCREEN_DOOR.get()))
				        .when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.005F, 0.01F))
		));
		t.accept(PVZLoot.FOOTBALL_ZOMBIE, getZombieLootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
				        .add(ItemLootEntry.lootTableItem(ItemRegister.FOOTBALL_HELMET.get()))
				        .add(ItemLootEntry.lootTableItem(ItemRegister.FOOTBALL_CHESTPLATE.get()))
				        .add(ItemLootEntry.lootTableItem(ItemRegister.FOOTBALL_LEGGINGS.get()))
				        .add(ItemLootEntry.lootTableItem(ItemRegister.FOOTBALL_BOOTS.get()))
				        .when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.01F, 0.01F))
		));
		t.accept(PVZLoot.GIGA_FOOTBALL_ZOMBIE, getZombieLootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
				        .add(ItemLootEntry.lootTableItem(ItemRegister.GIGA_HELMET.get()))
				        .add(ItemLootEntry.lootTableItem(ItemRegister.GIGA_CHESTPLATE.get()))
				        .add(ItemLootEntry.lootTableItem(ItemRegister.GIGA_LEGGINGS.get()))
				        .add(ItemLootEntry.lootTableItem(ItemRegister.GIGA_BOOTS.get()))
				        .when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.01F, 0.01F))
		));
		t.accept(PVZLoot.NOBLE_ZOMBIE, getZombieLootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
				        .add(ItemLootEntry.lootTableItem(ItemRegister.TIME_KEY_TO_DEEP.get()))
				        .when(RandomChanceWithLooting.randomChanceAndLootingBoost(1F, 0.01F)))
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
						.add(ItemLootEntry.lootTableItem(Items.MYCELIUM))
						.when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.08F, 0.01F))
		));
		t.accept(PVZLoot.COFFIN, getLootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
						.add(ItemLootEntry.lootTableItem(Items.MYCELIUM))
						.when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.08F, 0.01F))
		));
		t.accept(PVZLoot.MOURNER_ZOMBIE, getZombieLootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
				        .add(ItemLootEntry.lootTableItem(ItemRegister.SPORE.get()))
				        .when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.2F, 0.01F))
		));
		t.accept(PVZLoot.BOBSLE_TEAM, getZombieLootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
				        .add(ItemLootEntry.lootTableItem(ItemRegister.BOBSLE_CAR.get()))
				        .when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.05F, 0.01F))
		));
		t.accept(PVZLoot.ZOMBIE_DOLPHIN, getRottenFleshLootTable());
		t.accept(PVZLoot.FOODIE_ZOMBIE, getRottenFleshLootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
				        .add(ItemLootEntry.lootTableItem(ItemRegister.REAL_BRAIN.get()))
				        .when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.2F, 0.01F))
		));
		t.accept(PVZLoot.LAVA_ZOMBIE, getRottenFleshLootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
				        .add(ItemLootEntry.lootTableItem(ItemRegister.PEPPER.get()))
				        .when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.02F, 0.01F))
		));
		t.accept(PVZLoot.PUMPKIN_ZOMBIE, getRottenFleshLootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
				        .add(ItemLootEntry.lootTableItem(ItemRegister.CANDY.get()))
				        .when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.1F, 0.01F))
				).withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
						.add(ItemLootEntry.lootTableItem(ItemRegister.PUMPKIN_ENJOY_CARD.get()))
						.when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.005F, 0.01F))
		));
		t.accept(PVZLoot.TRICK_ZOMBIE, getLootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
				        .add(ItemLootEntry.lootTableItem(ItemRegister.CANDY.get()))
				        .when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.1F, 0.01F))
		));
		t.accept(PVZLoot.JACK_IN_BOX_ZOMBIE, getLootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
				        .add(ItemLootEntry.lootTableItem(ItemRegister.JACK_BOX.get()))
				        .when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.01F, 0.01F))
		));
		t.accept(PVZLoot.BALLOON_ZOMBIE, getLootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
				        .add(ItemLootEntry.lootTableItem(ItemRegister.BALLOON.get()))
				        .when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.005F, 0.01F))
		));
		t.accept(PVZLoot.DIGGER_ZOMBIE, getLootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
				        .add(ItemLootEntry.lootTableItem(ItemRegister.ORIGIN_PICKAXE.get()))
				        .add(ItemLootEntry.lootTableItem(Items.DIAMOND_PICKAXE))
				        .when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.005F, 0.01F))
		));
		t.accept(PVZLoot.RA_ZOMBIE, getLootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
				        .add(ItemLootEntry.lootTableItem(ItemRegister.RESOURCE_COLLECTOR.get()))
				        .when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.001F, 0.01F))
		));
		t.accept(PVZLoot.BUNGEE_ZOMBIE, getLootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
				        .add(ItemLootEntry.lootTableItem(ItemRegister.TARGET_ARROW.get()))
				        .when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.01F, 0.01F))
		));
		t.accept(PVZLoot.LADDER_ZOMBIE, getLootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
				        .add(ItemLootEntry.lootTableItem(BlockRegister.STEEL_LADDER.get()))
				        .when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.1F, 0.01F))
		));
		t.accept(PVZLoot.GARGANTUAR, getLootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
				        .add(ItemLootEntry.lootTableItem(ItemRegister.POLE.get()))
				        .add(ItemLootEntry.lootTableItem(ItemRegister.ZOMBIE_DOLL.get()))
				        .add(ItemLootEntry.lootTableItem(ItemRegister.WARNING_SIGN.get()))
				        .when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.01F, 0.01F))
		));
		t.accept(PVZLoot.GIGA_GARGANTUAR, getLootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
				        .add(ItemLootEntry.lootTableItem(ItemRegister.POLE.get()))
				        .add(ItemLootEntry.lootTableItem(ItemRegister.ZOMBIE_DOLL.get()))
				        .add(ItemLootEntry.lootTableItem(ItemRegister.WARNING_SIGN.get()))
				        .when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.08F, 0.01F))
		));
		t.accept(PVZLoot.EDGAR_090505, getLootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
				        .add(ItemLootEntry.lootTableItem(BlockRegister.SILVER_SUNFLOWER_TROPHY.get()))
				        .when(RandomChanceWithLooting.randomChanceAndLootingBoost(1F, 0.01F))
				).withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
						.add(ItemLootEntry.lootTableItem(ItemRegister.ZOMBIE_ON_YOUR_LAWN.get()))
						.when(RandomChanceWithLooting.randomChanceAndLootingBoost(1F, 0.01F))
				).withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
						.add(ItemLootEntry.lootTableItem(ItemRegister.TIME_SOURCE.get()))
						.when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.6F, 0.01F))
		));
		t.accept(PVZLoot.EDGAR_090517, getLootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
						.add(ItemLootEntry.lootTableItem(BlockRegister.GOLD_SUNFLOWER_TROPHY.get()))
						.when(RandomChanceWithLooting.randomChanceAndLootingBoost(1F, 0.01F))
				).withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
						.add(ItemLootEntry.lootTableItem(ItemRegister.ZOMBIE_ON_YOUR_LAWN.get()))
						.when(RandomChanceWithLooting.randomChanceAndLootingBoost(1F, 0.01F))
				).withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
						.add(ItemLootEntry.lootTableItem(ItemRegister.TIME_SOURCE.get()))
						.when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.6F, 0.01F))
				));
		t.accept(PVZLoot.PEASHOOTER_ZOMBIE, getLootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
				        .add(ItemLootEntry.lootTableItem(ItemRegister.PEA_SHOOTER_ENJOY_CARD.get()))
				        .when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.005F, 0.01F))
		));
		t.accept(PVZLoot.WALLNUT_ZOMBIE, getLootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
				        .add(ItemLootEntry.lootTableItem(ItemRegister.WALL_NUT_ENJOY_CARD.get()))
				        .when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.005F, 0.01F))
		));
		t.accept(PVZLoot.GATLINGPEA_ZOMBIE, getLootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
				        .add(ItemLootEntry.lootTableItem(ItemRegister.GATLING_PEA_ENJOY_CARD.get()))
				        .when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.004F, 0.01F))
		));
		t.accept(PVZLoot.TALLNUT_ZOMBIE, getLootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
				        .add(ItemLootEntry.lootTableItem(ItemRegister.TALL_NUT_ENJOY_CARD.get()))
				        .when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.005F, 0.01F))
		));
		t.accept(PVZLoot.SQUASH_ZOMBIE, getLootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
				        .add(ItemLootEntry.lootTableItem(ItemRegister.SQUASH_ENJOY_CARD.get()))
				        .when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.008F, 0.01F))
		));
		t.accept(PVZLoot.JALAPENO_ZOMBIE, getLootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
				        .add(ItemLootEntry.lootTableItem(ItemRegister.JALAPENO_ENJOY_CARD.get()))
				        .when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.005F, 0.01F))
		));
	}
	
	private static LootTable.Builder getLootTable() {
		return LootTable.lootTable();
	}
	
	private static LootTable.Builder getRottenFleshLootTable() {
		return LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
				.add(ItemLootEntry.lootTableItem(Items.ROTTEN_FLESH))
				.apply(SetCount.setCount(RandomValueRange.between(0.0F, 2.0F)))
				.apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F)))
				.when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.15F, 0.01F)));
	}
	
	private static LootTable.Builder getZombieLootTable() {
		return getRottenFleshLootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
				.add(ItemLootEntry.lootTableItem(ItemRegister.CORN_SEEDS.get()))
				.add(ItemLootEntry.lootTableItem(Items.SUNFLOWER))
				.add(ItemLootEntry.lootTableItem(ItemRegister.NUT.get()))
				.when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.01F, 0.01F)));
	}

}
