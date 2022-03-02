package com.hungteen.pvz.common.block.misc;

import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

/**
 * @program: pvzmod-1.16.5
 * @author: HungTeen
 * @create: 2022-03-02 14:36
 **/
public class PVZLogBlock extends RotatedPillarBlock {

    private final int fireSpeed;
    private final int burnSpeed;

    public PVZLogBlock(Properties properties, int fireSpeed, int burnSpeed) {
        super(properties);
        this.fireSpeed = fireSpeed;
        this.burnSpeed = burnSpeed;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
        return this.fireSpeed;
    }

    @Override
    public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
        return this.burnSpeed;
    }
}
