package com.hungteen.pvz.common.entity.ai.goal.target;

import com.hungteen.pvz.common.potion.EffectRegister;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.EnumSet;

public abstract class PVZTargetGoal extends Goal{

	protected final MobEntity mob;
	protected LivingEntity targetMob;
	protected final boolean mustSee;
	protected final boolean mustReach;
	protected int targetChance = 5;
	private final float upperHeight;
	private final float lowerHeight;
	private final float width;

	public PVZTargetGoal(MobEntity mobIn, boolean mustSee, boolean mustReach, float w, float h) {
		this(mobIn, mustSee, mustReach, w, h, h);
	}

	public PVZTargetGoal(MobEntity mobIn, boolean mustSee, boolean mustReach, float w, float h1, float h2) {
		this.mob = mobIn;
		this.mustSee = mustSee;
		this.mustReach = mustReach;
		this.width = w;
		this.upperHeight = h1;
		this.lowerHeight = h2;
		this.setFlags(EnumSet.of(Goal.Flag.TARGET));
	}

	@Override
	public void start() {
		this.mob.setTarget(this.targetMob);
	}

	@Override
	public boolean canContinueToUse() {
		LivingEntity entity = this.mob.getTarget();
		//alternative target not exist.
		if(! EntityUtil.isEntityValid(this.targetMob)) {
			return false;
		}
		if(! EntityUtil.isEntityValid(entity)) {
			entity = this.targetMob;
		} else if(! entity.is(this.targetMob)) {
			this.targetMob = entity;
		}
		if(! EntityUtil.isEntityValid(entity)) {
			return false;
		}
		if(Math.abs(entity.getX() - this.mob.getX()) > this.width || Math.abs(entity.getZ() - this.mob.getZ()) > this.width) {
			return false;
		}
		if(EntityUtil.canAttackEntity(mob, entity) && (this.mustReach || (! this.mustSee || this.checkSenses(entity)))) {
			if (this.checkOther(entity)) {
				this.mob.setTarget(entity);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void stop() {
		this.mob.setTarget(null);
	}
	
	protected boolean checkSenses(Entity entity) {
		return this.mob.getSensing().canSee(entity);
	}

	protected boolean checkOther(LivingEntity entity) {
		//invisible entity need closer, except it has light eyes effect.
		if(this.mustSee && entity.isInvisible() && this.mob.distanceToSqr(entity) > 100 
				&& ! this.mob.hasEffect(EffectRegister.LIGHT_EYE_EFFECT.get())) {
			return false;
		}
		return true;
	}

	protected AxisAlignedBB getAABB() {
		return new AxisAlignedBB(this.mob.getX() + width, this.mob.getY() + this.upperHeight,
				this.mob.getZ() + width, this.mob.getX() - width,
				this.mob.getY() - this.lowerHeight, this.mob.getZ() - width);
	}

}
