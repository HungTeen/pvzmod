package com.hungteen.pvz.entity.zombie.grassnight;

import com.hungteen.pvz.entity.zombie.base.DefenceZombieEntity;
import com.hungteen.pvz.entity.zombie.part.PVZHealthPartEntity;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class ScreenDoorZombieEntity extends DefenceZombieEntity{

	public ScreenDoorZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public void resetParts() {
		removeParts();
		this.part = new PVZHealthPartEntity(this, 1f, 1.7f);
		this.part.setOwner(this);
	}
	
	@Override
	public SoundEvent getPartHurtSound() {
		return SoundRegister.METAL_HIT.get();
	}
	
	@Override
	public float getLife() {
		return 24;
	}
	
	@Override
	public float getPartLife() {
		return 200;
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.SCREENDOOR_ZOMBIE;
	}

}
