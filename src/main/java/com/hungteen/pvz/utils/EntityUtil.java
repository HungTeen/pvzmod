package com.hungteen.pvz.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.enums.PVZGroupType;
import com.hungteen.pvz.api.interfaces.ICanCharm;
import com.hungteen.pvz.api.interfaces.IGroupEntity;
import com.hungteen.pvz.api.interfaces.IHasOwner;
import com.hungteen.pvz.common.entity.PVZMultiPartEntity;
import com.hungteen.pvz.common.entity.goal.attack.PVZZombieAttackGoal;
import com.hungteen.pvz.common.entity.misc.LawnMowerEntity;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.entity.zombie.grassday.PoleZombieEntity;
import com.hungteen.pvz.common.entity.zombie.poolnight.BalloonZombieEntity;
import com.hungteen.pvz.common.network.PVZPacketHandler;
import com.hungteen.pvz.common.network.SpawnParticlePacket;
import com.hungteen.pvz.compat.jade.provider.PVZEntityProvider;
import com.hungteen.pvz.register.EffectRegister;
import com.hungteen.pvz.utils.interfaces.IMultiPartEntity;

import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.PacketDistributor.TargetPoint;

public class EntityUtil {

	public static final Random RAND = new Random();
	
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
	
	public static boolean canHelpAttackOthers(@Nonnull Entity entity) {
		if(entity instanceof PVZZombieEntity) {
			return ((PVZZombieEntity) entity).canHelpAttack();
		}
		return true;
	}
	
