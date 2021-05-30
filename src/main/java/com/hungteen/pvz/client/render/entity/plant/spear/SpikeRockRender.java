package com.hungteen.pvz.client.render.entity.plant.spear;

import com.hungteen.pvz.client.model.entity.plant.spear.SpikeRockModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.spear.SpikeRockEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SpikeRockRender extends PVZPlantRender<SpikeRockEntity>{

	public SpikeRockRender(EntityRendererManager rendererManager) {
		super(rendererManager, new SpikeRockModel(), 0.4f);
	}

	@Override
	public float getScaleByEntity(SpikeRockEntity entity) {
		return 0.5f;
	}

	@Override
	public ResourceLocation getTextureLocation(SpikeRockEntity entity) {
		return StringUtil.prefix("textures/entity/plant/spear/spike_rock.png");
	}

}
