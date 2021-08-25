package com.hungteen.pvz.common.entity.plant.spear;

import java.util.HashSet;

import com.hungteen.pvz.common.core.PlantType;
import com.hungteen.pvz.common.entity.ai.goal.target.PVZGlobalTargetGoal;
import com.hungteen.pvz.common.entity.bullet.AbstractBulletEntity;
import com.hungteen.pvz.common.entity.bullet.ThornEntity;
import com.hungteen.pvz.common.entity.bullet.ThornEntity.ThornStates;
import com.hungteen.pvz.common.entity.bullet.ThornEntity.ThornTypes;
import com.hungteen.pvz.common.entity.plant.base.PlantShooterEntity;
import com.hungteen.pvz.common.entity.zombie.pool.BalloonZombieEntity;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.PlantUtil;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

public class CatTailEntity extends PlantShooterEntity {

	public HashSet<ThornEntity> thorns = new HashSet<>();
	private int powerCount = 0;
	private int powerTick = 0;
	private final int POWER_CD = 200;
	
	public CatTailEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(2, new SwimGoal(this));
	}
	
	@Override
	protected void addTargetGoals() {
		this.targetSelector.addGoal(0, new PVZGlobalTargetGoal(this, true, false, getShootRange(), getShootRange()));
	}

	@Override
	public void normalPlantTick() {
		super.normalPlantTick();
		if(! level.isClientSide) {
			if(this.powerTick > 0) {
				-- this.powerTick;
			}
			HashSet<ThornEntity> tmp = new HashSet<>();
			thorns.forEach(thorn -> {
				if(EntityUtil.isEntityValid(thorn) && thorn.isInControl()) {
					thorn.setThornTarget(this.getTarget());
					tmp.add(thorn);
				}
			});
			this.thorns.clear();
			this.thorns = tmp;
		}
	}
	
	@Override
	public void shootBullet() {
		this.performShoot(0, 0, 0.2, true, FORWARD_SHOOT_ANGLE);
	}
	
	@Override
	protected AbstractBulletEntity createBullet() {
		final ThornEntity thorn = new ThornEntity(level, this);
		thorn.setThornType(this.isPlantInSuperMode() ? ThornTypes.AUTO : ThornTypes.GUIDE);
		thorn.setThornState(this.getThornShootState());
		thorn.setExtraHitCount(this.getExtraAttackCount());
		this.thorns.add(thorn);
		return thorn;
	}
	
	@Override
	public boolean canPlantTarget(Entity entity) {
		if(entity instanceof BalloonZombieEntity) {
			return true;
		}
		return super.canPlantTarget(entity);
	}
	
	@Override
	public void startSuperMode(boolean first) {
		super.startSuperMode(first);
		this.powerCount += this.getPowerThornCount();
	}
	
	protected ThornStates getThornShootState() {
		if(this.powerCount > 0 && this.powerTick == 0) {
			this.powerTick = this.POWER_CD;
			-- this.powerCount;
			return ThornStates.POWER;
		}
		return ThornStates.NORMAL;
	}
	
	@Override
	protected boolean canAttackNow() {
		return this.getAttackTime() > 0 && this.getAttackTime() % getAnimCD() == 0;
	}
	
	@Override
	public boolean checkY(Entity target) {
		return true;
	}
	
	public int getPowerThornCount() {
		return this.getThreeStage(1, 2, 3);
	}
	
	public int getExtraAttackCount() {
		return MathUtil.getProgressByDif(5, 1, this.getPlantLvl(), PlantUtil.MAX_PLANT_LEVEL, 1, 4);
	}
	
	@Override
	public float getAttackDamage() {
		return this.getAverageProgress(2F, 6F);
	}
	
	public int getAnimCD() {
		return 8;
	}
	
	@Override
	public void startShootAttack() {
		this.setAttackTime(this.getAnimCD() * 2);
	}

	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.8F, 1.4F);
	}
	
	@Override
	public float getShootRange() {
		return 40;
	}
	
	@Override
	public float getBulletSpeed() {
		return 0F;
	}
	
	@Override
	public int getSuperTimeLength() {
		return 40;
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("power_thorn_count")){
			this.powerCount = compound.getInt("power_thorn_count");
		}
		if(compound.contains("power_shoot_tick")){
			this.powerTick = compound.getInt("power_shoot_tick");
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("power_thorn_count", this.powerCount);
		compound.putInt("power_shoot_tick", this.powerTick);
	}
	
	@Override
	public PlantType getPlantType() {
		return PVZPlants.CAT_TAIL;
	}
	
}
