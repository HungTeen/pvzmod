package com.hungteen.pvz.common.entity.goal.attack;

import java.util.EnumSet;

import com.hungteen.pvz.common.entity.plant.appease.StarFruitEntity;
import com.hungteen.pvz.common.entity.plant.spear.CatTailEntity;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.interfaces.IShooter;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;

public class ShooterAttackGoal extends Goal{

	protected IShooter shooter;
	protected MobEntity attacker;
	protected LivingEntity target;
	protected int attackTime;
	
	public ShooterAttackGoal(IShooter shooter) {
		if(!(shooter instanceof MobEntity)) {
			throw new IllegalArgumentException("ERROR TASK OWNER");
		}
		this.shooter = shooter;
		this.attacker = (MobEntity) shooter;
		this.attackTime = 0;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}
	
	@Override
	public boolean canUse() {
		if(! this.shooter.canShoot()) {
			return false;
		}
		LivingEntity attackTarget = this.attacker.getTarget();
		if(attackTarget == null) {
			return false;
		}else {
			this.target = attackTarget;
			if(this.checkTarget()) {
				return true;
			}
			this.stop();
			return false;
		}
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
//		System.out.println(this.checkTarget());
		++ this.attackTime;
		if(this.attackTime >= this.shooter.getShootCD()) {
			this.attackTime = 0;
			this.shooter.startShootAttack();
		}
		if(! (this.attacker instanceof StarFruitEntity)) {
			this.attacker.getLookControl().setLookAt(this.target, 30.0F, 30.0F);
		}
	}
	
	private boolean checkTarget() {
		if(EntityUtil.checkCanEntityTarget(this.attacker, this.target)) {
			if(this.attacker instanceof CatTailEntity) {
//				System.out.println(EntityUtil.checkCanSeeEntity(this.attacker, this.target));
				return EntityUtil.checkCanSeeEntity(this.attacker, this.target);
			}
			return this.attacker.getSensing().canSee(this.target);
		}
		return false;
	}
	
}
