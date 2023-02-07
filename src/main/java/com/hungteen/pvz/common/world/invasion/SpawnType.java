package com.hungteen.pvz.common.world.invasion;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SpawnType{

    private final EntityType<? extends MobEntity> spawnType;
    private CompoundNBT nbt = new CompoundNBT();
    private int invasionLevel;
    private int spawnWeight;
    private PlaceType placeType;

    public SpawnType(EntityType<? extends MobEntity> spawnType){
        this.spawnType = spawnType;
    }

    public void setInvasionLevel(int invasionLevel) {
        this.invasionLevel = invasionLevel;
    }

    public void setSpawnWeight(int spawnWeight) {
        this.spawnWeight = spawnWeight;
    }

    public EntityType<? extends MobEntity> getSpawnType() {
        return spawnType;
    }

    public int getInvasionLevel() {
        return invasionLevel;
    }

    public int getSpawnWeight() {
        return spawnWeight;
    }

    public void setNbt(CompoundNBT nbt) {
        this.nbt = nbt;
    }

    public CompoundNBT getNbt() {
        return nbt;
    }

    public void setPlaceType(PlaceType placeType) {
        this.placeType = placeType;
    }

    public PlaceType getPlaceType() {
        return placeType;
    }

    public boolean checkPos(World world, BlockPos pos){
        if(getPlaceType() == PlaceType.LAND){
            return world.getBlockState(pos.below()).isValidSpawn(world, pos.below(), this.spawnType) && world.getBlockState(pos.below()).getFluidState().isEmpty();
        } else if(getPlaceType() == PlaceType.WATER){
            return ! world.getBlockState(pos.below()).getFluidState().isEmpty();
        } else if(getPlaceType() == PlaceType.SNOW){
            return world.getBlockState(pos.below()).getBlock() == Blocks.SNOW || world.getBlockState(pos.below()).getBlock() == Blocks.SNOW_BLOCK;
        } else if(getPlaceType() == PlaceType.SNOW_AND_LAND){
            return world.getBlockState(pos.below()).getBlock() == Blocks.SNOW || (world.getBlockState(pos.below()).isValidSpawn(world, pos.below(), this.spawnType) && world.getBlockState(pos.below()).getFluidState().isEmpty());
        } else if(getPlaceType() == PlaceType.SKY){
            return true;
        }
        return false;
    }

    public enum PlaceType{

        LAND,
        WATER,
        SNOW,
        SKY,
        SNOW_AND_LAND
    }
}
