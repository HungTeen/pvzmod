package com.hungteen.pvz.client.render.entity.plant.explosion;

import com.hungteen.pvz.client.model.entity.plant.explosion.BambooLordModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.explosion.BambooLordEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BambooLordRender extends PVZPlantRender<BambooLordEntity> {

	public BambooLordRender(EntityRendererManager rendererManager) {
		super(rendererManager, new BambooLordModel(), 0.4F);
	}

}
