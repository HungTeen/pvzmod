package com.hungteen.pvzmod.entities.plants.common;

import com.hungteen.pvzmod.entities.bullets.EntityButter;
import com.hungteen.pvzmod.entities.bullets.EntityKernel;
import com.hungteen.pvzmod.entities.bullets.EntityPult;
import com.hungteen.pvzmod.entities.plants.base.EntityPultBase;
import com.hungteen.pvzmod.registry.PotionRegister;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityKernelPult extends EntityPultBase{

	private static final DataParameter<Boolean> IS_BUTTER_NOW = EntityDataManager.createKey(EntityCabbagePult.class, DataSerializers.BOOLEAN);
	
	public EntityKernelPult(World worldIn) {
		super(worldIn);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(IS_BUTTER_NOW, (this.getRNG().nextInt(2)==1)?true:false);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compund) {
		super.readEntityFromNBT(compund);
		this.setIsButterNow(compund.getBoolean("is_butter_now"));
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setBoolean("is_butter_now", this.getIsButterNow());
	}
	
	@Override
	protected EntityPult getBullet() {
        if(this.isPlantInSuperMode()&&!this.getIsSuperOut()) {
        	this.setIsSuperOut(true);
        	return new EntityButter(this.world,this,EntityPult.Type.EXPLODE);
        }
        if(this.getIsButterNow()) return new EntityButter(this.world,this,EntityPult.Type.NORMAL);
        return new EntityKernel(this.world,this,EntityPult.Type.NORMAL);
	}
	
	@Override
	protected void changeBullet() {
		if(this.getRNG().nextInt(5)==1) this.setIsButterNow(true);
		else this.setIsButterNow(false);
	}
	
	@Override
	public float getAttackDamage() {
		int lvl=this.getPlantLvl();
		if(lvl<=20) {
			int now=(lvl-1)/2;
			return 3+now;
		}
		return 3;
	}
	
	public PotionEffect getButterEffect()
	{
		int lvl=this.getPlantLvl();
		int time=0;
		if(lvl<=20) {
			int now=(lvl-1)/4;
			time=8+2*now;
		}
		return new PotionEffect(PotionRegister.BUTTER_EFFECT, time*20, 1, false, false);
	}
	
	public void setIsButterNow(boolean is)
	{
		dataManager.set(IS_BUTTER_NOW, is);
	}
	
	public boolean getIsButterNow()
	{
		return dataManager.get(IS_BUTTER_NOW);
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.KERNEL_PULT;
	}
}
