package com.hungteen.pvz.entity.plant.magic;

import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.entity.plant.base.PlantBomberEntity;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
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
		if(!this.world.isRemote) {
			float len = this.getAwakeRange();
			AxisAlignedBB aabb=EntityUtil.getEntityAABB(this, len, len + 2);
			boolean hasEffect = false;
			for(PVZPlantEntity plant : world.getEntitiesWithinAABB(PVZPlantEntity.class, aabb)) {
				if(! EntityUtil.checkCanEntityAttack(this, plant)) {
					plant.setSleepTime(- this.getAwakeTime());
					hasEffect = true;
				}
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
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(0.6f, 0.8f);
	}
	
	@Override
	public boolean hasNoGravity() {
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
