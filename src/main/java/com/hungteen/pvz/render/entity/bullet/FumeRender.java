package com.hungteen.pvz.render.entity.bullet;

import com.hungteen.pvz.entity.bullet.FumeEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FumeRender extends BulletRender<FumeEntity>{

	public FumeRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn);
	}

	@Override
	protected float getScaleByEntity(FumeEntity entity) {
		return 1f;
	}

}
