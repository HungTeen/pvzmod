package com.hungteen.pvz.common.entity.creature;

import com.hungteen.pvz.api.enums.PVZGroupType;
import com.hungteen.pvz.api.interfaces.IHasGroup;
import com.hungteen.pvz.api.interfaces.IHasOwner;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

import java.util.Optional;
import java.util.UUID;

/**
 * @program: pvzmod-1.16.5
 * @author: HungTeen
 * @create: 2022-02-11 16:00
 **/
public class GardenPlantEntity extends CreatureEntity implements IHasOwner, IHasGroup {

    private static final DataParameter<Integer> AGE = EntityDataManager.defineId(GardenPlantEntity.class, DataSerializers.INT);
    private static final DataParameter<Integer> STATE = EntityDataManager.defineId(GardenPlantEntity.class, DataSerializers.INT);

    public GardenPlantEntity(EntityType<? extends CreatureEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(AGE, 0);
        this.entityData.define(STATE, 0);
    }

    @Override
    public PVZGroupType getEntityGroupType() {
        return PVZGroupType.PLANTS;
    }

    @Override
    public Optional<UUID> getOwnerUUID() {
        return Optional.empty();
    }

    public enum GardenStates{
        NORMAL,
        INSECT,
        MUSIC,
        FERTILIZER,
        WATER,
        BEE
    }
}
