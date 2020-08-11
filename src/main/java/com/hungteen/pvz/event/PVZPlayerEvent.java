package com.hungteen.pvz.event;

import java.util.Random;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.capabilities.CapabilityHandler;
import com.hungteen.pvz.capabilities.player.PlayerDataManager;
import com.hungteen.pvz.register.ItemRegister;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=PVZMod.MOD_ID)
public class PVZPlayerEvent {

	@SubscribeEvent
	public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent ev) {
//		PVZMod.LOGGER.debug("player logged!");
		PlayerEntity player = ev.getPlayer();
		if(!player.world.isRemote) {
//		    player.sendMessage(new StringTextComponent("welcome"));
		}
		if (player instanceof ServerPlayerEntity && !player.world.isRemote) {
			player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
				PlayerDataManager plData = l.getPlayerData();
				PlayerDataManager.PlayerStats playerStats = plData.getPlayerStats();
				for(Resources res:Resources.values()) {
					playerStats.sendPacket(player, res);
				}
//			    PlayerDataManager.PlantStats plantStats = plData.getPlantStats();
//				for (Plants plant : Plants.values()) {
//				    PacketHandler.CHANNEL.sendTo(new PacketPlantLvlData(plant,plantStats.getPlantLevel(plant),plantStats.getPlantXp(plant),plantStats.getIsPlantLocked(plant)), (EntityPlayerMP) ev.player);
//			    }
			});
		}
	}

	@SubscribeEvent
	public static void onPlayerClone(PlayerEvent.Clone ev) {
		PlayerEntity player = ev.getPlayer();
		if (!player.world.isRemote) {
			PlayerUtil.clonePlayerData(ev.getOriginal(),player);
		}
	}
	
	@SubscribeEvent
	public static void onPlayerBreakBlock(BlockEvent.BreakEvent ev) {
		PlayerEntity player=ev.getPlayer();
		BlockState state = ev.getState();
		BlockPos pos = ev.getPos();
//		System.out.println("event start");
		if(!player.world.isRemote) {
			if(state.getBlock()==Blocks.GRASS||state.getBlock()==Blocks.TALL_GRASS) {//break grass
				Random rand = new Random();
//				System.out.println(PVZConfig.COMMON_CONFIG.BLOCK_SETTINGS.breakBlock.peaDropChance.get());
				if(rand.nextInt(PVZConfig.COMMON_CONFIG.BLOCK_SETTINGS.breakBlock.peaDropChance.get())==0) {//drop pea 
//					System.out.println("chance right");
					player.world.addEntity(new ItemEntity(player.world,pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ItemRegister.PEA.get(),1)));
				}
			}
		}
	}
}
