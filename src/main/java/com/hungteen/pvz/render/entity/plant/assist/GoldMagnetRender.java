package com.hungteen.pvz.render.entity.plant.assist;

import com.hungteen.pvz.entity.plant.assist.GoldMagnetEntity;
import com.hungteen.pvz.model.entity.plant.assist.GoldMagnetModel;
import com.hungteen.pvz.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GoldMagnetRender extends PVZPlantRender<GoldMagnetEntity> {

	public GoldMagnetRender(EntityRendererManager rendererManager) {
		super(rendererManager, new GoldMagnetModel(), 0.3F);
	}

	@Override
	public float getScaleByEntity(GoldMagnetEntity entity) {
		return 1.2F;
	}

	@Override
	public ResourceLocation getTextureLocation(GoldMagnetEntity entity) {
		if(entity.getAttackTime() > 0) return StringUtil.prefix("textures/entity/plant/assist/gold_magnet2.png");
		return StringUtil.prefix("textures/entity/plant/assist/gold_magnet.png");
	}

}
