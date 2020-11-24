package com.hungteen.pvz.entity.ai;

import java.util.EnumSet;

import com.hungteen.pvz.entity.plant.base.PlantProducerEntity;
import com.hungteen.pvz.utils.interfaces.IProducer;

import net.minecraft.entity.ai.goal.Goal;

public class ProducerGenGoal extends Goal{

	private PlantProducerEntity plant;
	private IProducer producer;
	
	public ProducerGenGoal(IProducer entity) {
		if(!(entity instanceof PlantProducerEntity)) {
			throw new IllegalArgumentException("ERROR TASK OWNER");
		}
		this.producer=entity;
		this.plant=(PlantProducerEntity) entity;
		this.setMutexFlags(EnumSet.of(Goal.Flag.TARGET));
	}
	
	@Override
	public boolean shouldExecute() {
		return true;
	}

	@Override
	public boolean shouldContinueExecuting() {
		return this.shouldExecute();
	}
	
	@Override
	public void resetTask() {
		
	}
	
	@Override
	public void tick() {
		if(!this.plant.canPlantNormalUpdate()) {
			return ;
		}
		if(this.plant.isPlantInSuperMode()){//Be Super Mode
			if(this.plant.getSuperTime()==1) {
			    this.producer.genSuper();
			}
			this.plant.setIsGenTime(true);
			return ;
        }
		this.plant.setAttackTime(this.plant.getAttackTime()+1);
		if(this.plant.getAttackTime()+10>=this.plant.genCD) {
		    this.plant.setIsGenTime(true);
	    }
	    else if(!this.plant.isPlantInSuperMode()){
		    this.plant.setIsGenTime(false);
	    }
		if(this.plant.getAttackTime()>=this.plant.genCD) {
		    this.producer.genSomething();
		    this.plant.genCD=this.producer.getGenCD();
			this.plant.setAttackTime(0);
		}
	}
}
