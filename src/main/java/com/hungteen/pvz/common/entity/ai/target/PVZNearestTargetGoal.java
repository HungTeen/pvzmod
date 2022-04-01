package com.hungteen.pvz.common.entity.ai.target;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.hungteen.pvz.utils.AlgorithmUtil;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-30 22:17
 **/
public class PVZNearestTargetGoal extends PVZTargetGoal {

    protected final AlgorithmUtil.EntitySorter sorter;
    protected final Class<? extends LivingEntity> targetClass;

    public PVZNearestTargetGoal(Mob mobIn, boolean mustSee, boolean mustReach) {
        this(mobIn, LivingEntity.class, mustSee, mustReach);
    }

    public PVZNearestTargetGoal(Mob mobIn, Class<? extends LivingEntity> targetClass, boolean mustSee, boolean mustReach) {
        super(mobIn, mustSee, mustReach);
        this.targetClass = targetClass;
        this.sorter = new AlgorithmUtil.EntitySorter(mob);
    }

    @Override
    public boolean canUse() {
        if (this.targetChance > 0 && this.mob.getRandom().nextInt(this.targetChance) != 0) {
            return false;
        }
        List<LivingEntity> list1 = EntityUtil.getTargetableLivings(this.mob, getAABB()).stream().filter(target -> {
            return (! this.mustSee || this.checkSenses(target)) && this.checkOther(target);
        }).collect(Collectors.toList());
        
        if (list1.isEmpty()) {
            return false;
        }
        Collections.sort(list1, this.sorter);
        this.targetMob = list1.get(0);
        return true;
    }

    @Override
    protected boolean checkOther(LivingEntity entity) {
        return super.checkOther(entity) && this.targetClass.isAssignableFrom(entity.getClass());
    }
}
