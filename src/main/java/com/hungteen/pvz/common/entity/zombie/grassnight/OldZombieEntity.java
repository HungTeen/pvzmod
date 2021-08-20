package com.hungteen.pvz.common.entity.zombie.grassnight;

import com.hungteen.pvz.remove.Zombies;
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
	public Zombies getZombieEnumName() {
		return Zombies.OLD_ZOMBIE;
	}
}
