package com.hungteen.pvz.client.render.entity.plant.ice;

import com.hungteen.pvz.client.model.entity.plant.ice.WinterMelonModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.ice.WinterMelonEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WinterMelonRender extends PVZPlantRender<WinterMelonEntity> {

	public WinterMelonRender(EntityRendererManager rendererManager) {
		super(rendererManager, new WinterMelonModel(), 0.5F);
	}

}
