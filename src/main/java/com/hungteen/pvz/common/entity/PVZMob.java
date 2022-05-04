package com.hungteen.pvz.common.entity;

import com.hungteen.pvz.api.interfaces.IHasGroup;
import com.hungteen.pvz.api.interfaces.IHasOwner;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.players.OldUsersConverter;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

import java.util.Optional;
import java.util.UUID;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-27 16:13
 **/
public abstract class PVZMob extends PathfinderMob implements IHasOwner, IHasGroup {

    private static final EntityDataAccessor<Optional<UUID>> OWNER_UUID = SynchedEntityData.defineId(PVZMob.class, EntityDataSerializers.OPTIONAL_UUID);

    public PVZMob(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
        this.refreshDimensions();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(OWNER_UUID, Optional.empty());
    }

    @Override
    protected boolean shouldDespawnInPeaceful() {
        return EntityGroupHandler.isMonsterGroup(this.getGroupType());
    }

    /* data */

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        {// save owner uuid.
            if (this.getOwnerUUID().isPresent()) {
                compound.putUUID("OwnerUUID", this.getOwnerUUID().get());
            }
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        {// owner uuid.
            UUID ownerUuid;
            if (compound.hasUUID("OwnerUUID")) {
                ownerUuid = compound.getUUID("OwnerUUID");
            } else {
                String s1 = compound.getString("OwnerUUID");
                ownerUuid = OldUsersConverter.convertMobOwnerIfNecessary(this.getServer(), s1);
            }
            if (ownerUuid != null) {
                try {
                    this.setOwnerUUID(ownerUuid);
                } catch (Throwable var4) {
                }
            }
        }
    }

    @Override
    public Optional<UUID> getOwnerUUID() {
        return this.entityData.get(OWNER_UUID);
    }

    public void setOwnerUUID(UUID uuid) {
        this.entityData.set(OWNER_UUID, Optional.ofNullable(uuid));
    }

}
