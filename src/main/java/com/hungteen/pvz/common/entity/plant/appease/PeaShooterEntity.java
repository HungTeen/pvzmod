package com.hungteen.pvz.common.entity.plant.appease;

import java.util.Optional;

import com.hungteen.pvz.common.entity.ai.goal.target.PVZNearestTargetGoal;
import com.hungteen.pvz.common.entity.bullet.itembullet.PeaEntity;
import com.hungteen.pvz.common.entity.plant.base.PlantShooterEntity;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class PeaShooterEntity extends PlantShooterEntity{

	protected static final double SHOOT_OFFSET = 0.2D;//pea position offset
	
	public PeaShooterEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, 5, getShootRange(), 2, 0));
	}
	
	@Override
	public void shootBullet() {
		this.performShoot(SHOOT_OFFSET, 0);
	}
	
	public void performShoot(double forwardOffset, double rightOffset) {
		Optional.ofNullable(this.getTarget()).ifPresent(target -> {
			final Vector3d vec = EntityUtil.getNormalisedVector2d(this, target);
            final double y = this.getY() + this.getDimensions(getPose()).height * 0.7f;
            final double deltaX = forwardOffset * vec.x;
            final double deltaZ = forwardOffset * vec.z;
            PeaEntity pea = new PeaEntity(this.level, this, this.getShootType(), this.getShootState());
            pea.setPos(this.getX() + deltaX, y, this.getZ() + deltaZ);
            pea.shootPea(vec.x, target.getY() + target.getBbHeight() - y, vec.z, this.getBulletSpeed());      
            EntityUtil.playSound(this, this.getShootSound());
            this.level.addFreshEntity(pea);
		});
	}
	
	protected SoundEvent getShootSound() {
		return SoundEvents.SNOW_GOLEM_SHOOT;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.8F, 1.5F);
	}

	protected PeaEntity.Type getShootType(){
		return PeaEntity.Type.NORMAL;
	}
	
	protected PeaEntity.State getShootState(){
		return PeaEntity.State.NORMAL;
	}
	
	@Override
	public float getBulletSpeed() {
		return 1.5F;
	}

	@Override
	public void startShootAttack() {
		this.setAttackTime(1);
	}

	@Override
	public int getSuperTimeLength() {
		if(this.isPlantInStage(1)) return 100;
		if(this.isPlantInStage(2)) return 120;
		return 150;
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.PEA_SHOOTER;
	}

}
