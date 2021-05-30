package com.hungteen.pvz.common.entity.zombie;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

public abstract class PVZZombieToolBase extends MobEntity{

	protected int liveTick = 0;
	private final int maxLiveTick = 3;
	
	public PVZZombieToolBase(EntityType<? extends MobEntity> type, World worldIn) {
		super(type, worldIn);
		this.setInvulnerable(true);
	}
	
	@Override
	public void aiStep() {
		super.aiStep();
		if(!level.isClientSide) {
			if(this.getPassengers().isEmpty()) {
				this.liveTick++;
			}else {
				this.liveTick=0;
			}
			if(this.liveTick>=this.maxLiveTick) {
				this.remove();
			}
		}
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		this.liveTick = compound.getInt("live_tick");
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("live_tick",this.liveTick);
	}

}
