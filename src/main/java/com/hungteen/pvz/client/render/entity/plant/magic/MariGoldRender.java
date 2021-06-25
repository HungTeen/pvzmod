package com.hungteen.pvz.client.render.entity.plant.magic;

import com.hungteen.pvz.client.model.entity.plant.magic.MariGoldModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.magic.MariGoldEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MariGoldRender extends PVZPlantRender<MariGoldEntity>{

	public MariGoldRender(EntityRendererManager rendererManager) {
		super(rendererManager, new MariGoldModel<MariGoldEntity>(), 0.45f);
	}

}
