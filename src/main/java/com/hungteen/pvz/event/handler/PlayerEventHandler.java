package com.hungteen.pvz.event.handler;

import com.hungteen.pvz.capability.CapabilityHandler;
import com.hungteen.pvz.capability.player.PlayerDataManager;
import com.hungteen.pvz.enchantment.EnchantmentUtil;
import com.hungteen.pvz.entity.drop.SunEntity;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.gui.search.SearchOption;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.Resources;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

public class PlayerEventHandler {
	
	/**
	 * run when player right click plantEntity with shovel.
	 */
	public static void onPlantShovelByPlayer(PlayerEntity player, PVZPlantEntity plant, ItemStack stack) {
		if(player.abilities.isCreativeMode || player.getUniqueID().equals(plant.getOwnerUUID().get()) || ! EntityUtil.checkCanEntityAttack(plant, player)) {
			if(plant.getOuterPlantType().isPresent()) {//has outer plant, shovel outer plant.
				SunEntity.spawnSunsByAmount(player.world, plant.getPosition(), EnchantmentUtil.getSunShovelAmount(stack, plant.outerSunCost));
				plant.removeOuterPlant();
			} else {
				SunEntity.spawnSunsByAmount(player.world, plant.getPosition(), EnchantmentUtil.getSunShovelAmount(stack, plant.plantSunCost));
				plant.remove();
			}
			EntityUtil.playSound(plant, SoundRegister.PLANT_ON_GROUND.get());
			stack.damageItem(3, player, (p) -> {p.sendBreakAnimation(Hand.MAIN_HAND);});
		}
	}
	
	public static void onPlayerKillZombie(PlayerEntity player, Zombies zombie) {
		PlayerUtil.addPlayerStats(player, Resources.KILL_COUNT, 1);
	}
	
	/**
	 * send packet from server to client to sync player's data.
	 */
	public static void onPlayerLogin(PlayerEntity player) {
		player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
			PlayerDataManager plData = l.getPlayerData();
			//resources
			PlayerDataManager.PlayerStats playerStats = plData.getPlayerStats();
			for(Resources res:Resources.values()) {
				playerStats.sendPacket(player, res);
			}
			//plants
		    PlayerDataManager.PlantStats plantStats = plData.getPlantStats();
			for (Plants plant : Plants.values()) {
			   plantStats.sendPlantPacket(player, plant);
		    }
			//almanacs
			SearchOption.OPTION.forEach((a) -> {
				ServerPlayerEntity serverplayer = (ServerPlayerEntity) player;
				if(PlayerUtil.isAlmanacUnlocked(serverplayer, a)) {
					PlayerUtil.unLockAlmanac(serverplayer, a);
				}
			});
			//item cd
			PlayerDataManager.ItemCDStats itemCDStats = plData.getItemCDStats();
			for(Plants p : Plants.values()) {
				player.getCooldownTracker().setCooldown(PlantUtil.getPlantSummonCard(p), itemCDStats.getPlantCardCD(p));
			}
		});
	}
	
}
