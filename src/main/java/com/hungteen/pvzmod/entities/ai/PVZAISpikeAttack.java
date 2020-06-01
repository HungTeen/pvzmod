package com.hungteen.pvzmod.entities.ai;

import com.hungteen.pvzmod.entities.plants.fight.EntitySpikeWeed;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.util.EnumHand;

public class PVZAISpikeAttack extends EntityAIAttackMelee{

	public PVZAISpikeAttack(EntitySpikeWeed creature, double speedIn, boolean useLongMemory) {
		super(creature, speedIn, useLongMemory);
	}

	@Override
	protected void checkAndPerformAttack(EntityLivingBase p_190102_1_, double p_190102_2_) {
		double d0 = this.getAttackReachSqr(p_190102_1_);

        if (p_190102_2_ <= d0 && this.attackTick <= 0)
        {
            this.attackTick = ((EntitySpikeWeed) this.attacker).getAttackCD();
            this.attacker.swingArm(EnumHand.MAIN_HAND);
            this.attacker.attackEntityAsMob(p_190102_1_);
        }
    }
	
	@Override
    protected double getAttackReachSqr(EntityLivingBase attackTarget)
    {
        return this.attacker.width * this.attacker.width;
    }
}
