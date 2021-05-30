package com.hungteen.pvz.client.render.entity.plant.appease;

import com.hungteen.pvz.client.model.entity.plant.appease.PeaShooterModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.appease.PeaShooterEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PeaShooterRender extends PVZPlantRender<PeaShooterEntity>{

	public PeaShooterRender(EntityRendererManager rendererManager) {
		super(rendererManager, new PeaShooterModel(),0.4f);
	}

	@Override
	public float getScaleByEntity(PeaShooterEntity entity) {
		return 1f;
	}

	@Override
	public ResourceLocation getTextureLocation(PeaShooterEntity entity) {
		return StringUtil.prefix("textures/entity/plant/appease/pea_shooter.png");
	}

}
