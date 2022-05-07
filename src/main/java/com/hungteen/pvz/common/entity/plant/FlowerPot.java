package com.hungteen.pvz.common.entity.plant;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.plant.base.FakePlant;
import com.hungteen.pvz.common.entity.plant.base.PVZPlant;
import com.hungteen.pvz.common.impl.type.plant.PVZPlants;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-05-07 11:33
 **/
public class FlowerPot extends FakePlant {
    public FlowerPot(EntityType<? extends PVZPlant> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public IPlantType getPlantType() {
        return PVZPlants.FLOWER_POT;
    }
}
