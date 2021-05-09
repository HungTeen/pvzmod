package com.hungteen.pvz.render.entity.plant.appease;

import com.hungteen.pvz.entity.plant.appease.AngelStarFruitEntity;
import com.hungteen.pvz.model.entity.plant.appease.AngelStarFruitModel;
import com.hungteen.pvz.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AngelStarFruitRender extends PVZPlantRender<AngelStarFruitEntity> {

	public AngelStarFruitRender(EntityRendererManager rendererManager) {
		super(rendererManager, new AngelStarFruitModel(), 0.44F);
	}
	
	@Override
	public float getScaleByEntity(AngelStarFruitEntity entity) {
		return 0.8F;
	}

	@Override
	public ResourceLocation getTextureLocation(AngelStarFruitEntity entity) {
		return StringUtil.prefix("textures/entity/plant/appease/angel_star_fruit.png");
	}

}
