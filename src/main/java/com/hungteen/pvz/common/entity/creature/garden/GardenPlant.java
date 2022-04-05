package com.hungteen.pvz.common.entity.creature.garden;

import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.enums.PVZGroupType;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.api.types.base.IPAZType;
import com.hungteen.pvz.common.entity.PVZMob;
import com.hungteen.pvz.common.impl.type.plant.PVZPlants;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.Optional;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-01 20:14
 **/
public class GardenPlant extends PVZMob {

    private static final EntityDataAccessor<String> PLANT_TYPE = SynchedEntityData.defineId(GardenPlant.class, EntityDataSerializers.STRING);

    public GardenPlant(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(PLANT_TYPE, PVZPlants.PEA_SHOOTER.getIdentity());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if(compound.contains("PlantType")){
            this.setPlantType(compound.getString("PlantType"));
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putString("PlantType", this.getPlantType().getIdentity());
    }

    public void setPlantType(IPlantType type){
        this.entityData.set(PLANT_TYPE, type.getIdentity());
    }

    private void setPlantType(String name){
        this.entityData.set(PLANT_TYPE, name);
    }

    @Nullable
    public IPlantType getPlantType(){
        Optional<IPAZType> opt = PVZAPI.get().getPAZType(this.entityData.get(PLANT_TYPE));
        return opt.isPresent() && opt.get() instanceof IPlantType ? (IPlantType) opt.get() : null;
    }

    @Override
    public PVZGroupType getGroupType() {
        return PVZGroupType.PLANTS;
    }
}
