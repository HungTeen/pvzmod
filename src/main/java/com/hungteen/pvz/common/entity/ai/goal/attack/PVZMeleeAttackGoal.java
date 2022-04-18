package com.hungteen.pvz.common.entity.ai.goal.attack;

import com.hungteen.pvz.common.entity.ai.goal.PVZGoal;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.level.pathfinder.Path;

import java.util.EnumSet;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-18 11:58
 **/
public abstract class PVZMeleeAttackGoal extends MeleeAttackGoal {

    protected final boolean longMemory;

    public PVZMeleeAttackGoal(PathfinderMob mob, double speedIn, boolean useLongMemory){
        super(mob, speedIn, useLongMemory);
        this.longMemory = useLongMemory;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canContinueToUse() {
        return super.canContinueToUse() && EntityUtil.checkCanEntityBeAttack(this.mob, this.mob.getTarget());
    }

}
