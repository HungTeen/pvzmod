package com.hungteen.pvz.render.entity.plant.toxic;

import com.hungteen.pvz.entity.plant.toxic.PuffShroomEntity;
import com.hungteen.pvz.model.entity.plant.toxic.PuffShroomModel;
import com.hungteen.pvz.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PuffShroomRender extends PVZPlantRender<PuffShroomEntity>{

	public PuffShroomRender(EntityRendererManager rendererManager) {
		super(rendererManager, new PuffShroomModel(), 0.3f);
	}

	@Override
	protected float getScaleByEntity(PuffShroomEntity entity) {
		int tick = entity.getLiveTick();
		int max = entity.getMaxLiveTick();
		float change = 0.2f;
		float small = MathHelper.clamp(tick*change/max, 0, change);
		return 0.6f - small;
	}

	@Override
	public ResourceLocation getEntityTexture(PuffShroomEntity entity) {
		return StringUtil.prefix("textures/entity/plant/toxic/puff_shroom.png");
	}

}
