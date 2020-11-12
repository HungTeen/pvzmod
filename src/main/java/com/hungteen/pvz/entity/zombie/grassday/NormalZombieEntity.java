package com.hungteen.pvz.entity.zombie.grassday;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.entity.zombie.base.UnderGroundZombieEntity;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class NormalZombieEntity extends UnderGroundZombieEntity {

	public NormalZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.isRiseType = false;
		if (this.getZombieType() == Type.BEARD) {
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ZombieUtil.NORMAL_DAMAGE);
		}
	}
	
	@Override
	protected float getWaterSlowDown() {
		return 0.91f;
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
	public Zombies getZombieEnumName() {
		return Zombies.NORMAL_ZOMBIE;
	}
}
