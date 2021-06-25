package com.hungteen.pvz.client.render.entity.plant.explosion;

import com.hungteen.pvz.client.model.entity.plant.explosion.CobCannonModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.explosion.CobCannonEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CobCannonRender extends PVZPlantRender<CobCannonEntity> {
	
	public CobCannonRender(EntityRendererManager rendererManager) {
		super(rendererManager, new CobCannonModel(), 0.5F);
	}

}
