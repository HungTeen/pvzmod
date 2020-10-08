package com.hungteen.pvz.entity.plant.defence;

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
	public EntitySize getSize(Pose poseIn) {
		return new EntitySize(0.9f, 2f, false);
	}
	
	@Override
	public float getPlantHealth() {
		int lvl = this.getPlantLvl();
		if(lvl<=19) return 790+10*lvl;
		if(lvl<=20) return 1000;
		return 800;
	}
	
	@Override
	public float getSuperLife() {
		int lvl = this.getPlantLvl();
		if(lvl<=6) return 800;
		if(lvl<=13) return 1000;
		if(lvl<=20) return 1200;
		return 800;
	}
	
	@Override
	protected float getAttractRange() {
		return 8;
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.TALL_NUT;
	}
	
}
