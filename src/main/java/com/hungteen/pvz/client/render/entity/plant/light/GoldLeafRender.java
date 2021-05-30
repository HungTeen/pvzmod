package com.hungteen.pvz.client.render.entity.plant.light;

import com.hungteen.pvz.client.model.entity.plant.light.GoldLeafModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.light.GoldLeafEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GoldLeafRender extends PVZPlantRender<GoldLeafEntity> {

	public GoldLeafRender(EntityRendererManager rendererManager) {
		super(rendererManager, new GoldLeafModel(), 0.35F);
	}

	@Override
	public float getScaleByEntity(GoldLeafEntity entity) {
		return 0.8F;
	}

	@Override
	public ResourceLocation getTextureLocation(GoldLeafEntity entity) {
		return StringUtil.prefix("textures/entity/plant/light/gold_leaf.png");
	}

}
