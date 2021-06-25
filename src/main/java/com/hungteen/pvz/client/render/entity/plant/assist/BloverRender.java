package com.hungteen.pvz.client.render.entity.plant.assist;

import com.hungteen.pvz.client.model.entity.plant.assist.BloverModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.assist.BloverEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BloverRender extends PVZPlantRender<BloverEntity> {

	public BloverRender(EntityRendererManager rendererManager) {
		super(rendererManager, new BloverModel(), 0.4F);
	}

//	@Override
//	public float getScaleByEntity(BloverEntity entity) {
//		return 1.2F + entity.getLiveTick() * 0.3F / entity.getReadyTime();
//	}

}
