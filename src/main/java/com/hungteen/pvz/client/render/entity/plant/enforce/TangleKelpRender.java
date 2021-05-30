package com.hungteen.pvz.client.render.entity.plant.enforce;

import com.hungteen.pvz.client.model.entity.plant.enforce.TangleKelpModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.enforce.TangleKelpEntity;
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
	public float getScaleByEntity(TangleKelpEntity entity) {
		return 0.6f;
	}

	@Override
	public ResourceLocation getTextureLocation(TangleKelpEntity entity) {
		return StringUtil.prefix("textures/entity/plant/enforce/tangle_kelp.png");
	}

}
