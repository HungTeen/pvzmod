package com.hungteen.pvz.common.block.misc;

import net.minecraft.world.level.block.BlockState;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.IBlockReader;

/**
 * @program: pvzmod-1.16.5
 * @author: HungTeen
 * @create: 2022-03-02 14:26
 **/
public class PVZLeavesBlock extends LeavesBlock {

    private final int fireSpeed;
    private final int burnSpeed;

    public PVZLeavesBlock(Properties properties, int fireSpeed, int burnSpeed) {
        super(properties);
        this.fireSpeed = fireSpeed;
        this.burnSpeed = burnSpeed;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, IBlockReader world, Mth pos, Direction face) {
        return this.fireSpeed;
    }

    @Override
    public int getFlammability(BlockState state, IBlockReader world, Mth pos, Direction face) {
        return this.burnSpeed;
    }

}
