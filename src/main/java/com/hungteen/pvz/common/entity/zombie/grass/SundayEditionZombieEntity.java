package com.hungteen.pvz.common.entity.zombie.grass;

import com.hungteen.pvz.common.impl.zombie.GrassZombies;
import com.hungteen.pvz.common.impl.zombie.ZombieType;
import com.hungteen.pvz.utils.ZombieUtil;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class SundayEditionZombieEntity extends NewspaperZombieEntity{

	public SundayEditionZombieEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.canBeCold = false;
	}

	@Override
	public int getAngryLevel() {
		return 10;
	}

	@Override
	public float getEatDamage() {
		return ZombieUtil.LITTLE_HIGH;
	}

	@Override
	public float getWalkSpeed() {
		return ZombieUtil.WALK_LITTLE_FAST;
	}

	@Override
	public float getLife() {
		return 350;
	}
	
	@Override
	public float getOuterLife() {
		return 100;
	}
	
	@Override
	public ZombieType getZombieType() {
		return GrassZombies.SUNDAY_EDITION_ZOMBIE;
	}
	
}
