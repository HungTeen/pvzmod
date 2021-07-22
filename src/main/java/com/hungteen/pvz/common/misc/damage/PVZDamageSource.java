package com.hungteen.pvz.common.misc.damage;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.hungteen.pvz.common.entity.bullet.BallEntity;
import com.hungteen.pvz.common.entity.bullet.ButterEntity;
import com.hungteen.pvz.common.entity.bullet.CornEntity;
import com.hungteen.pvz.common.entity.bullet.FumeEntity;
import com.hungteen.pvz.common.entity.bullet.KernelEntity;
import com.hungteen.pvz.common.entity.bullet.MelonEntity;
import com.hungteen.pvz.common.entity.bullet.StarEntity;
import com.hungteen.pvz.common.entity.bullet.itembullet.CabbageEntity;
import com.hungteen.pvz.common.entity.bullet.itembullet.PeaEntity;
import com.hungteen.pvz.common.entity.bullet.itembullet.SporeEntity;
import com.hungteen.pvz.common.entity.plant.enforce.SquashEntity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class PVZDamageSource extends DamageSource {

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
	private int damageCount = 0;

	public static final PVZDamageSource PLANT_WILT = new PVZDamageSource("plant_wilt");
	public static final PVZDamageSource CHOMPER_PLANT = new PVZDamageSource("chomper_plant").setEatDamage();

	public PVZDamageSource(String name) {
		this(name, null);
	}
	
	public PVZDamageSource(String name, Entity attacker) {
		this(name, attacker, attacker);
	}
	
	public PVZDamageSource(String name, Entity damagingEntity, Entity attacker) {
		super(name);
		this.attacker = damagingEntity;
		this.attackOwner = attacker;
	}
	
	//projectiles
	public static PVZDamageSource pea(PeaEntity pea, Entity shooter) {
		return (PVZDamageSource) new PVZDamageSource("pea", pea, shooter).setAppease();
	}
	
	public static PVZDamageSource snowPea(PeaEntity pea, Entity shooter) {
		return (PVZDamageSource) new PVZDamageSource("snow_pea", pea, shooter).setAppease().setIceDamage();
	}
	
	public static PVZDamageSource flamePea(PeaEntity pea, Entity shooter) {
		return (PVZDamageSource) new PVZDamageSource("flame_pea", pea, shooter).setAppease().setFlameDamage();
	}
	
	public static PVZDamageSource spore(SporeEntity pea, Entity shooter) {
		return (PVZDamageSource) new PVZDamageSource("spore", pea, shooter).setAppease();
	}
	
	public static PVZDamageSource fume(FumeEntity pea, Entity shooter) {
		return (PVZDamageSource) new PVZDamageSource("fume", pea, shooter).setAppease().setThroughDamage();
	}
	
	public static PVZDamageSource star(StarEntity pea, Entity shooter) {
		return (PVZDamageSource) new PVZDamageSource("star", pea, shooter).setAppease();
	}
	
	public static PVZDamageSource cabbage(CabbageEntity pea, Entity shooter) {
		return (PVZDamageSource) new PVZDamageSource("cabbage", pea, shooter).setParabola();
	}
	
	public static PVZDamageSource kernel(KernelEntity pea, Entity shooter) {
		return (PVZDamageSource) new PVZDamageSource("kernel", pea, shooter).setParabola();
	}
	
	public static PVZDamageSource corn(CornEntity pea, Entity shooter) {
		return (PVZDamageSource) new PVZDamageSource("corn", pea, shooter).setParabola().setExplosion();
	}
	
	public static PVZDamageSource butter(ButterEntity pea, Entity shooter) {
		return (PVZDamageSource) new PVZDamageSource("butter", pea, shooter).setParabola();
	}
	
	public static PVZDamageSource melon(MelonEntity pea, Entity shooter) {
		return (PVZDamageSource) new PVZDamageSource("melon", pea, shooter).setParabola();
	}
	
	public static PVZDamageSource winterMelon(MelonEntity pea, Entity shooter) {
		return (PVZDamageSource) new PVZDamageSource("winter_melon", pea, shooter).setParabola().setIceDamage();
	}
	
	public static PVZDamageSource ball(BallEntity pea, Entity shooter) {
		return (PVZDamageSource) new PVZDamageSource("ball", pea, shooter).setParabola();
	}
	
	//normal
	public static PVZDamageSource normal(Entity projectile, Entity attacker) {
		return new PVZDamageSource("normal", projectile, attacker);
	}
	
	public static PVZDamageSource normal(Entity attacker) {
		return normal(attacker, attacker);
	}

	//eat
	public static PVZDamageSource eat(Entity projectile, Entity attacker) {
		return new PVZDamageSource("eat", projectile, attacker).setEatDamage();
	}
	
	public static PVZDamageSource eat(Entity attacker) {
		return eat(attacker, attacker);
	}
	
	//explosion
	public static PVZDamageSource explode(Entity projectile, Entity shooter) {
		return (PVZDamageSource) new PVZDamageSource("explosion", projectile, shooter).setExplosion();
	}
	
	public static PVZDamageSource explode(Entity attacker) {
		return explode(attacker, attacker);
	}
	
	//deadly
	public static PVZDamageSource causeDeadlyDamage(Entity projectile, Entity shooter) {
		return new PVZDamageSource("deadly", projectile, shooter);
	}
	
	public static PVZDamageSource causeDeadlyDamage(Entity attacker) {
		return causeDeadlyDamage(attacker, attacker);
	}
	
	//crush
	public static PVZDamageSource causeCrushDamage(Entity attacker) {
		return new PVZDamageSource("crush", attacker).setCrushDamage();
	}
	
	//thorn
	public static PVZDamageSource causeThornDamage(Entity projectile, Entity shooter) {
		return new PVZDamageSource("thorn", projectile, shooter).setThornDamage();
	}
	
	public static PVZDamageSource causeThornDamage(Entity attacker) {
		return causeThornDamage(attacker, attacker);
	}
	
	//ice
	public static PVZDamageSource causeIceDamage(Entity projectile, Entity shooter) {
		return new PVZDamageSource("ice", projectile, shooter).setIceDamage();
	}
	
	public static PVZDamageSource causeIceDamage(Entity attacker) {
		return causeIceDamage(attacker, attacker);
	}
	
	//flame
	public static PVZDamageSource causeFlameDamage(Entity projectile, Entity shooter) {
		return new PVZDamageSource("flame", projectile, shooter).setFlameDamage();
	}
	
	public static PVZDamageSource causeFlameDamage(Entity attacker) {
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

	public PVZDamageSource setCount(int cnt) {
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

	public PVZDamageSource setAppease() {
		this.isAppease = true;
		this.setProjectile();
		return this;
	}

	public boolean isParabola() {
		return isParabola;
	}

	public PVZDamageSource setParabola() {
		this.isParabola = true;
		this.setProjectile();
		return this;
	}

	public boolean isIceDamage() {
		return isIceDamage;
	}

	public PVZDamageSource setIceDamage() {
		this.isIceDamage = true;
		return this;
	}

	public boolean isFlameDamage() {
		return isFlameDamage;
	}

	public PVZDamageSource setFlameDamage() {
		this.isFlameDamage = true;
		this.setIsFire();
		return this;
	}

	public boolean isEatDamage() {
		return isEatDamage;
	}

	public PVZDamageSource setEatDamage() {
		this.isEatDamage = true;
		return this;
	}

	public boolean isCrushDamage() {
		return isCrushDamage;
	}

	public PVZDamageSource setCrushDamage() {
		this.isCrushDamage = true;
		this.bypassArmor();
		return this;
	}

	public boolean isThroughDamage() {
		return isThroughDamage;
	}

	public PVZDamageSource setThroughDamage() {
		this.isThroughDamage = true;
		this.bypassArmor();
		return this;
	}

	public boolean isThornDamage() {
		return isThornDamage;
	}

	public PVZDamageSource setThornDamage() {
		this.isThornDamage = true;
		return this;
	}
	
}
