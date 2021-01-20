package com.hungteen.pvz.render.entity.bullet;

import com.hungteen.pvz.entity.bullet.NutEntity;

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
