package com.hungteen.pvz.client.render.entity.plant;

import com.hungteen.pvz.client.model.PVZModelLayers;
import com.hungteen.pvz.client.model.entity.plant.SnowPeaModel;
import com.hungteen.pvz.common.entity.plant.SnowPea;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-16 19:36
 **/
public class SnowPeaRender extends PVZPlantRender<SnowPea> {

    public SnowPeaRender(EntityRendererProvider.Context rendererManager) {
        super(rendererManager, new SnowPeaModel<>(rendererManager.bakeLayer(PVZModelLayers.SNOW_PEA)), 0.4F);
    }
}
