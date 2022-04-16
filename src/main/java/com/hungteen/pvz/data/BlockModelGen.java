package com.hungteen.pvz.data;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.block.PVZBlocks;
import com.hungteen.pvz.common.block.crops.CabbageBlock;
import com.hungteen.pvz.common.block.crops.CornBlock;
import com.hungteen.pvz.common.block.crops.PeaBlock;
import com.hungteen.pvz.utils.Util;
import com.mojang.datafixers.util.Pair;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-22 10:36
 **/
public class BlockModelGen extends BlockModelProvider {

    private Set<Block> addedBlocks = new HashSet<>();

    public BlockModelGen(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, PVZMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        /*
        Special model generated by blockbench.
         */
        addedBlocks.addAll(Arrays.asList(
                PVZBlocks.NUT_WALL_SIGN.get(),
                PVZBlocks.ABYSSAL_DARK_PORTAL_BLOCK.get()
//                BlockRegister.CARD_FUSION_TABLE.get(), BlockRegister.CHOMPER.get(), BlockRegister.DIAMOND_SUNFLOWER_TROPHY.get(),
//                BlockRegister.ESSENCE_ALTAR.get(), BlockRegister.FLOWER_POT.get(), BlockRegister.GOLD_SUNFLOWER_TROPHY.get(), BlockRegister.LANTERN.get(),
//                BlockRegister.LILY_PAD.get(), BlockRegister.SILVER_SUNFLOWER_TROPHY.get(), BlockRegister.STEEL_LADDER.get(), BlockRegister.SUN_CONVERTER.get()
        ));

        /*
        Crops with age property.
         */
        Arrays.asList(
               PVZBlocks.PEA.get(), PVZBlocks.CABBAGE.get(), PVZBlocks.CORN.get()
//                Pair.of(BlockRegister.TOXIC_SHROOM.get(), ToxicShroomBlock.AGE),
        ).forEach(block -> {
            genCrop(block);
        });

        /*
        Blocks with 2 textures(top & side).
         */
        Arrays.asList(
                PVZBlocks.NUT_LOG.get(), PVZBlocks.STRIPPED_NUT_LOG.get()
        ).forEach(b -> {
            log(b);
        });
        
        /*
        Blocks with 2 textures(top & side).
         */
        Arrays.asList(
                PVZBlocks.NUT_WOOD.get(), PVZBlocks.STRIPPED_NUT_WOOD.get()
        ).forEach(b -> {
            wood(b);
        });

         /*
        Door Blocks.
         */
        Arrays.asList(
                PVZBlocks.NUT_DOOR.get()
        ).forEach(b -> {
            door(b);
        });

        /*
        Trapdoor Blocks.
         */
        Arrays.asList(
                PVZBlocks.NUT_TRAPDOOR.get()
        ).forEach(b -> {
            trapdoor(b);
        });

        /*
        Fence Blocks.
         */
        Arrays.asList(
                PVZBlocks.NUT_FENCE.get()
        ).forEach(b -> {
            fence(b);
        });

        /*
        Fence Gate Blocks.
         */
        Arrays.asList(
                PVZBlocks.NUT_FENCE_GATE.get()
        ).forEach(b -> {
            fenceGate(b);
        });

        /*
        Sign Blocks.
         */
        Arrays.asList(
                PVZBlocks.NUT_SIGN.get()
        ).forEach(b -> {
            sign(b);
        });

        /*
        Blocks with 3 textures(up & down & side).
         */
//        Arrays.asList(
//
////                BlockRegister.FRAGMENT_SPLICE.get(), BlockRegister.SLOT_MACHINE.get(), BlockRegister.ARMA_ORE.get(), BlockRegister.TOXIC_ORE.get()
//        ).forEach(b -> {
//            topSideBottom(b);
//        });

        /*
        Block with 2 textures(up & side).
         */
//        Arrays.asList(BlockRegister.FROZEN_MELON.get()
//        ).forEach(b -> {
//            topSide(b);
//        });

        /*
        Blocks with cross style.
         */
//        Arrays.asList(BlockRegister.NUT_SAPLING.get()
//        ).forEach(b -> {
//            cross(b.getRegistryName().getPath(), StringUtil.prefix("block/" + b.getRegistryName().getPath()));
//            this.addedBlocks.add(b);
//        });



        /*
        Last step for all normal block models.
         */
        for(Block b : ForgeRegistries.BLOCKS) {
            if(b.getRegistryName().getNamespace().equals(PVZMod.MOD_ID) && ! addedBlocks.contains(b)) {
                fullCube(b);
            }
        }
    }

