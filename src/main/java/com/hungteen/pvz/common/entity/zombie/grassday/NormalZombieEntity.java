package com.hungteen.pvz.common.entity.zombie.grassday;

import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class NormalZombieEntity extends PVZZombieEntity {

	public NormalZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected float getWaterSlowDown() {
		return 0.91f;
	}

	@Override
	protected PVZZombieEntity.Type getSpawnType() {
		PVZZombieEntity.Type type = super.getSpawnType();
		if(type == PVZZombieEntity.Type.NORMAL) {
			if(this.getRandom().nextInt(200) == 0) {
				this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(ZombieUtil.NORMAL_DAMAGE);
			    return PVZZombieEntity.Type.BEARD;
			}
		}
		return type;
	}

	@Override
	public float getLife() {
		return 20;
	}
	
	@Override
	public boolean checkCanLostHand() {
		return this.getHealth() / this.getMaxHealth() < 0.5;
	}
	
	@Override
	public boolean checkCanLostHead() {
		return this.getHealth() < 1;
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.NORMAL_ZOMBIE;
	}
}
