package com.hungteen.pvz.client.render.entity.plant.magic;

import com.hungteen.pvz.client.model.entity.plant.magic.MariGoldModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.magic.MariGoldEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MariGoldRender extends PVZPlantRender<MariGoldEntity>{

	public MariGoldRender(EntityRendererManager rendererManager) {
		super(rendererManager, new MariGoldModel<MariGoldEntity>(), 0.45f);
	}
	
	@Override
	public float getScaleByEntity(MariGoldEntity entity) {
		return 0.5f;
	}

	@Override
	public ResourceLocation getTextureLocation(MariGoldEntity entity) {
		return StringUtil.prefix("textures/entity/plant/magic/marigold.png");
	}

}
