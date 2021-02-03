package com.hungteen.pvz.entity.ai.attack;

import java.util.EnumSet;

import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.interfaces.IPult;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;

public class PultAttackGoal extends Goal {

	private final IPult pult;
	protected MobEntity attacker;
	protected LivingEntity target;
	protected int attackTime;
	
	public PultAttackGoal(IPult pult) {
		this.pult = pult;
		if(! (pult instanceof MobEntity)) {
			System.out.println("Error : Wrong pult attacker !");
			return ;
		}
		this.attacker = (MobEntity) pult;
		this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}
	
	@Override
	public boolean shouldExecute() {
		if(! this.pult.shouldPult()) return false;
		LivingEntity target = this.attacker.getAttackTarget();
		if(! EntityUtil.isEntityValid(target)) return false;
		this.target = target;
		if(this.checkTarget(target)) {
			return true;
		}
		this.resetTask();
		return false;
	}
	
	@Override
	public boolean shouldContinueExecuting() {
		return this.shouldExecute();
	}
	
	@Override
	public void resetTask() {
		this.target = null;
		this.attacker.setAttackTarget(null);
	}

	@Override
	public void tick() {
		++ this.attackTime;
		if(this.attackTime >= this.pult.getPultCD()) {
			this.attackTime = 0;
			this.pult.startPultAttack();
		}
		this.attacker.getLookController().setLookPositionWithEntity(this.target, 30.0F, 30.0F);
	}
	
	protected boolean checkTarget(LivingEntity target) {
		if(EntityUtil.checkCanEntityTarget(this.attacker, this.target)) {
			return this.attacker.getEntitySenses().canSee(this.target);
		}
		return false;
	}

}
