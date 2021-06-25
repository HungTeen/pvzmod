package com.hungteen.pvz.client.render.entity.plant.spear;

import com.hungteen.pvz.client.model.entity.plant.spear.SpikeWeedModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.spear.SpikeWeedEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SpikeWeedRender extends PVZPlantRender<SpikeWeedEntity>{

	public SpikeWeedRender(EntityRendererManager rendererManager) {
		super(rendererManager, new SpikeWeedModel(), 0.4f);
	}

}
