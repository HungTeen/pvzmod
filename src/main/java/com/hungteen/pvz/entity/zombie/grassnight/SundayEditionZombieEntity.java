package com.hungteen.pvz.entity.zombie.grassnight;

import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class SundayEditionZombieEntity extends NewspaperZombieEntity{

	public SundayEditionZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void updateAngry(boolean is) {
		this.setAngry(is);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(is ? ZombieUtil.SUPER_FAST : ZombieUtil.LITTLE_FAST);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(is ? ZombieUtil.VERY_HIGH : ZombieUtil.LITTLE_HIGH);
	}
	
	@Override
	public float getLife() {
		return 250;
	}
	
	@Override
	public float getPartLife() {
		return 100;
	}
	
	@Override
	public boolean canBeCold() {
		return false;
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.SUNDAY_EDITION_ZOMBIE;
	}
	
}
