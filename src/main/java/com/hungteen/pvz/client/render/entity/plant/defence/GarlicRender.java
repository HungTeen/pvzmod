package com.hungteen.pvz.client.render.entity.plant.defence;

import com.hungteen.pvz.client.model.entity.plant.defence.GarlicModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.defence.GarlicEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GarlicRender extends PVZPlantRender<GarlicEntity> {

	private static final ResourceLocation GARLIC_TEX = StringUtil.prefix("textures/entity/plant/defence/garlic.png");
	
	public GarlicRender(EntityRendererManager rendererManager) {
		super(rendererManager, new GarlicModel(), 0.5F);
	}

	@Override
	public float getScaleByEntity(GarlicEntity entity) {
		return 0.9F;
	}

	@Override
	public ResourceLocation getTextureLocation(GarlicEntity entity) {
		return GARLIC_TEX;
	}

}
