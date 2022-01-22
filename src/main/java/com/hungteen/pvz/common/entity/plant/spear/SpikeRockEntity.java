package com.hungteen.pvz.common.entity.plant.spear;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
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
	public EntitySize getDimensions(Pose poseIn) {
		return new EntitySize(0.95f, 0.4f, false);
	}
	
	@Override
	public IPlantType getPlantType() {
		return PVZPlants.SPIKE_ROCK;
	}
	
}
