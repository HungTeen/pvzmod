package com.hungteen.pvz.entity.ai;

import java.util.EnumSet;

import com.hungteen.pvz.entity.plant.base.PlantDefenderEntity;
import com.hungteen.pvz.utils.interfaces.IDefender;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;

public class DefenderAttractGoal extends Goal{

	private PlantDefenderEntity plant;
	private IDefender defender;
	private int chance;
	
	public DefenderAttractGoal(IDefender entity,int chance) {
		if(!(entity instanceof PlantDefenderEntity)) {
			throw new IllegalArgumentException("ERROR TASK OWNER");
		}
		this.defender=entity;
		this.plant=(PlantDefenderEntity) entity;
		this.chance=chance;
		this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
	}
	
	@Override
	public boolean shouldExecute() {
		LivingEntity target=plant.getAttackTarget();
		if(target==null||!(target instanceof MobEntity)) return false;
		return plant.getRNG().nextInt(chance)==0;
	}

	@Override
	public void startExecuting() {
		defender.attract();
	}
}
