package com.hungteen.pvz.common.entity.plant;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.effect.PVZPotions;
import com.hungteen.pvz.common.entity.plant.base.ShooterPlant;
import com.hungteen.pvz.common.impl.type.plant.PVZPlants;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-27 16:59
 **/
public class SunFlower extends PVZPlant {

    public SunFlower(EntityType<? extends PVZPlant> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public IPlantType getPlantType() {
        return PVZPlants.SUN_FLOWER;
    }
}
