package com.hungteen.pvz.render.entity.plant.appease;

import com.hungteen.pvz.entity.plant.appease.ThreePeaterEntity;
import com.hungteen.pvz.model.entity.plant.appease.ThreePeaterModel;
import com.hungteen.pvz.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ThreePeaterRender extends PVZPlantRender<ThreePeaterEntity>{

	public ThreePeaterRender(EntityRendererManager rendererManager) {
		super(rendererManager, new ThreePeaterModel(), 0.4f);
	}

	@Override
	protected float getScaleByEntity(ThreePeaterEntity entity) {
		return 1;
	}

	@Override
	public ResourceLocation getEntityTexture(ThreePeaterEntity entity) {
		return StringUtil.prefix("textures/entity/plant/appease/three_peater.png");
	}

}
