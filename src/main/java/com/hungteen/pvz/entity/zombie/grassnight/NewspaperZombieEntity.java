package com.hungteen.pvz.entity.zombie.grassnight;

import com.hungteen.pvz.entity.zombie.base.DefenceZombieEntity;
import com.hungteen.pvz.entity.zombie.part.PVZHealthPartEntity;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class NewspaperZombieEntity extends DefenceZombieEntity {

	private static final float DEFENCE_HEALTH = 10;
	
	public NewspaperZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	public void resetParts() {
		removeParts();
		this.part = new PVZHealthPartEntity(this, 1f, 1.5f, DEFENCE_HEALTH);
		this.part.setOwner(this);
	}
	
	protected void updateAngry(boolean is) {
		if(is) {
			this.playSound(SoundRegister.ANGRY.get(), 1f, 1f);
		}
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(is ? ZombieUtil.LITTLE_FAST : ZombieUtil.LITTLE_SLOW);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(is ? ZombieUtil.LOW : ZombieUtil.LITTLE_LOW);
	}
	
	@Override
	public float getLife() {
		return 30;
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.NEWSPAPER_ZOMBIE;
	}

}
