package com.hungteen.pvz.entity.zombie.plantzombie;

import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class WallNutZombieEntity extends PVZZombieEntity {

	public WallNutZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public float getLife() {
		return 160;
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.WALLNUT_ZOMBIE;
	}

}
