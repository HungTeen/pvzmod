package com.hungteen.pvz.client.render.entity.plant.appease;

import com.hungteen.pvz.client.model.entity.plant.appease.ThreePeaterModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.appease.ThreePeaterEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ThreePeaterRender extends PVZPlantRender<ThreePeaterEntity>{

	public ThreePeaterRender(EntityRendererManager rendererManager) {
		super(rendererManager, new ThreePeaterModel(), 0.4f);
	}

}
