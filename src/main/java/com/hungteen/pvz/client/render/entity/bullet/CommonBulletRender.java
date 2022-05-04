package com.hungteen.pvz.client.render.entity.bullet;

import com.hungteen.pvz.client.model.PVZModelLayers;
import com.hungteen.pvz.client.model.entity.bullet.CommonBulletModel;
import com.hungteen.pvz.client.render.entity.PVZEntityRender;
import com.hungteen.pvz.common.entity.bullet.PVZProjectile;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-01 15:28
 **/
public abstract class CommonBulletRender<T extends PVZProjectile> extends PVZEntityRender<T> {

    public CommonBulletRender(EntityRendererProvider.Context context) {
        this(context, new CommonBulletModel<>(context.bakeLayer(PVZModelLayers.COMMON_BULLET)));
    }

    public CommonBulletRender(EntityRendererProvider.Context context, EntityModel m) {
        super(context, m);
    }

}
