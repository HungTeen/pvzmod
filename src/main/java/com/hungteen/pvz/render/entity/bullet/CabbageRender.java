package com.hungteen.pvz.render.entity.bullet;

import com.hungteen.pvz.entity.bullet.itembullet.CabbageEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CabbageRender extends BulletRender<CabbageEntity> {

	public CabbageRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn);
	}

	@Override
	protected float getScaleByEntity(CabbageEntity entity) {
		return 1.4F;
	}

}
