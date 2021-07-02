package com.hungteen.pvz.common.entity.plant.light;

import com.hungteen.pvz.common.entity.drop.SunEntity;
import com.hungteen.pvz.common.entity.plant.base.PlantProducerEntity;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class SunFlowerEntity extends PlantProducerEntity{

	public SunFlowerEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public void genSomething() {
		this.genSun(this.getSunAmount());
	}

	@Override
	public void genSuper() {
		SunEntity.spawnSunsByAmount(level, blockPosition(), this.getSuperSunAmount(), 100, 3);
		EntityUtil.playSound(this, SoundEvents.EXPERIENCE_ORB_PICKUP);
	}

	/**
	 * get normal gen sun amount by level.
	 */
	public int getSunAmount(){
		return MathUtil.getProgressByDif(4, 5, this.getPlantLvl(), PlantUtil.MAX_PLANT_LEVEL, 25, 50);
	}
	
	/**
	 * get normal gen sun amount by level.
	 */
	public int getSuperSunAmount(){
		if(this.isPlantInStage(1)) {
			return 500;
		}
		if(this.isPlantInStage(2)) {
			return 750;
		}
		return 1000;
	}
	
	@Override
	public int getGenCD() {//slow down 4 times at night or rain.
		final int time = 500;
		return this.level.isDay() && ! this.level.isRaining() ? time : 4 * time;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.8f, 1.65f);
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.SUN_FLOWER;
	}
	
	@Override
	public Plants getUpgradePlantType() {
		return Plants.TWIN_SUNFLOWER;
	}

}
