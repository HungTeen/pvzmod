package com.hungteen.pvz.client.render.entity.layer;

import com.hungteen.pvz.client.ClientProxy;
import com.hungteen.pvz.client.model.misc.ComponentModel;
import com.hungteen.pvz.common.entity.plant.base.PVZPlant;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.resources.ResourceLocation;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-08 16:02
 **/
public class SurroundDirtLayer<T extends PVZPlant> extends ComponentLayer<T> {

    public SurroundDirtLayer(RenderLayerParent<T, EntityModel<T>> parent, ComponentModel model) {
        super(parent, model);
    }

    @Override
    public ResourceLocation getRenderTexture(T entity) {
        final ResourceLocation res = ClientProxy.MC.getBlockRenderer().getBlockModelShaper().getTexture(entity.level.getBlockState(entity.blockPosition().below()), entity.level, entity.blockPosition().below()).getName();
        return new ResourceLocation(res.getNamespace(), "textures/" + res.getPath() + ".png");
    }

}
