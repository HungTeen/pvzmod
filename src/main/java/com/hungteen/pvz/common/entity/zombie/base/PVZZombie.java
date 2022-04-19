package com.hungteen.pvz.common.entity.zombie.base;

import java.util.Optional;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.api.enums.PVZGroupType;
import com.hungteen.pvz.api.interfaces.IZombieEntity;
import com.hungteen.pvz.api.types.base.IPAZType;
import com.hungteen.pvz.common.entity.PVZAttributes;
import com.hungteen.pvz.common.entity.ai.goal.attack.PVZZombieMeleeAttackGoal;
import com.hungteen.pvz.common.sound.PVZSounds;
import com.hungteen.pvz.common.PVZDamageSource;
import com.hungteen.pvz.common.entity.PVZEntities;
import com.hungteen.pvz.common.entity.PVZPAZ;
import com.hungteen.pvz.common.entity.ai.goal.PVZLookRandomlyGoal;
import com.hungteen.pvz.common.entity.ai.goal.target.PVZNearestTargetGoal;
import com.hungteen.pvz.common.entity.bullet.PVZProjectile;
import com.hungteen.pvz.common.entity.plant.base.PVZPlant;
import com.hungteen.pvz.common.entity.zombie.ZombieUtil;
import com.hungteen.pvz.common.entity.zombie.drop.ZombieDropPart;
import com.hungteen.pvz.utils.AlgorithmUtil;
import com.hungteen.pvz.utils.EntityUtil;

import com.hungteen.pvz.utils.enums.DropPartTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-04 19:31
 **/
public abstract class PVZZombie extends PVZPAZ implements IZombieEntity {

    /* state flag */
    private static final int LEFT_HAND_FLAG = 0;
    private static final int RIGHT_HAND_FLAG = 1;
    private static final int HEAD_FLAG = 2;
    /* collide */
    public boolean canCollideWithZombie = true;
    /* drop parts */
    protected boolean checkedLostHand = false;
    protected boolean checkedLostHead = false;
    protected boolean canDropBody = false;
    /* move helper */
    protected int climbUpTick = 0;
    protected int maxClimbUpTick = 5;

    public PVZZombie(EntityType<? extends PVZPAZ> entityType, Level level) {
        super(entityType, level);
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 6.0F);
        this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, 6.0F);
        this.setPathfindingMalus(BlockPathTypes.DAMAGE_OTHER, 6.0F);
        this.setPathfindingMalus(BlockPathTypes.UNPASSABLE_RAIL, 6.0F);
        this.setPathfindingMalus(BlockPathTypes.LEAVES, 4F);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(8, new PVZLookRandomlyGoal(this));
//        this.goalSelector.addGoal(7, new RandomWalkingGoal(this, 1.0D));
//        this.goalSelector.addGoal(7, new PVZSwimGoal(this));
        this.registerAttackGoals();
        this.registerTargetGoals();
    }

    /**
     * {@link #registerGoals()}
     */
    protected void registerAttackGoals() {
        this.goalSelector.addGoal(3, new PVZZombieMeleeAttackGoal(this, 1F, true));
//        this.goalSelector.addGoal(6, new ZombieBreakPlantBlockGoal(BlockRegister.FLOWER_POT.get(), this, 1F, 10));
    }

    /**
     * {@link #registerGoals()}
     */
    protected void registerTargetGoals() {
        this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, true));
    }

    /* handle spawn */

    @Override
    public void finalizeSpawn(CompoundTag tag) {
        super.finalizeSpawn(tag);
        if(! this.level.isClientSide){
//            this.setZombieType(this.getSpawnType());
//            if(this.needRising) {// rising from dirt.
//                this.setAnimTime(- RISING_CD);
//                this.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, RISING_CD + 10, 20, false, false));
//            }
        }
    }

//    @Override
//    public void updatePAZStates() {
//        super.updatePAZStates();
//        if(! this.level.isClientSide) {
//            if (this.canBeMini() && this.isMiniZombie()) {
//                this.onZombieBeMini();
//            }
//        }
//    }

