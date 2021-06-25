package com.hungteen.pvz.client.render.entity.plant.toxic;

import com.hungteen.pvz.client.model.entity.plant.toxic.PuffShroomModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.toxic.PuffShroomEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PuffShroomRender extends PVZPlantRender<PuffShroomEntity>{

	public PuffShroomRender(EntityRendererManager rendererManager) {
		super(rendererManager, new PuffShroomModel(), 0.3f);
	}

//	@Override
//	public float getScaleByEntity(PuffShroomEntity entity) {
//		int tick = entity.getLiveTick();
//		int max = entity.getMaxLiveTick();
//		float change = 0.2f;
//		float small = MathHelper.clamp(tick*change/max, 0, change);
//		return 0.6f - small;
//	}

}
