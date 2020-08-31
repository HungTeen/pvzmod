package com.hungteen.pvz.entity.zombie;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

public abstract class PVZZombieToolBase extends MobEntity{

	protected int liveTick = 0;
	private final int maxLiveTick = 5;
	
	public PVZZombieToolBase(EntityType<? extends MobEntity> type, World worldIn) {
		super(type, worldIn);
		this.setInvulnerable(true);
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
	}
	
	@Override
	public void livingTick() {
		super.livingTick();
		if(!world.isRemote) {
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
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.liveTick = compound.getInt("live_tick");
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("live_tick",this.liveTick);
	}

}
