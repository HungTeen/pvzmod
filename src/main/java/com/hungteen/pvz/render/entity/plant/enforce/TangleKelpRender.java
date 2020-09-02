package com.hungteen.pvz.render.entity.plant.enforce;

import com.hungteen.pvz.entity.plant.enforce.TangleKelpEntity;
import com.hungteen.pvz.model.entity.plant.enforce.TangleKelpModel;
import com.hungteen.pvz.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TangleKelpRender extends PVZPlantRender<TangleKelpEntity>{

	public TangleKelpRender(EntityRendererManager rendererManager) {
		super(rendererManager, new TangleKelpModel(), 0.3f);
	}

	@Override
	protected float getScaleByEntity(TangleKelpEntity entity) {
		return 0.6f;
	}

	@Override
	public ResourceLocation getEntityTexture(TangleKelpEntity entity) {
		return StringUtil.prefix("textures/entity/plant/enforce/tangle_kelp.png");
	}

}
