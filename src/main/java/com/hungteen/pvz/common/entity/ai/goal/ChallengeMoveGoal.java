package com.hungteen.pvz.common.entity.ai.goal;

import com.hungteen.pvz.common.world.challenge.Challenge;

import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;

/**
 * @program: pvzmod-1.16.5
 * @author: HungTeen
 * @create: 2022-02-03 13:55
 **/
public class ChallengeMoveGoal extends Goal {

    private final MobEntity owner;
    private final Challenge challenge;
    private final BlockPos center;

    public ChallengeMoveGoal(MobEntity owner, Challenge challenge){
        this.owner = owner;
        this.challenge = challenge;
        this.center = this.challenge.getCenter();
//        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.JUMP));
    }

    @Override
    public boolean canUse() {
        if(this.challenge == null || this.challenge.isRemoving()){
            return false;
        }
        if(this.owner.getTarget() != null){
            return false;
        }
        if(this.owner.distanceToSqr(this.center.getX(), this.center.getY(), this.center.getZ()) < 10){
            return false;
        }
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        return this.canUse();
    }

    public void start() {
        this.owner.getNavigation().moveTo(this.center.getX(), this.center.getY() + 1, this.center.getZ(), 1F);
    }

    public void stop() {
        this.owner.getNavigation().stop();
        super.stop();
    }

}