    private void fullCube(Block block) {
        cubeAll(block.getRegistryName().getPath(), Util.prefix("block/" + block.getRegistryName().getPath()));
    }

    private void genCrop(CropBlock block) {
        for(int i = 0; i <= block.getMaxAge(); ++ i) {
            crop(block.getRegistryName().getPath() + "_" + i, Util.prefix("block/" + block.getRegistryName().getPath() + "_" + i));
        }
        this.addedBlocks.add(block);
    }

    private void topSideBottom(Block block) {
        cube(block.getRegistryName().getPath(),
                Util.prefix("block/" + block.getRegistryName().getPath() + "_down"),
                Util.prefix("block/" + block.getRegistryName().getPath() + "_top"),
                Util.prefix("block/" + block.getRegistryName().getPath() + "_side"),
                Util.prefix("block/" + block.getRegistryName().getPath() + "_side"),
                Util.prefix("block/" + block.getRegistryName().getPath() + "_side"),
                Util.prefix("block/" + block.getRegistryName().getPath() + "_side")
        );
        this.addedBlocks.add(block);
    }

//    private void topSide(Block b) {
//        cubeColumn(b.getRegistryName().getPath(), Util.prefix("block/" + b.getRegistryName().getPath() + "_side"), Util.prefix("block/" + b.getRegistryName().getPath() + "_top"));
//        this.addedBlocks.add(b);
//    }

    private void log(Block b){
        cubeColumn(b.getRegistryName().getPath(), Util.prefix("block/" + b.getRegistryName().getPath()), Util.prefix("block/" + b.getRegistryName().getPath() + "_top"));
        this.addedBlocks.add(b);
    }

    private void wood(Block b){
        final String path = b.getRegistryName().getPath();
        String realPath = path.substring(0, path.indexOf("wood")) + "log";
        cubeColumn(b.getRegistryName().getPath(), Util.prefix("block/" + realPath), Util.prefix("block/" + realPath));
        this.addedBlocks.add(b);
    }

    private void door(Block b){
        final ResourceLocation bottom = Util.prefix("block/" + b.getRegistryName().getPath() + "_bottom");
        final ResourceLocation top = Util.prefix("block/" + b.getRegistryName().getPath() + "_top");
        doorBottomLeft(b.getRegistryName().getPath() + "_bottom", bottom, top);
        doorBottomRight(b.getRegistryName().getPath() + "_bottom_hinge", bottom, top);
        doorTopLeft(b.getRegistryName().getPath() + "_top", bottom, top);
        doorTopRight(b.getRegistryName().getPath() + "_top_hinge", bottom, top);
        this.addedBlocks.add(b);
    }

    private void trapdoor(Block b){
        final ResourceLocation res = Util.prefix("block/" + b.getRegistryName().getPath());
        trapdoorOrientableBottom(b.getRegistryName().getPath() + "_bottom", res);
        trapdoorOrientableOpen(b.getRegistryName().getPath() + "_open", res);
        trapdoorOrientableTop(b.getRegistryName().getPath() + "_top", res);
        this.addedBlocks.add(b);
    }

    private void fence(Block b){
        final String path = b.getRegistryName().getPath();
        final String realPath = path.substring(0, path.indexOf("fence")) + "planks";
        final ResourceLocation res = Util.prefix("block/" + realPath);
        fenceInventory(b.getRegistryName().getPath() + "_inventory", res);
        fencePost(b.getRegistryName().getPath() + "_post", res);
        fenceSide(b.getRegistryName().getPath() + "_side", res);
        this.addedBlocks.add(b);
    }

    private void fenceGate(Block b){
        final String path = b.getRegistryName().getPath();
        final String realPath = path.substring(0, path.indexOf("fence_gate")) + "planks";
        final ResourceLocation res = Util.prefix("block/" + realPath);
        fenceGate(b.getRegistryName().getPath() + "_gate", res);
        fenceGateOpen(b.getRegistryName().getPath() + "_open", res);
        fenceGateWall(b.getRegistryName().getPath() + "_wall", res);
        fenceGateWallOpen(b.getRegistryName().getPath() + "_wall_open", res);
        this.addedBlocks.add(b);
    }

    private void sign(Block b){
        final String path = b.getRegistryName().getPath();
        final String realPath = path.substring(0, path.indexOf("sign")) + "planks";
        final ResourceLocation res = Util.prefix("block/" + realPath);
        sign(b.getRegistryName().getPath(), res);
        this.addedBlocks.add(b);
    }

    @Override
    public String getName() {
        return "Plants vs Zombies block models";
    }
}
