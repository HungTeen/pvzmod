package com.hungteen.pvz.common.entity.ai.goal.target;

import java.util.EnumSet;
import java.util.stream.Collectors;

import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.TargetGoal;

public class PVZHurtByTargetGoal extends TargetGoal {

	private final int alertRange;
	private int executeTick = 0;
	
	public PVZHurtByTargetGoal(CreatureEntity creature, int range) {
		super(creature, true);
		this.alertRange = range;
		this.setFlags(EnumSet.of(Goal.Flag.TARGET));
	}

	@Override
	public boolean canUse() {
		LivingEntity target = this.mob.getTarget();
		if(! EntityUtil.isEntityValid(target)) {//focus on 20 tick.
			++ this.executeTick;
			if(this.executeTick < 20) {
			    return false;
			} else {
				this.executeTick = 0;
			}
		} else {
			return false;
		}
		LivingEntity attacker = this.mob.getLastHurtByMob();
		if (!EntityUtil.isEntityValid(attacker)) {//not exsit target.
			return false;
		}
		return EntityUtil.checkCanEntityBeAttack(mob, attacker);
	}

	@Override
	public void start() {
		this.mob.setTarget(this.mob.getLastHurtByMob());//set target.
		this.targetMob = this.mob.getTarget();
		this.alertOthers();
		super.start();
	}
	
	@Override
	public boolean canContinueToUse() {
		LivingEntity entity = this.mob.getTarget();
		if(! EntityUtil.isEntityValid(entity)) {
			entity = this.targetMob;
		}
		if(! EntityUtil.isEntityValid(entity)) {
			return false;
		}
		if(EntityUtil.canAttackEntity(mob, entity)) {
			this.mob.setTarget(entity);
			return true;
		}
		return false;
	}

	protected void alertOthers() {
		this.mob.level.getEntitiesOfClass(MobEntity.class, EntityUtil.getEntityAABB(this.mob, this.alertRange, this.alertRange)).stream().filter(entity -> {
			return this.canAlertEntity(entity) && entity.getTarget() == null && entity.getLastHurtByMob() == null;
		}).collect(Collectors.toList()).forEach(entity -> {
			this.alertOther(entity, this.targetMob);
		});
	}
	
	protected boolean canAlertEntity(LivingEntity entity) {
		return ! EntityUtil.checkCanEntityBeAttack(this.mob, entity) && EntityUtil.canHelpAttackOthers(entity);
	}

	protected void alertOther(MobEntity entity, LivingEntity target) {
		entity.setTarget(target);
	}

}
