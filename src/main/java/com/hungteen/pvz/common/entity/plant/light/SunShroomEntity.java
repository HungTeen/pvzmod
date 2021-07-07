package com.hungteen.pvz.common.entity.plant.light;

import com.hungteen.pvz.common.entity.drop.SunEntity;
import com.hungteen.pvz.common.entity.plant.base.PlantProducerEntity;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class SunShroomEntity extends PlantProducerEntity {
	
	public static final int GROW_CD = 24000;
	public static final int GROW_ANIM_CD = 10;
	
	public SunShroomEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	public void genSomething() {
		this.genSun(this.getCurrentSunAmount());
	}

	@Override
	public void genSuper() {
		SunEntity.spawnSunsByAmount(level, blockPosition(), this.getSuperSunAmount(), 100, 3);
		EntityUtil.playSound(this, SoundEvents.EXPERIENCE_ORB_PICKUP);
	}
	
	@Override
	public void startSuperMode(boolean first) {
		super.startSuperMode(first);
		if(! this.isInGrowStage(2)) {
			this.growUpTo(2);
		}
	}
	
	/**
	 * sun shroom grow up progress
	 */
	protected void growUpTo(int stage) {
		this.setExistTick(GROW_CD * (stage - 1) - GROW_ANIM_CD - 2); 
		EntityUtil.playSound(this, SoundRegister.PLANT_GROW.get());
	}
	
	/**
	 * {@link #getCurrentSunAmount()}
	 */
	public boolean isInGrowStage(int stage) {
		return this.getExistTick() > GROW_CD * (stage - 1);
	}
	
	/**
	 * get current sun gen num;
	 */
	protected int getCurrentSunAmount() {
		return this.isInGrowStage(3) ? this.getSunAmountInStage(3) : 
			   this.isInGrowStage(2) ? this.getSunAmountInStage(2) : 
			   this.getSunAmountInStage(1);
	}
	
	/**
	 * get sun amount when grow up.
	 * use in almanac.
	 */
	public int getSunAmountInStage(int stage){
		if(stage == 1) {
			return this.getPlantLvl() <= 15 ? 15 : 25;
		}
		if(stage == 2) {
			return this.getPlantLvl() <= 5 ? 25 : this.getPlantLvl() <= 10 ? 35 : 50;
		}
		return 75;
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
	public int getGenCD() {
		return 600;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return this.isInGrowStage(3) ? EntitySize.scalable(0.8f, 1.2f): 
			   this.isInGrowStage(2) ? EntitySize.scalable(0.6f, 1f) :
			   EntitySize.scalable(0.4f, 0.4f);
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.SUN_SHROOM;
	}

}
