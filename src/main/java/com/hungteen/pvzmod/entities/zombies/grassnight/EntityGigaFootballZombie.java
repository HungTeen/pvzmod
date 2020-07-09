package com.hungteen.pvzmod.entities.zombies.grassnight;

import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.ZombieUtil;
import com.hungteen.pvzmod.util.enums.Zombies;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityGigaFootballZombie extends EntityZombieBase{

	public EntityGigaFootballZombie(World worldIn) {
		super(worldIn);
		this.setSize(1f, 2.4f);
	}
	
	@Override
	protected void setSmallSize() {
		this.setSize(0.5f, 0.6f);
	}
	
	@Override
	public float getLife() {
		return 300;
	}
	
	@Override
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.FAST);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ZombieUtil.ANGRY_BEAT);
    }
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.GIGA_FOOTBALL_ZOMBIE;
	}
	
//	@Override
//	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
//		return null;
//	}
}
