package com.hungteen.pvz.common.entity.zombie;

import com.hungteen.pvz.api.types.IZombieType;
import com.hungteen.pvz.common.entity.zombie.base.PVZZombie;
import com.hungteen.pvz.common.impl.type.zombie.PVZZombies;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-24 08:03
 **/
public class ConeHeadZombie extends NormalZombie{

    public ConeHeadZombie(EntityType<? extends PVZZombie> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public IZombieType getZombieType() {
        return PVZZombies.CONE_HEAD_ZOMBIE;
    }
}
