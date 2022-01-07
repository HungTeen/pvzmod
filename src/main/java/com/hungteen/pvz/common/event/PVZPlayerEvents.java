package com.hungteen.pvz.common.event;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.events.PlayerLevelChangeEvent;
import com.hungteen.pvz.common.block.others.SteelLadderBlock;
import com.hungteen.pvz.common.capability.CapabilityHandler;
import com.hungteen.pvz.common.capability.player.PlayerDataManager;
import com.hungteen.pvz.common.enchantment.EnchantmentRegister;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.event.events.SummonCardUseEvent;
import com.hungteen.pvz.common.event.handler.PlayerEventHandler;
import com.hungteen.pvz.common.item.tool.plant.BowlingGloveItem;
import com.hungteen.pvz.common.item.tool.plant.PeaGunItem;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.common.network.PVZPacketHandler;
import com.hungteen.pvz.common.network.toclient.OtherStatsPacket;
import com.hungteen.pvz.common.potion.EffectRegister;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;

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
				if(lightLvl != l.getPlayerData().getOtherStats().lightLevel) {
					PVZPacketHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> {
					    return (ServerPlayerEntity) ev.player;
				    }), new OtherStatsPacket(2, 0, lightLvl));
					l.getPlayerData().getOtherStats().lightLevel = lightLvl;
				}
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
		if (! ev.getPlayer().level.isClientSide) {
			PlayerEventHandler.clonePlayerData(ev.getOriginal(), ev.getPlayer(), ev.isWasDeath());
		}
	}
	
	@SubscribeEvent
	public static void onPlayerInteract(PlayerInteractEvent.EntityInteractSpecific ev) {
		World world = ev.getWorld();
		PlayerEntity player = ev.getPlayer();
		if(! world.isClientSide && ev.getHand() == Hand.MAIN_HAND) {
			Entity entity = ev.getTarget();
			if(entity instanceof PVZPlantEntity && entity.isAlive()) {//still alive
				PVZPlantEntity plant = (PVZPlantEntity) entity;
				ItemStack stack = player.getMainHandItem();
				if(stack.getItem() instanceof SwordItem) {
					if(EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegister.ENERGY_TRANSFER.get(), stack) > 0) {
						if(plant.canStartSuperMode()){
							player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l) -> {
								PlayerDataManager stats = l.getPlayerData();
								if(stats.getResource(Resources.ENERGY_NUM) >= 1) {
									stats.addResource(Resources.ENERGY_NUM, - 1);
								    plant.startSuperMode(true);
								}
							});
						}
						
					}
				} else if(stack.getItem() instanceof ShovelItem) {
					PlayerEventHandler.onPlantShovelByPlayer(player, plant, stack);
				}
			}
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
//			if(ev.getItemStack().getItem() instanceof PlantCardItem) {// unlock plant card
//			    IPlantType plant = ((PlantCardItem) ev.getItemStack().getItem()).plantType;
//			    a = SearchOption.get(plant);
//			}
//			PlayerUtil.unLockAlmanac(player, a);
//		}
	}
	
}
