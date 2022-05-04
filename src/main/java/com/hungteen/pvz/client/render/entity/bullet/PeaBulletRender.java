package com.hungteen.pvz.client.render.entity.bullet;

import com.hungteen.pvz.common.entity.bullet.PVZProjectile.BulletStates;
import com.hungteen.pvz.common.entity.bullet.PeaBullet;
import com.hungteen.pvz.utils.Util;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-01 15:29
 **/
public class PeaBulletRender extends CommonBulletRender<PeaBullet> {

    private static final ResourceLocation COMMON = Util.texture("entity/bullet/pea/pea_bullet.png");
    private static final ResourceLocation SNOW_PEA = Util.texture("entity/bullet/pea/snow_pea_bullet.png");

    public PeaBulletRender(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(PeaBullet bullet) {
        return bullet.getBulletState() == BulletStates.NORMAL ? COMMON : SNOW_PEA;
    }

    @Override
    protected float getScaleByEntity(PeaBullet entity) {
        return 0.5F;
    }
}
