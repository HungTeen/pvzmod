package com.hungteen.pvz.common.entity.zombie;

import com.hungteen.pvz.api.types.IZombieType;
import com.hungteen.pvz.common.entity.PVZPAZ;
import com.hungteen.pvz.common.entity.zombie.base.PVZZombie;
import com.hungteen.pvz.common.impl.type.zombie.PVZZombies;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-05 08:13
 **/
public class NormalZombie extends PVZZombie {

    public NormalZombie(EntityType<? extends PVZPAZ> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public int getEnergeticDuration() {
        return 20;
    }

    @Override
    public IZombieType getZombieType() {
        return PVZZombies.NORMAL_ZOMBIE;
    }

    @Override
    protected float getLife() {
        return 20;
    }
}
