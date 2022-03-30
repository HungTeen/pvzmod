package com.hungteen.pvz.api.types;

import com.hungteen.pvz.api.interfaces.IPlantInfo;
import com.hungteen.pvz.api.types.base.IPAZType;
import net.minecraft.world.level.block.Block;

import java.util.Optional;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-26 15:37
 **/
public interface IPlantType extends IPAZType {

    /**
     * can plant card place on.
     */
    IPlaceType getPlacement();

    /**
     * the block type : it's not an entity, but a block. <br>
     * get corresponding block, such as Lily Pad.
     */
    Optional<Block> getPlantBlock();

    /**
     * the water type : it only lives in water. <br>
     * such as Tangle Kelp.
     */
    boolean isWaterPlant();

    /**
     * the shroom type : it need sleep at night. <br>
     * such as Puff Shroom.
     */
    boolean isShroomPlant();

    /**
     * the outer type : it's not an entity, but a render layer. <br>
     * such as Pumpkin.
     */
    Optional<IPlantInfo> getOuterPlant();

}
