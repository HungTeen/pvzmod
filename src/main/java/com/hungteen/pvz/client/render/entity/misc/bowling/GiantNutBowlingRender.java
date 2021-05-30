package com.hungteen.pvz.client.render.entity.misc.bowling;

import com.hungteen.pvz.common.entity.misc.bowling.GiantNutBowlingEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GiantNutBowlingRender extends AbstractbowlingRender<GiantNutBowlingEntity> {

	public GiantNutBowlingRender(EntityRendererManager renderManager) {
		super(renderManager);
	}
	
	protected float getRenderSize(GiantNutBowlingEntity entity) {
		return 1.2F;
	}
	
}
