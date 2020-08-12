package com.hungteen.pvz.entity.zombie.grassday;

import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class BucketHeadZombieEntity extends NormalZombieEntity{

	public BucketHeadZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public float getLife() {
		return 130;
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.BUCKETHEAD_ZOMBIE;
	}
}
