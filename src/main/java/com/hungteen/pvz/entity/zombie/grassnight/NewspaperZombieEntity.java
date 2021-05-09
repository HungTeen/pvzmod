package com.hungteen.pvz.entity.zombie.grassnight;

import com.hungteen.pvz.entity.zombie.base.DefenceZombieEntity;
import com.hungteen.pvz.entity.zombie.part.PVZHealthPartEntity;
import com.hungteen.pvz.register.SoundRegister;
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
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class NewspaperZombieEntity extends DefenceZombieEntity {

	private static final DataParameter<Boolean> IS_ANGRY = EntityDataManager.defineId(NewspaperZombieEntity.class, DataSerializers.BOOLEAN);
	
	public NewspaperZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(IS_ANGRY, false);
	}
	
	@Override
	protected void updateAttributes() {
		super.updateAttributes();
		this.updateAngry(false);
	}
	
	@Override
	public void resetParts() {
		removeParts();
		this.part = new PVZHealthPartEntity(this, 1f, 1f);
		this.part.setOwner(this);
	}
	
	@Override
	protected float getPartHeightOffset() {
		if(this.isMiniZombie()) return 0.2F;
		return 0.7f;
	}
	
	@Override
	public void tick() {
		super.tick();
		if(!level.isClientSide) {
			if(this.canPartsBeRemoved() && !this.isAngry()) {
				EntityUtil.playSound(this, SoundRegister.ANGRY.get());
				this.updateAngry(true);
			}
		}
	}
	
	protected void updateAngry(boolean is) {
		this.setAngry(is);
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(is ? ZombieUtil.LITTLE_FAST : ZombieUtil.LITTLE_SLOW);
		this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(is ? ZombieUtil.LOW : ZombieUtil.LITTLE_LOW);
	}
	
	@Override
	public SoundEvent getPartDeathSound() {
		return SoundRegister.PAPER_GONE.get();
	}
	
	@Override
	public float getLife() {
		return 22;
	}
	
	@Override
	public float getPartLife() {
		return 10;
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("is_zombie_angry")) {
			this.setAngry(compound.getBoolean("is_zombie_angry"));
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("is_zombie_angry", this.isAngry());
	}
	
	public void setAngry(boolean is) {
		this.entityData.set(IS_ANGRY, is);
	}
	
	public boolean isAngry() {
		return this.entityData.get(IS_ANGRY);
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.NEWSPAPER_ZOMBIE;
	}

}
