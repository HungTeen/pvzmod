package com.hungteen.pvz.common.entity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import net.minecraft.client.renderer.blockentity.SpawnerRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.GameRules;
import org.jetbrains.annotations.Nullable;

import com.hungteen.pvz.api.interfaces.IAlmanacEntry;
import com.hungteen.pvz.api.interfaces.IPAZEntity;
import com.hungteen.pvz.api.types.ISkillType;
import com.hungteen.pvz.common.entity.ai.PVZLookRandomlyGoal;
import com.hungteen.pvz.common.impl.PAZAlmanacs;
import com.hungteen.pvz.common.impl.type.SkillTypes;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.misc.WeightList;
import com.mojang.datafixers.util.Pair;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraftforge.common.ForgeMod;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-27 16:03
 **/
public abstract class PVZPAZ extends PVZMob implements IPAZEntity {

    private static final EntityDataAccessor<Integer> STATES = SynchedEntityData.defineId(PVZPAZ.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<CompoundTag> SKILLS = SynchedEntityData.defineId(PVZPAZ.class, EntityDataSerializers.COMPOUND_TAG);
    private static final EntityDataAccessor<Integer> EXIST_TICK = SynchedEntityData.defineId(PVZPAZ.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> ANIM_TICK = SynchedEntityData.defineId(PVZPAZ.class, EntityDataSerializers.INT);
    protected static final WeightList<DropType> NORMAL_DROP_LIST = new WeightList<>();
    protected Player ownerPlayer;

    public PVZPAZ(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SKILLS, new CompoundTag());
        this.entityData.define(EXIST_TICK, 0);
        this.entityData.define(ANIM_TICK, 0);
        this.entityData.define(STATES, 0);
    }

    public static AttributeSupplier.Builder createPAZAttributes() {
        return AttributeSupplier.builder()
                .add(Attributes.MAX_HEALTH)
                .add(Attributes.FOLLOW_RANGE)
                .add(Attributes.KNOCKBACK_RESISTANCE)
                .add(Attributes.MOVEMENT_SPEED)
                .add(Attributes.FLYING_SPEED)
                .add(Attributes.ATTACK_DAMAGE)
                .add(Attributes.ATTACK_KNOCKBACK)
                .add(Attributes.ATTACK_SPEED)
                .add(Attributes.ARMOR)
                .add(Attributes.ARMOR_TOUGHNESS)
                .add(Attributes.LUCK)
                .add(ForgeMod.SWIM_SPEED.get())
                .add(ForgeMod.NAMETAG_DISTANCE.get())
                .add(ForgeMod.ENTITY_GRAVITY.get())
                .add(ForgeMod.REACH_DISTANCE.get())
                .add(PVZAttributes.WORK_CD.get())
//                .add(PVZAttributes.INNER_DEFENCE_HP.get())
//                .add(PVZAttributes.OUTER_DEFENCE_HP.get())
                ;
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficultyInstance, MobSpawnType spawnType, @Nullable SpawnGroupData groupData, @Nullable CompoundTag tag) {
        this.finalizeSpawn(tag);
        return super.finalizeSpawn(level, difficultyInstance, spawnType, groupData, tag);
    }

    /**
     * final runtime before entity spawn in world. <br>
     * paz entity spawned with card will gain skills.
     */
    public void finalizeSpawn(CompoundTag tag){
        if (! this.level.isClientSide()) {
            if(tag != null){
                if(tag.contains(SkillTypes.SKILL_TAG)){
                    this.setSkills(tag.getCompound(SkillTypes.SKILL_TAG));
                }
            }
            this.getSpawnSound().ifPresent(s -> EntityUtil.playSound(this, s));
            this.initAttributes();
//            this.updatePAZStates();
        }
    }

    /**
     * update attributes when first spawn.
     * {@link #finalizeSpawn(CompoundTag)}
     */
    protected void initAttributes() {
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(this.getLife());
        this.getAttribute(Attributes.ARMOR).setBaseValue(this.getArmor());
        this.getAttribute(Attributes.ARMOR_TOUGHNESS).setBaseValue(this.getArmorToughness());
//        this.getAttribute(PVZAttributes.INNER_DEFENCE_HP.get()).setBaseValue(this.getInnerLife());
//        this.getAttribute(PVZAttributes.OUTER_DEFENCE_HP.get()).setBaseValue(this.getOuterLife());
        this.heal(this.getMaxHealth());
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new PVZLookRandomlyGoal(this));
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

    /**
     * use to start plant super mode.
     */
    @Override
    public void onEnergetic(boolean first) {
        this.heal(this.getMaxHealth());
//        if (first) {
//            PlayerEntity player = EntityUtil.getEntityOwner(level, this);
//            if (player != null && player instanceof ServerPlayerEntity) {
//                PlantSuperTrigger.INSTANCE.trigger((ServerPlayerEntity) player, this);
//            }
//            this.getOuterPlantInfo().ifPresent(p -> p.onEnergetic(this));
//        }
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
        if(target instanceof PVZPAZ){
            return ((PVZPAZ) target).canBeTargetBy(this);
        }
        return true;
    }

    @Override
    public boolean canBeEnergetic() {
        return this.canNormalUpdate();
    }

    @Override
    protected void pushEntities() {
        double dd = this.getCollideWidthOffset();
        List<LivingEntity> list = this.level.getEntitiesOfClass(LivingEntity.class,
                this.getBoundingBox().inflate(dd, 0, dd));
        if (!list.isEmpty()) {
            int i = this.level.getGameRules().getInt(GameRules.RULE_MAX_ENTITY_CRAMMING);
            if (i > 0 && list.size() > i - 1 && this.random.nextInt(4) == 0) {
                int j = 0;
                for (int k = 0; k < list.size(); ++k) {
                    if (!((Entity) list.get(k)).isPassenger()) {
                        ++j;
                    }
                }
                if (j > i - 1) {
                    this.hurt(DamageSource.CRAMMING, 6.0F);
                }
            }
            for (int l = 0; l < list.size(); ++ l) {
                final LivingEntity target = list.get(l);
                if (! this.is(target) && this.shouldCollideWithEntity(target)) {// can collide with
                    this.doPush(target);
                }
            }
        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        //can get hurt each attack by pvz damage.
        if (source instanceof PVZDamageSource) {
            this.invulnerableTime = 0;
        }
        //TODO Attack Back.
//        if(source.getEntity() instanceof LivingEntity && EntityUtil.checkCanEntityBeAttack(this, source.getEntity())){
//            //determine whether to change target.
//            if(EntityUtil.isEntityValid(this.getTarget()) && this.getRandom().nextFloat() < 0.4F){
//                if(this.distanceTo(source.getEntity()) < this.distanceTo(this.getTarget())){
//                    this.setTarget((LivingEntity) source.getEntity());
//                }
//            }
//        }
        return super.hurt(source, amount);
    }

    @Override
    public void die(DamageSource source) {
        super.die(source);
//        //if it was killed by bowling, it will not drop anything.
//        if (source instanceof PVZDamageSource && ((PVZDamageSource) source).getDirectEntity() instanceof AbstractBowlingEntity
//                && ((PVZDamageSource) source).getDamageCount() > 0) {
//            this.canSpawnDrop = false;
//        }
    }

    @Override
    protected void tickDeath() {
        super.tickDeath();
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

            if(! this.level.isClientSide){
                this.level.broadcastEntityEvent(this, (byte)60);
                this.remove(Entity.RemovalReason.KILLED);
            }
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

    /* features */
    protected abstract float getLife();

//    protected float getInnerLife(){
//        return 0;
//    }
//
//    protected float getOuterLife(){
//        return 0;
//    }

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
                Pair.of(PAZAlmanacs.COOL_DOWN, this.getPAZType().getCoolDown())
        ));
    }

