package com.hungteen.pvz.client.render.entity.plant.appease;

import com.hungteen.pvz.client.model.entity.plant.appease.AngelStarFruitModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.appease.AngelStarFruitEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AngelStarFruitRender extends PVZPlantRender<AngelStarFruitEntity> {

	public AngelStarFruitRender(EntityRendererManager rendererManager) {
		super(rendererManager, new AngelStarFruitModel(), 0.44F);
	}
	
}
