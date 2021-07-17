package com.hungteen.pvz.common.event.handler;

import com.hungteen.pvz.api.interfaces.IPVZPlant;
import com.hungteen.pvz.api.interfaces.IPVZZombie;
import com.hungteen.pvz.common.event.PVZLivingEvents;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class LivingEventHandler {

	/**
	 * apply potion effects on living.
	 * {@link PVZLivingEvents#onLivingHurt(net.minecraftforge.event.entity.living.LivingHurtEvent)}
	 */
	public static void handleHurtEffects(LivingEntity target, PVZDamageSource source) {
		if(source.isDefended()) {
			return ;
		}
		source.getEffects().forEach(effect -> {
			EntityUtil.addPotionEffect(target, effect);
		});
	}
	
	/**
	 * balance the damage between different mods.
	 * {@link PVZLivingEvents#onLivingHurt(net.minecraftforge.event.entity.living.LivingHurtEvent)}
	 */
	public static void handleHurtDamage(final LivingHurtEvent ev) {
		if(ev.getSource() == null || !(ev.getSource().getEntity() instanceof LivingEntity)) {
			return ;
		}
		final LivingEntity attacker = (LivingEntity) ev.getSource().getEntity();
		if(attacker instanceof IPVZZombie && ev.getEntityLiving() instanceof IPVZZombie) {
			if(! EntityUtil.isEntityBoss(attacker)) {//common zombie attack zombie.
				ev.setAmount(Math.min(EntityUtil.LIMITED_DAMAGE, ev.getAmount()));
			}
			return ;
		}
		//plant attack others except zombies.
		if(attacker instanceof IPVZPlant && !(ev.getEntityLiving() instanceof IPVZZombie)) {
			if(ev.getEntityLiving() instanceof EnderDragonEntity) {
				ev.setAmount(Math.min(EntityUtil.LIMITED_DAMAGE * 4, ev.getAmount()));
			} else {
				ev.setAmount(Math.min(EntityUtil.LIMITED_DAMAGE, ev.getAmount()));
			}
			return ;
		}
		//zombie attack others except plants.
		if(attacker instanceof IPVZZombie && !(ev.getEntityLiving() instanceof IPVZPlant)) {
			ev.setAmount(Math.min(EntityUtil.LIMITED_DAMAGE, ev.getAmount()));
		}
	}
	
}
