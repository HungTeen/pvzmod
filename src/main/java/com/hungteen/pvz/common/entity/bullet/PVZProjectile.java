package com.hungteen.pvz.common.entity.bullet;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.api.enums.PVZGroupType;
import com.hungteen.pvz.api.interfaces.IHasGroup;
import com.hungteen.pvz.api.interfaces.IHasOwner;
import com.hungteen.pvz.common.entity.EntityGroupHandler;
import com.hungteen.pvz.common.entity.plant.base.ShooterPlant;
import com.hungteen.pvz.utils.EntityUtil;
import com.mojang.math.Vector3d;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.TheEndGatewayBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;
import java.util.UUID;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-31 22:33
 **/
public abstract class PVZProjectile extends Projectile implements IHasGroup, IHasOwner {

    private static final EntityDataAccessor<Integer> BULLET_STATE = SynchedEntityData.defineId(PVZProjectile.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> BULLET_TYPE = SynchedEntityData.defineId(PVZProjectile.class, EntityDataSerializers.INT);
    protected PVZGroupType groupType;
    //check hit exist or not.
    protected boolean canExist = true;
    protected float airSlowDown = 0.99F;
    protected float attackDamage = 0F;

    public PVZProjectile(EntityType<? extends Projectile> type, Level level) {
        super(type, level);
        this.groupType = this.getInitialEntityGroup();
    }

    public PVZProjectile(EntityType<? extends Projectile> type, Level level, Entity entity) {
        super(type, level);
        this.summonByOwner(entity);
    }

    @Override
    public void defineSynchedData() {
        entityData.define(BULLET_STATE, BulletStates.NORMAL.ordinal());
        entityData.define(BULLET_TYPE, BulletTypes.NORMAL.ordinal());
    }

    /**
     * sync some data from owner.
     */
    public void summonByOwner(Entity owner) {
        this.setOwner(owner);
        this.groupType = EntityGroupHandler.getEntityGroupType(owner);
    }

    public void tick() {
        super.tick();
        //reach alive time limit.
        if (! level.isClientSide && this.tickCount >= this.getMaxLiveTick()) {
            this.discard();
        }
        //on hit.
        HitResult hitresult = ProjectileUtil.getHitResult(this, this::canHitEntity);
        boolean flag = false;
        if (hitresult.getType() == HitResult.Type.BLOCK) {
            BlockPos blockpos = ((BlockHitResult)hitresult).getBlockPos();
            BlockState blockstate = this.level.getBlockState(blockpos);
            if (blockstate.is(Blocks.NETHER_PORTAL)) {
                this.handleInsidePortal(blockpos);
                flag = true;
            } else if (blockstate.is(Blocks.END_GATEWAY)) {
                BlockEntity blockentity = this.level.getBlockEntity(blockpos);
                if (blockentity instanceof TheEndGatewayBlockEntity && TheEndGatewayBlockEntity.canEntityTeleport(this)) {
                    TheEndGatewayBlockEntity.teleportEntity(this.level, blockpos, blockstate, this, (TheEndGatewayBlockEntity)blockentity);
                }

                flag = true;
            }
        }

        if (hitresult.getType() != HitResult.Type.MISS && !flag && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, hitresult)) {
            this.onHit(hitresult);
        }
        //move.
        this.checkInsideBlocks();
        Vec3 vec3 = this.getDeltaMovement();
        double d2 = this.getX() + vec3.x;
        double d0 = this.getY() + vec3.y;
        double d1 = this.getZ() + vec3.z;
        this.updateRotation();
        float f;
        if (this.isInWater()) {
            for(int i = 0; i < 4; ++i) {
                float f1 = 0.25F;
                this.level.addParticle(ParticleTypes.BUBBLE, d2 - vec3.x * 0.25D, d0 - vec3.y * 0.25D, d1 - vec3.z * 0.25D, vec3.x, vec3.y, vec3.z);
            }

            f = 0.8F;
        } else {
            f = this.airSlowDown;
        }

        this.setDeltaMovement(vec3.scale((double)f));
        if (!this.isNoGravity()) {
            Vec3 vec31 = this.getDeltaMovement();
            this.setDeltaMovement(vec31.x, vec31.y - (double)this.getGravity(), vec31.z);
        }

        this.setPos(d2, d0, d1);
    }

    protected void onHit(HitResult result) {
        HitResult.Type type = result.getType();
        if (type == HitResult.Type.ENTITY) {
            this.onHitEntity((EntityHitResult)result);
        } else if (type == HitResult.Type.BLOCK) {
            this.onHitBlock((BlockHitResult)result);
        }

        if (type != HitResult.Type.MISS) {
            this.gameEvent(GameEvent.PROJECTILE_LAND, this.getOwner());
        }
        //handle hit and remove.
        if(! this.level.isClientSide && ! this.canExist){
            this.level.broadcastEntityEvent(this, (byte)3);
            this.discard();
        }

    }

    protected void onHitEntity(EntityHitResult result) {
        if(PVZConfig.bulletIngoreGroup()){
            this.dealDamageTo(result.getEntity());
        } else{
            if(this.shouldHit(result.getEntity())){
                this.dealDamageTo(result.getEntity());
            }
        }
    }

//    /**
//     * Gets the EntityRayTraceResult representing the entity hit.
//     * {@link #tick()}
//     */
//    @Nullable
//    protected EntityRayTraceResult rayTraceEntities(Vector3d startVec, Vector3d endVec) {
//        return EntityUtil.rayTraceEntities(level, this, startVec, endVec, entity ->
//                entity.isPickable() && shouldHit(entity) && (this.hitEntities == null || !this.hitEntities.contains(entity.getId())
//                ));
//    }

