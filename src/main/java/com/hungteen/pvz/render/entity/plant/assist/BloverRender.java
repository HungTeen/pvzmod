package com.hungteen.pvz.render.entity.plant.assist;

import com.hungteen.pvz.entity.plant.assist.BloverEntity;
import com.hungteen.pvz.model.entity.plant.assist.BloverModel;
import com.hungteen.pvz.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BloverRender extends PVZPlantRender<BloverEntity> {

	public BloverRender(EntityRendererManager rendererManager) {
		super(rendererManager, new BloverModel(), 0.4F);
	}

	@Override
	public float getScaleByEntity(BloverEntity entity) {
		return 1.2F + entity.getLiveTick() * 0.3F / entity.getReadyTime();
	}

	@Override
	public ResourceLocation getEntityTexture(BloverEntity entity) {
		return StringUtil.prefix("textures/entity/plant/assist/blover.png");
	}

}
