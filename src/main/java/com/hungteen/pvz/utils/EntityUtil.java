package com.hungteen.pvz.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nonnull;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.entity.plant.spear.SpikeWeedEntity;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.register.EffectRegister;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityUtil {

	public static boolean isOnGround(Entity entity){
		BlockPos pos=new BlockPos(entity).down();
		if(!entity.world.isAirBlock(pos)&&(entity.getPosY()-pos.getY())<=1.00001) {
			return true;
		}
		return false;
	}
	
	public static boolean isOnSnow(Entity entity) {
		BlockPos pos = entity.getPosition();
		return entity.world.getBlockState(pos).getBlock()==Blocks.SNOW||entity.world.getBlockState(pos.down()).getBlock()==Blocks.SNOW_BLOCK;
	}
	
	public static boolean isSuitableTarget(MobEntity entity,@Nonnull LivingEntity target,double range) {
		return entity.getDistanceSq(target)>getAttackRange(entity, target, range)&&checkCanEntityAttack(entity, target);
	}
	
	public static double getAttackRange(Entity a,Entity b,double r){
		return (a.getWidth()/2+b.getWidth()+r)*(a.getWidth()/2+b.getWidth()+r);
	}
	
	public static PlayerEntity getEntityOwner(World world, Entity entity) {
		if(entity==null) return null;
		UUID uuid = null;
		if(entity instanceof PVZPlantEntity) {
			uuid = ((PVZPlantEntity) entity).getOwnerUUID();
		}else if(entity instanceof PVZZombieEntity) {
			uuid = ((PVZZombieEntity) entity).getOwnerUUID();
		}
		return uuid == null ? null : world.getPlayerByUuid(uuid);
	}
	
//	/**
//	 * use to update collision about plants and zombies
//	 */
//	public static boolean checkShouldApplyCollision(LivingEntity base,LivingEntity target){
//		if(base instanceof PVZPlantEntity) {//base is a plant
//			if(target instanceof PVZPlantEntity) {//plants collide with plants include itself. Be careful,if add pumpkin,improve here
//				return true;
//			}
//			if(checkCanEntityAttack(base, target)) {//collide with enemy.
//				return true;
//			}
//			return false;
//		}
//		return true;
//	}
	
	/**
	 * check if attacker can set target as AttackTarget
	 */
	public static boolean checkCanEntityTarget(Entity attacker,Entity target) {
		if(attacker instanceof PVZZombieEntity) {
			return ((PVZZombieEntity) attacker).checkCanZombieTarget(target);
		}
		return checkCanEntityAttack(attacker, target);
	}
	
	/**
	 * use to check can the attacker attack the current target
	 */
	public static boolean checkCanEntityAttack(Entity attacker,Entity target){
		if(attacker==null||target==null) {//prevent crash
			return false;
		}
		if(!target.isAlive()) {
			return false;
		}
		World world=attacker.world;
		if(target instanceof PlayerEntity) {
			if(((PlayerEntity) target).isCreative()||target.isSpectator()) return false;
		}
		if(PVZConfig.COMMON_CONFIG.EntitySettings.CanPlantAttackOtherTeam.get()) {
			Team team1=getEntityTeam(world,attacker);
			Team team2=getEntityTeam(world,target);
			if(team1!=null&&team2!=null) {
				boolean change=getIsEntityCharmed(attacker)^getIsEntityCharmed(target);
				if(change) return team1.isSameTeam(team2);
				else return !team1.isSameTeam(team2);
			}
		}
		int attackerGroup=getEntityGroup(attacker);
		int targetGroup=getEntityGroup(target);
		if(attackerGroup*targetGroup<0) {
			if(target instanceof SpikeWeedEntity&&attacker instanceof PVZZombieEntity) {
				return ((PVZZombieEntity)attacker).canAttackSpike();
			}
			return true;
		}
		return false;
	}
	
	public static int getEntityGroup(Entity entity){
		int group=0;
		if(entity instanceof PlayerEntity) return 2;
		if(entity instanceof PVZPlantEntity) {
			group=((PVZPlantEntity) entity).getIsCharmed()?-1:1;
		}else if(entity instanceof MonsterEntity) {
			group=-1;
			if(entity instanceof PVZZombieEntity) {
				group=((PVZZombieEntity) entity).getIsCharmed()?1:-1;
			}
		}
		return group;
	}
	
	public static Team getEntityTeam(World world,Entity entity)
	{
		if(entity instanceof PlayerEntity) {
			return entity.getTeam();
		}
		if(entity instanceof PVZPlantEntity) {
			PlayerEntity player = world.getPlayerByUuid(((PVZPlantEntity) entity).getOwnerUUID());
			if(player==null) return null;
			return player.getTeam();
		}
		if(entity instanceof PVZZombieEntity) {
			PlayerEntity player = world.getPlayerByUuid(((PVZZombieEntity) entity).getOwnerUUID());
			if(player==null) return null;
			return player.getTeam();
		}
	    return null;
	}
	
	public static boolean getIsEntityCharmed(Entity entity)
	{
		if(entity instanceof PVZPlantEntity) return ((PVZPlantEntity) entity).getIsCharmed();
		if(entity instanceof PVZZombieEntity) return ((PVZZombieEntity) entity).getIsCharmed();
		return false;
	}
	
	public static List<LivingEntity> getEntityAttackableTarget(LivingEntity attacker,AxisAlignedBB aabb)
	{
		World world=attacker.world;
		List<LivingEntity> list=new ArrayList<>();
		for(LivingEntity entity:world.getEntitiesWithinAABB(LivingEntity.class, aabb)) {
			if(checkCanEntityAttack(attacker, entity)) {
				list.add(entity);
			}
		}
		return list;
	}
	
	public static boolean isEntityCold(LivingEntity entity) {
		return entity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getModifier(EffectRegister.COLD_EFFECT_UUID)!=null;
	}
	
	public static boolean isEntityFrozen(LivingEntity entity) {
		return entity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getModifier(EffectRegister.FROZEN_EFFECT_UUID)!=null;
	}
	
	public static AxisAlignedBB getEntityAABB(Entity entity,double w,double h){
		return new AxisAlignedBB(entity.getPosX()-w, entity.getPosY()-h, entity.getPosZ()-w, entity.getPosX()+w, entity.getPosY()+h, entity.getPosZ()+w);
	}
}
