package com.hungteen.pvz.common.entity.plant;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.plant.base.PVZPlant;
import com.hungteen.pvz.common.impl.type.plant.PVZPlants;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-08 15:57
 **/
public class PotatoMine extends PVZPlant {

    public PotatoMine(EntityType<? extends PVZPlant> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public int getEnergeticDuration() {
        return 0;
    }

    @Override
    public IPlantType getPlantType() {
        return PVZPlants.POTATO_MINE;
    }
}
