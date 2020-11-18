package com.hungteen.pvz.entity.plant.appease;

import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class GatlingPeaEntity extends PeaShooterEntity{

	public int animTime = 0;
	
	public GatlingPeaEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public void tick() {
		super.tick();
		if(world.isRemote) {
			if(this.getAttackTime() > 0) {
				this.animTime = 10;
			} else {
				if(this.getAttackTime() == 0 && this.animTime > 0) {
					-- this.animTime;
				}
			}
		}
	}
	
	@Override
	public void startShootAttack() {
		this.setAttackTime(4);
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.GATLING_PEA;
	}
	
}
