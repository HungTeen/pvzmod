package com.hungteen.pvz.entity.zombie;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Nullable;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.entity.ai.PVZLookRandomlyGoal;
import com.hungteen.pvz.entity.ai.PVZMeleeAttackGoal;
import com.hungteen.pvz.entity.ai.ZombieNearestTargetGoal;
import com.hungteen.pvz.entity.drop.CoinEntity;
import com.hungteen.pvz.entity.drop.EnergyEntity;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.entity.plant.enforce.SquashEntity;
import com.hungteen.pvz.entity.plant.spear.SpikeWeedEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.misc.damage.PVZDamageType;
import com.hungteen.pvz.misc.loot.PVZLoot;
import com.hungteen.pvz.register.EffectRegister;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Ranks;
import com.hungteen.pvz.utils.interfaces.IPVZZombie;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.GameRules;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

public abstract class PVZZombieEntity extends MonsterEntity implements IPVZZombie {

	private static final DataParameter<Integer> ZOMBIE_TYPE = EntityDataManager.createKey(PVZZombieEntity.class,
			DataSerializers.VARINT);
	private static final DataParameter<Optional<UUID>> OWNER_UUID = EntityDataManager.createKey(PVZZombieEntity.class,
			DataSerializers.OPTIONAL_UNIQUE_ID);
//	private static final DataParameter<Boolean> IS_COLD = EntityDataManager.createKey(PVZZombieEntity.class,
//			DataSerializers.BOOLEAN);
//	private static final DataParameter<Boolean> IS_FROZEN = EntityDataManager.createKey(PVZZombieEntity.class,
//			DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IS_CHARMED = EntityDataManager.createKey(PVZZombieEntity.class,
			DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IS_SMALL = EntityDataManager.createKey(PVZZombieEntity.class,
			DataSerializers.BOOLEAN);
//	private static final DataParameter<Boolean> IS_INVIS = EntityDataManager.createKey(PVZZombieEntity.class,
//			DataSerializers.BOOLEAN);
//	private static final DataParameter<Boolean> IS_BUTTER = EntityDataManager.createKey(PVZZombieEntity.class,
//			DataSerializers.BOOLEAN);

	public PVZZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.setZombieAttributes();
	}

	/**
	 * get zombie type : super normal beard
	 */
	protected Type getSpawnType() {
		int t = this.getRNG().nextInt(100);
		if (t <= PVZConfig.COMMON_CONFIG.EntitySettings.ZombieSuperChance.get())
			return Type.SUPER;
		return Type.NORMAL;
	}

	protected void setZombieAttributes() {
		this.setZombieMaxHealth(this.getLife());
	}

	@Override
	protected void registerData() {
		super.registerData();
		dataManager.register(ZOMBIE_TYPE, this.getSpawnType().ordinal());
		dataManager.register(OWNER_UUID, Optional.empty());
//		dataManager.register(IS_FROZEN, false);
//		dataManager.register(IS_COLD, false);
		dataManager.register(IS_CHARMED, false);
		dataManager.register(IS_SMALL, false);
//		dataManager.register(IS_INVIS, false);
//		dataManager.register(IS_BUTTER, false);
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ZombieUtil.LOW);
		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(ZombieUtil.ZOMBIE_FOLLOW_RANGE);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.NORMAL_SPEED);
		this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1D);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(8, new PVZLookRandomlyGoal(this));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(7, new SwimGoal(this));
		this.goalSelector.addGoal(0, new PVZMeleeAttackGoal(this));
		this.targetSelector.addGoal(0, new ZombieNearestTargetGoal(this, true, 80, 60));
