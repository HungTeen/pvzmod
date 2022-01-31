package com.hungteen.pvz.common.entity.zombie.grass;

import com.hungteen.pvz.common.impl.zombie.GrassZombies;
import com.hungteen.pvz.common.impl.zombie.ZombieType;
import com.hungteen.pvz.utils.ZombieUtil;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class FlagZombieEntity extends NormalZombieEntity{

	public FlagZombieEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public float getWalkSpeed() {
		return ZombieUtil.WALK_LITTLE_FAST;
	}

	@Override
	public float getLife() {
		return 19;
	}
	
	@Override
	public ZombieType getZombieType() {
		return GrassZombies.FLAG_ZOMBIE;
	}
}
