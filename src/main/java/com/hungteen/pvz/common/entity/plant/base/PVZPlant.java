package com.hungteen.pvz.common.entity.plant.base;

import java.util.List;
import java.util.Optional;

import com.hungteen.pvz.api.enums.PVZGroupType;
import com.hungteen.pvz.api.interfaces.IAlmanacEntry;
import com.hungteen.pvz.api.interfaces.IPlantEntity;
import com.hungteen.pvz.api.types.base.IPAZType;
import com.hungteen.pvz.common.entity.PVZAttributes;
import com.hungteen.pvz.common.PVZDamageSource;
import com.hungteen.pvz.common.entity.PVZPAZ;
import com.mojang.datafixers.util.Pair;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-27 16:54
 **/
public abstract class PVZPlant extends PVZPAZ implements IPlantEntity {

    private static final EntityDataAccessor<Integer> WORK_TICK = SynchedEntityData.defineId(PVZPlant.class, EntityDataSerializers.INT);
    // handle plant weak, place on wrong block.
    private static final int PLANT_WILT_CD = 10;
    protected boolean isImmuneToWilt = false;
    protected int lastWiltTime = 0;
    // handle plant collide with other plants.
    public boolean canCollideWithPlant = true;
    // handle plant can not leave its first spawn place Ban Get In Bucket !
    protected Optional<BlockPos> stayPos = Optional.empty();

    public PVZPlant(EntityType<? extends PVZPlant> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(WORK_TICK, 0);
    }

    @Override
    protected void initAttributes() {
        super.initAttributes();
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0);
        this.getAttribute(Attributes.KNOCKBACK_RESISTANCE).setBaseValue(1);
    }

    @Override
    public void pazTick() {
        super.pazTick();
        this.level.getProfiler().push("PVZ Plant Tick");
        this.plantTick();
        this.level.getProfiler().pop();

        if (this.canNormalUpdate()) {
            this.level.getProfiler().push("PVZ Normal Plant Tick");
            this.normalPlantTick();
            this.level.getProfiler().pop();
        }
    }

