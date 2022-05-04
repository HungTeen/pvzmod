package com.hungteen.pvz.client.render.entity.creature;

import com.hungteen.pvz.client.model.PVZModelLayers;
import com.hungteen.pvz.client.model.entity.creature.GrassCarpModel;
import com.hungteen.pvz.client.render.entity.PVZMobRender;
import com.hungteen.pvz.client.render.entity.layer.GrassCarpItemLayer;
import com.hungteen.pvz.common.entity.creature.GrassCarp;
import com.hungteen.pvz.utils.Util;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-28 20:53
 **/
public class GrassCarpRender extends PVZMobRender<GrassCarp> {

    private static final ResourceLocation RES = Util.prefix("textures/entity/creature/grass_carp.png");
    public GrassCarpRender(EntityRendererProvider.Context rendererManager) {
        super(rendererManager, new GrassCarpModel<>(rendererManager.bakeLayer(PVZModelLayers.GRASS_CARP)), 0);
        this.addLayer(new GrassCarpItemLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(GrassCarp p_114482_) {
        return RES;
    }
}
