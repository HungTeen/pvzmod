package com.hungteen.pvz.common.entity.plant;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nullable;

import com.hungteen.pvz.api.interfaces.IPVZPlant;
import com.hungteen.pvz.common.advancement.trigger.PlantSuperTrigger;
import com.hungteen.pvz.common.entity.drop.SunEntity;
import com.hungteen.pvz.common.entity.goal.PVZLookRandomlyGoal;
import com.hungteen.pvz.common.entity.plant.enforce.SquashEntity;
import com.hungteen.pvz.common.entity.plant.light.GoldLeafEntity;
import com.hungteen.pvz.common.entity.plant.spear.SpikeWeedEntity;
import com.hungteen.pvz.common.entity.zombie.grassnight.TombStoneEntity;
import com.hungteen.pvz.common.entity.zombie.poolnight.BalloonZombieEntity;
import com.hungteen.pvz.common.entity.zombie.poolnight.DiggerZombieEntity;
import com.hungteen.pvz.common.entity.zombie.roof.BungeeZombieEntity;
import com.hungteen.pvz.common.entity.zombie.roof.BungeeZombieEntity.BungeeStates;
import com.hungteen.pvz.common.item.card.ImitaterCardItem;
import com.hungteen.pvz.common.item.card.PlantCardItem;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.common.misc.damage.PVZDamageType;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.ParticleRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.AlgorithmUtil;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Essences;
import com.hungteen.pvz.utils.enums.MetalTypes;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.Ranks;
import com.hungteen.pvz.utils.interfaces.IHasMetal;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
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
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.GameRules;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

public abstract class PVZPlantEntity extends CreatureEntity implements IPVZPlant, IHasMetal {

