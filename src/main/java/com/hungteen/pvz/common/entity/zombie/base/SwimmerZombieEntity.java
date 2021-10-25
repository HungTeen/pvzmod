package com.hungteen.pvz.common.entity.zombie.base;

import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.world.World;

public abstract class SwimmerZombieEntity extends PVZZombieEntity{

	private static final float UP_DISTANCE = 10;
	
	public SwimmerZombieEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		setPathfindingMalus(PathNodeType.WATER, 0);
	}
	
	@Override
	public void tick() {
		super.tick();
		if(!level.isClientSide) {
			if(this.isInWater()) {
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
			return new EntitySize(0.5f, 0.6f, false);
		}
		if(this.getPose() == Pose.SPIN_ATTACK) {
			return new EntitySize(0.7f, 1.4f, false);
		}else if(this.getPose() == Pose.SWIMMING) {
			return new EntitySize(0.7f, 0.9f, false);
		}
		return new EntitySize(0.7f, 1.9f, false);
	}
	
	@Override
	protected float getWaterSlowDown() {
		return 0.94f;
	}
	
}
