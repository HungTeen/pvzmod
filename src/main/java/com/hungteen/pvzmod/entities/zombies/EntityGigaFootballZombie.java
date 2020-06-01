package com.hungteen.pvzmod.entities.zombies;

import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.util.EntityUtil;

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
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(300.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(EntityUtil.POLE_SPEED);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(EntityUtil.ZOMBIE_ANGRY_DAMAGE);
    }
	
	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		if(this.getHealth()<90) {
			this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(EntityUtil.FOOTBALL_SPEED);//БЉзп
		}
	}
	
//	@Override
//	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
//		return null;
//	}
}
