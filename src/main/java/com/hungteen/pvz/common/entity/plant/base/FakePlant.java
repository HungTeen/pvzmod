package com.hungteen.pvz.common.entity.plant.base;

import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-05-07 11:34
 **/
public abstract class FakePlant extends PVZPlant{

    public FakePlant(EntityType<? extends PVZPlant> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public int getEnergeticDuration() {
        return 0;
    }

    @Override
    public EntityDimensions getDimensions(Pose p_21047_) {
        return EntityDimensions.scalable(1F, 1F);
    }
}
