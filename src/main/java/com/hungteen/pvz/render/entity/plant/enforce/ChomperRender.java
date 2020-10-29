package com.hungteen.pvz.render.entity.plant.enforce;

import com.hungteen.pvz.entity.plant.enforce.ChomperEntity;
import com.hungteen.pvz.model.entity.plant.enforce.ChomperModel;
import com.hungteen.pvz.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChomperRender extends PVZPlantRender<ChomperEntity>{

	public ChomperRender(EntityRendererManager rendererManager) {
		super(rendererManager, new ChomperModel(), 0.5f);
	}

	@Override
	public float getScaleByEntity(ChomperEntity entity) {
		return 0.9f;
	}

	@Override
	public ResourceLocation getEntityTexture(ChomperEntity entity) {
		return StringUtil.prefix("textures/entity/plant/enforce/chomper.png");
	}

}
