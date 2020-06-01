package com.hungteen.pvzmod.entities.ai.attack;

import com.hungteen.pvzmod.entities.plants.base.EntityPultBase;
import com.hungteen.pvzmod.entities.plants.base.EntityShooterBase;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.interfaces.IPult;
import com.hungteen.pvzmod.util.interfaces.IShooter;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIBase;

public class PVZAIPultAttack extends EntityAIBase
{
	private IPult shooter;
	private EntityLiving attacker;
	private EntityLivingBase target;
	private int attackTime;
	
	public PVZAIPultAttack(IPult shooter) {
		if(!(shooter instanceof EntityLiving)) {
			throw new IllegalArgumentException("ERROR TASK OWNER");
		}
		this.shooter=shooter;
		this.attacker=(EntityLiving) shooter;
		this.setMutexBits(3);
	}
	
	@Override
	public boolean shouldExecute() {
		EntityLivingBase attackTarget=this.attacker.getAttackTarget();
		if(attackTarget==null) return false;
		else {
			this.target=attackTarget;
		    return true;
		}
	}
	
	@Override
	public boolean shouldContinueExecuting() {
		return this.shouldExecute()&&this.checkTarget();
	}
	
	@Override
	public void resetTask() {
//		this.attackTime=0;
		this.target=null;
	}

	@Override
	public void updateTask() {
		this.attackTime++;
		if(this.attackTime>=this.shooter.getPultSpeed()) {
			this.attackTime=0;
			this.shooter.startPultAttack();
		}
		this.attacker.getLookHelper().setLookPositionWithEntity(this.target, 30.0F, 30.0F);
	}
	
	private boolean checkTarget()
	{
		if(EntityUtil.checkCanEntityAttack(this.attacker,target)) {
			if(this.attacker.getEntitySenses().canSee(target)) {
				return true;
			}
		}
		return false;
	}
}
