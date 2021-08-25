package com.hungteen.pvz.common.entity.zombie.custom;

import com.hungteen.pvz.common.core.ZombieType;
import com.hungteen.pvz.common.entity.zombie.grass.AbstractTombStoneEntity;
import com.hungteen.pvz.common.impl.zombie.CustomZombies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class GigaTombStoneEntity extends AbstractTombStoneEntity {

	public GigaTombStoneEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.canBeCharm = false;
	}

	@Override
	public ZombieType getZombieType() {
		return CustomZombies.GIGA_TOMBSTONE;
	}

}
