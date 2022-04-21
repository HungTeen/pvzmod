package com.hungteen.pvz.common.entity.creature.garden;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.annotation.Nullable;

import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.enums.PVZGroupType;
import com.hungteen.pvz.api.interfaces.IPlantEntity;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.api.types.base.IPAZType;
import com.hungteen.pvz.common.entity.PVZMob;
import com.hungteen.pvz.common.impl.type.plant.PVZPlants;
import com.hungteen.pvz.common.sound.PVZSounds;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-01 20:14
 **/
public abstract class GardenPlant extends PVZMob {

    private static final EntityDataAccessor<String> PLANT_TYPE = SynchedEntityData.defineId(GardenPlant.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Integer> GROW_STAGE = SynchedEntityData.defineId(GardenPlant.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> GARDEN_STATE = SynchedEntityData.defineId(GardenPlant.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> ANIM_TICK = SynchedEntityData.defineId(GardenPlant.class, EntityDataSerializers.INT);
    public static final int GROW_ANIM_CD = 20;
    @Nullable
    protected LivingEntity displayEntity;
    protected long lastGrowTick;
    //use total to collect grow time between abnormal state.
    protected long totalGrowTick;
    protected int nextGrowCD;


    public GardenPlant(EntityType<? extends GardenPlant> entityType, Level level) {
        super(entityType, level);
        this.refreshDimensions();
        this.nextGrowCD = this.getNextGrowCD();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(PLANT_TYPE, PVZPlants.PEA_SHOOTER.getIdentity());
        this.entityData.define(GROW_STAGE, 0);
        this.entityData.define(GARDEN_STATE, GardenState.NORMAL.ordinal());
        this.entityData.define(ANIM_TICK, 0);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 5));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1);
    }

