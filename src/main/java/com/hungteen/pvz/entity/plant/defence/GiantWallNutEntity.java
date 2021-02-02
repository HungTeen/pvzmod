package com.hungteen.pvz.entity.plant.defence;

import com.hungteen.pvz.entity.bullet.itembullet.NutEntity;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class GiantWallNutEntity extends WallNutEntity {

	public GiantWallNutEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		this.setDefenceLife(this.getSuperLife());
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}
	
	@Override
	public void startSuperMode(boolean first) {
		super.startSuperMode(first);
		if(! world.isRemote) {
			for(int i = 0; i < this.getNutCount(); ++ i) {
				NutEntity nut = new NutEntity(world, this);
				nut.setPosition(this.getPosX(), this.getPosY() + 1D, this.getPosZ());
				nut.shoot((this.getRNG().nextFloat() - 0.5f) * 1.5D, 0.5D, (this.getRNG().nextFloat() - 0.5f) * 1.5D);
				world.addEntity(nut);
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
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(2F, 3F);
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
