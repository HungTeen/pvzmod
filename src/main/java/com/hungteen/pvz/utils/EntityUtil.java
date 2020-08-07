package com.hungteen.pvz.utils;

import java.util.ArrayList;
import java.util.List;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityUtil {

	public static boolean checkCanEntityAttack(Entity attacker,Entity target)
	{
		if(attacker==null||target==null) {//prevent crash
			return false;
		}
		World world=attacker.world;
		if(target instanceof PlayerEntity) {
			if(((PlayerEntity) target).isCreative()||target.isSpectator()) return false;
		}
		if(PVZConfig.COMMON_CONFIG.ENTITY_SETTINGS.canPlantAttackOtherTeam.get()) {
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
			return true;
		}
		return false;
	}
	
	public static int getEntityGroup(Entity entity)
	{
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
	
	public static AxisAlignedBB getEntityAABB(Entity entity,double w,double h)
	{
		return new AxisAlignedBB(entity.getPosX()-w, entity.getPosY()-h, entity.getPosZ()-w, entity.getPosX()+w, entity.getPosY()+h, entity.getPosZ()+w);
	}
}
