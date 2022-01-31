package com.hungteen.pvz.common.entity.zombie.other;

import com.hungteen.pvz.common.entity.misc.ZombieHandEntity;
import com.hungteen.pvz.common.entity.zombie.base.AbstractBossZombieEntity;
import com.hungteen.pvz.common.impl.zombie.ZombieType;
import com.hungteen.pvz.common.impl.zombie.CustomZombies;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

public class CoffinEntity extends AbstractBossZombieEntity {

	private static final DataParameter<Integer> GUARD_STATE = EntityDataManager.defineId(CoffinEntity.class, DataSerializers.INT);

	public CoffinEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.setIsWholeBody();
		this.kickRange = 3;
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(GUARD_STATE, 0);
	}
	
	@Override
	protected void initAttributes() {
		super.initAttributes();
		this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(ZombieUtil.LITTLE_LOW);
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.WALK_SLOW);
	}

	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		if (! level.isClientSide) {
			EntityUtil.playSound(this, SoundRegister.DIRT_RISE.get());
			ZombieHandEntity.spawnRangeZombieHands(level, this, 3);
		}
		return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	@Override
	public void zombieTick() {
		super.zombieTick();
		if(!level.isClientSide && this.isAlive()) {
			for(int i = 0; i < 4; i ++) {
				if(this.isGuardGone(i)) {
					continue;
				}
				if(this.bossInfo.getPercent() < (5 - i) * 1.0f / 6f) {
					this.setGuardStateById(i, 1);
					MournerZombieEntity zombie = EntityRegister.MOURNER_ZOMBIE.get().create(level);
					zombie.setRightShake((i & 1) == 0);
					this.onBossSummon(zombie, this.getTarget() == null ? this.blockPosition() : this.getTarget().blockPosition());
				}
			}
		}
	}

	@Override
	protected void onRemoveWhenDeath() {
		if(!level.isClientSide) {
		    NobleZombieEntity boss = EntityRegister.NOBLE_ZOMBIE.get().create(level);
		    this.onBossSummon(boss, blockPosition().above());
		}
	}

	@Override
	protected int getDeathTime() {
		return 1;
	}

	@Override
	protected double getCollideWidthOffset() {
		return 0.5;
	}
	
	@Override
	public float getLife() {
		return 1000;
	}

	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(2f, 2f);
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("guard_state")) {
			this.setGuardState(compound.getInt("guard_state"));
		}
		if (this.hasCustomName()) {
			this.bossInfo.setName(this.getDisplayName());
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("guard_state", this.getGuardState());
	}

	public int getGuardState() {
		return this.entityData.get(GUARD_STATE);
	}
	
	public void setGuardState(int state) {
		this.entityData.set(GUARD_STATE, state);
	}
	
	public boolean isGuardGone(int id) {
		return ((this.getGuardState() >> id) & 1) == 1;
	}
	
	public void setGuardStateById(int id, int is) {
		int state = this.getGuardState();
		if((is ^ (state >> id) & 1) == 1) {
			state ^= (1 << id);
		}
		this.entityData.set(GUARD_STATE, state);
	}

	@Override
	protected ResourceLocation getDefaultLootTable() {
		return PVZLoot.COFFIN;
	}

	@Override
	public ZombieType getZombieType() {
		return CustomZombies.COFFIN;
	}

}
