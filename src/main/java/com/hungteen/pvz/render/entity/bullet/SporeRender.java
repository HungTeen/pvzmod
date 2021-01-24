package com.hungteen.pvz.render.entity.bullet;

import com.hungteen.pvz.entity.bullet.itembullet.SporeEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SporeRender extends BulletRender<SporeEntity>{

	public SporeRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn);
	}

	@Override
	protected float getScaleByEntity(SporeEntity entity) {
		return 1f;
	}

}
