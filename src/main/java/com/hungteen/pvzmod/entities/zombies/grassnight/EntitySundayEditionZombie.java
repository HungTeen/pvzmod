package com.hungteen.pvzmod.entities.zombies.grassnight;

import com.hungteen.pvzmod.registry.PotionRegister;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.ZombieUtil;
import com.hungteen.pvzmod.util.enums.Zombies;

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
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.LITTLE_FAST);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ZombieUtil.BEAT);
    }
	
	@Override
	public float getLife() {
		return 240;
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
	protected float getAngryDamage()
	{
		return ZombieUtil.VERY_ANGRY_BEAT;
	}
	
	@Override
	protected float getAngrySpeed() {
		return ZombieUtil.FAST;
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.SUNDAY_EDITION_ZOMBIE;
	}
}
