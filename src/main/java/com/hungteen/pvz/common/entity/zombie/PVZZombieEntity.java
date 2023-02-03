package com.hungteen.pvz.common.entity.zombie;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.api.enums.BodyType;
import com.hungteen.pvz.api.enums.PVZGroupType;
import com.hungteen.pvz.api.paz.IZombieEntity;
import com.hungteen.pvz.api.types.IPAZType;
import com.hungteen.pvz.api.types.IZombieType;
import com.hungteen.pvz.client.particle.ParticleUtil;
import com.hungteen.pvz.common.block.BlockRegister;
import com.hungteen.pvz.common.entity.AbstractPAZEntity;
import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.common.entity.ai.goal.PVZLookRandomlyGoal;
import com.hungteen.pvz.common.entity.ai.goal.PVZSwimGoal;
import com.hungteen.pvz.common.entity.ai.goal.ZombieBreakPlantBlockGoal;
import com.hungteen.pvz.common.entity.ai.goal.attack.PVZZombieAttackGoal;
import com.hungteen.pvz.common.entity.ai.goal.target.PVZNearestTargetGoal;
import com.hungteen.pvz.common.entity.bullet.AbstractBulletEntity;
import com.hungteen.pvz.common.entity.misc.drop.CoinEntity;
import com.hungteen.pvz.common.entity.misc.drop.CoinEntity.CoinType;
import com.hungteen.pvz.common.entity.misc.drop.SunEntity;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.plant.enforce.SquashEntity;
import com.hungteen.pvz.common.entity.plant.spear.SpikeWeedEntity;
import com.hungteen.pvz.common.entity.zombie.body.ZombieDropBodyEntity;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.common.item.ItemRegister;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.common.potion.EffectRegister;
import com.hungteen.pvz.remove.MetalTypes;
import com.hungteen.pvz.utils.AlgorithmUtil;
import com.hungteen.pvz.utils.ConfigUtil;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.interfaces.ICanAttract;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.*;

import java.util.List;
import java.util.Optional;

public abstract class PVZZombieEntity extends AbstractPAZEntity implements IZombieEntity {

	private static final DataParameter<Integer> ZOMBIE_TYPE = EntityDataManager.defineId(PVZZombieEntity.class, DataSerializers.INT);
	private static final DataParameter<Integer> ATTACK_TIME = EntityDataManager.defineId(PVZZombieEntity.class, DataSerializers.INT);
    //negative means rising, positive means perform attack animation.
	private static final DataParameter<Integer> ANIM_TIME = EntityDataManager.defineId(PVZZombieEntity.class, DataSerializers.INT);
	private static final int CHARM_FLAG = 0;
	private static final int MINI_FLAG = 1;
	private static final int HAND_FLAG = 2;
	private static final int HEAD_FLAG = 3;
	public static final int PERFORM_ATTACK_CD = 10;
	public static final int RISING_CD = 30;
	protected boolean needRising = false;
	public boolean canCollideWithZombie = true;
	protected boolean canLostHand = true;
	protected boolean canLostHead = true;
	protected int climbUpTick = 0;
	protected int maxClimbUpTick = 5;

	public PVZZombieEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.xpReward = this.getZombieXp() / 2;
		this.setPathfindingMalus(PathNodeType.DANGER_FIRE, 6.0F);
		this.setPathfindingMalus(PathNodeType.DAMAGE_FIRE, 6.0F);
		this.setPathfindingMalus(PathNodeType.DAMAGE_OTHER, 6.0F);
		this.setPathfindingMalus(PathNodeType.UNPASSABLE_RAIL, 6.0F);
		this.setPathfindingMalus(PathNodeType.LEAVES, 4F);
		
