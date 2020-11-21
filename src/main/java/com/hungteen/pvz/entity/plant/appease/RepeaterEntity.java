package com.hungteen.pvz.entity.plant.appease;

import com.hungteen.pvz.entity.bullet.PeaEntity.Type;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class RepeaterEntity extends PeaShooterEntity{

	protected int bigPeaNum = 0;
	protected int shootBigTick = 0;
	protected final int SHOOT_BIG_CD = 100;
	
	public RepeaterEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public void normalPlantTick() {
		super.normalPlantTick();
		if(!this.world.isRemote) {
			this.shootBigTick ++;
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
		if(!world.isRemote) {
			this.bigPeaNum += this.getBigPeaNum();//can shoot extra big pea
		}
	}
	
	protected int getBigPeaNum() {
		return this.isPlantInStage(2) ? 1 : 2;
	}
	
	@Override
	public void startShootAttack() {
		this.setAttackTime(2);
	}
	
	@Override
	public int getSuperTimeLength() {
		if(this.isPlantInStage(1)) return 150;
		if(this.isPlantInStage(2)) return 175;
		return 200;
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.REPEATER;
	}

	@Override
	public Plants getUpgradePlantType() {
		return Plants.GATLING_PEA;
	}
}
