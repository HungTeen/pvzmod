package com.hungteen.pvz.common.entity.plant;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.hungteen.pvz.api.enums.PVZGroupType;
import com.hungteen.pvz.api.interfaces.ICanBeCharmed;
import com.hungteen.pvz.api.interfaces.IGroupEntity;
import com.hungteen.pvz.api.interfaces.IHasOwner;
import com.hungteen.pvz.common.advancement.trigger.PlantSuperTrigger;
import com.hungteen.pvz.common.capability.CapabilityHandler;
import com.hungteen.pvz.common.core.CardRank;
import com.hungteen.pvz.common.core.EssenceType;
import com.hungteen.pvz.common.core.PlantType;
import com.hungteen.pvz.common.entity.ai.goal.PVZLookRandomlyGoal;
import com.hungteen.pvz.common.entity.drop.SunEntity;
import com.hungteen.pvz.common.entity.plant.enforce.SquashEntity;
import com.hungteen.pvz.common.entity.plant.explosion.DoomShroomEntity;
import com.hungteen.pvz.common.entity.plant.light.GoldLeafEntity;
import com.hungteen.pvz.common.entity.plant.spear.SpikeWeedEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.entity.zombie.grassnight.TombStoneEntity;
import com.hungteen.pvz.common.entity.zombie.roof.BungeeZombieEntity;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.EffectRegister;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.ParticleRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.remove.MetalTypes;
import com.hungteen.pvz.utils.AlgorithmUtil;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.interfaces.IHasMetal;

import net.minecraft.block.Block;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.GameRules;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

public abstract class PVZPlantEntity extends CreatureEntity implements IHasOwner, IGroupEntity, ICanBeCharmed, IHasMetal {

	private static final DataParameter<Integer> SUPER_TIME = EntityDataManager.defineId(PVZPlantEntity.class, DataSerializers.INT);
	private static final DataParameter<Integer> PLANT_LVL = EntityDataManager.defineId(PVZPlantEntity.class, DataSerializers.INT);
	private static final DataParameter<Optional<UUID>> OWNER_UUID = EntityDataManager.defineId(PVZPlantEntity.class, DataSerializers.OPTIONAL_UUID);
	private static final DataParameter<Integer> PLANT_STATES = EntityDataManager.defineId(PVZPlantEntity.class, DataSerializers.INT);
	private static final DataParameter<Integer> ATTACK_TIME = EntityDataManager.defineId(PVZPlantEntity.class, DataSerializers.INT);
	private static final DataParameter<Integer> GOLD_TIME = EntityDataManager.defineId(PVZPlantEntity.class, DataSerializers.INT);
	private static final DataParameter<Integer> BOOST_TIME = EntityDataManager.defineId(PVZPlantEntity.class, DataSerializers.INT);
	private static final DataParameter<Float> PUMPKIN_LIFE = EntityDataManager.defineId(PVZPlantEntity.class, DataSerializers.FLOAT);
	private static final DataParameter<Integer> EXIST_TICK = EntityDataManager.defineId(PVZPlantEntity.class, DataSerializers.INT);  
	//handler plant level, plant level range in (1, 128).
	protected static final int PLANT_LEVEL_FLAG_LEN = 7;
	//handle sync plant level.
	private static final int PLANT_LEVEL_SYNC_CD = 1200;
	public boolean need_sync_level = true;
	//plant states flags.
	protected static final int LADDER_FLAG = 0;
	protected static final int CHARM_FLAG = 1;
	protected static final int SLEEP_FLAG = 2;
	//handle plant weak, place on wrong block.
	private static final int PLANT_WEAK_CD = 10;
	protected PlayerEntity ownerPlayer;
	protected boolean isImmuneToWeak = false;
	protected int weakTime = 0;
	//handle plant collide with other plants.
	public boolean canCollideWithPlant = true;
	//handle plant sleep.
	public int sleepTime = 0;
	//handle outer plant, like pumpkin.
	protected Optional<PlantType> outerPlant = Optional.empty();
	//handler sun cost of plant, and its outer plant.
	public int outerSunCost = 0;
	public int plantSunCost = 0;
	protected boolean canBeCold = true;
	protected boolean canBeFrozen = true;
	protected boolean canBeCharm = true;
	protected boolean canBeButter = true;
	protected boolean canBeRemove = true;
	protected boolean canHelpAttack = true;

