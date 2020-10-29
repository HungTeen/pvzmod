package com.hungteen.pvz.entity.zombie.plantzombie;

import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class PumpkinZombieEntity extends PVZZombieEntity{

	public PumpkinZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.SLOW);
	}
	
	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		if(!world.isRemote) {
			this.heal(20);
		}
		return super.attackEntityAsMob(entityIn);
	}
	
	@Override
	public float getLife() {
		return 180;
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.PUMPKIN_ZOMBIE;
	}

}