		this.setZombieType(this.getSpawnType());
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		entityData.define(ZOMBIE_TYPE, VariantType.NORMAL.ordinal());
		entityData.define(ATTACK_TIME, 0);
		entityData.define(ANIM_TIME, 0);
	}
	
	/**
	 * create zombie attributes.
	 * {@link EntityRegister#addEntityAttributes(net.minecraftforge.event.entity.EntityAttributeCreationEvent)}
	 */
	public static AttributeModifierMap createZombieAttributes() {
		return AbstractPAZEntity.createPAZAttributes()
				.add(Attributes.ATTACK_DAMAGE, ZombieUtil.VERY_LOW)
	    	    .add(Attributes.MAX_HEALTH, 20)
	     	    .add(Attributes.FOLLOW_RANGE, ZombieUtil.CLOSE_TARGET_RANGE)
	    		.add(Attributes.KNOCKBACK_RESISTANCE, 0.9)
	    		.add(Attributes.MOVEMENT_SPEED, ZombieUtil.WALK_NORMAL)
	    		.add(Attributes.FLYING_SPEED, 0)
	    		.build();
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(8, new PVZLookRandomlyGoal(this));
		this.goalSelector.addGoal(7, new RandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(7, new PVZSwimGoal(this));
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
		return super.createNavigation(world);
//		return new ZombiePathNavigator(this, world);
	}
	
	/* handle spawn */

	@Override
	public void finalizeSpawn(CompoundNBT tag) {
		super.finalizeSpawn(tag);
		if(! this.level.isClientSide){
			this.setZombieType(this.getSpawnType());
			if(this.needRising) {// rising from dirt.
				this.setAnimTime(- RISING_CD);
				this.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, RISING_CD + 10, 20, false, false));
			}
		}
	}

	@Override
	public void updatePAZStates() {
		super.updatePAZStates();
		if(! this.level.isClientSide) {
			if (this.canBeMini() && this.isMiniZombie()) {
			    this.onZombieBeMini();
			}
		}
	}
	
	/**
	 * get current variant type.
	 * it will be override by @NormalZombieEntity
	 */
	protected VariantType getSpawnType() {
		final int t = this.getRandom().nextInt(100);
		final int a = PVZConfig.COMMON_CONFIG.EntitySettings.ZombieSetting.ZombieSuperChance.get();
		final int b = PVZConfig.COMMON_CONFIG.EntitySettings.ZombieSetting.ZombieSunChance.get();
		return (t < a) ? VariantType.SUPER : (t < a + b) ? VariantType.SUN : VariantType.NORMAL;
	}

	@Override
	protected void initAttributes() {
		super.initAttributes();
		this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(this.getEatDamage());
		this.getAttribute(Attributes.FOLLOW_RANGE).setBaseValue(this.getFollowRange());
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(this.getWalkSpeed());
		this.getAttribute(Attributes.KNOCKBACK_RESISTANCE).setBaseValue(this.getKBValue());
	}

	@Override
	public void pazTick() {
		super.pazTick();
		this.level.getProfiler().push("PVZ Zombie Tick");
		this.zombieTick();
		this.level.getProfiler().pop();

		if (this.canNormalUpdate()) {
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
		//natural spawn zombie will heal in lava.
		if(! this.level.isClientSide){
			if(this.isInLava() && this.getExistTick() % 10 == 0 && ! this.getOwnerUUID().isPresent()){
				this.heal(20);
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
			if(this.canClimbWalls()) {
				if(++ this.climbUpTick <= this.maxClimbUpTick) {
					final Vector3d vec = this.getDeltaMovement();
				    this.setDeltaMovement(vec.x, 0.3D, vec.z);
				}
			} else {
				this.climbUpTick = 0;
			}
		}
	}
	
	/**
	 * {@link #normalZombieTick()}
	 */
	public boolean canClimbWalls() {
		return this.horizontalCollision;
	}

	/**
	 * trigger at {@link #hurt(DamageSource, float)}
	 */
	private void onLostHand(DamageSource source) {
		this.lostHand(true);
		ZombieDropBodyEntity body = EntityRegister.ZOMBIE_DROP_BODY.get().create(level);
		body.droppedByOwner(this, source, BodyType.HAND);
		level.addFreshEntity(body);
	}
	
	/**
	 * trigger at {@link #hurt(DamageSource, float)}
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
		this.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 1000000, 0, false, false));
		this.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 1000000, 0, false, false));
	}

	@Override
	public boolean canPAZTarget(Entity target) {
		//do not target entity underwater.
		if(target.isInWaterOrBubble() && target.getFluidHeight(FluidTags.WATER) + 0.05 > target.getBbHeight()){
			return false;
		}
		return super.canPAZTarget(target);
	}

	/**
	 * zombie perform attack CD.
	 * use for attack goals.
	 */
	public int getAttackCD() {
		if (!this.canNormalUpdate()) {//can not update means stop attack.
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
		return this.isMiniZombie() ? EntitySize.scalable(0.3F, 0.6F) : EntitySize.scalable(0.8f, 1.98f);
	}

	@Override
	public void die(DamageSource source) {
		super.die(source);
		if(ConfigUtil.enableZombieDropParts()) {
			if(! this.level.isClientSide) {
			    this.onFallBody(source);
			}
		}
	}

	@Override
	protected boolean canRemoveWhenDeath() {
		return (ConfigUtil.enableZombieDropParts() && !level.isClientSide) || super.canRemoveWhenDeath();//changed
	}

	@Override
	protected void onRemoveWhenDeath() {
		super.onRemoveWhenDeath();
		if (!level.isClientSide) {
			if (this.getVariantType() == VariantType.SUPER) {// drop energy
				this.dropEnergy();
			} else if (getVariantType() == VariantType.SUN) {
				this.dropSun();
			} else if (getVariantType() == VariantType.BEARD) {// finish achievement
			}
			if (this.canSpawnDrop) {
				this.spawnSpecialDrops();
			}
		}
	}

	/**
	 * {@link #onRemoveWhenDeath()}
	 * {@link #onCharmedBy(LivingEntity)}
	 */
	protected void dropEnergy() {
		EntityUtil.createEntityAndSpawn(level, EntityRegister.ENERGY.get(), this.blockPosition().above());
	}
	
	/**
	 * sun type zombie can drop sun after death.
	 * {@link #onRemoveWhenDeath()}
	 */
	protected void dropSun() {
		int num = this.getRandom().nextInt(8) + 3;
		for (int i = 0; i < num; ++i) {
			SunEntity.spawnSunRandomly(level, blockPosition().above(), 25, 2);
		}
	}
	
	/**
	 * zombies have chance to drop coin or chocolate when died.
	 * {@link #onRemoveWhenDeath()}
	 */
	protected void spawnSpecialDrops() {
		getDropSpecialList().getRandomItem(this.random).ifPresent(type -> {
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
	 * plant block are consider as group 1, so zombie will break them.
	 * {@link ZombieBreakPlantBlockGoal#canZombieContinue()}
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
		if(! level.isClientSide) {
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
		return false;
	}

	protected void dealDamageEffectToZombie(PVZEntityDamageSource source) {
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
	
	@Override
	public float getWalkTargetValue(BlockPos pos, IWorldReader worldIn) {
		return 9 - worldIn.getBrightness(LightType.BLOCK, pos);
	}
	
	@Override
	public void makeStuckInBlock(BlockState p_213295_1_, Vector3d p_213295_2_) {
		this.fallDistance = 0.0F;
	    this.stuckSpeedMultiplier = Vector3d.ZERO;
	}
	
	@Override
	protected float getBlockSpeedFactor() {//not affect by soul sand.
		Block block = this.level.getBlockState(this.blockPosition()).getBlock();
	    float f = block.getSpeedFactor();
	    if (block == Blocks.WATER || block == Blocks.BUBBLE_COLUMN) {
	    	return f;
	    }
	    return 1F;
	}

	public float getEatDamage(){
		return ZombieUtil.VERY_LOW;
	}

	public float getWalkSpeed(){
		return ZombieUtil.WALK_NORMAL;
	}

	public float getKBValue(){
		return 0.92F;
	}

	public float getFollowRange(){
		return ZombieUtil.CLOSE_TARGET_RANGE;
	}

	@Override
	public int getArmor() {
		return (int) this.getSkillValue(SkillTypes.TOUGH_BODY);
	}

	/**
	 * damage type of zombie.
	 * {@link #doHurtTarget(Entity)}
	 */
	protected PVZEntityDamageSource getZombieAttackDamageSource() {
		return PVZEntityDamageSource.eat(this);
	}

	/**
	 * true attack damage of zombie.
	 * {@link #doHurtTarget(Entity)}
	 */
	protected float getModifyAttackDamage(Entity entity, float f) {
		return f;
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
	public boolean canNormalUpdate() {
		return ! (this.needRising && this.getAnimTime() < 0) && super.canNormalUpdate();
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
		if (effect.getEffect() == EffectRegister.BUTTER_EFFECT.get() && !this.canBeButtered()) {
			return;
		}
		this.addEffect(effect);
	}

	@Override
	public void onCharmedBy(LivingEntity entity) {
		super.onCharmedBy(entity);
		if(this.canBeCharmed()) {
			this.setCharmed(!this.isCharmed());
			if (this.getVariantType() == VariantType.SUPER) {
				this.setZombieType(VariantType.NORMAL);
				this.dropEnergy();
			}
		}
	}
	
//	public void healZombie(float health) {
//		final float need1 = this.getMaxHealth() - this.getHealth();
//		this.heal(Math.min(need1, health));
//		health -= need1;
////		this.setDefenceLife(Math.max(this.getInnerLife(), this.getDefenceLife() + health));
//	}
	
	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		if(source instanceof PVZEntityDamageSource && ((PVZEntityDamageSource) source).isMustHurt()) {
			return false;
		}
		return source != DamageSource.OUT_OF_WORLD && !source.isCreativePlayer() && this.isZombieInvulnerableTo(source);
	}
	
	protected boolean isZombieInvulnerableTo(DamageSource source) {
		return this.isZombieRising() || (! EntityUtil.isEntityValid(source.getEntity()) && ! source.isMagic());
	}
	
	@Override
	public boolean fireImmune() {
		return true;
	}

	@Override
	public boolean ignoreExplosion() {
		return true;
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
		this.canBeButtered = false;
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
	
	public boolean canLostHand() {
		return this.canLostHand;
	}
	
	public boolean canLostHead() {
		return this.canLostHead;
	}

	@Override
	public boolean hasMetal() {
		return false;
	}

	@Override
	public void decreaseMetal() {

	}

	@Override
	public void increaseMetal() {

	}

	@Override
	public MetalTypes getMetalType() {
		return MetalTypes.EMPTY;
	}

	public boolean isZombieColdOrForzen() {
		return EntityUtil.isEntityCold(this) || EntityUtil.isEntityFrozen(this);
	}

	@Override
	public PVZGroupType getEntityGroupType() {
		return this.isCharmed() ? PVZGroupType.PLANTS : PVZGroupType.ZOMBIES;
	}

	@Override
	public IPAZType getPAZType() {
		return this.getZombieType();
	}

	/**
	 * relate to zombie type.
	 */
	public abstract IZombieType getZombieType();

	/* sound */
	
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundRegister.ZOMBIE_GROAN.get();
	}

	@Override
	public SoundEvent getHurtSound(DamageSource damageSourceIn) {
		if (damageSourceIn.getDirectEntity() instanceof AbstractBulletEntity) {
			return SoundRegister.SPLAT.get();
		}
		return super.getHurtSound(damageSourceIn);
	}
	
	public Optional<SoundEvent> getSpawnSound() {
		if(this.needRising) {//if zombie is rising from dirt.
			return Optional.ofNullable(SoundRegister.DIRT_RISE.get());
		}
		return Optional.empty();
	}
	
	/* data */
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("zombie_type", this.getVariantType().ordinal());
		compound.putInt("zombie_attack_time", this.getAttackTime());
		compound.putInt("zombie_anim_time", this.getAnimTime());
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("zombie_type")) {
			this.setZombieType(VariantType.values()[compound.getInt("zombie_type")]);
		}
		if (compound.contains("zombie_attack_time")) {
			this.setAttackTime(compound.getInt("zombie_attack_time"));
		}
		if (compound.contains("zombie_anim_time")) {
			this.setAnimTime(compound.getInt("zombie_anim_time"));
		}
	}
	
	/* getter setter */
	
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
	
	public VariantType getVariantType() {
		return VariantType.values()[entityData.get(ZOMBIE_TYPE)];
	}
	
	public void setZombieType(VariantType type) {
		entityData.set(ZOMBIE_TYPE, type.ordinal());
	}
	
	@Override
	public boolean isCharmed() {
		return AlgorithmUtil.BitOperator.hasBitOne(this.getPAZState(), CHARM_FLAG);
	}
	
	public void setCharmed(boolean is) {
		this.setStateByFlag(is, CHARM_FLAG);
	}

	public boolean isMiniZombie() {
		return AlgorithmUtil.BitOperator.hasBitOne(this.getPAZState(), MINI_FLAG);
	}
	
    public void setMiniZombie(boolean is) {
		this.setStateByFlag(is, MINI_FLAG);
	}
    
    public boolean hasHand() {
		return !AlgorithmUtil.BitOperator.hasBitOne(this.getPAZState(), HAND_FLAG);
	}
	
    public void lostHand(boolean is) {
		this.setStateByFlag(is, HAND_FLAG);
	}
    
    public boolean hasHead() {
		return !AlgorithmUtil.BitOperator.hasBitOne(this.getPAZState(), HEAD_FLAG);
	}
	
    public void lostHead(boolean is) {
		this.setStateByFlag(is, HEAD_FLAG);
	}
    
    private void setStateByFlag(boolean is, int flag) {
		this.setPAZState(AlgorithmUtil.BitOperator.setBit(this.getPAZState(), flag, is));
	}
    
	/**
	 * Zombie Variant Types.
	 */
	public enum VariantType {
		NORMAL, //the common type.
		SUPER, //zombie that will drop energy when death.
		BEARD, //zombie with green beard.(can only own by normal_zombie family)
		SUN, //zombie that will drop some sun when death.
	}

}
