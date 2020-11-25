package com.hungteen.pvz.entity.zombie.grassnight;

import java.util.List;

import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public class GigaFootballZombieEntity extends FootballZombieEntity{

	public boolean hasChanged = false;
	private final int minRushCD = 200;
	private final int maxRushCD = 600;
	
	public GigaFootballZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ZombieUtil.NORMAL_DAMAGE);
	}
	
	@Override
	public void normalZombieTick() {
		super.normalZombieTick();
		if(!this.world.isRemote) {
			if(!this.hasChanged && this.getAttackTime() == 0) {
				this.updateRush(true);
				this.hasChanged = true;
			}
			if(this.getAttackTime() > 0) {
				this.setAttackTime(this.getAttackTime() - 1);
			}
		}
	}
	
	@Override
	protected boolean shouldCollideWithEntity(LivingEntity target) {
		return EntityUtil.checkCanEntityAttack(this, target);
	}
	
	@Override
	protected void collideWithNearbyEntities() {
		double dd = this.getCollideWidthOffset();
		List<LivingEntity> list = this.world.getEntitiesWithinAABB(LivingEntity.class, this.getBoundingBox().grow(dd, 0, dd));
		if (!list.isEmpty()) {
			int i = this.world.getGameRules().getInt(GameRules.MAX_ENTITY_CRAMMING);
			if (i > 0 && list.size() > i - 1 && this.rand.nextInt(4) == 0) {
				int j = 0;
				for (int k = 0; k < list.size(); ++k) {
					if (!((Entity) list.get(k)).isPassenger()) {
						++j;
					}
				}
				if (j > i - 1) {
					this.attackEntityFrom(DamageSource.CRAMMING, 6.0F);
				}
			}
			for (int l = 0; l < list.size(); ++l) {
				LivingEntity target = list.get(l);
				if (target != this && this.shouldCollideWithEntity(target)) {// can collide with
					if(this.getAttackTime() == 0) {
						target.attackEntityFrom(PVZDamageSource.causeNormalDamage(this, this), ZombieUtil.SUPER_HIGH);
						this.updateRushCD();
					}
					this.collideWithEntity(target);
				}
			}
		}
	}
	
	protected void updateRushCD() {
		this.hasChanged = false;
		this.updateRush(false);
		this.setAttackTime(this.getRNG().nextInt(this.maxRushCD - this.minRushCD + 1) + this.minRushCD);
	}
	
	@Override
	public boolean canBeCold() {
		return this.getAttackTime() > 0;
	}
	
	@Override
	public boolean checkCanZombieTarget(LivingEntity target) {
		return EntityUtil.checkCanEntityAttack(this, target);
	}
	
	@Override
	public float getLife() {
		return 400;
	}
	
	protected void updateRush(boolean is) {
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(is ? ZombieUtil.HUGE_FAST : ZombieUtil.LITTLE_FAST);
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.hasChanged = compound.getBoolean("has_changed_speed");
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putBoolean("has_changed_speed", this.hasChanged);
	}
	
	@Override
	protected ResourceLocation getLootTable() {
		return PVZLoot.GIGA_FOOTBALL_ZOMBIE;
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.GIGA_FOOTBALL_ZOMBIE;
	}

}
