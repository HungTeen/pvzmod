package com.hungteen.pvz.common.impl.type;

import com.hungteen.pvz.api.types.IPlaceType;
import com.hungteen.pvz.common.tag.PVZBlockTags;
import com.hungteen.pvz.utils.BlockUtil;
import net.minecraft.world.level.block.Block;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-27 19:13
 **/
public class PlaceTypes {

    public static IPlaceType COMMON = (block) -> {
        return block.builtInRegistryHolder().is(PVZBlockTags.PLANT_SUIT_BLOCKS);
    };

}
