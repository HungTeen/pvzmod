package com.hungteen.pvz.utils;

import com.hungteen.pvz.common.potion.EffectRegister;

import net.minecraft.world.effect.MobEffectInstance;

public class WeaponUtil {

	/**
	 * get cold effect by snow pea lvl
	 */
	public static MobEffectInstance getPeaGunColdEffect(int lvl) {
		int duration=80,amount=5;
		if(lvl<=20) {
			int now=(lvl-1)/4;
			duration+=now*20;
			amount+=now;
		}
		return new MobEffectInstance(EffectRegister.COLD_EFFECT.get(), duration, amount,false,false);
	}
	 
}
