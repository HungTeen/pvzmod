package com.hungteen.pvz.client.render.entity.plant.magic;

import com.hungteen.pvz.client.model.entity.plant.magic.HypnoShroomModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.magic.HypnoShroomEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;

public class HypnoShroomRender extends PVZPlantRender<HypnoShroomEntity>{

	public HypnoShroomRender(EntityRendererManager rendererManager) {
		super(rendererManager, new HypnoShroomModel(), 0.5f);
	}

}
