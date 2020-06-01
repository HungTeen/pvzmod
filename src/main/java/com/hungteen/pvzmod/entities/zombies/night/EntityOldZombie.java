package com.hungteen.pvzmod.entities.zombies.night;

import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.registry.PotionRegister;
import com.hungteen.pvzmod.util.EntityUtil;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityOldZombie extends EntityPaperZombie{

	public EntityOldZombie(World worldIn) {
		super(worldIn);
	}

	@Override
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(120.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(EntityUtil.NORMAL_SPEED);
    }
	
	@Override
	public int getAttackTick() {
		if(!this.getIsAngry()) return super.getAttackTick();
		if (this.isPotionActive(PotionRegister.COLD_EFFECT)) {
			int lvl = this.getActivePotionEffect(PotionRegister.COLD_EFFECT).getAmplifier();
			return 2 * lvl + 6;
		}
		return 6;
	}
	
	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);
		if(this.getRNG().nextInt(1)==0) {
			if(!this.world.isRemote) {
				EntityZombieBase zombie=new EntitySundayEditionZombie(world);
				zombie.setPosition(posX, posY, posZ);
				this.world.spawnEntity(zombie);
			}
		}
	}
	
	@Override
	protected float getAngryLife() {
		return 40;
	}
	
	@Override
	protected float getAngryDamage()
	{
		return EntityUtil.ZOMBIE_ANGRY_DAMAGE;
	}
	
	@Override
	protected float getAngrySpeed() {
		return EntityUtil.POLE_SPEED;
	}
}
