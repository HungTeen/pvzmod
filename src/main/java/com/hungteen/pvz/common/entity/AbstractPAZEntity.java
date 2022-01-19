package com.hungteen.pvz.common.entity;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.api.interfaces.IAlmanacEntry;
import com.hungteen.pvz.api.paz.IPAZEntity;
import com.hungteen.pvz.api.types.IRankType;
import com.hungteen.pvz.api.types.ISkillType;
import com.hungteen.pvz.common.entity.misc.bowling.AbstractBowlingEntity;
import com.hungteen.pvz.common.entity.zombie.roof.BungeeZombieEntity;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.PAZAlmanacs;
import com.hungteen.pvz.utils.others.WeightList;
import com.mojang.datafixers.util.Pair;

import net.minecraft.command.impl.SummonCommand;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

import javax.annotation.Nullable;
import java.util.*;

//TODO 掉落物（金币等等）
public abstract class AbstractPAZEntity extends CreatureEntity implements IPAZEntity, IEntityAdditionalSpawnData {

    private static final DataParameter<Optional<UUID>> OWNER_UUID = EntityDataManager.defineId(AbstractPAZEntity.class, DataSerializers.OPTIONAL_UUID);
    private static final DataParameter<Integer> STATES = EntityDataManager.defineId(AbstractPAZEntity.class, DataSerializers.INT);
    private static final DataParameter<CompoundNBT> SKILLS = EntityDataManager.defineId(AbstractPAZEntity.class, DataSerializers.COMPOUND_TAG);
    private static final DataParameter<Integer> EXIST_TICK = EntityDataManager.defineId(AbstractPAZEntity.class, DataSerializers.INT);
    protected static final WeightList<DropType> NORMAL_DROP_LIST = new WeightList<>();
    protected PlayerEntity ownerPlayer;
    /* states */
    protected boolean canBeCold = true;
    protected boolean canBeFrozen = true;
    protected boolean canBeCharm = true;
    protected boolean canBeButtered = true;
    protected boolean canBeMini = true;
    protected boolean canBeInvisible = true;
    protected boolean canBeRemove = true;
    protected boolean canBeStealByBungee = true;
    /* misc */
    protected boolean canSpawnDrop = true;
    protected boolean canHelpAttack = true;

    static {
        //init drop list.
        final int p = PVZConfig.COMMON_CONFIG.EntitySettings.ZombieSetting.ZombieDropMultiper.get();
        final int pp = p * p;
        NORMAL_DROP_LIST.addItem(DropType.SILVER, p * p * p);
        NORMAL_DROP_LIST.addItem(DropType.GOLD, p * p);
        NORMAL_DROP_LIST.addItem(DropType.JEWEL, p);
        NORMAL_DROP_LIST.addItem(DropType.CHOCOLATE, p);
        NORMAL_DROP_LIST.setTotal(pp * pp);
    }

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
        this.entityData.define(STATES, 0);
    }

    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
        this.finalizeSpawn(dataTag);
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    /**
     * final runtime before entity spawn in world.
     */
    public void finalizeSpawn(CompoundNBT tag){
        if (! this.level.isClientSide()) {
            if(tag != null){
                if(tag.contains(SkillTypes.SKILL_TAG)){
                    this.setSkills(tag.getCompound(SkillTypes.SKILL_TAG));
                    
                }
            }
            this.getSpawnSound().ifPresent(s -> EntityUtil.playSound(this, s));
            this.updateAttributes();
        }
    }

    /**
     * spawned by player for the first time.
     */
    public void onSpawnedByPlayer(@Nullable PlayerEntity player, int sunCost) {
        if(player != null) {
            this.setOwnerUUID(player.getUUID());
        }
        this.heal(this.getMaxHealth());
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

    /**
     * tick not consider death.
     */
    public void pazTick(){

    }

    /**
     * only tick when it's in normal state.
     */
    public void normalPAZTick(){

    }

    @Override
    public void die(DamageSource source) {
        super.die(source);
        //if it was killed by bowling, it will not drop anything.
        if (source instanceof PVZDamageSource && ((PVZDamageSource) source).getDirectEntity() instanceof AbstractBowlingEntity
                && ((PVZDamageSource) source).getDamageCount() > 0) {
            this.canSpawnDrop = false;
        }
    }

    /**
     * update attributes when first spawn.
     * {@link #finalizeSpawn(CompoundNBT)}
     */
    protected void updateAttributes() {
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(this.getLife());
    }

    /* features */
    protected abstract float getLife();

    @Override
    public void addAlmanacEntries(List<Pair<IAlmanacEntry, Number>> list) {
        list.addAll(Arrays.asList(
                Pair.of(PAZAlmanacs.SUN_COST, this.getPAZType().getSunCost()),
                Pair.of(PAZAlmanacs.COOL_DOWN, this.getPAZType().getCoolDown().getCD(0))
        ));
    }

    /* misc get */

    public float getSkillValue(ISkillType type){
        final int lvl = SkillTypes.getSkillLevel(this.getSkills(), type);
        return type.getValueAt(lvl);
    }

    /**
     * stay alive, and check other conditions to keep normal tick.
     */
    public boolean canNormalUpdate(){
        return EntityUtil.isEntityValid(this)
                && ! (this.getVehicle() instanceof BungeeZombieEntity)
                && ! EntityUtil.isEntityFrozen(this)
                && ! EntityUtil.isEntityButter(this);
    }

    public IRankType getRank() {
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

    /**
     * how much xp will it get, when killed by player or plants.
     */
    public float getZombieXp() {
        return this.getPAZType().getXpPoint();
    }

    /**
     * init special drop list.
     */
    protected WeightList<DropType> getDropSpecialList(){
        return NORMAL_DROP_LIST;
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
        {// states.
            compound.putInt("paz_states", this.getPAZState());
        }
        {// save paz skills.
            compound.put(SkillTypes.SKILL_TAG, this.getSkills());
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
        {
            if(compound.contains("paz_states")){
                this.setPAZState(compound.getInt("paz_states"));
            }
        }
        {// paz skills.
            if (compound.contains(SkillTypes.SKILL_TAG)) {
                this.setSkills(compound.getCompound(SkillTypes.SKILL_TAG));
            }
        }
        {// misc.
            if(compound.contains("paz_exist_tick")) {
                this.setExistTick(compound.getInt("paz_exist_tick"));
            }
        }
        this.updateAttributes();
    }

    @Override
    public void readSpawnData(PacketBuffer additionalData) {

    }

    @Override
    public void writeSpawnData(PacketBuffer buffer) {

    }

    @Override
    protected ResourceLocation getDefaultLootTable() {
        return this.getPAZType().getLootTable();
    }

    public int getPAZState() {
        return this.entityData.get(STATES);
    }

    public void setPAZState(int state) {
        this.entityData.set(STATES, state);
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

    /**
     * Special Drop Types.
     */
    protected enum DropType{
        COPPER, //drop copper coin.
        SILVER, //drop silver coin.
        GOLD,  //drop gold coin.
        JEWEL, //drop jewel.
        CHOCOLATE, //drop chocolate.

    }

}
