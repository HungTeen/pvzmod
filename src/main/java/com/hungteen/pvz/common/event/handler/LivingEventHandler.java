package com.hungteen.pvz.common.event.handler;

import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.event.PVZLivingEvents;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.EffectRegister;
import com.hungteen.pvz.register.SoundRegister;
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
		if(! source.isDefended()) {//source not defended by armor.
			if(source.isFlameDamage()) {
				if(target.hasEffect(EffectRegister.COLD_EFFECT.get())){
				    target.removeEffect(EffectRegister.COLD_EFFECT.get());
				}
				if(target.hasEffect(EffectRegister.FROZEN_EFFECT.get())){
				    target.removeEffect(EffectRegister.FROZEN_EFFECT.get());
				}
			}
			source.getEffects().forEach(effect -> EntityUtil.addPotionEffect(target, effect));
		}
	}
	
	/**
	 * handle sound when living hurt.
	 * {@link PVZLivingEvents#onLivingHurt(net.minecraftforge.event.entity.living.LivingHurtEvent)}
	 */
	public static void handleHurtSounds(LivingEntity target, PVZDamageSource source) {
		if(source.isEatDamage()) {
			EntityUtil.playSound(target, SoundRegister.EAT.get());
		}
		if(source.isFlameDamage() && source.isAppease()) {
			EntityUtil.playSound(target, SoundRegister.FLAME_HIT.get());
		}
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
		if(attacker instanceof PVZZombieEntity && ev.getEntityLiving() instanceof PVZZombieEntity) {
			if(! EntityUtil.isEntityBoss(attacker)) {//common zombie attack zombie.
				ev.setAmount(Math.min(EntityUtil.LIMITED_DAMAGE, ev.getAmount()));
			}
			return ;
		}
		//plant attack others except zombies.
		if(attacker instanceof PVZPlantEntity && !(ev.getEntityLiving() instanceof PVZZombieEntity)) {
			if(ev.getEntityLiving() instanceof EnderDragonEntity) {
				ev.setAmount(Math.min(EntityUtil.LIMITED_DAMAGE * 4, ev.getAmount()));
			} else {
				ev.setAmount(Math.min(EntityUtil.LIMITED_DAMAGE, ev.getAmount()));
			}
			return ;
		}
		//zombie attack others except plants.
		if(attacker instanceof PVZZombieEntity && !(ev.getEntityLiving() instanceof PVZPlantEntity)) {
			ev.setAmount(Math.min(EntityUtil.LIMITED_DAMAGE, ev.getAmount()));
		}
	}
	
}
