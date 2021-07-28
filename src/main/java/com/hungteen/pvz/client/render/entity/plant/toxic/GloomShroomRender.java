package com.hungteen.pvz.client.render.entity.plant.toxic;

import com.hungteen.pvz.client.model.entity.plant.toxic.GloomShroomModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.toxic.GloomShroomEntity;
import com.hungteen.pvz.utils.AnimationUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GloomShroomRender extends PVZPlantRender<GloomShroomEntity> {

	public GloomShroomRender(EntityRendererManager rendererManager) {
		super(rendererManager, new GloomShroomModel(), 0.4F);
	}
	
	@Override
	public float getScaleByEntity(GloomShroomEntity entity) {
		final float changeSize = 0.1F;
		final float size = super.getScaleByEntity(entity);
		if(entity.isPlantInSuperMode()) {
			final int T = entity.getShootCD();
			final int tick = entity.getSuperTime() % T;
			return size + AnimationUtil.upDown(tick, T, changeSize);
		}
		final int T = entity.getShootCD();
		final int tick = entity.getShootTick() + T - entity.getShootCD();
		return tick >= 0 ? size + AnimationUtil.upDown(tick, T, changeSize) : size;
	}

}
