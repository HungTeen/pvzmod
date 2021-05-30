package com.hungteen.pvz.common.entity.plant.defence;

import com.hungteen.pvz.common.entity.bullet.itembullet.NutEntity;
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

public class GiantWallNutEntity extends WallNutEntity {

	public GiantWallNutEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		this.setDefenceLife(this.getSuperLife());
		return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}
	
	@Override
	public void startSuperMode(boolean first) {
		super.startSuperMode(first);
		if(! level.isClientSide) {
			for(int i = 0; i < this.getNutCount(); ++ i) {
				NutEntity nut = new NutEntity(level, this);
				nut.setPos(this.getX(), this.getY() + 1D, this.getZ());
				nut.shoot((this.getRandom().nextFloat() - 0.5f) * 1.5D, 0.5D, (this.getRandom().nextFloat() - 0.5f) * 1.5D);
				level.addFreshEntity(nut);
			}
		}
	}
	
	@Override
	public float getAttractRange() {
		return 7;
	}
	
	@Override
	public float getPlantHealth() {
		int lvl = this.getPlantLvl();
		if(lvl <= 19) return 790 + 10 * lvl;
		return 1000;
	}
	
	@Override
	public float getSuperLife() {
		int lvl = this.getPlantLvl();
		if(lvl <= 19) return 970 + 30 * lvl;
		return 1600;
	}
	
	public int getNutCount() {
		if(this.isPlantInStage(1)) return 50;
		if(this.isPlantInStage(2)) return 75;
		return 100;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(2F, 3F);
	}
	
	@Override
	public Plants getUpgradePlantType() {
		return null;
	}
	
	@Override
	public int getSuperTimeLength() {
		return 50;
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.GIANT_WALL_NUT;
	}

}
