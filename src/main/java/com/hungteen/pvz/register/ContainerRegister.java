package com.hungteen.pvz.register;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.gui.PeaGunScreen;
import com.hungteen.pvz.gui.PlayerInventoryScreen;
import com.hungteen.pvz.gui.almanac.AlmanacContainer;
import com.hungteen.pvz.gui.almanac.AlmanacScreen;
import com.hungteen.pvz.gui.container.PeaGunContainer;
import com.hungteen.pvz.gui.container.PlayerInventoryContainer;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@EventBusSubscriber(modid = PVZMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ContainerRegister {

	public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = new DeferredRegister<>(ForgeRegistries.CONTAINERS, PVZMod.MOD_ID);
	
	public static final RegistryObject<ContainerType<PlayerInventoryContainer>> PLAYER_INVENTORY = CONTAINER_TYPES.register("player_inventory", ()->{
		return IForgeContainerType.create((windowId, inv, data) -> {
            return new PlayerInventoryContainer(windowId, inv.player);
        });
	});
	
	public static final RegistryObject<ContainerType<AlmanacContainer>> ALMANAC = CONTAINER_TYPES.register("almanac", ()->{
		return IForgeContainerType.create((windowId, inv, data) -> {
            return new AlmanacContainer(windowId, inv.player);
        });
	});
	
	public static final RegistryObject<ContainerType<PeaGunContainer>> PEA_GUN = CONTAINER_TYPES.register("pea_gun", ()->{
		return IForgeContainerType.create((windowId, inv, data) -> {
            return new PeaGunContainer(windowId, inv.player);
        });
	});
	
	@SubscribeEvent
    public static void onClientSetupEvent(FMLClientSetupEvent event) {
        ScreenManager.registerFactory(PLAYER_INVENTORY.get(), PlayerInventoryScreen::new);
        ScreenManager.registerFactory(ALMANAC.get(), AlmanacScreen::new);
        ScreenManager.registerFactory(PEA_GUN.get(), PeaGunScreen::new);
    }
	
}