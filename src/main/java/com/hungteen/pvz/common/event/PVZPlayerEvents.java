package com.hungteen.pvz.common.event;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.events.PlayerLevelChangeEvent;
import com.hungteen.pvz.common.block.others.SteelLadderBlock;
import com.hungteen.pvz.common.capability.CapabilityHandler;
import com.hungteen.pvz.common.event.events.SummonCardUseEvent;
import com.hungteen.pvz.common.event.handler.PlayerEventHandler;
import com.hungteen.pvz.common.item.tool.plant.BowlingGloveItem;
import com.hungteen.pvz.common.item.tool.plant.PeaGunItem;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.common.potion.EffectRegister;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=PVZMod.MOD_ID)
public class PVZPlayerEvents {

	@SubscribeEvent
	public static void tickPlayer(TickEvent.PlayerTickEvent ev) {
		if(! ev.player.level.isClientSide) {
			PeaGunItem.checkHeadShoot(ev.player);
			ev.player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l) -> {
				if(l.getPlayerData().getOtherStats().playSoundTick > 0) {
				    -- l.getPlayerData().getOtherStats().playSoundTick;
				}
				if(l.getPlayerData().getOtherStats().updateGoodTick > 0) {
				    -- l.getPlayerData().getOtherStats().updateGoodTick;
				} else {
//					MysteryShopContainer.genNextGoods(ev.player);
				}
				int lightLvl = 0;
				if(ev.player.hasEffect(EffectRegister.LIGHT_EYE_EFFECT.get())) {
				     lightLvl = 1 + ev.player.getEffect(EffectRegister.LIGHT_EYE_EFFECT.get()).getAmplifier();
				}
//				if(lightLvl != l.getPlayerData().getOtherStats().lightLevel) {
//					PVZPacketHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> {
//					    return (ServerPlayerEntity) ev.player;
//				    }), new OtherStatsPacket(2, 0, lightLvl));
//					l.getPlayerData().getOtherStats().lightLevel = lightLvl;
//				}
			});
		} else {
			SteelLadderBlock.climbUp(ev.player);
		}
	}
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent ev) {
		PlayerEntity player = ev.getPlayer();
		if (! player.level.isClientSide) {
			PlayerEventHandler.onPlayerLogin(player);
		}
	}
	
	@SubscribeEvent
	public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent ev) {
		if (! ev.getPlayer().level.isClientSide) {
			PlayerEventHandler.onPlayerLogout(ev.getPlayer());
		}
	}

	@SubscribeEvent
	public static void onPlayerClone(PlayerEvent.Clone ev) {
		PlayerEventHandler.clonePlayerData(ev.getOriginal(), ev.getPlayer(), ev.isWasDeath());
	}
	
	@SubscribeEvent
	public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent ev) {
		if(! ev.getPlayer().level.isClientSide) {
			PlayerUtil.getOptManager(ev.getPlayer()).ifPresent(l -> l.syncToClient());
		}
	}
	
	@SubscribeEvent
	public static void onPlayerRespawn(PlayerEvent.PlayerChangedDimensionEvent ev) {
		if(! ev.getPlayer().level.isClientSide) {
			PlayerUtil.getOptManager(ev.getPlayer()).ifPresent(l -> l.syncToClient());
		}
	}
	
	@SubscribeEvent
	public static void onPlayerInteract(PlayerInteractEvent.EntityInteractSpecific ev) {
		if(! ev.getWorld().isClientSide && ev.getHand() == Hand.MAIN_HAND) {
			PlayerEventHandler.quickRemoveByPlayer(ev.getPlayer(), ev.getTarget(), ev.getPlayer().getMainHandItem());
			PlayerEventHandler.makeSuperMode(ev.getPlayer(), ev.getTarget(), ev.getPlayer().getMainHandItem());
		}
		BowlingGloveItem.onPickUp(ev);
	}
	
	@SubscribeEvent
	public static void onPlayerTreeLevelUp(PlayerLevelChangeEvent ev) {
		if(! ev.getPlayer().level.isClientSide && ev.isLevelUp()) {
			PlayerEventHandler.unLockPAZs(ev.getPlayer());
			PlayerUtil.playClientSound(ev.getPlayer(), SoundRegister.PLANT_GROW.get());
		    PlayerUtil.addResource(ev.getPlayer(), Resources.LOTTERY_CHANCE, 3);
		}
	}
	
	@SubscribeEvent
	public static void onPlayerPlantLevelUp(PlayerLevelChangeEvent.PAZLevelChangeEvent ev) {
		if(! ev.getPlayer().level.isClientSide) {
			PlayerUtil.addResource(ev.getPlayer(), Resources.TREE_XP, ev.getCurrentLevel() * 2);
		}
	}
	
	@SubscribeEvent
	public static void onSummonCardUse(SummonCardUseEvent ev) {
//		PlayerEntity player = ev.getPlayer();
//		if(! player.level.isClientSide) { //unlock almanac
//			SearchOption a = null;
//			if(ev.getHeldStack().getItem() instanceof PlantCardItem) {// unlock plant card
//			    IPlantType plant = ((PlantCardItem) ev.getHeldStack().getItem()).plantType;
//			    a = SearchOption.get(plant);
//			}
//			PlayerUtil.unLockAlmanac(player, a);
//		}
	}
	
}
