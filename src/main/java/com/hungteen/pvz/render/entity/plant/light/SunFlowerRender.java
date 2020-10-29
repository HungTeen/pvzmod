package com.hungteen.pvz.render.entity.plant.light;

import com.hungteen.pvz.entity.plant.light.SunFlowerEntity;
import com.hungteen.pvz.model.entity.plant.light.SunFlowerModel;
import com.hungteen.pvz.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.render.layer.SunLightLayer;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SunFlowerRender extends PVZPlantRender<SunFlowerEntity>{
	
	public SunFlowerRender(EntityRendererManager rendererManager) {
		super(rendererManager, new SunFlowerModel(), 0.4f);
	}

	@Override
	protected void addPlantLayers() {
		super.addPlantLayers();
		this.addLayer(new SunLightLayer<>(this));
	}
	
	@Override
	public float getScaleByEntity(SunFlowerEntity entity) {
		return 0.5f;
	}

	@Override
	public ResourceLocation getEntityTexture(SunFlowerEntity entity) {
		return StringUtil.prefix("textures/entity/plant/light/sun_flower.png");
	}
}
