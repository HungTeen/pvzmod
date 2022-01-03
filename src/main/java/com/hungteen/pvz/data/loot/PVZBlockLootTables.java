package com.hungteen.pvz.data.loot;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.block.BlockRegister;
import com.hungteen.pvz.common.item.ItemRegister;
import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropsBlock;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTable.Builder;
import net.minecraft.loot.conditions.BlockStateProperty;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.MatchTool;
import net.minecraft.loot.conditions.TableBonus;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PVZBlockLootTables extends BlockLootTables {

	private final Set<Block> knownBlocks = new HashSet<>();
	private ILootCondition.IBuilder tmpBuilder;
	private static final ILootCondition.IBuilder HAS_SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item()
			.hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1))));
//	private static final ILootCondition.IBuilder HAS_NO_SILK_TOUCH = HAS_SILK_TOUCH.invert();
	private static final ILootCondition.IBuilder HAS_SHEARS = MatchTool
			.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS));
	private static final ILootCondition.IBuilder HAS_SHEARS_OR_SILK_TOUCH = HAS_SHEARS.or(HAS_SILK_TOUCH);
	private static final ILootCondition.IBuilder HAS_NO_SHEARS_OR_SILK_TOUCH = HAS_SHEARS_OR_SILK_TOUCH.invert();
	private static final float[] NORMAL_LEAVES_SAPLING_CHANCES = new float[] { 0.05F, 0.0625F, 0.083333336F, 0.1F };
//	private static final float[] JUNGLE_LEAVES_SAPLING_CHANGES = new float[]{0.025F, 0.027777778F, 0.03125F, 0.041666668F, 0.1F};

	@Override
	protected void addTables() {
		final Set<Block> noLootBlocks = new HashSet<>(Arrays.asList(
				BlockRegister.LILY_PAD.get(), BlockRegister.FLOWER_POT.get(),
				BlockRegister.SLOT_MACHINE.get()
			    ));
		// drop item like coal ore
		Arrays.asList(BlockRegister.ORIGIN_ORE.get(), BlockRegister.APPEASE_ORE.get(), BlockRegister.LIGHT_ORE.get(),
				BlockRegister.EXPLOSION_ORE.get(), BlockRegister.DEFENCE_ORE.get(), BlockRegister.ICE_ORE.get(),
				BlockRegister.ENFORCE_ORE.get(), BlockRegister.TOXIC_ORE.get(), BlockRegister.ASSIST_ORE.get(),
				BlockRegister.MAGIC_ORE.get(), BlockRegister.FLAME_ORE.get(), BlockRegister.SPEAR_ORE.get(),
				BlockRegister.ARMA_ORE.get(), BlockRegister.ELECTRIC_ORE.get(), BlockRegister.SHADOW_ORE.get())
				.forEach((object) -> {
					this.add(object, (block) -> createOreDrop(block, object.essence.getEssenceItem()));
				});
		// crop
		this.tmpBuilder = getAgeBuilder(BlockRegister.CABBAGE.get(), 3);
		this.add(BlockRegister.CABBAGE.get(),
				createCropDrops(BlockRegister.CABBAGE.get(), ItemRegister.CABBAGE.get(), this.tmpBuilder));
		this.tmpBuilder = getAgeBuilder(BlockRegister.CORN.get(), 7);
		this.add(BlockRegister.CORN.get(),
				createCropDrops(BlockRegister.CORN.get(), ItemRegister.CORN.get(), this.tmpBuilder));

		// leaves
		this.add(BlockRegister.NUT_LEAVES.get(), (p_218506_0_) -> {
			return createLeavesDrops(p_218506_0_, BlockRegister.NUT_SAPLING.get(), ItemRegister.NUT.get(), NORMAL_LEAVES_SAPLING_CHANCES);
		});

		// misc
		this.dropOther(BlockRegister.GOLD_TILE1.get(), Blocks.GOLD_BLOCK);
		this.dropOther(BlockRegister.GOLD_TILE2.get(), Blocks.GOLD_BLOCK);
		this.dropOther(BlockRegister.GOLD_TILE3.get(), Blocks.GOLD_BLOCK);

		// other blocks are drop itself
		ForgeRegistries.BLOCKS.forEach(block -> {
			if (block.getRegistryName().getNamespace().equals(PVZMod.MOD_ID) && !noLootBlocks.contains(block) && !this.knownBlocks.contains(block)) {
				this.dropSelf(block);
			}
		});
	}

	@Override
	protected Iterable<Block> getKnownBlocks() {
		return this.knownBlocks;
	}

	@Override
	protected void add(Block p_218507_1_, Builder p_218507_2_) {
		super.add(p_218507_1_, p_218507_2_);
		this.knownBlocks.add(p_218507_1_);
	}

	private static Builder createCropDrops(Block block, Item crops, ILootCondition.IBuilder bb) {
		return createCropDrops(block, crops, block.asItem(), bb);
	}

	private ILootCondition.IBuilder getAgeBuilder(Block block, int age) {
		return BlockStateProperty.hasBlockStateProperties(block)
				.setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropsBlock.AGE, age));
	}

	protected static LootTable.Builder createLeavesDrops(Block p_218526_0_, Block p_218526_1_,
			Item drop, float... p_218526_2_) {
		return createLeavesDrops(p_218526_0_, p_218526_1_, p_218526_2_)
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).when(HAS_NO_SHEARS_OR_SILK_TOUCH)
						.add(applyExplosionCondition(p_218526_0_, ItemLootEntry.lootTableItem(drop))
								.when(TableBonus.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.01F, 0.015F,
										0.02F, 0.025F, 0.03F))));
	}

}
