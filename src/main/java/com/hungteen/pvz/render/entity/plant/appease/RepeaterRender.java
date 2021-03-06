package com.hungteen.pvz.render.entity.plant.appease;

import com.hungteen.pvz.entity.plant.appease.RepeaterEntity;
import com.hungteen.pvz.model.entity.plant.appease.RepeaterModel;
import com.hungteen.pvz.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class RepeaterRender extends PVZPlantRender<RepeaterEntity>{

	public RepeaterRender(EntityRendererManager rendererManager) {
		super(rendererManager, new RepeaterModel(), 0.4f);
	}

	@Override
	public float getScaleByEntity(RepeaterEntity entity) {
		return 1f;
	}

	@Override
	public ResourceLocation getTextureLocation(RepeaterEntity entity) {
		return StringUtil.prefix("textures/entity/plant/appease/repeater.png");
	}

}
