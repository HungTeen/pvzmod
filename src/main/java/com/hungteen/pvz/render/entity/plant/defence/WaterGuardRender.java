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
		super(rendererManager, new WaterGuardModel(), 0f);
	}

	@Override
	public float getScaleByEntity(WaterGuardEntity entity) {
		return 0.8f;
	}

	@Override
	public ResourceLocation getTextureLocation(WaterGuardEntity entity) {
		if(entity.getDefenceLife() > 0) return StringUtil.prefix("textures/entity/plant/defence/water_guard2.png");
		return StringUtil.prefix("textures/entity/plant/defence/water_guard.png");
	}

}
