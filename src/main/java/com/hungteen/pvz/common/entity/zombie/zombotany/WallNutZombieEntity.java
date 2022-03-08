package com.hungteen.pvz.common.entity.zombie.zombotany;

import com.hungteen.pvz.common.impl.zombie.ZombieType;
import com.hungteen.pvz.common.impl.zombie.Zombotanies;
import net.minecraft.world.entity.CreatureEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class WallNutZombieEntity extends AbstractZombotanyEntity {

	public WallNutZombieEntity(EntityType<? extends CreatureEntity> type, Level worldIn) {
		super(type, worldIn);
	}

	@Override
	public float getLife() {
		return 160;
	}
	
	@Override
	public ZombieType getZombieType() {
		return Zombotanies.WALLNUT_ZOMBIE;
	}

}
