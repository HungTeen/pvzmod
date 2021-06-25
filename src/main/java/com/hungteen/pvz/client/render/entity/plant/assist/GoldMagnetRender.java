package com.hungteen.pvz.client.render.entity.plant.assist;

import com.hungteen.pvz.client.model.entity.plant.assist.GoldMagnetModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.assist.GoldMagnetEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GoldMagnetRender extends PVZPlantRender<GoldMagnetEntity> {

	public GoldMagnetRender(EntityRendererManager rendererManager) {
		super(rendererManager, new GoldMagnetModel(), 0.3F);
	}

}
