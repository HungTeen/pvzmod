package com.hungteen.pvz.common.event.handler;

import com.hungteen.pvz.api.paz.IPAZEntity;
import com.hungteen.pvz.api.paz.IZombieEntity;
import com.hungteen.pvz.common.entity.zombie.base.AbstractBossZombieEntity;
import com.hungteen.pvz.common.event.PVZLivingEvents;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.common.potion.EffectRegister;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.ConfigUtil;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class LivingEventHandler {

	/**
	 * apply potion effects on living.
	 * {@link PVZLivingEvents#onLivingHurt(net.minecraftforge.event.entity.living.LivingHurtEvent)}
	 */
	public static void handleHurtEffects(LivingEntity target, PVZEntityDamageSource source) {
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
	public static void handleHurtSounds(LivingEntity target, PVZEntityDamageSource source) {
		if(source.isEatDamage()) {
			EntityUtil.playSound(target, SoundRegister.CHOMP.get());
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
		//all paz entity can not deal more than limit damage to other entities.
		if(ev.getSource() instanceof PVZEntityDamageSource && ! (ev.getEntityLiving() instanceof IPAZEntity && ev.getSource().getEntity() instanceof IPAZEntity)){
			ev.setAmount(Math.min(ConfigUtil.getLimitDamage(), ev.getAmount()));
		}
		//(not boss)zombie damage to zombie or both are boss entity.
		if(ev.getEntityLiving() instanceof IZombieEntity && ev.getSource().getEntity() instanceof IZombieEntity && (! (ev.getSource().getEntity() instanceof AbstractBossZombieEntity) || (ev.getEntityLiving() instanceof AbstractBossZombieEntity))){
			ev.setAmount(Math.min(100, ev.getAmount()));
		}
		//avoid instant kill mod.
		if(ev.getSource() != DamageSource.OUT_OF_WORLD && ev.getAmount() > ev.getEntityLiving().getMaxHealth() * 0.8 && ev.getEntityLiving() instanceof AbstractBossZombieEntity){
			ev.setAmount(ConfigUtil.getLimitDamage());
		}
	}
	
}
