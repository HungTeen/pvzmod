package com.hungteen.pvz.client.render.entity.bullet;

import com.hungteen.pvz.common.entity.bullet.CabbageBullet;
import com.hungteen.pvz.common.entity.bullet.PVZProjectile;
import com.hungteen.pvz.utils.Util;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-05-04 11:45
 **/
public class CabbageBulletRender extends CommonBulletRender<CabbageBullet> {

    private static final ResourceLocation COMMON = Util.texture("entity/bullet/cabbage_bullet.png");

    public CabbageBulletRender(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected float getScaleByEntity(CabbageBullet entity) {
        return entity.getBulletType() == PVZProjectile.BulletTypes.NORMAL ? 0.5F : 1F;
    }

    @Override
    public ResourceLocation getTextureLocation(CabbageBullet p_114482_) {
        return COMMON;
    }
}
