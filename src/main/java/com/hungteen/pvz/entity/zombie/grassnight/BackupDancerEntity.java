package com.hungteen.pvz.entity.zombie.grassnight;

import com.hungteen.pvz.entity.zombie.base.UnderGroundZombieEntity;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

public class BackupDancerEntity extends UnderGroundZombieEntity{

	public static final int DANCE_CD = 100;
	private int ownerId;
	private final int minWalkCD = 60;
	private final int maxWalkCD = 300;
	private int walkCD = 0;
	
	public BackupDancerEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	public void tick() {
		super.tick();
		if(! world.isRemote) {
			this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(this.getAttackTime() > 0 ? 0 : ZombieUtil.LITTLE_SLOW);
		}
	}
	
	@Override
	public void normalZombieTick() {
		super.normalZombieTick();
		if(this.getAttackTime() < 0) {
			return ;
		}
		if(! world.isRemote) {
			if(this.needFollow()) {
			    this.setAttackTime(this.getDancerOwner().getAttackTime());
			    this.rotationYaw = this.getDancerOwner().rotationYaw;
			    this.rotationPitch = this.getDancerOwner().rotationPitch;
			    this.rotationYawHead = this.getDancerOwner().rotationYawHead;
		    } else {
			    if(this.getAttackTime() == 0) {
			    	if(this.walkCD == 0 && this.getRNG().nextFloat() < 0.05f) {
			    	    this.setAttackTime(DANCE_CD);
			    	    this.walkCD = this.getRNG().nextInt(this.maxWalkCD - this.minWalkCD + 1) + this.minWalkCD;
			    	} else if(this.walkCD > 0) {
			    		-- this.walkCD;
			    	}
				} else if(this.getAttackTime() > 0) {
			    	this.setAttackTime(this.getAttackTime() - 1);
			    }
		    }
		}
	}
	
	@Override
	public float getLife() {
		return 20;
	}
	
	public boolean needFollow() {
		return this.getDancerOwner() != null && this.getDancerOwner().isAlive();
	}
	
    public void setOwnerId(int id) {
		this.ownerId = id;
	}
    
    public DancingZombieEntity getDancerOwner() {
    	Entity entity = this.world.getEntityByID(ownerId);
    	if(entity instanceof DancingZombieEntity) {
    		return (DancingZombieEntity) entity;
    	}
    	return null;
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
    	super.readAdditional(compound);
    	this.ownerId = compound.getInt("dancer_owner_id");
    	this.walkCD = compound.getInt("zombie_walk_cd");
    }
    
    @Override
    public void writeAdditional(CompoundNBT compound) {
    	super.writeAdditional(compound);
    	compound.putInt("dancer_owner_id", this.ownerId);
    	compound.putInt("zombie_walk_cd", this.walkCD);
    }
    
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.BACKUP_DANCER;
	}

}
