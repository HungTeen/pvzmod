package com.hungteen.pvz.common.entity.plant.magic;

import com.hungteen.pvz.common.advancement.trigger.EntityEffectAmountTrigger;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.plant.base.PlantBomberEntity;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class CoffeeBeanEntity extends PlantBomberEntity{

	public CoffeeBeanEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.canCollideWithPlant = false;
		this.isImmuneToWeak = true;
	}

	@Override
	public void startBomb() {
		if(!this.level.isClientSide) {
			float len = this.getAwakeRange();
			AxisAlignedBB aabb=EntityUtil.getEntityAABB(this, len, len + 2);
			boolean hasEffect = false;
			int awakeCnt = 0;
			for(PVZPlantEntity plant : level.getEntitiesOfClass(PVZPlantEntity.class, aabb)) {
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
	
	public float getAwakeRange() {
		return this.getPlantLvl() <= 10 ? 1.5f : 2.5f;
	}
	
	public int getAwakeTime() {
		int lvl = this.getPlantLvl();
		if(lvl <= 20) {
			return 22800 + 1200 * lvl;
		}
		return 48000;
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
	public Plants getPlantEnumName() {
		return Plants.COFFEE_BEAN;
	}

}
