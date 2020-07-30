package com.hungteen.pvz.event;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.capabilities.CapabilityHandler;
import com.hungteen.pvz.capabilities.player.PVZGuiTabPlayerData;
import com.hungteen.pvz.capabilities.player.PlayerDataManager;
import com.hungteen.pvz.capabilities.player.PlayerDataProvider;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=PVZMod.MOD_ID)
public class PVZPlayerEvent {

	@SubscribeEvent
	public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent ev) {
		PVZMod.LOGGER.debug("player logged!");
		PlayerEntity player = ev.getPlayer();
		if(!player.world.isRemote) {
		    player.sendMessage(new StringTextComponent("welcome"));
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
	public static void rightClick(PlayerInteractEvent.RightClickBlock ev)
	{
//		PVZMod.LOGGER.debug("112");
		PlayerEntity player=ev.getPlayer();
		if(!player.world.isRemote) {
			if(player.getHeldItemMainhand().getItem()==Items.DIAMOND_SWORD) {
				player.sendMessage(new StringTextComponent("lvl:"+PVZGuiTabPlayerData.getPlayerStats(Resources.SUN_NUM)));
			}
		}
	}
}
