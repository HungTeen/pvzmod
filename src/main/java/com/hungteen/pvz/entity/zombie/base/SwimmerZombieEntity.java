package com.hungteen.pvz.entity.zombie.base;

import com.hungteen.pvz.entity.zombie.PVZZombieEntity;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.world.World;

public abstract class SwimmerZombieEntity extends PVZZombieEntity{

	private static final float UP_DISTANCE = 10;
	
	public SwimmerZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		setPathfindingMalus(PathNodeType.WATER, 0);
	}
	
	@Override
	public void tick() {
		super.tick();
		if(!level.isClientSide) {
			if(this.isInWater() || this.isInLava()) {
				if(this.getTarget() != null && this.distanceToSqr(this.getTarget()) <= UP_DISTANCE) {
					this.setPose(Pose.SPIN_ATTACK);
				}else {
					this.setPose(Pose.SWIMMING);
				}
			}else {
				this.setPose(Pose.STANDING);
			}
		}
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		if(this.isMiniZombie()) {
			return new EntitySize(0.4f, 0.6f, false);
		}
		if(this.getPose() == Pose.SPIN_ATTACK) {
			return new EntitySize(0.7f, 1.2f, false);
		}else if(this.getPose() == Pose.SWIMMING) {
			return new EntitySize(0.7f, 0.5f, false);
		}
		return new EntitySize(0.7f, 1.9f, false);
	}
	
	@Override
	protected float getWaterSlowDown() {
		return 0.93f;
	}
	
	@Override
	public boolean isPushedByFluid() {
		return false;
	}

}
