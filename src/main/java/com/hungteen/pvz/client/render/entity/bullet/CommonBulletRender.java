package com.hungteen.pvz.client.render.entity.bullet;

import com.hungteen.pvz.client.render.entity.PVZEntityRender;
import com.hungteen.pvz.common.entity.bullet.PVZProjectile;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-01 15:28
 **/
public abstract class CommonBulletRender<T extends PVZProjectile> extends PVZEntityRender<T> {

    public CommonBulletRender(EntityRendererProvider.Context context, EntityModel m) {
        super(context, m);
    }

}
