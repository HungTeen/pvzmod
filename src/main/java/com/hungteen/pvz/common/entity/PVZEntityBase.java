package com.hungteen.pvz.common.entity;

import com.mojang.math.Vector3d;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-10 08:59
 **/
public abstract class PVZEntityBase extends Entity {

    public PVZEntityBase(EntityType<?> type, Level world) {
        super(type, world);
    }

    @Override
    protected void defineSynchedData() {
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
    }

    @Override
    public void tick() {
        super.tick();
        if(this.tickCount <= 5) {
            this.refreshDimensions();
        }
    }

    protected void tickMove() {
        Vec3 vec3d = this.getDeltaMovement();
        double d0 = this.getX() + vec3d.x;
        double d1 = this.getY() + vec3d.y;
        double d2 = this.getZ() + vec3d.z;
        float f1;
        if (this.isInWater()) {
            for (int i = 0; i < 4; ++i) {
                this.level.addParticle(ParticleTypes.BUBBLE, d0 - vec3d.x * 0.25D, d1 - vec3d.y * 0.25D,
                        d2 - vec3d.z * 0.25D, vec3d.x, vec3d.y, vec3d.z);
            }
            f1 = 0.8F;
        } else {
            f1 = 1F;
        }
        this.setDeltaMovement(vec3d.scale((double) f1));
        if (! this.isNoGravity()) {
            Vec3 vec3d1 = this.getDeltaMovement();
            this.setDeltaMovement(vec3d1.x, vec3d1.y - (double) this.getGravityVelocity(), vec3d1.z);
        }
        this.move(MoverType.SELF, this.getDeltaMovement());
    }

    protected float getGravityVelocity() {
        return 0.05F;
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}