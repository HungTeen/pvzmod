package com.hungteen.pvz.common.entity.plant.base;

import com.hungteen.pvz.common.entity.plant.PVZPlant;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-27 16:59
 **/
public class ShooterPlant extends PVZPlant {

    public ShooterPlant(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

}
