package com.hungteen.pvz.client.render.entity.plant.appease;

import com.hungteen.pvz.client.model.entity.plant.appease.RepeaterModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.appease.RepeaterEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;

public class RepeaterRender extends PVZPlantRender<RepeaterEntity>{

	public RepeaterRender(EntityRendererManager rendererManager) {
		super(rendererManager, new RepeaterModel(), 0.4f);
	}

}
