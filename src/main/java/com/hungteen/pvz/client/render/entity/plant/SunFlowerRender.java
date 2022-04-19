package com.hungteen.pvz.client.render.entity.plant;

import com.hungteen.pvz.client.model.PVZModelLayers;
import com.hungteen.pvz.client.model.entity.plant.SunFlowerModel;
import com.hungteen.pvz.common.entity.plant.SunFlower;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-28 18:12
 **/
public class SunFlowerRender extends PVZPlantRender<SunFlower> {

    public SunFlowerRender(EntityRendererProvider.Context rendererManager) {
        super(rendererManager, new SunFlowerModel<>(rendererManager.bakeLayer(PVZModelLayers.SUN_FLOWER)), 0.5F);
    }

}
