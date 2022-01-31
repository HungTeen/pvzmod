package com.hungteen.pvz.common.entity;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.api.interfaces.IAlmanacEntry;
import com.hungteen.pvz.api.paz.IPAZEntity;
import com.hungteen.pvz.api.types.IRankType;
import com.hungteen.pvz.api.types.ISkillType;
import com.hungteen.pvz.common.entity.misc.bowling.AbstractBowlingEntity;
import com.hungteen.pvz.common.entity.zombie.roof.BungeeZombieEntity;
import com.hungteen.pvz.common.event.PVZLivingEvents;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.enums.PAZAlmanacs;
import com.hungteen.pvz.utils.others.WeightList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    protected boolean canHelpAttack = true;// no use ?

    static {
        //init drop list.
        final int p = PVZConfig.COMMON_CONFIG.EntitySettings.DropChanceMultiper.get();
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
//        this.setPersistenceRequired();
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

    public static AttributeModifierMap.MutableAttribute createPAZAttributes() {
        return AttributeModifierMap.builder()
                .add(Attributes.MAX_HEALTH)
                .add(Attributes.KNOCKBACK_RESISTANCE)
                .add(Attributes.ATTACK_KNOCKBACK)
                .add(Attributes.ATTACK_SPEED)
                .add(Attributes.MOVEMENT_SPEED)
                .add(Attributes.ARMOR)
                .add(Attributes.ARMOR_TOUGHNESS)
                .add(Attributes.ATTACK_DAMAGE)
                .add(net.minecraftforge.common.ForgeMod.SWIM_SPEED.get())
                .add(net.minecraftforge.common.ForgeMod.NAMETAG_DISTANCE.get())
                .add(net.minecraftforge.common.ForgeMod.ENTITY_GRAVITY.get())
                .add(PVZAttributes.INNER_DEFENCE_HP.get())
                .add(PVZAttributes.OUTER_DEFENCE_HP.get())
                ;
    }

    /**
     * used in invasion spawn.
     */
    public static void randomInitSkills(AbstractPAZEntity pazEntity, int maxLevel){
        final CompoundNBT skillNBT = new CompoundNBT();
        final List<ISkillType> skills = pazEntity.getPAZType().getSkills();
        int point = MathUtil.getRandomMinMax(pazEntity.getRandom(), maxLevel / 2, maxLevel);
        for(int i = 0; i < 10; ++ i){
            final ISkillType skillType = skills.get(pazEntity.getRandom().nextInt(skills.size()));
            int lvl = 0;
            while(lvl < skillType.getMaxLevel() && skillType.getCostAt(lvl) <= point && pazEntity.getRandom().nextFloat() < 0.8F){
                point -= skillType.getCostAt(lvl);
                skillNBT.putInt(skillType.getIdentity(), lvl);
                ++ lvl;
            }
        }
        pazEntity.setSkills(skillNBT);
        pazEntity.initAttributes();
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
            this.initAttributes();
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
        this.setPersistenceRequired();//avoid being refreshed by chunk.
    }

    @Override
    public void aiStep() {
        super.aiStep();
        this.level.getProfiler().push("PAZ Tick");
        this.pazTick();
        this.level.getProfiler().pop();
    }

    /**
     * tick not consider death.
     */
    public void pazTick(){
        if(! level.isClientSide){
            this.setExistTick(this.getExistTick() + 1);
        }
    }

    @Override
    protected boolean shouldDespawnInPeaceful() {
        return EntityGroupHander.isMonsterGroup(this.getEntityGroupType());
    }

    /**
     * {@link PVZLivingEvents#onLivingHurt(LivingHurtEvent)}
     */
    public static void damageOuterDefence(final LivingHurtEvent ev) {
        float amount = ev.getAmount();
        if(ev.getEntityLiving() instanceof AbstractPAZEntity && ((AbstractPAZEntity) ev.getEntityLiving()).canOuterDefend(ev.getSource())){
            final AbstractPAZEntity pazEntity = (AbstractPAZEntity) ev.getEntityLiving();
            final double life = pazEntity.getOuterDefenceLife();
            if(life > 0){
                if(life > amount){
                    pazEntity.setOuterDefenceLife(life - amount);
                    amount = 0;
                    pazEntity.onOuterDefenceHurt();
                } else{
                    amount -= life;
                    pazEntity.setOuterDefenceLife(0);
                    pazEntity.onOuterDefenceBroken();
                }
            }
        }
        ev.setAmount(amount == 0 ? 0.000001F : amount);
    }
    
    /**
     * {@link PVZLivingEvents#onLivingDamage(LivingDamageEvent)}
     */
    public static void damageInnerDefence(final LivingDamageEvent ev) {
        float amount = ev.getAmount();
        if(ev.getEntityLiving() instanceof AbstractPAZEntity){
            final AbstractPAZEntity pazEntity = (AbstractPAZEntity) ev.getEntityLiving();
            final double life = pazEntity.getInnerDefenceLife();
            if(life > 0){
                if(life > amount){
                    pazEntity.setInnerDefenceLife(life - amount);
                    amount = 0;
                    pazEntity.onInnerDefenceHurt();
                } else{
                    amount -= life;
                    pazEntity.setInnerDefenceLife(0);
                    pazEntity.onInnerDefenceBroken();
                }
            }
        }
        ev.setAmount(amount == 0 ? 0.000001F : amount);
    }

    public void onOuterDefenceHurt(){

    }

    public void onOuterDefenceBroken(){

    }

    public void onInnerDefenceHurt(){

    }

    public void onInnerDefenceBroken(){

    }

    public boolean canOuterDefend(DamageSource source){
        return true;
    }

    /**
     * check can set target as attackTarget.
     */
    public boolean checkCanPAZTarget(Entity target) {
        return EntityUtil.checkCanEntityBeTarget(this, target) && this.canPAZTarget(target);
    }

    /**
     * check can attack target.
     */
    public boolean checkCanPAZAttack(Entity target) {
        return EntityUtil.checkCanEntityBeAttack(this, target) && this.canPAZTarget(target);
    }

    /**
     * can be targeted by living, often use for plant's target.
     * e.g. plants with metal can not be targeted.
     */
    public boolean canBeTargetBy(LivingEntity living) {
        return true;
    }

    /**
     * do not attack living.
     * e.g. spike weed, bungee, plants with steel ladder.
     */
    public boolean canPAZTarget(Entity target) {
        if(target instanceof AbstractPAZEntity){
            return ((AbstractPAZEntity) target).canBeTargetBy(this);
        }
        return true;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        //can get hurt each attack by pvz damage.
        if (source instanceof PVZEntityDamageSource) {
            this.invulnerableTime = 0;
        }
        if(source.getEntity() instanceof LivingEntity && EntityUtil.checkCanEntityBeAttack(this, source.getEntity())){
            //determine whether to change target.
            if(EntityUtil.isEntityValid(this.getTarget()) && this.getRandom().nextFloat() < 0.4F){
                if(this.distanceTo(source.getEntity()) < this.distanceTo(this.getTarget())){
                    this.setTarget((LivingEntity) source.getEntity());
                }
            }
        }
        return super.hurt(source, amount);
    }

    @Override
    public void die(DamageSource source) {
        super.die(source);
        //if it was killed by bowling, it will not drop anything.
        if (source instanceof PVZEntityDamageSource && ((PVZEntityDamageSource) source).getDirectEntity() instanceof AbstractBowlingEntity
                && ((PVZEntityDamageSource) source).getDamageCount() > 0) {
            this.canSpawnDrop = false;
        }
    }

    @Override
    protected void tickDeath() {
        ++ this.deathTime;
        if (this.canRemoveWhenDeath()) {
            for (int i = 0; i < 5; ++i) {
                double d0 = this.random.nextGaussian() * 0.02D;
                double d1 = this.random.nextGaussian() * 0.02D;
                double d2 = this.random.nextGaussian() * 0.02D;
                this.level.addParticle(ParticleTypes.POOF, this.getRandomX(1.0D), this.getRandomY(),
                        this.getRandomZ(1.0D), d0, d1, d2);
            }
            this.onRemoveWhenDeath();
            this.remove();
        }
    }

    /**
     * {@link #tickDeath()}
     */
    protected void onRemoveWhenDeath(){
    }

    protected boolean canRemoveWhenDeath(){
        return this.deathTime >= this.getDeathTime();
    }

    protected int getDeathTime(){
        return 20;
    }

    /**
     * update attributes when first spawn.
     * {@link #finalizeSpawn(CompoundNBT)}
     */
    protected void initAttributes() {
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(this.getLife());
        this.getAttribute(Attributes.ARMOR).setBaseValue(this.getArmor());
        this.getAttribute(Attributes.ARMOR_TOUGHNESS).setBaseValue(this.getArmorToughness());
        this.getAttribute(PVZAttributes.INNER_DEFENCE_HP.get()).setBaseValue(this.getInnerLife());
        this.getAttribute(PVZAttributes.OUTER_DEFENCE_HP.get()).setBaseValue(this.getOuterLife());
        this.heal(this.getMaxHealth());
    }

    /* features */
    protected abstract float getLife();

    protected float getInnerLife(){
        return 0;
    }

    protected float getOuterLife(){
        return 0;
    }

    public int getArmor() {
        return 0;
    }

    public int getArmorToughness() {
        return 0;
    }

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

    @Override
    public boolean removeWhenFarAway(double p_213397_1_) {
        return super.removeWhenFarAway(p_213397_1_);
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
    public int getZombieXp() {
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
        if(this.getExistTick() < 2){
            this.initAttributes();
        }
    }

    @Override
    public void readSpawnData(PacketBuffer additionalData) {

    }

    @Override
    public void writeSpawnData(PacketBuffer buffer) {

    }

    /**
     * how many health does zombie has.
     * {@link EntityUtil#getCurrentHealth(net.minecraft.entity.LivingEntity)}
     */
    public double getCurrentHealth() {
        return this.getInnerDefenceLife() + this.getOuterDefenceLife() + this.getHealth();
    }

    /**
     * how many max health does zombie have currently.
     * {@link EntityUtil#getCurrentMaxHealth(net.minecraft.entity.LivingEntity)}
     */
    public double getCurrentMaxHealth() {
        return this.getInnerDefenceLife() + this.getOuterDefenceLife() + this.getMaxHealth();
    }

    public double getInnerDefenceLife() {
        return this.getAttributeValue(PVZAttributes.INNER_DEFENCE_HP.get());
    }

    public void setInnerDefenceLife(double life) {
        this.getAttribute(PVZAttributes.INNER_DEFENCE_HP.get()).setBaseValue(life);
    }

    public double getOuterDefenceLife() {
        return this.getAttributeValue(PVZAttributes.OUTER_DEFENCE_HP.get());
    }

    public void setOuterDefenceLife(double life) {
        this.getAttribute(PVZAttributes.OUTER_DEFENCE_HP.get()).setBaseValue(life);
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
