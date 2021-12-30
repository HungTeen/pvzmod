package com.hungteen.pvz.common.entity.plant.appease;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.impl.plant.PVZPlants;

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
		if(level.isClientSide) {
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
		return 1;
//		return this.getThreeStage(2, 4, 6);
	}
	
	@Override
	public int getSuperTimeLength() {
		return 200;
	}
	
	@Override
	public void startShootAttack() {
		this.setAttackTime(4);
	}
	
	@Override
	public IPlantType getPlantType() {
		return PVZPlants.GATLING_PEA;
	}
	
}
