package com.hungteen.pvz.entity.zombie;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Nullable;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.advancement.trigger.CharmZombieTrigger;
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
import com.hungteen.pvz.entity.drop.SunEntity;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.entity.plant.enforce.SquashEntity;
import com.hungteen.pvz.entity.plant.spear.SpikeRockEntity;
import com.hungteen.pvz.entity.plant.spear.SpikeWeedEntity;
import com.hungteen.pvz.entity.zombie.roof.BungeeZombieEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.misc.damage.PVZDamageType;
import com.hungteen.pvz.register.BlockRegister;
import com.hungteen.pvz.register.EffectRegister;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.ItemRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.AlgorithmUtil;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.InvasionEvents;
import com.hungteen.pvz.utils.enums.Ranks;
import com.hungteen.pvz.utils.enums.Zombies;
import com.hungteen.pvz.utils.interfaces.IPVZZombie;
import com.hungteen.pvz.utils.others.WeightList;
import com.hungteen.pvz.world.data.PVZInvasionData;
import com.hungteen.pvz.world.invasion.OverworldInvasion;
import com.mojang.datafixers.util.Pair;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.GameRules;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

public abstract class PVZZombieEntity extends MonsterEntity implements IPVZZombie {

	private static final DataParameter<Integer> ZOMBIE_TYPE = EntityDataManager.defineId(PVZZombieEntity.class,
			DataSerializers.INT);
	private static final DataParameter<Optional<UUID>> OWNER_UUID = EntityDataManager.defineId(PVZZombieEntity.class,
			DataSerializers.OPTIONAL_UUID);
	private static final DataParameter<Integer> ZOMBIE_STATES = EntityDataManager.defineId(PVZZombieEntity.class,
			DataSerializers.INT);
	private static final DataParameter<Integer> ATTACK_TIME = EntityDataManager.defineId(PVZZombieEntity.class,
			DataSerializers.INT);
	private static final DataParameter<Integer> ANIM_TIME = EntityDataManager.defineId(PVZZombieEntity.class,
			DataSerializers.INT);
	private static final DataParameter<Float> DEFENCE_LIFE = EntityDataManager.defineId(PVZZombieEntity.class,
			DataSerializers.FLOAT);
	private static final DataParameter<Integer> ZOMBIE_LEVEL = EntityDataManager.defineId(PVZZombieEntity.class,
			DataSerializers.INT);
	protected WeightList<DropType> dropSpecialList;
	private static final int CHARM_FLAG = 0;
	private static final int MINI_FLAG = 1;
	public static final int PERFORM_ATTACK_CD = 10;
	public boolean canCollideWithZombie = true;
	protected boolean hasDirectDefence = false;
	protected boolean canSpawnDrop = true;
	protected boolean canBeCold = true;
	protected boolean canBeFrozen = true;
	protected boolean canBeCharm = true;
	protected boolean canBeButter = true;
	protected boolean canBeMini = true;
	protected boolean canBeInvis = true;
	protected boolean canBeStealByBungee = true;
	protected int maxDeathTime = 20;

