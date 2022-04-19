package com.hungteen.pvz.common.entity.ai.goal;

import net.minecraft.world.entity.ai.goal.Goal;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-16 15:40
 **/
public abstract class PVZGoal extends Goal {

    private final boolean alwaysTick;

    public PVZGoal(){
        this(true);
    }

    public PVZGoal(boolean alwaysTick){
        this.alwaysTick = alwaysTick;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return this.alwaysTick;
    }
}