    /**
     * check collide.
     */
    protected double getCollideWidthOffset() {
        return 0;
    }

    protected boolean shouldCollideWithEntity(Entity entity){
        return true;
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
        return true;
//        return ! (this.getVehicle() instanceof BungeeZombieEntity)
//                && ! EntityUtil.isEntityFrozen(this)
//                && ! EntityUtil.isEntityButter(this);
    }

    @Override
    public boolean canBeLeashed(Player player) {
        return false;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean isPushedByFluid() {
        return false;
    }

    @Override
    public boolean removeWhenFarAway(double dis) {
        return super.removeWhenFarAway(dis);
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
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("PAZStates", this.getPAZState());
        compound.put(SkillTypes.SKILL_TAG, this.getSkills());
        compound.putInt("ExistTick", this.getExistTick());
        compound.putInt("AnimTick", this.getAnimTick());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if(compound.contains("PAZStates")){
            this.setPAZState(compound.getInt("PAZStates"));
        }
        if (compound.contains(SkillTypes.SKILL_TAG)) {
            this.setSkills(compound.getCompound(SkillTypes.SKILL_TAG));
        }
        if(compound.contains("ExistTick")) {
            this.setExistTick(compound.getInt("ExistTick"));
        }
        if(compound.contains("AnimTick")){
            this.setAnimTick(compound.getInt("AnimTick"));
        }
        //use to update attributes when using data pack.
        if(! compound.contains("UpdateAttributesWhenSpawn") || compound.getBoolean("UpdateAttributesWhenSpawn")){
            this.finalizeSpawn(null);
        }
    }

    public int getPAZState() {
        return this.entityData.get(STATES);
    }

    public void setPAZState(int state) {
        this.entityData.set(STATES, state);
    }

    public void setSkills(CompoundTag nbt) {
        this.entityData.set(SKILLS, nbt);
    }

    public CompoundTag getSkills() {
        return this.entityData.get(SKILLS);
    }

    public int getExistTick() {
        return this.entityData.get(EXIST_TICK);
    }

    public void setExistTick(int tick) {
        this.entityData.set(EXIST_TICK, tick);
    }

    public int getAnimTick() {
        return this.entityData.get(ANIM_TICK);
    }

    public void setAnimTick(int tick) {
        this.entityData.set(ANIM_TICK, tick);
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
