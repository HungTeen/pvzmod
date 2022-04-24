package com.hungteen.pvz.common.entity.creature.garden;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-20 23:02
 *
 * TODO Mari Gold Garden Plant.
 **/
public class FlowerGardenPlant extends GardenPlant{
    public FlowerGardenPlant(EntityType<? extends GardenPlant> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected int getNextGrowCD() {
        return 0;
    }

    @Override
    protected int getMaxStage() {
        return 0;
    }

    @Override
    public float getRenderScale() {
        return 0;
    }
}
