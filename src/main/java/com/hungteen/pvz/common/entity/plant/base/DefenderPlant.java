package com.hungteen.pvz.common.entity.plant.base;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.ai.PlantAttractGoal;
import com.hungteen.pvz.utils.interfaces.ICanAttract;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-01 17:31
 **/
public abstract class DefenderPlant extends PVZPlant implements ICanAttract {

    public DefenderPlant(EntityType<? extends PVZPlant> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void initAttributes() {
        super.initAttributes();
        this.getAttribute(Attributes.FOLLOW_RANGE).setBaseValue(this.getAttractRange());
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new PlantAttractGoal(this, this, 20));
    }

    @Override
    public boolean canAttract(LivingEntity target) {
        return ICanAttract.super.canAttract(target) && this.getSensing().hasLineOfSight(target);
    }

    @Override
    public int getEnergeticDuration() {
        return 40;
    }

}