	private static final DataParameter<Integer> SUPER_TIME = EntityDataManager.defineId(PVZPlantEntity.class,
			DataSerializers.INT);
	private static final DataParameter<Integer> PLANT_LVL = EntityDataManager.defineId(PVZPlantEntity.class,
			DataSerializers.INT);
	private static final DataParameter<Optional<UUID>> OWNER_UUID = EntityDataManager.defineId(PVZPlantEntity.class,
			DataSerializers.OPTIONAL_UUID);
	private static final DataParameter<Integer> PLANT_STATES = EntityDataManager.defineId(PVZPlantEntity.class,
			DataSerializers.INT);
	private static final DataParameter<Integer> ATTACK_TIME = EntityDataManager.defineId(PVZPlantEntity.class,
			DataSerializers.INT);
	private static final DataParameter<Integer> GOLD_TIME = EntityDataManager.defineId(PVZPlantEntity.class,
			DataSerializers.INT);
	private static final DataParameter<Integer> BOOST_TIME = EntityDataManager.defineId(PVZPlantEntity.class,
			DataSerializers.INT);
	private static final DataParameter<Integer> SLEEP_TIME = EntityDataManager.defineId(PVZPlantEntity.class,
			DataSerializers.INT);
	private static final DataParameter<Integer> LIVE_TICK = EntityDataManager.defineId(PVZPlantEntity.class,
			DataSerializers.INT);
	private static final DataParameter<Float> PUMPKIN_LIFE = EntityDataManager.defineId(PVZPlantEntity.class,
			DataSerializers.FLOAT);
	// plant states flag
	protected static final int LADDER_FLAG = 0;
	protected static final int CHARM_FLAG = 1;
	protected static final int PLANT_LEVEL_FLAG_LEN = 7;// plant level range in (1, 128)
	protected int weakTime = 0;//weak tick
	private final int weakCD = 10;
	protected boolean isImmuneToWeak = false;//can plant survive in the no dirt block
	protected Optional<Plants> outerPlant = Optional.empty();//outer plant of current plant, like pumpkin.
	public boolean canCollideWithPlant = true;
	protected boolean canBeCharmed = true;
	public int plantSunCost = 0;
	public int outerSunCost = 0;

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
		entityData.define(SLEEP_TIME, 0);
		entityData.define(LIVE_TICK, 0);
		entityData.define(PUMPKIN_LIFE, 0f);
		entityData.define(PLANT_STATES, 0);
	}

	public static AttributeModifierMap createPlantAttributes() {
		return LivingEntity.createLivingAttributes().add(Attributes.MAX_HEALTH, 30).add(Attributes.FOLLOW_RANGE, 40.0D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 1).add(Attributes.MOVEMENT_SPEED, 0).build();
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(2, new PVZLookRandomlyGoal(this));
	}

	/**
	 * init attributes with plant lvl
	 */
	public void onSpawnedByPlayer(PlayerEntity player, int lvl) {
		this.setPlantLvl(lvl);
		this.setOwnerUUID(player.getUUID());
		this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(this.getPlantHealth());
		this.heal(this.getMaxHealth());
	}

	@Override
	public float getPlantHealth() {
		int lvl = this.getPlantLvl();
		if (lvl <= 14) {
			return 27.5f + 2.5f * lvl;
		}
		return 5 * lvl - 10;
	}

	@Override
	public void aiStep() {
		super.aiStep();
		if (!this.isAlive()) {
			return;
		}
		this.plantBaseTick();
		if (this.canPlantNormalUpdate()) {
			this.normalPlantTick();
		}
	}

	public boolean canPlantNormalUpdate() {
		if (this.getVehicle() instanceof BungeeZombieEntity || this.hasMetal())
			return false;
		return !this.isPlantSleeping() && !EntityUtil.isEntityFrozen(this) && !EntityUtil.isEntityButter(this);
	}

	/**
	 * base tick for normal plant
	 */
	protected void plantBaseTick() {
		// when plant stand on wrong block
		if (!this.level.isClientSide && !this.isImmuneToWeak && this.getVehicle() == null) {
			if (this.checkNormalPlantWeak() && this.weakTime <= 0) {
				this.weakTime = this.weakCD;
				this.hurt(PVZDamageSource.causeWeakDamage(this, this), this.getMaxHealth() / 5);
			}
			this.weakTime = Math.max(0, this.weakTime - 1);
		}
		// super mode or boost time or sleep time
		if (!this.level.isClientSide) {
			this.setSuperTime(Math.max(0, this.getSuperTime() - 1));
			this.setBoostTime(Math.max(0, this.getBoostTime() - 1));
			if (this.shouldPlantRegularSleep()) {
				this.setSleepTime(Math.min(1, this.getSleepTime() + 1));
			} else {
				if(this.getSleepTime() > 0) this.setSleepTime(0);
				else this.setSleepTime(Math.min(0, this.getSleepTime() + 1));
			}
		}
		// spawn sleep particle
		if (level.isClientSide && this.isPlantSleeping() && this.tickCount % 20 == 0) {
			level.addParticle(ParticleRegister.SLEEP.get(), this.getX(), this.getY() + this.getEyeHeight(), this.getZ(),
					0.05, 0.05, 0.05);
		}
		// max live tick
//		if (!level.isClientSide) {
//			this.setLiveTick(this.getLiveTick() + 1);
//			if (this.getLiveTick() >= this.getMaxLiveTick()) {// it's time to disappear
//				this.remove();
//			}
//		}
		// lock the x and z of plant
		if (this.shouldLockXZ()) {
			if (this.getVehicle() == null) {
				BlockPos pos = this.blockPosition();
				this.setPos(pos.getX() + 0.5, this.getY(), pos.getZ() + 0.5);
			}
		}
		if (!level.isClientSide) {
			if (this.getPlantEnumName().isWaterPlant && this.isInWater()) {
				Vector3d vec = this.getDeltaMovement();
				double speedY = Math.min(vec.y, 0.05D);
				this.setDeltaMovement(vec.x, speedY, vec.z);
			}
		}
	}

	/**
	 * tick for normal plant
	 */
	protected void normalPlantTick() {
		if (!this.level.isClientSide && this.getGoldTime() < GoldLeafEntity.GOLD_GEN_CD) {
			Block block = this.level.getBlockState(this.blockPosition().below()).getBlock();
			int lvl = GoldLeafEntity.getBlockGoldLevel(block);
			if (lvl <= 0) return;
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

	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		if (!worldIn.isClientSide()) {
			EntityUtil.playSound(this, this.getSpawnSound());
			this.updateAttributes();
			this.heal(this.getMaxHealth());
		}
		return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}
	
	protected void updateAttributes() {
		this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(this.getPlantHealth());
	}

	/**
	 * check if the plant can stand on the current position
	 */
	protected boolean checkNormalPlantWeak() {
		if (this.isImmuneToWeak || this.getVehicle() != null) return false;
		if (this.getPlantEnumName().isWaterPlant) {
			return this.onGround && !this.isInWater()
					&& level.getBlockState(blockPosition()).getBlock() != Blocks.WATER;
		} else {
			if (!this.onGround)
				return false;
			if (this.isInWater())
				return true;
			double y1 = this.getY();
			double y2 = MathHelper.floor(y1);
			BlockPos pos = (Math.abs(y1 - y2) <= 0.01D ? this.blockPosition().below() : this.blockPosition());
			Block current = level.getBlockState(pos).getBlock();
			return !PlantUtil.getPlantSuitBlock(getPlantEnumName()).stream().anyMatch((block) -> {
				return block == current;
			});
		}
	}

	/**
	 * some zombie can attack but can not target. such as digger zombie.
	 */
	public boolean checkCanPlantTarget(LivingEntity entity) {
		return EntityUtil.checkCanEntityAttack(this, entity) && this.canPlantTarget(entity);
	}

	/**
	 * use to extends for specific plants.
	 */
	protected boolean canPlantTarget(LivingEntity entity) {
		if (entity instanceof DiggerZombieEntity) {
			return ((DiggerZombieEntity) entity).getAttackTime() == DiggerZombieEntity.MAX_OUT_TIME;
		} else if (entity instanceof BalloonZombieEntity) {
			return !((BalloonZombieEntity) entity).hasBalloon();
		} else if (entity instanceof BungeeZombieEntity) {
			return ((BungeeZombieEntity) entity).getBungeeState() == BungeeStates.CATCH;
		}
		return true;
	}

	/**
	 * use for shroom's sleep ,need check for later coffee bean update
	 */
	protected boolean shouldPlantRegularSleep() {
		if (this.getPlantEnumName().isShroomPlant) {
			return level.isDay();
		}
		return false;
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (source instanceof PVZDamageSource) {
			this.invulnerableTime = 0;
		}
		amount = this.pumpkinReduceDamage(source, amount);
		return super.hurt(source, amount);
	}

	@Override
	public boolean doHurtTarget(Entity entityIn) {
		entityIn.invulnerableTime = 0;
		return super.doHurtTarget(entityIn);
	}

	/**
	 * lock the movement of plant
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
							&& !EntityUtil.checkCanEntityAttack(this, entityIn)) {
						if (this.tickCount >= entityIn.tickCount) {
							this.hurt(DamageSource.CRAMMING, 10);
						}
//                	    entityIn.attackEntityFrom(DamageSource.CRAMMING, 10);
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
		return !this.getPlantEnumName().isWaterPlant;
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
				if (target != this && shouldCollideWithEntity(target)) {// can collide with
					this.doPush(target);
				}
			}
		}
	}

	/**
	 * common plants collide with common plants, mobs who target them,tombstone.
	 */
	protected boolean shouldCollideWithEntity(LivingEntity target) {
		if (target instanceof PVZPlantEntity) {
			if (!this.canCollideWithPlant || !((PVZPlantEntity) target).canCollideWithPlant) {
				return false;
			}
			if (target instanceof SquashEntity) {
				return !EntityUtil.checkCanEntityAttack(this, target);
			}
			if (target instanceof SpikeWeedEntity) {
				return !EntityUtil.checkCanEntityAttack(this, target);
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
		return this.isPlantImmuneTo(source) && source != DamageSource.OUT_OF_WORLD && !source.isCreativePlayer();
	}

	/**
	 * a function that replace isinvulnerable
	 */
	public boolean isPlantImmuneTo(DamageSource source) {
		if (this.isPlantInSuperMode())
			return true;
		return false;
	}

	@Override
	public boolean canBreatheUnderwater() {
		return this.getPlantEnumName().isWaterPlant;
	}

	@Nullable
	public Plants getUpgradePlantType() {
		return null;
	}

	public void onPlantBeCharmed() {
		if (!this.canBeCharmed)
			return;
		this.setCharmed(!this.isCharmed());
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("plant_weak_time", this.weakTime);
		compound.putInt("plant_super_time", this.getSuperTime());
		compound.putInt("plant_lvl", this.getPlantLvl());
		if (this.getOwnerUUID().isPresent()) {
			compound.putUUID("OwnerUUID", this.getOwnerUUID().get());
		}
		compound.putInt("plant_attack_time", this.getAttackTime());
		compound.putInt("plant_gold_time", this.getGoldTime());
		compound.putInt("plant_boost_time", this.getBoostTime());
		compound.putBoolean("is_plant_charmed", this.isCharmed());
		compound.putInt("plant_sleep_time", this.getSleepTime());
		compound.putInt("plant_live_tick", this.getLiveTick());
		this.outerPlant.ifPresent((plant) -> {
			compound.putInt("outer_plant_type", plant.ordinal());
		});
		compound.putFloat("pumpkin_life", this.getPumpkinLife());
		compound.putInt("plant_sun_cost", this.plantSunCost);
		compound.putInt("outer_sun_cost", this.outerSunCost);
		compound.putBoolean("immune_to_weak", this.isImmuneToWeak);
		compound.putInt("plant_state", this.getPlantState());
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("plant_weak_time")) {
			this.weakTime = compound.getInt("plant_weak_time");
		}
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
		if (compound.contains("is_plant_charmed")) {
			this.setCharmed(compound.getBoolean("is_plant_charmed"));
		}
		if (compound.contains("plant_sleep_time")) {
			this.setSleepTime(compound.getInt("plant_sleep_time"));
		}
		if (compound.contains("plant_live_tick")) {
			this.setLiveTick(compound.getInt("plant_live_tick"));
		}
		if (compound.contains("pumpkin_life")) {
			this.setPumpkinLife(compound.getFloat("pumpkin_life"));
		}
		if (compound.contains("outer_plant_type")) {
			this.outerPlant = Optional.of(Plants.values()[compound.getInt("outer_plant_type")]);
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
	}

	/**
	 * use to start plant super mode
	 */
	public void startSuperMode(boolean first) {
		this.setSuperTime(this.getSuperTimeLength());
		this.heal(this.getMaxHealth());
		this.setLiveTick(0);
		if (first) {
			PlayerEntity player = EntityUtil.getEntityOwner(level, this);
			if (player != null && player instanceof ServerPlayerEntity) {
				PlantSuperTrigger.INSTANCE.trigger((ServerPlayerEntity) player, this);
				PlayerUtil.addPlantXp(player, this.getPlantEnumName(), 2);
			}
			this.outerPlant.ifPresent((plant) -> {
				if (plant == Plants.PUMPKIN) {
					this.setPumpkinLife(PlantUtil.PUMPKIN_LIFE + PlantUtil.PUMPKIN_SUPER_LIFE);
				}
			});
		}
	}

	/**
	 * pumpkin reduce the hurt damage
	 */
	protected float pumpkinReduceDamage(DamageSource source, float amount) {
		if (!level.isClientSide) {
			if (this.outerPlant.isPresent() && this.outerPlant.get() == Plants.PUMPKIN) {
				if (this.getPumpkinLife() > amount) { // damage pumpkin health first
					this.setPumpkinLife(this.getPumpkinLife() - amount);
					amount = 0;
				} else {
					amount -= this.getPumpkinLife();
					this.setPumpkinLife(0f);
					this.outerPlant = Optional.empty();
				}
			}
		}
		return amount;
	}

	public boolean isPlantInSuperMode() {
		return this.getSuperTime() > 0;
	}

	/**
	 * check can start super mode currently
	 */
	public boolean canStartSuperMode() {
		return !this.isPlantSleeping() && this.hasSuperMode() && !this.isPlantInSuperMode();
	}

	private boolean hasSuperMode() {
		return this.getSuperTimeLength() > 0;
	}

	public boolean isPlantInBoost() {
		return this.getBoostTime() > 0;
	}

	public int getCoolDownTime() {
		return PlantUtil.getPlantCoolDownTime(getPlantEnumName(), getPlantLvl());
	}

	public int getSunCost() {
		return PlantUtil.getPlantSunCost(getPlantEnumName());
	}

	@Override
	public Essences getPlantEssenceType() {
		return PlantUtil.getPlantEssenceType(getPlantEnumName());
	}

	@Override
	public Ranks getPlantRank(Plants plant) {
		return PlantUtil.getPlantRankByName(plant);
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return false;
	}

	/**
	 * is Plant Sleeping
	 */
	public boolean isPlantSleeping() {
		return this.getSleepTime() > 0;
	}

	public void setOuterPlantType(Plants p) {
		this.outerPlant = Optional.of(p);
	}

	public Optional<Plants> getOuterPlantType() {
		return this.outerPlant;
	}

	public void setImmunneToWeak(boolean is) {
		this.isImmuneToWeak = is;
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
			ItemStack stack = player.getItemInHand(hand);
			if (stack.getItem() instanceof PlantCardItem) {// plant card right click plant entity
				PlantCardItem item = (PlantCardItem) stack.getItem();
				if (!this.getPlantEnumName().isBigPlant && item.plantType == Plants.PUMPKIN) { // use pumpkin card on
																								// plant entity
					if (this.outerPlant.isPresent() && this.outerPlant.get() == Plants.PUMPKIN) {
						if (this.getPumpkinLife() < PlantUtil.PUMPKIN_LIFE) { // heal pumpkin
							PlantCardItem.checkSunAndHealPlant(player, this, item, stack);
						}
					} else { // place pumpkin on plant entity
						PlantCardItem.checkSunAndOuterPlant(player, this, item, stack);
					}
				} else if (!this.getPlantEnumName().isBigPlant && item instanceof ImitaterCardItem
						&& ((ImitaterCardItem) item).isPlantTypeEqual(stack, Plants.PUMPKIN)) {
					if (this.outerPlant.isPresent() && this.outerPlant.get() == Plants.PUMPKIN) {
						if (this.getPumpkinLife() < PlantUtil.PUMPKIN_LIFE) { // heal pumpkin
							PlantCardItem.checkSunAndHealPlant(player, this, item, stack);
						}
					} else { // place pumpkin on plant entity
						PlantCardItem.checkSunAndOuterPlant(player, this, item, stack);
					}
				} else if (item.plantType == Plants.COFFEE_BEAN) { // place coffee bean on plant entity
					PlantCardItem.checkSunAndSummonPlant(player, stack, item, blockPosition(), (plantEntity) -> {
						plantEntity.startRiding(this);
					});
				} else if (item instanceof ImitaterCardItem
						&& ((ImitaterCardItem) item).isPlantTypeEqual(stack, Plants.COFFEE_BEAN)) {
					ImitaterCardItem.checkSunAndSummonImitater(player, stack, item, blockPosition(), (imitater) -> {
						imitater.startRiding(this);
					});
				} else if (this.getUpgradePlantType() == item.plantType) { // place upgrade plant entity on base plant
																			// entity
					PlantCardItem.checkSunAndSummonPlant(player, stack, item, blockPosition(), (plantEntity) -> {
						this.onPlantUpgrade(plantEntity);
					});
				} else if (item instanceof ImitaterCardItem
						&& ((ImitaterCardItem) item).isPlantTypeEqual(stack, getUpgradePlantType())) {
					ImitaterCardItem.checkSunAndSummonImitater(player, stack, item, blockPosition(), (imitater) -> {
						imitater.targetPlantEntity = Optional.of(this);
					});
				}
			}
		}
		return ActionResultType.SUCCESS;
	}

	protected void onPlantUpgrade(PVZPlantEntity plantEntity) {
		// keep old plant's outer plant, such as pumpkin.
		if (!plantEntity.getPlantEnumName().isBigPlant) {
			plantEntity.setPumpkinLife(this.getPumpkinLife());
			this.getOuterPlantType().ifPresent((plantType) -> {
				plantEntity.setOuterPlantType(plantType);
			});
			plantEntity.outerSunCost = this.outerSunCost;
		}
		// add sun cost to new plant.
		plantEntity.plantSunCost += this.plantSunCost;
		// keep sleep of plant
		plantEntity.setSleepTime(this.getSleepTime());
		// remove old plant itself
		this.remove();
	}

	public float getCurrentHealth() {
		return this.getHealth() + this.getPumpkinLife();
	}

	public float getCurrentMaxHealth() {
		return this.getMaxHealth() + this.getPumpkinLife();
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

	public int getSleepTime() {
		return entityData.get(SLEEP_TIME);
	}

	public void setSleepTime(int cd) {
		entityData.set(SLEEP_TIME, cd);
	}

	public void setPlantLvl(int lvl) {
		entityData.set(PLANT_LVL, lvl);
	}

	public int getPlantLvl() {
		return entityData.get(PLANT_LVL);
	}

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

	public int getLiveTick() {
		return this.entityData.get(LIVE_TICK);
	}

	public void setLiveTick(int tick) {
		this.entityData.set(LIVE_TICK, tick);
	}

	public float getPumpkinLife() {
		return this.entityData.get(PUMPKIN_LIFE);
	}

	public void setPumpkinLife(float life) {
		this.entityData.set(PUMPKIN_LIFE, life);
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
	
	public boolean isCharmed() {
		return AlgorithmUtil.BitOperator.hasBitOne(this.getPlantState(), CHARM_FLAG);
	}

	public void setCharmed(boolean flag) {
		this.setPlantState(AlgorithmUtil.BitOperator.setBit(this.getPlantState(), CHARM_FLAG, flag));
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

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		if (damageSourceIn instanceof PVZDamageSource
				&& ((PVZDamageSource) damageSourceIn).getPVZDamageType() == PVZDamageType.EAT)
			return SoundRegister.PLANT_HURT.get();
		return super.getHurtSound(damageSourceIn);
	}

	protected SoundEvent getSpawnSound() {
		return this.getPlantEnumName().isWaterPlant ? SoundRegister.PLANT_IN_WATER.get()
				: SoundRegister.PLANT_ON_GROUND.get();
	}

}
