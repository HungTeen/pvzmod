package com.hungteen.pvz.common.entity.ai.goal.attack;

import java.util.EnumSet;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.interfaces.IPult;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;

public class PultAttackGoal extends Goal {

	private final IPult pult;
	protected final MobEntity attacker;
	protected LivingEntity target;
	protected final boolean checkSight;
	protected int attackTime;
	
	public PultAttackGoal(IPult pult) {
		this(pult, true);
	}
	
	public PultAttackGoal(IPult pult, boolean checkSight) {
		this.pult = pult;
		this.checkSight = checkSight;
		this.attacker = (MobEntity) pult;
		if(! (pult instanceof MobEntity)) {
			PVZMod.LOGGER.fatal("Error : Wrong pult attacker !");
			return ;
		}
		this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}
	
	@Override
	public boolean canUse() {
		//attacker can not pult because of itself.
		if(! this.pult.shouldPult()) {
			return false;
		}
		final LivingEntity target = this.attacker.getTarget();
		//attacker can not pult because of its target, so clear target.
		if(! EntityUtil.isEntityValid(target) || ! this.checkTarget(target)) {
			this.stop();
			return false;
		}
		this.target = target;
		return true;
	}
	
	@Override
	public boolean canContinueToUse() {
		return this.canUse();
	}
	
	@Override
	public void stop() {
		this.target = null;
		this.attacker.setTarget(null);
	}

	@Override
	public void tick() {
		++ this.attackTime;
		if(this.attackTime >= this.pult.getPultCD()) {
			this.attackTime = 0;
			this.pult.startPultAttack();
		}
		this.attacker.getLookControl().setLookAt(this.target, 30.0F, 30.0F);
	}
	
	protected boolean checkTarget(LivingEntity target) {
		if(EntityUtil.checkCanEntityBeAttack(this.attacker, target)) {
			return ! this.checkSight || this.attacker.getSensing().canSee(target);
		}
		return false;
	}

}
