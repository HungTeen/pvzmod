package com.hungteen.pvz.common.entity.plant.defence;

import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.world.World;

public class TallNutEntity extends WallNutEntity{

	public TallNutEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return new EntitySize(0.9f, 1.9f, false);
	}
	
	@Override
	public float getPlantHealth() {
		int lvl = this.getPlantLvl();
		if(lvl <= 19) return 790 + 10 * lvl;
		return 1000;
	}
	
	@Override
	public float getSuperLife() {
		if(this.isPlantInStage(1)) return 800;
		if(this.isPlantInStage(2)) return 1000;
		return 1200;
	}
	
	@Override
	public float getAttractRange() {
		return 5;
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.TALL_NUT;
	}
	
}
