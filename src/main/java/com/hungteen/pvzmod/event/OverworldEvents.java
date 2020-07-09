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
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class OverworldEvents{

	private static final Random rand = new Random();
	
	public static void doTickCheck(TickEvent.WorldTickEvent ev)
	{
		if (ev.world.getTotalWorldTime() <= 24000L) return; //第一天莫得
		int time = (int)(ev.world.getWorldTime() % 24000L);
		switch(time) {
		case 500:
			rand.setSeed(ev.world.getSeed() + ev.world.getTotalWorldTime());
			if (rand.nextInt(ConfigurationUtil.MainConfig.eventSettings.plantZombieDayChance)==0)
				activateEvent(ev.world, SpecialEvents.PLANTZOMBIE_DAY);
			if (rand.nextInt(ConfigurationUtil.MainConfig.eventSettings.smallZombieDayChance)==0)
				activateEvent(ev.world, SpecialEvents.SMALLZOMBIE_DAY);
			if (rand.nextInt(ConfigurationUtil.MainConfig.eventSettings.invisZombieDayChance)==0)
				activateEvent(ev.world, SpecialEvents.INVISZOMBIE_DAY);
			break;
		case 6000:
			hugeWave(ev.world,1);
			break;
		case 18000:
			hugeWave(ev.world,2);
			break;
		case 23500:
			deactivateEvent(ev.world, SpecialEvents.PLANTZOMBIE_DAY);
			deactivateEvent(ev.world, SpecialEvents.SMALLZOMBIE_DAY);
			deactivateEvent(ev.world, SpecialEvents.INVISZOMBIE_DAY);
			break;
		}
	}
	
	public static void activateEvent(World world, @Nonnull SpecialEvents event) {
		OverworldData data=OverworldData.getGlobalData(world);
		if (!data.hasEvent(event)) {
			data.add(event);
			if(event==SpecialEvents.PLANTZOMBIE_DAY)
			    EntitySpawnRegister.addEventSpawns(event);

			ITextComponent message = getEventMessage(event, false);
			SoundEvent sound = null;
			
			//sound = SoundsHandler.eventSpecialDayStart;

			for (EntityPlayer pl : world.playerEntities) {
				pl.sendMessage(message);

				if (sound != null)
					world.playSound(null, pl.posX, pl.posY, pl.posZ, sound, SoundCategory.AMBIENT, 1.0f, 1.0f);
			}
		}
	}
	
	public static void deactivateEvent(World world,@Nonnull SpecialEvents event)
	{
		OverworldData data=OverworldData.getGlobalData(world);
		if(data.hasEvent(event)) {
			data.remove(event);
			ITextComponent message = getEventMessage(event, true);
			if(event==SpecialEvents.PLANTZOMBIE_DAY)
			    EntitySpawnRegister.removeEventSpawns(event);
			for (EntityPlayer pl : world.playerEntities) {
				pl.sendMessage(message);
			}
		}
	}
	
	private static ITextComponent getEventMessage(SpecialEvents event, boolean isEnding) {
		switch (event) {
			case PLANTZOMBIE_DAY:
				return StringUtil.getColourLocale("message.event." + (isEnding ? "end" : "start") + ".plantZombieDay", TextFormatting.DARK_GREEN);
			case SMALLZOMBIE_DAY:
			    return StringUtil.getColourLocale("message.event." + (isEnding ? "end" : "start") + ".smallZombieDay", TextFormatting.DARK_GRAY);
			case INVISZOMBIE_DAY:
			    return StringUtil.getColourLocale("message.event." + (isEnding ? "end" : "start") + ".invisZombieDay", TextFormatting.DARK_BLUE);
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
}
