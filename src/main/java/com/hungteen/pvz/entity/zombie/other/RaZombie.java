package com.hungteen.pvz.entity.zombie.other;

import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class RaZombie extends PVZZombieEntity {

	public RaZombie(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public float getLife() {
		return 36;
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.RA_ZOMBIE;
	}

}
