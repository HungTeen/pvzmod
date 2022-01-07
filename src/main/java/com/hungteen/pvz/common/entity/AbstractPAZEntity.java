package com.hungteen.pvz.common.entity;

import com.hungteen.pvz.api.paz.IPAZEntity;
import com.hungteen.pvz.api.types.IRankType;
import com.hungteen.pvz.common.entity.zombie.roof.BungeeZombieEntity;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

import java.util.Optional;
import java.util.UUID;

public abstract class AbstractPAZEntity extends CreatureEntity implements IPAZEntity, IEntityAdditionalSpawnData {

    private static final DataParameter<Optional<UUID>> OWNER_UUID = EntityDataManager.defineId(AbstractPAZEntity.class, DataSerializers.OPTIONAL_UUID);
    private static final DataParameter<CompoundNBT> SKILLS = EntityDataManager.defineId(AbstractPAZEntity.class, DataSerializers.COMPOUND_TAG);
    private static final DataParameter<Integer> EXIST_TICK = EntityDataManager.defineId(AbstractPAZEntity.class, DataSerializers.INT);
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
        this.entityData.define(SKILLS, new CompoundNBT());
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

    }

    /* features */
    protected abstract float getLife();

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
        {// save owner uuid.
            if (this.getOwnerUUID().isPresent()) {
                compound.putUUID("OwnerUUID", this.getOwnerUUID().get());
            }
        }
        {// save paz skills.
            compound.put("paz_skills", this.getSkills());
        }
        {// misc.
            compound.putInt("paz_exist_tick", this.getExistTick());
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
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
        {// paz skills.
            if (compound.contains("paz_skills")) {
                this.setSkills(compound.getCompound("paz_skills"));
            }
        }
        {// misc.
            if(compound.contains("paz_exist_tick")) {
                this.setExistTick(compound.getInt("paz_exist_tick"));
            }
        }
    }

    @Override
    public void readSpawnData(PacketBuffer additionalData) {

    }

    @Override
    public void writeSpawnData(PacketBuffer buffer) {

    }

    public void setSkills(CompoundNBT nbt) {
        this.entityData.set(SKILLS, nbt);
    }

    public CompoundNBT getSkills() {
        return this.entityData.get(SKILLS);
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
