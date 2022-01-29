package com.hungteen.pvz.utils;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.enums.PVZGroupType;
import com.hungteen.pvz.api.interfaces.ICanBeCharmed;
import com.hungteen.pvz.api.interfaces.IHasGroup;
import com.hungteen.pvz.api.interfaces.IHasOwner;
import com.hungteen.pvz.common.entity.AbstractPAZEntity;
import com.hungteen.pvz.common.entity.EntityGroupHander;
import com.hungteen.pvz.common.entity.PVZMultiPartEntity;
import com.hungteen.pvz.common.entity.ai.goal.attack.PVZZombieAttackGoal;
import com.hungteen.pvz.common.entity.ai.goal.target.PVZHurtByTargetGoal;
import com.hungteen.pvz.common.entity.bullet.AbstractBulletEntity;
import com.hungteen.pvz.common.entity.misc.LawnMowerEntity;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.plant.assist.BloverEntity;
import com.hungteen.pvz.common.entity.plant.magic.StrangeCatEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.entity.zombie.base.AbstractBossZombieEntity;
import com.hungteen.pvz.common.entity.zombie.grass.PoleZombieEntity;
import com.hungteen.pvz.common.entity.zombie.pool.BalloonZombieEntity;
import com.hungteen.pvz.common.entity.zombie.pool.BobsleTeamEntity;
import com.hungteen.pvz.common.event.handler.LivingEventHandler;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.common.network.PVZPacketHandler;
import com.hungteen.pvz.common.network.toclient.SpawnParticlePacket;
import com.hungteen.pvz.common.potion.EffectRegister;
import com.hungteen.pvz.compat.jade.provider.PVZEntityProvider;
import com.hungteen.pvz.utils.interfaces.IHasMultiPart;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.IParticleData;
import net.minecraft.potion.EffectInstance;
import net.minecraft.scoreboard.Team;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.PacketDistributor.TargetPoint;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EntityUtil {

	public static final Random RAND = new Random();
	public static final float LIMITED_DAMAGE = 100;
	
	public static Vector3d getNormalisedVector2d(@Nonnull Entity a, @Nonnull Entity b) {
		final double dx = b.getX() - a.getX();
		final double dz = b.getZ() - a.getZ();
		final double dis = Math.sqrt(dx * dx + dz * dz);
		return new Vector3d(dx / dis, 0, dz / dis);
	}

	@Nullable
	public static Entity createWithNBT(World world, EntityType<?> entityType, CompoundNBT nbt, BlockPos pos){
		if(! World.isInSpawnableBounds(pos)) {
			PVZMod.LOGGER.error("Invalid position when trying summon entity !");
			return null;
		}
		final CompoundNBT compound = nbt.copy();
		compound.putString("id", entityType.getRegistryName().toString());
		Entity entity = EntityType.loadEntityRecursive(compound, world, e -> {
			e.moveTo(pos, e.xRot, e.yRot);
			return e;
		});
		if(entity == null) {
			PVZMod.LOGGER.error("summon entity failed !");
			return null;
		} else {
			if(world instanceof ServerWorld && ! ((ServerWorld) world).tryAddFreshEntityWithPassengers(entity)) {
				PVZMod.LOGGER.error("summon entity duplicated uuid !");
				return null;
			}
		}
		return entity;
	}
	
	/**
	 * can zombie be instant remove by lawn mower.
	 * {@link LawnMowerEntity#checkAndRemoveEntity(Entity)}
	 */
	public static boolean canEntityBeRemoved(Entity entity) {
		if(entity instanceof PVZZombieEntity) {
			return ((PVZZombieEntity) entity).canZombieBeRemoved();
		}
		if(!entity.getType().getRegistryName().getNamespace().equals(PVZMod.MOD_ID) 
				&& (entity instanceof LivingEntity)) {
			return ((LivingEntity) entity).getMaxHealth() > 100F;
		}
		return true;
	}
	
	/**
	 * {@link PVZHurtByTargetGoal}
	 */
	public static boolean canHelpAttackOthers(@Nonnull Entity entity) {
		if(entity instanceof PVZZombieEntity) {
			return ((PVZZombieEntity) entity).canHelpAttack();
		}
		if(entity instanceof PVZPlantEntity) {
			return ((PVZPlantEntity) entity).canHelpAttack();
		}
		return true;
	}
	
	/**
	 * spawn particle from server side to client side.
	 */
	public static void spawnParticle(Entity entity, int type) {
		PVZPacketHandler.CHANNEL.send(PacketDistributor.NEAR.with(() -> {
			return new TargetPoint(entity.getX(), entity.getY(), entity.getZ(), 40, entity.level.dimension());
		}), new SpawnParticlePacket(type, entity.getX(), entity.getY(), entity.getZ()));
	}
	
	/**
	 * spawn no speed particle on client side.
	 */
	public static void spawnStaticParticle(Entity entity, IParticleData type) {
		spawnSpeedParticle(entity, type, 0);
	}
	
	/**
	 * spawn speed particle on client side.
	 */
	public static void spawnSpeedParticle(Entity entity, IParticleData type, float speed) {
		WorldUtil.spawnRandomSpeedParticle(entity.level, type, entity.position().add(0, entity.getBbHeight(), 0), speed);
	}
	
	public static boolean canDestroyBlock(World world, BlockPos pos, Entity entity) {
		return canDestroyBlock(world, pos, world.getBlockState(pos), entity);
	}
	
	public static void playSound(Entity entity, SoundEvent ev) {
		if(ev != null) {
			entity.playSound(ev, 1.0F, RAND.nextFloat() * 0.2F + 0.9F);
		}
	}
	
	public static boolean isEntityValid(Entity target) {
		return target != null && target.isAlive();
	}
	
	/**
	 * {@link StrangeCatEntity#startSuperMode(boolean)}
	 */
	public static List<LivingEntity> getRandomLivingInRange(World world, LivingEntity attacker, AxisAlignedBB aabb, int cnt) {
		List<LivingEntity> list = new ArrayList<>();
		for(LivingEntity living : EntityUtil.getTargetableLivings(attacker, aabb)){
			list.add(living);
			if(-- cnt <= 0) {
				break;
			}
		}
		return list;
	}
	
	/**
	 * get how many health the target has currently.
	 * {@link #getCurrentDefenceHealth(LivingEntity)}
	 */
	public static float getCurrentHealth(LivingEntity target) {
		if(target instanceof AbstractPAZEntity) {
			return (float) ((AbstractPAZEntity) target).getCurrentHealth();
		}
		return target.getHealth();
	}
	
	/**
	 * get entity's defence health.
	 * use to show on jade mod.
	 * {@link PVZEntityProvider}
	 */
	public static float getCurrentDefenceHealth(LivingEntity entity) {
		return (float) (getCurrentHealth(entity) - entity.getHealth());
	}
	
	/**
	 * get the max health the target has currently.
	 */
	public static float getCurrentMaxHealth(LivingEntity target) {
		if(target instanceof AbstractPAZEntity) {
			return (float) ((AbstractPAZEntity) target).getCurrentMaxHealth();
		}
		return target.getMaxHealth();
	}
	
	public static float getMaxHealthDamage(LivingEntity target) {
		return getMaxHealthDamage(target, 1);
	}
	
	public static float getMaxHealthDamage(LivingEntity target, float multiple) {
		return (float) (EntityUtil.getCurrentMaxHealth(target) * multiple);
	}
	
	/**
	 * entity's health more than 100 is consider as other mod's boss.
	 */
	public static boolean isEntityBoss(@Nonnull LivingEntity entity) {
		if(entity instanceof AbstractBossZombieEntity) {
			return true;
		}
		if(entity instanceof PVZPlantEntity) {
			return false;
		}
		return entity.getHealth() > 100F;
	}

	public static boolean canSeeEntity(Entity entity, Entity target) {
		Vector3d start = entity.position().add(0, entity.getEyeHeight(), 0);
		Vector3d lowerEnd = target.position();
		Vector3d upperEnd = lowerEnd.add(0, target.getBbHeight(), 0);
		RayTraceContext ray1 = new RayTraceContext(start, lowerEnd, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, entity);
		RayTraceContext ray2 = new RayTraceContext(start, upperEnd, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, entity);
		return entity.level.clip(ray1).getType() != RayTraceResult.Type.BLOCK || entity.level.clip(ray2).getType() != RayTraceResult.Type.BLOCK;
	}
	
	/**
	 * can entity pass without hit block when it has a motion vec.
	 * {@link PoleZombieEntity}
	 */
	public static boolean canEntityPass(Entity entity, Vector3d vec, float length) {
		Vector3d lowStart = entity.position();
		Vector3d upperStart = entity.position().add(0, entity.getBbHeight(), 0);
		Vector3d lowerEnd = lowStart.add(vec.scale(length));
		Vector3d upperEnd = upperStart.add(vec.scale(length));
		RayTraceContext ray1 = new RayTraceContext(lowStart, lowerEnd, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, entity);
		RayTraceContext ray2 = new RayTraceContext(upperStart, upperEnd, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, entity);
		return entity.level.clip(ray1).getType() != RayTraceResult.Type.BLOCK && entity.level.clip(ray2).getType() != RayTraceResult.Type.BLOCK;
	}
	
	/**
	 * check can entity destroy the specific block.
	 */
	public static boolean canDestroyBlock(World world, BlockPos pos, BlockState state, Entity entity) {
		float hardness = state.getDestroySpeed(world, pos);
		return hardness >= 0f && hardness < 50f && !state.getBlock().isAir(state, world, pos)
				&& state.getBlock().canEntityDestroy(state, world, pos, entity) && (!(entity instanceof LivingEntity)
				|| ForgeEventFactory.onEntityDestroyBlock((LivingEntity) entity, pos, state));
	}
	
	/**
	 * use to create entity and spawn it in world.
	 */
	public static void createEntityAndSpawn(World world, EntityType<?> type, BlockPos pos) {
		Entity entity = type.create(world);
		onEntitySpawn(world, entity, pos);
	}
	
	/**
	 * use to spawn entity in world.
	 */
	public static void onEntitySpawn(IWorld world, Entity entity, BlockPos pos) {
		entity.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
		if(entity instanceof MobEntity) {
			((MobEntity) entity).finalizeSpawn((IServerWorld) world, world.getCurrentDifficultyAt(entity.blockPosition()), SpawnReason.SPAWNER, null, null);
		}
		world.addFreshEntity(entity);
	}
	
	/**
	 * spawn in random range position.
	 */
	public static void onEntityRandomPosSpawn(IWorld world, Entity entity, BlockPos pos, int dis) {
		pos = pos.offset(MathUtil.getRandomInRange(world.getRandom(), dis), world.getRandom().nextInt(dis) + 1, MathUtil.getRandomInRange(world.getRandom(), dis));
		onEntitySpawn(world, entity, pos);
	}
	
	/**
	 * check if entity is on ground
	 */
	public static boolean isOnGround(Entity entity){
		BlockPos pos = entity.blockPosition().below();
		if(!entity.level.isEmptyBlock(pos) && (entity.getY() - pos.getY()) <= 1.00001) {
			return true;
		}
		return false;
	}
	
	/**
	 * check if entity is on snow.
	 * {@link BobsleTeamEntity#zombieTick()}
	 */
	public static boolean isOnSnow(Entity entity) {
		BlockPos pos = entity.blockPosition();
		return entity.level.getBlockState(pos).is(Blocks.SNOW) || entity.level.getBlockState(pos.below()).is(Blocks.SNOW_BLOCK);
	}
	
	/**
	 * check if entity is on ice.
	 * {@link BobsleTeamEntity#zombieTick()}
	 */
	public static boolean isOnIce(Entity entity) {
		BlockPos pos = entity.blockPosition();
		return entity.level.getBlockState(pos).is(BlockTags.ICE) || entity.level.getBlockState(pos.below()).is(BlockTags.ICE);
	}
	
	/**
	 * use for squash to check can attack
	 */
	public static boolean isSuitableTargetInRange(MobEntity entity, @Nonnull LivingEntity target, double range) {
		return entity.distanceToSqr(target) > getAttackRange(entity, target, range) && canTargetEntity(entity, target);
	}
	
	/**
	 * get entity attack range by width and r
	 */
	public static double getAttackRange(Entity a, Entity b, double r) {
		double two = Math.sqrt(2);
		double dis = (a.getBbWidth() / two + b.getBbWidth() / two + r);
		return dis * dis;
	}
	
	/**
	 * get the nearest distance of two entities.
	 * {@link PVZZombieAttackGoal}
	 */
	public static double getNearestDistance(Entity a, Entity b) {
		double dx = a.getX() - b.getX();
		double dz = a.getZ() - b.getZ();
		double dy = 0;
		if(a.getY() > b.getY() + b.getBbHeight()) {
			dy = a.getY() - b.getY() - b.getBbHeight();
		} else if(b.getY() > a.getY() + a.getBbHeight()) {
			dy = b.getY() - a.getY() - a.getBbHeight();
		}
		return dx * dx + dy * dy + dz * dz;
	}
	
	/**
	 * get the owner of entity
	 */
	@Nullable
	public static PlayerEntity getEntityOwner(World world, @Nullable Entity entity) {
		UUID uuid = null;
		if(entity instanceof IHasOwner) {
			uuid = ((IHasOwner) entity).getOwnerUUID().orElse(null);
		}
		return uuid == null ? null : world.getPlayerByUUID(uuid);
	}
	
	/**
	 * use for TargetGoal to check if attacker can set target as AttackTarget.
	 */
	public static boolean canTargetEntity(Entity attacker, Entity target) {
		if(attacker instanceof PVZZombieEntity) {
		    return ((PVZZombieEntity) attacker).checkCanZombieTarget(target);
		}
		if(attacker instanceof PVZPlantEntity) {
	        return ((PVZPlantEntity) attacker).checkCanPlantTarget(target);
		}
		return checkCanEntityBeTarget(attacker, target);
	}
	
	public static boolean canAttackEntity(Entity attacker, Entity target) {
		if(attacker instanceof PVZZombieEntity) {
			return ((PVZZombieEntity) attacker).checkCanZombieAttack(target);
		}
		if(attacker instanceof PVZPlantEntity) {
			return ((PVZPlantEntity) attacker).checkCanPlantAttack(target);
		}
		return checkCanEntityBeAttack(attacker, target);
	}
	
	/**
	 * check can TargetGoal select target as attackTarget.
	 */
	public static boolean checkCanEntityBeTarget(Entity attacker, Entity target) {
		if(! EntityUtil.isEntityValid(attacker) || ! EntityUtil.isEntityValid(target) || EntityUtil.isOpEntity(target)) {//prevent crash
			return false;
		}
		if(target instanceof PVZMultiPartEntity) {//can attack its owner then can attack it
			return checkCanEntityBeTarget(attacker, ((PVZMultiPartEntity) target).getOwner());
		}
		if(ConfigUtil.isTeamAttackEnable()) {//enable team attack
			final Team team1 = getEntityTeam(attacker.level, attacker);
		    final Team team2 = getEntityTeam(attacker.level, target);
			if(team1 != null && team2 != null) {
				return isEntityCharmed(attacker) ^ isEntityCharmed(target) ? team1.isAlliedTo(team2) : ! team1.isAlliedTo(team2);
			}
		}
		if(attacker instanceof LivingEntity) {//target the entity who attack it before.
			if(target.is(((LivingEntity) attacker).getLastHurtMob()) && checkCanEntityBeAttack(attacker, target)) {
				return true;
			}
		}
		return EntityGroupHander.checkCanTarget(getEntityGroup(attacker), getEntityGroup(target));
	}
	
	/**
	 * check can AttackGoal continue to attack target.
	 */
	public static boolean checkCanEntityBeAttack(Entity attacker, Entity target) {
		if(! EntityUtil.isEntityValid(attacker) || ! EntityUtil.isEntityValid(target)) {//prevent crash
			return false;
		}
		if(target instanceof PVZMultiPartEntity) {//can attack its owner then can attack it
			return checkCanEntityBeAttack(attacker, ((PVZMultiPartEntity) target).getOwner());
		}
		if(target instanceof PlayerEntity && !PlayerUtil.isPlayerSurvival((PlayerEntity) target)) {
			return false;
		}
		if(ConfigUtil.isTeamAttackEnable()) {//enable team attack
			final Team team1 = getEntityTeam(attacker.level, attacker);
			final Team team2 = getEntityTeam(attacker.level, target);
			if(team1 != null && team2 != null) {
				return isEntityCharmed(attacker) ^ isEntityCharmed(target) ? team1.isAlliedTo(team2) : ! team1.isAlliedTo(team2);
			}
		}
		return EntityGroupHander.checkCanAttack(getEntityGroup(attacker), getEntityGroup(target));
	}

	/**
	 * both entity belong to the same side of group.
	 */
	public static boolean isFriendly(Entity a, Entity b){
		return (a == null || b == null) ? false : ! EntityGroupHander.checkCanTarget(getEntityGroup(a), getEntityGroup(b));
	}

	/**
	 * both entity belong to the same side of group.
	 */
	public static boolean isEnemy(Entity a, Entity b){
		return (a == null || b == null) ? false : EntityGroupHander.checkCanTarget(getEntityGroup(a), getEntityGroup(b));
	}

	public static PVZGroupType getEntityGroup(Entity entity) {
		if(entity instanceof PlayerEntity){
			return EntityGroupHander.getPlayerGroup((PlayerEntity) entity);
		}
		return (entity instanceof IHasGroup) ? ((IHasGroup) entity).getEntityGroupType() : EntityGroupHander.getEntityGroupType(entity);
	}

	/**
	 * get team of the entity.
	 * used at {@link #checkCanEntityBeAttack(Entity, Entity)}
	 */
	@Nullable
	public static Team getEntityTeam(World world, Entity entity){
		if(entity instanceof PlayerEntity) {
			return entity.getTeam();
		}
		if(entity instanceof IHasOwner && ((IHasOwner) entity).getOwnerUUID().isPresent()) {
			PlayerEntity player = world.getPlayerByUUID(((IHasOwner) entity).getOwnerUUID().get());
			return player == null ? null : player.getTeam();
		}
	    return entity.getTeam();
	}
	
	/**
	 * check if entity is charmed
	 */
	public static boolean isEntityCharmed(Entity entity){
		if(entity instanceof ICanBeCharmed) {
			return ((ICanBeCharmed) entity).isCharmed();
		}
		return false;
	}
	
	/**
	 * get targetable livingentity in range.
	 */
	public static List<LivingEntity> getTargetableLivings(@Nonnull Entity attacker, AxisAlignedBB aabb){
		return getPredicateEntities(attacker, aabb, LivingEntity.class, target -> canTargetEntity(attacker, target));
	}
	
	/**
	 * get viewable targetable livingentity in range.
	 */
	public static List<LivingEntity> getViewableTargetableEntity(@Nonnull Entity attacker, AxisAlignedBB aabb){
		return getTargetableLivings(attacker, aabb).stream().filter(target -> {
			return canSeeEntity(attacker, target);
		}).collect(Collectors.toList());
	}
	
	/**
	 * get targetable entities with details.
	 * often use for normal attack check.
	 */
	public static List<Entity> getTargetableEntities(@Nonnull Entity attacker, AxisAlignedBB aabb){
		return getPredicateEntities(attacker, aabb, Entity.class, target -> canTargetEntity(attacker, target));
	}
	
	/**
	 * get friendly entities.
	 */
	public static List<LivingEntity> getFriendlyLivings(@Nonnull Entity attacker, AxisAlignedBB aabb){
		return getPredicateEntities(attacker, aabb, LivingEntity.class, target -> ! canAttackEntity(attacker, target));
	}
	
	/**
	 * get targetable entities by original check function.
	 * {@link #getWholeTargetableEntities(Entity, AxisAlignedBB)}
	 */
	private static List<Entity> getTargetableEntitiesIngoreCheck(@Nonnull Entity attacker, AxisAlignedBB aabb){
		return getPredicateEntities(attacker, aabb, Entity.class, target -> checkCanEntityBeTarget(attacker, target));
	}
	
	/**
	 * {@link #getTargetableEntities(Entity, AxisAlignedBB)}
	 * {@link #getTargetableEntitiesIngoreCheck(Entity, AxisAlignedBB)}
	 */
	public static <T extends Entity> List<T> getPredicateEntities(@Nonnull Entity attacker, AxisAlignedBB aabb, Class<T> tClass, Predicate<T> predicate){
		if(attacker == null) {
			return new ArrayList<>();
		}
		return attacker.level.getEntitiesOfClass(tClass, aabb).stream().filter(target -> {
			return ! attacker.equals(target) && predicate.test(target);
		}).collect(Collectors.toList());
	}
	
	/**
	 * get final attack entities for explosion or other range attack.
	 * use for every range attack condition.
	 */
	public static List<Entity> getWholeTargetableEntities(@Nonnull Entity attacker, AxisAlignedBB aabb) {
		IntOpenHashSet set = new IntOpenHashSet();
		List<Entity> list = new ArrayList<>();
		if(attacker == null) return list;//prevent crash.
		List<Entity> targets = getTargetableEntitiesIngoreCheck(attacker, aabb);
		//choose owner first.
		targets.stream().filter(target -> ! (target instanceof PVZMultiPartEntity) 
				&& ! set.contains(target.getId())).forEach(target -> {
			set.addAll(getOwnerAndPartsID(target));
			list.add(target);
		});
		//deal with part.
		targets.stream().filter(target -> target instanceof PVZMultiPartEntity 
				&& ! set.contains(target.getId())).forEach(target -> {
			set.addAll(getOwnerAndPartsID(target));
			list.add(target);
		});
		return list;
	}
	
	/**
	 * get all parts of a entity.
	 * {@link AbstractBulletEntity#addHitEntity(Entity)}
	 */
	public static List<Integer> getOwnerAndPartsID(Entity entity){
		List<Integer> list = new ArrayList<>();
		if(entity instanceof PVZMultiPartEntity) {//the entity is a part.
			LivingEntity owner = ((PVZMultiPartEntity) entity).getOwner();
			if(owner == null) {// no owner
				list.add(entity.getId());
			} else {// get all id for owner's parts
				IHasMultiPart parent = ((PVZMultiPartEntity) entity).getParent();
				for(Entity target : parent.getMultiParts()) {
				    if(target != null) {
				    	list.add(target.getId());
				    }
				}
				list.add(owner.getId());
			}
		} else if(entity instanceof IHasMultiPart){
			for(Entity target : ((IHasMultiPart) entity).getMultiParts()) {
			    if(target != null) {
			    	list.add(target.getId());
			    }
			}
			list.add(entity.getId());
		} else {
			list.add(entity.getId());
		}
		return list;
	}
	
	/**
	 * add entity potion effect.
	 * {@link LivingEventHandler#handleHurtEffects(LivingEntity, PVZEntityDamageSource)}
	 */
	public static void addPotionEffect(Entity entity, EffectInstance effect) {
		if(entity instanceof PVZMultiPartEntity) {
			addPotionEffect(((PVZMultiPartEntity) entity).getOwner(), effect);
		} else if(entity instanceof PVZZombieEntity) {
			((PVZZombieEntity) entity).checkAndAddPotionEffect(effect);
		} else if(entity instanceof PVZPlantEntity){
			((PVZPlantEntity) entity).checkAndAddPotionEffect(effect);
		} else if(entity instanceof LivingEntity) {
			((LivingEntity) entity).addEffect(effect);
		}
	}
	
	/**
	 * is entity has cold effect.
	 */
	public static boolean isEntityCold(LivingEntity entity) {
		return entity.getAttribute(Attributes.MOVEMENT_SPEED).getModifier(EffectRegister.COLD_EFFECT_UUID) != null;
	}
	
	/**
	 * is entity has frozen effect.
	 */
	public static boolean isEntityFrozen(LivingEntity entity) {
		return entity.getAttribute(Attributes.MOVEMENT_SPEED).getModifier(EffectRegister.FROZEN_EFFECT_UUID) != null;
	}
	
	/**
	 * is entity has butter effect.
	 */
	public static boolean isEntityButter(LivingEntity entity) {
		return entity.getAttribute(Attributes.MOVEMENT_SPEED).getModifier(EffectRegister.BUTTER_EFFECT_UUID) != null;
	}
	
	/**
	 * {@link BloverEntity#blow()}
	 */
	public static boolean isEntityInSky(Entity entity) {
		if(entity instanceof FlyingEntity || entity instanceof BatEntity) {
			return true;
		}
		if(entity instanceof BalloonZombieEntity && ((BalloonZombieEntity) entity).hasBalloon()) {
			return true;
		}
		return ! entity.isOnGround() && ! entity.isInWater() && ! entity.isInLava();
	}
	
	public static boolean hasNearBy(World world, BlockPos pos, double r, Predicate<Entity> pre) {
		return world.getEntitiesOfClass(Entity.class, BlockUtil.getAABB(pos, r, r)).stream().filter(target -> {
			return pre.test(target);
		}).count() > 0;
	}

	/**
	 * set max health and heal.
	 */
	public static void setLivingMaxHealthAndHeal(LivingEntity living, float maxHealth) {
		living.getAttribute(Attributes.MAX_HEALTH).setBaseValue(maxHealth);
		living.heal(maxHealth);
	}
	
	/**
	 * used for bullets hit check.
	 * get predicate entity between start to end. 
	 */
	public static EntityRayTraceResult rayTraceEntities(World world, Entity entity, Vector3d startVec, Vector3d endVec, Predicate<Entity> predicate) {
		return ProjectileHelper.getEntityHitResult(world, entity, startVec, endVec, 
				entity.getBoundingBox().expandTowards(entity.getDeltaMovement()).inflate(1.0D), predicate);
	}
	
	/**
	 * used for item ray trace.
	 * get predicate entity with dis and vec. 
	 */
	public static EntityRayTraceResult rayTraceEntities(World world, Entity entity, Vector3d lookVec, double distance, Predicate<Entity> predicate) {
	    final Vector3d startVec = entity.position().add(0, entity.getEyeHeight(), 0);
	    Vector3d endVec = startVec.add(lookVec.normalize().scale(distance));
	    RayTraceContext ray = new RayTraceContext(startVec, endVec, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, entity);
		RayTraceResult result = world.clip(ray);
		if(result.getType() != RayTraceResult.Type.MISS) {// hit something
			endVec = result.getLocation();
		}
	    return ProjectileHelper.getEntityHitResult(world, entity, startVec, endVec, 
				entity.getBoundingBox().inflate(distance), predicate);
	}
	
	/**
	 * get AABB by entity's width and height.
	 */
	public static AxisAlignedBB getEntityAABB(Entity entity, double w, double h){
		return BlockUtil.getAABB(entity.blockPosition(), w, h);
	}

	/**
	 * no need to target or attack an op entity.
	 */
	public static boolean isOpEntity(Entity entity){
		if(entity instanceof PlayerEntity && ! PlayerUtil.isPlayerSurvival((PlayerEntity) entity)){
			return true;
		}
		return entity.isInvulnerable();
	}
	
}
