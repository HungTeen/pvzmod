package com.hungteen.pvz.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.entity.PVZMultiPartEntity;
import com.hungteen.pvz.entity.npc.CrazyDaveEntity;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.entity.zombie.poolnight.BalloonZombieEntity;
import com.hungteen.pvz.network.PVZPacketHandler;
import com.hungteen.pvz.network.SpawnParticlePacket;
import com.hungteen.pvz.register.EffectRegister;
import com.hungteen.pvz.utils.interfaces.IGroupEntity;
import com.hungteen.pvz.utils.interfaces.IMultiPartEntity;

import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.player.PlayerEntity;
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
	
	public static boolean canEntityBeRemoved(Entity entity) {
		if(entity instanceof PVZZombieEntity) {
			return ((PVZZombieEntity) entity).canZombieBeRemoved();
		}
		return entity.canChangeDimensions();
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
			return EntityUtil.checkCanEntityTarget(attacker, target);
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

	public static boolean checkCanSeeEntity(Entity entity, Entity target) {
		Vector3d start = entity.position().add(0, entity.getEyeHeight(), 0);
		Vector3d lowerEnd = target.position();
		Vector3d upperEnd = lowerEnd.add(0, target.getBbHeight(), 0);
		RayTraceContext ray1 = new RayTraceContext(start, lowerEnd, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, entity);
		RayTraceContext ray2 = new RayTraceContext(start, upperEnd, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, entity);
		return entity.level.clip(ray1).getType() != RayTraceResult.Type.BLOCK || entity.level.clip(ray2).getType() != RayTraceResult.Type.BLOCK;
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
	 * use to spawn mob in world
	 */
	public static void onMobEntitySpawn(IWorld world, MobEntity entity, BlockPos pos) {
		entity.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
		entity.finalizeSpawn((IServerWorld) world, world.getCurrentDifficultyAt(entity.blockPosition()), SpawnReason.SPAWNER, null, null);
		world.addFreshEntity(entity);
	}
	
	/**
	 * use to spawn entity in world
	 */
	public static void onEntitySpawn(IWorld world, Entity entity, BlockPos pos) {
		entity.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
		world.addFreshEntity(entity);
	}
	
	public static void onMobEntityRandomPosSpawn(IWorld world, MobEntity entity, BlockPos pos, int dis) {
		pos = pos.offset(entity.getRandom().nextInt(dis * 2 + 1) - dis, entity.getRandom().nextInt(dis) + 1, entity.getRandom().nextInt(dis * 2 + 1) - dis);
		onMobEntitySpawn(world, entity, pos);
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
		return entity.distanceToSqr(target) > getAttackRange(entity, target, range) && checkCanEntityAttack(entity, target);
	}
	
	/**
	 * get entity attack range by width and r
	 */
	public static double getAttackRange(Entity a, Entity b, double r) {
		double two = Math.sqrt(2);
		double dis = (a.getBbWidth() / two + b.getBbWidth() / two + r);
		return dis * dis;
	}
	
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
	 * check if attacker can set target as AttackTarget
	 */
	public static boolean checkCanEntityTarget(Entity attacker, LivingEntity target) {
		if(attacker instanceof PVZZombieEntity) {
			return ((PVZZombieEntity) attacker).checkCanZombieTarget(target);
		}
		if(attacker instanceof PVZPlantEntity) {
			return ((PVZPlantEntity) attacker).checkCanPlantTarget(target);
		}
		return checkCanEntityAttack(attacker, target);
	}
	
	/**
	 * use to check can the attacker attack the current target
	 */
	public static boolean checkCanEntityAttack(Entity attacker, Entity target) {
		if(target instanceof PVZMultiPartEntity) {//can attack its owner then can attack it
			target = ((PVZMultiPartEntity) target).getOwner();
		}
		if(attacker == null || target == null) {//prevent crash
			return false;
		}
		if(! target.isAlive()) {//need be a alive entity
			return false;
		}
		World world = attacker.level;
		if(target instanceof PlayerEntity) {//false if target is creative or spectator player 
			if(((PlayerEntity) target).isCreative() || target.isSpectator()) {
				return false;
			}
		}
		if(PVZConfig.COMMON_CONFIG.EntitySettings.TeamAttack.get()) {//enable team attack
			Team team1 = getEntityTeam(world, attacker);
			Team team2 = getEntityTeam(world, target);
			if(team1 != null && team2 != null) {
				boolean change = isEntityCharmed(attacker) ^ isEntityCharmed(target);
				return change ? team1.isAlliedTo(team2) : ! team1.isAlliedTo(team2);
			}
		}
		int attackerGroup = getEntityGroup(attacker);
		int targetGroup = getEntityGroup(target);
		if(attackerGroup * targetGroup < 0) {//can attack
			return true;
		}
		return false;
	}
	
	/**
	 * get entity's group
	 * players : config file
	 * plants & crazydave : 1
	 * zombies & monsters : -1
	 * multipart the same as its owner
	 * others : 0 
	 */
	public static int getEntityGroup(Entity entity) {
		int group = 0;
		if(entity instanceof PlayerEntity) {
			return PVZConfig.COMMON_CONFIG.EntitySettings.PlayerOriginGroup.get();
		}
		if(entity instanceof PVZPlantEntity) {
			group = ((PVZPlantEntity) entity).isCharmed() ? -1 : 1;
		} else if(entity instanceof CrazyDaveEntity){
			group = 1;
		} else if(entity instanceof IMob) {
			group = -1;
			if(entity instanceof PVZZombieEntity) {
				group = ((PVZZombieEntity) entity).isCharmed() ? 1 : -1;
			}
		} else if(entity instanceof IGroupEntity) {
			return ((IGroupEntity) entity).getEntityGroupType();
		} else if(entity instanceof GolemEntity) {
			return 1;
		}
		return group;
	}
	
	/**
	 * get team of the entity
	 */
	public static Team getEntityTeam(World world,Entity entity){
		if(entity instanceof PlayerEntity) {
			return entity.getTeam();
		}
		if(entity instanceof PVZPlantEntity && ((PVZPlantEntity) entity).getOwnerUUID().isPresent()) {
			PlayerEntity player = world.getPlayerByUUID(((PVZPlantEntity) entity).getOwnerUUID().get());
			return player == null ? null : player.getTeam();
		}
		if(entity instanceof PVZZombieEntity && ((PVZZombieEntity) entity).getOwnerUUID().isPresent()) {
			PlayerEntity player = world.getPlayerByUUID(((PVZZombieEntity) entity).getOwnerUUID().get());
			return player == null ? null : player.getTeam();
		}
	    return entity.getTeam();
	}
	
	/**
	 * check if entity is charmed
	 */
	public static boolean isEntityCharmed(Entity entity){
		if(entity instanceof PVZPlantEntity) {
			return ((PVZPlantEntity) entity).isCharmed();
		}
		if(entity instanceof PVZZombieEntity) {
			return ((PVZZombieEntity) entity).isCharmed();
		}
		return false;
	}
	
	/**
	 * get targetable entity
	 */
	public static List<LivingEntity> getEntityTargetableEntity(Entity attacker, AxisAlignedBB aabb){
		List<LivingEntity> list = new ArrayList<>();
		for(LivingEntity entity : attacker.level.getEntitiesOfClass(LivingEntity.class, aabb)) {
			if(attacker != entity && checkCanEntityTarget(attacker, entity)) {
				list.add(entity);
			}
		}
		return list;
	}
	
	/**
	 * get attackable entity
	 */
	public static List<Entity> getEntityAttackableTarget(Entity attacker, AxisAlignedBB aabb){
		List<Entity> list = new ArrayList<>();
		if(attacker == null) return list;
		for(Entity entity : attacker.level.getEntitiesOfClass(Entity.class, aabb)) {
			if(attacker != entity && checkCanEntityAttack(attacker, entity)) {
				list.add(entity);
			}
		}
		return list;
	}
	
	/**
	 * get targetable entity
	 */
	public static List<LivingEntity> getViewableTargetableEntity(Entity attacker, AxisAlignedBB aabb){
		List<LivingEntity> list = new ArrayList<>();
		for(LivingEntity entity : attacker.level.getEntitiesOfClass(LivingEntity.class, aabb)) {
			if(attacker != entity && EntityUtil.checkCanSeeEntity(attacker, entity) && checkCanEntityTarget(attacker, entity)) {
				list.add(entity);
			}
		}
		return list;
	}
	
	/**
	 * get final attack entities for explosion or other range attack.
	 */
	public static List<Entity> getAttackEntities(Entity attacker, AxisAlignedBB aabb) {
		IntOpenHashSet set = new IntOpenHashSet();
		List<Entity> list = new ArrayList<>();
		if(attacker == null) return list;
		List<Entity> targets = getEntityAttackableTarget(attacker, aabb);
		targets.forEach((target) -> {//owner first.
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
	 * add entity cold effect 
	 */
	public static void addEntityPotionEffect(Entity entity, EffectInstance effect) {
		if(entity instanceof PVZMultiPartEntity) {
			addEntityPotionEffect(((PVZMultiPartEntity) entity).getOwner(), effect);
		} else if(entity instanceof PVZZombieEntity) {
			((PVZZombieEntity) entity).checkAndAddPotionEffect(effect);
		} else if(entity instanceof LivingEntity) {
			((LivingEntity) entity).addEffect(effect);
		}
	}
	
	/**
	 * get is entity cold
	 */
	public static boolean isEntityCold(LivingEntity entity) {
		return entity.getAttribute(Attributes.MOVEMENT_SPEED).getModifier(EffectRegister.COLD_EFFECT_UUID) != null;
	}
	
	/**
	 * get is entity frozen
	 */
	public static boolean isEntityFrozen(LivingEntity entity) {
		return entity.getAttribute(Attributes.MOVEMENT_SPEED).getModifier(EffectRegister.FROZEN_EFFECT_UUID) != null;
	}
	
	/**
	 * get is entity butter
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
