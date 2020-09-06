package com.hungteen.pvz.render.entity.plant.defence;

import com.hungteen.pvz.entity.plant.defence.WaterGuardEntity;
import com.hungteen.pvz.model.entity.plant.defence.WaterGuardModel;
import com.hungteen.pvz.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WaterGuardRender extends PVZPlantRender<WaterGuardEntity>{

	public WaterGuardRender(EntityRendererManager rendererManager) {
		super(rendererManager, new WaterGuardModel(), 0.3f);
	}

	@Override
	protected float getScaleByEntity(WaterGuardEntity entity) {
		return 0.8f;
	}

	@Override
	public ResourceLocation getEntityTexture(WaterGuardEntity entity) {
		return StringUtil.prefix("textures/entity/plant/defence/water_guard.png");
	}

}
