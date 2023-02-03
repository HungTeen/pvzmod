package com.hungteen.pvz.common.entity.plant.light;

import com.hungteen.pvz.api.interfaces.IAlmanacEntry;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.misc.drop.SunEntity;
import com.hungteen.pvz.common.entity.plant.base.PlantProducerEntity;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.PAZAlmanacs;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;

public class SunFlowerEntity extends PlantProducerEntity{

	public SunFlowerEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public void genSomething() {
		this.genSun(this.getSunAmount(), 1);
	}

	@Override
	public void genSuper() {
		SunEntity.spawnSunsByAmount(level, blockPosition(), this.getSuperSunAmount(), 100, 3);
		EntityUtil.playSound(this, SoundEvents.EXPERIENCE_ORB_PICKUP);
	}

	@Override
	public void addAlmanacEntries(List<Pair<IAlmanacEntry, Number>> list) {
		super.addAlmanacEntries(list);
		list.addAll(Arrays.asList(
				Pair.of(PAZAlmanacs.GEN_SUN_AMOUNT, this.getSunAmount())
		));
	}

	/**
	 * get normal gen sun amount by maxLevel.
	 */
	public int getSunAmount(){
		return 25;
	}
	
	/**
	 * get normal gen sun amount by maxLevel.
	 */
	public int getSuperSunAmount(){
		return 500;
	}
	
	@Override
	public int getGenCD() {//slow down 4 times at night or rain.
		final int time = 500;
		return this.level.isDay() && ! this.level.isRaining() ? time : 4 * time;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.8f, 1.3f);
	}
	
	@Override
	public IPlantType getPlantType() {
		return PVZPlants.SUN_FLOWER;
	}

}
