package com.hungteen.pvz.common.entity.ai.goal;

import com.hungteen.pvz.common.entity.plant.base.PVZPlant;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-30 13:40
 **/
public class PVZLookRandomlyGoal extends RandomLookAroundGoal {

    private Mob plant;

    public PVZLookRandomlyGoal(Mob entitylivingIn) {
        super(entitylivingIn);
        this.plant = entitylivingIn;
    }

    @Override
    public boolean canUse() {
        return this.canExecute() && super.canUse();
    }

    private boolean canExecute() {
        if(this.plant instanceof PVZPlant && !((PVZPlant) this.plant).canNormalUpdate()) {
            return false;
        }
//        if(this.plant instanceof PVZZombie && !((PVZZombie) this.plant).canNormalUpdate()) {
//            return false;
//        }
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        return this.canExecute() && super.canContinueToUse();
    }
}

