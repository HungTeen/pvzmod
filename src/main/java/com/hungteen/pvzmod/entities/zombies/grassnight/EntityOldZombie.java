package com.hungteen.pvzmod.entities.zombies.grassnight;

import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.registry.PotionRegister;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.ZombieUtil;
import com.hungteen.pvzmod.util.enums.Zombies;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityOldZombie extends EntityPaperZombie{

	private final int summonChance = 10;
	
	public EntityOldZombie(World worldIn) {
		super(worldIn);
	}

	@Override
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.NORMAL_SPEED);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ZombieUtil.BIT_EAT);
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
		if(this.getRNG().nextInt(summonChance)==0) {
			if(!this.world.isRemote) {
				EntityZombieBase zombie=new EntitySundayEditionZombie(world);
				zombie.setPosition(posX, posY, posZ);
				this.world.spawnEntity(zombie);
			}
		}
	}
	
	@Override
	public float getLife() {
		return 120;
	}
	
	@Override
	protected float getAngryDamage()
	{
		return ZombieUtil.ANGRY_BEAT;
	}
	
	@Override
	protected float getAngrySpeed() {
		return ZombieUtil.BIT_FAST;
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.OLD_ZOMBIE;
	}
}
