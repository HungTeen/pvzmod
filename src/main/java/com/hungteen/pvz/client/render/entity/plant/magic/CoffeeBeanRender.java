package com.hungteen.pvz.client.render.entity.plant.magic;

import com.hungteen.pvz.client.model.entity.plant.magic.CoffeeBeanModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.magic.CoffeeBeanEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CoffeeBeanRender extends PVZPlantRender<CoffeeBeanEntity>{

	public CoffeeBeanRender(EntityRendererManager rendererManager) {
		super(rendererManager, new CoffeeBeanModel(), 0.3f);
	}

}
