package com.hungteen.pvz.event;

import java.util.Random;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.capability.CapabilityHandler;
import com.hungteen.pvz.capability.player.PlayerDataManager;
import com.hungteen.pvz.capability.player.PlayerDataManager.PlayerStats;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.event.events.SummonCardUseEvent;
import com.hungteen.pvz.event.handler.PlayerEventHandler;
import com.hungteen.pvz.item.tool.PeaGunItem;
import com.hungteen.pvz.item.tool.card.PlantCardItem;
import com.hungteen.pvz.register.EnchantmentRegister;
import com.hungteen.pvz.register.ItemRegister;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.enums.Almanacs;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=PVZMod.MOD_ID)
public class PVZPlayerEvents {

	@SubscribeEvent
	public static void tickPlayer(TickEvent.PlayerTickEvent ev) {
		if(!ev.player.world.isRemote) {
			//for pea gun
			ItemStack stack = ev.player.getItemStackFromSlot(EquipmentSlotType.HEAD);
			if(stack.getItem() instanceof PeaGunItem && !ev.player.getCooldownTracker().hasCooldown(stack.getItem())) {
			    ((PeaGunItem)stack.getItem()).checkAndShootPea(ev.player.world, ev.player, stack);
			    if(!ev.player.getCooldownTracker().hasCooldown(stack.getItem())) {
			    	ev.player.getCooldownTracker().setCooldown(stack.getItem(), 200);//cool down for no pea
			    }
			}
		}
	}
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent ev) {
		PlayerEntity player = ev.getPlayer();
		if (! player.world.isRemote && player instanceof ServerPlayerEntity) {
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
				PlayerDataManager.AlmanacStats almanacStats = plData.getAlmanacStats();
				for(Almanacs a: Almanacs.values()) {
					almanacStats.sendAlmanacPacket(player, a);
				}
				//item cd
				PlayerDataManager.ItemCDStats itemCDStats = plData.getItemCDStats();
				for(Plants p : Plants.values()) {
//					System.out.println(p.toString() + p.summonCard);
					player.getCooldownTracker().setCooldown(PlantUtil.getPlantSummonCard(p), itemCDStats.getPlantCardCD(p));
				}
			});
		}
	}
	
	@SubscribeEvent
	public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent ev) {
		PlayerEntity player = ev.getPlayer();
		if (! player.world.isRemote && player instanceof ServerPlayerEntity) {
			player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
				PlayerDataManager plData = l.getPlayerData();
				//item cd
				PlayerDataManager.ItemCDStats itemCDStats = plData.getItemCDStats();
				for(Plants p : Plants.values()) {
					itemCDStats.setPlantCardBar(p, player.getCooldownTracker().getCooldown(PlantUtil.getPlantSummonCard(p), 0f));
				}
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
	public static void onPlayerGetAdvancement(AdvancementEvent ev) {
		if(! ev.getPlayer().world.isRemote) {
			if(PVZConfig.COMMON_CONFIG.WorldSettings.GiveBeginnerReward.get() && ev.getAdvancement().getId().equals(StringUtil.prefix("root"))) {
				ev.getPlayer().addItemStackToInventory(new ItemStack(ItemRegister.PEA_SHOOTER_CARD.get()));
				ev.getPlayer().addItemStackToInventory(new ItemStack(ItemRegister.SUN_FLOWER_CARD.get()));
				ev.getPlayer().addItemStackToInventory(new ItemStack(ItemRegister.WALL_NUT_CARD.get()));
				ev.getPlayer().addItemStackToInventory(new ItemStack(ItemRegister.POTATO_MINE_CARD.get()));
			}
		}
	}
	
	@SubscribeEvent
	public static void onPlayerBreakBlock(BlockEvent.BreakEvent ev) {
		PlayerEntity player=ev.getPlayer();
		BlockState state = ev.getState();
		BlockPos pos = ev.getPos();
		if(! player.world.isRemote) {
			if(state.getBlock()==Blocks.GRASS || state.getBlock()==Blocks.TALL_GRASS) {//break grass
				Random rand = new Random();
				if(rand.nextInt(PVZConfig.COMMON_CONFIG.BlockSettings.BreakBlock.PeaDropChance.get())==0) {//drop pea 
					player.world.addEntity(new ItemEntity(player.world,pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ItemRegister.PEA.get(),1)));
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void onPlayerInteract(PlayerInteractEvent.EntityInteractSpecific ev){
		World world = ev.getWorld();
		PlayerEntity player = ev.getPlayer();
		if(! world.isRemote) {
			Entity entity = ev.getTarget();
			if(entity instanceof PVZPlantEntity) {
				PVZPlantEntity plant = (PVZPlantEntity) entity;
				ItemStack stack = player.getHeldItemMainhand();
				if(stack.getItem() instanceof SwordItem && entity.isAlive()) {
					if(EnchantmentHelper.getEnchantmentLevel(EnchantmentRegister.ENERGY_TRANSFER.get(), stack) > 0) {
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
//			else if(entity instanceof PVZZombieEntity) {
//				PVZZombieEntity zombie = (PVZZombieEntity) entity;
//				ItemStack stack = player.getHeldItemMainhand();
//				if(stack.getItem() instanceof SwordItem) {
//					zombie.setZombieType(PVZZombieEntity.Type.SUPER);
//				}
//			}
		}
	}
	
	@SubscribeEvent
	public static void onSummonCardUse(SummonCardUseEvent ev) {
//		System.out.println(ev.getItemStack().getItem());
		PlayerEntity player = ev.getPlayer();
//		Almanacs a = null;
//		if(! player.world.isRemote) {
//			if(ev.getItemStack().getItem() instanceof PlantCardItem) {// unlock plant card
//			    Plants plant = ((PlantCardItem) ev.getItemStack().getItem()).plantType;
//			    a = Enum.valueOf(Almanacs.class, plant.toString());
//			}
//		}
//		if(! player.world.isRemote && a != null) {//unlock almanac
//			PlayerUtil.unLockAlmanac(player, a);
//		}
	}
	
}
