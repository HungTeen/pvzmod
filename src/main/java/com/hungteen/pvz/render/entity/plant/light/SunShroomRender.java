package com.hungteen.pvz.render.entity.plant.light;

import com.hungteen.pvz.entity.plant.light.SunShroomEntity;
import com.hungteen.pvz.model.entity.plant.light.SunShroomModel;
import com.hungteen.pvz.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.render.layer.SunLightLayer;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SunShroomRender extends PVZPlantRender<SunShroomEntity>{
	
	public SunShroomRender(EntityRendererManager rendererManager) {
		super(rendererManager, new SunShroomModel(), 0.3f);
	}

	@Override
	protected void addPlantLayers() {
		super.addPlantLayers();
		this.addLayer(new SunLightLayer<>(this));
	}
	
	@Override
	protected float getScaleByEntity(SunShroomEntity entity) {
		float smallSize = 0.2f;
		float bigSize = 0.4f;
		return smallSize + entity.getGrowAnim() * (bigSize - smallSize) / entity.growAnimTo;
	}

	@Override
	public ResourceLocation getEntityTexture(SunShroomEntity entity) {
		return StringUtil.prefix("textures/entity/plant/light/sun_shroom.png");
	}
}
