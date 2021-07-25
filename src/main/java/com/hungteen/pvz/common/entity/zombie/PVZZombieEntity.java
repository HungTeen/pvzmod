package com.hungteen.pvz.common.entity.zombie;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.enums.PVZGroupType;
import com.hungteen.pvz.api.interfaces.IPVZZombie;
import com.hungteen.pvz.client.particle.ParticleUtil;
import com.hungteen.pvz.common.advancement.trigger.CharmZombieTrigger;
import com.hungteen.pvz.common.cache.InvasionCache;
import com.hungteen.pvz.common.entity.ai.goal.PVZLookRandomlyGoal;
import com.hungteen.pvz.common.entity.ai.goal.PVZSwimGoal;
import com.hungteen.pvz.common.entity.ai.goal.ZombieBreakPlantBlockGoal;
import com.hungteen.pvz.common.entity.ai.goal.attack.PVZZombieAttackGoal;
import com.hungteen.pvz.common.entity.ai.goal.target.PVZNearestTargetGoal;
import com.hungteen.pvz.common.entity.ai.navigator.ZombiePathNavigator;
import com.hungteen.pvz.common.entity.bullet.AbstractBulletEntity;
import com.hungteen.pvz.common.entity.drop.CoinEntity;
import com.hungteen.pvz.common.entity.drop.CoinEntity.CoinType;
import com.hungteen.pvz.common.entity.drop.SunEntity;
import com.hungteen.pvz.common.entity.misc.bowling.AbstractBowlingEntity;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.plant.enforce.SquashEntity;
import com.hungteen.pvz.common.entity.plant.magic.HypnoShroomEntity;
import com.hungteen.pvz.common.entity.plant.spear.SpikeWeedEntity;
import com.hungteen.pvz.common.entity.zombie.body.ZombieDropBodyEntity;
import com.hungteen.pvz.common.entity.zombie.body.ZombieDropBodyEntity.BodyType;
import com.hungteen.pvz.common.entity.zombie.roof.BungeeZombieEntity;
import com.hungteen.pvz.common.event.PVZLivingEvents;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.common.world.data.PVZInvasionData;
import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.register.BlockRegister;
import com.hungteen.pvz.register.EffectRegister;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.EntitySpawnRegister;
import com.hungteen.pvz.register.ItemRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.AlgorithmUtil;
import com.hungteen.pvz.utils.ConfigUtil;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.InvasionEvents;
import com.hungteen.pvz.utils.enums.Ranks;
import com.hungteen.pvz.utils.enums.Zombies;
import com.hungteen.pvz.utils.interfaces.ICanAttract;
import com.hungteen.pvz.utils.others.WeightList;
import com.mojang.datafixers.util.Pair;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
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
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
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
import net.minecraftforge.event.entity.living.LivingDamageEvent;

public abstract class PVZZombieEntity extends MonsterEntity implements IPVZZombie{

	private static final DataParameter<Integer> ZOMBIE_TYPE = EntityDataManager.defineId(PVZZombieEntity.class, DataSerializers.INT);
	private static final DataParameter<Optional<UUID>> OWNER_UUID = EntityDataManager.defineId(PVZZombieEntity.class, DataSerializers.OPTIONAL_UUID);
	private static final DataParameter<Integer> ZOMBIE_STATES = EntityDataManager.defineId(PVZZombieEntity.class, DataSerializers.INT);
	private static final DataParameter<Integer> ATTACK_TIME = EntityDataManager.defineId(PVZZombieEntity.class, DataSerializers.INT);
    //negative means rising, positive means perform attack animation.
	private static final DataParameter<Integer> ANIM_TIME = EntityDataManager.defineId(PVZZombieEntity.class, DataSerializers.INT);
	protected static final DataParameter<Float> DEFENCE_LIFE = EntityDataManager.defineId(PVZZombieEntity.class, DataSerializers.FLOAT);
	private static final DataParameter<Integer> ZOMBIE_LEVEL = EntityDataManager.defineId(PVZZombieEntity.class, DataSerializers.INT);
	protected static WeightList<DropType> dropSpecialList;
	private static final int CHARM_FLAG = 0;
	private static final int MINI_FLAG = 1;
	private static final int HAND_FLAG = 2;
	private static final int HEAD_FLAG = 3;
	public static final int PERFORM_ATTACK_CD = 10;
	public static final int RISING_CD = 30;
	protected boolean needRising = false;
	protected boolean hasDirectDefence = false;
	public boolean canCollideWithZombie = true;
	protected boolean canSpawnDrop = true;
	protected boolean canBeCold = true;
	protected boolean canBeFrozen = true;
	protected boolean canBeCharm = true;
	protected boolean canBeButter = true;
	protected boolean canBeMini = true;
	protected boolean canBeInvis = true;
	protected boolean canBeStealByBungee = true;
	protected boolean canBeRemove = true;
	protected boolean canLostHand = true;
	protected boolean canLostHead = true;
	protected boolean canHelpAttack = true;
	protected int maxDeathTime = 20;

