package com.hungteen.pvz.common.entity.plant.magic;

import com.hungteen.pvz.api.interfaces.IAlmanacEntry;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.advancement.trigger.EntityEffectAmountTrigger;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.plant.base.PlantBomberEntity;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.PAZAlmanacs;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;

public class CoffeeBeanEntity extends PlantBomberEntity{

	public CoffeeBeanEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.canCollideWithPlant = false;
		this.isImmuneToWeak = true;
		this.hasBombAlamancs = false;
	}

	@Override
	public void startBomb(boolean server) {
		if(! this.level.isClientSide) {
			final float len = this.getWorkRange();
			boolean hasEffect = false;
			int awakeCnt = 0;
			for(PVZPlantEntity plant : level.getEntitiesOfClass(PVZPlantEntity.class, EntityUtil.getEntityAABB(this, len, len))) {
				if(! EntityUtil.canTargetEntity(this, plant)) {
					if(plant.isPlantSleeping()) {
						++ awakeCnt;
					}
					plant.sleepTime = - this.getAwakeTime();
					hasEffect = true;
				}
			}
			PlayerEntity player = EntityUtil.getEntityOwner(level, this);
			if(player != null && player instanceof ServerPlayerEntity) {
				EntityEffectAmountTrigger.INSTANCE.trigger((ServerPlayerEntity) player, this, awakeCnt);
			}
			if(hasEffect) {
				EntityUtil.playSound(this, SoundRegister.WAKE_UP.get());
			}
		}
	}

	@Override
	public void addAlmanacEntries(List<Pair<IAlmanacEntry, Number>> list) {
		super.addAlmanacEntries(list);
		list.addAll(Arrays.asList(
				Pair.of(PAZAlmanacs.WORK_RANGE, this.getWorkRange()),
				Pair.of(PAZAlmanacs.AWAKE_TIME, this.getAwakeTime())
		));
	}

	public int getAwakeTime() {
		return 48000;
	}

	public float getWorkRange(){
		return 2.5F;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.6f, 0.8f);
	}
	
	@Override
	public boolean isNoGravity() {
		return true;
	}

	@Override
	public int getReadyTime() {
		return 30;
	}

	@Override
	public IPlantType getPlantType() {
		return PVZPlants.COFFEE_BEAN;
	}

}
