package com.hungteen.pvz.register;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.capabilities.CapabilityHandler;
import com.hungteen.pvz.item.PVZSpawnEggItem;
import com.hungteen.pvz.item.armor.BucketArmorItem;
import com.hungteen.pvz.item.armor.ConeArmorItem;
import com.hungteen.pvz.network.PVZPacketHandler;

import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@EventBusSubscriber(modid = PVZMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class RegistryHandler {

	public static void init()
	{
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		ItemRegister.ITEMS.register(bus);
		BlockRegister.BLOCKS.register(bus);
		EntityRegister.ENTITY_TYPES.register(bus);
		SoundRegister.SOUNDS.register(bus);
		ParticleRegister.PARTICLE_TYPES.register(bus);
		PotionRegister.EFFECTS.register(bus);
		BiomeRegister.BIOMES.register(bus);
		FeatureRegister.FEATURES.register(bus);
	}
	
	@SubscribeEvent
    public static void setup(FMLCommonSetupEvent ev)
    {
    	CapabilityHandler.registerCapabilities();
    	PVZPacketHandler.init();
    	BiomeRegister.addBiomes();
    	BiomeRegister.addBiomeFeatures();
    	StructureRegister.registerStructureType();
    }
	
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent evt) {
		ConeArmorItem.initArmorModel();
		BucketArmorItem.initArmorModel();
	}
	
	/**
	 * Exists to work around a limitation with Spawn Eggs:
	 * Spawn Eggs require an EntityType, but EntityTypes are created AFTER Items.
	 * Therefore it is "impossible" for modded spawn eggs to exist.
	 * To get around this we have our own custom SpawnEggItem, but it needs
	 * some extra setup after Item and EntityType registration completes.
	 */
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onPostRegisterEntities(final RegistryEvent.Register<EntityType<?>> event) {
		PVZSpawnEggItem.initUnaddedEggs();
	}
}
