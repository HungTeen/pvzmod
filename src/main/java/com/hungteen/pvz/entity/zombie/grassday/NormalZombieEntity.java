package com.hungteen.pvz.entity.zombie.grassday;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.entity.zombie.poolday.DuckyTubeEntity;
import com.hungteen.pvz.register.EntityRegister;
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

public class NormalZombieEntity extends PVZZombieEntity {

	public NormalZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		if (this.getZombieType() == Type.BEARD) {
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ZombieUtil.NORMAL_DAMAGE);
		}
	}
	
	@Override
	public void normalZombieTick() {
		super.normalZombieTick();
		if(!this.world.isRemote && this.isAlive()) {
			if(this.getRidingEntity()==null && this.isInWater()) {
			    DuckyTubeEntity duck = EntityRegister.DUCKY_TUBE.get().create(world);
			    duck.setLocationAndAngles(this.getPosX(), this.getPosY(), this.getPosZ(), this.rotationYaw, 0.0F);
			    world.addEntity(duck);
			    this.startRiding(duck,true);
		    }
			if(this.getRidingEntity()!=null&&!this.isInWater()) {
				this.stopRiding();
			}
		}
	}

	@Override
	protected PathNavigator createNavigator(World worldIn) {
		if(this.isInWater()) {
			return new SwimmerPathNavigator(this, worldIn);
		}
		return new GroundPathNavigator(this, worldIn);
	}
	
	@Override
	protected Type getSpawnType() {
		int t = this.getRNG().nextInt(100);
		if (t <= PVZConfig.COMMON_CONFIG.EntitySettings.ZombieSuperChance.get()) {
			return Type.SUPER;
		}
		if (t == 99) {
			return Type.BEARD;
		}
		return Type.NORMAL;
	}

	@Override
	public float getLife() {
		return 20;
	}

	@Override
	public EntitySize getSize(Pose poseIn) {
		return new EntitySize(0.8f, 1.98f, false);
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.NORMAL_ZOMBIE;
	}
}
