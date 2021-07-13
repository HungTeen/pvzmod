package com.hungteen.pvz.client.render.entity.plant.assist;

import com.hungteen.pvz.client.model.entity.plant.assist.LilyPadModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.assist.LilyPadEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LilyPadRender extends PVZPlantRender<LilyPadEntity> {

	public LilyPadRender(EntityRendererManager rendererManager) {
		super(rendererManager, new LilyPadModel(), 0.4F);
	}

}
