package com.hungteen.pvzmod.entities.zombies.night;

import com.hungteen.pvzmod.registry.PotionRegister;
import com.hungteen.pvzmod.util.EntityUtil;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntitySundayEditionZombie extends EntityPaperZombie{

	public EntitySundayEditionZombie(World worldIn) {
		super(worldIn);
		this.paper.setSize(1f,1.8f);
	}

	@Override
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(230.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(EntityUtil.FLAG_SPEED);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(EntityUtil.ZOMBIE_ANGRY_DAMAGE);
    }
	
	@Override
	public int getAttackTick() {
		if(!this.getIsAngry()) return super.getAttackTick();
		if (this.isPotionActive(PotionRegister.COLD_EFFECT)) {
			int lvl = this.getActivePotionEffect(PotionRegister.COLD_EFFECT).getAmplifier();
			return 1 * lvl + 4;
		}
		return 4;
	}
	
	@Override
	protected float getAngryLife() {
		return 90;
	}
	
	@Override
	protected float getAngryDamage()
	{
		return EntityUtil.ZOMBIE_VERY_ANGRY_DAMAGE;
	}
	
	@Override
	protected float getAngrySpeed() {
		return EntityUtil.FOOTBALL_SPEED;
	}
}
