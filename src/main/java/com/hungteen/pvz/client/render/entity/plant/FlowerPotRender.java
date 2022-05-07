package com.hungteen.pvz.client.render.entity.plant;

import com.hungteen.pvz.client.render.entity.PVZBlockRender;
import com.hungteen.pvz.common.block.PVZBlocks;
import com.hungteen.pvz.common.entity.plant.FlowerPot;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.level.block.state.BlockState;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-05-07 11:40
 **/
public class FlowerPotRender extends PVZBlockRender<FlowerPot> {

    public FlowerPotRender(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public BlockState getBlockByEntity(FlowerPot entity) {
        return PVZBlocks.FLOWER_POT.get().defaultBlockState();
    }
}
