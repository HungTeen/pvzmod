package com.hungteen.pvz.common.entity.zombie.zombotany;

import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public abstract class AbstractZombotanyEntity extends PVZZombieEntity {

	public AbstractZombotanyEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected float getWaterSlowDown() {
		return 0.91f;
	}
	
}
