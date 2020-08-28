package com.hungteen.pvz.misc.damage;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class PVZDamageSource extends DamageSource{

	private Entity attackOwner = null;
	private Entity attacker = null;
	private PVZDamageType damageType;
	
	public static final DamageSource CHOMPER_PLANT = new DamageSource("chomper_plant");
	
	public PVZDamageSource(String name, Entity damagingEntity, Entity attacker, PVZDamageType damageType) {
		super(name);
		this.attacker=damagingEntity;
		this.attackOwner=attacker;
		this.damageType=damageType;
	}
	
	public static PVZDamageSource causeNormalDamage(Entity projectile, Entity shooter) {
		return new PVZDamageSource("pvz_normal",projectile, shooter, PVZDamageType.NORMAL);
	}
	
	public static PVZDamageSource causeWeakDamage(Entity projectile, Entity shooter){	
		return new PVZDamageSource("pvz_weak",projectile, shooter, PVZDamageType.WEAK);
	}
	
	public static PVZDamageSource causeAppeaseDamage(Entity projectile, Entity shooter){	
		return new PVZDamageSource("pvz_appease",projectile, shooter, PVZDamageType.APPEASE);
	}
	
	public static PVZDamageSource causeEatDamage(Entity projectile, Entity shooter){	
		return new PVZDamageSource("pvz_eat",projectile, shooter, PVZDamageType.EAT);
	}
	
	public static PVZDamageSource causeExplosionDamage(Entity projectile, Entity shooter){	
		return new PVZDamageSource("pvz_explosion",projectile, shooter, PVZDamageType.EXPLOSION);
	}
	
	public static PVZDamageSource causeIceDamage(Entity projectile, Entity shooter){	
		return new PVZDamageSource("pvz_ice",projectile, shooter, PVZDamageType.ICE);
	}
	
	@Override
	public ITextComponent getDeathMessage(LivingEntity entityLivingBaseIn) {
        String s = "death.attack." + this.getDamageType();
        return new TranslationTextComponent(s, entityLivingBaseIn.getDisplayName());
	}

	public static PVZDamageSource copyWithNewEnt(PVZDamageSource other, Entity damagingEntity, Entity attacker) {
		PVZDamageSource newSrc = new PVZDamageSource(other.getDamageType(), damagingEntity, attacker, other.damageType);
		return newSrc;
	}
	
	public PVZDamageType getPVZDamageType()
	{
		return this.damageType;
	}
	
	@Override
	public Entity getTrueSource() {
		return this.attackOwner;
	}

	@Override
	public Entity getImmediateSource() {
		return this.attacker;
	}
}
