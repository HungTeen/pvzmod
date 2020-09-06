package com.hungteen.pvz.entity.zombie.poolday;

import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.world.World;

public class SnorkelZombieEntity extends PVZZombieEntity{

	public SnorkelZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.LITTLE_FAST);
	}

	@Override
	public void normalZombieTick() {
		super.normalZombieTick();
		if(this.isInWater()||this.isSwimming()) {
			this.setPose(Pose.SWIMMING);
		}else {
			this.setPose(Pose.STANDING);
		}
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		if(poseIn==Pose.SWIMMING) {
			return new EntitySize(0.3f,0.3f,false);
		}
		return new EntitySize(0.7f,1.9f,false);
	}
	
	@Override
	protected PathNavigator createNavigator(World worldIn) {
		if(this.isInWater()) return new SwimmerPathNavigator(this, worldIn);
		return new GroundPathNavigator(this, worldIn);
	}
	
	@Override
	protected float getWaterSlowDown() {
		return 0.93f;
	}
	
	@Override
	public float getLife() {
		return 20;
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.SNORKEL_ZOMBIE;
	}

}
