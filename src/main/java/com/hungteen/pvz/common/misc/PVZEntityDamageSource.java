package com.hungteen.pvz.common.misc;

import com.hungteen.pvz.common.entity.bullet.*;
import com.hungteen.pvz.common.entity.bullet.itembullet.CabbageEntity;
import com.hungteen.pvz.common.entity.bullet.itembullet.MetalItemEntity;
import com.hungteen.pvz.common.entity.bullet.itembullet.PeaEntity;
import com.hungteen.pvz.common.entity.bullet.itembullet.SporeEntity;
import com.hungteen.pvz.common.entity.plant.enforce.SquashEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class PVZEntityDamageSource extends EntityDamageSource {

	private final List<EffectInstance> effects = new ArrayList<>();
	private Entity attackOwner = null;
	private Entity attacker = null;
	private boolean isAppease = false;//shooter.
	private boolean isParabola = false;//pult.
	private boolean isIceDamage = false;
	private boolean isFlameDamage = false;
	private boolean isEatDamage = false;//check by hypno shroom.
	private boolean isCrushDamage = false;//damage can be defended by spike rock.
	private boolean isThroughDamage = false;//damage can ignore defence. such as fume pass screen door.
	private boolean isThornDamage = false;//sharp damage, which can hit balloon.
	private boolean isDefended = false;// is defended by some defence. such as snow pea hit screen door.
	private boolean mustHurt = false;//explosion must hurt every zombies.
	private int damageCount = 0;

	public static final PVZEntityDamageSource PLANT_WILT = new PVZEntityDamageSource("plant_wilt");
	public static final PVZEntityDamageSource CHOMPER_PLANT = new PVZEntityDamageSource("chomper_plant").setEatDamage();

	public PVZEntityDamageSource(String name) {
		this(name, null);
	}

	public PVZEntityDamageSource(String name, Entity attacker) {
		this(name, attacker, attacker);
	}

	public PVZEntityDamageSource(String name, Entity damagingEntity, Entity attacker) {
		super(name, attacker);
		this.attacker = damagingEntity;
		this.attackOwner = attacker;
	}
	
	//projectiles
	public static PVZEntityDamageSource pea(PeaEntity pea, Entity shooter) {
		return (PVZEntityDamageSource) new PVZEntityDamageSource("pea", pea, shooter).setAppease();
	}
	
	public static PVZEntityDamageSource snowPea(PeaEntity pea, Entity shooter) {
		return (PVZEntityDamageSource) new PVZEntityDamageSource("snow_pea", pea, shooter).setAppease().setIceDamage();
	}
	
	public static PVZEntityDamageSource flamePea(PeaEntity pea, Entity shooter) {
		return (PVZEntityDamageSource) new PVZEntityDamageSource("flame_pea", pea, shooter).setAppease().setFlameDamage();
	}
	
	public static PVZEntityDamageSource spore(SporeEntity pea, Entity shooter) {
		return (PVZEntityDamageSource) new PVZEntityDamageSource("spore", pea, shooter).setAppease();
	}
	
	public static PVZEntityDamageSource fume(FumeEntity pea, Entity shooter) {
		return (PVZEntityDamageSource) new PVZEntityDamageSource("fume", pea, shooter).setAppease().setThroughDamage();
	}
	
	public static PVZEntityDamageSource star(StarEntity pea, Entity shooter) {
		return (PVZEntityDamageSource) new PVZEntityDamageSource("star", pea, shooter).setAppease();
	}
	
	public static PVZEntityDamageSource metal(MetalItemEntity pea, Entity shooter) {
		return (PVZEntityDamageSource) new PVZEntityDamageSource("metal", pea, shooter).setAppease();
	}
	
	public static PVZEntityDamageSource cabbage(CabbageEntity pea, Entity shooter) {
		return (PVZEntityDamageSource) new PVZEntityDamageSource("cabbage", pea, shooter).setParabola();
	}
	
	public static PVZEntityDamageSource kernel(KernelEntity pea, Entity shooter) {
		return (PVZEntityDamageSource) new PVZEntityDamageSource("kernel", pea, shooter).setParabola();
	}
	
	public static PVZEntityDamageSource corn(CornEntity pea, Entity shooter) {
		return (PVZEntityDamageSource) new PVZEntityDamageSource("corn", pea, shooter).setParabola().setExplosion();
	}
	
	public static PVZEntityDamageSource butter(ButterEntity pea, Entity shooter) {
		return (PVZEntityDamageSource) new PVZEntityDamageSource("butter", pea, shooter).setParabola();
	}
	
	public static PVZEntityDamageSource melon(MelonEntity pea, Entity shooter) {
		return (PVZEntityDamageSource) new PVZEntityDamageSource("melon", pea, shooter).setParabola();
	}
	
	public static PVZEntityDamageSource winterMelon(MelonEntity pea, Entity shooter) {
		return (PVZEntityDamageSource) new PVZEntityDamageSource("winter_melon", pea, shooter).setParabola().setIceDamage();
	}
	
	public static PVZEntityDamageSource ball(BallEntity pea, Entity shooter) {
		return (PVZEntityDamageSource) new PVZEntityDamageSource("ball", pea, shooter).setParabola();
	}
	
	//normal
	public static PVZEntityDamageSource normal(Entity projectile, Entity attacker) {
		return new PVZEntityDamageSource("normal", projectile, attacker);
	}
	
	public static PVZEntityDamageSource normal(Entity attacker) {
		return normal(attacker, attacker);
	}

	//eat
	public static PVZEntityDamageSource eat(Entity projectile, Entity attacker) {
		return new PVZEntityDamageSource("eat", projectile, attacker).setEatDamage();
	}
	
	public static PVZEntityDamageSource eat(Entity attacker) {
		return eat(attacker, attacker);
	}
	
	//explosion
	public static PVZEntityDamageSource explode(Entity projectile, Entity shooter) {
		return (PVZEntityDamageSource) new PVZEntityDamageSource("explosion", projectile, shooter).setMustHurt().setExplosion();
	}
	
	public static PVZEntityDamageSource explode(Entity attacker) {
		return explode(attacker, attacker);
	}
	
	//deadly
	public static PVZEntityDamageSource causeDeadlyDamage(Entity projectile, Entity shooter) {
		return new PVZEntityDamageSource("deadly", projectile, shooter).setMustHurt();
	}
	
	public static PVZEntityDamageSource causeDeadlyDamage(Entity attacker) {
		return causeDeadlyDamage(attacker, attacker);
	}
	
	//crush
	public static PVZEntityDamageSource causeCrushDamage(Entity attacker) {
		return new PVZEntityDamageSource("crush", attacker).setCrushDamage();
	}
	
	//thorn
	public static PVZEntityDamageSource causeThornDamage(Entity projectile, Entity shooter) {
		return new PVZEntityDamageSource("thorn", projectile, shooter).setThornDamage();
	}
	
	public static PVZEntityDamageSource causeThornDamage(Entity attacker) {
		return causeThornDamage(attacker, attacker);
	}
	
	//ice
	public static PVZEntityDamageSource causeIceDamage(Entity projectile, Entity shooter) {
		return new PVZEntityDamageSource("ice", projectile, shooter).setIceDamage();
	}
	
	public static PVZEntityDamageSource causeIceDamage(Entity attacker) {
		return causeIceDamage(attacker, attacker);
	}
	
	//flame
	public static PVZEntityDamageSource causeFlameDamage(Entity projectile, Entity shooter) {
		return new PVZEntityDamageSource("flame", projectile, shooter).setFlameDamage();
	}
	
	public static PVZEntityDamageSource causeFlameDamage(Entity attacker) {
		return causeFlameDamage(attacker, attacker);
	}

	/**
	 * {@link SquashEntity#isPlantImmuneTo(DamageSource)}
	 */
	public static boolean isEnforceDamage(DamageSource source) {
		return ! source.isProjectile() && ! source.isMagic() && ! source.isExplosion() && ! source.isFire();
	}

	@Override
	public ITextComponent getLocalizedDeathMessage(LivingEntity entityLivingBaseIn) {
		String s = "death.attack.pvz." + this.getMsgId();
		return new TranslationTextComponent(s, entityLivingBaseIn.getDisplayName());
	}

	@Override
	public Entity getEntity() {
		return this.attackOwner;
	}

	@Override
	public Entity getDirectEntity() {
		return this.attacker;
	}
	
	/**
	 * Gets the location from which the damage originates.
	 */
	@Nullable
	public Vector3d getSourcePosition() {
		return this.attacker != null ? this.attacker.position() : null;
	}

	public PVZEntityDamageSource setCount(int cnt) {
		this.damageCount = cnt;
		return this;
	}
	
	public int getDamageCount() {
		return this.damageCount;
	}
	
	//handle effects.
	public void addEffect(EffectInstance instance) {
		this.effects.add(instance);
	}
	
	public List<EffectInstance> getEffects(){
		return this.effects;
	}
	
	public void setDefended(boolean is) {
		this.isDefended = is;
	}
    
	/**
	 * if true, effect can not be apply on target.
	 */
	public boolean isDefended() {
		return this.isDefended;
	}

	//getter and setter.
	public boolean isAppease() {
		return isAppease;
	}

	public PVZEntityDamageSource setAppease() {
		this.isAppease = true;
		this.setProjectile();
		return this;
	}

	public boolean isParabola() {
		return isParabola;
	}

	public PVZEntityDamageSource setParabola() {
		this.isParabola = true;
		this.setProjectile();
		return this;
	}

	public boolean isIceDamage() {
		return isIceDamage;
	}

	public PVZEntityDamageSource setIceDamage() {
		this.isIceDamage = true;
		return this;
	}

	public boolean isFlameDamage() {
		return isFlameDamage;
	}

	public PVZEntityDamageSource setFlameDamage() {
		this.isFlameDamage = true;
		this.setIsFire();
		return this;
	}

	public boolean isEatDamage() {
		return isEatDamage;
	}

	public PVZEntityDamageSource setEatDamage() {
		this.isEatDamage = true;
		return this;
	}

	public boolean isCrushDamage() {
		return isCrushDamage;
	}

	public PVZEntityDamageSource setCrushDamage() {
		this.isCrushDamage = true;
		this.bypassArmor();
		return this;
	}

	public boolean isThroughDamage() {
		return isThroughDamage;
	}

	public PVZEntityDamageSource setThroughDamage() {
		this.isThroughDamage = true;
		this.bypassArmor();
		return this;
	}

	public boolean isThornDamage() {
		return isThornDamage;
	}

	public PVZEntityDamageSource setThornDamage() {
		this.isThornDamage = true;
		return this;
	}
	
	public boolean isMustHurt() {
		return this.mustHurt;
	}

	public PVZEntityDamageSource setMustHurt() {
		this.mustHurt = true;
		return this;
	}
	
}
