package com.hungteen.pvz.client.render.entity.plant.magic;

import com.hungteen.pvz.client.model.entity.plant.magic.StrangeCatModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.magic.StrangeCatEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;

public class StrangeCatRender extends PVZPlantRender<StrangeCatEntity> {

	public StrangeCatRender(EntityRendererManager rendererManager) {
		super(rendererManager, new StrangeCatModel(), 0.4F);
	}

}
