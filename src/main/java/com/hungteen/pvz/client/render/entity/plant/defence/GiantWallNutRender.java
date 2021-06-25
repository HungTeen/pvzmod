package com.hungteen.pvz.client.render.entity.plant.defence;

import com.hungteen.pvz.client.model.entity.plant.defence.WallNutModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.defence.GiantWallNutEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GiantWallNutRender extends PVZPlantRender<GiantWallNutEntity> {

	public GiantWallNutRender(EntityRendererManager rendererManager) {
		super(rendererManager, new WallNutModel<>(), 2F);
	}

}
