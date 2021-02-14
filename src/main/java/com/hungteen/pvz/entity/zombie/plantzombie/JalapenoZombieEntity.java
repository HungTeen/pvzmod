package com.hungteen.pvz.entity.zombie.plantzombie;

import com.hungteen.pvz.entity.plant.flame.JalapenoEntity;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class JalapenoZombieEntity extends PVZZombieEntity {

	public JalapenoZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.LITTLE_FAST);
	}
	
	@Override
	public void onDeath(DamageSource cause) {
		if(! EntityUtil.isEntityCold(this) && ! EntityUtil.isEntityFrozen(this)) {
			this.startBomb();
		}
		super.onDeath(cause);
	}
	
	public void startBomb() {
		int range = this.getFireRange();
		for(int i = - range; i <= range; ++ i) {
			JalapenoEntity.clearSnowAndSpawnFlame(this, i, 0);
			JalapenoEntity.clearSnowAndSpawnFlame(this, 0, i);
		}
		if(! world.isRemote) {
			EntityUtil.playSound(this, SoundRegister.JALAPENO.get());
		}
		JalapenoEntity.fireMob(this, range, 0.5f);
		JalapenoEntity.fireMob(this, 0.5f, range);
		if(this.isCharmed()) {
			JalapenoEntity.killIceBall(this);
		}
	}
	
	public int getFireRange() {
		return 20;
	}

	@Override
	public float getLife() {
		return 44;
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.JALAPENO_ZOMBIE;
	}
	
}
