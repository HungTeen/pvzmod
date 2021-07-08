package com.hungteen.pvz.client.render.entity.plant.toxic;

import com.hungteen.pvz.client.model.entity.plant.toxic.FumeShroomModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.base.PlantShooterEntity;
import com.hungteen.pvz.common.entity.plant.toxic.FumeShroomEntity;
import com.hungteen.pvz.utils.AnimationUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FumeShroomRender extends PVZPlantRender<FumeShroomEntity>{

	public FumeShroomRender(EntityRendererManager rendererManager) {
		super(rendererManager, new FumeShroomModel(), 0.7f);
	}

	@Override
	public float getScaleByEntity(FumeShroomEntity entity) {
		final float changeSize = 0.1F;
		final float size = super.getScaleByEntity(entity);
		if(entity.isPlantInSuperMode()) {
			final int T = PlantShooterEntity.SHOOT_ANIM_CD;
			final int tick = entity.getSuperTime() % T;
			return size + AnimationUtil.upDown(tick, T, changeSize);
		}
		final int T = PlantShooterEntity.SHOOT_ANIM_CD;
		final int tick = entity.getShootTick() + T - entity.getShootCD();
		return tick >= 0 ? size + AnimationUtil.upDown(tick, T, changeSize) : size;
	}
	
}
