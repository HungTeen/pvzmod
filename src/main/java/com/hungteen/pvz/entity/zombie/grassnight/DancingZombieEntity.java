package com.hungteen.pvz.entity.zombie.grassnight;

import java.util.List;

import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DancingZombieEntity extends PVZZombieEntity{

	private static final DataParameter<Integer> SUMMON_TIME = EntityDataManager.defineId(DancingZombieEntity.class, DataSerializers.INT);
	private static final int DANCER_NUM = 4;
	private static final float[][] POS_OFFSET = new float[][] {{2,0},{-2,0},{0,2},{0,-2}};
	public static final int SUMMON_CD = 10;
	public static final int DANCE_CD = 100;
	private final int minWalkCD = 60;
	private final int maxWalkCD = 300;
	private int walkCD = 0;
	
	public DancingZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(SUMMON_TIME, 0);
	}
	
	@Override
	public void tick() {
		super.tick();
		if(! level.isClientSide) {
			this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(this.getAttackTime() > 0 ? 0 : ZombieUtil.LITTLE_SLOW);
		}
	}

	@Override
	public void normalZombieTick() {
		super.normalZombieTick();
		if(! level.isClientSide) {
			if(this.getSummonTime() > 0) {
				this.setSummonTime(this.getSummonTime() - 1);
				return ;
			}
			if(this.getAttackTime() > 0) {
				this.setAttackTime(this.getAttackTime() - 1);
			} else if(this.getAttackTime() == 0) {
				if(this.walkCD == 0) {
					if(this.getRandom().nextFloat() < 0.05f) {//can dance now
	    	            this.setAttackTime(DANCE_CD);
	    	            int num = this.shouldSummon();
	    	            if(num > 0) {
	    	            	this.setSummonTime(SUMMON_CD);
	    	            	this.summonDancer(num);
	    	            }
	    	            this.walkCD = this.getRandom().nextInt(this.maxWalkCD - this.minWalkCD + 1) + this.minWalkCD;
					}
				} else if(this.walkCD > 0) {
	    		    -- this.walkCD;
				}
			}
		}
	}
	
	private void summonDancer(int num) {
		for(int i = 0; i < DANCER_NUM; ++ i) {
			if(this.getRandom().nextInt(4) < num) {
				BackupDancerEntity dancer = EntityRegister.BACKUP_DANCER.get().create(level);
				BlockPos pos = this.blockPosition().offset(POS_OFFSET[i][0], 0, POS_OFFSET[i][1]);
				dancer.setOwnerId(this.getId());
				dancer.setCharmed(this.isCharmed());
				EntityUtil.onMobEntitySpawn(level, dancer, pos);
			}
		}
	}
	
	private int shouldSummon() {
		List<BackupDancerEntity> list = this.level.getEntitiesOfClass(BackupDancerEntity.class, EntityUtil.getEntityAABB(this, 50, 50), (zombie)->{
			return this.canSee(zombie) && ! zombie.isCharmed() && zombie.isAlive() && zombie.getDancerOwner() == this;
		});
		return DANCER_NUM - list.size(); 
	}
	
	@Override
	public float getLife() {
		return 50;
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("zombie_summon_tick")) {
			this.setSummonTime(compound.getInt("zombie_summon_tick"));
		}
		if(compound.contains("zombie_walk_cd")) {
			this.walkCD = compound.getInt("zombie_walk_cd");
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("zombie_summon_tick", this.getSummonTime());
		compound.putInt("zombie_walk_cd", this.walkCD);
	}
	
	public int getSummonTime(){
		return entityData.get(SUMMON_TIME);
	}
	
	public void setSummonTime(int time){
		entityData.set(SUMMON_TIME, time);
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.DANCING_ZOMBIE;
	}

}
