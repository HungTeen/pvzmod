package com.hungteen.pvzmod.registry;

import com.hungteen.pvzmod.capability.CapabilityHandler;
import com.hungteen.pvzmod.capability.handler.PVZPlayerCapability;
import com.hungteen.pvzmod.capability.interfaces.ICapabilityPVZPlayer;
import com.hungteen.pvzmod.capability.storage.PVZPlayerStorage;
import com.hungteen.pvzmod.command.PVZCommandPlantLvl;
import com.hungteen.pvzmod.event.BlockEvents;
import com.hungteen.pvzmod.event.ConfigurationEvents;
import com.hungteen.pvzmod.event.EntityEvents;
import com.hungteen.pvzmod.event.PlayerEvents;
import com.hungteen.pvzmod.event.TreeEvents;
import com.hungteen.pvzmod.event.WorldEvents;
import com.hungteen.pvzmod.gui.GuiHandler;
import com.hungteen.pvzmod.packet.PacketHandler;
import com.hungteen.pvzmod.util.interfaces.IHasModel;
import com.hungteen.pvzmod.world.gen.WorldGenStructures;
import com.hungteen.pvzmod.world.gen.WorldGenOres;
import com.hungteen.pvzmod.world.gen.WorldGenOrigins;
import com.hungteen.pvzmod.world.gen.WorldGenTrees;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber
public class RegistryHandler {

	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(ItemRegister.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().registerAll(BlockRegister.BLOCKS.toArray(new Block[0]));
	}
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void onModelRegister(ModelRegistryEvent event)
	{
		for(Item item:ItemRegister.ITEMS)
		{
			if(item instanceof IHasModel)
			{
				((IHasModel) item).registerModels();
			}
		}
		
		for(Block block:BlockRegister.BLOCKS)
		{
			if(block instanceof IHasModel)
			{
				((IHasModel) block).registerModels();
			}
		}
	}
	
	public static void preInitRegistries()
	{
		EntityRegister.registerEntities();
		EntitySpawnRegister.registerEntitySpawn();
		new CreativeTabRegister();
		registerCapabilities();
		registerGenerators();
		PotionRegister.registerPotions();
	}
	
	public static void initRegistries()
	{
		new GuiHandler();
		registerEvents();
		PacketHandler.init();
		RecipeRegister.init();
	}
	
	public static void postInitRegistries()
	{
		
	}
	
	public static void serverRegistries(FMLServerStartingEvent ev)
	{
		ev.registerServerCommand(new PVZCommandPlantLvl());
	}
	
	private static void registerGenerators()
	{
		GameRegistry.registerWorldGenerator(new WorldGenOres(),3);
		GameRegistry.registerWorldGenerator(new WorldGenTrees(), 15);
		GameRegistry.registerWorldGenerator(new WorldGenOrigins(), 30);
		GameRegistry.registerWorldGenerator(new WorldGenStructures(), 0);
	}
	
	private static void registerEvents()
	{
		MinecraftForge.EVENT_BUS.register(new PlayerEvents());
		MinecraftForge.EVENT_BUS.register(new WorldEvents());
		MinecraftForge.EVENT_BUS.register(new EntityEvents());
		MinecraftForge.EVENT_BUS.register(new BlockEvents());
		MinecraftForge.EVENT_BUS.register(new ConfigurationEvents());
		MinecraftForge.TERRAIN_GEN_BUS.register(new TreeEvents());
	}
	
	private static void registerCapabilities()
	{
		CapabilityManager.INSTANCE.register(ICapabilityPVZPlayer.class, new PVZPlayerStorage(), PVZPlayerCapability.class);
		MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
	}
	
	
}
