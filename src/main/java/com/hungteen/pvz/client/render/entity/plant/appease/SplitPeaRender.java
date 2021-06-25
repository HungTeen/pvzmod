package com.hungteen.pvz.client.render.entity.plant.appease;

import com.hungteen.pvz.client.model.entity.plant.appease.SplitPeaModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.appease.SplitPeaEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SplitPeaRender extends PVZPlantRender<SplitPeaEntity>{

	public SplitPeaRender(EntityRendererManager rendererManager) {
		super(rendererManager, new SplitPeaModel(), 0.4f);
	}

}
