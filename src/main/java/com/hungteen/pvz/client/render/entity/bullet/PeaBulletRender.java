package com.hungteen.pvz.client.render.entity.bullet;

import com.hungteen.pvz.client.model.PVZModelLayers;
import com.hungteen.pvz.client.model.bullet.CommonBulletModel;
import com.hungteen.pvz.common.entity.bullet.PeaBullet;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.Util;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-01 15:29
 **/
public class PeaBulletRender extends CommonBulletRender<PeaBullet> {

    private static final ResourceLocation COMMON = Util.texture("entity/bullet/pea_bullet.png");

    public PeaBulletRender(EntityRendererProvider.Context context) {
        super(context, new CommonBulletModel(context.bakeLayer(PVZModelLayers.PEA_BULLET)));
    }

    @Override
    public ResourceLocation getTextureLocation(PeaBullet bullet) {
        return COMMON;
    }

    @Override
    protected float getScaleByEntity(PeaBullet entity) {
        return 0.5F;
    }
}
