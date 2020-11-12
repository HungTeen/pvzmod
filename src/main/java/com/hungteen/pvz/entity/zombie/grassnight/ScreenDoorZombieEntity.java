package com.hungteen.pvz.entity.zombie.grassnight;

import com.hungteen.pvz.entity.zombie.base.DefenceZombieEntity;
import com.hungteen.pvz.entity.zombie.part.PVZHealthPartEntity;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class ScreenDoorZombieEntity extends DefenceZombieEntity{

	private static final float DEFENCE_HEALTH = 200;
	
	public ScreenDoorZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.setDefenceLife(DEFENCE_HEALTH);
	}

	@Override
	public void resetParts() {
		removeParts();
		this.part = new PVZHealthPartEntity(this, 1f, 1.5f);
		this.part.setOwner(this);
	}
	
	@Override
	public float getLife() {
		return 24;
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.SCREENDOOR_ZOMBIE;
	}

}
