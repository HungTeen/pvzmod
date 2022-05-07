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
 * @create: 2022-05-07 11:34
 **/
public class LilyPad extends FakePlant {

    public LilyPad(EntityType<? extends PVZPlant> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public IPlantType getPlantType() {
        return PVZPlants.LILY_PAD;
    }
}
