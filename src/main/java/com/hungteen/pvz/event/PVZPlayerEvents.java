package com.hungteen.pvz.event;

import java.util.Random;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.capabilities.player.PlayerDataManager;
import com.hungteen.pvz.capabilities.player.PlayerDataManager.PlayerStats;
import com.hungteen.pvz.capability.CapabilityHandler;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.event.events.SummonCardUseEvent;
import com.hungteen.pvz.item.tool.PeaGunItem;
import com.hungteen.pvz.item.tool.card.PlantCardItem;
import com.hungteen.pvz.register.EnchantmentRegister;
import com.hungteen.pvz.register.ItemRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlayerUtil;
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
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
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
		if (player instanceof ServerPlayerEntity && !player.world.isRemote) {
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
				if(rand.nextInt(PVZConfig.COMMON_CONFIG.BlockSettings.BreakBlock.PeaDropChance.get())==0) {//drop pea 
//					System.out.println("chance right");
					player.world.addEntity(new ItemEntity(player.world,pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ItemRegister.PEA.get(),1)));
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void onPlayerInteract(PlayerInteractEvent.EntityInteractSpecific ev){
		World world = ev.getWorld();
		PlayerEntity player = ev.getPlayer();
		if(!world.isRemote) {
			Entity entity = ev.getTarget();
			if(entity instanceof PVZPlantEntity) {
				PVZPlantEntity plant = (PVZPlantEntity) entity;
				ItemStack stack = player.getHeldItemMainhand();
				if(stack.getItem() instanceof SwordItem) {
					if(EnchantmentHelper.getEnchantmentLevel(EnchantmentRegister.ENERGY_TRANSFER.get(), stack)>0) {
						if(plant.canStartSuperMode()){
							player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
								PlayerStats stats = l.getPlayerData().getPlayerStats();
								if(stats.getPlayerStats(Resources.ENERGY_NUM)>=1) {
									stats.addPlayerStats(Resources.ENERGY_NUM, -1);
								    plant.startSuperMode(true);
								}
							});
						}
					}
				}else if(stack.getItem() instanceof ShovelItem) {
					if(player.abilities.isCreativeMode||player.getUniqueID().equals(plant.getOwnerUUID())||!EntityUtil.checkCanEntityAttack(plant, player)) {
						plant.playSound(SoundEvents.BLOCK_GRASS_BREAK, 1f, 1f);
						plant.remove();
						stack.damageItem(3, player, (p) -> {p.sendBreakAnimation(ev.getHand());});
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void onSummonCardUse(SummonCardUseEvent ev) {
//		System.out.println(ev.getItemStack().getItem());
		PlayerEntity player = ev.getPlayer();
		Almanacs a = null;
		if(!player.world.isRemote) {
			if(ev.getItemStack().getItem() instanceof PlantCardItem) {// unlock plant card
			    Plants plant = ((PlantCardItem) ev.getItemStack().getItem()).getPlant();
			    a = Almanacs.getAlmanacByName(plant.toString().toLowerCase());
			}
		}
		if(!player.world.isRemote && a != null) {//unlock almanac
			PlayerUtil.unLockAlmanac(player, a);
		}
	}
	
}
