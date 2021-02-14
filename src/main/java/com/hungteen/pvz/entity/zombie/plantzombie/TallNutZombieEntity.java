package com.hungteen.pvz.entity.zombie.plantzombie;

import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class TallNutZombieEntity extends PVZZombieEntity {

	public TallNutZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.LITTLE_SLOW);
	}
	
	@Override
	public float getLife() {
		return 320;
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.TALLNUT_ZOMBIE;
	}

}
