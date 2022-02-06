package com.hungteen.pvz.utils;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;

/**
 * @program: pvzmod-1.16.5
 * @author: HungTeen
 * @create: 2022-01-19 22:44
 **/
public class EffectUtil {

    public static EffectInstance effect(Effect effect, int time, int lvl){
        return new EffectInstance(effect, time, lvl, false, false);
    }

    public static EffectInstance viewEffect(Effect effect, int time, int lvl){
        return new EffectInstance(effect, time, lvl, true, true);
    }

}
