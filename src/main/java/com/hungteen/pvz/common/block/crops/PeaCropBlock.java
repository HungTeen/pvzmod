package com.hungteen.pvz.common.block.crops;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-01 16:18
 *
 * TODO Pea Crops.
 **/
public class PeaCropBlock extends CropBlock {

    public PeaCropBlock() {
        super(Block.Properties.copy(Blocks.WHEAT));
    }

}
