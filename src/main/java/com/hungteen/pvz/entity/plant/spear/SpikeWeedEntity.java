package com.hungteen.pvz.entity.plant.spear;

import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class SpikeWeedEntity extends SpikeRockEntity {

	public SpikeWeedEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	public float getAttackDamage(){
		int lvl = this.getPlantLvl();
		if(lvl <= 20) {
			int now = (lvl - 1) / 4;
			return 2 + 0.25f * now;
		}
		return 3;
	}
	
	public int getAttackCD() {
		int lvl = this.getPlantLvl();
		if(lvl <= 19) return 41 - lvl;
		return 20;
	}
	
	@Override
	public int getSpikesCount() {
		return 0;
	}
	
	@Override
	public int getSuperTimeLength() {
		if(this.isPlantInStage(1)) return 65;
		if(this.isPlantInStage(2)) return 85;
		return 105;
	}
	
	@Override
	public Plants getUpgradePlantType() {
		return Plants.SPIKE_ROCK;
	}
	
    @Override
	public Plants getPlantEnumName() {
		return Plants.SPIKE_WEED;
	}

}
