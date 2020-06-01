package com.hungteen.pvzmod.entities.plants.base;

import com.hungteen.pvzmod.util.interfaces.IGenPlant;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public abstract class EntityGenPlantBase extends EntityPlantBase implements IGenPlant{

	private static final DataParameter<Boolean> IS_GEN_TIME = EntityDataManager.createKey(EntityGenPlantBase.class, DataSerializers.BOOLEAN);
	
	public EntityGenPlantBase(World worldIn) {
		super(worldIn);
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(IS_GEN_TIME, false);
	}

	@Override
	public void onNormalPlantUpdate() {
		super.onNormalPlantUpdate();
		if(this.isPlantInSuperMode()){//·Å´óÕÐ 
			if(this.getSuperTime()==1) {
			    if(!this.world.isRemote) {
			        this.genSuper();
			    }
			}
			this.setIsGenTime(true);
			return ;
        }
		this.setAttackTime(this.getAttackTime()+1);
//		System.out.println(this.getIsGenTime());
		if(!this.world.isRemote) {
		    if(this.getAttackTime()+5>=this.getAttackCD()) {
			    this.setIsGenTime(true);
		    }
		    else{
			    this.setIsGenTime(false);
		    }
		}
		if(this.getAttackTime()>=this.getAttackCD()) {
			if(!this.world.isRemote) {
				this.genSomething();
			}
			this.setAttackTime(0);
		}
	}
	
//	protected void playGenEffect(int num)
//	{
//		;
//	}
	
	@Override
	public int getSuperTimeLength() {
		return 7;
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		this.setIsGenTime(compound.getBoolean("is_gen_time"));
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setBoolean("is_gen_time", this.getIsGenTime());
	}
	
	public boolean getIsGenTime()
	{
		return dataManager.get(IS_GEN_TIME);
	}
	
	public void setIsGenTime(boolean is)
	{
		this.dataManager.set(IS_GEN_TIME, is);
	}
}
