package com.hungteen.pvz.common.entity.creature;

import com.hungteen.pvz.client.particle.ParticleUtil;
import com.hungteen.pvz.common.entity.PVZEntities;
import com.hungteen.pvz.common.item.PVZItems;
import com.hungteen.pvz.common.tag.PVZBlockTags;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.MathUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.IForgeShearable;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-28 20:25
 **/
public class GrassCarp extends Animal implements Bucketable, IForgeShearable {

    private static final EntityDataAccessor<Boolean> BALD = SynchedEntityData.defineId(GrassCarp.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(Axolotl.class, EntityDataSerializers.BOOLEAN);
    public static final Predicate<ItemEntity> ALLOWED_ITEMS = (itemEntity) -> {
        return !itemEntity.hasPickUpDelay() && itemEntity.isAlive() && itemEntity.isInWater();
    };
    private int nextChangeTick = this.getNextChangeTick();
    private int growHairTick = 0;

    public GrassCarp(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new FishMoveControl(this);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.refreshDimensions();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(BALD, false);
        this.entityData.define(FROM_BUCKET, false);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.MOVEMENT_SPEED, 0.8D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 8.0F, 1.6D, 1.4D, EntitySelector.NO_SPECTATORS::test));
        this.goalSelector.addGoal(4, new FishSwimGoal(this));
        this.goalSelector.addGoal(3, new CollectItemsGoal(this));
        this.goalSelector.addGoal(1, new ThrowItemsGoal(this));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
    }

    @Override
    public void tick() {
        super.tick();
        if(! this.level.isClientSide){
            //grow hair.
            if(this.isBald() && -- this.growHairTick <= 0){
                this.setBald(false);
            }

            //change blocks.
            if(-- this.nextChangeTick <= 0) {
            	final int range = 3;
            	for(int i = - range; i <= range; ++ i) {
            		for(int j = - range; j <= range; ++ j) {
                        for(int h = -1; h <= 2; ++ h){
                            final BlockPos pos = this.blockPosition().offset(i, h, j);
                            if (checkBlock(pos)) {
                                for (int k = -range; k < range; ++k) {
                                    ParticleUtil.spawnParticles(this.level, ParticleTypes.COMPOSTER, new Vec3(MathUtil.toVector(pos.above()).x() - 0.5 + (float) random.nextInt(10) / 10,
                                            MathUtil.toVector(pos.above()).y() + (float) (random.nextInt(3)) / 10,
                                            MathUtil.toVector(pos.above()).z() - 0.5 + (float) random.nextInt(10) / 10));
                                }
                            }
                        }
                	}
            	}
            	this.nextChangeTick = this.getNextChangeTick();
            }
        }
    }

    public void baseTick() {
        int i = this.getAirSupply();
        super.baseTick();
        this.handleAirSupply(i);
    }

    public void travel(Vec3 vec3) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(0.01F, vec3);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
            if (this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(vec3);
        }

    }

    public void aiStep() {
        if (!this.isInWater() && this.onGround && this.verticalCollision) {
            this.setDeltaMovement(this.getDeltaMovement().add((double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F), (double)0.4F, (double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F)));
            this.onGround = false;
            this.hasImpulse = true;
//            this.playSound(this.getFlopSound(), this.getSoundVolume(), this.getVoicePitch());
        }

        super.aiStep();
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
        GrassCarp grassCarp = PVZEntities.GRASS_CARP.get().create(level);
        if(grassCarp != null){
            grassCarp.setPersistenceRequired();
        }
        return grassCarp;
    }

    protected boolean checkBlock(BlockPos pos) {

        if (this.level.getBlockState(pos).is(PVZBlockTags.DIRT_NO_GRASS)) {
            if (this.level.getFluidState(pos.above()).isEmpty() & random.nextInt(5) == 1) {
                this.level.setBlock(pos, Blocks.GRASS_BLOCK.defaultBlockState(), 3);
            }
            return true;
        }

        if (this.level.getBlockState(pos).is(Tags.Blocks.STONE)) {
            if (random.nextInt(10) == 1) {
                this.level.setBlock(pos, Blocks.MOSS_BLOCK.defaultBlockState(), 3);
            }
            return true;
        }

        return false;
    }

    @Override
    public boolean isShearable(@NotNull ItemStack item, Level level, BlockPos pos) {
        return ! this.isBald();
    }

    @NotNull
    @Override
    public List<ItemStack> onSheared(@Nullable Player player, @NotNull ItemStack item, Level level, BlockPos pos, int fortune) {
        level.playSound(null, this, SoundEvents.SHEEP_SHEAR, player == null ? SoundSource.BLOCKS : SoundSource.PLAYERS, 1.0F, 1.0F);
        this.gameEvent(GameEvent.SHEAR, player);
        if (!level.isClientSide) {
            this.setBald(true);
            this.growHairTick = 1200 + this.random.nextInt(600);

            int count = this.isBaby() ? 1 : MathUtil.getRandomMinMax(this.random, 1, 3);

            java.util.List<ItemStack> items = new java.util.ArrayList<>();
            for (int j = 0; j < count; ++j) {
                items.add(new ItemStack(Items.KELP));
            }
            return items;
        }
        return java.util.Collections.emptyList();
    }

    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        return Bucketable.bucketMobPickup(player, hand, this).orElse(super.mobInteract(player, hand));
    }

    protected void handleAirSupply(int p_30344_) {
        if (this.isAlive() && !this.isInWaterOrBubble()) {
            this.setAirSupply(p_30344_ - 1);
            if (this.getAirSupply() == -20) {
                this.setAirSupply(0);
                this.hurt(DamageSource.DROWN, 2.0F);
            }
        } else {
            this.setAirSupply(300);
        }

    }

    public void saveToBucketTag(ItemStack itemStack) {
        Bucketable.saveDefaultDataToBucketTag(this, itemStack);
        CompoundTag compoundtag = itemStack.getOrCreateTag();
        compoundtag.putBoolean("Bald", this.isBald());
        compoundtag.putInt("Age", this.getAge());
        compoundtag.putInt("NextChangeTick", this.nextChangeTick);
    }

    public void loadFromBucketTag(CompoundTag tag) {
        Bucketable.loadDefaultDataFromBucketTag(this, tag);
        if(tag.contains("Bald")){
            this.setBald(tag.getBoolean("Bald"));
        }
        if(tag.contains("Age")){
            this.setAge(tag.getInt("Age"));
        }
        if(tag.contains("NextChangeTick")) {
        	this.nextChangeTick = tag.getInt("NextChangeTick");
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("Bald", this.isBald());
        tag.putBoolean("FromBucket", this.fromBucket());
        tag.putInt("NextChangeTick", this.nextChangeTick);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if(tag.contains("Bald")) {
        	this.setBald(tag.getBoolean("Bald"));
        }
        if(tag.contains("FromBucket")) {
        	this.setFromBucket(tag.getBoolean("FromBucket"));
        }
        if(tag.contains("NextChangeTick")) {
        	this.nextChangeTick = tag.getInt("NextChangeTick");
        }
    }

    public boolean isBald(){
        return this.entityData.get(BALD);
    }

    public void setBald(boolean is){
        this.entityData.set(BALD, is);
    }

    protected boolean canRandomSwim() {
        return true;
    }

    @Override
    public boolean fromBucket() {
        return this.entityData.get(FROM_BUCKET);
    }

    @Override
    public void setFromBucket(boolean is) {
        this.entityData.set(FROM_BUCKET, is);
    }

    @Override
    public boolean isFood(ItemStack food) {
        return food.is(Items.EMERALD);
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(PVZItems.GRASS_CARP_BUCKET.get());
    }

    @Override
    public SoundEvent getPickupSound() {
        return SoundEvents.BUCKET_FILL_AXOLOTL;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource p_149161_) {
        return SoundEvents.AXOLOTL_HURT;
    }

    @Override
    @javax.annotation.Nullable
    protected SoundEvent getDeathSound() {
        return SoundEvents.AXOLOTL_DEATH;
    }

    @Override
    @javax.annotation.Nullable
    protected SoundEvent getAmbientSound() {
        return this.isInWater() ? SoundEvents.AXOLOTL_IDLE_WATER : SoundEvents.AXOLOTL_IDLE_AIR;
    }

    @Override
    protected SoundEvent getSwimSplashSound() {
        return SoundEvents.AXOLOTL_SPLASH;
    }

    @Override
    protected SoundEvent getSwimSound() {
        return SoundEvents.AXOLOTL_SWIM;
    }

    @Override
    public MobType getMobType() {
        return MobType.WATER;
    }

    @Override
    public EntityDimensions getDimensions(Pose p_21047_) {
        return EntityDimensions.scalable(0.4F, 0.4F).scale(this.getScale());
    }

    private int getNextChangeTick(){
        return MathUtil.getRandomMinMax(this.random, 100, 400);
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public boolean isPushedByFluid() {
        return false;
    }

    @Override
    public boolean canBeLeashed(Player player) {
        return false;
    }

    @Override
    protected PathNavigation createNavigation(Level p_27480_) {
        return new WaterBoundPathNavigation(this, p_27480_);
    }

    static class FishMoveControl extends MoveControl {
        private final GrassCarp fish;

        FishMoveControl(GrassCarp p_27501_) {
            super(p_27501_);
            this.fish = p_27501_;
        }

        public void tick() {
            if (this.fish.isEyeInFluid(FluidTags.WATER)) {
                this.fish.setDeltaMovement(this.fish.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
            }

            if (this.operation == MoveControl.Operation.MOVE_TO && !this.fish.getNavigation().isDone()) {
                float f = (float)(this.speedModifier * this.fish.getAttributeValue(Attributes.MOVEMENT_SPEED));
                this.fish.setSpeed(Mth.lerp(0.125F, this.fish.getSpeed(), f));
                double d0 = this.wantedX - this.fish.getX();
                double d1 = this.wantedY - this.fish.getY();
                double d2 = this.wantedZ - this.fish.getZ();
                if (d1 != 0.0D) {
                    double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                    this.fish.setDeltaMovement(this.fish.getDeltaMovement().add(0.0D, (double)this.fish.getSpeed() * (d1 / d3) * 0.1D, 0.0D));
                }

                if (d0 != 0.0D || d2 != 0.0D) {
                    float f1 = (float)(Mth.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                    this.fish.setYRot(this.rotlerp(this.fish.getYRot(), f1, 90.0F));
                    this.fish.yBodyRot = this.fish.getYRot();
                }

            } else {
                this.fish.setSpeed(0.0F);
            }
        }
    }

    static class FishSwimGoal extends RandomSwimmingGoal {
        private final GrassCarp fish;

        public FishSwimGoal(GrassCarp p_27505_) {
            super(p_27505_, 1.0D, 40);
            this.fish = p_27505_;
        }

        public boolean canUse() {
            return this.fish.canRandomSwim() && super.canUse();
        }
    }

    class CollectItemsGoal extends Goal {

        private final GrassCarp grassCarp;
        private ItemEntity target;
        private int cooldown;

        CollectItemsGoal(GrassCarp grassCarp) {
            this.grassCarp = grassCarp;
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        public boolean canUse() {
        	if(this.grassCarp.isBaby() || ! this.grassCarp.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
        		return false;
        	}
            if (!EntityUtil.isEntityValid(target)) {
                if (--this.cooldown <= 0) {
                    final List<ItemEntity> list = this.grassCarp.level.getEntitiesOfClass(ItemEntity.class, this.grassCarp.getBoundingBox().inflate(16), ALLOWED_ITEMS);
                    if (!list.isEmpty()) {
                        target = list.get(0);
                        return true;
                    }
                }
                return false;
            }
            return true;
        }

        @Override
        public boolean canContinueToUse() {
            return EntityUtil.isEntityValid(this.target) && this.target.distanceTo(this.grassCarp) <= 100;
        }

        public void start() {
            this.grassCarp.getNavigation().moveTo(this.target, 1F);
            this.cooldown = this.grassCarp.random.nextInt(100);
        }

        public void stop() {
            this.target = null;
        }

        public void tick() {
            if (this.grassCarp.distanceTo(this.target) <= 2) {
                this.grassCarp.setItemSlot(EquipmentSlot.MAINHAND, this.target.getItem());
                this.target.discard();
            } else {
                this.grassCarp.getNavigation().moveTo(this.target, 1F);
            }
        }
    }

    class ThrowItemsGoal extends MoveToBlockGoal {

        private final GrassCarp grassCarp;

        ThrowItemsGoal(GrassCarp grassCarp) {
            super(grassCarp, 1, 12, 4);
            this.grassCarp = grassCarp;
        }

        public boolean canUse() {
            return super.canUse() && ! this.grassCarp.isBaby() && ! this.grassCarp.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty();
        }

        @Override
        public boolean canContinueToUse() {
            return super.canContinueToUse() && ! this.grassCarp.isBaby() && ! this.grassCarp.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty();
        }

        protected boolean isValidTarget(LevelReader p_32413_, BlockPos p_32414_) {
            BlockPos blockpos = p_32414_.above();
            return p_32413_.isEmptyBlock(blockpos) && p_32413_.isEmptyBlock(blockpos.above()) ? p_32413_.getBlockState(p_32414_).entityCanStandOn(p_32413_, p_32414_, this.grassCarp) : false;
        }

        public void tick() {
            if (this.grassCarp.distanceToSqr(MathUtil.toVector(this.blockPos)) <= 8) {
                this.drop(this.grassCarp.getItemBySlot(EquipmentSlot.MAINHAND));
                this.grassCarp.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
            } else{
                super.tick();
            }
        }

        private void drop(ItemStack itemStack) {
            if (!itemStack.isEmpty()) {
                ItemEntity itementity = new ItemEntity(this.grassCarp.level, this.grassCarp.getX(), this.grassCarp.getEyeY(), this.grassCarp.getZ(), itemStack);
                itementity.setPickUpDelay(40);
                itementity.setThrower(this.grassCarp.getUUID());
                final Vec3 speed = MathUtil.toVector(this.blockPos).subtract(this.grassCarp.position()).add(0, 1.2, 0).normalize();
                itementity.setDeltaMovement(speed.scale(0.5F));
                this.grassCarp.level.addFreshEntity(itementity);
            }
        }
    }

}
