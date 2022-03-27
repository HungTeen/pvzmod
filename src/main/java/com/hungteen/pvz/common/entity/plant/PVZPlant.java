package com.hungteen.pvz.common.entity.plant;

import com.hungteen.pvz.api.enums.PVZGroupType;
import com.hungteen.pvz.api.interfaces.IPlantEntity;
import com.hungteen.pvz.api.types.base.IPAZType;
import com.hungteen.pvz.common.entity.PVZPAZ;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-27 16:54
 **/
public abstract class PVZPlant extends PVZPAZ implements IPlantEntity {

    public PVZPlant(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public IPAZType getPAZType() {
        return getPlantType();
    }

    @Override
    public PVZGroupType getGroupType() {
        return PVZGroupType.PLANTS;
    }

}
