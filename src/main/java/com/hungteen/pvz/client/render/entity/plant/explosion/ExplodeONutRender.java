package com.hungteen.pvz.client.render.entity.plant.explosion;

import com.hungteen.pvz.client.model.entity.plant.defence.WallNutModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.explosion.ExplodeONutEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ExplodeONutRender extends PVZPlantRender<ExplodeONutEntity> {

	public ExplodeONutRender(EntityRendererManager rendererManager) {
		super(rendererManager, new WallNutModel<ExplodeONutEntity>(), 0.6F);
	}

}