	public PVZZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		//refresh size.
		this.refreshDimensions();
		//caculate how mush xp will zombie drop.
		this.xpReward = ZombieUtil.caculateZombieXp(this);
		//init drop coin list
		this.dropSpecialList = this.getDropSpecialList();
	}

	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		if (!level.isClientSide) {
			//decide the type of zombie, common or super or beard or sun.
			this.setZombieType(this.getSpawnType());
			EntityUtil.playSound(this, getSpawnSound());
			//update its attributes like max health.
			this.updateAttributes();
			//check zombie state, must after method updateAttributes()
			if (level.dimension() == World.OVERWORLD) {
				PVZInvasionData data = PVZInvasionData.getOverWorldInvasionData(level);
				if (this.canBeMini() && data.hasEvent(InvasionEvents.MINI)) {
					this.onZombieBeMini();
				}
				if (this.canBeInvis() && data.hasEvent(InvasionEvents.INVIS)) {
					this.addEffect(new EffectInstance(Effects.INVISIBILITY, 1000000, 10, false, false));
				}
			}
		}
		return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	/**
	 * get zombie type : super or normal or beard or sun
	 */
	protected Type getSpawnType() {
		int t = this.getRandom().nextInt(100);
		int a = PVZConfig.COMMON_CONFIG.EntitySettings.ZombieSetting.ZombieSuperChance.get();
		int b = PVZConfig.COMMON_CONFIG.EntitySettings.ZombieSetting.ZombieSunChance.get();
		if (t < a)
			return Type.SUPER;
		if (t < a + b)
			return Type.SUN;
		return Type.NORMAL;
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		entityData.define(ZOMBIE_TYPE, Type.NORMAL.ordinal());
		entityData.define(OWNER_UUID, Optional.empty());
		entityData.define(ZOMBIE_STATES, 0);
		entityData.define(ATTACK_TIME, 0);
		entityData.define(DEFENCE_LIFE, 0f);
		entityData.define(ZOMBIE_LEVEL, 1);
		entityData.define(ANIM_TIME, 0);
	}
	
	public static AttributeModifierMap createZombieAttributes() {
		return MonsterEntity.createMonsterAttributes()
				.add(Attributes.ATTACK_DAMAGE, ZombieUtil.LOW)
	    	    .add(Attributes.MAX_HEALTH, 20)
	     	    .add(Attributes.FOLLOW_RANGE, 40.0D)
	    		.add(Attributes.KNOCKBACK_RESISTANCE, 1)
	    		.add(Attributes.MOVEMENT_SPEED, ZombieUtil.NORMAL_SPEED)
	    		.add(Attributes.FLYING_SPEED, 0)
	    		.build();
	}
	
	/**
	 * update zombie attributes when first spawn.
	 */
	protected void updateAttributes() {
		this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(ZombieUtil.LOW);
		this.getAttribute(Attributes.FOLLOW_RANGE).setBaseValue(30);
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.NORMAL_SPEED);
		this.getAttribute(Attributes.KNOCKBACK_RESISTANCE).setBaseValue(1D);
		EntityUtil.setLivingMaxHealthAndHeal(this, this.getLife());
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(8, new PVZLookRandomlyGoal(this));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(7, new PVZSwimGoal(this));
		this.goalSelector.addGoal(3, new PVZZombieAttackGoal(this, true));
		this.goalSelector.addGoal(6, new BreakBlockGoal(BlockRegister.FLOWER_POT.get(), this, 1F, 10));
		this.targetSelector.addGoal(0, new ZombieNearestTargetGoal(this, true, 60, 30));
	}

	@Override
	public void aiStep() {
		super.aiStep();
		if (!this.isAlive() || !this.canZombieNormalUpdate()) {
			return;
		}
		if (this.tickCount <= 2) {
			this.refreshDimensions();
		}
		this.normalZombieTick();
	}

	/**
	 * tick when zombie is normal state.
	 * (not be frozen or butter and so on).
	 */
	public void normalZombieTick() {
		if(! this.level.isClientSide) {
			this.setAnimTime(Math.max(0, this.getAnimTime() - 1));
		}
	}

	/**
	 * get current health of zombie(contain its defence health).
	 */
	public float getCurrentHealth() {
		return this.hasDirectDefence ? this.getDefenceLife() + this.getHealth() : this.getHealth();
	}

	/**
	 * get current max health of zombie(contain defence health).
	 */
	public float getCurrentMaxHealth() {
		return this.hasDirectDefence ? this.getDefenceLife() + this.getMaxHealth() : this.getMaxHealth();
	}

	/**
	 * run when zombie be mini state.
	 * change max health to 60%.
	 * give speed effect and damage boost.
	 */
	public void onZombieBeMini() {
		this.setMiniZombie(true);
		final float healthDec = 0.6F;
		EntityUtil.setLivingMaxHealthAndHeal(this, this.getMaxHealth() * healthDec);
		if (this.hasDirectDefence) {
			this.setDefenceLife(this.getDefenceLife() * healthDec);
		}
		this.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 1000000, 0, false, false));
		this.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 1000000, 0, false, false));
	}

	/**
	 * zombie perform attack CD.
	 */
	public int getAttackCD() {
		if (!this.canZombieNormalUpdate()) {//can not update means stop attack.
			return 10000000;
		}
		int cd = 20;
		if (this.hasEffect(EffectRegister.COLD_EFFECT.get())) {//cold will decrease attack CD.
			int lvl = this.getEffect(EffectRegister.COLD_EFFECT.get()).getAmplifier();
			cd += 3 * lvl;
		}
		return cd;
	}

	@Override
	public EntitySize getDimensions(Pose poseIn) {
		if (this.isMiniZombie())
			return EntitySize.scalable(0.3F, 0.6F);
		return new EntitySize(0.8f, 1.98f, false);
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);
		//if zombie was killed by bowling, it will not drop coin.
		if (cause instanceof PVZDamageSource && ((PVZDamageSource) cause).getPVZDamageType() == PVZDamageType.BOWLING
				&& ((PVZDamageSource) cause).getDamageCount() > 0) {
			this.canSpawnDrop = false;
		}
		if(! this.level.isClientSide) {
//			ZombieBodyEntity body = EntityRegister.ZOMBIE_BODY.get().create(level);
//			body.setPos(getX(), getY(), getZ());
//			level.addFreshEntity(body);
		}
	}

	@Override
	protected void tickDeath() {
		++this.deathTime;
		if (this.deathTime == this.maxDeathTime) {
			this.remove();
			for (int i = 0; i < 20; ++i) {
				double d0 = this.random.nextGaussian() * 0.02D;
				double d1 = this.random.nextGaussian() * 0.02D;
				double d2 = this.random.nextGaussian() * 0.02D;
				this.level.addParticle(ParticleTypes.POOF, this.getRandomX(1.0D), this.getRandomY(),
						this.getRandomZ(1.0D), d0, d1, d2);
			}
			this.onZombieRemove();
		}
	}

	/**
	 * the last tick of zombies. for drop item and coin.
	 */
	protected void onZombieRemove() {
		if (!level.isClientSide) {
			if (getZombieType() == Type.SUPER) {// drop energy
				this.dropEnergy();
			} else if (getZombieType() == Type.SUN) {
				this.dropSun();
			} else if (getZombieType() == Type.BEARD) {// finish achievement
			}
			if (this.canSpawnDrop) {
				this.spawnSpecialDrops();
			}
		}
	}

	/**
	 * super mode type zombie can drop energy after death
	 */
	protected void dropEnergy() {
		EnergyEntity energy = EntityRegister.ENERGY.get().create(level);
		EntityUtil.onMobEntitySpawn(level, energy, this.blockPosition().above());
	}

	/**
	 * sun type zombie can drop sun after death
	 */
	protected void dropSun() {
		int num = this.getRandom().nextInt(8) + 3;
		for (int i = 0; i < num; ++i) {
			SunEntity sun = EntityRegister.SUN.get().create(level);
			sun.setAmount(25);
			EntityUtil.onMobEntityRandomPosSpawn(level, sun, blockPosition().above(), 2);
		}
	}
	
	/**
	 * zombies have chance to drop coin or chocolate when died.
	 */
	protected void spawnSpecialDrops() {
		this.dropSpecialList.getRandomItem(this.random).ifPresent(type -> {
			this.doZombieDrop(type);
		});
	}

	/**
	 * do drop with different droptype.
	 */
	private void doZombieDrop(DropType type) {
		switch(type) {
		case COPPER:{
			CoinEntity coin = EntityRegister.COIN.get().create(level);
			coin.setAmount(CoinType.COPPER.money);
			EntityUtil.onMobEntitySpawn(level, coin, blockPosition());
			break;
		}
		case SILVER:{
			CoinEntity coin = EntityRegister.COIN.get().create(level);
			coin.setAmount(CoinType.SILVER.money);
			EntityUtil.onMobEntitySpawn(level, coin, blockPosition());
			break;
		}
		case GOLD:{
			CoinEntity coin = EntityRegister.COIN.get().create(level);
			coin.setAmount(CoinType.GOLD.money);
			EntityUtil.onMobEntitySpawn(level, coin, blockPosition());
			break;
		}
		case JEWEL:{
			JewelEntity jewel = EntityRegister.JEWEL.get().create(level);
			EntityUtil.onMobEntitySpawn(level, jewel, blockPosition());
			break;
		}
		case CHOCOLATE:{
			ItemEntity chocolate = new ItemEntity(level, getX(), getY(), getZ(),
					new ItemStack(ItemRegister.CHOCOLATE.get()));
			EntityUtil.playSound(chocolate, SoundRegister.JEWEL_DROP.get());
			level.addFreshEntity(chocolate);
		}
	}
	}
	
	/**
	 * init zombie special drop list.
	 */
	protected WeightList<DropType> getDropSpecialList(){
		int p = PVZConfig.COMMON_CONFIG.EntitySettings.ZombieSetting.ZombieDropMultiper.get();
		int pp = p * p;
		return WeightList.of(
				pp * pp,
				Pair.of(DropType.SILVER, p * p * p),
				Pair.of(DropType.GOLD, p * p ),
				Pair.of(DropType.JEWEL, p),
				Pair.of(DropType.CHOCOLATE, p)
				);
	}

	/**
	 * p can zombie set target as attackTarget
	 */
	public boolean checkCanZombieTarget(LivingEntity target) {
		return EntityUtil.checkCanEntityAttack(this, target) && this.canZombieTarget(target);
	}

	protected boolean canZombieTarget(LivingEntity target) {
		if (target instanceof SpikeRockEntity)
			return false;
		if (target instanceof BungeeZombieEntity)
			return false;
		if (target instanceof PVZPlantEntity && ((PVZPlantEntity) target).hasMetal())
			return false;
		return true;
	}

	public boolean checkCanZombieBreakBlock() {
		return !this.isCharmed();
	}

	public boolean canZombieBeRemoved() {
		return true;
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (level.isClientSide)
			return false;
		if (source instanceof PVZDamageSource)
			this.invulnerableTime = 0;
		boolean flag = super.hurt(source, amount);
		if (source instanceof PVZDamageSource) {
			if (this.canBeFrozen() && ((PVZDamageSource) source).getPVZDamageType() == PVZDamageType.ICE
					&& !((PVZDamageSource) source).isDefended()) {
				if (!this.isZombieColdOrForzen() && !this.level.isClientSide) {
					EntityUtil.playSound(this, SoundRegister.ZOMBIE_FROZEN.get());
				}
			} else if (((PVZDamageSource) source).getPVZDamageType() == PVZDamageType.FIRE
					&& !((PVZDamageSource) source).isDefended()) {
				if (this.isZombieColdOrForzen() && !this.level.isClientSide) {
					EntityUtil.playSound(this, SoundRegister.ZOMBIE_FIRE.get());
					this.removeEffect(EffectRegister.COLD_EFFECT.get());
					this.removeEffectNoUpdate(EffectRegister.FROZEN_EFFECT.get());
				}
			}
		}
		return flag;
	}

	protected void dealDamageEffectToZombie(PVZDamageSource source) {
		if (source.isDefended()) {
			return;
		}
		for (EffectInstance effect : source.getEffects()) {
			EntityUtil.addEntityPotionEffect(this, effect);
		}
	}

	@Override
	public boolean doHurtTarget(Entity entityIn) {
		entityIn.invulnerableTime = 0;
		this.setAnimTime(PERFORM_ATTACK_CD);
		// add
		float f = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
		float f1 = (float) this.getAttributeValue(Attributes.ATTACK_KNOCKBACK);
		if (entityIn instanceof LivingEntity) {
			f += EnchantmentHelper.getDamageBonus(this.getMainHandItem(), ((LivingEntity) entityIn).getMobType());
			f1 += (float) EnchantmentHelper.getKnockbackBonus(this);
		}

		int i = EnchantmentHelper.getFireAspect(this);
		if (i > 0) {
			entityIn.setSecondsOnFire(i * 4);
		}

		boolean flag = entityIn.hurt(getZombieAttackDamageSource(), getModifyAttackDamage(entityIn, f));
		if (flag) {
			if (f1 > 0.0F && entityIn instanceof LivingEntity) {
				((LivingEntity) entityIn).knockback(f1 * 0.5F,
						(double) MathHelper.sin(this.yRot * ((float) Math.PI / 180F)),
						(double) (-MathHelper.cos(this.yRot * ((float) Math.PI / 180F))));
				this.setDeltaMovement(this.getDeltaMovement().multiply(0.6D, 1.0D, 0.6D));
			}

			if (entityIn instanceof PlayerEntity) {
				PlayerEntity playerentity = (PlayerEntity) entityIn;
				this.maybeDisableShield(playerentity, this.getMainHandItem(),
						playerentity.isUsingItem() ? playerentity.getUseItem() : ItemStack.EMPTY);
			}

			this.doEnchantDamageEffects(this, entityIn);
			this.setLastHurtMob(entityIn);
		}
		return flag;
	}

	private void maybeDisableShield(PlayerEntity p_233655_1_, ItemStack p_233655_2_, ItemStack p_233655_3_) {
		if (!p_233655_2_.isEmpty() && !p_233655_3_.isEmpty() && p_233655_2_.getItem() instanceof AxeItem
				&& p_233655_3_.getItem() == Items.SHIELD) {
			float f = 0.25F + (float) EnchantmentHelper.getBlockEfficiency(this) * 0.05F;
			if (this.random.nextFloat() < f) {
				p_233655_1_.getCooldowns().addCooldown(Items.SHIELD, 100);
				this.level.broadcastEntityEvent(p_233655_1_, (byte) 30);
			}
		}
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	public static boolean canZombieSpawn(EntityType<? extends PVZZombieEntity> zombieType, IWorld worldIn,
			SpawnReason reason, BlockPos pos, Random rand) {
		if(! checkZombieSpawn(zombieType, worldIn, reason)) return false;
		return worldIn.getBrightness(LightType.BLOCK, pos) > 8 ? false
				: checkAnyLightMonsterSpawnRules(zombieType, worldIn, reason, pos, rand);
	}
	
	/**
	 * chunk spawn zombie need has invasion event for that day.
	 */
	public static boolean checkZombieSpawn(EntityType<? extends PVZZombieEntity> zombieType, IWorld worldIn, SpawnReason reason) {
		if(reason != SpawnReason.NATURAL) return true;
		Optional<Zombies> opt = getZombieNameByType(zombieType);
		if(worldIn instanceof World) {
			if(opt.isPresent()) {
			    return OverworldInvasion.ZOMBIE_INVADE_SET.contains(opt.get());
			} else {
			    System.out.println("Error : No Such Zombie Type !");
			    return false;
			}
		}
		return false;
	}
	
	public static Optional<Zombies> getZombieNameByType(EntityType<? extends PVZZombieEntity> zombieType){
		return Optional.ofNullable(ZombieUtil.ENTITY_TYPE_ZOMBIE.get(zombieType));
	}

	@Override
	public float getWalkTargetValue(BlockPos pos, IWorldReader worldIn) {
		return 9 - worldIn.getBrightness(LightType.BLOCK, pos);
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
	public void push(Entity entityIn) {
		if (this.isSleeping()) {
			return;
		}
		if (!this.isPassengerOfSameVehicle(entityIn)) {
			if (!entityIn.noPhysics && !this.noPhysics) {
				double d0 = entityIn.getX() - this.getX();
				double d1 = entityIn.getZ() - this.getZ();
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
					d0 = d0 * (double) (1.0F - this.pushthrough);
					d1 = d1 * (double) (1.0F - this.pushthrough);
					if (!this.isVehicle()) {
						this.push(-d0, 0.0D, -d1);
					}
					if (!entityIn.isVehicle()) {
						if (!(entityIn instanceof PVZPlantEntity)) {
							entityIn.push(d0, 0.0D, d1);
						}
					}
				}
			}
		}
	}

	@Override
	protected void pushEntities() {
		double dd = this.getCollideWidthOffset();
		List<LivingEntity> list = this.level.getEntitiesOfClass(LivingEntity.class,
				this.getBoundingBox().inflate(dd, 0, dd));
		if (!list.isEmpty()) {
			int i = this.level.getGameRules().getInt(GameRules.RULE_MAX_ENTITY_CRAMMING);
			if (i > 0 && list.size() > i - 1 && this.random.nextInt(4) == 0) {
				int j = 0;
				for (int k = 0; k < list.size(); ++k) {
					if (!((Entity) list.get(k)).isPassenger()) {
						++j;
					}
				}
				if (j > i - 1) {
					this.hurt(DamageSource.CRAMMING, 6.0F);
				}
			}
			for (int l = 0; l < list.size(); ++l) {
				LivingEntity target = list.get(l);
				if (target != this && this.shouldCollideWithEntity(target)) {// can collide with
					this.doPush(target);
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
		if (this.getTarget() == target) {
			if (target instanceof SquashEntity || target instanceof SpikeWeedEntity) {
				return false;
			}
			return true;
		}
		if (target instanceof PVZZombieEntity) {
			return this.canCollideWithZombie && ((PVZZombieEntity) target).canCollideWithZombie;
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

	public boolean hasDefence() {
		return this.hasDirectDefence;
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("zombie_type", this.getZombieType().ordinal());
		if (this.getOwnerUUID().isPresent()) {
			compound.putUUID("OwnerUUID", this.getOwnerUUID().get());
		}
		compound.putInt("zombie_states_flag", this.getZombieStates());
		compound.putInt("zombie_attack_time", this.getAttackTime());
		compound.putInt("zombie_anim_time", this.getAnimTime());
		compound.putFloat("defence_life", this.getDefenceLife());
		compound.putInt("zombie_level", this.getZombieLevel());
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("zombie_type")) {
			this.setZombieType(Type.values()[compound.getInt("zombie_type")]);
		}
		// owner UUID
		UUID uuid;
		if (compound.hasUUID("OwnerUUID")) {
			uuid = compound.getUUID("OwnerUUID");
		} else {
			String s1 = compound.getString("OwnerUUID");
			uuid = PreYggdrasilConverter.convertMobOwnerIfNecessary(this.getServer(), s1);
		}
		if (uuid != null) {
			try {
				this.setOwnerUUID(uuid);
			} catch (Throwable var4) {
			}
		}
		if (compound.contains("zombie_states_flag")) {
			this.setZombieStates(compound.getInt("zombie_states_flag"));
		}
		if (compound.contains("zombie_attack_time")) {
			this.setAttackTime(compound.getInt("zombie_attack_time"));
		}
		if (compound.contains("zombie_anim_time")) {
			this.setAnimTime(compound.getInt("zombie_anim_time"));
		}
		if (compound.contains("defence_life")) {
			this.setDefenceLife(compound.getFloat("defence_life"));
		}
		if(compound.contains("zombie_level")) {
			this.setZombieLevel(compound.getInt("zombie_level"));
		}
	}

	@Override
	public boolean canBeLeashed(PlayerEntity player) {
		return false;
	}

	/**
	 * check can zombie add effect
	 */
	public void checkAndAddPotionEffect(EffectInstance effect) {
		if (effect.getEffect() == EffectRegister.COLD_EFFECT.get() && !this.canBeCold())
			return;
		if (effect.getEffect() == EffectRegister.FROZEN_EFFECT.get() && !this.canBeFrozen())
			return;
		if (effect.getEffect() == EffectRegister.BUTTER_EFFECT.get() && !this.canBeButter())
			return;
		this.addEffect(effect);
	}

	/**
	 * when zombie turn to oppsite charm state charm -> uncharm uncharm -> charm
	 */
	public void onCharmed(PVZPlantEntity plantEntity) {
		if (this.canBeCharmed()) {
			PlayerEntity player = EntityUtil.getEntityOwner(level, plantEntity);
			if (player != null && player instanceof ServerPlayerEntity) {
				CharmZombieTrigger.INSTANCE.trigger((ServerPlayerEntity) player, this);
			}
			this.setCharmed(!this.isCharmed());
			if (this.getZombieType() == Type.SUPER) {
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
		return this.canBeFrozen && !this.isInWater() && !this.isInLava();
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

	public boolean canBeStealByBungee() {
		return this.canBeStealByBungee;
	}

	@Nullable
	protected SoundEvent getSpawnSound() {
		return null;
	}

	public int getZombieStates() {
		return this.entityData.get(ZOMBIE_STATES);
	}

	public void setZombieStates(int state) {
		this.entityData.set(ZOMBIE_STATES, state);
	}

	public float getDefenceLife() {
		return entityData.get(DEFENCE_LIFE);
	}

	public void setDefenceLife(float life) {
		entityData.set(DEFENCE_LIFE, life);
	}
	
	public Optional<UUID> getOwnerUUID() {
		return entityData.get(OWNER_UUID);
	}

	public void setOwnerUUID(UUID uuid) {
		this.entityData.set(OWNER_UUID, Optional.ofNullable(uuid));
	}

	public void setCharmed(boolean is) {
		this.setStateByFlag(is, CHARM_FLAG);
	}

	public void setMiniZombie(boolean is) {
		this.setStateByFlag(is, MINI_FLAG);
	}
	
	private void setStateByFlag(boolean is, int flag) {
		this.setZombieStates(AlgorithmUtil.BitOperator.setBit(this.getZombieStates(), flag, is));
	}

	public void setZombieType(Type type) {
		entityData.set(ZOMBIE_TYPE, type.ordinal());
	}

	public boolean isCharmed() {
		return AlgorithmUtil.BitOperator.hasBitOne(this.getZombieStates(), CHARM_FLAG);
	}

	public boolean isMiniZombie() {
		return AlgorithmUtil.BitOperator.hasBitOne(this.getZombieStates(), MINI_FLAG);
	}
	
	public int getAttackTime() {
		return entityData.get(ATTACK_TIME);
	}

	public void setAttackTime(int cd) {
		entityData.set(ATTACK_TIME, cd);
	}
	
	public int getAnimTime() {
		return entityData.get(ANIM_TIME);
	}

	public void setAnimTime(int cd) {
		entityData.set(ANIM_TIME, cd);
	}
	
	public int getZombieLevel() {
		return entityData.get(ZOMBIE_LEVEL);
	}

	public void setZombieLevel(int level) {
		entityData.set(ZOMBIE_LEVEL, level);
	}

	public boolean isZombieColdOrForzen() {
		return EntityUtil.isEntityCold(this) || EntityUtil.isEntityFrozen(this);
	}

	public boolean canZombieNormalUpdate() {
		if (this.getVehicle() instanceof BungeeZombieEntity)
			return false;
		return !EntityUtil.isEntityFrozen(this) && !EntityUtil.isEntityButter(this);
	}

	public Type getZombieType() {
		return Type.values()[entityData.get(ZOMBIE_TYPE)];
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundRegister.ZOMBIE_SAY.get();
	}

	@Override
	public SoundEvent getHurtSound(DamageSource damageSourceIn) {
		if (damageSourceIn.getDirectEntity() instanceof AbstractBulletEntity) {
			return SoundRegister.PEA_HIT.get();
		}
		return super.getHurtSound(damageSourceIn);
	}

	@Override
	protected ResourceLocation getDefaultLootTable() {
		return PVZLoot.NORMAL_ZOMBIE;
	}

	public enum Type {
		NORMAL, SUPER, BEARD, SUN
	}
	
	protected enum DropType{
		COPPER, SILVER, GOLD, JEWEL, CHOCOLATE
	}

}
