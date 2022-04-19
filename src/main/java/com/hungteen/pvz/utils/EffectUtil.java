package com.hungteen.pvz.utils;

import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-17 22:37
 **/
public class EffectUtil {

    public static MobEffectInstance effect(MobEffect effect, int time, int lvl){
        return new MobEffectInstance(effect, time, lvl, false, false);
    }

    public static MobEffectInstance viewEffect(MobEffect effect, int time, int lvl){
        return new MobEffectInstance(effect, time, lvl, true, true);
    }

}