	public PVZZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.refreshDimensions();
		this.xpReward = ZombieUtil.caculateZombieXp(this);
		dropSpecialList = this.getDropSpecialList();
		this.onLevelChanged();
		this.setPathfindingMalus(PathNodeType.DANGER_FIRE, 6.0F);
		this.setPathfindingMalus(PathNodeType.DAMAGE_FIRE, 6.0F);
		this.setPathfindingMalus(PathNodeType.DAMAGE_OTHER, 8.0F);
		this.setPathfindingMalus(PathNodeType.UNPASSABLE_RAIL, 8.0F);
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
	
	/**
	 * create zombie attributes.
	 * {@link EntityRegister#addEntityAttributes(net.minecraftforge.event.entity.EntityAttributeCreationEvent)}
	 */
	public static AttributeModifierMap createZombieAttributes() {
		return MonsterEntity.createMonsterAttributes()
				.add(Attributes.ATTACK_DAMAGE, ZombieUtil.LOW)
	    	    .add(Attributes.MAX_HEALTH, 20)
	     	    .add(Attributes.FOLLOW_RANGE, ZombieUtil.NORMAL_FOLLOW_RANGE)
	    		.add(Attributes.KNOCKBACK_RESISTANCE, 1)
	    		.add(Attributes.MOVEMENT_SPEED, ZombieUtil.WALK_NORMAL)
	    		.add(Attributes.FLYING_SPEED, 0)
	    		.build();
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(8, new PVZLookRandomlyGoal(this));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(7, new PVZSwimGoal(this));
//		this.targetSelector.addGoal(2, new PVZHurtByTargetGoal(this, 10));
		this.registerAttackGoals();
		this.registerTargetGoals();
	}
	
	/**
	 * {@link #registerGoals()}
	 */
	protected void registerAttackGoals() {
		this.goalSelector.addGoal(3, new PVZZombieAttackGoal(this, true));
		this.goalSelector.addGoal(6, new ZombieBreakPlantBlockGoal(BlockRegister.FLOWER_POT.get(), this, 1F, 10));
	}
	
