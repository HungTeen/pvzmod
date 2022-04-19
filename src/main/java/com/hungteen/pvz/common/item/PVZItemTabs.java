package com.hungteen.pvz.common.item;

import com.hungteen.pvz.common.block.PVZBlocks;
import com.hungteen.pvz.common.impl.type.plant.PVZPlants;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-08 22:38
 **/
public class PVZItemTabs {

    public static final CreativeModeTab PVZ_MISC = new CreativeModeTab("pvz_misc") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.IRON_INGOT);
        }

    };

    public static final CreativeModeTab PVZ_BLOCK = new CreativeModeTab("pvz_block") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(PVZBlocks.ORIGIN_BLOCK.get());
        }

    };
//    .setEnchantmentCategories(PVZEnchantmentTypes.getPVZEnchantmentTypes());

    public static final CreativeModeTab PVZ_PLANT_CARD = new CreativeModeTab("pvz_plant_card") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(PVZPlants.SUN_FLOWER.getSummonCard().get());
        }
    };

    public static final CreativeModeTab PVZ_USEFUL = new CreativeModeTab("pvz_useful") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.IRON_SWORD);
        }
    };

//    public static final CreativeModeTab PVZ_ENVELOPE = new CreativeModeTab("pvz_envelope") {
//        @Override
//        public ItemStack makeIcon() {
//            return new ItemStack(ItemRegister.CHALLENGE_ENVELOPE.get());
//        }
//    };

}
