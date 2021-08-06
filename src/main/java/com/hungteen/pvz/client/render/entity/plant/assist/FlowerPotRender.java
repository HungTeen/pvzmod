package com.hungteen.pvz.client.render.entity.plant.assist;

import com.hungteen.pvz.client.model.entity.plant.assist.FlowerPotModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.assist.FlowerPotEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FlowerPotRender extends PVZPlantRender<FlowerPotEntity> {

	public FlowerPotRender(EntityRendererManager rendererManager) {
		super(rendererManager, new FlowerPotModel(), 0.5F);
	}

}
