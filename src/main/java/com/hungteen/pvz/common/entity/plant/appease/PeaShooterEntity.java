package com.hungteen.pvz.common.entity.plant.appease;

import java.util.Optional;

import com.hungteen.pvz.common.entity.ai.goal.target.PVZNearestTargetGoal;
import com.hungteen.pvz.common.entity.bullet.itembullet.PeaEntity;
import com.hungteen.pvz.common.entity.plant.base.PlantShooterEntity;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.ShootTypes;

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
		this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, getShootRange(), 1));
	}
	
	@Override
	public void shootBullet() {
		if(this.isPlantInSuperMode()) {
			final int cnt = this.getSuperShootCount();
			for(int i = 0; i < cnt; ++ i) {
				final float offset = MathUtil.getRandomFloat(getRandom()) / 5;
				final float offsetH = MathUtil.getRandomFloat(getRandom()) / 5;
				this.performShoot(SHOOT_OFFSET, offset, offsetH, i == 0, ShootTypes.FORWARD);
			}
		} else {
			this.performShoot(SHOOT_OFFSET, 0, 0, true, ShootTypes.FORWARD);
		}
	}
	
	/**
	 * shoot pea with offsets.
	 * {@link #shootBullet()}
	 */
	public void performShoot(double forwardOffset, double rightOffset, double heightOffset, boolean needSound, ShootTypes type) {
		Optional.ofNullable(this.getTarget()).ifPresent(target -> {
			final Vector3d vec = EntityUtil.getNormalisedVector2d(this, target);
            final double deltaY = this.getDimensions(getPose()).height * 0.7F + heightOffset;
            final double deltaX = forwardOffset * vec.x + rightOffset * vec.z;
            final double deltaZ = forwardOffset * vec.z + rightOffset * vec.x;
            final PeaEntity pea = new PeaEntity(this.level, this, this.getShootType(), this.getShootState());
            pea.setPos(this.getX() + deltaX, this.getY() + deltaY, this.getZ() + deltaZ);
            switch(type) {//choose shoot position. 
            case FORWARD:{
            	pea.shootPea(vec.x, target.getY() + target.getBbHeight() - pea.getY(), vec.z, this.getBulletSpeed());
            	break;
            }
            default:break;
            }
            if(needSound) {
            	EntityUtil.playSound(this, this.getShootSound());
            }
            pea.summonByOwner(this);
            this.level.addFreshEntity(pea);
		});
	}
	
	/**
	 * get how many peas need shoot per tick, when super.
	 */
	public int getSuperShootCount() {
		final int min = this.isPlantInStage(3) ? 2 : 1;
		final int max = this.isPlantInStage(1) ? 2 : 3;
		return MathUtil.getRandomMinMax(getRandom(), min, max);
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
		return 100;
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.PEA_SHOOTER;
	}

}
