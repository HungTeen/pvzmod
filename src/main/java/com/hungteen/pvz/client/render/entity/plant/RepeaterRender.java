package com.hungteen.pvz.client.render.entity.plant;

import com.hungteen.pvz.client.model.PVZModelLayers;
import com.hungteen.pvz.client.model.entity.plant.RepeaterModel;
import com.hungteen.pvz.common.entity.plant.Repeater;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-05-06 13:08
 **/
public class RepeaterRender extends PVZPlantRender<Repeater> {

    public RepeaterRender(EntityRendererProvider.Context rendererManager) {
        super(rendererManager, new RepeaterModel<>(rendererManager.bakeLayer(PVZModelLayers.REPEATER)), 0.4F);
    }
}
