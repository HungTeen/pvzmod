package com.hungteen.pvz.client.render.entity.layer;

import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.Util;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-18 09:59
 **/
public class ColdLayer<T extends LivingEntity, M extends EntityModel<T>> extends PVZSkinLayer<T, M>{

    private static final ResourceLocation RESOURCE_LOCATION = Util.texture("entity/layer/cold_layer.png");

    public ColdLayer(RenderLayerParent parent) {
        super(parent);
    }

    @Override
    public boolean canRender(T entity) {
        return EntityUtil.isEntityCold(entity);
    }

    @Override
    protected float xOffset(float tick) {
        return 0;
    }

    @Override
    protected ResourceLocation getTextureLocation() {
        return RESOURCE_LOCATION;
    }

}
