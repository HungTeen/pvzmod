package com.hungteen.pvz.common.entity.zombie.zombotany;

import com.hungteen.pvz.common.impl.zombie.ZombieType;
import com.hungteen.pvz.common.impl.zombie.Zombotanies;
import net.minecraft.world.entity.CreatureEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class GatlingPeaZombieEntity extends PeaShooterZombieEntity {

	public GatlingPeaZombieEntity(EntityType<? extends CreatureEntity> type, Level worldIn) {
		super(type, worldIn);
	}

	@Override
	protected int getShootNum() {
		return 4;
	}
	
	@Override
	public float getLife() {
		return 30;
	}
	
	@Override
	public ZombieType getZombieType() {
		return Zombotanies.GATLINGPEA_ZOMBIE;
	}
	
}
