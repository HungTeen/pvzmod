package com.hungteen.pvz.common;

import com.hungteen.pvz.common.block.PVZBlocks;
import com.hungteen.pvz.common.item.PVZItems;
import com.hungteen.pvz.utils.BlockUtil;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-11 08:39
 **/
public class CommonRegister {

    /**
     * {@link com.hungteen.pvz.PVZMod#setUp(FMLCommonSetupEvent)}
     */
    public static void registerCompostable() {
        registerCompostable(0.3F, PVZBlocks.NUT_LEAVES.get());
        registerCompostable(0.3F, PVZBlocks.NUT_SAPLING.get());
        registerCompostable(0.3F, PVZItems.PEA.get());
        registerCompostable(0.3F, PVZItems.CABBAGE_SEEDS.get());
        registerCompostable(0.3F, PVZItems.CORN_SEEDS.get());
        registerCompostable(0.65F, PVZItems.NUT.get());
        registerCompostable(0.65F, PVZItems.PEPPER.get());
        registerCompostable(0.65F, PVZItems.CORN.get());
        registerCompostable(0.85F, PVZItems.CABBAGE.get());
    }

    /**
     * {@link com.hungteen.pvz.PVZMod#setUp(FMLCommonSetupEvent)}
     */
    public static void registerAxeStrips() {
        registerAxeStrip(PVZBlocks.NUT_LOG.get(), PVZBlocks.STRIPPED_NUT_LOG.get());
        registerAxeStrip(PVZBlocks.NUT_WOOD.get(), PVZBlocks.STRIPPED_NUT_WOOD.get());
    }

    private static void registerCompostable(float chance, ItemLike itemIn) {
        ComposterBlock.COMPOSTABLES.put(itemIn.asItem(), chance);
    }

    private static void registerAxeStrip(Block oldState, Block newState) {
        BlockUtil.STRIPPABLES.put(oldState, newState);
    }
}
