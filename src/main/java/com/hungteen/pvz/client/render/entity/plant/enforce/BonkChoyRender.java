package com.hungteen.pvz.client.render.entity.plant.enforce;

import com.hungteen.pvz.client.model.entity.plant.enforce.BonkChoyModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.enforce.BonkChoyEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;

public class BonkChoyRender extends PVZPlantRender<BonkChoyEntity> {

	public BonkChoyRender(EntityRendererManager rendererManager) {
		super(rendererManager, new BonkChoyModel(), 0.4F);
	}

}
