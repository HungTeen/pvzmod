package com.hungteen.pvz.client.render.entity.plant.light;

import com.hungteen.pvz.client.model.entity.plant.light.SunShroomModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.light.SunShroomEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SunShroomRender extends PVZPlantRender<SunShroomEntity>{
	
	public SunShroomRender(EntityRendererManager rendererManager) {
		super(rendererManager, new SunShroomModel(), 0.3f);
	}

	@Override
	public float getScaleByEntity(SunShroomEntity entity) {
		final float smallSize = 0.2F;
		final float bigSize = 0.35F;
//		final float hugeSize = 0.6F;
		final int T = SunShroomEntity.GROW_ANIM_CD;
		final int tick = entity.getExistTick();
		if(tick <= 20) {
			return smallSize;
		}
		if(entity.isInGrowStage(3)) {
			
		}
		if(entity.isInGrowStage(2)) {
			return bigSize;
		}
		if(entity.isInGrowStage(1)) {
			final int now = tick + T - SunShroomEntity.GROW_CD;
			return now >= 0 ? smallSize + (bigSize - smallSize) * now / T : smallSize;
		}
		return smallSize;
	}

}
