package com.hungteen.pvz.common.entity.plant.enforce;

import com.hungteen.pvz.common.entity.plant.base.PlantCloserEntity;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.world.World;

public class TangleKelpEntity extends PlantCloserEntity{

	public TangleKelpEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	public void focusOnTarget(LivingEntity target) {
		super.focusOnTarget(target);
		if(target.getVehicle() == null) {
			EntityUtil.playSound(this, SoundRegister.DRAG.get());
			this.getTarget().startRiding(this, true);
		}
		this.setDeltaMovement(0, - 0.4F, 0);
	}
	
	@Override
	public void performAttack(LivingEntity target) {
		target.hurt(PVZDamageSource.causeNormalDamage(this, this), this.getAttackDamage());
		this.remove();
	}

	@Override
	public void startSuperMode(boolean first) {
		super.startSuperMode(first);
		if(!level.isClientSide) {
			int cnt = this.getSuperCount();
			for(LivingEntity target : EntityUtil.getTargetableLivings(this, EntityUtil.getEntityAABB(this, 25, 3))) {
				TangleKelpEntity entity = EntityRegister.TANGLE_KELP.get().create(level);
				entity.setPos(target.getX(), target.getY(), target.getZ());
				PlantUtil.copyPlantData(entity, this);
				level.addFreshEntity(entity);
				entity.setTarget(target);
				target.startRiding(entity, true);
				EntityUtil.playSound(entity, SoundRegister.DRAG.get());
				if(-- cnt <= 0) {
					break;
				}
			}
		}
	}
	
	@Override
	public boolean canPlantTarget(Entity entity) {
		return super.canPlantTarget(entity) && (entity.getVehicle() == null || entity.getVehicle().is(this));
	}
	
	public float getAttackDamage(){
		return PlantUtil.getPlantAverageProgress(this, 150, 350);
	}
	
	public int getSuperCount(){
		return this.isPlantInStage(1) ? 3 : this.isPlantInStage(2) ? 4 : 5;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return new EntitySize(0.6f, 1f, false);
	}
	
	@Override
	public double getPassengersRidingOffset() {
		return 0;
	}
	
	@Override
	public boolean rideableUnderWater() {
		return true;
	}
	
	@Override
	public boolean canBeRiddenInWater(Entity rider) {
		return true;
	}
	
	@Override
	public boolean isNoGravity() {
		return this.isInWater();
	}
	
	@Override
	public int getSuperTimeLength() {
		return 20;
	}
	
	@Override
	protected boolean canBeImmuneToEnforce(Entity entity) {
		return true;
	}
	
	@Override
	public int getAttackCD() {
		return 20;
	}
	
	@Override
	public float getCloseHeight() {
		return 2;
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.TANGLE_KELP;
	}

}
