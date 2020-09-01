package com.hungteen.pvz.entity.zombie.poolday;

import com.hungteen.pvz.entity.zombie.PVZZombieToolBase;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.world.World;

public class DuckyTubeEntity extends PVZZombieToolBase{

	public DuckyTubeEntity(EntityType<? extends MobEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new SwimGoal(this));
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
	}
	
	@Override
	public void baseTick() {
		super.baseTick();
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return new EntitySize(0.2f,0.2f,false);
	}
	
	@Override
	public double getMountedYOffset() {
		return -0.8f;
	}
	
	@Override
	protected float getWaterSlowDown() {
		return 0.9f;
	}
	
	@Override
	public boolean isPushedByWater() {
		return false;
	}
	
	@Override
	public boolean canBeRiddenInWater() {
		return true;
	}
	
	@Override
	public boolean canBeRiddenInWater(Entity rider) {
		return true;
	}
	
}
