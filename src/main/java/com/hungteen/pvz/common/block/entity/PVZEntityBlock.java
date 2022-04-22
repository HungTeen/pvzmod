package com.hungteen.pvz.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-22 10:58
 **/
public abstract class PVZEntityBlock extends BaseEntityBlock {

    protected PVZEntityBlock(Properties properties) {
        super(properties);
    }

}
