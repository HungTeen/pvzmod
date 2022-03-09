package com.hungteen.pvz.client.render.entity.misc.bowling;

import com.hungteen.pvz.common.entity.misc.bowling.ExplosionBowlingEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ExplosionBowlingRender extends AbstractbowlingRender<ExplosionBowlingEntity> {

	public ExplosionBowlingRender(EntityRendererManager renderManager) {
		super(renderManager);
	}
	
//	@Override
//	public ResourceLocation getTextureLocation(ExplosionBowlingEntity entity) {
//		return PVZPlants.EXPLODE_O_NUT.getRenderResource();
//	}

}
