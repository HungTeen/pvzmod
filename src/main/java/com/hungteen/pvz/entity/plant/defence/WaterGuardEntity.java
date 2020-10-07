package com.hungteen.pvz.entity.plant.defence;

import com.hungteen.pvz.entity.plant.base.PlantDefenderEntity;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * removed in 1.15.2 - 0.2
 */
public class WaterGuardEntity extends PlantDefenderEntity{

	public WaterGuardEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if(!world.isRemote) {
			if(!this.world.isAirBlock(new BlockPos(this).up())) {
				this.setMotion(this.getMotion().getX(), 0.05f, this.getMotion().getZ());
			}else {
				this.setMotion(this.getMotion().getX(), 0, this.getMotion().getZ());
			}
		}
	}
	
	@Override
	public void startSuperMode() {
		super.startSuperMode();
		this.spawnGuard();
	}
	
	private void spawnGuard() {
//		if(!world.isRemote) {
//			int [] dx = new int[] {1, -1, 0, 0};
//			int [] dz = new int[] {0, 0, 1, -1};
//			for(int i=0;i<4;i++) {
//				WaterGuardEntity guard = EntityRegister.WATER_GUARD.get().create(world);
//				guard.setPosition(this.getPosX()+dx[i], this.getPosY(), this.getPosZ()+dz[i]);
//				PlantUtil.copyPlantData(guard, this);
//				world.addEntity(guard);
//			}
//		}
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return new EntitySize(0.8f, 0.8f, false);
	}
	
	@Override
	public boolean hasNoGravity() {
		return this.isInWater();
	}
	
	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}
	
	@Override
	protected void collideWithEntity(Entity entityIn) {
	}
	
	@Override
	protected boolean checkNormalPlantWeak() {//check if it leave water
		if(this.isImmuneToWeak) return false;
		return !this.isInWater();
	}
	
	@Override
	public float getSuperLife() {
		int lvl=this.getPlantLvl();
		if(lvl<=6) return 300;
		if(lvl<=13) return 400;
		if(lvl<=20) return 500;
		return 300;
	}
	
	@Override
	public int getSuperTimeLength() {
		return 20;
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.WATER_GUARD;
	}

}
