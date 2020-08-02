package com.hungteen.pvz.register;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.item.PVZSpawnEggItem;

import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@EventBusSubscriber(modid = PVZMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class RegistryHandler {

	public static void init()
	{
		ItemRegister.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		BlockRegister.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
		EntityRegister.ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
		SoundRegister.SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());
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
