package com.hungteen.pvz.common.entity.plant.base;

import com.hungteen.pvz.api.interfaces.IAlmanacEntry;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.utils.enums.PAZAlmanacs;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;

/**
 * Bomber Plants will be remove after several seconds and perform explosion when dying.
 * use ATTACK_TIME to perform scale animation.
 */
public abstract class PlantBomberEntity extends PVZPlantEntity {

	protected boolean hasBombAlamancs = true;
	
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

	@Override
	public void addAlmanacEntries(List<Pair<IAlmanacEntry, Number>> list) {
		super.addAlmanacEntries(list);
		if(hasBombAlamancs){
			list.addAll(Arrays.asList(
					Pair.of(PAZAlmanacs.EXPLODE_DAMAGE, this.getExplodeDamage()),
					Pair.of(PAZAlmanacs.EXPLODE_RANGE, this.getExplodeRange())
			));
		}
	}

	public float getExplodeDamage(){return 0;}

	public float getExplodeRange(){return 0;}
	
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
