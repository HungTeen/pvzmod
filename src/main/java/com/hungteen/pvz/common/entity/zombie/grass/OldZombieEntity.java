package com.hungteen.pvz.common.entity.zombie.grass;

import com.hungteen.pvz.common.core.ZombieType;
import com.hungteen.pvz.common.impl.zombie.GrassZombies;
import com.hungteen.pvz.utils.ZombieUtil;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class OldZombieEntity extends NewspaperZombieEntity{
	
	public OldZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void updateAngry(boolean is) {
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(is ? ZombieUtil.WALK_VERY_FAST : ZombieUtil.WALK_NORMAL);
		this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(is ? ZombieUtil.NORMAL_DAMAGE : ZombieUtil.LITTLE_LOW);
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
	public ZombieType getZombieType() {
		return GrassZombies.OLD_ZOMBIE;
	}
}
