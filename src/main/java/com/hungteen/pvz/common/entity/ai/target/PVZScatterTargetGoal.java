package com.hungteen.pvz.common.entity.ai.target;

import com.hungteen.pvz.utils.AlgorithmUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-15 17:16
 **/
public class PVZScatterTargetGoal extends PVZTargetGoal {

    protected final AlgorithmUtil.EntitySorter sorter;

    public PVZScatterTargetGoal(Mob mobIn, boolean mustSee, boolean mustReach) {
        this(mobIn, LivingEntity.class, mustSee, mustReach);
    }

    public PVZScatterTargetGoal(Mob mobIn, Class<? extends LivingEntity> targetClass, boolean mustSee, boolean mustReach) {
        super(mobIn, targetClass, mustSee, mustReach);
        this.sorter = new AlgorithmUtil.EntitySorter(mob);
    }

    @Override
    public boolean canUse() {
        return false;
    }
}
