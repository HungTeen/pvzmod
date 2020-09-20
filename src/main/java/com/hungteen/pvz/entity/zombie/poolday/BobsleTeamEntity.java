package com.hungteen.pvz.entity.zombie.poolday;

import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class BobsleTeamEntity extends PVZZombieEntity{

	public BobsleTeamEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	public Type getZombieType() {
		return Type.NORMAL;
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.VERY_FAST);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ZombieUtil.NORMAL_DAMAGE);
	}
	
	@Override
	public void livingTick() {
		super.livingTick();
		if(!world.isRemote) {
			if(!EntityUtil.isOnSnow(this)) {
				this.attackEntityFrom(DamageSource.DROWN, 10);
			}
		}
	}
	
	@Override
	protected void onZombieRemove() {
		super.onZombieRemove();
		if(!world.isRemote) {
			for(int i=0;i<4;i++) {
				BobsleZombieEntity zombie = EntityRegister.BOBSLE_ZOMBIE.get().create(world);
				zombie.setPosition(this.getPosX()+(this.getRNG().nextFloat()-0.5)*4, this.getPosY()+0.5f, this.getPosZ()+(this.getRNG().nextFloat()-0.5)*4);
				world.addEntity(zombie);
			}
		}
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(1.25f, 1.4f);
	}
	
	@Override
	public float getLife() {
		return 60;
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.BOBSLE_TEAM;
	}

}
