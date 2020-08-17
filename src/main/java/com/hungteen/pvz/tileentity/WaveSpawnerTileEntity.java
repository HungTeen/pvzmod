package com.hungteen.pvz.tileentity;

import com.hungteen.pvz.register.TileEntityRegister;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

public class WaveSpawnerTileEntity extends TileEntity implements ITickableTileEntity{

	protected int currentWave;
	protected boolean isStarted;
	
	public WaveSpawnerTileEntity() {
		super(TileEntityRegister.WAVE_SPAWNER.get());
		this.currentWave=0;
		this.isStarted=false;
	}
	
//	public void nextWave()
//	{
//		this.currentWave++;
//		this.markDirty();
//	}
//	
//	public int getCurrentWave()
//	{
//		return this.currentWave;
//	}
	
	public void checkAndStartWave()
	{
		if(!this.isStarted) {
			this.isStarted=true;
			this.markDirty();
		}
	}
	
	@Override
	public void tick() {
		if(!this.isStarted) return ;
		
	}

	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		this.currentWave=compound.getInt("current_wave");
		this.isStarted=compound.getBoolean("is_started");
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.putInt("current_wave", this.currentWave);
		compound.putBoolean("is_started", this.isStarted);
		return super.write(compound);
	}
}
