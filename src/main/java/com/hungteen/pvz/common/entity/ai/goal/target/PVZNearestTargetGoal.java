package com.hungteen.pvz.common.entity.ai.goal.target;

import java.util.Collections;
import java.util.List;

import com.hungteen.pvz.utils.AlgorithmUtil;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import org.jetbrains.annotations.Nullable;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-30 22:17
 **/
public class PVZNearestTargetGoal extends PVZTargetGoal {

    protected final AlgorithmUtil.EntitySorter sorter;

    public PVZNearestTargetGoal(Mob mobIn, boolean mustSee, boolean mustReach) {
        this(mobIn, LivingEntity.class, mustSee, mustReach);
    }

    public PVZNearestTargetGoal(Mob mobIn, Class<? extends LivingEntity> targetClass, boolean mustSee, boolean mustReach) {
        super(mobIn, targetClass, mustSee, mustReach);
        this.sorter = new AlgorithmUtil.EntitySorter(mob);
    }

    @Nullable
    @Override
    protected LivingEntity chooseTarget(List<LivingEntity> list) {
        if(! list.isEmpty()){
            Collections.sort(list, this.sorter);
            return list.get(0);
        }
        return null;
    }

}
