package com.hungteen.pvz.common.entity.plant.spear;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class SpikeRockEntity extends SpikeWeedEntity {

    public SpikeRockEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
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