    @Nullable
    public static IPlantType getRandomType(Random rand) {
        final List<IPlantType> list = PVZAPI.get().getPAZTypes().stream().filter(IPlantType.class::isInstance).map(IPlantType.class::cast).toList();
        return list.isEmpty() ? null : list.get(rand.nextInt(list.size()));
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level.isClientSide) {
            this.checkGardenState();

            if (this.getGardenState() == GardenState.NORMAL && this.canGrow()) {
                if (this.lastGrowTick == 0) {
                    this.lastGrowTick = this.level.getGameTime();
                }
                if (this.level.getGameTime() + this.totalGrowTick - this.lastGrowTick > this.nextGrowCD) {
                    this.setGardenState(GardenState.NEED_FERTILIZER);
                }
            }

            if (this.getAnimTick() > 0) {
                this.setAnimTick(this.getAnimTick() - 1);
            }
        }
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> dataAccessor) {
        super.onSyncedDataUpdated(dataAccessor);
        if (dataAccessor.equals(GROW_STAGE)) {
            this.refreshDimensions();
        }
    }

    /**
     * whether place on wrong environment.
     */
    protected void checkGardenState() {

    }

    @Override
    public InteractionResult interactAt(Player player, Vec3 vec3, InteractionHand hand) {
        if (!this.level.isClientSide) {
            final ItemStack stack = player.getItemInHand(hand);
            if (stack.is(Items.BONE_MEAL)) {
                if (this.canGrow() && this.getGardenState() == GardenState.NEED_FERTILIZER) {
                    this.onGrow();
                }
            }
        }
        return super.interactAt(player, vec3, hand);
    }

    /**
     * only use in client side.
     */
    public void syncAnimation(LivingEntity entity){
        entity.yHeadRot = this.yHeadRot;
        entity.yHeadRotO = this.yHeadRotO;
        entity.yBodyRot = this.yBodyRot;
        entity.yBodyRotO = this.yBodyRotO;
        entity.setXRot(this.getXRot());
        entity.xRotO = this.xRotO;
        entity.setYRot(this.getYRot());
        entity.yRotO = this.yRotO;
    }

    /**
     * grow up.
     */
    public void onGrow() {
        this.lastGrowTick = this.level.getGameTime();
        this.nextGrowCD = this.getNextGrowCD();
        this.totalGrowTick = 0;
        EntityUtil.playSound(this, PVZSounds.PLANT_GROW.get());
        this.setAnimTick(GROW_ANIM_CD);
        this.spawnGrowDrops(this.getGrowStage());
        this.setGrowStage(this.getGrowStage() + 1);
    }

    protected void spawnGrowDrops(int stage) {

    }

    @Nullable
    public LivingEntity getOrCreateDisplayEntity(Level level) {
        boolean needRefresh = false;
        if (this.tickCount > 2 && this.displayEntity == null && this.getPlantType() != null && this.getPlantType().getEntityType().isPresent()) {
            needRefresh = true;
        }
        if (this.displayEntity instanceof IPlantEntity) {
            if (this.tickCount % 20 == 0 && ((IPlantEntity) this.displayEntity).getPlantType() != this.getPlantType()) {
                needRefresh = true;
            }
        }
        if (needRefresh) {
            final CompoundTag compound = new CompoundTag();
            compound.putString("id", this.getPlantType().getEntityType().get().getRegistryName().toString());
            
            Entity entity = EntityType.loadEntityRecursive(compound, level, e -> {
                e.moveTo(this.blockPosition(), this.getXRot(), this.getYRot());
                return e;
            });
            if(entity instanceof LivingEntity) {
            	this.displayEntity = (LivingEntity) entity;
            }
        }
        return this.displayEntity;
    }

    protected abstract int getNextGrowCD();

    protected abstract int getMaxStage();

    public abstract float getRenderScale();

    public boolean canGrow() {
        return this.getGrowStage() < this.getMaxStage();
    }

    /**
     * reach max stage.
     */
    public boolean isPlantRipe() {
        return !this.canGrow();
    }

    /**
     * only render sprout.
     */
    public boolean isSprout() {
        return this.getGrowStage() == 0;
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return EntityDimensions.scalable(0.4F, 0.45F);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("PlantType")) {
            this.setPlantType(compound.getString("PlantType"));
        }
        if (compound.contains("GrowStage")) {
            this.setGrowStage(compound.getInt("GrowStage"));
        }
        if (compound.contains("LastGrowTick")) {
            this.lastGrowTick = compound.getLong("LastGrowTick");
        }
        if (compound.contains("NextGrowCD")) {
            this.nextGrowCD = compound.getInt("NextGrowCD");
        }
        if (compound.contains("AnimTick")) {
            this.setAnimTick(compound.getInt("AnimTick"));
        }
        if (compound.contains("GardenState")) {
            this.setGardenState(GardenState.values()[compound.getInt("GardenState")]);
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putString("PlantType", this.getPlantType().getIdentity());
        compound.putInt("GrowStage", this.getGrowStage());
        compound.putLong("LastGrowTick", this.lastGrowTick);
        compound.putInt("NextGrowCD", this.nextGrowCD);
        compound.putInt("AnimTick", this.getAnimTick());
        compound.putInt("GardenState", this.getGardenState().ordinal());
    }

    public void setPlantType(IPlantType type) {
        this.entityData.set(PLANT_TYPE, type.getIdentity());
    }

    private void setPlantType(String name) {
        this.entityData.set(PLANT_TYPE, name);
    }

    @Nullable
    public IPlantType getPlantType() {
        Optional<IPAZType> opt = PVZAPI.get().getPAZType(this.entityData.get(PLANT_TYPE));
        return opt.isPresent() && opt.get() instanceof IPlantType ? (IPlantType) opt.get() : null;
    }

    public void setGrowStage(int stage) {
        this.entityData.set(GROW_STAGE, stage);
    }

    public int getGrowStage() {
        return this.entityData.get(GROW_STAGE);
    }

    public void setAnimTick(int tick) {
        this.entityData.set(ANIM_TICK, tick);
    }

    public int getAnimTick() {
        return this.entityData.get(ANIM_TICK);
    }

    public void setGardenState(GardenState state) {
        this.entityData.set(GARDEN_STATE, state.ordinal());
    }

    public GardenState getGardenState() {
        return GardenState.values()[this.entityData.get(GARDEN_STATE)];
    }

    @Override
    public PVZGroupType getGroupType() {
        return PVZGroupType.PLANTS;
    }

    public enum GardenState {
        /**
         * Normal Stage, nothing special.
         */
        NORMAL,

        SLEEP,

        NEED_WATER,

        NEED_FERTILIZER,

        NEED_MUSIC,

        NEED_BEE,

        EXCITE

    }

}
