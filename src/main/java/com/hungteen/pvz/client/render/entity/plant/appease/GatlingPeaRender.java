package com.hungteen.pvz.client.render.entity.plant.appease;

import com.hungteen.pvz.client.model.entity.plant.appease.GatlingPeaModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.appease.GatlingPeaEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GatlingPeaRender extends PVZPlantRender<GatlingPeaEntity>{

	public GatlingPeaRender(EntityRendererManager rendererManager) {
		super(rendererManager, new GatlingPeaModel(), 0.4f);
	}

}
