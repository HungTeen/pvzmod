package com.hungteen.pvz.common.entity.plant.appease;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.bullet.itembullet.PeaEntity.Type;
import com.hungteen.pvz.common.impl.plant.PVZPlants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class RepeaterEntity extends PeaShooterEntity{

	protected static final int SHOOT_BIG_CD = 100;
	protected int bigPeaNum = 0;
	protected int shootBigTick = 0;
	
	public RepeaterEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public void normalPlantTick() {
		super.normalPlantTick();
		if(!this.level.isClientSide) {
			++ this.shootBigTick;
		}
	}
	
	@Override
	protected Type getShootType() {
		if(! this.isPlantInSuperMode() && this.bigPeaNum > 0 && this.shootBigTick >= SHOOT_BIG_CD) {
			this.shootBigTick = 0;
			-- this.bigPeaNum;
			return Type.BIG;
		}
		return Type.NORMAL;
	}
	
	@Override
	public void startSuperMode(boolean first) {
		super.startSuperMode(first);
		if(!level.isClientSide) {
			this.bigPeaNum += this.getBigPeaNum();//can shoot extra big pea
		}
	}
	
	protected int getBigPeaNum() {
		return 1;
//		return this.isPlantInStage(1) ? 1 : this.isPlantInStage(2) ? 2 : 3;
	}
	
	@Override
	public void startShootAttack() {
		this.setAttackTime(2);
	}
	
	@Override
	public int getSuperTimeLength() {
		return 120;
	}
	
	@Override
	public IPlantType getPlantType() {
		return PVZPlants.REPEATER;
	}
}
