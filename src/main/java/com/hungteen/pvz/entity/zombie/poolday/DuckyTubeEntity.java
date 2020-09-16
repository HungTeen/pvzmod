package com.hungteen.pvz.entity.zombie.poolday;

import com.hungteen.pvz.entity.zombie.PVZZombieToolBase;
import com.hungteen.pvz.utils.ZombieUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class DuckyTubeEntity extends PVZZombieToolBase{

	public DuckyTubeEntity(EntityType<? extends MobEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.LITTLE_FAST);
	}
	
	@Override
	public void baseTick() {
		super.baseTick();
		if(!world.isRemote&&this.isInWater()) {
			this.setMotion(this.getMotion().getX(),0.05f,this.getMotion().getZ());
		}
	}
	
	@Override
	public boolean hasNoGravity() {
		return this.isInWater();
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return new EntitySize(0.2f,0.2f,false);
	}
	
	@Override
	public double getMountedYOffset() {
		return -0.7f;
	}
	
	@Override
	protected float getWaterSlowDown() {
		return 0.91f;
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
