package com.hungteen.pvz.client.render.entity.plant.appease;

import com.hungteen.pvz.client.model.entity.plant.appease.StarFruitModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.appease.StarFruitEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StarFruitRender extends PVZPlantRender<StarFruitEntity> {

	public StarFruitRender(EntityRendererManager rendererManager) {
		super(rendererManager, new StarFruitModel(), 0.45F);
	}
	
}
