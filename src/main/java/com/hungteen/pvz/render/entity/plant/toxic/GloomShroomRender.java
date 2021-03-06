package com.hungteen.pvz.render.entity.plant.toxic;

import com.hungteen.pvz.entity.plant.toxic.GloomShroomEntity;
import com.hungteen.pvz.model.entity.plant.toxic.GloomShroomModel;
import com.hungteen.pvz.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GloomShroomRender extends PVZPlantRender<GloomShroomEntity> {

	public GloomShroomRender(EntityRendererManager rendererManager) {
		super(rendererManager, new GloomShroomModel(), 0.4F);
	}

	@Override
	public float getScaleByEntity(GloomShroomEntity entity) {
		return 1F;
	}

	@Override
	public ResourceLocation getTextureLocation(GloomShroomEntity entity) {
		return StringUtil.prefix("textures/entity/plant/toxic/gloom_shroom.png");
	}

}
