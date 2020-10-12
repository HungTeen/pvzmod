package com.hungteen.pvz.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.entity.PVZMultiPartEntity;
import com.hungteen.pvz.entity.npc.CrazyDaveEntity;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.register.EffectRegister;

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
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class EntityUtil {

	/**
	 * use to spawn mob in world
	 */
	public static void onMobEntitySpawn(IWorld world, MobEntity entity, BlockPos pos) {
		entity.setPosition(pos.getX(), pos.getY(), pos.getZ());
		entity.onInitialSpawn(world, world.getDifficultyForLocation(pos), SpawnReason.SPAWNER, null, null);
		world.addEntity(entity);
	}
	
	/**
	 * check if entity is on ground
	 */
	public static boolean isOnGround(Entity entity){
		BlockPos pos=new BlockPos(entity).down();
		if(!entity.world.isAirBlock(pos)&&(entity.getPosY()-pos.getY())<=1.00001) {
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
		if(attacker==null || target==null) {//prevent crash
			return false;
		}
		if(!target.isAlive() || !attacker.isAlive()) {//need be a alive entity
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
			if(team1 != null && team2!=null) {
				boolean change = isEntityCharmed(attacker) ^ isEntityCharmed(target);
				return change ? team1.isSameTeam(team2) : !team1.isSameTeam(team2);
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
		}else if(entity instanceof CrazyDaveEntity){
			group = 1;
		}else if(entity instanceof MonsterEntity) {
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
			if(checkCanEntityTarget(attacker, entity)) {
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
			if(checkCanEntityAttack(attacker, entity)) {
				list.add(entity);
			}
		}
		return list;
	}
	
	/**
	 * add entity cold effect 
	 */
	public static void setEntityPotionEffect(Entity entity, EffectInstance effect) {
		if(entity instanceof PVZMultiPartEntity) {
			setEntityPotionEffect(((PVZMultiPartEntity) entity).getOwner(), effect);
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
		return entity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getModifier(EffectRegister.COLD_EFFECT_UUID)!=null;
	}
	
	/**
	 * get is entity frozen
	 */
	public static boolean isEntityFrozen(LivingEntity entity) {
		return entity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getModifier(EffectRegister.FROZEN_EFFECT_UUID)!=null;
	}
	
	/**
	 * get aabb by entity and w & h
	 */
	public static AxisAlignedBB getEntityAABB(Entity entity, double w, double h){
		return new AxisAlignedBB(entity.getPosX() - w, entity.getPosY() - h, entity.getPosZ() - w, entity.getPosX() + w, entity.getPosY() + h, entity.getPosZ() + w);
	}
	
}
