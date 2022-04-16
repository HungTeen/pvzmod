package com.hungteen.pvz.common.entity.ai.target;

import com.hungteen.pvz.utils.AlgorithmUtil;
import com.hungteen.pvz.utils.MathUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-15 17:16
 **/
public class PVZScatterTargetGoal extends PVZNearestTargetGoal {

    private static final int IGNORE_DISTANCE = 6;
    private static final int MUST_ATTACK_DISTANCE = 10;
    protected final AlgorithmUtil.EntitySorter sorter;


    public PVZScatterTargetGoal(Mob mobIn, boolean mustSee, boolean mustReach) {
        this(mobIn, LivingEntity.class, mustSee, mustReach);
    }

    public PVZScatterTargetGoal(Mob mobIn, Class<? extends LivingEntity> targetClass, boolean mustSee, boolean mustReach) {
        super(mobIn, targetClass, mustSee, mustReach);
        this.sorter = new AlgorithmUtil.EntitySorter(mob);
    }

    @Override
    protected @Nullable LivingEntity chooseTarget(List<LivingEntity> list) {
        if(! list.isEmpty()){
            Collections.sort(list, this.sorter);
            final float nearestDistance = list.get(0).distanceTo(this.mob);
            // you must attack when target is very close to you.
            if(nearestDistance <= MUST_ATTACK_DISTANCE){
                return list.get(0);
            }
            //binary search.
            int l = 0, r = list.size() - 1;
            int resultPos = - 1;
            while(l <= r){
                int mid = (l + r) / 2;
                final float tmpDistance = list.get(mid).distanceTo(this.mob);
                //find the most far target which is also less than a specific distance with the nearest distance.
                if(tmpDistance - nearestDistance <= IGNORE_DISTANCE){
                    l = mid + 1;
                    resultPos = mid;
                } else{
                    r = mid - 1;
                }
            }
            if(resultPos == - 1 || resultPos == 0){
                return list.get(0);
            }
            return list.get(this.mob.getRandom().nextInt(resultPos + 1));
        }
        return null;
    }
}
