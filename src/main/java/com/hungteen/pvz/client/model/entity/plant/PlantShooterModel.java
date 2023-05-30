package com.hungteen.pvz.client.model.entity.plant;

import java.util.Optional;

import com.hungteen.pvz.common.entity.plant.base.PlantShooterEntity;
import com.hungteen.pvz.utils.AnimationUtil;

import net.minecraft.client.renderer.model.ModelRenderer;

public abstract class PlantShooterModel<T extends PlantShooterEntity> extends PVZPlantModel<T> {

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.getHeadModel().ifPresent(m -> m.xRot = 0.1f);
		this.getBodyModel().ifPresent(m -> m.xRot = -0.1f);
		if(entity.isPlantInSuperMode()) {
			final int T = PlantShooterEntity.SHOOT_ANIM_CD;
			final int tick = entity.getSuperTime() % T;
			if(tick >= 0) {
				this.getHeadModel().ifPresent(m -> m.xRot = AnimationUtil.getUpDownUpDown(tick, T, getMaxRotAngle() / 5) + 0.1f);
				this.getBodyModel().ifPresent(m -> m.xRot = AnimationUtil.getUpDownUpDown(tick, T, - getMaxRotAngle() / 5) - 0.1f);
			} 
		} else {
			final int T = PlantShooterEntity.SHOOT_ANIM_CD;
			final int tick = entity.getShootTick() + T - entity.getShootCD();
			if(tick >= 0) {
				this.getHeadModel().ifPresent(m -> m.xRot = AnimationUtil.getUpDownUpDown(tick, T, - getMaxRotAngle()/2 ) + 0.1f);
				this.getBodyModel().ifPresent(m -> m.xRot = AnimationUtil.getUpDownUpDown(tick, T, getMaxRotAngle()) - 0.1f);
			} 
		}
	}
	
	public float getMaxRotAngle() {
		return 15F;
	}
	
	public Optional<ModelRenderer> getHeadModel(){
		return Optional.empty();
	}
	
	public Optional<ModelRenderer> getBodyModel(){
		return Optional.empty();
	}
	
}
