package com.hungteen.pvz.common.entity.plant.base;

import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.utils.interfaces.IBomber;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class PlantBomberEntity extends PVZPlantEntity implements IBomber{

	public PlantBomberEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		this.setAttackTime(this.getAttackTime() + 1);
		if(this.getAttackTime() >= this.getReadyTime()) {
			this.startBomb();
			this.remove();
			this.setAttackTime(0);
		}
	}
	
	@Override
	public boolean isPlantImmuneTo(DamageSource source) {
		return super.isPlantImmuneTo(source) || (! this.isPlantSleeping() && PVZDamageSource.isEnforceDamage(source));
	}
	
	@Override
	public int getSuperTimeLength() {
		return 0;
	}

}
