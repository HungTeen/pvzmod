package com.hungteen.pvz.entity.plant.appease;

import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class GatlingPeaEntity extends RepeaterEntity{

	public int animTime = 0;
	
	public GatlingPeaEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public void tick() {
		super.tick();
		if(world.isRemote) {
			if(this.getAttackTime() > 0) {
				this.animTime = 15;
			} else {
				if(this.getAttackTime() == 0 && this.animTime > 0) {
					-- this.animTime;
				}
			}
		}
	}
	
	@Override
	protected int getBigPeaNum() {
		if(this.isPlantInStage(1)) return 2;
		if(this.isPlantInStage(2)) return 3;
		return 4;
	}
	
	@Override
	public int getSuperTimeLength() {
		if(this.isPlantInStage(1)) return 200;
		if(this.isPlantInStage(2)) return 250;
		return 300;
	}
	
	@Override
	public void startShootAttack() {
		this.setAttackTime(4);
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.GATLING_PEA;
	}
	
	@Override
	public Plants getUpgradePlantType() {
		return null;
	}

}
