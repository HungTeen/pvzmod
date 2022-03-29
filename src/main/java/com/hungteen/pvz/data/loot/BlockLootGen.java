package com.hungteen.pvz.data.loot;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.block.cubes.EssenceOreBlock;
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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

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

        //essence ores.
        BlockUtil.getFilterBlocks(block -> block instanceof EssenceOreBlock).forEach(block -> {
            this.add(block, b -> {
                return createOreDrop(b, ((EssenceOreBlock) b).essence.getEssenceItem());
            });
        });

        // drop item like coal ore
//        Arrays.asList(BlockRegister.ORIGIN_ORE.get(), BlockRegister.APPEASE_ORE.get(), BlockRegister.LIGHT_ORE.get(),
//                        BlockRegister.EXPLOSION_ORE.get(), BlockRegister.DEFENCE_ORE.get(), BlockRegister.ICE_ORE.get(),
//                        BlockRegister.ENFORCE_ORE.get(), BlockRegister.TOXIC_ORE.get(), BlockRegister.ASSIST_ORE.get(),
//                        BlockRegister.MAGIC_ORE.get(), BlockRegister.FLAME_ORE.get(), BlockRegister.SPEAR_ORE.get(),
//                        BlockRegister.ARMA_ORE.get(), BlockRegister.ELECTRIC_ORE.get(), BlockRegister.SHADOW_ORE.get())
//                .forEach((object) -> {
//                    this.add(object, (block) -> createOreDrop(block, object.essence.getEssenceItem()));
//                });
//        // crop
//        this.tmpBuilder = getAgeBuilder(BlockRegister.CABBAGE.get(), 3);
//        this.add(BlockRegister.CABBAGE.get(),
//                createCropDrops(BlockRegister.CABBAGE.get(), ItemRegister.CABBAGE.get(), this.tmpBuilder));
//        this.tmpBuilder = getAgeBuilder(BlockRegister.CORN.get(), 7);
//        this.add(BlockRegister.CORN.get(),
//                createCropDrops(BlockRegister.CORN.get(), ItemRegister.CORN.get(), this.tmpBuilder));
//
//        // leaves
//        this.add(BlockRegister.NUT_LEAVES.get(), (p_218506_0_) -> {
//            return createLeavesDrops(p_218506_0_, BlockRegister.NUT_SAPLING.get(), ItemRegister.NUT.get(), NORMAL_LEAVES_SAPLING_CHANCES);
//        });
//
//        // misc
//        this.dropOther(BlockRegister.GOLD_TILE1.get(), Blocks.GOLD_BLOCK);
//        this.dropOther(BlockRegister.GOLD_TILE2.get(), Blocks.GOLD_BLOCK);
//        this.dropOther(BlockRegister.GOLD_TILE3.get(), Blocks.GOLD_BLOCK);

        // other blocks are drop itself
//        ForgeRegistries.BLOCKS.forEach(block -> {
//            if (block.getRegistryName().getNamespace().equals(PVZMod.MOD_ID) && !noLootBlocks.contains(block) && !this.knownBlocks.contains(block)) {
//                this.dropSelf(block);
//            }
//        });
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

    private static LootTable.Builder createCropDrops(Block block, Item crops, LootItemCondition.Builder bb) {
        return createCropDrops(block, crops, block.asItem(), bb);
    }

//    private LootItemCondition.Builder getAgeBuilder(Block block, int age) {
//        return BlockStateProperty.hasBlockStateProperties(block)
//                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropsBlock.AGE, age));
//    }
//
//    protected static LootTable.Builder createLeavesDrops(Block p_218526_0_, Block p_218526_1_,
//                                                         Item drop, float... p_218526_2_) {
//        return createLeavesDrops(p_218526_0_, p_218526_1_, p_218526_2_)
//                .withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).when(HAS_NO_SHEARS_OR_SILK_TOUCH)
//                        .add(applyExplosionCondition(p_218526_0_, ItemLootEntry.lootTableItem(drop))
//                                .when(TableBonus.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.01F, 0.015F,
//                                        0.02F, 0.025F, 0.03F))));
//    }

}