	public PVZPlantEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.refreshDimensions();
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		entityData.define(SUPER_TIME, 0);
		entityData.define(PLANT_LVL, 1);
		entityData.define(OWNER_UUID, Optional.empty());
		entityData.define(ATTACK_TIME, 0);
		entityData.define(GOLD_TIME, 0);
		entityData.define(BOOST_TIME, 0);
		entityData.define(PUMPKIN_LIFE, 0f);
		entityData.define(PLANT_STATES, 0);
		entityData.define(EXIST_TICK, 0);
	}

	public static AttributeModifierMap createPlantAttributes() {
		return LivingEntity.createLivingAttributes()
				.add(Attributes.MAX_HEALTH, 30)
				.add(Attributes.FOLLOW_RANGE, 40.0D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 1)
				.add(Attributes.MOVEMENT_SPEED, 0).build();
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(2, new PVZLookRandomlyGoal(this));
	}
	
	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		if (! worldIn.isClientSide()) {
			EntityUtil.playSound(this, this.getSpawnSound());
			this.updateAttributes();
			this.heal(this.getMaxHealth());
		}
		return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}
	
	/**
	 * update plant attributes when first spawn.
	 * {@link #finalizeSpawn(IServerWorld, DifficultyInstance, SpawnReason, ILivingEntityData, CompoundNBT)}
	 */
	protected void updateAttributes() {
		this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(this.getPlantHealth());
	}

	@Override
	public void aiStep() {
		super.aiStep();
		this.level.getProfiler().push("PVZ Plant Tick");
		this.plantTick();
		this.level.getProfiler().pop();
		if (this.canPlantNormalUpdate()) {
			this.level.getProfiler().push("PVZ Normal Plant Tick");
			this.normalPlantTick();
			this.level.getProfiler().pop();
		}
	}
	
	/**
	 * check can run {@link #normalPlantTick()} or not.
	 */
	public boolean canPlantNormalUpdate() {
		if(! EntityUtil.isEntityValid(this)) {
			return false;
		}
		if (this.getVehicle() instanceof BungeeZombieEntity || this.hasMetal()) {
			return false;
		}
		return !this.isPlantSleeping() && !EntityUtil.isEntityFrozen(this) && !EntityUtil.isEntityButter(this);
	}

	/**
	 * plant tick.
	 * {@link #aiStep()}
	 */
	protected void plantTick() {
		/* check plant wilt. */
		if (!this.level.isClientSide) {
			if (this.shouldWilt() && this.weakTime <= 0) {
				this.weakTime = PLANT_WEAK_CD;
				this.hurt(PVZDamageSource.PLANT_WILT, EntityUtil.getMaxHealthDamage(this, 0.35F));
			}
			this.weakTime = Math.max(0, this.weakTime - 1);
		}
		// super mode or boost time or sleep time
		if (!this.level.isClientSide) {
			//handle super mode.
			this.setSuperTime(Math.max(0, this.getSuperTime() - 1));
			//handle boost mode(no use for currrent version).
			this.setBoostTime(Math.max(0, this.getBoostTime() - 1));
			this.setExistTick(this.getExistTick() + 1);
			//handler plant's sleep.
			if (this.shouldPlantRegularSleep()) {
				this.sleepTime = this.sleepTime <= 1 ? this.sleepTime + 1 : this.sleepTime - 1;
			} else {
				this.sleepTime = this.sleepTime <= -1 ? this.sleepTime + 1 : this.sleepTime - 1;
			}
			if(! this.isPlantSleeping() && this.sleepTime > 0) {
				this.setPlantSleeping(true);
			}
			if(this.isPlantSleeping() && this.sleepTime <= 0) {
				this.setPlantSleeping(false);
			}
		}
		// spawn sleep particle
		if (level.isClientSide && this.isPlantSleeping() && this.tickCount % 20 == 0) {
			EntityUtil.spawnSpeedParticle(this, ParticleRegister.SLEEP.get(), 0.05F);
		}
		// lock the x and z of plant
		if (this.shouldLockXZ()) {
			if (this.getVehicle() == null) {
				BlockPos pos = this.blockPosition();
				this.setPos(pos.getX() + 0.5, this.getY(), pos.getZ() + 0.5);
			}
		}
		if (!level.isClientSide) {//set float on water.
			if (this.getPlantType().isWaterPlant() && this.isInWater()) {
				Vector3d vec = this.getDeltaMovement();
				double speedY = Math.min(vec.y, 0.05D);
				this.setDeltaMovement(vec.x, speedY, vec.z);
			}
		}
		//update plant level.
		if(!level.isClientSide && this.need_sync_level && this.getExistTick() % PLANT_LEVEL_SYNC_CD == 5) {
			this.getOwnerPlayer().ifPresent(player -> {
				player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent(l -> {
					this.updatePlantLevel(l.getPlayerData().getPlantStats().getPlantLevel(getPlantType()));
				});
			});
		}
	}

	/**
	 * tick when plant is normal state.
	 * (not be frozen or butter and so on).
	 * {@link #aiStep()}
	 */
	protected void normalPlantTick() {
		//tick when plant is place on gold tile, and produce sun.
		if (!this.level.isClientSide && this.getGoldTime() < GoldLeafEntity.GOLD_GEN_CD) {
			Block block = this.level.getBlockState(this.blockPosition().below()).getBlock();
			int lvl = GoldLeafEntity.getBlockGoldLevel(block);
			if (lvl <= 0) {//not gole tile.
				return;
			}
			this.setGoldTime(this.getGoldTime() + 1);
			if (this.getGoldTime() >= GoldLeafEntity.GOLD_GEN_CD) {
				this.setGoldTime(0);
				SunEntity sun = EntityRegister.SUN.get().create(level);
				sun.setAmount(GoldLeafEntity.getGoldGenAmount(lvl));
				EntityUtil.onMobEntityRandomPosSpawn(level, sun, blockPosition(), 2);
				EntityUtil.playSound(this, SoundEvents.EXPERIENCE_ORB_PICKUP);
			}
		}
	}

	/**
	 * check if the plant can stand on the current position.
	 * {@link #plantTick()}
	 */
	protected boolean shouldWilt() {
		if (! this.isImmuneToWeak && this.getVehicle() == null) {//fit check condition and is allowed to wilt.
			if(this.getPlantType().isWaterPlant()) {//on ground, not in water.
				return this.isOnGround() && ! this.isInWater() && ! this.level.getFluidState(blockPosition()).getType().is(FluidTags.WATER);
			}
			if(this.isInWaterOrBubble()) {
				return false;
			}
		    final BlockPos pos = Math.abs(this.getY() - this.blockPosition().getY()) <= 0.01D ? this.blockPosition().below() : this.blockPosition();
		    return ! this.getPlantType().getPlacement().canPlaceOnBlock(level.getBlockState(pos).getBlock());
		}
		return false;
	}
	
	/**
	 * init attributes with plant lvl.
	 */
	public void onSpawnedByPlayer(PlayerEntity player, int lvl) {
		this.setOwnerUUID(player.getUUID());
		this.updatePlantLevel(lvl);
	}
	
	/**
	 * update plant's level.
	 * {@link #plantTick()}
	 */
	public void updatePlantLevel(int lvl) {
		if(this.getPlantLvl() != lvl) {//level changed
		    this.setPlantLvl(lvl);
		    this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(this.getPlantHealth());
		    this.heal(this.getMaxHealth());
		}
	}

	/**
	 * check can plant set target as attackTarget.
	 * {@link EntityUtil#canEntityAttack(Entity, Entity)}
	 */
	public boolean checkCanPlantTarget(Entity target) {
		return EntityUtil.checkCanEntityBeTarget(this, target) && this.canPlantTarget(target);
	}
	
	/**
	 * check can plant attack target.
	 * {@link EntityUtil#canAttackEntity(Entity, Entity)}
	 */
	public boolean checkCanPlantAttack(Entity entity) {
		return EntityUtil.checkCanEntityBeAttack(this, entity) && this.canPlantTarget(entity);
	}
	
	/**
	 * can plant be target by living, often use for zombie's target.
	 * {@link PVZZombieEntity#canZombieTarget(Entity)}
	 */
	public boolean canBeTargetBy(LivingEntity living) {
		return ! this.hasMetal();
	}

	/**
	 * use to extends for specific plants.
	 * {@link #checkCanPlantTarget(Entity)}
	 */
	public boolean canPlantTarget(Entity target) {
		if(target instanceof PVZZombieEntity) {
			return ((PVZZombieEntity) target).canBeTargetBy(this);
		}
		if(target instanceof PVZPlantEntity) {
			return ((PVZPlantEntity) target).canBeTargetBy(this);
		}
		return true;
	}

	/**
	 * use for shroom's sleep ,need check for later coffee bean update.
	 * {@link #plantTick()}
	 */
	protected boolean shouldPlantRegularSleep() {
		if (this.getPlantType().isShroomPlant()) {
			return level.isDay();
		}
		return false;
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if(level.isClientSide) {
			return false;
		}
		if (source instanceof PVZDamageSource) {
			this.invulnerableTime = 0;
		}
		amount = this.pumpkinReduceDamage(source, amount);
		return super.hurt(source, amount);
	}
	
	/**
	 * pumpkin reduce the hurt damage.
	 */
	protected float pumpkinReduceDamage(DamageSource source, float amount) {
		if(source instanceof PVZDamageSource && ((PVZDamageSource) source).isParabola()) {
			return amount;
		}
		if (this.outerPlant.isPresent() && this.outerPlant.get() == PVZPlants.PUMPKIN) {
			if (this.getPumpkinLife() > amount) { // damage pumpkin health first
				this.setPumpkinLife(this.getPumpkinLife() - amount);
				amount = 0;
			} else {
				amount -= this.getPumpkinLife();
				this.setPumpkinLife(0f);
				this.outerPlant = Optional.empty();
			}
		}
		return amount;
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
	 * {@link DoomShroomEntity#startBomb(boolean)}
	 */
	public static void clearLadders(LivingEntity entity, AxisAlignedBB aabb) {
		entity.level.getEntitiesOfClass(PVZPlantEntity.class, aabb, target -> {
			return target.hasMetal() && ! EntityUtil.checkCanEntityBeAttack(entity, target);
		}).forEach(plant -> {
			plant.setMetal(false);
		});
	}

	@Override
	public boolean doHurtTarget(Entity entityIn) {
		entityIn.invulnerableTime = 0;
		return super.doHurtTarget(entityIn);
	}

	/**
	 * lock the movement of plant.
	 * {@link #plantTick()}
	 */
	protected boolean shouldLockXZ() {
		return true;
	}

	@Override
	public void push(Entity entityIn) {
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
					if (!entityIn.isVehicle()) {
						entityIn.push(d0, 0.0D, d1);
					}
				} else {
					if (this instanceof PVZPlantEntity && entityIn instanceof PVZPlantEntity
							&& !EntityUtil.canTargetEntity(this, entityIn)) {
						if (this.tickCount >= entityIn.tickCount) {
							this.hurt(PVZDamageSource.PLANT_WILT, EntityUtil.getMaxHealthDamage(this, 0.5F));
						}
					}
				}
			}
		}
	}

	@Override
	public boolean isPushable() {
		return false;
	}

	@Override
	public boolean isPushedByFluid() {
		return false;
	}

	@Override
	protected void pushEntities() {
		List<LivingEntity> list = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox());
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
				if (! this.is(target) && shouldCollideWithEntity(target)) {// can collide with
					this.doPush(target);
				}
			}
		}
	}

	/**
	 * common plants collide with common plants, mobs who target them, tombstone.
	 * {@link #pushEntities()}
	 */
	protected boolean shouldCollideWithEntity(LivingEntity target) {
		if (target instanceof PVZPlantEntity) {
			if (!this.canCollideWithPlant || !((PVZPlantEntity) target).canCollideWithPlant) {
				return false;
			}
			if (target instanceof SquashEntity) {
				return !EntityUtil.canTargetEntity(this, target);
			}
			if (target instanceof SpikeWeedEntity) {
				return !EntityUtil.canTargetEntity(this, target);
			}
			return true;
		}
		if (target instanceof MobEntity) {
			if (((MobEntity) target).getTarget() == this) {
				return true;
			}
			if (target instanceof TombStoneEntity) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		return this.isPlantImmuneTo(source) && source != DamageSource.OUT_OF_WORLD && !source.isCreativePlayer() && ! source.equals(PVZDamageSource.PLANT_WILT);
	}

	/**
	 * {@link #isInvulnerableTo(DamageSource)}
	 */
	public boolean isPlantImmuneTo(DamageSource source) {
		return this.isPlantInSuperMode();
	}

	@Override
	public boolean canBreatheUnderwater() {
		return this.getPlantType().isWaterPlant();
	}
	
	/**
	 * {@link EntityUtil#canHelpAttackOthers(Entity)}
	 */
	public boolean canHelpAttack() {
		return this.canHelpAttack;
	}

	/**
	 * get plant max health.
	 */
	public float getPlantHealth() {
		int lvl = this.getPlantLvl();
		if (lvl <= 14) {
			return 27.5f + 2.5f * lvl;
		}
		if (lvl <= 20) {
			return 5 * lvl - 10;
		}
		return 100;
	}
	
	/**
	 * on charmed by entity.
	 */
	@Override
	public void onCharmedBy(LivingEntity entity) {
		if(! this.canBeCharmed()) {
			return ;
		}
		this.setCharmed(! this.isCharmed());
	}
	
	/**
	 * use to start plant super mode.
	 */
	public void startSuperMode(boolean first) {
		this.setSuperTime(this.getSuperTimeLength());
		this.heal(this.getMaxHealth());
		if (first) {
			PlayerEntity player = EntityUtil.getEntityOwner(level, this);
			if (player != null && player instanceof ServerPlayerEntity) {
				PlantSuperTrigger.INSTANCE.trigger((ServerPlayerEntity) player, this);
				PlayerUtil.addPlantXp(player, this.getPlantType(), 2);
			}
			this.outerPlant.ifPresent(plant -> {
				if (plant == PVZPlants.PUMPKIN) {
					this.setPumpkinLife(PlantUtil.PUMPKIN_LIFE + PlantUtil.PUMPKIN_SUPER_LIFE);
				}
			});
		}
	}
	
	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return ! this.getOwnerUUID().isPresent();
	}
	
	public void removeOuterPlant() {
		this.outerPlant = Optional.empty();
		this.setPumpkinLife(0);
		this.outerSunCost = 0;
		if (this.hasMetal()) {
			this.decreaseMetal();
		}
	}

	@Override
	public ActionResultType interactAt(PlayerEntity player, Vector3d vec3d, Hand hand) {
		if (!level.isClientSide) {
//			ItemStack stack = player.getItemInHand(hand);
//			if (stack.getItem() instanceof PlantCardItem) {// plant card right click plant entity
//				PlantCardItem item = (PlantCardItem) stack.getItem();
//				if (!this.getPlantType().isBigPlant && item.plantType == Plants.PUMPKIN) { // use pumpkin card on
//																								// plant entity
//					if (this.outerPlant.isPresent() && this.outerPlant.get() == Plants.PUMPKIN) {
//						if (this.getPumpkinLife() < PlantUtil.PUMPKIN_LIFE) { // heal pumpkin
//							PlantCardItem.checkSunAndHealPlant(player, this, item, stack);
//						}
//					} else { // place pumpkin on plant entity
//						PlantCardItem.checkSunAndOuterPlant(player, this, item, stack);
//					}
//				} else if (!this.getPlantType().isBigPlant && item instanceof ImitaterCardItem
//						&& ((ImitaterCardItem) item).isPlantTypeEqual(stack, Plants.PUMPKIN)) {
//					if (this.outerPlant.isPresent() && this.outerPlant.get() == Plants.PUMPKIN) {
//						if (this.getPumpkinLife() < PlantUtil.PUMPKIN_LIFE) { // heal pumpkin
//							PlantCardItem.checkSunAndHealPlant(player, this, item, stack);
//						}
//					} else { // place pumpkin on plant entity
//						PlantCardItem.checkSunAndOuterPlant(player, this, item, stack);
//					}
//				} else if (item.plantType == PVZPlants.COFFEE_BEAN) { // place coffee bean on plant entity
//					PlantCardItem.checkSunAndSummonPlant(player, stack, item, blockPosition(), (plantEntity) -> {
//						plantEntity.startRiding(this);
//					});
//				} else if (item instanceof ImitaterCardItem
//						&& ((ImitaterCardItem) item).isPlantTypeEqual(stack, PVZPlants.COFFEE_BEAN)) {
//					ImitaterCardItem.checkSunAndSummonImitater(player, stack, item, blockPosition(), (imitater) -> {
//						imitater.startRiding(this);
//					});
//				} else if (this.getUpgradePlantType() == item.plantType) { // place upgrade plant entity on base plant
//																			// entity
//					PlantCardItem.checkSunAndSummonPlant(player, stack, item, blockPosition(), (plantEntity) -> {
//						this.onPlantUpgrade(plantEntity);
//					});
//				} else if (item instanceof ImitaterCardItem
//						&& ((ImitaterCardItem) item).isPlantTypeEqual(stack, getUpgradePlantType())) {
//					ImitaterCardItem.checkSunAndSummonImitater(player, stack, item, blockPosition(), (imitater) -> {
//						imitater.targetPlantEntity = Optional.of(this);
//					});
//				}
//			}
		}
		return ActionResultType.SUCCESS;
	}

	protected void onPlantUpgrade(PVZPlantEntity plantEntity) {
		// keep old plant's outer plant, such as pumpkin.
			plantEntity.setPumpkinLife(this.getPumpkinLife());
			this.getOuterPlantType().ifPresent((plantType) -> {
				plantEntity.setOuterPlantType(plantType);
			});
			plantEntity.outerSunCost = this.outerSunCost;
		// add sun cost to new plant.
		plantEntity.plantSunCost += this.plantSunCost;
		// keep sleep of plant
		plantEntity.sleepTime = this.sleepTime;
		// remove old plant itself
		this.remove();
	}

	/* misc get */

	public boolean canBeButter() {
		return this.canBeButter;
	}

	public boolean canBeCharmed() {
		return this.canBeCharm;
	}

	public boolean canBeFrozen() {
		return this.canBeFrozen && !this.isInWaterOrBubble() && !this.isInLava();
	}

	public boolean canBeCold() {
		return this.canBeCold;
	}
	
	public boolean isPlantInSuperMode() {
		return this.getSuperTime() > 0;
	}

	private boolean hasSuperMode() {
		return this.getSuperTimeLength() > 0;
	}

	public boolean isPlantInBoost() {
		return this.getBoostTime() > 0;
	}

	/**
	 * get plant's defence life.
	 * {@link #getCurrentHealth()}
	 * {@link #getCurrentMaxHealth()}
	 */
	protected float getCurrentDefenceHealth() {
		return this.getPumpkinLife();
	}
	
	/**
	 * the total health of plants include defence health. 
	 * {@link EntityUtil#getCurrentHealth(net.minecraft.entity.LivingEntity)}
	 */
	public float getCurrentHealth() {
		return this.getHealth() + this.getCurrentDefenceHealth();
	}

	/**
	 * the total max health of plants include defence health. 
	 * {@link EntityUtil#getCurrentMaxHealth(net.minecraft.entity.LivingEntity)}
	 */
	public float getCurrentMaxHealth() {
		return this.getMaxHealth() + this.getCurrentDefenceHealth();
	}

	protected boolean isPlantInStage(int stage) {
		int lvl = this.getPlantLvl();
		if (stage == 1)
			return lvl <= 6;
		if (stage == 2)
			return 7 <= lvl && lvl <= 13;
		if (stage == 3)
			return 14 <= lvl && lvl <= 20;
		return false;
	}
	
	public int getThreeStage(int a, int b, int c) {
		return this.isPlantInStage(1) ? a : this.isPlantInStage(2) ? b : c;
	}
	
	public int getAverageProgress(int a, int b) {
		return PlantUtil.getPlantAverageProgress(this, a, b);
	}
	
	public float getAverageProgress(float a, float b) {
		return PlantUtil.getPlantAverageProgress(this, a, b);
	}
	
	/**
	 * check can start super mode currently.
	 */
	public boolean canStartSuperMode() {
		return this.canPlantNormalUpdate() && this.hasSuperMode() && !this.isPlantInSuperMode();
	}
	
    /**
     * {@link #plantTick()}
     */
	public Optional<PlayerEntity> getOwnerPlayer() {
		if(! this.hasOwner()) {
			return Optional.empty();
		}
		if(! EntityUtil.isEntityValid(this.ownerPlayer)) {
			this.ownerPlayer = this.level.getPlayerByUUID(this.getOwnerUUID().get());
		}
		return Optional.ofNullable(this.ownerPlayer);
	}
	
	@Override
	public PVZGroupType getEntityGroupType() {
		return this.isCharmed() ? PVZGroupType.ZOMBIES : PVZGroupType.PLANTS;
	}
	
	public boolean hasOwner() {
		return this.getOwnerUUID().isPresent();
	}
	
	/* data */
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("plant_super_time", this.getSuperTime());
		compound.putInt("plant_lvl", this.getPlantLvl());
		if (this.getOwnerUUID().isPresent()) {
			compound.putUUID("OwnerUUID", this.getOwnerUUID().get());
		}
		compound.putInt("plant_attack_time", this.getAttackTime());
		compound.putInt("plant_gold_time", this.getGoldTime());
		compound.putInt("plant_boost_time", this.getBoostTime());
		compound.putInt("plant_sleep_time", this.sleepTime);
		this.outerPlant.ifPresent((plant) -> {
			compound.putString("outer_plant_type", plant.getIdentity());
		});
		compound.putFloat("pumpkin_life", this.getPumpkinLife());
		compound.putInt("plant_sun_cost", this.plantSunCost);
		compound.putInt("outer_sun_cost", this.outerSunCost);
		compound.putBoolean("immune_to_weak", this.isImmuneToWeak);
		compound.putInt("plant_state", this.getPlantState());
		compound.putInt("plant_exist_tick", this.getExistTick());
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("plant_super_time")) {
			this.setSuperTime(compound.getInt("plant_super_time"));
		}
		if (compound.contains("plant_lvl")) {
			this.setPlantLvl(compound.getInt("plant_lvl"));
		}
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
		if (compound.contains("plant_attack_time")) {
			this.setAttackTime(compound.getInt("plant_attack_time"));
		}
		if (compound.contains("plant_gold_time")) {
			this.setGoldTime(compound.getInt("plant_gold_time"));
		}
		if (compound.contains("plant_boost_time")) {
			this.setBoostTime(compound.getInt("plant_boost_time"));
		}
		if(compound.contains("plant_sleep_time")) {
			this.sleepTime = compound.getInt("plant_sleep_time");
		}
		if (compound.contains("pumpkin_life")) {
			this.setPumpkinLife(compound.getFloat("pumpkin_life"));
		}
		if (compound.contains("outer_plant_type")) {
			final String string = compound.getString("outer_plant_type");
		    this.outerPlant = PlantType.getPlantByName(string);
		}
		if (compound.contains("plant_sun_cost")) {
			this.plantSunCost = compound.getInt("plant_sun_cost");
		}
		if (compound.contains("outer_sun_cost")) {
			this.outerSunCost = compound.getInt("outer_sun_cost");
		}
		if (compound.contains("immune_to_weak")) {
			this.isImmuneToWeak = compound.getBoolean("immune_to_weak");
		}
		if (compound.contains("plant_state")) {
			this.setPlantState(compound.getInt("plant_state"));
		}
		if(compound.contains("plant_exist_tick")) {
			this.setExistTick(compound.getInt("plant_exist_tick"));
		}
	}

	/* getter setter */
	
	public void setOuterPlantType(PlantType p) {
		this.outerPlant = Optional.of(p);
	}

	public Optional<PlantType> getOuterPlantType() {
		return this.outerPlant;
	}

	public void setImmunneToWeak(boolean is) {
		this.isImmuneToWeak = is;
	}

	public int getBoostTime() {
		return entityData.get(BOOST_TIME);
	}

	public void setBoostTime(int time) {
		entityData.set(BOOST_TIME, time);
	}

	public int getGoldTime() {
		return entityData.get(GOLD_TIME);
	}

	public void setGoldTime(int cd) {
		entityData.set(GOLD_TIME, cd);
	}

	public int getAttackTime() {
		return entityData.get(ATTACK_TIME);
	}

	public void setAttackTime(int cd) {
		entityData.set(ATTACK_TIME, cd);
	}

	public void setPlantLvl(int lvl) {
		entityData.set(PLANT_LVL, lvl);
	}

	public int getPlantLvl() {
		return entityData.get(PLANT_LVL);
	}

	@Override
	public Optional<UUID> getOwnerUUID() {
		return entityData.get(OWNER_UUID);
	}

	public void setOwnerUUID(UUID uuid) {
		this.entityData.set(OWNER_UUID, Optional.ofNullable(uuid));
	}

	public void setSuperTime(int time) {
		entityData.set(SUPER_TIME, time);
	}

	public int getSuperTime() {
		return entityData.get(SUPER_TIME);
	}

	public float getPumpkinLife() {
		return this.entityData.get(PUMPKIN_LIFE);
	}

	public void setPumpkinLife(float life) {
		this.entityData.set(PUMPKIN_LIFE, life);
	}
	
	public int getExistTick() {
		return this.entityData.get(EXIST_TICK);
	}

	public void setExistTick(int tick) {
		this.entityData.set(EXIST_TICK, tick);
	}

	public int getPlantState() {
		return this.entityData.get(PLANT_STATES);
	}

	public void setPlantState(int state) {
		this.entityData.set(PLANT_STATES, state);
	}

	@Override
	public boolean hasMetal() {
		return AlgorithmUtil.BitOperator.hasBitOne(this.getPlantState(), LADDER_FLAG);
	}

	public void setMetal(boolean flag) {
		this.setPlantState(AlgorithmUtil.BitOperator.setBit(this.getPlantState(), LADDER_FLAG, flag));
	}
	
	@Override
	public boolean isCharmed() {
		return AlgorithmUtil.BitOperator.hasBitOne(this.getPlantState(), CHARM_FLAG);
	}

	public void setCharmed(boolean flag) {
		this.setPlantState(AlgorithmUtil.BitOperator.setBit(this.getPlantState(), CHARM_FLAG, flag));
	}
	
	public boolean isPlantSleeping() {
		return AlgorithmUtil.BitOperator.hasBitOne(this.getPlantState(), SLEEP_FLAG);
	}
	
	public void setPlantSleeping(boolean flag) {
		this.setPlantState(AlgorithmUtil.BitOperator.setBit(this.getPlantState(), SLEEP_FLAG, flag));
	}
	
	@Override
	public void increaseMetal() {
		this.setMetal(true);
	}

	@Override
	public void decreaseMetal() {
		this.setMetal(false);
	}

	@Override
	public MetalTypes getMetalType() {
		return MetalTypes.LADDER;
	}

	/* sound */
	
	protected SoundEvent getSpawnSound() {
		return this.getPlantType().isWaterPlant() ? SoundRegister.PLANT_IN_WATER.get()
				: SoundRegister.PLANT_ON_GROUND.get();
	}
	
	/* plant type */
	
	public int getCoolDownTime() {
		return this.getPlantType().getCD().getCD(this.getPlantLvl());
	}

	public EssenceType getPlantEssenceType() {
		return this.getPlantType().getEssence();
	}

	public CardRank getPlantRank() {
		return this.getPlantType().getRank();
	}
	
	/**
	 * match entity with plant type.
	 */
	public abstract PlantType getPlantType();
	
	public abstract int getSuperTimeLength();

}
