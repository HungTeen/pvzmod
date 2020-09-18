package com.hungteen.pvz.entity.zombie;

import com.hungteen.pvz.entity.ai.BreakLilyPadGoal;
import com.hungteen.pvz.entity.ai.PVZNearestTargetGoal;
import com.hungteen.pvz.entity.ai.ZombieMeleeAttackGoal;
import com.hungteen.pvz.register.BlockRegister;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public abstract class PVZWaterZombieEntity extends PVZZombieEntity{

	private static final float UP_SPEED = 0.05f;
	protected PathNavigator waterNavigator;
	protected PathNavigator groundNavigator;

	public PVZWaterZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		setPathPriority(PathNodeType.WATER, 0);
		this.waterNavigator = new SwimmerPathNavigator(this, worldIn);
		this.groundNavigator = new GroundPathNavigator(this, worldIn);
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
		this.goalSelector.addGoal(7, new RandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(0, new ZombieMeleeAttackGoal(this, 1.0, false));
		this.goalSelector.addGoal(2, new BreakLilyPadGoal(BlockRegister.LILY_PAD.get(), this, 1, 3));
		this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, 80, 60));
	}
	
	@Override
	public void livingTick() {
		super.livingTick();
		if(this.ticksExisted%20==0) {
//		    System.out.println(this.navigator.getPath());
		}
		if(!world.isRemote) {//swim up
			if(this.isInWater() && this.getSubmergedHeight() > this.getEyeHeight()){
				Vec3d v = this.getMotion();
				this.setMotion(v.getX(), UP_SPEED, v.getZ());
			}
		}
	}
	
	@Override
	public boolean checkCanZombieTarget(Entity target) {
		if(super.checkCanZombieTarget(target)) {
			return !this.isInWater()||target.isInWater();
		}
		return false;
	}
	
	@Override
	public void updateSwimming() {
		if (!this.world.isRemote) {
			if (this.isServerWorld() && this.isInWater()) {
				this.navigator = this.waterNavigator;
				this.setSwimming(true);
			} else {
				this.navigator = this.groundNavigator;
				this.setSwimming(false);
			}
		}
	}
	
	@Override
	public boolean isPushedByWater() {
		return false;
	}
	
	@Override
	public boolean canBeFrozen() {
		return false;
	}

	@Override
	protected float getWaterSlowDown() {
		return 0.93f;
	}
	
}