//		this.goalSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, WallNutEntity.class, true));
	}

	@Override
	public void livingTick() {
		super.livingTick();
		if (!this.isAlive() || this.canZombieNormalUpdate()) {
			return;
		}
		this.normalZombieTick();
	}

	public void normalZombieTick() {

	}

	public void setZombieMaxHealth(float health) {
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(health);
		this.heal(health);
	}

	public int getAttackCD() {
		if (!this.canZombieNormalUpdate()) {
			return 10000000;
		}
		int now = 20;
		if (this.isPotionActive(EffectRegister.COLD_EFFECT.get())) {
			int lvl = this.getActivePotionEffect(EffectRegister.COLD_EFFECT.get()).getAmplifier();
			now += 3 * lvl;
		}
		return now;
	}

	@Override
	public EntitySize getSize(Pose poseIn) {
		return new EntitySize(0.8f, 1.98f, false);
	}
	
	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);
	}

	@Override
	protected void onDeathUpdate() {
		++this.deathTime;
		if (this.deathTime == 20) {
			this.remove();
			for (int i = 0; i < 20; ++i) {
				double d0 = this.rand.nextGaussian() * 0.02D;
				double d1 = this.rand.nextGaussian() * 0.02D;
				double d2 = this.rand.nextGaussian() * 0.02D;
				this.world.addParticle(ParticleTypes.POOF, this.getPosXRandom(1.0D), this.getPosYRandom(),
						this.getPosZRandom(1.0D), d0, d1, d2);
			}
			this.onZombieRemove();
		}
	}

	/**
	 * the last tick of zombies.
	 * for drop item and coin.
	 */
	protected void onZombieRemove() {
		if (!world.isRemote) {
			if (getZombieType() == Type.SUPER) {//drop energy
				this.dropEnergy();
			} else if (getZombieType() == Type.BEARD) {// finish achievement
			}
			this.dropCoin();
			this.zombieDropItem();
		}
	}

	/**
	 * super mode type zombie can drop energy after death
	 */
	protected void dropEnergy() {
		EnergyEntity energy = EntityRegister.ENERGY.get().create(world);
		EntityUtil.onMobEntitySpawn(world, energy, this.getPosition().up());
	}
	
	/**
	 * zombies have chance to drop coin when died.
	 */
	protected void dropCoin() {
		int num = this.getRNG().nextInt(10000);
		int amount = 0;
		if (num < 1000) {
			amount = 1;
		}else if (num < 1100) {
			amount = 10;
		}else if (num < 1110) {
			amount = 100;
		}else if(num < 1111) {
			amount = 1000;
		}
		if (amount != 0) {
			CoinEntity coin = EntityRegister.COIN.get().create(world);
			coin.setAmount(amount);
			EntityUtil.onMobEntitySpawn(world, coin, getPosition());
		}
	}

	/**
	 * zombie loottable is different from default mc. only in server side
	 */
	protected void zombieDropItem() {
	}
	
	/**
	 * can zombie set target as attackTarget
	 */
	public boolean checkCanZombieTarget(LivingEntity target) {
		if(target instanceof SpikeWeedEntity) {
			return false;
		}
		return EntityUtil.checkCanEntityAttack(this, target);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (source instanceof PVZDamageSource) {
			this.hurtResistantTime = 0;
			if (((PVZDamageSource) source).getPVZDamageType() == PVZDamageType.ICE && !((PVZDamageSource) source).isDefended()) {
				if (!this.isZombieColdOrForzen() && !this.world.isRemote) {
					this.playSound(SoundRegister.ZOMBIE_FROZEN.get(), 1f, 1f);
				}
			} else if (((PVZDamageSource) source).getPVZDamageType() == PVZDamageType.FIRE && !((PVZDamageSource) source).isDefended()) {
				if (this.isZombieColdOrForzen() && !this.world.isRemote) {
					this.playSound(SoundRegister.ZOMBIE_FIRE.get(), 1f, 1f);
					this.removePotionEffect(EffectRegister.COLD_EFFECT.get());
					this.removeActivePotionEffect(EffectRegister.FROZEN_EFFECT.get());
				}
			}
		}
		return super.attackEntityFrom(source, amount);
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		entityIn.hurtResistantTime = 0;
		// add
		float f = (float) this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue();
		float f1 = (float) this.getAttribute(SharedMonsterAttributes.ATTACK_KNOCKBACK).getValue();
		if (entityIn instanceof LivingEntity) {
			f += EnchantmentHelper.getModifierForCreature(this.getHeldItemMainhand(),
					((LivingEntity) entityIn).getCreatureAttribute());
			f1 += (float) EnchantmentHelper.getKnockbackModifier(this);
		}

		int i = EnchantmentHelper.getFireAspectModifier(this);
		if (i > 0) {
			entityIn.setFire(i * 4);
		}

		boolean flag = entityIn.attackEntityFrom(getZombieAttackDamageSource(), getModifyAttackDamage(entityIn, f));
		if (flag) {
			if (f1 > 0.0F && entityIn instanceof LivingEntity) {
				((LivingEntity) entityIn).knockBack(this, f1 * 0.5F,
						(double) MathHelper.sin(this.rotationYaw * ((float) Math.PI / 180F)),
						(double) (-MathHelper.cos(this.rotationYaw * ((float) Math.PI / 180F))));
				this.setMotion(this.getMotion().mul(0.6D, 1.0D, 0.6D));
			}

			if (entityIn instanceof PlayerEntity) {
				PlayerEntity playerentity = (PlayerEntity) entityIn;
				ItemStack itemstack = this.getHeldItemMainhand();
				ItemStack itemstack1 = playerentity.isHandActive() ? playerentity.getActiveItemStack()
						: ItemStack.EMPTY;
				if (!itemstack.isEmpty() && !itemstack1.isEmpty()
						&& itemstack.canDisableShield(itemstack1, playerentity, this)
						&& itemstack1.isShield(playerentity)) {
					float f2 = 0.25F + (float) EnchantmentHelper.getEfficiencyModifier(this) * 0.05F;
					if (this.rand.nextFloat() < f2) {
						playerentity.getCooldownTracker().setCooldown(itemstack.getItem(), 100);
						this.world.setEntityState(playerentity, (byte) 30);
					}
				}
			}

			this.applyEnchantments(this, entityIn);
			this.setLastAttackedEntity(entityIn);
		}
		return flag;
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	public static boolean canZombieSpawn(EntityType<? extends PVZZombieEntity> zombieType, IWorld worldIn,
			SpawnReason reason, BlockPos pos, Random rand) {
		return worldIn.getLightFor(LightType.BLOCK, pos) > 8 ? false
				: canMonsterSpawn(zombieType, worldIn, reason, pos, rand);
	}

	@Override
	public float getBlockPathWeight(BlockPos pos, IWorldReader worldIn) {
		return 9 - worldIn.getLightFor(LightType.BLOCK, pos);
	}

	protected PVZDamageSource getZombieAttackDamageSource() {
		return PVZDamageSource.causeEatDamage(this, this);
	}

	protected float getModifyAttackDamage(Entity entity, float f) {
		return f;
	}

	public Ranks getZombieRank() {
		return ZombieUtil.getZombieRank(getZombieEnumName());
	}

	@Override
	public int getZombieXp() {
		return ZombieUtil.getZombieXp(getZombieEnumName());
	}

	@Override
	public void applyEntityCollision(Entity entityIn) {
		if (this.isSleeping()) {
			return;
		}
		if (!this.isRidingSameEntity(entityIn)) {
			if (!entityIn.noClip && !this.noClip) {
				double d0 = entityIn.getPosX() - this.getPosX();
				double d1 = entityIn.getPosZ() - this.getPosZ();
				double d2 = MathHelper.absMax(d0, d1);
				if (d2 >= 0.009999999776482582D) {// collide from out to in,add velocity to out
					d2 = (double) MathHelper.sqrt(d2);
					d0 = d0 / d2;
					d1 = d1 / d2;
					double d3 = 1.0D / d2;
					if (d3 > 1.0D) {
						d3 = 1.0D;
					}
					d0 = d0 * d3;
					d1 = d1 * d3;
					d0 = d0 * 0.05000000074505806D;
					d1 = d1 * 0.05000000074505806D;
					d0 = d0 * (double) (1.0F - this.entityCollisionReduction);
					d1 = d1 * (double) (1.0F - this.entityCollisionReduction);
					if (!this.isBeingRidden()) {
						this.addVelocity(-d0, 0.0D, -d1);
					}
					if (!entityIn.isBeingRidden()) {
						if (!(entityIn instanceof PVZPlantEntity)) {
							entityIn.addVelocity(d0, 0.0D, d1);
						}
					}
				}
			}
		}
	}

	@Override
	protected void collideWithNearbyEntities() {
		List<Entity> list = this.world.getEntitiesWithinAABB(Entity.class, this.getBoundingBox());
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
				Entity target = list.get(l);
				if (this.shouldCollideWithEntity(target)) {// can collide with
					this.collideWithEntity(target);
				}
			}
		}
	}
	
	/**
	 * can zombie collide with target
	 */
	protected boolean shouldCollideWithEntity(Entity target) {
		if(target instanceof PVZPlantEntity) {
			if(target instanceof SquashEntity||target instanceof SpikeWeedEntity) {
			    return false;
		    }
			return EntityUtil.checkCanEntityAttack(this, target);
		}
		return EntityUtil.checkCanEntityAttack(this, target);
	}
	
	/**
	 * can zombie push target
	 */
	protected boolean checkCanPushEntity(Entity target) {
		return !(target instanceof PVZPlantEntity);
	}

	@Override
	protected float getWaterSlowDown() {
		return 0.75f;
	}
	
	@Override
	public boolean canAttackSpike() {
		return false;
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("zombie_type", this.getZombieType().ordinal());
		if (this.getOwnerUUID() == null) {
			compound.putString("OwnerUUID", "");
		} else {
			compound.putString("OwnerUUID", this.getOwnerUUID().toString());
		}
//		compound.putBoolean("is_zombie_cold", this.getIsCold());
//		compound.putBoolean("is_zombie_frozen", this.getIsFrozen());
//		compound.putBoolean("is_zombie_butter", this.getIsButter());
		compound.putBoolean("is_zombie_small", this.isSmall());
//		compound.putBoolean("is_zombie_invis", this.getIsInivs());
		compound.putBoolean("is_zombie_charmed", this.isCharmed());
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.setZombieType(Type.values()[compound.getInt("zombie_type")]);
		String s;
		if (compound.contains("OwnerUUID", 8)) {
			s = compound.getString("OwnerUUID");
		} else {
			String s1 = compound.getString("Owner");
			s = PreYggdrasilConverter.convertMobOwnerIfNeeded(this.getServer(), s1);
		}
		if (!s.isEmpty()) {
			try {
				this.setOwnerUUID(UUID.fromString(s));
			} catch (Throwable var4) {
			}
		}
//		this.setIsCold(compound.getBoolean("is_zombie_cold"));
//		this.setIsFrozen(compound.getBoolean("is_zombie_frozen"));
//		this.setIsButter(compound.getBoolean("is_zombie_butter"));
		this.setSmall(compound.getBoolean("is_zombie_small"));
//		this.setIsInvis(compound.getBoolean("is_zombie_invis"));
		this.setCharmed(compound.getBoolean("is_zombie_charmed"));
	}

	@Override
	public boolean canBeLeashedTo(PlayerEntity player) {
		return false;
	}
	
	/**
	 * check can zombie add effect
	 */
	public void checkAndAddPotionEffect(EffectInstance effect) {
		if(effect.getPotion() == EffectRegister.COLD_EFFECT.get() && !this.canBeCold()) {
			return;
		}
		if(effect.getPotion() == EffectRegister.FROZEN_EFFECT.get() && !this.canBeFrozen()) {
			return;
		}
		this.addPotionEffect(effect);
	}
	
	/**
	 * when zombie turn to oppsite charm state
	 * charm -> uncharm
	 * uncharm -> charm
	 */
	public void onCharmed() {
		if(this.canBeCharmed()) {
			this.setCharmed(!this.isCharmed());
			if(this.getZombieType() == Type.SUPER) {
				this.setZombieType(Type.NORMAL);
				this.dropEnergy();
			}
		}
	}
	
	public boolean canBeButter() {
		return true;
	}
	
	public boolean canBeCharmed() {
		return true;
	}

	public boolean canBeFrozen() {
		return !this.isInWater();
	}

	public boolean canBeSmall() {
		return true;
	}

	public boolean canBeInvis() {
		return true;
	}

	public boolean canBeCold() {
		return true;
	}

	@Nullable
	public UUID getOwnerUUID() {
		return dataManager.get(OWNER_UUID).orElse((UUID) null);
	}

	public void setOwnerUUID(UUID uuid) {
		this.dataManager.set(OWNER_UUID, Optional.ofNullable(uuid));
	}

	public void setCharmed(boolean is) {
		dataManager.set(IS_CHARMED, is);
	}

