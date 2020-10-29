package com.hungteen.pvz.render.entity.plant.spear;

import com.hungteen.pvz.entity.plant.spear.SpikeWeedEntity;
import com.hungteen.pvz.model.entity.plant.spear.SpikeWeedModel;
import com.hungteen.pvz.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SpikeWeedRender extends PVZPlantRender<SpikeWeedEntity>{

	public SpikeWeedRender(EntityRendererManager rendererManager) {
		super(rendererManager, new SpikeWeedModel(), 0.4f);
	}

	@Override
	public float getScaleByEntity(SpikeWeedEntity entity) {
		return 0.5f;
	}

	@Override
	public ResourceLocation getEntityTexture(SpikeWeedEntity entity) {
		return StringUtil.prefix("textures/entity/plant/spear/spike_weed.png");
	}

}
