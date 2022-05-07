package com.hungteen.pvz.client.render.entity.plant;

import com.hungteen.pvz.client.render.entity.PVZBlockRender;
import com.hungteen.pvz.common.block.PVZBlocks;
import com.hungteen.pvz.common.entity.plant.LilyPad;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.level.block.state.BlockState;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-05-07 11:39
 **/
public class LilyPadRender extends PVZBlockRender<LilyPad> {

    public LilyPadRender(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public BlockState getBlockByEntity(LilyPad entity) {
        return PVZBlocks.LILY_PAD.get().defaultBlockState();
    }
}
