package com.hungteen.pvz.common.entity.ai;

import com.hungteen.pvz.common.entity.plant.base.PVZPlant;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.interfaces.ICanAttract;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-01 17:56
 **/
public class PlantAttractGoal extends Goal {

    private final ICanAttract attracter;
    private final PVZPlant plantEntity;
    private final int attractCD;
    private int attractTick;

    public PlantAttractGoal(PVZPlant plantEntity, ICanAttract attracter, int cd) {
        this.attracter = attracter;
        this.plantEntity = plantEntity;
        this.attractCD = cd;
    }

    @Override
    public boolean canUse() {
        return this.plantEntity.canNormalUpdate();
    }

    @Override
    public boolean canContinueToUse() {
        return this.canUse();
    }

    @Override
    public void tick() {
        ++ attractTick;
        if(attractTick >= attractCD) {
            this.attract();
            this.attractTick = 0;
        }
    }

    protected void attract() {
        double range = this.plantEntity.getAttributeValue(Attributes.FOLLOW_RANGE);
        EntityUtil.getTargetableLivings(this.plantEntity, EntityUtil.getEntityAABB(this.plantEntity, range, range))
                .stream().filter(target -> this.attracter.canAttract(target)).forEach(target -> {
                    this.attracter.attract(target);
                });
    }

}
