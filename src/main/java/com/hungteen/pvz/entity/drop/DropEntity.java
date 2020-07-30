package com.hungteen.pvz.entity.drop;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public abstract class DropEntity extends Entity{

	private static final DataParameter<Integer> AMOUNT = EntityDataManager.createKey(SunEntity.class, DataSerializers.VARINT);
	private int liveTime;
	
	public DropEntity(EntityType<? extends Entity> type, World worldIn) {
		super(type, worldIn);
		this.liveTime=0;
	}
	
	@Override
	protected void registerData() {
		this.dataManager.register(AMOUNT, 0);
	}
	
	@Override
	public void baseTick() {
		super.baseTick();
		this.liveTime++;
		if(this.liveTime>=this.getMaxLiveTick()) {
			this.remove();
		}
	}
	
	/**
	 * drop live tick,read from config file
	 */
	protected abstract int getMaxLiveTick();
	
	public int getAmount()
	{
		return this.dataManager.get(AMOUNT);
	}
	
	public void setAmount(int num)
	{
		this.dataManager.set(AMOUNT, num);
	}
	
	@Override
	protected void readAdditional(CompoundNBT compound) {
		this.liveTime=compound.getInt("live_time");
		this.setAmount(compound.getInt("drop_amount"));
	}

	@Override
	protected void writeAdditional(CompoundNBT compound) {
		compound.putInt("live_time", this.liveTime);
		compound.putInt("drop_amount",this.getAmount());
	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
