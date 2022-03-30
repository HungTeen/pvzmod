package com.hungteen.pvz.common.entity.plant.base;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-27 16:59
 **/
public abstract class ShooterPlant extends PVZPlant {

    public ShooterPlant(EntityType<? extends PVZPlant> entityType, Level level) {
        super(entityType, level);
    }

}
