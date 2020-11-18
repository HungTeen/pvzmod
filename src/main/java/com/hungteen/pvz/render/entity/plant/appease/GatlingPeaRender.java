package com.hungteen.pvz.render.entity.plant.appease;

import com.hungteen.pvz.entity.plant.appease.GatlingPeaEntity;
import com.hungteen.pvz.model.entity.plant.appease.GatlingPeaModel;
import com.hungteen.pvz.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GatlingPeaRender extends PVZPlantRender<GatlingPeaEntity>{

	public GatlingPeaRender(EntityRendererManager rendererManager) {
		super(rendererManager, new GatlingPeaModel(), 0.4f);
	}

	@Override
	public float getScaleByEntity(GatlingPeaEntity entity) {
		return 0.5f;
	}

	@Override
	public ResourceLocation getEntityTexture(GatlingPeaEntity entity) {
		return StringUtil.prefix("textures/entity/plant/appease/gatling_pea.png");
	}

}
