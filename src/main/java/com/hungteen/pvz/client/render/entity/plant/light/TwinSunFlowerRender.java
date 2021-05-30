package com.hungteen.pvz.client.render.entity.plant.light;

import com.hungteen.pvz.client.model.entity.plant.light.TwinSunFlowerModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.light.TwinSunFlowerEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TwinSunFlowerRender extends PVZPlantRender<TwinSunFlowerEntity> {

	public TwinSunFlowerRender(EntityRendererManager rendererManager) {
		super(rendererManager, new TwinSunFlowerModel(), 0.48f);
	}

	@Override
	public float getScaleByEntity(TwinSunFlowerEntity entity) {
		return 0.45f;
	}

	@Override
	public ResourceLocation getTextureLocation(TwinSunFlowerEntity entity) {
		return StringUtil.prefix("textures/entity/plant/light/sun_flower.png");
	}

}
