package com.hungteen.pvz.common.entity.plant;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.bullet.PVZProjectile;
import com.hungteen.pvz.common.entity.plant.base.PVZPlant;
import com.hungteen.pvz.common.impl.type.plant.PVZPlants;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-16 19:33
 **/
public class SnowPea extends PeaShooter{

    public SnowPea(EntityType<? extends PVZPlant> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected PVZProjectile.BulletStates getBulletState() {
        return PVZProjectile.BulletStates.ICE;
    }

    @Override
    public IPlantType getPlantType() {
        return PVZPlants.SNOW_PEA;
    }

}
