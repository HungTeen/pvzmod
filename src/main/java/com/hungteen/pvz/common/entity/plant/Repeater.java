package com.hungteen.pvz.common.entity.plant;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.plant.base.PVZPlant;
import com.hungteen.pvz.common.impl.type.plant.PVZPlants;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-05-06 11:22
 **/
public class Repeater extends PeaShooter{

    public Repeater(EntityType<? extends PVZPlant> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void startAttack(@Nonnull Entity target) {
        this.shootCount += 2;
    }

    @Override
    public void onEnergetic(boolean first) {
        super.onEnergetic(first);
        this.shootCount = 120;
        this.powerShootCount = 2;
    }

    @Override
    public int getEnergeticDuration() {
        return 300;
    }

    @Override
    public IPlantType getPlantType() {
        return PVZPlants.REPEATER;
    }
}
