package com.hungteen.pvz.entity.zombie.poolday;

import com.hungteen.pvz.entity.zombie.base.SwimmerZombieEntity;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class DolphinRiderZombieEntity extends SwimmerZombieEntity{

	public DolphinRiderZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void updateAttributes() {
		super.updateAttributes();
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.FAST);
	}
	
	@Override
	public float getLife() {
		return 20;
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.DOLPHIN_RIDER_ZOMBIE;
	}

}
