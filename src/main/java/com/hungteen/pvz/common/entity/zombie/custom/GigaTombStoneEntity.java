package com.hungteen.pvz.common.entity.zombie.custom;

import com.hungteen.pvz.common.entity.zombie.grass.AbstractTombStoneEntity;
import com.hungteen.pvz.common.impl.zombie.CustomZombies;
import com.hungteen.pvz.common.impl.zombie.ZombieType;
import net.minecraft.world.entity.CreatureEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class GigaTombStoneEntity extends AbstractTombStoneEntity {

	public GigaTombStoneEntity(EntityType<? extends CreatureEntity> type, Level worldIn) {
		super(type, worldIn);
		this.canBeCharm = false;
	}

	@Override
	public ZombieType getZombieType() {
		return CustomZombies.GIGA_TOMBSTONE;
	}

}
