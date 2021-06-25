package com.hungteen.pvz.client.render.entity.plant.enforce;

import com.hungteen.pvz.client.model.entity.plant.enforce.TangleKelpModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.enforce.TangleKelpEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TangleKelpRender extends PVZPlantRender<TangleKelpEntity>{

	public TangleKelpRender(EntityRendererManager rendererManager) {
		super(rendererManager, new TangleKelpModel(), 0.3f);
	}

}