//	public void setIsInvis(boolean is) {
//		dataManager.set(IS_INVIS, is);
//	}

	public void setSmall(boolean is) {
		dataManager.set(IS_SMALL, is);
	}

//	public void setIsCold(boolean is) {
//		dataManager.set(IS_COLD, is);
//	}
//
//	public void setIsFrozen(boolean is) {
//		dataManager.set(IS_FROZEN, is);
//	}
//
//	public void setIsButter(boolean is) {
//		dataManager.set(IS_BUTTER, is);
//	}

	public void setZombieType(Type type) {
		dataManager.set(ZOMBIE_TYPE, type.ordinal());
	}

	public boolean isCharmed() {
		return dataManager.get(IS_CHARMED);
	}

//	public boolean getIsInivs() {
//		return dataManager.get(IS_INVIS);
//	}

	public boolean isSmall() {
		return dataManager.get(IS_SMALL);
	}

//	public boolean getIsButter() {
//		return dataManager.get(IS_BUTTER);
//	}
//
//	public boolean getIsCold() {
//		return dataManager.get(IS_COLD);
//	}
//
//	public boolean getIsFrozen() {
//		return dataManager.get(IS_FROZEN);
//	}

	public boolean isZombieColdOrForzen() {
		return EntityUtil.isEntityCold(this) || EntityUtil.isEntityFrozen(this);
	}

	public boolean canZombieNormalUpdate() {
		return !EntityUtil.isEntityFrozen(this);
	}

	public Type getZombieType() {
		return Type.values()[dataManager.get(ZOMBIE_TYPE)];
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundRegister.ZOMBIE_SAY.get();
	}

	@Override
	protected ResourceLocation getLootTable() {
		return PVZLoot.NORMAL_ZOMBIE;
	}

	public enum Type {
		NORMAL, SUPER, BEARD
	}
}
