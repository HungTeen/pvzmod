package com.hungteen.pvz.client.render.entity.bullet;

import com.hungteen.pvz.common.entity.bullet.itembullet.NutEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;

public class NutRender extends BulletRender<NutEntity> {

	public NutRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn);
	}

	@Override
	protected float getScaleByEntity(NutEntity entity) {
		return 0.5F;
	}

}
