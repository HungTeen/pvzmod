package com.hungteen.pvz.common.world.invasion;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobEntity;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;

public class SpawnType{

    private final EntityType<? extends MobEntity> spawnType;
    private CompoundTag nbt = new CompoundTag();
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

    public void setNbt(CompoundTag nbt) {
        this.nbt = nbt;
    }

    public CompoundTag getNbt() {
        return nbt;
    }

    public void setPlaceType(PlaceType placeType) {
        this.placeType = placeType;
    }

    public PlaceType getPlaceType() {
        return placeType;
    }

    public boolean checkPos(Level world, Mth pos){
        if(getPlaceType() == PlaceType.LAND){
            return world.getBlockState(pos.below()).isValidSpawn(world, pos.below(), this.spawnType) && world.getBlockState(pos.below()).getFluidState().isEmpty();
        } else if(getPlaceType() == PlaceType.WATER){
            return ! world.getBlockState(pos.below()).getFluidState().isEmpty();
        } else if(getPlaceType() == PlaceType.SNOW){
            return world.getBlockState(pos).getBlock() == Blocks.SNOW || world.getBlockState(pos.below()).getBlock() == Blocks.SNOW_BLOCK;
        } else if(getPlaceType() == PlaceType.SKY){
            return true;
        }
        return false;
    }

    public enum PlaceType{

        LAND,
        WATER,
        SNOW,
        SKY
    }
}
