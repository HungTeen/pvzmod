package com.hungteen.pvz.render.entity.bullet;

import com.hungteen.pvz.entity.bullet.itembullet.MetalItemEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;

public class MetalItemRender extends BulletRender<MetalItemEntity> {

	public MetalItemRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn);
	}

	@Override
	protected float getScaleByEntity(MetalItemEntity entity) {
		return 1;
	}

}
