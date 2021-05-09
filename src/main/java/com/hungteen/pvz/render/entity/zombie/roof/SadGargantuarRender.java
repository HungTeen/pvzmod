package com.hungteen.pvz.render.entity.zombie.roof;

import com.hungteen.pvz.entity.zombie.roof.SadGargantuarEntity;
import com.hungteen.pvz.model.entity.zombie.roof.GargantuarModel;
import com.hungteen.pvz.render.entity.zombie.PVZZombieRender;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SadGargantuarRender extends PVZZombieRender<SadGargantuarEntity> {

	public SadGargantuarRender(EntityRendererManager rendererManager) {
		super(rendererManager, new GargantuarModel<>(), 1F);
	}

	@Override
	protected float getScaleByEntity(SadGargantuarEntity entity) {
		if(entity.isMiniZombie()) return 0.4F;
		return 0.8F;
	}

	@Override
	public ResourceLocation getTextureLocation(SadGargantuarEntity entity) {
		return GargantuarRender.GARGANTUAR_TEX;
	}

}
