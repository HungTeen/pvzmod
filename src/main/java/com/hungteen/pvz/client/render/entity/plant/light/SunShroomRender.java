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
		float smallSize = 0.2f;
		float bigSize = 0.4f;
		return smallSize + entity.getGrowAnim() * (bigSize - smallSize) / entity.growAnimTo;
	}

}
