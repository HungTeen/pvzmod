package com.hungteen.pvz.entity.zombie;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Nullable;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.entity.ai.BreakBlockGoal;
import com.hungteen.pvz.entity.ai.PVZLookRandomlyGoal;
import com.hungteen.pvz.entity.ai.PVZSwimGoal;
import com.hungteen.pvz.entity.ai.attack.PVZZombieAttackGoal;
import com.hungteen.pvz.entity.ai.target.ZombieNearestTargetGoal;
import com.hungteen.pvz.entity.bullet.AbstractBulletEntity;
import com.hungteen.pvz.entity.drop.CoinEntity;
import com.hungteen.pvz.entity.drop.CoinEntity.CoinType;
import com.hungteen.pvz.entity.drop.EnergyEntity;
import com.hungteen.pvz.entity.drop.JewelEntity;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.entity.plant.enforce.SquashEntity;
import com.hungteen.pvz.entity.plant.spear.SpikeWeedEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.misc.damage.PVZDamageType;
import com.hungteen.pvz.register.BlockRegister;
import com.hungteen.pvz.register.EffectRegister;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.ItemRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Ranks;
import com.hungteen.pvz.utils.interfaces.IPVZZombie;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.item.ItemEntity;
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
import net.minecraft.world.DifficultyInstance;
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
	private static final DataParameter<Boolean> IS_CHARMED = EntityDataManager.createKey(PVZZombieEntity.class,
			DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IS_SMALL = EntityDataManager.createKey(PVZZombieEntity.class,
			DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> ATTACK_TIME = EntityDataManager.createKey(PVZZombieEntity.class, DataSerializers.VARINT);
	private static final DataParameter<Float> DEFENCE_LIFE = EntityDataManager.createKey(PVZZombieEntity.class, DataSerializers.FLOAT);
	protected boolean hasDirectDefence = false;
	protected boolean canSpawnDrop = true;
	protected boolean canBeCold = true;
	protected boolean canBeFrozen = true;
	protected boolean canBeCharm = true;
	protected boolean canBeButter = true;
	protected boolean canBeMini = true;
	protected boolean canBeInvis = true;
	
	public PVZZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.recalculateSize();
		this.setZombieAttributes();
		this.experienceValue = this.getZombieRank().ordinal() * 2 + 5;
	}

	@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		if(! world.isRemote) {
			this.setZombieType(this.getSpawnType());
			EntityUtil.playSound(this, getSpawnSound());
		}
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}
	
	/**
	 * get zombie type : super normal beard
	 */
	protected Type getSpawnType() {
		int t = this.getRNG().nextInt(100);
		if (t <= PVZConfig.COMMON_CONFIG.EntitySettings.ZombieSuperChance.get()) {
			return Type.SUPER;
		}
		return Type.NORMAL;
	}

	protected void setZombieAttributes() {
		this.setZombieMaxHealth(this.getLife());
	}

	@Override
	protected void registerData() {
		super.registerData();
		dataManager.register(ZOMBIE_TYPE, Type.NORMAL.ordinal());
		dataManager.register(OWNER_UUID, Optional.empty());
		dataManager.register(IS_CHARMED, false);
		dataManager.register(IS_SMALL, false);
		dataManager.register(ATTACK_TIME, 0);
		dataManager.register(DEFENCE_LIFE, 0f);
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ZombieUtil.LOW);
		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(30);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.NORMAL_SPEED);
		this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1D);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(8, new PVZLookRandomlyGoal(this));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(7, new PVZSwimGoal(this));
		this.goalSelector.addGoal(2, new PVZZombieAttackGoal(this, true));
		this.goalSelector.addGoal(6, new BreakBlockGoal(BlockRegister.FLOWER_POT.get(), this, 1F, 10));
		this.targetSelector.addGoal(0, new ZombieNearestTargetGoal(this, true, 60, 30));
	}

	@Override
	public void livingTick() {
		super.livingTick();
		if (!this.isAlive() || !this.canZombieNormalUpdate()) {
			return;
		}
//		if(this.ticksExisted % 40 == 0) {
//			System.out.println(this.getDefenceLife());
//		}
		this.normalZombieTick();
	}

	public void normalZombieTick() {
	}

	public void setZombieMaxHealth(float health) {
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(health);
		this.heal(health);
	}
	
	public float getCurrentHealth() {
		if(this.hasDirectDefence) return this.getDefenceLife() + this.getHealth();
		return this.getHealth();
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
		if(cause instanceof PVZDamageSource && ((PVZDamageSource)cause).getPVZDamageType() == PVZDamageType.BOWLING && ((PVZDamageSource)cause).getDamageCount() > 0) {
			this.canSpawnDrop = false;
		}
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
		if (! world.isRemote) {
			if (getZombieType() == Type.SUPER) {//drop energy
				this.dropEnergy();
			} else if (getZombieType() == Type.BEARD) {// finish achievement
			}
			if(this.canSpawnDrop) {
				this.dropCoinOrSpecial();
			}
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
	protected void dropCoinOrSpecial() {
		int num = this.getRNG().nextInt(10000);
		if(num < 1110) {
			int amount = CoinType.GOLD.money;
			if(num < 1000) amount = CoinType.COPPER.money;
			else if(num < 1100) amount = CoinType.SILVER.money;
			CoinEntity coin = EntityRegister.COIN.get().create(world);
			coin.setAmount(amount);
			EntityUtil.onMobEntitySpawn(world, coin, getPosition());
			return ;
		}
		if(num == 1110) {
			JewelEntity jewel = EntityRegister.JEWEL.get().create(world);
			EntityUtil.onMobEntitySpawn(world, jewel, getPosition());
			return ;
		}
		if(num == 1111){
			ItemEntity chocolate = new ItemEntity(world, getPosX(), getPosY(), getPosZ(), new ItemStack(ItemRegister.CHOCOLATE.get()));
			EntityUtil.playSound(chocolate, SoundRegister.JEWEL_DROP.get());
			world.addEntity(chocolate);
			return ;
		}
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

	public boolean checkCanZombieBreakBlock() {
		return ! this.isCharmed();
	}
	
	public boolean canZombieBeRemoved() {
		return true;
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if(! world.isRemote && this.hasDirectDefence) {
			if(this.getDefenceLife() > 0) {
				if(this.getDefenceLife() > amount) {
				    this.setDefenceLife(this.getDefenceLife() - amount);
				    amount = 0;
			    } else {
				    amount -= this.getDefenceLife();
				    this.setDefenceLife(0);
			    }
				EntityUtil.playSound(this, getHurtSound(source));
				if(amount == 0) {
					amount = 0.001F;
				}
			}
		}
		boolean flag = super.attackEntityFrom(source, amount);
		if (source instanceof PVZDamageSource) {
			this.hurtResistantTime = 0;
			if (this.canBeFrozen() && ((PVZDamageSource) source).getPVZDamageType() == PVZDamageType.ICE && !((PVZDamageSource) source).isDefended()) {
				if (! this.isZombieColdOrForzen() && ! this.world.isRemote) {
					this.playSound(SoundRegister.ZOMBIE_FROZEN.get(), 1f, 1f);
				}
			} else if (((PVZDamageSource) source).getPVZDamageType() == PVZDamageType.FIRE && !((PVZDamageSource) source).isDefended()) {
				if (this.isZombieColdOrForzen() && ! this.world.isRemote) {
					this.playSound(SoundRegister.ZOMBIE_FIRE.get(), 1f, 1f);
					this.removePotionEffect(EffectRegister.COLD_EFFECT.get());
					this.removeActivePotionEffect(EffectRegister.FROZEN_EFFECT.get());
				}
			}
		}
		return flag;
	}
	
	protected void dealDamageEffectToZombie(PVZDamageSource source) {
		if(source.isDefended()) {
			return ;
		}
		for(EffectInstance effect : source.getEffects()) {
			EntityUtil.addEntityPotionEffect(this, effect);
		}
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
					this.collideWithEntity(target);
				}
			}
		}
	}
	
	protected double getCollideWidthOffset() {
		return 0f;
	}
	
	/**
	 * can zombie collide with target
	 */
	protected boolean shouldCollideWithEntity(LivingEntity target) {
		if(this.getAttackTarget() == target) {
			if(target instanceof SquashEntity || target instanceof SpikeWeedEntity) {
			    return false;
		    }
			return true;
		}
		if(target instanceof PVZZombieEntity) {
			return true;
		}
		return false;
	}
	
	/**
	 * can zombie push target
	 */
	protected boolean checkCanPushEntity(Entity target) {
		return !(target instanceof PVZPlantEntity);
	}

	@Override
	protected float getWaterSlowDown() {
		return 0.85f;
	}
	
	@Override
	public boolean canAttackSpike() {
		return false;
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("zombie_type", this.getZombieType().ordinal());
		if (this.getOwnerUUID().isPresent()) {
			compound.putString("OwnerUUID", this.getOwnerUUID().get().toString());
		} else {
			compound.putString("OwnerUUID", "");
		}
		compound.putBoolean("is_zombie_small", this.isSmall());
		compound.putBoolean("is_zombie_charmed", this.isCharmed());
		compound.putInt("zombie_attack_time", this.getAttackTime());
		compound.putFloat("defence_life", this.getDefenceLife());
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		if(compound.contains("zombie_type")) {
			this.setZombieType(Type.values()[compound.getInt("zombie_type")]);
		}
		//owner UUID
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
		if(compound.contains("is_zombie_small")) {
			this.setSmall(compound.getBoolean("is_zombie_small"));
		}
		if(compound.contains("is_zombie_charmed")) {
			this.setCharmed(compound.getBoolean("is_zombie_charmed"));
		}
		if(compound.contains("zombie_attack_time")) {
			this.setAttackTime(compound.getInt("zombie_attack_time"));
		}if(compound.contains("defence_life")) {
			this.setDefenceLife(compound.getFloat("defence_life"));
		}
	}

	@Override
	public boolean canBeLeashedTo(PlayerEntity player) {
		return false;
	}
	
	/**
	 * check can zombie add effect
	 */
	public void checkAndAddPotionEffect(EffectInstance effect) {
		if(effect.getPotion() == EffectRegister.COLD_EFFECT.get() && ! this.canBeCold()) return;
		if(effect.getPotion() == EffectRegister.FROZEN_EFFECT.get() && ! this.canBeFrozen()) return;
		if(effect.getPotion() == EffectRegister.BUTTER_EFFECT.get() && ! this.canBeButter()) return ;
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
		return this.canBeButter;
	}
	
	public boolean canBeCharmed() {
		return this.canBeCharm;
	}

	public boolean canBeFrozen() {
		return this.canBeFrozen && ! this.isInWater() && ! this.isInLava();
	}

	public boolean canBeMini() {
		return this.canBeMini;
	}

	public boolean canBeInvis() {
		return this.canBeInvis;
	}

	public boolean canBeCold() {
		return this.canBeCold;
	}
	
	@Nullable
	protected SoundEvent getSpawnSound() {
		return null;
	}
	
	public float getDefenceLife() {
		return dataManager.get(DEFENCE_LIFE);
	}
	
	public void setDefenceLife(float life) {
		dataManager.set(DEFENCE_LIFE, life);
	}
	
	public Optional<UUID> getOwnerUUID() {
		return dataManager.get(OWNER_UUID);
	}

	public void setOwnerUUID(UUID uuid) {
		this.dataManager.set(OWNER_UUID, Optional.ofNullable(uuid));
	}

	public void setCharmed(boolean is) {
		dataManager.set(IS_CHARMED, is);
	}

	public void setSmall(boolean is) {
		dataManager.set(IS_SMALL, is);
	}

	public void setZombieType(Type type) {
		dataManager.set(ZOMBIE_TYPE, type.ordinal());
	}

	public boolean isCharmed() {
		return dataManager.get(IS_CHARMED);
	}

	public boolean isSmall() {
		return dataManager.get(IS_SMALL);
	}
	
	public int getAttackTime(){
    	return dataManager.get(ATTACK_TIME);
    }
    
    public void setAttackTime(int cd){
    	dataManager.set(ATTACK_TIME, cd);
    }

	public boolean isZombieColdOrForzen() {
		return EntityUtil.isEntityCold(this) || EntityUtil.isEntityFrozen(this);
	}

	public boolean canZombieNormalUpdate() {
		return ! EntityUtil.isEntityFrozen(this) && ! EntityUtil.isEntityButter(this);
	}

	public Type getZombieType() {
		return Type.values()[dataManager.get(ZOMBIE_TYPE)];
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundRegister.ZOMBIE_SAY.get();
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		if(damageSourceIn.getImmediateSource() instanceof AbstractBulletEntity) {
			return SoundRegister.PEA_HIT.get();
		}
		return super.getHurtSound(damageSourceIn);
	}

	@Override
	protected ResourceLocation getLootTable() {
		return PVZLoot.NORMAL_ZOMBIE;
	}

	public enum Type {
		NORMAL, SUPER, BEARD
	}
}
