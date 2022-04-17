package com.hungteen.pvz.common.block.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-17 16:32
 **/
public class PVZSlabBlock extends SlabBlock {

    public PVZSlabBlock(Properties properties) {
        super(properties);
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return 5;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return 20;
    }

}
