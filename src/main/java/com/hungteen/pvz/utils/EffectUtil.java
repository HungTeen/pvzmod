package com.hungteen.pvz.utils;

import net.minecraft.world.effect.Effect;
import net.minecraft.world.effect.MobEffectInstance;

/**
 * @program: pvzmod-1.16.5
 * @author: HungTeen
 * @create: 2022-01-19 22:44
 **/
public class EffectUtil {

    public static MobEffectInstance effect(Effect effect, int time, int lvl){
        return new MobEffectInstance(effect, time, lvl, false, false);
    }

    public static MobEffectInstance viewEffect(Effect effect, int time, int lvl){
        return new MobEffectInstance(effect, time, lvl, true, true);
    }

}
