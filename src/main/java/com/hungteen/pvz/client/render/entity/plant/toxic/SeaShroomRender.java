package com.hungteen.pvz.client.render.entity.plant.toxic;

import com.hungteen.pvz.client.model.entity.plant.toxic.SeaShroomModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.toxic.SeaShroomEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SeaShroomRender extends PVZPlantRender<SeaShroomEntity>{

	public SeaShroomRender(EntityRendererManager rendererManager) {
		super(rendererManager, new SeaShroomModel(), 0f);
	}

	@Override
	public float getScaleByEntity(SeaShroomEntity entity) {
		return 0.6f;
	}

	@Override
	public ResourceLocation getTextureLocation(SeaShroomEntity entity) {
		return StringUtil.prefix("textures/entity/plant/toxic/sea_shroom.png");
	}

}
