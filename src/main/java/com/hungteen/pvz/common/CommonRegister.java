package com.hungteen.pvz.common;

import net.minecraft.world.level.ItemLike;
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
//        registerCompostable(0.3F, BlockRegister.NUT_LEAVES.get());
//        registerCompostable(0.3F, BlockRegister.NUT_SAPLING.get());
//        registerCompostable(0.3F, ItemRegister.PEA.get());
//        registerCompostable(0.3F, ItemRegister.CABBAGE_SEEDS.get());
//        registerCompostable(0.3F, ItemRegister.CORN_SEEDS.get());
//        registerCompostable(0.4F, ItemRegister.NUT.get());
//        registerCompostable(0.5F, ItemRegister.CABBAGE.get());
//        registerCompostable(0.5F, ItemRegister.PEPPER.get());
//        registerCompostable(0.5F, ItemRegister.CORN.get());
    }

    private static void registerCompostable(float chance, ItemLike itemIn) {
        ComposterBlock.COMPOSTABLES.put(itemIn.asItem(), chance);
    }
}
