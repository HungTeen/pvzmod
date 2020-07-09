package com.hungteen.pvzmod.util;

import java.util.ArrayList;
import java.util.List;

import com.hungteen.pvzmod.entities.plants.base.EntityPlantBase;
import com.hungteen.pvzmod.entities.plants.defence.EntityPumpkin;
import com.hungteen.pvzmod.entities.plants.fight.EntitySpikeRock;
import com.hungteen.pvzmod.entities.plants.fight.EntitySpikeWeed;
import com.hungteen.pvzmod.entities.plants.fight.EntitySquash;
import com.hungteen.pvzmod.entities.plants.flame.EntityJalapeno;
import com.hungteen.pvzmod.entities.plants.ice.EntityIceShroom;
import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.entities.zombies.roof.EntityImp;
import com.hungteen.pvzmod.entities.zombies.special.EntityDolphin;
import com.hungteen.pvzmod.entities.zombies.special.EntityDuckyTube;
import com.hungteen.pvzmod.entities.zombies.special.EntityElementBall;
import com.hungteen.pvzmod.entities.zombies.special.EntityNormalDefence;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityUtil {
	
	public static double getAttackRange(Entity a,Entity b,double r)
	{
		return (a.width/2+b.width+r)*(a.width/2+b.width+r);
	}
	
	public static boolean isOnGround(Entity entity)
	{
		BlockPos pos=new BlockPos(entity);
		pos=pos.add(0, -1, 0);
		if(entity.world.getBlockState(pos).getMaterial()!=Material.AIR&&(entity.posY-pos.getY())<=1.1) {
			return true;
		}
		return false;
	}
	
	public static List<EntityLivingBase> getEntityAttackableTarget(EntityLivingBase attacker,AxisAlignedBB aabb)
	{
		World world = attacker.world;
		List<EntityLivingBase> list=new ArrayList<>();
		for(EntityLivingBase entity:world.getEntitiesWithinAABB(EntityLivingBase.class, aabb)) {
			if(checkCanEntityAttack(attacker, entity)) {
				list.add(entity);
			}
		}
		return list;
	}
	
	public static boolean checkCanEntityAttack(Entity attacker,Entity target)
	{
		if(attacker==null||target==null) {
			return false;
		}
		World world=attacker.world;
		//首先列举一些不论如何都不能进攻的判断
		if(target instanceof EntityPlayer) {//创造玩家当然不能攻击
			if(((EntityPlayer) target).isCreative()||((EntityPlayer) target).isSpectator()) {
				return false;
			}
		}
		if(target instanceof EntityZombieBase) {//僵尸道具不能攻击
			if(target instanceof EntityDuckyTube) return false;
			if(target instanceof EntityDolphin) return false;
			if(target instanceof EntityImp) {
				if(target.isRiding()) return false;
			}
		}
		boolean flag = ConfigurationUtil.MainConfig.damageSettings.canPlantHurtOtherTeams;
		if(flag) {
			Team team1=getEntityTeam(world, attacker);
			Team team2=getEntityTeam(world, target);
			if(team1!=null&&team2!=null) {//都有队伍才能判断
				if(getIsEntityCharmed(attacker)) return team1.isSameTeam(team2);//魅惑的话攻击该队所有实体
				return !team1.isSameTeam(team2);//相同队伍则不攻击
			}
		}
		
		//1为植物阵营 0为中立 -1为僵尸阵营
		int attackerGroup=getEntityGroup(attacker);
		int targetGroup=getEntityGroup(target);
		if(attackerGroup*targetGroup<0) {//阵营判断上 应该可攻击 留着后面特殊情况的预判
			if(target instanceof EntityElementBall) {
				if(((EntityElementBall) target).getBallState()==EntityElementBall.State.FLAME&&attacker instanceof EntityIceShroom) {
					return true;
				}
				if(((EntityElementBall) target).getBallState()==EntityElementBall.State.ICE&&attacker instanceof EntityJalapeno) {
					return true;
				}
				return false;
			}
			return true;
		}
		return false;
	}
	
	/**
	 * 获取实体的阵营
	 */
	public static int getEntityGroup(Entity entity)
	{
		int entityGroup=0;
		//2为玩家阵营 1为植物阵营 0为中立 -1为僵尸阵营
		if(entity instanceof EntityPlayer) {
			entityGroup=2;
		}
		else if(entity instanceof EntityPlantBase) {
			entityGroup=((EntityPlantBase) entity).getIsCharmed()?-1:1;
		}else if(entity instanceof EntityMob) {
			entityGroup=-1;
			if(entity instanceof EntityZombieBase) {
				if(((EntityZombieBase) entity).getIsCharmed()) { 
					entityGroup=1;
				}
			}
		}else if(entity instanceof EntityNormalDefence) {
			entityGroup=-1;
			EntityZombieBase zombie = ((EntityNormalDefence) entity).getOwner();
			if(zombie instanceof EntityZombieBase) {
				if(zombie.getIsCharmed()) { 
					entityGroup=1;
				}
			}
		}
		return entityGroup;
	}
	
	/**
	 * 判断实体是否被魅惑
	 */
	public static boolean getIsEntityCharmed(Entity entity)
	{
		if(entity instanceof EntityPlantBase) {
			return ((EntityPlantBase) entity).getIsCharmed();
		}
		if(entity instanceof EntityZombieBase) {
			return ((EntityZombieBase) entity).getIsCharmed();
		}
		return false;
	}
	
	public static Team getEntityTeam(World world,Entity entity)
	{
		if(entity instanceof EntityPlayer) {
			return ((EntityPlayer)entity).getTeam();
		}
	    else if(entity instanceof EntityPlantBase) {
			String name=((EntityPlantBase) entity).getOwnerName();
			if(name!="") {
				EntityPlayer player = world.getPlayerEntityByName(name);
				if(player==null) return null;
				return player.getTeam();
			}
		}
		else if(entity instanceof EntityZombieBase) {
			String name=((EntityZombieBase) entity).getOwnerName();
			if(name!="") {
				EntityPlayer player = world.getPlayerEntityByName(name);
				if(player==null) return null;
				return player.getTeam();
			}
		}
		return null;
	}
	
	public static boolean checkShouldApplyCollision(Entity base,Entity target)
	{
		if(base instanceof EntityPlantBase) {//本体是植物
			if(target instanceof EntityPlayer) {//不与玩家碰撞
				return false;
			}
			if(target instanceof EntityPumpkin) {//植物不与南瓜头碰撞，但南瓜头与南瓜头碰撞
				if(!(base instanceof EntityPumpkin)) return false;
				return true;
			}
			if(base instanceof EntityPumpkin) {//南瓜头不与植物碰撞，但南瓜头与南瓜头碰撞
				if(!(target instanceof EntityPumpkin)) return false;
				return true;
			}
			if(base instanceof EntitySquash) {
				if(EntityUtil.checkCanEntityAttack(base,target)) {
					if(((EntityPlantBase) base).getAttackTime()==0) {
						return true;
					}
				}
				if(target instanceof EntitySquash) return true; //自己挤压自己
				return false;
			}
		}
		else if(base instanceof EntityZombieBase) {//本体是僵尸
			if(target instanceof EntitySpikeRock) return false;//不碰撞地刺 地刺王
			if(target instanceof EntitySquash) return false;//不碰窝瓜 但会被窝瓜碰
			if(target instanceof EntityZombieBase) return false;//不碰僵尸
		}
		return true;
	}
	
	public static double getDistanceSq(Entity owner,Entity target) {
		if(target==null) return 1000d;
		double d0 = owner.posX - target.posX;
        double d1 = owner.posY - target.posY;
        double d2 = owner.posZ - target.posZ;
        return MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
	}
}