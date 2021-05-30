package com.hungteen.pvz.common.entity.misc;

import com.hungteen.pvz.common.entity.zombie.PVZZombieToolBase;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.world.World;

public class DuckyTubeEntity extends PVZZombieToolBase {

//	private static final float UP_SPEED = 0.05f;

	public DuckyTubeEntity(EntityType<? extends MobEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new SwimGoal(this));
	}

//	@Override
//	protected void func_110147_ax() {
//		super.func_110147_ax();
//		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.LITTLE_FAST);
//	}

//	@Override
//	public void livingTick() {
//		super.livingTick();
//		if(!world.isRemote) {//swim up
//			if(this.isInWater() && this.getSubmergedHeight() > this.getEyeHeight()){
//				Vec3d v = this.getMotion();
//				this.setMotion(v.getX(), UP_SPEED, v.getZ());
//			}
//		}
//	}

	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return new EntitySize(0.2f, 0.2f, false);
	}

	@Override
	public double getPassengersRidingOffset() {
		return -0.7f;
	}

	@Override
	protected float getWaterSlowDown() {
		return 0.93f;
	}

	@Override
	public boolean isPushedByFluid() {
		return false;
	}

	@Override
	public boolean rideableUnderWater() {
		return true;
	}

	@Override
	public boolean canBeRiddenInWater(Entity rider) {
		return true;
	}

}