    protected boolean shouldHit(Entity target) {
        return EntityUtil.canTargetEntity(this.getOwnerOrSelf(), target);
    }

    protected void onHitBlock(BlockHitResult result) {
        BlockState blockstate = this.level.getBlockState(result.getBlockPos());
        blockstate.onProjectileHit(this.level, blockstate, result, this);
    }

    protected void dealDamageTo(Entity target){

    }

    /**
     * handle server to client event.
     */
    @Override
    public void handleEntityEvent(byte id) {
        if (id == 3) {//die event.
            ParticleOptions particleoptions = this.getHitParticle();
            if(particleoptions != null){
                for(int i = 0; i < 8; ++i) {
                    this.level.addParticle(particleoptions, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
                }
            }
        }
    }

    protected ParticleOptions getHitParticle() {
        return null;
//        ItemStack itemstack = this.getItemRaw();
//        return (ParticleOptions)(itemstack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemParticleOption(ParticleTypes.ITEM, itemstack));
    }

    /**
     * shoot bullet such as pea or spore
     */
    public void shootPea(Vector3d vec, double speed, double angleOffset) {
        this.shootPea(vec.x, vec.y, vec.z, speed, angleOffset);
    }

    /**
     * shoot bullet such as pea or spore
     */
    public void shootPea(double dx, double dy, double dz, double speed, double angleOffset) {
        final double down = this.getShootPeaAngle();
        final double dxz = Math.sqrt(dx * dx + dz * dz);
        if(down != 0){
            dy = Mth.clamp(dy, - dxz / down, dxz / down);//fix dy by angle
        }
//		System.out.println(dy + "," + dxz);
        final double degree = Mth.atan2(dz, dx) + Math.toRadians(angleOffset);
        dx = Math.cos(degree) * dxz;
        dz = Math.sin(degree) * dxz;
        final double totSpeed = Math.sqrt(dxz * dxz + dy * dy);
        this.setDeltaMovement(new Vec3(dx / totSpeed, dy / totSpeed, dz / totSpeed).scale(speed));
    }

    public void shootToTarget(LivingEntity target, double speed) {
        this.setDeltaMovement(target.position().add(0, target.getEyeHeight(), 0).subtract(this.position()).normalize().scale(speed));
    }

    public boolean shouldRenderAtSqrDistance(double dis) {
        double d0 = this.getBoundingBox().getSize() * 4.0D;
        if (Double.isNaN(d0)) {
            d0 = 4.0D;
        }

        d0 *= 64.0D;
        return dis < d0 * d0;
    }

    /**
     * get how much angle can shoot by thrower
     */
    public double getShootPeaAngle() {
        if (this.getOwner() instanceof ShooterPlant) {
            return ((ShooterPlant) this.getOwner()).getMaxShootAngle();
        }
        return 0;
    }

    public Entity getOwnerOrSelf() {
        return this.getOwner() == null ? this : this.getOwner();
    }

    /**
     * how long can bullet exist.
     */
    public int getMaxLiveTick(){
        return 100;
    }

    protected float getGravity() {
        return 0.03F;
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        compound.putInt("TickCount", this.tickCount);
        compound.putInt("GroupType", this.groupType.ordinal());
        compound.putBoolean("CanExist", this.canExist);
        compound.putFloat("AttackDamage", this.attackDamage);
        compound.putInt("BulletState", this.getBulletState().ordinal());
        compound.putInt("BulletType", this.getBulletType().ordinal());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditionalSaveData(CompoundTag compound) {
        if(compound.contains("TickCount")) {
            this.tickCount = compound.getInt("TickCount");
        }
        if(compound.contains("GroupType")) {
            this.groupType = EntityGroupHandler.getGroup(compound.getInt("GroupType"));
        }
        if(compound.contains("CanExist")){
            this.canExist = compound.getBoolean("CanExist");
        }
        if(compound.contains("AttackDamage")){
            this.attackDamage = compound.getFloat("AttackDamage");
        }
        if (compound.contains("BulletState")) {
            this.setBulletState(BulletStates.values()[compound.getInt("BulletState")]);
        }
        if (compound.contains("BulletType")) {
            this.setBulletType(BulletTypes.values()[compound.getInt("BulletType")]);
        }
    }

    public BulletStates getBulletState() {
        return BulletStates.values()[entityData.get(BULLET_STATE)];
    }

    public void setBulletState(BulletStates state) {
        entityData.set(BULLET_STATE, state.ordinal());
    }

    public BulletTypes getBulletType() {
        return BulletTypes.values()[entityData.get(BULLET_TYPE)];
    }

    public void setBulletType(BulletTypes type) {
        entityData.set(BULLET_TYPE, type.ordinal());
    }

    public float getCurrentDamage(){
        return this.getAttackDamage();
    }

    public float getAttackDamage() {
        return this.attackDamage;
    }

    public void setAttackDamage(float damage) {
        this.attackDamage = damage;
    }

    @Override
    public PVZGroupType getGroupType() {
        return this.groupType;
    }

    /**
     * default group type.
     */
    public PVZGroupType getInitialEntityGroup() {
        return PVZGroupType.NEUTRALS;
    }

    @Override
    public Optional<UUID> getOwnerUUID() {
        if(this.getOwner() != null){
            return Optional.ofNullable(this.getOwner().getUUID());
        }
        return Optional.empty();
    }

    public enum BulletTypes {
        NORMAL,
        BIG,
        HUGE,
    }

    public enum BulletStates {
        ICE,
        NORMAL,
        FIRE,
        BLUE_FIRE,
        ELECTRICITY,
    }

}
