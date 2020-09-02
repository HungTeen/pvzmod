package com.hungteen.pvz.render.entity.plant.enforce;

import com.hungteen.pvz.entity.plant.enforce.SquashEntity;
import com.hungteen.pvz.model.entity.plant.enforce.SquashModel;
import com.hungteen.pvz.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SquashRender extends PVZPlantRender<SquashEntity>{

	public SquashRender(EntityRendererManager rendererManager) {
		super(rendererManager, new SquashModel(), 0.5f);
	}

	@Override
	protected float getScaleByEntity(SquashEntity entity) {
		return 0.5f;
	}

	@Override
	public ResourceLocation getEntityTexture(SquashEntity entity) {
		return StringUtil.prefix("textures/entity/plant/enforce/squash.png");
	}

}
