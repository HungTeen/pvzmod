package com.hungteen.pvz.render.entity.bullet;

import com.hungteen.pvz.entity.bullet.PotatoEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PotatoRender extends BulletRender<PotatoEntity>{

	public PotatoRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn);
	}

	@Override
	protected float getScaleByEntity(PotatoEntity entity) {
		return 1f;
	}
}