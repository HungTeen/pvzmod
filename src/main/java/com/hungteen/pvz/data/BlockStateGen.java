package com.hungteen.pvz.data;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.block.PVZBlocks;
import com.hungteen.pvz.common.block.crops.CabbageBlock;
import com.hungteen.pvz.common.block.crops.CornBlock;
import com.hungteen.pvz.common.block.crops.PeaBlock;
import com.hungteen.pvz.common.item.PVZItems;
import com.hungteen.pvz.utils.Util;
import com.mojang.datafixers.util.Pair;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-22 10:33
 **/
public class BlockStateGen extends BlockStateProvider {

    private Set<Block> addedBlocks = new HashSet<>();

    public BlockStateGen(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, PVZMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        /*
        Special model generated by blockbench.
         */
        addedBlocks.addAll(Arrays.asList(
                PVZBlocks.ABYSSAL_DARK_PORTAL_BLOCK.get()
//                BlockRegister.CARD_FUSION_TABLE.get(), BlockRegister.CHOMPER.get(), BlockRegister.DIAMOND_SUNFLOWER_TROPHY.get(),
//                BlockRegister.ESSENCE_ALTAR.get(), BlockRegister.FLOWER_POT.get(), BlockRegister.GOLD_SUNFLOWER_TROPHY.get(), BlockRegister.LANTERN.get(),
//                BlockRegister.LILY_PAD.get(), BlockRegister.SILVER_SUNFLOWER_TROPHY.get(), BlockRegister.STEEL_LADDER.get(), BlockRegister.SUN_CONVERTER.get()
        ));

        /*
        Crops with age property.
         */
        Arrays.asList(
                Pair.of(PVZBlocks.PEA.get(), PeaBlock.AGE),
//                Pair.of(BlockRegister.TOXIC_SHROOM.get(), ToxicShroomBlock.AGE),
                Pair.of(PVZBlocks.CABBAGE.get(), CabbageBlock.AGE),
                Pair.of(PVZBlocks.CORN.get(), CornBlock.AGE)
        ).forEach(pair -> {
            cropBlockState(pair.getFirst(), pair.getSecond());
        });

        /*
        RotatedPillarBlocks.
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
                Pair.of(PVZBlocks.NUT_SIGN.get(), PVZBlocks.NUT_WALL_SIGN.get())
        ).forEach(pair -> {
            sign(pair.getFirst(), pair.getSecond());
        });

        /*
        Last step for all normal block models.
         */
        for(Block b : ForgeRegistries.BLOCKS) {
            if(b.getRegistryName().getNamespace().equals(PVZMod.MOD_ID) && ! addedBlocks.contains(b)) {
                simpleBlock(b);
            }
        }
    }

    private void cropBlockState(Block block, Property<Integer> p) {
        getVariantBuilder(block).forAllStates(state -> {
            int i = state.getValue(p);
            return ConfiguredModel.builder()
                    .modelFile(models().crop(block.getRegistryName().getPath() + "_" + i, Util.prefix("block/" + block.getRegistryName().getPath() + "_" + i)))
                    .build();
        });
        this.addedBlocks.add(block);
    }

    private void log(RotatedPillarBlock b) {
        logBlock(b);
        this.addedBlocks.add(b);
    }
    
    private void wood(RotatedPillarBlock b) {
    	final String path = b.getRegistryName().getPath();
        String realPath = path.substring(0, path.indexOf("wood")) + "log";
        final ResourceLocation res = Util.prefix("block/" + realPath);
        axisBlock(b, res, res);
        this.addedBlocks.add(b);
    }

    private void door(DoorBlock b){
        final ResourceLocation bottom = Util.prefix("block/" + b.getRegistryName().getPath() + "_bottom");
        final ResourceLocation top = Util.prefix("block/" + b.getRegistryName().getPath() + "_top");
        doorBlock(b, bottom, top);
        this.addedBlocks.add(b);
    }

    private void trapdoor(TrapDoorBlock b){
        final ResourceLocation res = Util.prefix("block/" + b.getRegistryName().getPath());
        trapdoorBlock(b, res, true);
        this.addedBlocks.add(b);
    }

    private void fence(FenceBlock b){
        final String path = b.getRegistryName().getPath();
        final String realPath = path.substring(0, path.indexOf("fence")) + "planks";
        final ResourceLocation res = Util.prefix("block/" + realPath);
        fenceBlock(b, res);
        this.addedBlocks.add(b);
    }

    private void fenceGate(FenceGateBlock b){
        final String path = b.getRegistryName().getPath();
        final String realPath = path.substring(0, path.indexOf("fence_gate")) + "planks";
        final ResourceLocation res = Util.prefix("block/" + realPath);
        fenceGateBlock(b, res);
        this.addedBlocks.add(b);
    }

    private void sign(StandingSignBlock b, WallSignBlock b2){
        final String path = b.getRegistryName().getPath();
        final String realPath = path.substring(0, path.indexOf("sign")) + "planks";
        final ResourceLocation res = Util.prefix("block/" + realPath);
        signBlock(b, b2, res);
        this.addedBlocks.add(b);
        this.addedBlocks.add(b2);
    }

//	private void horizontalBlockState(Block block) {
//		ModelFile file = models().cubeAll(block.getRegistryName().getPath(), StringUtil.prefix("block/" + block.getRegistryName().getPath()));
//		horizontalBlock(block, file);
//	}

    @Override
    public String getName() {
        return "Plants vs Zombies blockstates";
    }

}
