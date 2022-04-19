package com.hungteen.pvz.common.entity.plant;

import com.hungteen.pvz.api.interfaces.IAlmanacEntry;
import com.hungteen.pvz.api.interfaces.IHasEffects;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.sound.PVZSounds;
import com.hungteen.pvz.common.effect.PVZEffects;
import com.hungteen.pvz.common.entity.bullet.PVZProjectile;
import com.hungteen.pvz.common.entity.plant.base.PVZPlant;
import com.hungteen.pvz.common.impl.PAZAlmanacs;
import com.hungteen.pvz.common.impl.type.plant.PVZPlants;
import com.hungteen.pvz.utils.EffectUtil;
import com.mojang.datafixers.util.Pair;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

import java.util.*;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-16 19:33
 **/
public class SnowPea extends PeaShooter implements IHasEffects {

    public SnowPea(EntityType<? extends PVZPlant> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public Collection<MobEffectInstance> getEffects() {
        return List.of(getColdEffect());
    }

    public MobEffectInstance getColdEffect() {
        return EffectUtil.effect(PVZEffects.COLD_EFFECT.get(), this.getColdTick(), this.getColdLvl());
    }

    @Override
    public void addAlmanacEntries(List<Pair<IAlmanacEntry, Number>> list) {
        super.addAlmanacEntries(list);
        list.addAll(Arrays.asList(
                Pair.of(PAZAlmanacs.COLD_LEVEL, this.getColdLvl()),
                Pair.of(PAZAlmanacs.COLD_TIME, this.getColdTick())
        ));
    }

    /**
     * cold effect maxLevel.
     */
    public int getColdLvl() {
        return 5;
    }

    /**
     * cold effect duration.
     */
    public int getColdTick() {
        return 80;
    }

    @Override
    protected SoundEvent getShootSound() {
        return PVZSounds.SNOW_SHOOT.get();
    }

    @Override
    protected PVZProjectile.BulletStates getBulletState() {
        return PVZProjectile.BulletStates.ICE;
    }

    @Override
    public IPlantType getPlantType() {
        return PVZPlants.SNOW_PEA;
    }

}
