package com.hungteen.pvz.common.entity.effect;

import com.hungteen.pvz.common.entity.PVZEntityBase;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-10 08:58
 **/
public abstract class EffectEntityBase extends PVZEntityBase {

    private static final EntityDataAccessor<Integer> EXIST_TICK = SynchedEntityData.defineId(EffectEntityBase.class,
            EntityDataSerializers.INT);
    protected int maxEffectTick;

    public EffectEntityBase(EntityType<?> type, Level world) {
        super(type, world);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(EXIST_TICK, 0);
    }

    @Override
    public void tick() {
        super.tick();
        if(! this.level.isClientSide) {
            this.setExistTick(this.getExistTick() + 1);
            if(this.getExistTick() > this.maxEffectTick) {
                this.discard();
            }
        }
    }

    /**
     * Checks if the entity is in range to render.
     */
    @OnlyIn(Dist.CLIENT)
    public boolean shouldRenderAtSqrDistance(double distance) {
        double d0 = this.getBoundingBox().getSize() * 4.0D;
        if (Double.isNaN(d0)) {
            d0 = 4.0D;
        }
        d0 = d0 * 256.0D;
        return distance < d0 * d0;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("effect_exist_tick", this.getExistTick());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("effect_exist_tick")) {
            this.setExistTick(compound.getInt("effect_exist_tick"));
        }
    }

    public int getExistTick() {
        return this.entityData.get(EXIST_TICK);
    }

    public void setExistTick(int tick) {
        this.entityData.set(EXIST_TICK, tick);
    }

}
