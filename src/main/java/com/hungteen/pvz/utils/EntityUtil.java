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
import com.hungteen.pvz.register.EffectRegister;
import com.hungteen.pvz.utils.interfaces.IMultiPartEntity;

import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class EntityUtil {

	public static final Random RAND = new Random();
	
	public static boolean canDestroyBlock(World world, BlockPos pos, Entity entity) {
		return canDestroyBlock(world, pos, world.getBlockState(pos), entity);
	}
	
	public static void playSound(Entity entity, SoundEvent ev) {
		if(ev != null) {
			entity.playSound(ev, 1.0F, RAND.nextFloat() * 0.2F + 0.9F);
		}
	}

	public static boolean checkCanSeeEntity(Entity entity, Entity target) {
		Vec3d start = entity.getPositionVec().add(0, entity.getEyeHeight(), 0);
		Vec3d lowerEnd = target.getPositionVec();
		Vec3d upperEnd = lowerEnd.add(0, target.getHeight(), 0);
		RayTraceContext ray1 = new RayTraceContext(start, lowerEnd, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, entity);
		RayTraceContext ray2 = new RayTraceContext(start, upperEnd, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, entity);
		return entity.world.rayTraceBlocks(ray1).getType() != RayTraceResult.Type.BLOCK || entity.world.rayTraceBlocks(ray2).getType() != RayTraceResult.Type.BLOCK;
	}
	
	/**
	 * check can entity destroy the specific block.
	 */
	public static boolean canDestroyBlock(World world, BlockPos pos, BlockState state, Entity entity) {
		float hardness = state.getBlockHardness(world, pos);
		return hardness >= 0f && hardness < 50f && !state.getBlock().isAir(state, world, pos)
				&& state.getBlock().canEntityDestroy(state, world, pos, entity) && (!(entity instanceof LivingEntity)
				|| ForgeEventFactory.onEntityDestroyBlock((LivingEntity) entity, pos, state));
	}
	
	/**
	 * use to spawn mob in world
	 */
	public static void onMobEntitySpawn(IWorld world, MobEntity entity, BlockPos pos) {
		entity.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
		entity.onInitialSpawn(world, world.getDifficultyForLocation(entity.getPosition()), SpawnReason.SPAWNER, null, null);
		world.addEntity(entity);
	}
	
	public static void onMobEntityRandomPosSpawn(IWorld world, MobEntity entity, BlockPos pos, int dis) {
		pos = pos.add(entity.getRNG().nextInt(dis * 2 + 1) - dis, entity.getRNG().nextInt(dis) + 1, entity.getRNG().nextInt(dis * 2 + 1) - dis);
		onMobEntitySpawn(world, entity, pos);
	}
	
	/**
	 * check if entity is on ground
	 */
	public static boolean isOnGround(Entity entity){
		BlockPos pos=new BlockPos(entity).down();
		if(!entity.world.isAirBlock(pos) && (entity.getPosY() - pos.getY()) <= 1.00001) {
			return true;
		}
		return false;
	}
	
	/**
	 * check if entity is on snow
	 */
	public static boolean isOnSnow(Entity entity) {
		BlockPos pos = entity.getPosition();
		return entity.world.getBlockState(pos).getBlock()==Blocks.SNOW||entity.world.getBlockState(pos.down()).getBlock()==Blocks.SNOW_BLOCK;
	}
	
	/**
	 * use for squash to check can attack
	 */
	public static boolean isSuitableTargetInRange(MobEntity entity, @Nonnull LivingEntity target, double range) {
		return entity.getDistanceSq(target) > getAttackRange(entity, target, range) && checkCanEntityAttack(entity, target);
	}
	
	/**
	 * get entity attack range by width and r
	 */
	public static double getAttackRange(Entity a, Entity b, double r){
		return (a.getWidth() / 2 + b.getWidth() + r) * (a.getWidth() / 2 + b.getWidth() + r);
	}
	
	/**
	 * get the owner of entity
	 */
	@Nullable
	public static PlayerEntity getEntityOwner(World world, Entity entity) {
		if(entity==null) {
			return null;
		}
		UUID uuid = null;
		if(entity instanceof PVZPlantEntity) {
			uuid = ((PVZPlantEntity) entity).getOwnerUUID();
		}else if(entity instanceof PVZZombieEntity) {
			uuid = ((PVZZombieEntity) entity).getOwnerUUID();
		}
		return uuid == null ? null : world.getPlayerByUuid(uuid);
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
	public static boolean checkCanEntityAttack(Entity attacker, Entity target){
		if(target instanceof PVZMultiPartEntity) {//can attack its owner then can attack it
			target = ((PVZMultiPartEntity) target).getOwner();
		}
		if(attacker == null || target == null) {//prevent crash
			return false;
		}
		if(! target.isAlive() || ! attacker.isAlive()) {//need be a alive entity
			return false;
		}
		World world = attacker.world;
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
				return change ? team1.isSameTeam(team2) : ! team1.isSameTeam(team2);
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
	 * players : 2
	 * plants & crazydave : 1
	 * zombies & monsters : -1
	 * multipart the same as its owner
	 * others : 0 
	 */
	public static int getEntityGroup(Entity entity){
		int group = 0;
		if(entity instanceof PlayerEntity) {
			return 2;
		}
		if(entity instanceof PVZPlantEntity) {
			group = ((PVZPlantEntity) entity).isCharmed() ? -1 : 1;
		} else if(entity instanceof CrazyDaveEntity){
			group = 1;
		} else if(entity instanceof MonsterEntity) {
			group = -1;
			if(entity instanceof PVZZombieEntity) {
				group = ((PVZZombieEntity) entity).isCharmed() ? 1 : -1;
			}
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
		if(entity instanceof PVZPlantEntity) {
			PlayerEntity player = world.getPlayerByUuid(((PVZPlantEntity) entity).getOwnerUUID());
			return player == null ? null : player.getTeam();
		}
		if(entity instanceof PVZZombieEntity) {
			PlayerEntity player = world.getPlayerByUuid(((PVZZombieEntity) entity).getOwnerUUID());
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
	public static List<LivingEntity> getEntityTargetableEntity(LivingEntity attacker, AxisAlignedBB aabb){
		List<LivingEntity> list = new ArrayList<>();
		for(LivingEntity entity : attacker.world.getEntitiesWithinAABB(LivingEntity.class, aabb)) {
			if(attacker != entity && checkCanEntityTarget(attacker, entity)) {
				list.add(entity);
			}
		}
		return list;
	}
	
	/**
	 * get attackable entity
	 */
	public static List<Entity> getEntityAttackableTarget(LivingEntity attacker, AxisAlignedBB aabb){
		List<Entity> list = new ArrayList<>();
		for(Entity entity : attacker.world.getEntitiesWithinAABB(Entity.class, aabb)) {
			if(attacker != entity && checkCanEntityAttack(attacker, entity)) {
				list.add(entity);
			}
		}
		return list;
	}
	
	/**
	 * get final attack entities for explosion or other range attack.
	 */
	public static List<Entity> getAttackEntities(LivingEntity attacker, AxisAlignedBB aabb) {
		IntOpenHashSet set = new IntOpenHashSet();
		List<Entity> list = new ArrayList<>();
		List<Entity> targets = getEntityAttackableTarget(attacker, aabb);
		targets.forEach((target) -> {//owner first.
			if(! (target instanceof PVZMultiPartEntity)) {
				if(! set.contains(target.getEntityId())) {
					set.addAll(getOwnerAndPartsID(target));
					list.add(target);
				}
			}
		});
		targets.forEach((target) -> {//deal with part.
			if(! set.contains(target.getEntityId())) {
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
				list.add(entity.getEntityId());
			}else {// get all id for owner's parts
				IMultiPartEntity parent = ((PVZMultiPartEntity) entity).getParent();
				for(Entity target : parent.getParts()) {
				    if(target != null) {
				    	list.add(target.getEntityId());
				    }
				}
				list.add(owner.getEntityId());
			}
		} else if(entity instanceof IMultiPartEntity){
			for(Entity target : ((IMultiPartEntity) entity).getParts()) {
			    if(target != null) {
			    	list.add(target.getEntityId());
			    }
			}
			list.add(entity.getEntityId());
		} else {
			list.add(entity.getEntityId());
		}
		return list;
	}
	
	/**
	 * add entity cold effect 
	 */
	public static void addEntityPotionEffect(Entity entity, EffectInstance effect) {
		if(entity instanceof PVZMultiPartEntity) {
			addEntityPotionEffect(((PVZMultiPartEntity) entity).getOwner(), effect);
		}else if(entity instanceof PVZZombieEntity) {
			((PVZZombieEntity) entity).checkAndAddPotionEffect(effect);
		}else if(entity instanceof LivingEntity) {
			((LivingEntity) entity).addPotionEffect(effect);
		}
	}
	
	/**
	 * get is entity cold
	 */
	public static boolean isEntityCold(LivingEntity entity) {
		return entity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getModifier(EffectRegister.COLD_EFFECT_UUID) != null;
	}
	
	/**
	 * get is entity frozen
	 */
	public static boolean isEntityFrozen(LivingEntity entity) {
		return entity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getModifier(EffectRegister.FROZEN_EFFECT_UUID) != null;
	}
	
	/**
	 * get aabb by entity and w & h
	 */
	public static AxisAlignedBB getEntityAABB(Entity entity, double w, double h){
		return new AxisAlignedBB(entity.getPosX() - w, entity.getPosY() - h, entity.getPosZ() - w, entity.getPosX() + w, entity.getPosY() + h, entity.getPosZ() + w);
	}
	
}
