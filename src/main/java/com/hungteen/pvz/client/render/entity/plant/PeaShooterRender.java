package com.hungteen.pvz.client.render.entity.plant;

import com.hungteen.pvz.client.model.PVZModelLayers;
import com.hungteen.pvz.client.model.plant.PeaShooterModel;
import com.hungteen.pvz.common.entity.plant.PeaShooter;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-01 15:32
 **/
public class PeaShooterRender extends PVZPlantRender<PeaShooter> {

    public PeaShooterRender(EntityRendererProvider.Context rendererManager) {
        super(rendererManager, new PeaShooterModel<>(rendererManager.bakeLayer(PVZModelLayers.PEA_SHOOTER)), 0.4F);
    }

}
