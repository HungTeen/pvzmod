package com.hungteen.pvz.common.entity.zombie.grassnight;

import com.hungteen.pvz.remove.Zombies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class GigaTombStoneEntity extends AbstractTombStoneEntity {

	public GigaTombStoneEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.canBeCharm = false;
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.GIGA_TOMB_STONE;
	}

}
