package com.hungteen.pvz.common.world.invasion;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SpawnType{

    private final EntityType<? extends MobEntity> spawnType;
    //TODO nbt
    private CompoundNBT nbt = new CompoundNBT();
    private int occurDay;
    private int spawnWeight;
    private boolean canSpawnInWater = false;

    public SpawnType(EntityType<? extends MobEntity> spawnType){
        this.spawnType = spawnType;
    }

    public void setOccurDay(int occurDay) {
        this.occurDay = occurDay;
    }

    public void setSpawnWeight(int spawnWeight) {
        this.spawnWeight = spawnWeight;
    }

    public EntityType<? extends MobEntity> getSpawnType() {
        return spawnType;
    }

    public int getOccurDay() {
        return occurDay;
    }

    public int getSpawnWeight() {
        return spawnWeight;
    }

    public void setNbt(CompoundNBT nbt) {
        this.nbt = nbt;
    }

    public void setSpawnInWater(boolean canSpawnInWater) {
        this.canSpawnInWater = canSpawnInWater;
    }

    public boolean canSpawnInWater() {
        return canSpawnInWater;
    }

    public boolean checkPos(World world, BlockPos pos){
        if(! world.getBlockState(pos.below()).isValidSpawn(world, pos.below(), this.spawnType)){
            return false;
        }
        if(this.canSpawnInWater){
            return ! world.getBlockState(pos.below()).getFluidState().isEmpty();
        } else{
            return world.getBlockState(pos.below()).getFluidState().isEmpty();
        }
    }
}