//    /**
//     * get current variant type.
//     * it will be override by @NormalZombieEntity
//     */
//    protected VariantType getSpawnType() {
//        final int t = this.getRandom().nextInt(100);
//        final int a = PVZConfig.COMMON_CONFIG.EntitySettings.ZombieSetting.ZombieSuperChance.get();
//        final int b = PVZConfig.COMMON_CONFIG.EntitySettings.ZombieSetting.ZombieSunChance.get();
//        return (t < a) ? VariantType.SUPER : (t < a + b) ? VariantType.SUN : VariantType.NORMAL;
//    }

    @Override
    protected void initAttributes() {
        super.initAttributes();
        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(this.getEatDamage());
        this.getAttribute(Attributes.FOLLOW_RANGE).setBaseValue(this.getFollowRange());
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(this.getWalkSpeed());
        this.getAttribute(Attributes.KNOCKBACK_RESISTANCE).setBaseValue(this.getKBValue());
        this.getAttribute(PVZAttributes.WORK_CD.get()).setBaseValue(this.getZombieAttackCD());
    }

    @Override
    public void pazTick() {
        super.pazTick();
        this.level.getProfiler().push("PVZ Zombie Tick");
        this.zombieTick();
        this.level.getProfiler().pop();

        if (this.canNormalUpdate()) {
            this.level.getProfiler().push("PVZ Normal Zombie Tick");
            this.normalZombieTick();
            this.level.getProfiler().pop();
        }
    }

    /**
     * tick whether zombie is in normal state or not.
     * {@link #aiStep()}
     */
    public void zombieTick() {
        if (this.tickCount <= 2) {
            this.refreshDimensions();
        }
        //rising particle
//        if(this.isZombieRising()) {
//            this.setAnimTime(this.getAnimTime() + 1);
//            if(level.isClientSide) {
//                ParticleUtil.spawnSplash(this.level, this.position(), 1);
//            }
//        }
        //natural spawn zombie will heal in lava.
        if(! this.level.isClientSide){
            if(this.isInLava() && this.getExistTick() % 10 == 0 && ! this.getOwnerUUID().isPresent()){
                this.heal(20);
            }
        }
    }

    /**
     * tick when zombie is normal state.
     * (not be frozen or butter and so on).
     * {@link #aiStep()}
     */
    public void normalZombieTick() {
        if(! this.level.isClientSide) {
//            this.setAnimTime(Math.max(0, this.getAnimTime() - 1));
//            if(this.canClimbWalls()) {
//                if(++ this.climbUpTick <= this.maxClimbUpTick) {
//                    final Vec3 vec = this.getDeltaMovement();
//                    this.setDeltaMovement(vec.x, 0.3D, vec.z);
//                }
//            } else {
//                this.climbUpTick = 0;
//            }
        }
    }

    /**
     * Copy from super {@link Mob#doHurtTarget(Entity)}
     */
    @Override
    public boolean doHurtTarget(Entity entityIn) {
//        this.setAnimTime(PERFORM_ATTACK_CD);

        // add
        float f = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
        float f1 = (float) this.getAttributeValue(Attributes.ATTACK_KNOCKBACK);
        if (entityIn instanceof LivingEntity) {
            f += EnchantmentHelper.getDamageBonus(this.getMainHandItem(), ((LivingEntity) entityIn).getMobType());
            f1 += (float) EnchantmentHelper.getKnockbackBonus(this);
        }

        int i = EnchantmentHelper.getFireAspect(this);
        if (i > 0) {
            entityIn.setSecondsOnFire(i * 4);
        }

        boolean flag = entityIn.hurt(getZombieAttackDamageSource(), f);
        if (flag) {
            if (f1 > 0.0F && entityIn instanceof LivingEntity) {
                ((LivingEntity)entityIn).knockback((double)(f1 * 0.5F), (double)Mth.sin(this.getYRot() * ((float)Math.PI / 180F)), (double)(-Mth.cos(this.getYRot() * ((float)Math.PI / 180F))));
                this.setDeltaMovement(this.getDeltaMovement().multiply(0.6D, 1.0D, 0.6D));
            }

            if (entityIn instanceof Player) {
                Player player = (Player)entityIn;
                this.maybeDisableShield(player, this.getMainHandItem(), player.isUsingItem() ? player.getUseItem() : ItemStack.EMPTY);
            }

            this.doEnchantDamageEffects(this, entityIn);
            this.setLastHurtMob(entityIn);
        }
        return flag;
    }

    /**
     * copy from default code.
     */
    private void maybeDisableShield(Player player, ItemStack itemStack, ItemStack itemStack1) {
        if (!itemStack.isEmpty() && !itemStack1.isEmpty() && itemStack.getItem() instanceof AxeItem
                && itemStack1.getItem() == Items.SHIELD) {
            float f = 0.25F + (float) EnchantmentHelper.getBlockEfficiency(this) * 0.05F;
            if (this.random.nextFloat() < f) {
                player.getCooldowns().addCooldown(Items.SHIELD, 100);
                this.level.broadcastEntityEvent(player, (byte) 30);
            }
        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if(! level.isClientSide) {
            boolean flag = super.hurt(source, amount);
            if(this.canLostHand(source, amount) && this.random.nextDouble() < PVZConfig.dropHandChance()){
                if(this.random.nextDouble() < 0.5){
                    this.onLostHand(source, amount, true);
                } else{
                    this.onLostHand(source, amount, false);
                }
            }
            if(this.canLostHead(source, amount) && this.random.nextDouble() < PVZConfig.dropHeadChance()){
                this.onLostHead(source, amount);
            }
            return flag;
        }
        return false;
    }

    @Override
    public void die(DamageSource source) {
        super.die(source);
        if(this.canDropBody()) {
            if(! this.level.isClientSide) {
                this.onFallBody(source);
            }
        }
    }

    @Override
    protected boolean canRemoveWhenDeath() {
        return this.canDropBody() || super.canRemoveWhenDeath();
    }

    @Override
    protected void onRemoveWhenDeath() {
        super.onRemoveWhenDeath();
        if (!level.isClientSide) {
//            if (this.getVariantType() == VariantType.SUPER) {// drop energy
//                this.dropEnergy();
//            } else if (getVariantType() == VariantType.SUN) {
//                this.dropSun();
//            } else if (getVariantType() == VariantType.BEARD) {// finish achievement
//            }
//            if (this.canSpawnDrop) {
//                this.spawnSpecialDrops();
//            }
        }
    }

    /**
     * damage type of zombie.
     * {@link #doHurtTarget(Entity)}
     */
    protected PVZDamageSource getZombieAttackDamageSource() {
        return PVZDamageSource.eat(this);
    }

    /**
     * some zombies are not able to drop hands.
     * {@link #hurt}
     */
    public boolean canLostHand(DamageSource source, float amount) {
        return amount >= 1F && this.getHealth() < Math.min(40, this.getMaxHealth() * 0.5F);
    }

    /**
     * some zombies are not able to drop heads.
     * {@link #hurt}
     */
    public boolean canLostHead(DamageSource source, float amount) {
        return amount >= 2F && this.getHealth() < Math.min(10, this.getMaxHealth() * 0.1F);
    }

    /**
     * some zombies are not able to drop heads.
     * {@link #hurt}
     */
    public boolean canDropBody() {
        return PVZConfig.enableDropBody() && this.canDropBody;
    }

    /**
     * trigger at {@link #hurt(DamageSource, float)}
     */
    private void onLostHand(DamageSource source, float amount, boolean right) {
        ZombieDropPart body = PVZEntities.ZOMBIE_DROP_PART.get().create(level);
        if(right){
            this.lostRightHand(true);
            body.setItemSlot(EquipmentSlot.MAINHAND, this.getMainHandItem().copy());
            this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
            body.onDrop(this, source, amount, DropPartTypes.RIGHT_HAND);
        } else{
            this.lostLeftHand(true);
            body.setItemSlot(EquipmentSlot.OFFHAND, this.getOffhandItem().copy());
            this.setItemSlot(EquipmentSlot.OFFHAND, ItemStack.EMPTY);
            body.onDrop(this, source, amount, DropPartTypes.LEFT_HAND);
        }
        level.addFreshEntity(body);
    }

    /**
     * trigger at {@link #hurt(DamageSource, float)}
     */
    private void onLostHead(DamageSource source, float amount) {
        this.lostHead(true);
        ZombieDropPart body = PVZEntities.ZOMBIE_DROP_PART.get().create(level);
        body.onDrop(this, source, amount, DropPartTypes.HEAD);
        level.addFreshEntity(body);
    }

    /**
     * trigger at {@link #die(DamageSource)}
     */
    protected void onFallBody(DamageSource source) {
        ZombieDropPart body = PVZEntities.ZOMBIE_DROP_PART.get().create(level);
        body.onDrop(this, source, 0, DropPartTypes.WHOLE_BODY);
        body.setMaxLiveTick(40);
        this.setBodyStates(body);
        level.addFreshEntity(body);
    }

    /**
     * set states to body.
     * such as has paper or not.
     * {@link #onFallBody(DamageSource)}
     */
    protected void setBodyStates(ZombieDropPart body) {
//        body.setMini(this.isMiniZombie());
    }

    @Override
    public void push(Entity entity) {
        if (this.isSleeping()) {
            return;
        }
        if (!this.isPassengerOfSameVehicle(entity)) {
            if (!entity.noPhysics && !this.noPhysics) {
                double d0 = entity.getX() - this.getX();
                double d1 = entity.getZ() - this.getZ();
                double d2 = Mth.absMax(d0, d1);
                if (d2 >= (double) 0.01F) {
                    d2 = Math.sqrt(d2);
                    d0 /= d2;
                    d1 /= d2;
                    double d3 = 1.0D / d2;
                    if (d3 > 1.0D) {
                        d3 = 1.0D;
                    }

                    d0 *= d3;
                    d1 *= d3;
                    d0 *= (double) 0.05F;
                    d1 *= (double) 0.05F;
                    if (!this.isVehicle()) {
                        this.push(-d0, 0.0D, -d1);
                    }

                    if (!entity.isVehicle()) {
                        if (checkCanPushEntity(entity)) {
                            entity.push(d0, 0.0D, d1);
                        }
                    }
                } else{

                }
            }
        }
    }

    protected double getCollideWidthOffset() {
        return - 0.25D;
    }

    /**
     * can zombie collide with target.
     * {@link #pushEntities()}
     */
    protected boolean shouldCollideWithEntity(LivingEntity target) {
        if (this.getTarget() == target) {
//            if (target instanceof SquashEntity || target instanceof SpikeWeedEntity) {
//                return false;
//            }
            return true;
        }
        if (target instanceof PVZZombie) {
            return this.canCollideWithZombie && ((PVZZombie) target).canCollideWithZombie;
        }
        return false;
    }

    /**
     * can zombie push target.
     * {@link #push(Entity)}
     */
    protected boolean checkCanPushEntity(Entity target) {
        return !(target instanceof PVZPlant);
    }

    public float getEatDamage(){
        return ZombieUtil.VERY_LOW;
    }

    public float getWalkSpeed(){
        return ZombieUtil.WALK_NORMAL;
    }

    public float getKBValue(){
        return 0.92F;
    }

    public float getFollowRange(){
        return ZombieUtil.NORMAL_RANGE;
    }

    @Override
    public int getArmor() {
        return 0;
//        return (int) this.getSkillValue(SkillTypes.TOUGH_BODY);
    }

    public int getZombieAttackCD(){
        return 20;
    }

    public double getCurrentAttackCD(){
        return this.getAttribute(PVZAttributes.WORK_CD.get()).getValue();
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        if(source instanceof PVZDamageSource && ((PVZDamageSource) source).isMustHurt()) {
            return false;
        }
        return source != DamageSource.OUT_OF_WORLD && !source.isCreativePlayer() && this.isZombieInvulnerableTo(source);
    }

    protected boolean isZombieInvulnerableTo(DamageSource source) {
        return this.isZombieRising() || (! EntityUtil.isEntityValid(source.getEntity()) && ! source.isMagic());
    }

    /**
     * is zombie still rising from dirt.
     */
    public boolean isZombieRising() {
        return false;
//        return this.getAnimTime() < 0;
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    public boolean ignoreExplosion() {
        return true;
    }

    @Override
    protected float getWaterSlowDown() {
        return 0.85f;
    }

    /* sound */

    @Override
    protected SoundEvent getAmbientSound() {
        return PVZSounds.ZOMBIE_GROAN.get();
    }

    @Override
    public SoundEvent getHurtSound(DamageSource damageSourceIn) {
        if (damageSourceIn.getDirectEntity() instanceof PVZProjectile) {
            return PVZSounds.SPLAT.get();
        }
        return super.getHurtSound(damageSourceIn);
    }

    public Optional<SoundEvent> getSpawnSound() {
//        if(this.needRising) {//if zombie is rising from dirt.
//            return Optional.ofNullable(PVZSounds.DIRT_RISE.get());
//        }
        return Optional.empty();
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("CheckedLostHand", this.checkedLostHand);
        compound.putBoolean("CheckedLostHead", this.checkedLostHead);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if(compound.contains("CheckedLostHand")){
            this.checkedLostHand = compound.getBoolean("CheckedLostHand");
        }
        if(compound.contains("CheckedLostHead")){
            this.checkedLostHead = compound.getBoolean("CheckedLostHead");
        }
    }

    public boolean hasLeftHand() {
        return !AlgorithmUtil.BitOperator.hasBitOne(this.getPAZState(), LEFT_HAND_FLAG);
    }

    public void lostLeftHand(boolean is) {
        this.setStateByFlag(is, LEFT_HAND_FLAG);
    }

    public boolean hasRightHand() {
        return !AlgorithmUtil.BitOperator.hasBitOne(this.getPAZState(), RIGHT_HAND_FLAG);
    }

    public void lostRightHand(boolean is) {
        this.setStateByFlag(is, RIGHT_HAND_FLAG);
    }

    public boolean hasHead() {
        return !AlgorithmUtil.BitOperator.hasBitOne(this.getPAZState(), HEAD_FLAG);
    }

    public void lostHead(boolean is) {
        this.setStateByFlag(is, HEAD_FLAG);
    }

    private void setStateByFlag(boolean is, int flag) {
        this.setPAZState(AlgorithmUtil.BitOperator.setBit(this.getPAZState(), flag, is));
    }

    @Override
    public PVZGroupType getGroupType() {
        return PVZGroupType.ZOMBIES;
    }

    @Override
    public IPAZType getPAZType() {
        return this.getZombieType();
    }

}
