package com.hungteen.pvz.render.entity.plant.appease;

import com.hungteen.pvz.entity.plant.appease.StarFruitEntity;
import com.hungteen.pvz.model.entity.plant.appease.StarFruitModel;
import com.hungteen.pvz.render.entity.plant.PVZPlantRender;
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
	public ResourceLocation getEntityTexture(StarFruitEntity entity) {
		return StringUtil.prefix("textures/entity/plant/appease/star_fruit.png");
	}

}
