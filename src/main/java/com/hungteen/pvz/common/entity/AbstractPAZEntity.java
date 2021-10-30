package com.hungteen.pvz.common.entity;

import com.hungteen.pvz.api.IPAZEntity;
import com.hungteen.pvz.api.types.IRankType;
import com.hungteen.pvz.common.entity.zombie.roof.BungeeZombieEntity;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

import java.util.Optional;
import java.util.UUID;

public abstract class AbstractPAZEntity extends CreatureEntity implements IPAZEntity {

    private static final DataParameter<Optional<UUID>> OWNER_UUID = EntityDataManager.defineId(AbstractPAZEntity.class, DataSerializers.OPTIONAL_UUID);
    private static final DataParameter<Integer> PAZ_LVL = EntityDataManager.defineId(AbstractPAZEntity.class, DataSerializers.INT);
    private static final DataParameter<Integer> EXIST_TICK = EntityDataManager.defineId(AbstractPAZEntity.class, DataSerializers.INT);
    //handler maxLevel, maxLevel range in (1, 128).
    protected static final int LEVEL_FLAG_LEN = 7;
    //handle sync maxLevel.
    private static final int LEVEL_SYNC_CD = 1200;
    protected PlayerEntity ownerPlayer;
    protected boolean canBeCold = true;
    protected boolean canBeFrozen = true;
    protected boolean canBeCharm = true;
    protected boolean canBeButtered = true;
    protected boolean canBeMini = true;
    protected boolean canBeInvisible = true;
    protected boolean canBeRemove = true;
    protected boolean canBeStealByBungee;

    public AbstractPAZEntity(EntityType<? extends CreatureEntity> entityType, World world) {
        super(entityType, world);
        this.refreshDimensions();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(OWNER_UUID, Optional.empty());
        this.entityData.define(PAZ_LVL, 1);
        this.entityData.define(EXIST_TICK, 0);
    }

    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
        this.finalizeSpawn();
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    /**
     * final runtime before entity spawn in world.
     */
    public void finalizeSpawn(){
        if (! this.level.isClientSide()) {
            this.getSpawnSound().ifPresent(s -> EntityUtil.playSound(this, s));
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();
        this.level.getProfiler().push("PAZ Tick");
        this.pazTick();
        this.level.getProfiler().pop();
        if (this.canNormalUpdate()) {
            this.level.getProfiler().push("PAZ Normal Tick");
            this.normalPAZTick();
            this.level.getProfiler().pop();
        }
    }

    public void pazTick(){

    }

    public void normalPAZTick(){
        //TODO 植物信息同步
/*        *//* update plant maxLevel & outer plant maxLevel *//*
        if(!maxLevel.isClientSide && this.getExistTick() % PLANT_LEVEL_SYNC_CD == 5) {
            this.getOwnerPlayer().ifPresent(player -> {
                this.getPlantInfo().ifPresent(info -> {
                    if(info.needSyncLevel) {
                        this.updatePlantLevel(PlayerUtil.getPAZLevel(player, info.getType()));
                    }
                });
                this.getOuterPlantInfo().ifPresent(info -> {
                    if(info.needSyncLevel) {
                        info.setLevel(PlayerUtil.getPAZLevel(player, info.getType()));
                    }
                });
            });
        }*/
    }

    /* misc get */

    public boolean canNormalUpdate(){
        if(! EntityUtil.isEntityValid(this)) {
            return false;
        }
        if (this.getVehicle() instanceof BungeeZombieEntity || this.hasMetal()) {
            return false;
        }
        return !EntityUtil.isEntityFrozen(this) && !EntityUtil.isEntityButter(this);
    }

    public int getCoolDownTime() {
        return this.getPAZType().getCoolDown().getCD(this.getPAZLevel());
    }

    public IRankType getPlantRank() {
        return this.getPAZType().getRank();
    }

    @Override
    public boolean canBeCold() {
        return this.canBeCold;
    }

    @Override
    public boolean canBeButtered() {
        return this.canBeButtered;
    }

    @Override
    public boolean canBeFrozen() {
        return this.canBeFrozen && !this.isInWaterOrBubble() && !this.isInLava();
    }

    @Override
    public boolean canBeCharmed() {
        return this.canBeCharm;
    }

    @Override
    public boolean canBeMini() {
        return this.canBeMini;
    }

    @Override
    public boolean canBeInvisible() {
        return this.canBeInvisible;
    }

    /* sound */

    public Optional<SoundEvent> getSpawnSound() {
        return Optional.empty();
    }

    /* data */

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("paz_lvl", this.getPAZLevel());
        if (this.getOwnerUUID().isPresent()) {
            compound.putUUID("OwnerUUID", this.getOwnerUUID().get());
        }
        compound.putInt("paz_exist_tick", this.getExistTick());
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("paz_lvl")) {
            this.setPAZLevel(compound.getInt("paz_lvl"));
        }
        {// owner uuid.
            UUID ownerUuid;
            if (compound.hasUUID("OwnerUUID")) {
                ownerUuid = compound.getUUID("OwnerUUID");
            } else {
                String s1 = compound.getString("OwnerUUID");
                ownerUuid = PreYggdrasilConverter.convertMobOwnerIfNecessary(this.getServer(), s1);
            }
            if (ownerUuid != null) {
                try {
                    this.setOwnerUUID(ownerUuid);
                } catch (Throwable var4) {
                }
            }
        }
        if(compound.contains("paz_exist_tick")) {
            this.setExistTick(compound.getInt("paz_exist_tick"));
        }
    }

    public void setPAZLevel(int lvl) {
        this.entityData.set(PAZ_LVL, Math.min(lvl, this.getPAZType().getMaxLevel()));
    }

    public int getPAZLevel() {
        return this.entityData.get(PAZ_LVL);
    }

    @Override
    public Optional<UUID> getOwnerUUID() {
        return this.entityData.get(OWNER_UUID);
    }

    public void setOwnerUUID(UUID uuid) {
        this.entityData.set(OWNER_UUID, Optional.ofNullable(uuid));
    }

    public int getExistTick() {
        return this.entityData.get(EXIST_TICK);
    }

    public void setExistTick(int tick) {
        this.entityData.set(EXIST_TICK, tick);
    }

}
