package com.hungteen.pvz.register;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.gui.almanac.AlmanacContainer;
import com.hungteen.pvz.gui.almanac.AlmanacScreen;
import com.hungteen.pvz.gui.container.DaveShopContainer;
import com.hungteen.pvz.gui.container.PeaGunContainer;
import com.hungteen.pvz.gui.container.PlayerInventoryContainer;
import com.hungteen.pvz.gui.container.SunConverterContainer;
import com.hungteen.pvz.gui.container.SunShopContainer;
import com.hungteen.pvz.gui.screen.DaveShopScreen;
import com.hungteen.pvz.gui.screen.PeaGunScreen;
import com.hungteen.pvz.gui.screen.PlayerInventoryScreen;
import com.hungteen.pvz.gui.screen.SunConverterScreen;
import com.hungteen.pvz.gui.screen.SunShopScreen;

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
	
	public static final RegistryObject<ContainerType<PlayerInventoryContainer>> PLAYER_INVENTORY = CONTAINER_TYPES.register("player_inventory", () -> {
		return IForgeContainerType.create((windowId, inv, data) -> {
            return new PlayerInventoryContainer(windowId, inv.player);
        });
	});
	
	public static final RegistryObject<ContainerType<AlmanacContainer>> ALMANAC = CONTAINER_TYPES.register("almanac", () -> {
		return IForgeContainerType.create((windowId, inv, data) -> {
            return new AlmanacContainer(windowId, inv.player);
        });
	});
	
	public static final RegistryObject<ContainerType<PeaGunContainer>> PEA_GUN = CONTAINER_TYPES.register("pea_gun", () -> {
		return IForgeContainerType.create((windowId, inv, data) -> {
            return new PeaGunContainer(windowId, inv.player);
        });
	});
	
	public static final RegistryObject<ContainerType<DaveShopContainer>> DAVE_SHOP = CONTAINER_TYPES.register("dave_shop", () -> {
		return IForgeContainerType.create((windowId, inv, data) -> {
            return new DaveShopContainer(windowId, inv.player);
        });
	});
	
	public static final RegistryObject<ContainerType<SunShopContainer>> SUN_SHOP = CONTAINER_TYPES.register("sun_shop", () -> {
		return IForgeContainerType.create((windowId, inv, data) -> {
            return new SunShopContainer(windowId, inv.player);
        });
	});
	
	public static final RegistryObject<ContainerType<SunConverterContainer>> SUN_CONVERTER = CONTAINER_TYPES.register("sun_converter", () -> {
		return IForgeContainerType.create((windowId, inv, data) -> {
            return new SunConverterContainer(windowId, inv.player, data.readBlockPos());
        });
	});
	
	@SubscribeEvent
    public static void onClientSetupEvent(FMLClientSetupEvent event) {
        ScreenManager.registerFactory(PLAYER_INVENTORY.get(), PlayerInventoryScreen::new);
        ScreenManager.registerFactory(ALMANAC.get(), AlmanacScreen::new);
        ScreenManager.registerFactory(PEA_GUN.get(), PeaGunScreen::new);
        ScreenManager.registerFactory(DAVE_SHOP.get(), DaveShopScreen::new);
        ScreenManager.registerFactory(SUN_SHOP.get(), SunShopScreen::new);
        ScreenManager.registerFactory(SUN_CONVERTER.get(), SunConverterScreen::new);
    }
	
}