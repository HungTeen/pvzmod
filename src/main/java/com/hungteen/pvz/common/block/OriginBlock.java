package com.hungteen.pvz.common.block;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.api.types.IEssenceType;
import com.hungteen.pvz.common.impl.EssenceType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class OriginBlock extends Block {

    private static final Map<Block, IEssenceType> BLOCK_TO_ESSENCE = new HashMap<>();
    private static final int RADIATION_RANGE = 1;

    public OriginBlock() {
        super(Block.Properties.of(Material.STONE, MaterialColor.COLOR_GREEN).strength(15, 50).harvestLevel(3).harvestTool(ToolType.PICKAXE)
                .lightLevel(i -> 15).randomTicks().sound(SoundType.GLASS).noOcclusion());
    }

    /**
     * update map when tags change.
     */
    public static void updateRadiationMap() {
        BLOCK_TO_ESSENCE.clear();

        EssenceType.ESSENCES.forEach(e -> {
            e.getRadiationBlockTag().ifPresent(tag -> {
                tag.getValues().forEach(b -> {
                    BLOCK_TO_ESSENCE.put(b, e);
                });
            });
        });
    }

    @SuppressWarnings("deprecation")
    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        super.tick(state, worldIn, pos, rand);
        if (!worldIn.isClientSide) {
            if (!worldIn.isAreaLoaded(pos, 3)) return;
            final int r = RADIATION_RANGE;
            for (int i = -r; i <= r; ++i) {
                for (int j = -r; j <= r; ++j) {
                    for (int k = -r; k <= r; ++k) {
                        if (rand.nextInt(PVZConfig.COMMON_CONFIG.BlockSettings.OriginBlockEffectChance.get()) == 0) {
                            checkAndGrow(worldIn, pos.getX() + i, pos.getY() + j, pos.getZ() + k);
                        }
                    }
                }
            }
        }
    }

    /**
     * check specific block and grow
     */
    private void checkAndGrow(World world, int x, int y, int z) {
        //TODO 优化原始精华块代码
        final BlockPos pos = new BlockPos(x, y, z);
        final BlockState blockstate = world.getBlockState(pos);
        final Block block = blockstate.getBlock();
        if (BLOCK_TO_ESSENCE.containsKey(block)) {
            world.setBlockAndUpdate(pos, BLOCK_TO_ESSENCE.get(block).getEssenceOre().defaultBlockState());
        }
    }

}

