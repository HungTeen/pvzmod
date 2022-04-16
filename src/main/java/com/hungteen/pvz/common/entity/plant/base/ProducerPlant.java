package com.hungteen.pvz.common.entity.plant.base;

import com.hungteen.pvz.api.interfaces.IAlmanacEntry;
import com.hungteen.pvz.common.entity.PVZAttributes;
import com.hungteen.pvz.common.entity.ai.PVZGoal;
import com.hungteen.pvz.common.impl.PAZAlmanacs;
import com.hungteen.pvz.utils.EntityUtil;
import com.mojang.datafixers.util.Pair;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;

import java.util.Arrays;
import java.util.List;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-30 11:47
 **/
public abstract class ProducerPlant extends PVZPlant {

    public ProducerPlant(EntityType<? extends PVZPlant> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void initAttributes() {
        super.initAttributes();
        this.getAttribute(PVZAttributes.WORK_CD.get()).setBaseValue(this.getGenCD());
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new ProducerGenGoal(this));
    }

    @Override
    protected void normalPlantTick() {
        super.normalPlantTick();
    }

    @Override
    public void addAlmanacEntries(List<Pair<IAlmanacEntry, Number>> list) {
        super.addAlmanacEntries(list);
        list.addAll(Arrays.asList(
                Pair.of(PAZAlmanacs.GEN_CD, this.getGenCD())
        ));
    }

    /**
     * produce something ,like sunflower produce sun.
     * {@link ProducerGenGoal#tick()}
     */
    protected abstract void genSomething();

    /**
     * super produce.
     * {@link ProducerGenGoal#tick()}
     */
    protected abstract void genSuper();

    /**
     * get next produce CD.
     * {@link ProducerGenGoal#tick()}
     */
    public abstract int getGenCD();

    /**
     * animation usage.
     */
    public int getAnimGenCD() {
        return 10;
    }

    @Override
    public int getEnergeticDuration() {
        return 30;
    }

    /**
     * is producer going to gen, use for render sunflower sun layer.
     */
    public boolean isPlantInGen() {
        return this.getWorkTick() + this.getAnimGenCD() >= this.getCurrentWorkCD() || (EntityUtil.inEnergetic(this) && EntityUtil.getEnergeticTime(this) < this.getAnimGenCD());
    }

    static class ProducerGenGoal extends PVZGoal {

        private final ProducerPlant producer;

        public ProducerGenGoal(ProducerPlant entity) {
            this.producer = entity;
        }

        @Override
        public boolean canUse() {
            return true;
        }

        @Override
        public boolean canContinueToUse() {
            return true;
        }

        @Override
        public void stop() {
        }

        @Override
        public void tick() {
            //skip.
            if(! this.producer.canNormalUpdate()) {
                return ;
            }
            if(EntityUtil.inEnergetic(this.producer)){//Be Super Mode
                if(EntityUtil.getEnergeticTime(this.producer) == 1) {
                    this.producer.genSuper();
                }
                return ;
            }
            final int time = this.producer.getWorkTick();
            if(time >= this.producer.getCurrentWorkCD()){
                this.producer.genSomething();
                this.producer.setWorkTick(0);
            } else{
                this.producer.setWorkTick(time + 1);
            }
        }
    }

}
