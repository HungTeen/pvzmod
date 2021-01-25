package com.hungteen.pvz.event.handler;

import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.LivingEntity;

public class LivingEventHandler {

	public static void handleHurtEffects(LivingEntity target, PVZDamageSource source) {
		if(source.isDefended()) return ;
		source.getEffects().forEach((effect) -> {
			EntityUtil.addEntityPotionEffect(target, effect);
		});
		
	}
}
