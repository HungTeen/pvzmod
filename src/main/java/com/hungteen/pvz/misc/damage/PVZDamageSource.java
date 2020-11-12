package com.hungteen.pvz.misc.damage;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class PVZDamageSource extends DamageSource {

	private Entity attackOwner = null;
	private Entity attacker = null;
	private PVZDamageType damageType;
	private boolean isDefended = false;// is defended by some defence
	private List<EffectInstance> effects = new ArrayList<>();

	public static final DamageSource CHOMPER_PLANT = new DamageSource("chomper_plant");

	public PVZDamageSource(String name, Entity damagingEntity, Entity attacker, PVZDamageType damageType) {
		super(name);
		this.attacker = damagingEntity;
		this.attackOwner = attacker;
		this.damageType = damageType;
	}

	public static PVZDamageSource causeNormalDamage(Entity projectile, Entity shooter) {
		return new PVZDamageSource("pvz_normal", projectile, shooter, PVZDamageType.NORMAL);
	}

	public static PVZDamageSource causeWeakDamage(Entity projectile, Entity shooter) {
		return new PVZDamageSource("pvz_weak", projectile, shooter, PVZDamageType.WEAK);
	}

	public static PVZDamageSource causeAppeaseDamage(Entity projectile, Entity shooter) {
		return new PVZDamageSource("pvz_appease", projectile, shooter, PVZDamageType.APPEASE);
	}

	public static PVZDamageSource causeEatDamage(Entity projectile, Entity shooter) {
		return new PVZDamageSource("pvz_eat", projectile, shooter, PVZDamageType.EAT);
	}

	public static PVZDamageSource causeExplosionDamage(Entity projectile, Entity shooter) {
		return new PVZDamageSource("pvz_explosion", projectile, shooter, PVZDamageType.EXPLOSION);
	}

	public static PVZDamageSource causeIceDamage(Entity projectile, Entity shooter) {
		return new PVZDamageSource("pvz_ice", projectile, shooter, PVZDamageType.ICE);
	}

	public static PVZDamageSource causeFireDamage(Entity projectile, Entity shooter) {
		return new PVZDamageSource("pvz_fire", projectile, shooter, PVZDamageType.FIRE);
	}

	public static PVZDamageSource causeSpikeDamage(Entity projectile, Entity shooter) {
		return new PVZDamageSource("pvz_spike", projectile, shooter, PVZDamageType.SPIKE);
	}

	public static PVZDamageSource causeCrushDamage(Entity projectile, Entity shooter) {
		return new PVZDamageSource("pvz_crush", projectile, shooter, PVZDamageType.CRUSH);
	}

	public static PVZDamageSource causeThroughDamage(Entity projectile, Entity shooter) {
		return new PVZDamageSource("pvz_through", projectile, shooter, PVZDamageType.THROUGH);
	}

	public static boolean isEnforceDamage(DamageSource source) {
		if (source.damageType.equals("mob")) {
			return true;
		}
		if (source instanceof PVZDamageSource) {
			PVZDamageType type = ((PVZDamageSource) source).getPVZDamageType();
			if (type == PVZDamageType.EAT || type == PVZDamageType.CRUSH || type == PVZDamageType.SPIKE) {
				return true;
			}
		}
		return false;
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

	public PVZDamageType getPVZDamageType() {
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

	public void setDefended(boolean is) {
		this.isDefended = is;
	}

	public boolean isDefended() {
		return this.isDefended;
	}

	@Override
	public boolean isUnblockable() {
		return this.getPVZDamageType() == PVZDamageType.THROUGH;
	}
	
	public void addEffect(EffectInstance instance) {
		this.effects.add(instance);
	}
	
	public List<EffectInstance> getEffects(){
		return this.effects;
	}
	
	/**
	 * Gets the location from which the damage originates.
	 */
	@Nullable
	public Vec3d getDamageLocation() {
		return this.attacker != null ? this.attacker.getPositionVec() : null;
	}

}
