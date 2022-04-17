package com.hungteen.pvz.api.interfaces;

import net.minecraft.world.effect.MobEffectInstance;

import java.util.Collection;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-17 22:31
 **/
public interface IHasEffects {

    Collection<MobEffectInstance> getEffects();

}
