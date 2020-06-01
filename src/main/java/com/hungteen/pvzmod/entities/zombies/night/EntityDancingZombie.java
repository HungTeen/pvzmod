package com.hungteen.pvzmod.entities.zombies.night;

import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.SoundsHandler;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class EntityDancingZombie extends EntityZombieBase{

	private static final DataParameter<Integer> DANCE_TIME = EntityDataManager.createKey(EntityDancingZombie.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> SUMMON_TIME = EntityDataManager.createKey(EntityDancingZombie.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> WALK_TIME = EntityDataManager.createKey(EntityDancingZombie.class, DataSerializers.VARINT);
	private EntityBackupDancer left_dancer=null;
	private EntityBackupDancer right_dancer=null;
	private EntityBackupDancer front_dancer=null;
	private EntityBackupDancer back_dancer=null;
	
	public EntityDancingZombie(World worldIn) {
		super(worldIn);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(DANCE_TIME, 0);
		dataManager.register(SUMMON_TIME, 0);
		dataManager.register(WALK_TIME, 0);
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50);
	}
	
	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		if(this.getIsFrozen()) {
			return ;
		}
		if(this.getDanceTime()>0) {//跳舞就跳舞 不许打扰
			this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0);
			this.setDanceTime(this.getDanceTime()-1);
			return ;
		}
		if(this.getSummonTime()>0) {//跳完舞 检测是否需要召唤
			if(this.getSummonTime()==1) {
				if(!this.world.isRemote) {
					summonDancers();
					this.world.playSound(null, this.posX, this.posY, this.posZ, SoundsHandler.START_DANCE,SoundCategory.VOICE,4f, 1f);
				}
			}
			this.setSummonTime(this.getSummonTime()-1);
			return ;
		}
		if(this.getWalkTime()>0) {
			this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(EntityUtil.NORMAL_SPEED);
			if(this.getWalkTime()==1) {
				this.setDanceTime(100);   //设置跳舞时间
				if(checkSummon()) {
			    	this.setSummonTime(20);
			    }
			}
			this.setWalkTime(this.getWalkTime()-1);
		}else {
			this.setWalkTime(100);//什么行动都没了就走路呗~
		}
	}
	
	private boolean checkSummon()
	{
		if(this.left_dancer==null||this.left_dancer.isDead) return true;
		if(this.right_dancer==null||this.right_dancer.isDead) return true;
		if(this.front_dancer==null||this.front_dancer.isDead) return true;
		if(this.back_dancer==null||this.back_dancer.isDead) return true;
		return false;
	}
	
	private void summonDancers()
	{
		if(this.left_dancer==null||this.left_dancer.isDead) {
			this.left_dancer=new EntityBackupDancer(world);
			this.left_dancer.setPosition(posX-2, posY, posZ);
			this.left_dancer.setOwner(this);
			this.world.spawnEntity(this.left_dancer);
		}
		if(this.right_dancer==null||this.right_dancer.isDead) {
			this.right_dancer=new EntityBackupDancer(world);
			this.right_dancer.setPosition(posX+2, posY, posZ);
			this.right_dancer.setOwner(this);
			this.world.spawnEntity(this.right_dancer);
		}
		if(this.front_dancer==null||this.front_dancer.isDead) {
			this.front_dancer=new EntityBackupDancer(world);
			this.front_dancer.setPosition(posX, posY, posZ+2);
			this.front_dancer.setOwner(this);
			this.world.spawnEntity(this.front_dancer);
		}
		if(this.back_dancer==null||this.back_dancer.isDead) {
			this.back_dancer=new EntityBackupDancer(world);
			this.back_dancer.setPosition(posX, posY, posZ-2);
			this.back_dancer.setOwner(this);
			this.world.spawnEntity(this.back_dancer);
		}
	}
	
	public int getDanceTime()
	{
		return dataManager.get(DANCE_TIME);
	}
	
	public int getSummonTime()
	{
		return dataManager.get(SUMMON_TIME);
	}
	
	public void setDanceTime(int time)
	{
		dataManager.set(DANCE_TIME, time);
	}
	
	public void setSummonTime(int time)
	{
		dataManager.set(SUMMON_TIME, time);
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
