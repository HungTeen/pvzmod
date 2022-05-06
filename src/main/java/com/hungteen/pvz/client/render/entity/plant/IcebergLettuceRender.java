package com.hungteen.pvz.client.render.entity.plant;

import com.hungteen.pvz.client.model.PVZModelLayers;
import com.hungteen.pvz.client.model.entity.plant.IcebergLettuceModel;
import com.hungteen.pvz.common.entity.plant.IcebergLettuce;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-05-06 13:08
 **/
public class IcebergLettuceRender extends PVZPlantRender<IcebergLettuce> {

    public IcebergLettuceRender(EntityRendererProvider.Context rendererManager) {
        super(rendererManager, new IcebergLettuceModel<>(rendererManager.bakeLayer(PVZModelLayers.ICEBERG_LETTUCE)), 0.3F);
    }
}
