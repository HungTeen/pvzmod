package com.hungteen.pvz.entity.ai;

import java.util.EnumSet;

import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.interfaces.IShooter;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;

public class ShooterAttackGoal extends Goal{

	private IShooter shooter;
	private MobEntity attacker;
	private LivingEntity target;
	private int attackTime;
	
	public ShooterAttackGoal(IShooter shooter) {
		if(!(shooter instanceof LivingEntity)) {
			throw new IllegalArgumentException("ERROR TASK OWNER");
		}
		this.shooter=shooter;
		this.attacker=(MobEntity) shooter;
		this.attackTime=0;
		this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}
	
	@Override
	public boolean shouldExecute() {
		LivingEntity attackTarget=this.attacker.getAttackTarget();
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
		this.attacker.setAttackTarget(null);
	}

	@Override
	public void tick() {
		this.attackTime++;
		if(this.attackTime>=this.shooter.getShootCD()) {
			this.attackTime=0;
			this.shooter.startShootAttack();
		}
		this.attacker.getLookController().setLookPositionWithEntity(this.target, 30.0F, 30.0F);
	}
	
	private boolean checkTarget(){
		if(EntityUtil.checkCanEntityAttack(this.attacker,target)) {
			return this.attacker.getEntitySenses().canSee(target) && this.shooter.checkY(target);
		}
		return false;
	}
}
