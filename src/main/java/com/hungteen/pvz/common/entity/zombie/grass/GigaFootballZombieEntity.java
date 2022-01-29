package com.hungteen.pvz.common.entity.zombie.grass;

import com.hungteen.pvz.common.impl.zombie.ZombieType;
import com.hungteen.pvz.common.impl.zombie.GrassZombies;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.remove.MetalTypes;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GigaFootballZombieEntity extends FootballZombieEntity {

	protected boolean isRushing = false;
	private final int minRushCD = 200;
	private final int maxRushCD = 600;
	
	public GigaFootballZombieEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.canCollideWithZombie = false;
	}
	
	@Override
	public void normalZombieTick() {
		super.normalZombieTick();
		if(! this.level.isClientSide) {
			if(!this.isRushing && this.getAttackTime() == 0) {
				this.updateRush(true);
			}
			this.setAttackTime(Math.max(0, this.getAttackTime() - 1));
		}
	}
	
	protected void updateRush(boolean is) {
		this.isRushing = is;
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(is ? ZombieUtil.WALK_HUGE_FAST : ZombieUtil.WALK_FAST);
		if(! is) {
			this.setAttackTime(MathUtil.getRandomMinMax(getRandom(), minRushCD, maxRushCD));
		}
	}
	
	@Override
	protected boolean shouldCollideWithEntity(LivingEntity target) {
		return EntityUtil.canTargetEntity(this, target);
	}
	
	@Override
	protected void doPush(Entity target) {
		if(this.isRushing() && target instanceof LivingEntity) {
			target.hurt(PVZEntityDamageSource.causeCrushDamage(this), EntityUtil.getMaxHealthDamage((LivingEntity) target));
			this.updateRush(false);
		}
	}
	
	@Override
	protected double getCollideWidthOffset() {
		return 0.4D;
	}

	@Override
	public float getInnerLife() {
		return 300;
	}

	@Override
	public float getEatDamage() {
		return ZombieUtil.NORMAL_DAMAGE;
	}

	@Override
	public float getWalkSpeed() {
		return ZombieUtil.WALK_HUGE_FAST;
	}

	@Override
	public MetalTypes getMetalType() {
		return MetalTypes.GIGA_HELMET;
	}

	@Override
	public boolean canBeCold() {
		return ! this.isRushing();
	}
	
	@Override
	public boolean canBeButtered() {
		return ! this.isRushing();
	}
	
	public boolean isRushing() {
		return this.isRushing;
	}
	
	@Override
	public float getLife() {
		return 100;
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("is_zombie_rushing")) {
			this.isRushing = compound.getBoolean("is_zombie_rushing");
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("is_zombie_rushing", this.isRushing);
	}
	
	@Override
	protected ResourceLocation getDefaultLootTable() {
		return PVZLoot.GIGA_FOOTBALL_ZOMBIE;
	}
	
	@Override
	public ZombieType getZombieType() {
		return GrassZombies.GIGA_FOOTBALL_ZOMBIE;
	}

}
