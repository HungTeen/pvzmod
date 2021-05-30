package com.hungteen.pvz.common.event;

import java.util.Optional;
import java.util.Random;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.client.gui.search.SearchOption;
import com.hungteen.pvz.common.capability.CapabilityHandler;
import com.hungteen.pvz.common.capability.player.PlayerDataManager;
import com.hungteen.pvz.common.capability.player.PlayerDataManager.PlayerStats;
import com.hungteen.pvz.common.container.shop.MysteryShopContainer;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.event.events.PlayerLevelUpEvent;
import com.hungteen.pvz.common.event.events.SummonCardUseEvent;
import com.hungteen.pvz.common.event.handler.PlayerEventHandler;
import com.hungteen.pvz.common.item.card.PlantCardItem;
import com.hungteen.pvz.common.item.tool.PeaGunItem;
import com.hungteen.pvz.common.network.OtherStatsPacket;
import com.hungteen.pvz.common.network.PVZPacketHandler;
import com.hungteen.pvz.register.EffectRegister;
import com.hungteen.pvz.register.EnchantmentRegister;
import com.hungteen.pvz.register.ItemRegister;
import com.hungteen.pvz.utils.ItemUtil;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.registries.ForgeRegistries;

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
					MysteryShopContainer.genNextGoods(ev.player);
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
		}
	}
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent ev) {
		PlayerEntity player = ev.getPlayer();
		if (! player.level.isClientSide && player instanceof ServerPlayerEntity) {
			PlayerEventHandler.onPlayerLogin(player);
		}
	}
	
	@SubscribeEvent
	public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent ev) {
		PlayerEntity player = ev.getPlayer();
		if (! player.level.isClientSide && player instanceof ServerPlayerEntity) {
			player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
				PlayerDataManager plData = l.getPlayerData();
				//item cd
				PlayerDataManager.ItemCDStats itemCDStats = plData.getItemCDStats();
				for(Plants p : Plants.values()) {
					itemCDStats.setPlantCardBar(p, player.getCooldowns().getCooldownPercent(PlantUtil.getPlantSummonCard(p), 0f));
				}
			});
		}
	}

	@SubscribeEvent
	public static void onPlayerClone(PlayerEvent.Clone ev) {
		PlayerEntity player = ev.getPlayer();
		if (! player.level.isClientSide) {
			PlayerUtil.clonePlayerData(ev.getOriginal(),player);
		}
	}
	
	@SubscribeEvent
	public static void onPlayerGetAdvancement(AdvancementEvent ev) {
		if(! ev.getPlayer().level.isClientSide) {
			if(ev.getAdvancement().getId().equals(StringUtil.prefix("adventure/root"))) {
				if(PVZConfig.COMMON_CONFIG.WorldSettings.GiveBeginnerReward.get()) {
					ev.getPlayer().addItem(new ItemStack(ItemRegister.PEA_SHOOTER_CARD.get()));
		    		ev.getPlayer().addItem(new ItemStack(ItemRegister.SUN_FLOWER_CARD.get()));
			    	ev.getPlayer().addItem(new ItemStack(ItemRegister.WALL_NUT_CARD.get()));
				    ev.getPlayer().addItem(new ItemStack(ItemRegister.POTATO_MINE_CARD.get()));
				}
				if(ModList.get().isLoaded(StringUtil.PATCHOULI)) {
					Optional.ofNullable(ForgeRegistries.ITEMS.getValue(ItemUtil.GUIDE_BOOK)).ifPresent(item -> {
						final ItemStack book = new ItemStack(item, 1);
						book.getOrCreateTag().putString("patchouli:book", "pvz:pvz_guide");
						ev.getPlayer().addItem(book);
					});
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void onPlayerBreakBlock(BlockEvent.BreakEvent ev) {
		PlayerEntity player=ev.getPlayer();
		BlockState state = ev.getState();
		BlockPos pos = ev.getPos();
		if(! player.level.isClientSide) {
			if(state.getBlock()==Blocks.GRASS || state.getBlock()==Blocks.TALL_GRASS) {//break grass
				Random rand = new Random();
				if(rand.nextInt(PVZConfig.COMMON_CONFIG.BlockSettings.BreakBlock.PeaDropChance.get()) == 0) {//drop pea 
					player.level.addFreshEntity(new ItemEntity(player.level,pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ItemRegister.PEA.get(), 1)));
				}
				if(rand.nextInt(PVZConfig.COMMON_CONFIG.BlockSettings.BreakBlock.CabbageDropChance.get()) == 0) {//drop cabbage
					player.level.addFreshEntity(new ItemEntity(player.level,pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ItemRegister.CABBAGE_SEEDS.get(), 1)));
				}
			}
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
								PlayerStats stats = l.getPlayerData().getPlayerStats();
								if(stats.getPlayerStats(Resources.ENERGY_NUM) >= 1) {
									stats.addPlayerStats(Resources.ENERGY_NUM, - 1);
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
		    PlayerUtil.addPlayerStats(ev.getPlayer(), Resources.LOTTERY_CHANCE, 3);
		}
	}
	
	@SubscribeEvent
	public static void onPlayerPlantLevelUp(PlayerLevelUpEvent.PlantLevelUpEvent ev) {
		if(! ev.getPlayer().level.isClientSide) {
			PlayerUtil.addPlayerStats(ev.getPlayer(), Resources.TREE_XP, ev.getCurrentLevel() * 2);
		}
	}
	
	@SubscribeEvent
	public static void onSummonCardUse(SummonCardUseEvent ev) {
		PlayerEntity player = ev.getPlayer();
		if(! player.level.isClientSide) { //unlock almanac
			SearchOption a = null;
			if(ev.getItemStack().getItem() instanceof PlantCardItem) {// unlock plant card
			    Plants plant = ((PlantCardItem) ev.getItemStack().getItem()).plantType;
			    a = SearchOption.get(plant);
			}
			PlayerUtil.unLockAlmanac(player, a);
		}
	}
	
}
