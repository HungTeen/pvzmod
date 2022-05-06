package com.hungteen.pvz.data.loot;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.block.PVZBlocks;
import com.hungteen.pvz.common.block.cubes.EssenceOreBlock;
import com.hungteen.pvz.common.block.plant.crops.CabbageBlock;
import com.hungteen.pvz.common.block.plant.crops.CornBlock;
import com.hungteen.pvz.common.block.plant.crops.PeaBlock;
import com.hungteen.pvz.common.item.PVZItems;
import com.hungteen.pvz.utils.BlockUtil;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-09 10:34
 **/
public class BlockLootGen extends BlockLoot {

    private final Set<Block> knownBlocks = new HashSet<>();
    private LootItemCondition.Builder tmpBuilder;
    private static final LootItemCondition.Builder HAS_SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item()
            .hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))));
    //	private static final ILootCondition.IBuilder HAS_NO_SILK_TOUCH = HAS_SILK_TOUCH.invert();
    private static final LootItemCondition.Builder HAS_SHEARS = MatchTool
            .toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS));
    private static final LootItemCondition.Builder HAS_SHEARS_OR_SILK_TOUCH = HAS_SHEARS.or(HAS_SILK_TOUCH);
    private static final LootItemCondition.Builder HAS_NO_SHEARS_OR_SILK_TOUCH = HAS_SHEARS_OR_SILK_TOUCH.invert();
    private static final float[] NORMAL_LEAVES_SAPLING_CHANCES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};
//	private static final float[] JUNGLE_LEAVES_SAPLING_CHANGES = new float[]{0.025F, 0.027777778F, 0.03125F, 0.041666668F, 0.1F};

    @Override
    protected void addTables() {
        //some blocks have no loot.
        final Set<Block> noLootBlocks = new HashSet<>(Arrays.asList(
//                BlockRegister.LILY_PAD.get(), BlockRegister.FLOWER_POT.get(),
//                BlockRegister.SLOT_MACHINE.get()
        ));

        /*
        Essence ores.
         */
        BlockUtil.getFilterBlocks(EssenceOreBlock.class::isInstance).stream().map(EssenceOreBlock.class::cast).forEach(block -> {
            this.add(block, createOreDrop(block, block.essence.getEssenceItem()));
        });

        /*
        Crops.
         */
        this.addCrop(PVZBlocks.PEA.get(), PVZItems.PEA.get(), PeaBlock.AGE, 7);
        this.addCrop(PVZBlocks.CABBAGE.get(), PVZItems.CABBAGE_SEEDS.get(), PVZItems.CABBAGE.get(), CabbageBlock.AGE, 3);
        this.addCrop(PVZBlocks.CORN.get(), PVZItems.CORN_SEEDS.get(), PVZItems.CORN.get(), CornBlock.AGE, 3);

        /*
        Leaves.
         */
        this.addLeavesDrops(PVZBlocks.NUT_LEAVES.get(), PVZBlocks.NUT_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES);
        this.addLeavesDrops(PVZBlocks.NUT_LEAVES_WITH_NUTS.get(), PVZBlocks.NUT_SAPLING.get(), PVZItems.NUT.get(), NORMAL_LEAVES_SAPLING_CHANCES);

//
//        // misc
//        this.dropOther(BlockRegister.GOLD_TILE1.get(), Blocks.GOLD_BLOCK);
//        this.dropOther(BlockRegister.GOLD_TILE2.get(), Blocks.GOLD_BLOCK);
//        this.dropOther(BlockRegister.GOLD_TILE3.get(), Blocks.GOLD_BLOCK);

        // other blocks are drop itself.
        ForgeRegistries.BLOCKS.forEach(block -> {
            if (block.getRegistryName().getNamespace().equals(PVZMod.MOD_ID) && !noLootBlocks.contains(block) && !this.knownBlocks.contains(block) && block.asItem() != Items.AIR) {
                this.dropSelf(block);
            }
        });
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return this.knownBlocks;
    }

    @Override
    protected void add(Block block, LootTable.Builder builder) {
        super.add(block, builder);
        this.knownBlocks.add(block);
    }

    /**
     * Wheat type crops.
     */
    protected void addCrop(Block cropBlock, Item cropSeed, Item cropItem, IntegerProperty property, int reachAge){
        LootItemCondition.Builder builder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(cropBlock).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(property, reachAge));
        this.add(cropBlock, createCropDrops(cropBlock, cropItem, cropSeed, builder));
    }

    /**
     * Carrot type crops.
     */
    protected void addCrop(Block cropBlock, Item cropItem, IntegerProperty property, int reachAge){
        LootItemCondition.Builder builder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(cropBlock).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(property, reachAge));
        this.add(cropBlock, applyExplosionDecay(cropBlock, LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(cropItem))).withPool(LootPool.lootPool().when(builder).add(LootItem.lootTableItem(cropItem).apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3))))));
    }

    protected void addLeavesDrops(Block block, Block sapling, float... chance) {
        this.add(block, createLeavesDrops(block, sapling, chance));
    }

    protected void addLeavesDrops(Block block, Block sapling, Item seed, float... chance) {
        this.add(block, createLeavesDrops(block, sapling, chance).withPool(
                LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .when(HAS_NO_SHEARS_OR_SILK_TOUCH)
                        .add(applyExplosionCondition(block, LootItem.lootTableItem(seed))
                                .when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.6F, 0.7F, 0.8F, 0.9F, 1F))
                        )
        ));
    }

//    protected static LootTable.Builder addLeavesDrops(Block p_218526_0_, Block p_218526_1_,
//                                                         Item drop, float... p_218526_2_) {
//        return addLeavesDrops(p_218526_0_, p_218526_1_, p_218526_2_)
//                .withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).when(HAS_NO_SHEARS_OR_SILK_TOUCH)
//                        .add(applyExplosionCondition(p_218526_0_, ItemLootEntry.lootTableItem(drop))
//                                .when(TableBonus.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.01F, 0.015F,
//                                        0.02F, 0.025F, 0.03F))));
//    }

}