	public static void spawnParticle(Entity entity, int type) {
		PVZPacketHandler.CHANNEL.send(PacketDistributor.NEAR.with(() -> {
			return new TargetPoint(entity.getX(), entity.getY(), entity.getZ(), 40, entity.level.dimension());
		}), new SpawnParticlePacket(type, entity.getX(), entity.getY(), entity.getZ()));
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
	
	public static List<LivingEntity> getRandomLivingInRange(World world, LivingEntity attacker, AxisAlignedBB aabb, int cnt) {
		List<LivingEntity> list = new ArrayList<>();
		for(LivingEntity living : world.getEntitiesOfClass(LivingEntity.class, aabb, (target) -> {
			return EntityUtil.canTargetEntity(attacker, target);
		})){
			list.add(living);
			if(-- cnt <= 0) break;
		}
		return list;
	}
	
	/**
	 * get how many health the target has currently.
	 */
	public static float getCurrentHealth(LivingEntity target) {
		if(target instanceof PVZZombieEntity) {
			return ((PVZZombieEntity) target).getCurrentHealth();
		} else if(target instanceof PVZPlantEntity) {
			return ((PVZPlantEntity) target).getCurrentHealth();
		}
		return target.getHealth();
	}
	
	/**
	 * entity's defence health.
	 * {@link PVZEntityProvider}
	 */
	public static float getCurrentDefenceHealth(LivingEntity entity) {
		if(entity instanceof PVZZombieEntity) {
			return ((PVZZombieEntity) entity).getDefenceLife();
		} else if(entity instanceof PVZPlantEntity) {
			return ((PVZPlantEntity) entity).getCurrentDefenceHealth();
		}
		return 0;
	}
	
	/**
	 * get the max health the target has currently.
	 */
	public static float getCurrentMaxHealth(LivingEntity target) {
		if(target instanceof PVZZombieEntity) {
			return ((PVZZombieEntity) target).getCurrentMaxHealth();
		} else if(target instanceof PVZPlantEntity) {
			return ((PVZPlantEntity) target).getCurrentMaxHealth();
		}
		return target.getMaxHealth();
	}
	
	/**
	 * entity's level.
	 * {@link PVZEntityProvider}
	 */
	public static int getEntityLevel(LivingEntity entity) {
		if(entity instanceof PVZZombieEntity) {
			return ((PVZZombieEntity) entity).getZombieLevel();
		} else if(entity instanceof PVZPlantEntity) {
			return ((PVZPlantEntity) entity).getPlantLvl();
		}
		return 0;
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
	public static void onMobEntityRandomPosSpawn(IWorld world, MobEntity entity, BlockPos pos, int dis) {
		pos = pos.offset(entity.getRandom().nextInt(dis * 2 + 1) - dis, entity.getRandom().nextInt(dis) + 1, entity.getRandom().nextInt(dis * 2 + 1) - dis);
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
	 * check if entity is on snow
	 */
	public static boolean isOnSnow(Entity entity) {
		BlockPos pos = entity.blockPosition();
		return entity.level.getBlockState(pos).getBlock()==Blocks.SNOW||entity.level.getBlockState(pos.below()).getBlock()==Blocks.SNOW_BLOCK;
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
	public static PlayerEntity getEntityOwner(World world, Entity entity) {
		if(entity == null) return null;
		UUID uuid = null;
		if(entity instanceof PVZPlantEntity) {
			uuid = ((PVZPlantEntity) entity).getOwnerUUID().orElse(null);
		} else if(entity instanceof PVZZombieEntity) {
			uuid = ((PVZZombieEntity) entity).getOwnerUUID().orElse(null);
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
		if(! isEntityValid(attacker) || ! isEntityValid(target)) {//prevent crash
			return false;
		}
		if(target instanceof PVZMultiPartEntity) {//can attack its owner then can attack it
			return checkCanEntityBeTarget(attacker, ((PVZMultiPartEntity) target).getOwner());
		}
		if(target instanceof PlayerEntity && !PlayerUtil.isPlayerSurvival((PlayerEntity) target)) {
			return false;
		}
		if(ConfigUtil.isTeamAttackEnable()) {//enable team attack
			Team team1 = getEntityTeam(attacker.level, attacker);
			Team team2 = getEntityTeam(attacker.level, target);
			if(team1 != null && team2 != null) {
				boolean change = isEntityCharmed(attacker) ^ isEntityCharmed(target);
				return change ? team1.isAlliedTo(team2) : ! team1.isAlliedTo(team2);
			}
		}
		return PVZGroupType.checkCanTarget(getEntityGroup(attacker), getEntityGroup(target));
	}
	
	/**
	 * check can AttackGoal continue to attack target.
	 */
	public static boolean checkCanEntityBeAttack(Entity attacker, Entity target) {
		if(! isEntityValid(attacker) || ! isEntityValid(target)) {//prevent crash
			return false;
		}
		if(target instanceof PVZMultiPartEntity) {//can attack its owner then can attack it
			return checkCanEntityBeAttack(attacker, ((PVZMultiPartEntity) target).getOwner());
		}
		if(target instanceof PlayerEntity && !PlayerUtil.isPlayerSurvival((PlayerEntity) target)) {
			return false;
		}
		if(ConfigUtil.isTeamAttackEnable()) {//enable team attack
			Team team1 = getEntityTeam(attacker.level, attacker);
			Team team2 = getEntityTeam(attacker.level, target);
			if(team1 != null && team2 != null) {
				boolean change = isEntityCharmed(attacker) ^ isEntityCharmed(target);
				return change ? team1.isAlliedTo(team2) : ! team1.isAlliedTo(team2);
			}
		}
		return PVZGroupType.checkCanAttack(getEntityGroup(attacker), getEntityGroup(target));
	}
	
	/**
	 * get entity's group.
	 * specially mention : multiparts' group the same as its owner.
	 * others entity is group 0.
	 */
	public static PVZGroupType getEntityGroup(Entity entity) {
		if(entity instanceof ServerPlayerEntity) {// get player's group
			return PlayerUtil.getPlayerGroupType((ServerPlayerEntity) entity);
		}
		if(entity instanceof PVZMultiPartEntity) {
			return getEntityGroup(((PVZMultiPartEntity) entity).getOwner());
		}
		if(entity instanceof IGroupEntity) {
			return ((IGroupEntity) entity).getEntityGroupType();
		}
		if(PVZGroupType.isOtherMonsters(entity)) {
			return PVZGroupType.OTHER_MONSTERS;
		}
		if(PVZGroupType.isOtherGuards(entity)) {
			return PVZGroupType.OTHER_GURADS;
		}
		return PVZGroupType.CREATURES;
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
		if(entity instanceof ICanCharm) {
			return ((ICanCharm) entity).isCharmed();
		}
		return false;
	}
	
	/**
	 * get targetable livingentity in range.
	 */
	public static List<LivingEntity> getTargetableLivings(@Nonnull Entity attacker, AxisAlignedBB aabb){
		return attacker.level.getEntitiesOfClass(LivingEntity.class, aabb).stream().filter(target -> {
			return ! attacker.equals(target) && canTargetEntity(attacker, target);
		}).collect(Collectors.toList());
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
	 * get attackable entities.
	 */
	public static List<Entity> getTargetableEntities(@Nonnull Entity attacker, AxisAlignedBB aabb){
		if(attacker == null) return new ArrayList<>();
		return attacker.level.getEntitiesOfClass(Entity.class, aabb).stream().filter(target -> {
			return ! attacker.equals(target) && canTargetEntity(attacker, target);
		}).collect(Collectors.toList());
	}
//	
	/**
	 * get final attack entities for explosion or other range attack.
	 */
	public static List<Entity> getRangeTargetableEntities(@Nonnull Entity attacker, AxisAlignedBB aabb) {
		IntOpenHashSet set = new IntOpenHashSet();
		List<Entity> list = new ArrayList<>();
		if(attacker == null) return list;//prevent crash.
		List<Entity> targets = getTargetableEntities(attacker, aabb);
		targets.forEach(target -> {//owner first.
			if(! (target instanceof PVZMultiPartEntity)) {
				if(! set.contains(target.getId())) {
					set.addAll(getOwnerAndPartsID(target));
					list.add(target);
				}
			}
		});
		targets.forEach((target) -> {//deal with part.
			if(! set.contains(target.getId())) {
				set.addAll(getOwnerAndPartsID(target));
				list.add(target);
			}
		});
		return list;
	}
	
	/**
	 * get all parts of a entity.
	 */
	public static List<Integer> getOwnerAndPartsID(Entity entity){
		List<Integer> list = new ArrayList<>();
		if(entity instanceof PVZMultiPartEntity) {//the entity is a part.
			LivingEntity owner = ((PVZMultiPartEntity) entity).getOwner();
			if(owner == null) {// no owner
				list.add(entity.getId());
			} else {// get all id for owner's parts
				IMultiPartEntity parent = ((PVZMultiPartEntity) entity).getParent();
				for(Entity target : parent.getMultiParts()) {
				    if(target != null) {
				    	list.add(target.getId());
				    }
				}
				list.add(owner.getId());
			}
		} else if(entity instanceof IMultiPartEntity){
			for(Entity target : ((IMultiPartEntity) entity).getMultiParts()) {
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
	 */
	public static void addPotionEffect(Entity entity, EffectInstance effect) {
		if(entity instanceof PVZMultiPartEntity) {
			addPotionEffect(((PVZMultiPartEntity) entity).getOwner(), effect);
		} else if(entity instanceof PVZZombieEntity) {
			((PVZZombieEntity) entity).checkAndAddPotionEffect(effect);
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
	
	public static boolean isEntityInSky(Entity entity) {
		if(entity instanceof FlyingEntity || entity instanceof BatEntity) return true;
		if(entity instanceof BalloonZombieEntity && ((BalloonZombieEntity) entity).hasBalloon()) return true;
		return ! entity.isOnGround() && ! entity.isInWater() && ! entity.isInLava();
	}

	/**
	 * set max health and heal.
	 */
	public static void setLivingMaxHealthAndHeal(LivingEntity living, float maxHealth) {
		living.getAttribute(Attributes.MAX_HEALTH).setBaseValue(maxHealth);
		living.heal(maxHealth);
	}
	
	/**
	 * get aabb by entity and w & h
	 */
	public static AxisAlignedBB getEntityAABB(Entity entity, double w, double h){
		return new AxisAlignedBB(entity.getX() - w, entity.getY() - h, entity.getZ() - w, entity.getX() + w, entity.getY() + h, entity.getZ() + w);
	}
	
}
