package com.hungteen.pvz.render.entity.plant.light;

import com.hungteen.pvz.entity.plant.light.SunFlowerEntity;
import com.hungteen.pvz.model.entity.plant.light.SunFlowerModel;
import com.hungteen.pvz.render.entity.plant.PlantRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SunFlowerRender extends PlantRender<SunFlowerEntity>{

	
	public SunFlowerRender(EntityRendererManager rendererManager) {
		super(rendererManager, new SunFlowerModel(), 0.4f);
	}

	@Override
	protected float getScaleByEntity(SunFlowerEntity entity) {
		return 0.5f;
	}

	@Override
	public ResourceLocation getEntityTexture(SunFlowerEntity entity) {
		return StringUtil.prefix("textures/entity/plant/light/sun_flower.png");
	}
}
