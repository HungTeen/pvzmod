package com.hungteen.pvz.client.render.entity.plant.magic;

import com.hungteen.pvz.client.model.entity.plant.magic.StrangeCatModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.magic.StrangeCatEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class StrangeCatRender extends PVZPlantRender<StrangeCatEntity> {

	public StrangeCatRender(EntityRendererManager rendererManager) {
		super(rendererManager, new StrangeCatModel(), 0.4F);
	}

	@Override
	public float getScaleByEntity(StrangeCatEntity entity) {
		return 0.18F;
	}

	@Override
	public ResourceLocation getTextureLocation(StrangeCatEntity entity) {
		return StringUtil.prefix("textures/entity/plant/magic/strange_cat.png");
	}

}
