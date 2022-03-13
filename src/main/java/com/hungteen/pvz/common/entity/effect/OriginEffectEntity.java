package com.hungteen.pvz.common.entity.effect;

import com.hungteen.pvz.common.entity.PVZEntities;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-10 09:09
 **/
public class OriginEffectEntity extends EffectEntityBase {

    private static final EntityDataAccessor<Float> LENGTH = SynchedEntityData.defineId(OriginEffectEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> COLOR = SynchedEntityData.defineId(OriginEffectEntity.class, EntityDataSerializers.INT);

    public OriginEffectEntity(EntityType<?> type, Level world) {
        super(type, world);
        this.maxEffectTick = 100;
    }

    public static void create(Level world, BlockPos pos, int color){
        OriginEffectEntity effectEntity = PVZEntities.ORIGIN_EFFECT.get().create(world);
        final boolean exist = EntityUtil.hasNearBy(world, pos, 5, e -> e instanceof OriginEffectEntity);
        if(! exist) {// avoid overlapped.
            EntityUtil.onEntitySpawn(world, effectEntity, pos);
            effectEntity.setColor(color);
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(LENGTH, 1F);
        this.entityData.define(COLOR, 255);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if(compound.contains("effect_color")){
            this.setColor(compound.getInt("effect_color"));
        }
        if(compound.contains("effect_length")){
            this.setLength(compound.getFloat("effect_length"));
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("effect_color", this.getColor());
        compound.putFloat("effect_length", this.getLength());
    }

    public void setColor(int color){
        this.entityData.set(COLOR, color);
    }

    public int getColor(){
        return this.entityData.get(COLOR);
    }

    public void setLength(float l){
        this.entityData.set(LENGTH, l);
    }

    public float getLength(){
        return this.entityData.get(LENGTH);
    }
}
