package com.hungteen.pvzmod.entities.zombies.night;

import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.util.EntityUtil;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class EntityBackupDancer extends EntityZombieBase{

	private EntityDancingZombie owner;
	private static final DataParameter<Integer> DANCE_TIME = EntityDataManager.createKey(EntityBackupDancer.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> WALK_TIME = EntityDataManager.createKey(EntityBackupDancer.class, DataSerializers.VARINT);
	
	public EntityBackupDancer(World worldIn) {
		super(worldIn);
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(DANCE_TIME, 0);
		dataManager.register(WALK_TIME, 0);
	}
	
	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
//		if(this.world.isRemote) return ;
		if(this.owner!=null&&!this.owner.isDead) {
			this.setDanceTime(this.owner.getDanceTime());
			if(this.getDanceTime()>0) this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0);
			else this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(EntityUtil.NORMAL_SPEED);
			this.setWalkTime(this.owner.getWalkTime());
			return ;
		}
		if(this.getDanceTime()>0) {//跳舞就跳舞 不许打扰
			this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0);
			this.setDanceTime(this.getDanceTime()-1);
			return ;
		}
		if(this.getWalkTime()>0) {
			this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(EntityUtil.NORMAL_SPEED);
			if(this.getWalkTime()==1) {
				this.setDanceTime(100);   //设置跳舞时间
			}
			this.setWalkTime(this.getWalkTime()-1);
		}else {
			this.setWalkTime(100);//什么行动都没了就走路呗~
		}
	}
	
	public void setOwner(EntityDancingZombie owner)
	{
		this.owner=owner;
	}
	
	public int getDanceTime()
	{
		return dataManager.get(DANCE_TIME);
	}
	
	public void setDanceTime(int time)
	{
		dataManager.set(DANCE_TIME, time);
	}
	
	public int getWalkTime()
	{
		return dataManager.get(WALK_TIME);
	}
	
	public void setWalkTime(int time)
	{
		dataManager.set(WALK_TIME, time);
	}
	
}
