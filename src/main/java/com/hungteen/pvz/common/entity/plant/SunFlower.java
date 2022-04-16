package com.hungteen.pvz.common.entity.plant;

import com.hungteen.pvz.api.interfaces.IAlmanacEntry;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.PVZAttributes;
import com.hungteen.pvz.common.entity.drop.Sun;
import com.hungteen.pvz.common.entity.plant.base.PVZPlant;
import com.hungteen.pvz.common.entity.plant.base.ProducerPlant;
import com.hungteen.pvz.common.impl.PAZAlmanacs;
import com.hungteen.pvz.common.impl.type.plant.PVZPlants;
import com.hungteen.pvz.utils.EntityUtil;
import com.mojang.datafixers.util.Pair;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-27 16:59
 **/
public class SunFlower extends ProducerPlant {

    protected boolean relax = false;

    public SunFlower(EntityType<? extends PVZPlant> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void plantTick() {
        super.plantTick();
        //update work cd.
        if(! this.level.isClientSide){
            if(this.isSunEnough() && this.relax){
                this.relax = false;
                this.getAttribute(PVZAttributes.WORK_CD.get()).setBaseValue(this.getGenCD());
            }
            if(! this.isSunEnough() && ! this.relax){
                this.relax = true;
                this.getAttribute(PVZAttributes.WORK_CD.get()).setBaseValue(this.getGenCD() * 4);
            }
        }
    }

    protected boolean isSunEnough(){
        return this.level.isDay() && ! this.level.isRaining();
    }

    @Override
    public void genSomething() {
        Sun.dropSunRandomly(level, blockPosition(), this.getSunAmount(), 0.25);
        EntityUtil.playSound(this, SoundEvents.EXPERIENCE_ORB_PICKUP);
    }

    @Override
    public void genSuper() {
        Sun.spawnSunsByAmount(level, blockPosition(), this.getSuperSunAmount(), 100, 0.3);
        EntityUtil.playSound(this, SoundEvents.EXPERIENCE_ORB_PICKUP);
    }

    @Override
    public void addAlmanacEntries(List<Pair<IAlmanacEntry, Number>> list) {
        super.addAlmanacEntries(list);
        list.addAll(Arrays.asList(
                Pair.of(PAZAlmanacs.GEN_SUN_AMOUNT, this.getSunAmount())
        ));
    }

    /**
     * get normal gen sun amount by maxLevel.
     */
    public int getSunAmount(){
        return 25;
    }

    /**
     * get normal gen sun amount by maxLevel.
     */
    public int getSuperSunAmount(){
        return 500;
    }

    @Override
    public int getGenCD() {
        return 500;
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return EntityDimensions.scalable(0.8F, 1.2F);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if(compound.contains("SunEnough")){
            this.relax = compound.getBoolean("SunEnough");
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("SunEnough", this.relax);
    }

    @Override
    public IPlantType getPlantType() {
        return PVZPlants.SUN_FLOWER;
    }

}
