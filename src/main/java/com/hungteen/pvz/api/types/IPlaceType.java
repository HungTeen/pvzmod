package com.hungteen.pvz.api.types;

import net.minecraft.world.level.block.Block;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-26 15:40
 **/
public interface IPlaceType {

    /**
     * {@link PVZPlantEntity#shouldWilt()}
     */
    boolean canPlaceOnBlock(Block block);

}
