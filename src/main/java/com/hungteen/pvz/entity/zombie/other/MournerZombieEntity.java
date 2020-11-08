package com.hungteen.pvz.entity.zombie.other;

import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class MournerZombieEntity extends PVZZombieEntity{

	public MournerZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public float getLife() {
		return 48;
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.MOURNER_ZOMBIE;
	}

}
