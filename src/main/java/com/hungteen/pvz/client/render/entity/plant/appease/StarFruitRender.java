package com.hungteen.pvz.client.render.entity.plant.appease;

import com.hungteen.pvz.client.model.entity.plant.appease.StarFruitModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.appease.StarFruitEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StarFruitRender extends PVZPlantRender<StarFruitEntity> {

	public StarFruitRender(EntityRendererManager rendererManager) {
		super(rendererManager, new StarFruitModel(), 0.45F);
	}
	
	@Override
	public float getScaleByEntity(StarFruitEntity entity) {
		return 0.82F;
	}

	@Override
	public ResourceLocation getTextureLocation(StarFruitEntity entity) {
		return StringUtil.prefix("textures/entity/plant/appease/star_fruit.png");
	}

}
