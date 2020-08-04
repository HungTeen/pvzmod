package com.hungteen.pvz.render.entity.plant.appease;

import com.hungteen.pvz.entity.plant.appease.PeaShooterEntity;
import com.hungteen.pvz.model.entity.plant.PeaShooterModel;
import com.hungteen.pvz.render.entity.plant.PlantRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PeaShooterRender extends PlantRender<PeaShooterEntity>{

	public PeaShooterRender(EntityRendererManager rendererManager) {
		super(rendererManager, new PeaShooterModel(),0.8f);
	}

	@Override
	protected float getScaleByEntity(PeaShooterEntity entity) {
		return 0.5f;
	}

	@Override
	public ResourceLocation getEntityTexture(PeaShooterEntity entity) {
		return StringUtil.prefix("textures/entity/plant/appease/pea_shooter.png");
	}

}
