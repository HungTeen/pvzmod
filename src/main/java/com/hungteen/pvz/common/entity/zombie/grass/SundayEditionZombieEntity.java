package com.hungteen.pvz.common.entity.zombie.grass;

import com.hungteen.pvz.common.impl.ZombieType;
import com.hungteen.pvz.common.impl.zombie.GrassZombies;
import com.hungteen.pvz.utils.ZombieUtil;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.world.World;

public class SundayEditionZombieEntity extends NewspaperZombieEntity{

	public SundayEditionZombieEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.canBeCold = false;
	}

	@Override
	protected void updateAngry(boolean is) {
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(is ? ZombieUtil.WALK_SUPER_FAST : ZombieUtil.WALK_LITTLE_FAST);
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
	public ZombieType getZombieType() {
		return GrassZombies.SUNDAY_EDITION_ZOMBIE;
	}
	
}
