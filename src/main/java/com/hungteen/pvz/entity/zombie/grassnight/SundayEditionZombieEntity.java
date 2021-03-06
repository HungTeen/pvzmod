package com.hungteen.pvz.entity.zombie.grassnight;

import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class SundayEditionZombieEntity extends NewspaperZombieEntity{

	public SundayEditionZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.canBeCold = false;
	}

	@Override
	protected void updateAngry(boolean is) {
		this.setAngry(is);
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(is ? ZombieUtil.SUPER_FAST : ZombieUtil.LITTLE_FAST);
		this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(is ? ZombieUtil.VERY_HIGH : ZombieUtil.LITTLE_HIGH);
	}
	
	@Override
	public float getLife() {
		return 350;
	}
	
	@Override
	public float getPartLife() {
		return 100;
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.SUNDAY_EDITION_ZOMBIE;
	}
	
}
