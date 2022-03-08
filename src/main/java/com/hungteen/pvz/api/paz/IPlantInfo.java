package com.hungteen.pvz.api.paz;

import com.hungteen.pvz.api.types.IPlantType;
import net.minecraft.nbt.CompoundTag;

/**
 * used for info data, which owned by both inner plants and outer plants.
 */
public interface IPlantInfo {

    /**
     * read data from entity nbt.
     */
    void read(CompoundTag nbt);

    /**
     * write data to entity nbt.
     */
    void write(CompoundTag nbt);

    /**
     * place it on plantEntity.
     */
    void placeOn(IPlantEntity plantEntity, int sunCost);

    /**
     * run when its inner plants in super mode.
     */
    void onSuper(IPlantEntity plantEntity);

    /**
     * run when heal by card.
     */
    void onHeal(IPlantEntity plantEntity, float percent);

    void setType(IPlantType type);

    IPlantType getType();

    void setSunCost(int cost);

    int getSunCost();

}
