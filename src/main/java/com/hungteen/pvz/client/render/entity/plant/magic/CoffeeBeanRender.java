package com.hungteen.pvz.client.render.entity.plant.magic;

import com.hungteen.pvz.client.model.entity.plant.magic.CoffeeBeanModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.magic.CoffeeBeanEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CoffeeBeanRender extends PVZPlantRender<CoffeeBeanEntity>{

	public CoffeeBeanRender(EntityRendererManager rendererManager) {
		super(rendererManager, new CoffeeBeanModel(), 0.3f);
	}

	@Override
	public float getScaleByEntity(CoffeeBeanEntity entity) {
		return 0.6f;
	}

	@Override
	public ResourceLocation getTextureLocation(CoffeeBeanEntity entity) {
		return StringUtil.prefix("textures/entity/plant/magic/coffee_bean.png");
	}

}
