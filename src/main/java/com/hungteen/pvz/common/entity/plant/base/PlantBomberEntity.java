package com.hungteen.pvz.common.entity.plant.base;

import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

/**
 * Bomber Plants will be remove after several seconds and perform explosion when dying.
 * use ATTACK_TIME to perform scale animation.
 */
public abstract class PlantBomberEntity extends PVZPlantEntity {

	public PlantBomberEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		final int time = this.getAttackTime();
		if(this.level.isClientSide) {
			if(time == this.getReadyTime() - 1) {
		    	this.startBomb(false);
			}
		} else {
		    if(time > this.getReadyTime()) {
			    this.startBomb(true);
			    this.remove();
		    } 
		    this.setAttackTime(time + 1);
		}
	}
	
	/**
	 * start explosion.
	 */
    protected abstract void startBomb(boolean server);
    
    /**
     * explosion pre time
     */
	public abstract int getReadyTime();
	
	@Override
	public boolean isPlantImmuneTo(DamageSource source) {
		return super.isPlantImmuneTo(source) || this.canNormalUpdate();
	}
	
	@Override
	public int getSuperTimeLength() {
		return 0;
	}

}
