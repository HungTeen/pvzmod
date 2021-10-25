package com.hungteen.pvz.common.event;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.client.gui.search.SearchOption;
import com.hungteen.pvz.common.capability.CapabilityHandler;
import com.hungteen.pvz.common.capability.player.PlayerDataManager;
import com.hungteen.pvz.common.enchantment.EnchantmentRegister;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.event.events.PlayerLevelUpEvent;
import com.hungteen.pvz.common.event.events.SummonCardUseEvent;
import com.hungteen.pvz.common.event.handler.BlockEventHandler;
import com.hungteen.pvz.common.event.handler.PlayerEventHandler;
import com.hungteen.pvz.common.item.spawn.card.PlantCardItem;
import com.hungteen.pvz.common.item.tool.plant.PeaGunItem;
import com.hungteen.pvz.common.network.PVZPacketHandler;
import com.hungteen.pvz.common.network.toclient.OtherStatsPacket;
import com.hungteen.pvz.compat.patchouli.PatchouliHandler;
import com.hungteen.pvz.register.BlockRegister;
import com.hungteen.pvz.register.EffectRegister;
import com.hungteen.pvz.register.ItemRegister;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.enums.Resources;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;

@Mod.EventBusSubscriber(modid=PVZMod.MOD_ID)
public class PVZPlayerEvents {

	@SubscribeEvent
	public static void tickPlayer(TickEvent.PlayerTickEvent ev) {
		if(! ev.player.level.isClientSide) {
			//for pea gun
			ItemStack stack = ev.player.getItemBySlot(EquipmentSlotType.HEAD);
			if(stack.getItem() instanceof PeaGunItem && !ev.player.getCooldowns().isOnCooldown(stack.getItem())) {
			    ((PeaGunItem)stack.getItem()).checkAndShootPea(ev.player.level, ev.player, stack);
			    if(!ev.player.getCooldowns().isOnCooldown(stack.getItem())) {
			    	ev.player.getCooldowns().addCooldown(stack.getItem(), 200);//cool down for no pea
			    }
			}
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
			if(ev.player.horizontalCollision && ev.player.onClimbable()){
				//is on steel ladder.
				if(ev.player.level.getBlockState(ev.player.blockPosition()).getBlock().is(BlockRegister.STEEL_LADDER.get())) {
				    final Vector3d vec = ev.player.getDeltaMovement();
				    ev.player.setDeltaMovement(vec.x, 0.5F, vec.z);
				}
			}
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
	public static void onPlayerGetAdvancement(AdvancementEvent ev) {
		if(! ev.getPlayer().level.isClientSide) {
			if(ev.getAdvancement().getId().equals(StringUtil.prefix("adventure/root"))) {
				if(PVZConfig.COMMON_CONFIG.RuleSettings.GiveBeginnerReward.get()) {
					ev.getPlayer().addItem(new ItemStack(ItemRegister.PEA_SHOOTER_CARD.get()));
		    		ev.getPlayer().addItem(new ItemStack(ItemRegister.SUN_FLOWER_CARD.get()));
			    	ev.getPlayer().addItem(new ItemStack(ItemRegister.WALL_NUT_CARD.get()));
				    ev.getPlayer().addItem(new ItemStack(ItemRegister.POTATO_MINE_CARD.get()));
				}
				PatchouliHandler.giveInitialGuideBook(ev.getPlayer());
			}
		}
	}
	
	@SubscribeEvent
	public static void onPlayerBreakBlock(BlockEvent.BreakEvent ev) {
		BlockEventHandler.checkAndDropSeeds(ev);
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
	}
	
	@SubscribeEvent
	public static void onPlayerTreeLevelUp(PlayerLevelUpEvent.TreeLevelUpEvent ev) {
		if(! ev.getPlayer().level.isClientSide) {
			PlayerUtil.playClientSound(ev.getPlayer(), 9);
		    PlayerUtil.addResource(ev.getPlayer(), Resources.LOTTERY_CHANCE, 3);
		}
	}
	
	@SubscribeEvent
	public static void onPlayerPlantLevelUp(PlayerLevelUpEvent.PAZLevelUpEvent ev) {
		if(! ev.getPlayer().level.isClientSide) {
			PlayerUtil.addResource(ev.getPlayer(), Resources.TREE_XP, ev.getCurrentLevel() * 2);
		}
	}
	
	@SubscribeEvent
	public static void onSummonCardUse(SummonCardUseEvent ev) {
		PlayerEntity player = ev.getPlayer();
		if(! player.level.isClientSide) { //unlock almanac
			SearchOption a = null;
			if(ev.getItemStack().getItem() instanceof PlantCardItem) {// unlock plant card
			    IPlantType plant = ((PlantCardItem) ev.getItemStack().getItem()).plantType;
			    a = SearchOption.get(plant);
			}
			PlayerUtil.unLockAlmanac(player, a);
		}
	}
	
}
