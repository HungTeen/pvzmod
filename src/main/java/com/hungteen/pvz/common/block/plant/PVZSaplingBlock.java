package com.hungteen.pvz.common.block.plant;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-24 12:11
 **/
public class PVZSaplingBlock extends SaplingBlock {

    public PVZSaplingBlock(AbstractTreeGrower grower) {
        super(grower, BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING));
    }


}
