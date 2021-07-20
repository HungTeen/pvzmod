package com.hungteen.pvz.common.entity.plant.spear;

import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

public class SpikeRockEntity extends SpikeWeedEntity {

    public SpikeRockEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		if(! level.isClientSide) {
			this.setSpikeNum(this.getSpikesCount());
		}
		return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}
	
	public float getAttackDamage() {
		int lvl = this.getPlantLvl();
		if(lvl <= 16) {
			int now = (lvl - 1) / 2;
			return 3.75F + 0.25F * now;
		}
		return 6;
	}
	
	public int getSpikesCount() {
		int lvl = this.getPlantLvl();
		if(lvl <= 20) {
			int now = (lvl - 1) / 5;
			return 3 * now + 6;
		}
		return 15;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return new EntitySize(0.95f, 0.4f, false);
	}
	
	@Override
	public int getSuperTimeLength() {
		if(this.isPlantInStage(1)) return 100;
		if(this.isPlantInStage(2)) return 150;
		return 200;
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.SPIKE_ROCK;
	}
	
}
