package com.hungteen.pvz.client.render.entity.plant;

import com.hungteen.pvz.client.model.PVZModelLayers;
import com.hungteen.pvz.client.model.component.SurroundDirtModel;
import com.hungteen.pvz.client.model.plant.PotatoMineModel;
import com.hungteen.pvz.client.render.entity.layer.SurroundDirtLayer;
import com.hungteen.pvz.common.entity.plant.PotatoMine;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-08 16:03
 **/
public class PotatoMineRender extends PVZPlantRender<PotatoMine> {
    public PotatoMineRender(EntityRendererProvider.Context rendererManager) {
        super(rendererManager, new PotatoMineModel<>(rendererManager.bakeLayer(PVZModelLayers.POTATO_MINE)), 0.5F);
        this.addLayer(new SurroundDirtLayer<>(this, new SurroundDirtModel<>(rendererManager.bakeLayer(PVZModelLayers.SURROUND_DIRT))));
    }
}
