package com.hungteen.pvz.render.entity.plant.appease;

import com.hungteen.pvz.entity.plant.appease.SplitPeaEntity;
import com.hungteen.pvz.model.entity.plant.appease.SplitPeaModel;
import com.hungteen.pvz.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SplitPeaRender extends PVZPlantRender<SplitPeaEntity>{

	public SplitPeaRender(EntityRendererManager rendererManager) {
		super(rendererManager, new SplitPeaModel(), 0.4f);
	}

	@Override
	public float getScaleByEntity(SplitPeaEntity entity) {
		return 1f;
	}

	@Override
	public ResourceLocation getTextureLocation(SplitPeaEntity entity) {
		return StringUtil.prefix("textures/entity/plant/appease/split_pea.png");
	}

}
