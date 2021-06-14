package com.hungteen.pvz.common.entity.zombie.grassnight;

import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

public class BackupDancerEntity extends PVZZombieEntity{

	public static final int DANCE_CD = 100;
	private int ownerId;
	private final int minWalkCD = 60;
	private final int maxWalkCD = 300;
	private int walkCD = 0;
	
	public BackupDancerEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	public void zombieTick() {
		super.zombieTick();
		if(! level.isClientSide) {
			this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(this.getAttackTime() > 0 ? 0 : ZombieUtil.WALK_LITTLE_SLOW);
		}
	}
	
	@Override
	public void normalZombieTick() {
		super.normalZombieTick();
		if(this.getAttackTime() < 0) {
			return ;
		}
		if(! level.isClientSide) {
			if(this.needFollow()) {
			    this.setAttackTime(this.getDancerOwner().getAttackTime());
			    this.yRot = this.getDancerOwner().yRot;
			    this.xRot = this.getDancerOwner().xRot;
			    this.yHeadRot = this.getDancerOwner().yHeadRot;
		    } else {
			    if(this.getAttackTime() == 0) {
			    	if(this.walkCD == 0 && this.getRandom().nextFloat() < 0.05f) {
			    	    this.setAttackTime(DANCE_CD);
			    	    this.walkCD = this.getRandom().nextInt(this.maxWalkCD - this.minWalkCD + 1) + this.minWalkCD;
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
    	Entity entity = this.level.getEntity(ownerId);
    	if(entity instanceof DancingZombieEntity) {
    		return (DancingZombieEntity) entity;
    	}
    	return null;
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
    	super.readAdditionalSaveData(compound);
    	if(compound.contains("dancer_owner_id")) {
    		this.ownerId = compound.getInt("dancer_owner_id");
    	}
    	if(compound.contains("zombie_walk_cd")) {
    		this.walkCD = compound.getInt("zombie_walk_cd");
    	}
    }
    
    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
    	super.addAdditionalSaveData(compound);
    	compound.putInt("dancer_owner_id", this.ownerId);
    	compound.putInt("zombie_walk_cd", this.walkCD);
    }
    
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.BACKUP_DANCER;
	}

}
