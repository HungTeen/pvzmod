package com.hungteen.pvz.entity.plant.magic;

import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.entity.plant.base.PlantBomberEntity;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class CoffeeBeanEntity extends PlantBomberEntity{

	public CoffeeBeanEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public void startBomb() {
		if(!this.world.isRemote) {
			float len = this.getAwakeRange();
			AxisAlignedBB aabb=EntityUtil.getEntityAABB(this, len, len);
			for(PVZPlantEntity plant : world.getEntitiesWithinAABB(PVZPlantEntity.class, aabb)) {
				if(!EntityUtil.checkCanEntityAttack(this, plant)) {
					plant.setSleepTime(- this.getAwakeTime());
				}
			}
		}
	}
	
	public float getAwakeRange() {
		return this.getPlantLvl() <= 10 ? 1.5f : 2.5f;
	}
	
	public int getAwakeTime() {
		int lvl = this.getPlantLvl();
		if(lvl <= 6) {
			return 24000;
		}else if(lvl <= 13) {
			return 36000;
		}else if(lvl <= 20) {
			return 48000;
		}
		return 24000;
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
