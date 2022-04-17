package com.hungteen.pvz.common.block.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.BlockState;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-17 11:53
 *
 * {@link FireBlock#bootStrap()}
 **/
public class PVZBurnBlock extends Block {

    private final int fireSpeed;
    private final int burnSpeed;

    public PVZBurnBlock(Properties properties, int fireSpeed, int burnSpeed) {
        super(properties);
        this.fireSpeed = fireSpeed;
        this.burnSpeed = burnSpeed;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return this.fireSpeed;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return this.burnSpeed;
    }

}
