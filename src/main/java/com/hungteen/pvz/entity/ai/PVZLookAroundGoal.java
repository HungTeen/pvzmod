package com.hungteen.pvz.entity.ai;

import java.util.EnumSet;

import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;

public class PVZLookAroundGoal extends Goal {

	protected final MobEntity owner;
	private double lookX;
	private double lookZ;
	private int idleTime;

	public PVZLookAroundGoal(MobEntity entitylivingIn) {
		this.owner = entitylivingIn;
		this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	@Override
	public boolean shouldExecute() {
		return this.owner.getAttackTarget() == null && this.owner.getRNG().nextFloat() < 0.02F;
	}

	public boolean shouldContinueExecuting() {
		return this.idleTime >= 0;
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void startExecuting() {
		double d0 = (Math.PI * 2D) * this.owner.getRNG().nextDouble();
		this.lookX = Math.cos(d0);
		this.lookZ = Math.sin(d0);
		this.idleTime = 20 + this.owner.getRNG().nextInt(20);
	}

	/**
	 * Keep ticking a continuous task that has already been started
	 */
	public void tick() {
		--this.idleTime;
		this.owner.getLookController().setLookPosition(this.owner.getPosX() + this.lookX, this.owner.getPosYEye(),
				this.owner.getPosZ() + this.lookZ);
	}

}
