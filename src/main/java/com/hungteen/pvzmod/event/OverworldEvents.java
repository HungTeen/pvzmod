package com.hungteen.pvzmod.event;

import java.util.HashSet;
import java.util.Random;

import javax.annotation.Nonnull;

import com.hungteen.pvzmod.Main;
import com.hungteen.pvzmod.entities.zombies.grassday.EntityBucketHeadZombie;
import com.hungteen.pvzmod.entities.zombies.grassday.EntityConeHeadZombie;
import com.hungteen.pvzmod.entities.zombies.grassday.EntityFlagZombie;
import com.hungteen.pvzmod.entities.zombies.grassday.EntityNormalZombie;
import com.hungteen.pvzmod.registry.EntitySpawnRegister;
import com.hungteen.pvzmod.util.ConfigurationUtil;
import com.hungteen.pvzmod.util.SoundsHandler;
import com.hungteen.pvzmod.util.StringUtil;
import com.hungteen.pvzmod.util.enums.SpecialEvents;
import com.hungteen.pvzmod.world.data.OverworldData;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.event.terraingen.BiomeEvent.GetWaterColor;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class OverworldEvents{

	private static final Random rand = new Random();
	
	public static void doTickCheck(TickEvent.WorldTickEvent ev)
	{
		long totalTime = ev.world.getTotalWorldTime();
		if(totalTime<=24000l*getSafeDayLength()) {
//			System.out.println(getSafeDayLength()+" "+totalTime);
			return ; //世界安全时间
		}
		int time = (int)(totalTime % 24000L);//当天的时间
		switch(time) {
		case 399:{
			OverworldData data=OverworldData.getGlobalData(ev.world);
			data.setChanged(false);
			break;
		}
		case 400:{//400tick：开始生成特殊事件
			OverworldData data=OverworldData.getGlobalData(ev.world);
//			System.out.println("10 tick now");
			if(!data.hasChanged()) {//还没改变，防止世界时间停止，而不停刷新
				data.setChanged(true);
//				System.out.println("changed");
				rand.setSeed(ev.world.getSeed() + totalTime);
				if(data.isZombossDefeated()||rand.nextInt(100)<getAttackChance(totalTime)) {//可以攻击
					data.setAttack(true);
					System.out.println("start attack!");
					activateEvent(ev.world, SpecialEvents.NORMAL_ZOMBIE);
					activateEvent(ev.world, SpecialEvents.DAY_ZOMBIE);
					if (rand.nextInt(ConfigurationUtil.MainConfig.eventSettings.plantZombieEventChance)==0) {
						activateEvent(ev.world, SpecialEvents.PLANT_ZOMBIE);
				    }
			        if (rand.nextInt(ConfigurationUtil.MainConfig.eventSettings.miniZombieEventChance)==0) {
				        activateEvent(ev.world, SpecialEvents.MINI_ZOMBIE);
			        }
			        if (rand.nextInt(ConfigurationUtil.MainConfig.eventSettings.invisZombieEventChance)==0) {
				        activateEvent(ev.world, SpecialEvents.INVIS_ZOMBIE);
			        }
			        for(EntityPlayer pl:ev.world.playerEntities) {
			        	pl.sendMessage(StringUtil.getColourLocale("message.huge_wave", TextFormatting.DARK_RED));
			        	ev.world.playSound(null, pl.posX, pl.posY, pl.posZ, SoundsHandler.HUGE_WAVE, SoundCategory.AMBIENT, 1.0f, 1.0f);
			        }
				}
			}
			break;
		}
		case 11599:{
			OverworldData data=OverworldData.getGlobalData(ev.world);
			data.setChanged(false);
			break;
		}
		case 11600:{//11800 tick:白天结束 白天僵尸消失
			OverworldData data=OverworldData.getGlobalData(ev.world);
//			System.out.println("day");
			if(!data.hasChanged()) {
			    data.setChanged(true);
//			    System.out.println("day!");
			    deactivateEvent(ev.world, SpecialEvents.DAY_ZOMBIE);
			}
			break;
		}
		case 12399:{
			OverworldData data=OverworldData.getGlobalData(ev.world);
			data.setChanged(false);
			break;
		}
		case 12400:{//12020 tick:夜晚开始 夜晚僵尸生成
			OverworldData data=OverworldData.getGlobalData(ev.world);
//			System.out.println("night");
			if(!data.hasChanged()) {
				data.setChanged(true);
				if(data.isAttack()) {
//					System.out.println("night!");
					activateEvent(ev.world, SpecialEvents.NIGHT_ZOMBIE);
				}
			}
			break;
		}
		case 23599:{
			OverworldData data=OverworldData.getGlobalData(ev.world);
			data.setChanged(false);
			break;
		}
		case 23600:{//23800 tick:一天结束，取消所有生成
			OverworldData data=OverworldData.getGlobalData(ev.world);
			if(!data.hasChanged()) {
			    data.setChanged(false);
			    deactivateEvent(ev.world, SpecialEvents.NORMAL_ZOMBIE);
			    deactivateEvent(ev.world, SpecialEvents.NIGHT_ZOMBIE);
			    deactivateEvent(ev.world, SpecialEvents.PLANT_ZOMBIE);
			    deactivateEvent(ev.world, SpecialEvents.MINI_ZOMBIE);
			    deactivateEvent(ev.world, SpecialEvents.INVIS_ZOMBIE);
			    data.setAttack(false);
			}
			break;
		}
		}
	}
	
	public static void activateEvent(World world, @Nonnull SpecialEvents event) {
		OverworldData data=OverworldData.getGlobalData(world);
		if (!data.hasEvent(event)) {
			data.add(event);
			switch(event) {
			case NORMAL_ZOMBIE:
			case DAY_ZOMBIE:
			case NIGHT_ZOMBIE:
			case PLANT_ZOMBIE:{
				EntitySpawnRegister.addEventSpawns(event);
				break;
			}
			default:break;
			}
			switch(event) {
			case PLANT_ZOMBIE:
			case MINI_ZOMBIE:
			case INVIS_ZOMBIE:{
				ITextComponent message = getEventMessage(event, false);
				for (EntityPlayer pl : world.playerEntities) {
					pl.sendMessage(message);
				}
				break;
			}
			default:break;
			}
		}
	}
	
	public static void deactivateEvent(World world,@Nonnull SpecialEvents event)
	{
		OverworldData data=OverworldData.getGlobalData(world);
		if(data.hasEvent(event)) {
			data.remove(event);
			switch(event) {
			case NORMAL_ZOMBIE:
			case DAY_ZOMBIE:
			case NIGHT_ZOMBIE:
			case PLANT_ZOMBIE:{
				EntitySpawnRegister.removeEventSpawns(event);
				break;
			}
			default:break;
			}
			switch(event) {
			case PLANT_ZOMBIE:
			case MINI_ZOMBIE:
			case INVIS_ZOMBIE:{
				ITextComponent message = getEventMessage(event, true);
				for (EntityPlayer pl : world.playerEntities) {
				   pl.sendMessage(message);
			    }
				break;
			}
			default:break;
			}
		}
	}
	
	private static ITextComponent getEventMessage(SpecialEvents event, boolean isEnding) {
		switch (event) {
			case PLANT_ZOMBIE:
				return StringUtil.getColourLocale("message.event." + (isEnding ? "end" : "start") + ".plantZombieEvent", TextFormatting.DARK_GREEN);
			case MINI_ZOMBIE:
			    return StringUtil.getColourLocale("message.event." + (isEnding ? "end" : "start") + ".miniZombieEvent", TextFormatting.DARK_GRAY);
			case INVIS_ZOMBIE:
			    return StringUtil.getColourLocale("message.event." + (isEnding ? "end" : "start") + ".invisZombieEvent", TextFormatting.DARK_BLUE);
			default:
				return null;
		}
	}

	private static void hugeWave(World world,int type)
	{
		for(EntityPlayer player:world.playerEntities) {
			player.sendMessage(StringUtil.getColourLocale("message.huge_wave", TextFormatting.DARK_RED));
			double x=player.posX;
			double y=player.posY;
			double z=player.posZ;
			world.playSound(null, x, y, z, SoundsHandler.HUGE_WAVE, SoundCategory.AMBIENT, 4f, 1f);
			if(!player.isCreative()&&!player.isSpectator()) {//一大波僵尸可进攻该玩家
				BlockPos block = getBlock(world, x, y, z);
				summonZombie(world, block, 10, 1);
				summonZombie(world, block, 3, 2);
				summonZombie(world, block, 1, 3);
				summonZombie(world, block, 1, 4);
			}
		}
	}
	
	private static void summonZombie(World world,BlockPos block,int num,int type)
	{
		for(int i=1;i<=num;i++) {
			EntityNormalZombie zombie=null;
	        if(type==1) zombie=new EntityNormalZombie(world);
	        else if(type==2) zombie=new EntityConeHeadZombie(world);
	        else if(type==3) zombie=new EntityBucketHeadZombie(world);
	        else if(type==4) zombie=new EntityFlagZombie(world);
	        if(zombie!=null) {
	        	zombie.setPosition(block.getX(), block.getY(), block.getZ());
			    world.spawnEntity(zombie);
	        }
		}
	}
	
	private static BlockPos getBlock(World world,double x,double y,double z)
	{
		Random rand=new Random();
		while(true) {
			int dx=(rand.nextInt(20)+0)*((rand.nextInt(1)==1)?1:-1);//40 - 80
			int dz=(rand.nextInt(20)+0)*((rand.nextInt(1)==1)?1:-1);
			int nowX=MathHelper.floor(x)+dx;
			int nowZ=MathHelper.floor(z)+dz;
			int nowY=world.getHeight();
			
			if(Math.abs(dx)+Math.abs(dz)<=80) {
				while(nowY-->=0) {
				    Block block = world.getBlockState(new BlockPos(nowX,nowY,nowZ)).getBlock();
				    if(block!=Blocks.AIR) break;
			    }
				return new BlockPos(nowX,nowY+1,nowZ);
			}
		}
	}
	
	/**
	 * 获取进攻概率
	 */
	public static int getAttackChance(long time)
	{
		int dif=ConfigurationUtil.getPVZDifficulty();
		int chance=20+dif*20;
		return chance;
	}
	
	public static int getSafeDayLength()
	{
		int dif=ConfigurationUtil.getPVZDifficulty();
		return 3-dif;
	}
}