//    /**
//     * check can run {@link #normalPlantTick()} or not.
//     */
//    @Override
//    public boolean canNormalUpdate() {
//        return ! this.hasMetal() && !this.isPlantSleeping() && super.canNormalUpdate();
//    }

    /**
     * plant tick.
     * {@link #aiStep()}
     */
    protected void plantTick() {
        if (! this.level.isClientSide) {
            // check plant wilt.
            if (this.shouldWilt() && this.lastWiltTime <= this.getExistTick()) {
                this.hurt(PVZDamageSource.PLANT_WILT, this.getMaxHealth() / 3);
                this.lastWiltTime = this.getExistTick() + PLANT_WILT_CD;
            }
            if(this.shouldMove() && this.stayPos.isPresent() && this.getExistTick() % 20 == 0){
                if(! this.stayPos.get().closerThan(this.blockPosition(), 9)){
                    this.discard();
                }
            }
        }
//        // super mode or boost time or sleep time
//        if (!this.level.isClientSide) {
//            //handle super mode.
//            this.setSuperTime(Math.max(0, this.getSuperTime() - 1));
//            //handle boost mode(no use for currrent version).
//            this.setBoostTime(Math.max(0, this.getBoostTime() - 1));
//            //handler plant's sleep.
//            if (this.shouldPlantRegularSleep()) {
//                this.sleepTime = this.sleepTime <= 1 ? this.sleepTime + 1 : this.sleepTime - 1;
//            } else {
//                this.sleepTime = this.sleepTime <= -1 ? this.sleepTime + 1 : this.sleepTime - 1;
//            }
//            if(! this.isPlantSleeping() && this.sleepTime > 0) {
//                this.setPlantSleeping(true);
//            }
//            if(this.isPlantSleeping() && this.sleepTime <= 0) {
//                this.setPlantSleeping(false);
//            }
//        }
//        // spawn sleep particle
//        if (level.isClientSide && this.isPlantSleeping() && this.tickCount % 20 == 0) {
//            EntityUtil.spawnSpeedParticle(this, ParticleRegister.SLEEP.get(), 0.05F);
//        }
        // lock the x and z of plant
        if (this.shouldMove()) {
            if (this.getVehicle() == null) {
                BlockPos pos = this.blockPosition();
                this.setPos(pos.getX() + 0.5, this.getY(), pos.getZ() + 0.5);
            }
        }
        if (!level.isClientSide) {//set float on water.
            if (this.getPlantType().isWaterPlant() && this.isInWater()) {
                Vec3 vec = this.getDeltaMovement();
                double speedY = Math.min(vec.y, 0.05D);
                this.setDeltaMovement(vec.x, speedY, vec.z);
            }
        }
    }

    /**
     * tick when plant is normal state.
     * (not be frozen or butter and so on).
     * {@link #aiStep()}
     */
    protected void normalPlantTick() {
//        /* tick when plant is place on gold tile, and produce sun */
//        if (!this.level.isClientSide && this.getGoldTime() < GoldLeafEntity.GOLD_GEN_CD) {
//            Block block = this.level.getBlockState(this.blockPosition().below()).getBlock();
//            int lvl = GoldLeafEntity.getBlockGoldLevel(block);
//            if (lvl <= 0) {//not gole tile.
//                this.setGoldTime(0);
//                return;
//            }
//            this.setGoldTime(this.getGoldTime() + 1);
//            if (this.getGoldTime() >= GoldLeafEntity.GOLD_GEN_CD) {
//                this.setGoldTime(0);
//                SunEntity sun = EntityRegister.SUN.get().create(level);
//                sun.setAmount(GoldLeafEntity.getGoldGenAmount(lvl));
//                EntityUtil.onEntityRandomPosSpawn(level, sun, blockPosition(), 2);
//                EntityUtil.playSound(this, SoundEvents.EXPERIENCE_ORB_PICKUP);
//            }
//        }
    }

    /**
     * check if the plant can stand on the current position.
     * {@link #plantTick()}
     */
    public boolean shouldWilt() {
        if (! this.isImmuneToWilt() && this.getVehicle() == null) {//fit check condition and is allowed to wilt.
            if(this.getPlantType().isWaterPlant()) {//on ground, not in water.
                return this.isOnGround() && ! this.isInWater() && ! this.level.getFluidState(blockPosition()).is(FluidTags.WATER);
            }
            if(this.isInWaterOrBubble()) {//can not stay in water.
                return true;
            }
            final BlockPos pos = Math.abs(this.getY() - this.blockPosition().getY()) <= 0.01D ? this.blockPosition().below() : this.blockPosition();
            return ! this.getPlantType().getPlacement().canPlaceOnBlock(level.getBlockState(pos).getBlock());
        }
        return false;
    }

    /**
     * lock the movement of plant.
     * {@link #plantTick()}
     */
    protected boolean shouldMove() {
        return true;
    }

    @Override
    public boolean canBeEnergetic() {
        return super.canBeEnergetic() && this.getEnergeticDuration() > 0;
    }

    @Override
    protected float getLife() {
        return 20;
    }

    @Override
    public void addAlmanacEntries(List<Pair<IAlmanacEntry, Number>> list) {
        super.addAlmanacEntries(list);
//        list.addAll(Arrays.asList(
//                Pair.of(PAZAlmanacs.HEALTH, this.getSkillValue(SkillTypes.PLANT_MORE_LIFE))
//        ));
    }

    /**
     * common plants collide with common plants, mobs who target them, tombstone.
     * {@link #pushEntities()}
     */
    protected boolean shouldCollideWithEntity(LivingEntity target) {
        if (target instanceof PVZPlant) {
            if (!this.canCollideWithPlant || !((PVZPlant) target).canCollideWithPlant) {
                return false;
            }
//            if (target instanceof SquashEntity) {
//                return !EntityUtil.canTargetEntity(this, target);
//            }
//            if (target instanceof SpikeWeedEntity) {
//                return !EntityUtil.canTargetEntity(this, target);
//            }
            return true;
        }
        if (target instanceof Mob) {
            if (((Mob) target).getTarget() == this) {
                return true;
            }
//            if (target instanceof TombStoneEntity) {
//                return true;
//            }
        }
        return false;
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
                if (d2 >= (double)0.01F) {
                    d2 = Math.sqrt(d2);
                    d0 /= d2;
                    d1 /= d2;
                    double d3 = 1.0D / d2;
                    if (d3 > 1.0D) {
                        d3 = 1.0D;
                    }

                    d0 *= d3;
                    d1 *= d3;
                    d0 *= (double)0.05F;
                    d1 *= (double)0.05F;
                    if (!this.isVehicle()) {
                        this.push(-d0, 0.0D, -d1);
                    }

                    if (!entity.isVehicle()) {
                        entity.push(d0, 0.0D, d1);
                    }
                } else {
                    if (entity instanceof PVZPlant) {
                        if (this.tickCount >= entity.tickCount) {
                            this.hurt(PVZDamageSource.PLANT_WILT, this.getMaxHealth() / 3);
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        return this.isPlantImmuneTo(source) && source != DamageSource.OUT_OF_WORLD && !source.isCreativePlayer() && ! source.equals(PVZDamageSource.PLANT_WILT);
    }

    /**
     * {@link #isInvulnerableTo(DamageSource)}
     */
    public boolean isPlantImmuneTo(DamageSource source) {
        return false;
    }

    @Override
    public boolean canBreatheUnderwater() {
        return this.getPlantType().isWaterPlant();
    }

    public double getCurrentWorkCD(){
        return this.getAttribute(PVZAttributes.WORK_CD.get()).getValue();
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if(compound.contains("StayPos")){
            this.stayPos = Optional.ofNullable(BlockPos.of(compound.getLong("StayPos")));
        }
//        PlantInfo.write(this.innerPlant, compound, "inner_plant_info");
//        PlantInfo.write(this.outerPlant, compound, "outer_plant_info");
        compound.putBoolean("ImmuneToWilt", this.isImmuneToWilt);
        compound.putInt("WorkTick", this.getWorkTick());
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        if(this.stayPos.isPresent()){
            compound.putLong("StayPos", this.stayPos.get().asLong());
        }
//        PlantInfo.read(this.innerPlant, compound, "inner_plant_info");
//        PlantInfo.read(this.outerPlant, compound, "outer_plant_info");
        if (compound.contains("ImmuneToWilt")) {
            this.isImmuneToWilt = compound.getBoolean("ImmuneToWilt");
        }
        if(compound.contains("WorkTick")){
            this.setWorkTick(compound.getInt("WorkTick"));
        }
    }

    /* getter setter */

//    public Optional<IPlantInfo> getOuterPlantInfo() {
//        return Optional.ofNullable(this.outerPlant);
//    }
//
//    public Optional<IPlantInfo> getPlantInfo() {
//        return Optional.ofNullable(this.innerPlant);
//    }

    public void setImmuneToWilt(boolean is) {
        this.isImmuneToWilt = is;
    }

    public boolean isImmuneToWilt() {
        return this.isImmuneToWilt;
    }

    public void setWorkTick(int workTick) {
        this.entityData.set(WORK_TICK, workTick);
    }

    public int getWorkTick() {
        return this.entityData.get(WORK_TICK);
    }

    @Override
    public IPAZType getPAZType() {
        return getPlantType();
    }

    @Override
    public PVZGroupType getGroupType() {
        return PVZGroupType.PLANTS;
    }

}
