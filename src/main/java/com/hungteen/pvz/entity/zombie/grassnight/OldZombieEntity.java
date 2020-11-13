package com.hungteen.pvz.entity.zombie.grassnight;

import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class OldZombieEntity extends NewspaperZombieEntity{

	
	public OldZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void updateAngry(boolean is) {
		this.setAngry(is);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(is ? ZombieUtil.VERY_FAST : ZombieUtil.NORMAL_SPEED);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(is ? ZombieUtil.NORMAL_DAMAGE : ZombieUtil.LITTLE_LOW);
	}
	
	@Override
	public float getLife() {
		return 90;
	}
	
	@Override
	public float getPartLife() {
		return 40;
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.OLD_ZOMBIE;
	}
}
