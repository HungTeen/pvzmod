package com.hungteen.pvz.render.entity.plant.enforce;

import com.hungteen.pvz.entity.plant.enforce.BonkChoyEntity;
import com.hungteen.pvz.model.entity.plant.enforce.BonkChoyModel;
import com.hungteen.pvz.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class BonkChoyRender extends PVZPlantRender<BonkChoyEntity> {

	private static final ResourceLocation BONK_CHOY_TEX = StringUtil.prefix("textures/entity/plant/enforce/bonk_choy.png");
	
	public BonkChoyRender(EntityRendererManager rendererManager) {
		super(rendererManager, new BonkChoyModel(), 0.4F);
	}

	@Override
	public float getScaleByEntity(BonkChoyEntity entity) {
		return 1F;
	}

	@Override
	public ResourceLocation getEntityTexture(BonkChoyEntity entity) {
		return BONK_CHOY_TEX;
	}

}