	/**
	 * {@link #registerGoals()}
	 */
	protected void registerTargetGoals() {
		this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, true, ZombieUtil.NORMAL_TARGET_RANGE, ZombieUtil.NORMAL_TARGET_HEIGHT));
	}
	
	@Override
	protected PathNavigator createNavigation(World world) {
		return new ZombiePathNavigator(this, world);
	}
	
	/* handle spawn */

	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		if (!level.isClientSide) {
			this.setZombieType(this.getSpawnType());
			this.getSpawnSound().ifPresent(s -> {
				EntityUtil.playSound(this, s);
			});
			/*just test */
			//set its spawn level
			final int minLvl = MathHelper.clamp(InvasionCache.InvasionDifficulty / 100 + 1, 1, 20);
			final int maxLvl = MathHelper.clamp(InvasionCache.InvasionDifficulty / 50 + 1, 1, 20);
			final int lvl = MathUtil.getRandomMinMax(getRandom(), minLvl, maxLvl);
			this.setZombieLevel(lvl);
			/*end test */
			this.updateAttributes();
			if(this.needRising) {// rising from dirt.
				this.setAnimTime(- RISING_CD);
				this.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, RISING_CD + 10, 20, false, false));
			}
			if (level.dimension() == World.OVERWORLD) {//update states.
				PVZInvasionData data = PVZInvasionData.getOverWorldInvasionData(level);
				//zombie rising from dirt can not be mini state.
				if (! this.needRising && this.canBeMini() && data.hasEvent(InvasionEvents.MINI)) {
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
	 * get current variant type.
	 * it will be override by @NormalZombieEntity
	 * {@link #finalizeSpawn(IServerWorld, DifficultyInstance, SpawnReason, ILivingEntityData, CompoundNBT)}
	 */
	protected Type getSpawnType() {
		int t = this.getRandom().nextInt(100);
		int a = PVZConfig.COMMON_CONFIG.EntitySettings.ZombieSetting.ZombieSuperChance.get();
		int b = PVZConfig.COMMON_CONFIG.EntitySettings.ZombieSetting.ZombieSunChance.get();
		return (t < a) ? Type.SUPER : (t < a + b) ? Type.SUN : Type.NORMAL;
	}

	/**
	 * update zombie attributes when first spawn.
	 * {@link #finalizeSpawn(IServerWorld, DifficultyInstance, SpawnReason, ILivingEntityData, CompoundNBT)}
	 */
	protected void updateAttributes() {
		this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(ZombieUtil.LOW);
		this.getAttribute(Attributes.FOLLOW_RANGE).setBaseValue(30);
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.WALK_NORMAL);
		this.getAttribute(Attributes.KNOCKBACK_RESISTANCE).setBaseValue(1D);
		EntityUtil.setLivingMaxHealthAndHeal(this, this.getLife());
		this.setDefenceLife(this.getExtraLife());
		this.onLevelChanged();
	}

	@Override
	public void aiStep() {
		super.aiStep();
		this.level.getProfiler().push("PVZ Zombie Tick");
		this.zombieTick();
		this.level.getProfiler().pop();
		if (this.canZombieNormalUpdate()) {
			this.level.getProfiler().push("PVZ Normal Zombie Tick");
		    this.normalZombieTick();
		    this.level.getProfiler().pop();
		}
	}
	
	/**
	 * tick whether zombie is in normal state or not.
	 * {@link #aiStep()}
	 */
	public void zombieTick() {
		if (this.tickCount <= 2) {
			this.refreshDimensions();
		}
		//rising particle
		if(this.isZombieRising()) {
			this.setAnimTime(this.getAnimTime() + 1);
			if(level.isClientSide) {
				ParticleUtil.spawnSplash(this.level, this.position(), 1);
			}
		}
	}

	/**
	 * tick when zombie is normal state.
	 * (not be frozen or butter and so on).
	 * {@link #aiStep()}
	 */
	public void normalZombieTick() {
		if(! this.level.isClientSide) {
			this.setAnimTime(Math.max(0, this.getAnimTime() - 1));
		}
	}
	
	public void onSpawnedByPlayer(PlayerEntity player, int lvl) {
		this.setOwnerUUID(player.getUUID());
		this.updateZombieLevel(lvl);
	}
	
	public void updateZombieLevel(int lvl) {
		if(this.getZombieLevel() != lvl) {//level changed.
			this.setZombieLevel(lvl);
		}
	}

	/**
	 * trigger at {@link #hurt()}
	 */
	private void onLostHand(DamageSource source) {
		this.lostHand(true);
		ZombieDropBodyEntity body = EntityRegister.ZOMBIE_DROP_BODY.get().create(level);
		body.droppedByOwner(this, source, BodyType.HAND);
		level.addFreshEntity(body);
	}
	
	/**
	 * trigger at {@link #hurt()}
	 */
	private void onLostHead(DamageSource source) {
		this.lostHead(true);
		ZombieDropBodyEntity body = EntityRegister.ZOMBIE_DROP_BODY.get().create(level);
		body.droppedByOwner(this, source, BodyType.HEAD);
		level.addFreshEntity(body);
	}
	
	/**
	 * trigger at {@link #die(DamageSource)}
	 */
	protected void onFallBody(DamageSource source) {
		ZombieDropBodyEntity body = EntityRegister.ZOMBIE_DROP_BODY.get().create(level);
		body.droppedByOwner(this, source, BodyType.BODY);
		body.setMaxLiveTick(40);
		this.setBodyStates(body);
		level.addFreshEntity(body);
	}
	
	/**
	 * set states to body.
	 * such as has paper or not.
	 * {@link #onFallBody(DamageSource)}
	 */
	protected void setBodyStates(ZombieDropBodyEntity body) {
		body.setMini(this.isMiniZombie());
	}
	
	/**
	 * trigger when zombie be mini state.
	 * change max health to 60% and give speed effect and damage boost.
	 * {@link #finalizeSpawn(IServerWorld, DifficultyInstance, SpawnReason, ILivingEntityData, CompoundNBT)}
	 */
	public void onZombieBeMini() {
		this.setMiniZombie(true);
		final float healthDec = 0.6F;
		EntityUtil.setLivingMaxHealthAndHeal(this, this.getMaxHealth() * healthDec);
		this.setDefenceLife(this.getDefenceLife() * healthDec);
		this.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 1000000, 0, false, false));
		this.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 1000000, 0, false, false));
	}
	
	/**
	 * called every time when zombie's level changed.
	 * {@link #onSpawnedByPlayer(PlayerEntity, int)}
	 */
	public void onLevelChanged() {
	}

	/**
	 * zombie perform attack CD.
	 * use for attack goals.
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
	
	/**
	 * get how many percent of health will be resist.
	 */
	public float getHurtReduction() {
		int lvl = this.getZombieLevel();
		final float inc = 0.2F;
		return (lvl >= 20) ? 0.2F : 1.0F / (1 + inc * (lvl - 1));
	}

	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return this.isMiniZombie() ? EntitySize.scalable(0.3F, 0.6F) : EntitySize.scalable(0.8f, 1.98f);
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);
		//if zombie was killed by bowling, it will not drop anything.
		if (cause instanceof PVZDamageSource && ((PVZDamageSource) cause).getDirectEntity() instanceof AbstractBowlingEntity
				&& ((PVZDamageSource) cause).getDamageCount() > 0) {
			this.canSpawnDrop = false;
		}
		if(ConfigUtil.enableZombieDropParts()) {
			if(! this.level.isClientSide) {
			    this.onFallBody(cause);
			}
		}
	}

	@Override
	protected void tickDeath() {
		++ this.deathTime;
		if (ConfigUtil.enableZombieDropParts() || this.deathTime >= this.maxDeathTime) {
			for (int i = 0; i < 5; ++i) {
				double d0 = this.random.nextGaussian() * 0.02D;
				double d1 = this.random.nextGaussian() * 0.02D;
				double d2 = this.random.nextGaussian() * 0.02D;
				this.level.addParticle(ParticleTypes.POOF, this.getRandomX(1.0D), this.getRandomY(),
						this.getRandomZ(1.0D), d0, d1, d2);
			}
			this.onZombieRemove();
			this.remove();
		}
	}

	/**
	 * the last tick of zombies.
	 * {@link #tickDeath()}
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
	 * {@link #onZombieRemove()}
	 * {@link #onCharmedBy(LivingEntity)}
	 */
	protected void dropEnergy() {
		EntityUtil.createEntityAndSpawn(level, EntityRegister.ENERGY.get(), this.blockPosition().above());
	}
	
	/**
	 * sun type zombie can drop sun after death.
	 * {@link #onZombieRemove()}
	 */
	protected void dropSun() {
		int num = this.getRandom().nextInt(8) + 3;
		for (int i = 0; i < num; ++i) {
			SunEntity.spawnSunRandomly(level, blockPosition().above(), 25, 2);
		}
	}
	
	/**
	 * zombies have chance to drop coin or chocolate when died.
	 * {@link #onZombieRemove()}
	 */
	protected void spawnSpecialDrops() {
		dropSpecialList.getRandomItem(this.random).ifPresent(type -> {
			this.doZombieDrop(type);
		});
	}

	/**
	 * do drop with different droptype.
	 * {@link #spawnSpecialDrops()}
	 */
	private void doZombieDrop(DropType type) {
		switch(type) {
		case COPPER:{
			CoinEntity.spawnCoin(level, blockPosition(), CoinType.COPPER);
			break;
		}
		case SILVER:{
			CoinEntity.spawnCoin(level, blockPosition(), CoinType.SILVER);
			break;
		}
		case GOLD:{
			CoinEntity.spawnCoin(level, blockPosition(), CoinType.GOLD);
			break;
		}
		case JEWEL:{
			EntityUtil.createEntityAndSpawn(level, EntityRegister.JEWEL.get(), blockPosition());
			break;
		}
		case CHOCOLATE:{
			ItemEntity chocolate = new ItemEntity(level, getX(), getY(), getZ(),
					new ItemStack(ItemRegister.CHOCOLATE.get()));
			EntityUtil.playSound(chocolate, SoundRegister.JEWEL_DROP.get());
			level.addFreshEntity(chocolate);
			break;
		}
		}
	}
	
	/**
	 * init zombie special drop list.
	 * {@link #PVZZombieEntity(EntityType, World)}
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
	 * check can zombie set target as attackTarget.
	 * {@link EntityUtil#canEntityTarget(Entity, Entity)}
	 */
	public boolean checkCanZombieTarget(Entity target) {
		return EntityUtil.checkCanEntityBeTarget(this, target) && this.canZombieTarget(target);
	}
	
	/**
	 * check can zombie attack target.
	 * {@link EntityUtil#canEntityAttack(Entity, Entity)}
	 */
	public boolean checkCanZombieAttack(Entity target) {
		return EntityUtil.checkCanEntityBeAttack(this, target) && this.canZombieTarget(target);
	}
	
	/**
	 * can zombie be target by living, often use for plant's target.
	 * {@link PVZPlantEntity#canPlantTarget(Entity)}
	 */
	public boolean canBeTargetBy(LivingEntity living) {
		return true;
	}

	/**
	 * do not attack spike weed, bungee, plants with steel ladder.
	 */
	public boolean canZombieTarget(Entity target) {
		if(target instanceof PVZPlantEntity) {
			return ((PVZPlantEntity) target).canBeTargetBy(this);
		}
		if(target instanceof PVZZombieEntity) {
			return ((PVZZombieEntity) target).canBeTargetBy(this);
		}
		return true;
	}

	/**
	 * plant block are consider as group 1, so zombie will break them.
	 * {@link ZombieBreakPlantBlockGoal#canZombieContinue()}
	 * {@link LilyPadBlock#entityInside(net.minecraft.block.BlockState, World, BlockPos, Entity)
	 */
	public boolean canBreakPlantBlock() {
		return ! this.isCharmed();
	}

	/**
	 * check can zombie be removed by Lawn Mower.
	 * {@link EntityUtil#canEntityBeRemoved(Entity)}
	 */
	public boolean canZombieBeRemoved() {
		return this.canBeRemove;
	}
	
	/**
	 * {@link EntityUtil#canHelpAttackOthers(Entity)}
	 */
	public boolean canHelpAttack() {
		return this.canHelpAttack;
	}
	
	@Override
	public boolean hurt(DamageSource source, float amount) {
		if(level.isClientSide) {
			return false;
		}
		if (source instanceof PVZDamageSource) {//can get hurt each attack by pvz damage.
			this.invulnerableTime = 0;
		}
		amount = amount * this.getHurtReduction(); // hurt reduction
		boolean flag = super.hurt(source, amount);
		if(ConfigUtil.enableZombieDropParts()) {
		    if(!this.level.isClientSide) {
			    if(this.hasHand() && this.canLostHand() && this.checkCanLostHand()) {
				    this.onLostHand(source);
			    }
			    if(this.hasHead() && this.canLostHead() && this.checkCanLostHead()) {
				    this.onLostHead(source);
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
			EntityUtil.addPotionEffect(this, effect);
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

	/**
	 * copy from default code.
	 */
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
	
	public static boolean checkSpawn(EntityType<? extends PVZZombieEntity> zombieType, IWorld worldIn,
			SpawnReason reason, BlockPos pos, Random rand) {
		return worldIn.getBrightness(LightType.BLOCK, pos) > 8 ? false
				: checkAnyLightMonsterSpawnRules(zombieType, worldIn, reason, pos, rand);
	}
	
	/**
	 * {@link EntitySpawnRegister#registerEntitySpawns(net.minecraftforge.event.RegistryEvent.Register)}
	 */
	public static boolean canZombieSpawn(EntityType<? extends PVZZombieEntity> zombieType, IWorld worldIn,
			SpawnReason reason, BlockPos pos, Random rand) {
		if(! checkZombieSpawn(zombieType, worldIn, reason)) return false;
		return checkSpawn(zombieType, worldIn, reason, pos, rand);
	}
	
	/**
	 * chunk spawn zombie need has invasion event for that day.
	 */
	public static boolean checkZombieSpawn(EntityType<? extends PVZZombieEntity> zombieType, IWorld worldIn, SpawnReason reason) {
		if(reason != SpawnReason.NATURAL) return true;
		Optional<Zombies> opt = ZombieUtil.getZombieNameByType(zombieType);
		if(worldIn instanceof World) {
			if(opt.isPresent()) {
			    return InvasionCache.ZOMBIE_INVADE_SET.contains(opt.get());
			} else {
				PVZMod.LOGGER.error("No Such Zombie Type !");
			    return false;
			}
		}
		return false;
	}
	
	/**
	 * {@link PVZLivingEvents#onLivingDamage(LivingDamageEvent)}
	 */
	public static void damageZombieDefence(final LivingDamageEvent ev) {
		float amount = ev.getAmount();
		if(ev.getEntityLiving() instanceof PVZZombieEntity) {// zombie defence hit
			final PVZZombieEntity zombie = (PVZZombieEntity) ev.getEntityLiving();
			if(zombie.hasDirectDefence() && zombie.getDefenceLife() > 0) {
				if(zombie.getDefenceLife() > amount) {
				    zombie.setDefenceLife(zombie.getDefenceLife() - amount);
				    amount = 0;
				} else {
			        amount -= zombie.getDefenceLife();
					zombie.setDefenceLife(0);
				}
			}
		}
		ev.setAmount(amount == 0 ? 0.000001F : amount);
	}
	
	@Override
	public float getWalkTargetValue(BlockPos pos, IWorldReader worldIn) {
		return 9 - worldIn.getBrightness(LightType.BLOCK, pos);
	}
	
	@Override
	protected float getBlockSpeedFactor() {//not affect by soul sand.
		Block block = this.level.getBlockState(this.blockPosition()).getBlock();
	    float f = block.getSpeedFactor();
	    if (block == Blocks.WATER || block != Blocks.BUBBLE_COLUMN) {
	    	return f;
	    }
	    return 1F;
	}
	
	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return ! this.getOwnerUUID().isPresent();
	}

	/**
	 * damage type of zombie.
	 * {@link #doHurtTarget(Entity)}
	 */
	protected PVZDamageSource getZombieAttackDamageSource() {
		return PVZDamageSource.eat(this);
	}

	/**
	 * true attack damage of zombie.
	 * {@link #doHurtTarget(Entity)}
	 */
	protected float getModifyAttackDamage(Entity entity, float f) {
		return f;
	}

	@Override
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
						if (checkCanPushEntity(entityIn)) {
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
			for (int l = 0; l < list.size(); ++ l) {
				final LivingEntity target = list.get(l);
				if (! this.is(target) && this.shouldCollideWithEntity(target)) {// can collide with
					this.doPush(target);
				}
			}
		}
	}
	
	protected double getCollideWidthOffset() {
		return - 0.25D;
	}
	
	/**
	 * can zombie collide with target.
	 * {@link #pushEntities()}
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
	 * can zombie push target.
	 * {@link #push(Entity)}
	 */
	protected boolean checkCanPushEntity(Entity target) {
		return !(target instanceof PVZPlantEntity);
	}

	@Override
	protected float getWaterSlowDown() {
		return 0.85f;
	}
	
	@Override
	public boolean isPushedByFluid() {
		return false;
	}
	
	/**
	 * check can run {@link #normalZombieTick()} or not.
	 */
	public boolean canZombieNormalUpdate() {
		if(! EntityUtil.isEntityValid(this)) {
			return false;
		}
		if(this.needRising && this.getAnimTime() < 0) {//rising didn't update.
			return false;
		}
		if (this.getVehicle() instanceof BungeeZombieEntity) {
			return false;
		}
		return !EntityUtil.isEntityFrozen(this) && !EntityUtil.isEntityButter(this);
	}
	
	@Override
	public boolean canBeLeashed(PlayerEntity player) {
		return ! EntityUtil.checkCanEntityBeAttack(this, player);
	}
	
	@Override
	public boolean canBeAttractedBy(ICanAttract defender) {
		return true;
	}
	
	@Override
	public void attractBy(ICanAttract defender) {
	}
	
	/**
	 * some zombies are not able to drop hands.
	 * {@link #hurt}
	 */
	public boolean checkCanLostHand() {
		return this.getHealth() < Math.min(40, this.getMaxHealth() * 0.5F);
	}
	
	/**
	 * some zombies are not able to drop heads.
	 * {@link #hurt}
	 */
	public boolean checkCanLostHead() {
		return this.getHealth() < 10 && this.getHealth() / this.getMaxHealth() < 0.1F;
	}

	/**
	 * check can zombie add effect.
	 * {@link EntityUtil#addPotionEffect(Entity, EffectInstance)}
	 */
	public void checkAndAddPotionEffect(EffectInstance effect) {
		if (effect.getEffect() == EffectRegister.COLD_EFFECT.get() && !this.canBeCold()) {
			return;
		}
		if (effect.getEffect() == EffectRegister.FROZEN_EFFECT.get() && !this.canBeFrozen()) {
			return;
		}
		if (effect.getEffect() == EffectRegister.BUTTER_EFFECT.get() && !this.canBeButter()) {
			return;
		}
		this.addEffect(effect);
	}

	/**
	 * when zombie turn to oppsite charm state charm -> uncharm or uncharm -> charm.
	 * {@link HypnoShroomEntity#die(DamageSource)}
	 */
	@Override
	public void onCharmedBy(LivingEntity entity) {
		if(! this.canBeCharmed()) {
			return ;
		}
		PlayerEntity player = EntityUtil.getEntityOwner(level, entity);
		if (player != null && player instanceof ServerPlayerEntity) {
			CharmZombieTrigger.INSTANCE.trigger((ServerPlayerEntity) player, this);
		}
		this.setCharmed(!this.isCharmed());
		if (this.getZombieType() == Type.SUPER) {
			this.setZombieType(Type.NORMAL);
			this.dropEnergy();
		}
	}
	
	public void healZombie(float health) {
		final float need1 = this.getMaxHealth() - this.getHealth(); 
		this.heal(Math.min(need1, health));
		health -= need1;
		this.setDefenceLife(Math.max(this.getExtraLife(), this.getDefenceLife() + health));
	}
	
	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		if(source instanceof PVZDamageSource && ((PVZDamageSource) source).isMustHurt()) {
			return false;
		}
		return source != DamageSource.OUT_OF_WORLD && !source.isCreativePlayer() && this.isZombieInvulnerableTo(source);
	}
	
	protected boolean isZombieInvulnerableTo(DamageSource source) {
		return this.isZombieRising() || (! EntityUtil.isEntityValid(source.getEntity()) && ! source.isMagic());
	}
	
	/**
	 * is zombie still rising from dirt.
	 */
	public boolean isZombieRising() {
		return this.getAnimTime() < 0;
	}
	
	/* misc set */
	
	public void setZombieRising() {
		this.needRising = true;
	}
	
	/**
	 * it will not be affect by any effects.
	 */
	public void setImmuneAllEffects() {
		this.canBeButter = false;
		this.canBeCold = false;
		this.canBeFrozen = false;
	}
	
	/**
	 * it will not drop head and hand.
	 */
	public void setIsWholeBody() {
		this.canLostHand = false;
		this.canLostHead = false;
	}
	
	/* misc get */

	@Override
	public boolean canBeButter() {
		return this.canBeButter;
	}

	@Override
	public boolean canBeCharmed() {
		return this.canBeCharm;
	}

	@Override
	public boolean canBeFrozen() {
		return this.canBeFrozen && !this.isInWaterOrBubble() && !this.isInLava();
	}

	@Override
	public boolean canBeMini() {
		return this.canBeMini;
	}

	@Override
	public boolean canBeInvis() {
		return this.canBeInvis;
	}

	@Override
	public boolean canBeCold() {
		return this.canBeCold;
	}

	@Override
	public boolean canBeStealByBungee() {
		return this.canBeStealByBungee;
	}
	
	public boolean canLostHand() {
		return this.canLostHand;
	}
	
	public boolean canLostHead() {
		return this.canLostHead;
	}
	
	/**
	 * {@link #damageZombieDefence(LivingDamageEvent)}
	 */
	public boolean hasDirectDefence() {
		return this.hasDirectDefence;
	}

	public boolean isZombieColdOrForzen() {
		return EntityUtil.isEntityCold(this) || EntityUtil.isEntityFrozen(this);
	}
	
	@Override
	public float getExtraLife() {
		return 0;
	}
	
	/**
	 * get current health of zombie(contain its defence health).
	 */
	public float getCurrentHealth() {
		return this.getDefenceLife() + this.getHealth();
	}

	/**
	 * get current max health of zombie(contain defence health).
	 */
	public float getCurrentMaxHealth() {
		return this.getDefenceLife() + this.getMaxHealth();
	}
	
	@Override
	public PVZGroupType getEntityGroupType() {
		return this.isCharmed() ? PVZGroupType.PLANTS : PVZGroupType.ZOMBIES;
	}

	/* sound */
	
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
	
	protected Optional<SoundEvent> getSpawnSound() {
		if(this.needRising) {//if zombie is rising from dirt.
			return Optional.ofNullable(SoundRegister.DIRT_RISE.get());
		}
		return Optional.empty();
	}
	
	/* loot */

	@Override
	protected ResourceLocation getDefaultLootTable() {
		return PVZLoot.NORMAL_ZOMBIE;
	}
	
	/* data */
	
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
	
	/* getter setter */
	
	public int getZombieLevel() {
		return entityData.get(ZOMBIE_LEVEL);
	}

	public void setZombieLevel(int level) {
		entityData.set(ZOMBIE_LEVEL, level);
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

	@Override
	public Optional<UUID> getOwnerUUID() {
		return entityData.get(OWNER_UUID);
	}
	
	public void setOwnerUUID(UUID uuid) {
		this.entityData.set(OWNER_UUID, Optional.ofNullable(uuid));
	}
	
	public Type getZombieType() {
		return Type.values()[entityData.get(ZOMBIE_TYPE)];
	}
	
	public void setZombieType(Type type) {
		entityData.set(ZOMBIE_TYPE, type.ordinal());
	}
	
	@Override
	public boolean isCharmed() {
		return AlgorithmUtil.BitOperator.hasBitOne(this.getZombieStates(), CHARM_FLAG);
	}
	
	public void setCharmed(boolean is) {
		this.setStateByFlag(is, CHARM_FLAG);
	}

	public boolean isMiniZombie() {
		return AlgorithmUtil.BitOperator.hasBitOne(this.getZombieStates(), MINI_FLAG);
	}
	
    public void setMiniZombie(boolean is) {
		this.setStateByFlag(is, MINI_FLAG);
	}
    
    public boolean hasHand() {
		return !AlgorithmUtil.BitOperator.hasBitOne(this.getZombieStates(), HAND_FLAG);
	}
	
    public void lostHand(boolean is) {
		this.setStateByFlag(is, HAND_FLAG);
	}
    
    public boolean hasHead() {
		return !AlgorithmUtil.BitOperator.hasBitOne(this.getZombieStates(), HEAD_FLAG);
	}
	
    public void lostHead(boolean is) {
		this.setStateByFlag(is, HEAD_FLAG);
	}
    
    private void setStateByFlag(boolean is, int flag) {
		this.setZombieStates(AlgorithmUtil.BitOperator.setBit(this.getZombieStates(), flag, is));
	}
    
	/**
	 * Zombie Variant Types.
	 */
	public enum Type {
		NORMAL, //the common type.
		SUPER, //zombie that will drop energy when death.
		BEARD, //zombie with green beard.(can only own by normal_zombie family)
		SUN, //zombie that will drop some sun when death.
	}
	
	/**
	 * Special Drop Types.
	 */
	protected enum DropType{
		COPPER, //drop copper coin.
		SILVER, //drop silver coin.
		GOLD,  //drop gold coin.
		JEWEL, //drop jewel.
		CHOCOLATE, //drop chocolate.
	}

}
