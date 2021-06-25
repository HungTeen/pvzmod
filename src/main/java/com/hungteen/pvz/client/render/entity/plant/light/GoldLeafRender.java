package com.hungteen.pvz.client.render.entity.plant.light;

import com.hungteen.pvz.client.model.entity.plant.light.GoldLeafModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.light.GoldLeafEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GoldLeafRender extends PVZPlantRender<GoldLeafEntity> {

	public GoldLeafRender(EntityRendererManager rendererManager) {
		super(rendererManager, new GoldLeafModel(), 0.35F);
	}

}
