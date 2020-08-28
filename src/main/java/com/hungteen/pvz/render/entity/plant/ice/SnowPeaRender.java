package com.hungteen.pvz.render.entity.plant.ice;

import com.hungteen.pvz.entity.plant.ice.SnowPeaEntity;
import com.hungteen.pvz.model.entity.plant.ice.SnowPeaModel;
import com.hungteen.pvz.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SnowPeaRender extends PVZPlantRender<SnowPeaEntity>{

	public SnowPeaRender(EntityRendererManager rendererManager) {
		super(rendererManager, new SnowPeaModel(), 0.4f);
	}

	@Override
	protected float getScaleByEntity(SnowPeaEntity entity) {
		return 1f;
	}

	@Override
	public ResourceLocation getEntityTexture(SnowPeaEntity entity) {
		return StringUtil.prefix("textures/entity/plant/ice/snow_pea.png");
	}

}
