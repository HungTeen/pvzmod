package com.hungteen.pvz.common.entity.plant.spear;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import net.minecraft.world.entity.CreatureEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class SpikeRockEntity extends SpikeWeedEntity {

    public SpikeRockEntity(EntityType<? extends CreatureEntity> type, Level worldIn) {
		super(type, worldIn);
		this.setSpikeNum(this.getSpikesCount());
	}
	
	public int getSpikesCount() {
		return (int) this.getSkillValue(SkillTypes.MORE_SPIKE);
	}

	@Override
	public int getAttackCD() {
		return 20;
	}

	@Override
	public IPlantType getPlantType() {
		return PVZPlants.SPIKE_ROCK;
	}
	
}
