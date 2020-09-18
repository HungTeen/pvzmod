package com.hungteen.pvz.entity.zombie.grassday;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.entity.ai.PVZNearestTargetGoal;
import com.hungteen.pvz.entity.ai.ZombieMeleeAttackGoal;
import com.hungteen.pvz.entity.misc.DuckyTubeEntity;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class NormalZombieEntity extends PVZZombieEntity {

	public NormalZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		if (this.getZombieType() == Type.BEARD) {
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ZombieUtil.NORMAL_DAMAGE);
		}
	}
		
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
		this.goalSelector.addGoal(7, new RandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(0, new ZombieMeleeAttackGoal(this, 1.0, false));
		this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, 80, 60));
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
