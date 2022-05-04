package com.hungteen.pvz.client.render.entity.plant;

import com.hungteen.pvz.client.model.PVZModelLayers;
import com.hungteen.pvz.client.model.entity.plant.CabbagePultModel;
import com.hungteen.pvz.common.entity.plant.CabbagePult;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-01 15:32
 **/
public class CabbagePultRender extends PVZPlantRender<CabbagePult> {

    public CabbagePultRender(EntityRendererProvider.Context rendererManager) {
        super(rendererManager, new CabbagePultModel<>(rendererManager.bakeLayer(PVZModelLayers.CABBAGE_PULT)), 0.4F);
    }

}
