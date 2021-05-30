package com.hungteen.pvz.client.render.entity.misc.bowling;

import com.hungteen.pvz.client.render.entity.plant.explosion.ExplodeONutRender;
import com.hungteen.pvz.common.entity.misc.bowling.ExplosionBowlingEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ExplosionBowlingRender extends AbstractbowlingRender<ExplosionBowlingEntity> {

	public ExplosionBowlingRender(EntityRendererManager renderManager) {
		super(renderManager);
	}
	
	@Override
	public ResourceLocation getTextureLocation(ExplosionBowlingEntity entity) {
		return ExplodeONutRender.TEXTURE1;
	}

}
