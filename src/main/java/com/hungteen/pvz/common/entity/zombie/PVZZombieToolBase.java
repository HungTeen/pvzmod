package com.hungteen.pvz.common.entity.zombie;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobEntity;
import net.minecraft.world.level.Level;

public abstract class PVZZombieToolBase extends MobEntity{

	protected int liveTick = 0;
	private final int maxLiveTick = 3;
	
	public PVZZombieToolBase(EntityType<? extends MobEntity> type, Level worldIn) {
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
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.liveTick = compound.getInt("live_tick");
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("live_tick",this.liveTick);
	}

}
