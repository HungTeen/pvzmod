package com.hungteen.pvzmod.entities.plants.base;

import com.hungteen.pvzmod.Main;
import com.hungteen.pvzmod.particles.base.PVZParticleType;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public abstract class EntityShroomBase extends EntityPlantBase{

	private static final DataParameter<Integer> AWAKE_TIME = EntityDataManager.createKey(EntityShroomBase.class, DataSerializers.VARINT);
	private static final DataParameter<Boolean> IS_SLEEPING = EntityDataManager.createKey(EntityShroomBase.class, DataSerializers.BOOLEAN);
	
	public EntityShroomBase(World worldIn) {
		super(worldIn);
	}
	
	@Override
	public void onNormalPlantUpdate() {
		super.onNormalPlantUpdate();
		if(this.getAwakeTime()>0) {
			this.setAwakeTime(this.getAwakeTime()-1);
		}
//		if(this.ticksExisted%40==0) {
//			System.out.println(this.isSleeping());
//		}
		if(this.getIsSleeping()&&this.ticksExisted%10==0) {
			Main.proxy.spawnParticle(PVZParticleType.SLEEPING, this.posX, this.posY+this.getEyeHeight(), this.posZ, 0.05,0.05,0.05);
			//this.world.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, posX, posY+this.getEyeHeight(), posZ, (this.getRNG().nextFloat()-0.5f), this.getRNG().nextFloat(), (this.getRNG().nextFloat()-0.5f));
		}
		if(!this.world.isRemote) {
		    if(this.shouldSleepNow()) {
			    this.setIsSleeping(true);
		    }
		    else {
			    this.setIsSleeping(false);
		    }
		}
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(AWAKE_TIME, 0);
		dataManager.register(IS_SLEEPING, false);
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setInteger("shroom_awake_time", this.getAwakeTime());
		compound.setBoolean("is_shroom_sleeping",this.getIsSleeping());
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		this.setAwakeTime(compound.getInteger("shroom_awake_time"));
		this.setIsSleeping(compound.getBoolean("is_shroom_sleeping"));
	}
	
	@Override
	public boolean canStartSuperMode() {
		return !this.getIsSleeping();
	}
	
	public boolean shouldSleepNow()
	{
		return this.world.isDaytime()&&this.getAwakeTime()==0;
	}

	public void setAwakeTime(int time)
	{
		dataManager.set(AWAKE_TIME, time);
	}
	
	public int getAwakeTime()
	{
		return dataManager.get(AWAKE_TIME);
	}
	
	public void setIsSleeping(boolean is)
	{
		dataManager.set(IS_SLEEPING, is);
	}
	
	public boolean getIsSleeping()
	{
		return dataManager.get(IS_SLEEPING);
	}
}
