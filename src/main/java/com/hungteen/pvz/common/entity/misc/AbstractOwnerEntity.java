package com.hungteen.pvz.common.entity.misc;

import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nullable;

import com.hungteen.pvz.api.enums.PVZGroupType;
import com.hungteen.pvz.api.interfaces.IGroupEntity;
import com.hungteen.pvz.api.interfaces.IHasOwner;
import com.hungteen.pvz.common.entity.PVZEntityBase;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public abstract class AbstractOwnerEntity extends PVZEntityBase implements IGroupEntity, IHasOwner {

	protected LivingEntity owner;
	protected UUID ownerId;
	protected PVZGroupType groupType;
	
	public AbstractOwnerEntity(EntityType<?> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
		this.groupType = this.getInitialEntityGroup();
	}
	
	public AbstractOwnerEntity(EntityType<?> type, World worldIn, LivingEntity livingEntityIn) {
		super(type, worldIn);
		this.owner = livingEntityIn;
		this.ownerId = livingEntityIn.getUUID();
		this.groupType = EntityUtil.getEntityGroup(owner);
	}

	@Override
	public boolean isAttackable() {
		return false;
	}
	
	public void setOwner(LivingEntity player) {
		this.owner = player;
	}

	@Nullable
	public LivingEntity getOwner() {
		if ((this.owner == null || ! this.owner.isAlive()) && this.ownerId != null && this.level instanceof ServerWorld) {
			Entity entity = ((ServerWorld) this.level).getEntity(this.ownerId);
			if (entity instanceof LivingEntity) {
				this.owner = (LivingEntity) entity;
			} else {
				this.owner = null;
			}
		}
		return this.owner;
	}
	
	@Override
	public Optional<UUID> getOwnerUUID() {
		return Optional.ofNullable(this.uuid);
	}
	
	public PVZGroupType getInitialEntityGroup() {
		return PVZGroupType.CREATURES;
	}
	
	@Override
	public PVZGroupType getEntityGroupType() {
		return this.groupType;
	}
	
	public void addAdditionalSaveData(CompoundNBT compound) {
		if (this.ownerId != null) {
			compound.put("owner", NBTUtil.createUUID(this.ownerId));
		}
		compound.putInt("entity_tick_exist", this.tickCount);
		compound.putInt("group_owner_type", this.groupType.ordinal());
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readAdditionalSaveData(CompoundNBT compound) {
		this.owner = null;
		if (compound.contains("owner", 10)) {
			this.ownerId = NBTUtil.loadUUID(compound.getCompound("owner"));
		}
		if(compound.contains("entity_tick_exist")) {
			this.tickCount = compound.getInt("entity_tick_exist");
		}
		if(compound.contains("group_owner_type")) {
			this.groupType = PVZGroupType.getGroup(compound.getInt("group_owner_type"));
		}
	}

}
