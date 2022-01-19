package com.hungteen.pvz.common.entity.misc.drop;

import com.hungteen.pvz.api.interfaces.ICollectible;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

public abstract class DropEntity extends MobEntity implements ICollectible {

	private static final DataParameter<Integer> AMOUNT = EntityDataManager.defineId(DropEntity.class, DataSerializers.INT);
	private static final DataParameter<Integer> STATE  = EntityDataManager.defineId(DropEntity.class, DataSerializers.INT);
	protected int liveTime = 0;
	
	public DropEntity(EntityType<? extends MobEntity> type, World worldIn) {
		super(type, worldIn);
		this.setInvulnerable(true);
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(AMOUNT, 1);
		this.entityData.define(STATE, DropStates.NORMAL.ordinal());
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(4, new SwimGoal(this));
	}
	
	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		this.onDropped();
		return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}
	
	@Override
	public void tick() {
		super.tick();
		this.noPhysics = this.getDropState() != DropStates.NORMAL;
		
		if(! level.isClientSide) {
			if(this.getDropState() == DropStates.NORMAL) {
				++ this.liveTime;
			}
		    if(this.liveTime >= this.getMaxLiveTick()) {
			    this.remove();
		    }
		}
	}
	
	@Override
	public void playerTouch(PlayerEntity entityIn) {// collect by colliding with entity.
		if(this.canCollectBy(entityIn)) {
			this.onCollect(entityIn);
		}
	}
	
	@Override
	public boolean canCollectBy(LivingEntity living) {
		return living instanceof PlayerEntity && EntityUtil.isEntityValid(this) && this.getDropState() != DropStates.STEAL;
	}
	
	@Override
	public void onCollect(LivingEntity living) {
		if(living instanceof PlayerEntity) {
			this.onCollectedByPlayer((PlayerEntity) living);
		}
		this.remove();
	}
	
	/**
	 * run when collect the drop.
	 */
	public abstract void onCollectedByPlayer(PlayerEntity player);
	
	/**
	 * drop live tick,read from config file
	 */
	protected abstract int getMaxLiveTick();
	
	/**
	 * called when first join to world.
	 */
	protected void onDropped() {
		
	}

	@Override
	protected void doPush(Entity entityIn) {
	}

	@Override
	protected void pushEntities() {
	}

	@Override
	public boolean isPickable() {
		return false;
	}
	
	@Override
	public void onSyncedDataUpdated(DataParameter<?> key) {
		if(AMOUNT.equals(key)) {
			this.refreshDimensions();
		}
		super.onSyncedDataUpdated(key);
	}
	
	@Override
	public boolean causeFallDamage(float distance, float damageMultiplier) {
		return false;
	}
	
	@Override
	protected void playBlockFallSound() {
		return ;
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("live_time")) {
			this.liveTime = compound.getInt("live_time");
		}
		if(compound.contains("drop_amount")) {
			this.setAmount(compound.getInt("drop_amount"));
		}
		if(compound.contains("drop_state")) {
			this.setDropState(DropStates.values()[compound.getInt("drop_state")]);
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("live_time", this.liveTime);
		compound.putInt("drop_amount", this.getAmount());
		compound.putInt("drop_state", this.getDropState().ordinal());
	}
	
	public int getAmount(){
		return this.entityData.get(AMOUNT);
	}
	
	public void setAmount(int num){
		this.entityData.set(AMOUNT, num);
	}
	
	public DropStates getDropState(){
		return DropStates.values()[this.entityData.get(STATE)];
	}
	
	public void setDropState(DropStates state){
		this.entityData.set(STATE, state.ordinal());
	}
	
	public static enum DropStates {
		NORMAL,
		ABSORB,
		STEAL
	}
	
}