package com.hungteen.pvz.common.entity.zombie.grass;

import com.hungteen.pvz.common.impl.zombie.GrassZombies;
import com.hungteen.pvz.common.impl.zombie.ZombieType;
import com.hungteen.pvz.utils.ZombieUtil;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class OldZombieEntity extends NewspaperZombieEntity{
	
	public OldZombieEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public int getAngryLevel() {
		return 5;
	}

	@Override
	public float getEatDamage() {
		return ZombieUtil.LITTLE_LOW;
	}

	@Override
	public float getLife() {
		return 90;
	}
	
	@Override
	public float getOuterLife() {
		return 40;
	}
	
	@Override
	public ZombieType getZombieType() {
		return GrassZombies.OLD_ZOMBIE;
	}
}
