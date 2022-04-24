package com.hungteen.pvz.data;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.block.PVZBlocks;
import com.hungteen.pvz.common.item.PVZItems;
import com.hungteen.pvz.common.item.spawn.card.PlantCardItem;
import com.hungteen.pvz.utils.Util;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-09 15:18
 **/
public class ItemModelGen extends ItemModelProvider {

    private Set<Item> addedItems = new HashSet<>();

    public ItemModelGen(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, PVZMod.MOD_ID, helper);
    }

    @Override
    protected void registerModels() {
        /*
        Special model generated by blockbench.
         */
        addedItems.addAll(Arrays.asList(
                PVZItems.PEA_GUN.get()
//                ItemRegister.PEA_GUN.get(), ItemRegister.ZOMBIE_FLAG.get(), ItemRegister.BOBSLE_CAR.get(),
//                ItemRegister.SCREEN_DOOR.get(), ItemRegister.JACK_BOX.get(), ItemRegister.BALLOON.get(),
//                ItemRegister.WARNING_SIGN.get(), ItemRegister.ZOMBIE_DOLL.get(), ItemRegister.POLE.get(),
//                ItemRegister.BUCKET_HEAD.get(), ItemRegister.RESOURCE_COLLECTOR.get()
        ));

        /*
        Single add block items so that except adding by method below.
         */
        Arrays.asList(
                PVZItems.PEA.get(), PVZItems.CABBAGE_SEEDS.get(), PVZItems.CORN_SEEDS.get()//,ItemRegister.SPORE.get()
        ).forEach(i -> {
            genNormalModel(i);
            this.addedItems.add(i);
        });

        /*
        Fence.
         */
        Arrays.asList(
                PVZBlocks.NUT_FENCE.get()
        ).forEach(block -> {
            genBlockModel(block, block.getRegistryName().getPath() + "_inventory");
        });

        /*
        Trap Door.
         */
        Arrays.asList(
                PVZBlocks.NUT_TRAPDOOR.get()
        ).forEach(block -> {
            genBlockModel(block, block.getRegistryName().getPath() + "_bottom");
        });

        /*
        Block-items with tex in item/
         */
        Arrays.asList(
                PVZBlocks.NUT_DOOR.get().asItem(), PVZItems.NUT_SIGN.get()
        ).forEach(i -> {
            genNormalModel(i);
            this.addedItems.add(i);
        });

        /*
        Block-items with tex in block/
         */
        Arrays.asList(
                PVZBlocks.NUT_SAPLING
//                BlockRegister.STEEL_LADDER.get()
        ).forEach(i -> {
            genItemModelWithBlock(i.get().asItem());
        });

        /*
        For hand held item.
         */
        Arrays.asList(
                PVZItems.ORIGIN_AXE.get(), PVZItems.ORIGIN_HOE.get(), PVZItems.ORIGIN_PICKAXE.get(), PVZItems.ORIGIN_SHOVEL.get(), PVZItems.ORIGIN_SWORD.get()
        ).forEach(i -> {
            addedItems.add(i);
            genHeld(i.getRegistryName().getPath(), Util.prefix("item/" + i.getRegistryName().getPath()));
        });

        //3 types of sun storage sapling.
//        genSameModelsWithAdd(ItemRegister.SUN_STORAGE_SAPLING.get(), ItemRegister.SMALL_SUN_STORAGE_SAPLING.get(), ItemRegister.LARGE_SUN_STORAGE_SAPLING.get(), ItemRegister.ONCE_SUN_STORAGE_SAPLING.get());


        /*
        For mostly common items.
         */
        for (Item i : ForgeRegistries.ITEMS) {
            //ignore.
            if (!i.getRegistryName().getNamespace().equals(PVZMod.MOD_ID) || addedItems.contains(i)){
                continue;
            }
            if (i instanceof SpawnEggItem) {//for spawn eggs.
                addedItems.add(i);
                getBuilder(i.getRegistryName().getPath()).parent(getExistingFile(new ResourceLocation("item/template_spawn_egg")));
            } else if (i instanceof PlantCardItem) {//for plant cards.
                IPlantType plant = ((PlantCardItem) i).plantType;
                final ResourceLocation plantResource = new ResourceLocation(plant.getModID(), "art/plant/" + plant.toString());
                addedItems.add(i);
                if (((PlantCardItem) i).isEnjoyCard) {
                    final ResourceLocation r = Util.prefix("item/misc/mega_card");
                    genNormal(i.getRegistryName().getPath(), r, plantResource);
                } else {
                    genNormal(i.getRegistryName().getPath(), Util.prefix("item/misc/" + plant.getRankType().getName() + "_card"), plantResource);
                }
            } else if (i instanceof BlockItem) {//normal block items.
                genBlockModel(((BlockItem) i).getBlock());
            }
        }

        /*
        Last step for all normal item models.
         */
        for (Item i : ForgeRegistries.ITEMS) {
            if (i.getRegistryName().getNamespace().equals(PVZMod.MOD_ID) && !addedItems.contains(i)) {
                genNormal(i.getRegistryName().getPath(), Util.prefix("item/" + i.getRegistryName().getPath()));
            }
        }
    }

    /**
     * for items with the same texture.
     */
    private void genSameModelsWithAdd(Item... items) {
        final Item first = items[0];
        for (Item i : items) {
            genNormal(i.getRegistryName().getPath(), Util.prefix("item/" + first.getRegistryName().getPath()));
            this.addedItems.add(i);
        }
    }

    private void genNormalModel(Item i) {
        genNormal(i.getRegistryName().getPath(), Util.prefix("item/" + i.getRegistryName().getPath()));
    }

    private ItemModelBuilder genNormal(String name, ResourceLocation... layers) {
        return gen(name, "item/generated", layers);
    }

    private ItemModelBuilder genHeld(String name, ResourceLocation... layers) {
        return gen(name, "item/handheld", layers);
    }

    private ItemModelBuilder gen(String name, String parent, ResourceLocation... layers) {
        ItemModelBuilder builder = withExistingParent(name, parent);
        for (int i = 0; i < layers.length; i++) {
            builder = builder.texture("layer" + i, layers[i]);
        }
        return builder;
    }

    private void genBlockModel(Block b) {
        genBlockModel(b, b.getRegistryName().getPath());
    }

    private void genBlockModel(Block b, String path) {
        withExistingParent(b.getRegistryName().getPath(), Util.prefix("block/" + path));
        this.addedItems.add(b.asItem());
    }

    private void genItemModelWithBlock(Item i) {
        genNormal(i.getRegistryName().getPath(), Util.prefix("block/" + i.getRegistryName().getPath()));
        this.addedItems.add(i);
    }

    @Override
    public String getName() {
        return "Plants vs Zombies item and itemblock models";
    }
}