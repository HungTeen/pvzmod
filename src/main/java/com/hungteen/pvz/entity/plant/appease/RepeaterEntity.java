package com.hungteen.pvz.entity.plant.appease;

import com.hungteen.pvz.entity.bullet.PeaEntity.Type;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class RepeaterEntity extends PeaShooterEntity{

	protected int bigPeaNum;
	protected int shootBigTick;
	protected final int SHOOT_BIG_CD = 100;
	
	public RepeaterEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.bigPeaNum=0;
		this.shootBigTick=0;
	}

	@Override
	public void normalPlantTick() {
		super.normalPlantTick();
		if(!this.world.isRemote) {
			this.shootBigTick++;
		}
	}
	
	@Override
	protected Type getShootType() {
		if(this.bigPeaNum>0&&this.shootBigTick>=SHOOT_BIG_CD) {
			this.shootBigTick=0;
			--this.bigPeaNum;
			return Type.BIG;
		}
		return Type.NORMAL;
	}
	
	@Override
	public void startSuperMode() {
		super.startSuperMode();
		if(!world.isRemote) {
			this.bigPeaNum+=this.getPlantLvl()<=13?1:2;//can shoot extra big pea
		}
	}
	
	@Override
	public void startShootAttack() {
		this.setAttackTime(2);
	}
	
	@Override
	public int getSuperTimeLength() {
		int lvl=this.getPlantLvl();
		if(lvl<=6) return 120;
		else if(lvl<=13) return 150;
		else if(lvl<=20) return 200;
		return 120;
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.REPEATER;
	}
}
