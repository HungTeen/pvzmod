package com.hungteen.pvz.common.entity;

import com.hungteen.pvz.api.enums.PVZGroupType;
import com.hungteen.pvz.api.interfaces.IHasGroup;
import com.hungteen.pvz.api.interfaces.IHasOwner;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.NBTUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-30 23:09
 **/
public class PVZOwnerEntity extends PVZEntity implements IHasGroup, IHasOwner {

    protected Entity owner;
    protected UUID ownerId;
    protected PVZGroupType groupType;

    public PVZOwnerEntity(EntityType<?> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
        this.groupType = this.getInitialEntityGroup();
    }

    public PVZOwnerEntity(EntityType<?> type, Level worldIn, Entity livingEntityIn) {
        super(type, worldIn);
        this.summonByOwner(livingEntityIn);
    }

    /**
     * sync some data from owner.
     */
    public void summonByOwner(Entity owner) {
        this.owner = owner;
        this.ownerId = owner.getUUID();
        this.groupType = EntityGroupHandler.getEntityGroupType(owner);
    }

    @Override
    public boolean isAttackable() {
        return false;
    }

    public void setOwner(LivingEntity player) {
        this.owner = player;
    }

    @Nullable
    public Entity getOwner() {
        if (EntityUtil.isEntityValid(this.owner) && this.ownerId != null && this.level instanceof ServerLevel) {
            this.owner = ((ServerLevel) this.level).getEntity(this.ownerId);
        }
        return this.owner;
    }

    public Entity getOwnerOrSelf() {
        return this.getOwner() == null ? this : this.getOwner();
    }

    @Override
    public Optional<UUID> getOwnerUUID() {
        return Optional.ofNullable(this.uuid);
    }

    public PVZGroupType getInitialEntityGroup() {
        return PVZGroupType.NEUTRALS;
    }

    @Override
    public PVZGroupType getGroupType() {
        return this.groupType;
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        if (this.ownerId != null) {
            compound.putUUID("Owner", this.ownerId);
        }
        compound.putInt("TickCount", this.tickCount);
        compound.putInt("GroupType", this.groupType.ordinal());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditionalSaveData(CompoundTag compound) {
        this.owner = null;
        if (compound.hasUUID("Owner")) {
            this.ownerId = compound.getUUID("Owner");
        }
        if(compound.contains("TickCount")) {
            this.tickCount = compound.getInt("TickCount");
        }
        if(compound.contains("GroupType")) {
            this.groupType = EntityGroupHandler.getGroup(compound.getInt("GroupType"));
        }
    }
}
