package com.hungteen.pvzmod.damage;

import java.util.ArrayList;

import com.hungteen.pvzmod.util.enums.Enums;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.translation.I18n;

public class PVZDamageSource extends EntityDamageSource{

	protected boolean attackSuccessful=false;
	public Entity attacker=null;
	public PVZDamageType damageType;
	
	public void setAttackSuccessful() {
		this.attackSuccessful=true;
	}
	
	public boolean wasSuccessful() {
		return this.attackSuccessful;
	}
	
	public static PVZDamageSource causeWeakDamage(Entity projectile, Entity shooter){	
		return new PVZDamageSource("pvz_weak",projectile, shooter, PVZDamageType.WEAK);
	}
	
	public static PVZDamageSource causeExplosionDamage(Entity projectile, Entity shooter){	
		return new PVZDamageSource("pvz_explosion",projectile, shooter, PVZDamageType.EXPLOSION);
	}
	
	public static PVZDamageSource causeNormalDamage(Entity bullet,Entity shooter) {
		return new PVZDamageSource("pvz_physics",bullet, shooter, PVZDamageType.NORMAL);
	}
	
	public static PVZDamageSource causeSnowDamage(Entity bullet,Entity shooter) {
		return new PVZDamageSource("pvz_snow",bullet, shooter, PVZDamageType.SNOW);
	}
	
	public static PVZDamageSource causeIceDamage(Entity bullet,Entity shooter) {
		return new PVZDamageSource("pvz_ice",bullet, shooter, PVZDamageType.ICE);
	}
	
	public static PVZDamageSource causeFireDamage(Entity bullet,Entity shooter) {
		return new PVZDamageSource("pvz_fire",bullet, shooter, PVZDamageType.FIRE);
	}
	
	public static PVZDamageSource causeButterDamage(Entity bullet,Entity shooter) {
		return new PVZDamageSource("pvz_butter",bullet, shooter, PVZDamageType.BUTTER);
	}
	
	public static PVZDamageSource causeMagicDamage(Entity bullet,Entity shooter) {
		return new PVZDamageSource("pvz_magic",bullet, shooter, PVZDamageType.MAGIC);
	}
	
	public static PVZDamageSource causeEatDamage(Entity bullet,Entity shooter) {//僵尸吃植物
		return new PVZDamageSource("pvz_eat",bullet, shooter, PVZDamageType.EAT);
	}
	
	public static PVZDamageSource causeDeadDamage(Entity bullet,Entity shooter) {//巨人 车子的致命攻击 但是会被地刺王抵消
		return new PVZDamageSource("pvz_dead",bullet, shooter, PVZDamageType.DEAD);
	}
	
	public static PVZDamageSource causeMotalDamage(Entity bullet,Entity shooter) {//来自潘多拉魔盒必死的攻击
		return new PVZDamageSource("pvz_motal",bullet, shooter, PVZDamageType.MOTAL);
	}
	
	@Override
    public ITextComponent getDeathMessage(EntityLivingBase entityLivingBaseIn)
    {
		ITextComponent itextcomponent;
		ItemStack itemstack = ItemStack.EMPTY;
		if(this.attacker==null && this.damageSourceEntity==null) {	
			itextcomponent = entityLivingBaseIn.getDisplayName();
		} else {	
			itextcomponent = this.attacker == null ? this.damageSourceEntity.getDisplayName() : this.attacker.getDisplayName();
			itemstack = this.attacker instanceof EntityLivingBase ? ((EntityLivingBase)this.attacker).getHeldItemMainhand() : ItemStack.EMPTY;  
		}
        String s = "death.attack." + this.getDamageType();
        String s1 = s + ".item";
        return !itemstack.isEmpty() && itemstack.hasDisplayName() && I18n.canTranslate(s1) ? new TextComponentTranslation(s1, new Object[] {entityLivingBaseIn.getDisplayName(), itextcomponent, itemstack.getTextComponent()}) : new TextComponentTranslation(s, new Object[] {entityLivingBaseIn.getDisplayName(), itextcomponent});
    }
	
	public PVZDamageSource(String name, Entity damagingEntity, Entity attacker, PVZDamageType damageType) {
		super(name, damagingEntity);
		this.attacker = attacker;
		this.damageType = damageType;
		setBehaviourForVanilla();
	}

	public static PVZDamageSource copyWithNewEnt(PVZDamageSource other, Entity damagingEntity, Entity attacker) {
		PVZDamageSource newSrc = new PVZDamageSource(other.getDamageType(), damagingEntity, attacker, other.damageType);
		return newSrc;
	}
	
	public void setBehaviourForVanilla(){
		switch(damageType){
		    case NORMAL:
				break;
			case EXPLOSION:
				this.setExplosion();
				break;
			case ICE:
				break;
			default:
				break;
		}
	}
	
	public PVZDamageType getPVZDamageType() {
		return damageType;
	}
	
	@Override
	public Entity getTrueSource() {
		return this.attacker;
	}

	@Override
	public Entity getImmediateSource() {
		return this.damageSourceEntity;
	}
}
