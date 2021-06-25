package com.hungteen.pvz.client.render.entity.plant.ice;

import com.hungteen.pvz.client.model.entity.plant.ice.SnowPeaModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.ice.SnowPeaEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SnowPeaRender extends PVZPlantRender<SnowPeaEntity>{

	public SnowPeaRender(EntityRendererManager rendererManager) {
		super(rendererManager, new SnowPeaModel(), 0.4f);
	}

}
