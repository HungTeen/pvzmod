package com.hungteen.pvz.register;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.gui.container.AlmanacContainer;
import com.hungteen.pvz.gui.container.CardFusionContainer;
import com.hungteen.pvz.gui.container.EssenceAltarContainer;
import com.hungteen.pvz.gui.container.FragmentSpliceContainer;
import com.hungteen.pvz.gui.container.ImitaterContainer;
import com.hungteen.pvz.gui.container.PeaGunContainer;
import com.hungteen.pvz.gui.container.PlayerInventoryContainer;
import com.hungteen.pvz.gui.container.SlotMachineContainer;
import com.hungteen.pvz.gui.container.SunConverterContainer;
import com.hungteen.pvz.gui.container.shop.DaveShopContainer;
import com.hungteen.pvz.gui.container.shop.MysteryShopContainer;
import com.hungteen.pvz.gui.container.shop.PennyShopContainer;
import com.hungteen.pvz.gui.container.shop.SunShopContainer;
import com.hungteen.pvz.gui.screen.AlmanacScreen;
import com.hungteen.pvz.gui.screen.CardFusionScreen;
import com.hungteen.pvz.gui.screen.EssenceAltarScreen;
import com.hungteen.pvz.gui.screen.FragmentSpliceScreen;
import com.hungteen.pvz.gui.screen.ImitaterScreen;
import com.hungteen.pvz.gui.screen.PeaGunScreen;
import com.hungteen.pvz.gui.screen.PlayerInventoryScreen;
import com.hungteen.pvz.gui.screen.SlotMachineScreen;
import com.hungteen.pvz.gui.screen.SunConverterScreen;
import com.hungteen.pvz.gui.screen.shop.DaveShopScreen;
import com.hungteen.pvz.gui.screen.shop.MysteryShopScreen;
import com.hungteen.pvz.gui.screen.shop.PennyShopScreen;
import com.hungteen.pvz.gui.screen.shop.SunShopScreen;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.IWorldPosCallable;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@EventBusSubscriber(modid = PVZMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ContainerRegister {

	public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS, PVZMod.MOD_ID);
	
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
	public static final RegistryObject<ContainerType<FragmentSpliceContainer>> FRAGMENT_SPLICE = CONTAINER_TYPES.register("fragment_splice", () -> {
		return IForgeContainerType.create((windowId, inv, data) -> {
            return new FragmentSpliceContainer(windowId, inv.player, data.readBlockPos());
        });
	});
	public static final RegistryObject<ContainerType<SlotMachineContainer>> SLOT_MACHINE = CONTAINER_TYPES.register("slot_machine", () -> {
		return IForgeContainerType.create((windowId, inv, data) -> {
            return new SlotMachineContainer(windowId, inv.player, data.readBlockPos());
        });
	});
	public static final RegistryObject<ContainerType<PennyShopContainer>> PENNY_SHOP = CONTAINER_TYPES.register("penny_shop", () -> {
		return IForgeContainerType.create((windowId, inv, data) -> {
            return new PennyShopContainer(windowId, inv.player);
        });
	});
	public static final RegistryObject<ContainerType<MysteryShopContainer>> MYSTERY_SHOP = CONTAINER_TYPES.register("mystery_shop", () -> {
		return IForgeContainerType.create((windowId, inv, data) -> {
            return new MysteryShopContainer(windowId, inv.player);
        });
	});
	public static final RegistryObject<ContainerType<EssenceAltarContainer>> ESSENCE_ALTAR = CONTAINER_TYPES.register("essence_altar", () -> {
		return IForgeContainerType.create((windowId, inv, data) -> {
            return new EssenceAltarContainer(windowId, inv.player, IWorldPosCallable.create(inv.player.level, data.readBlockPos()));
        });
	});
	public static final RegistryObject<ContainerType<CardFusionContainer>> CARD_FUSION = CONTAINER_TYPES.register("card_fusion", () -> {
		return IForgeContainerType.create((windowId, inv, data) -> {
            return new CardFusionContainer(windowId, inv.player, data.readBlockPos());
        });
	});
	public static final RegistryObject<ContainerType<ImitaterContainer>> IMITATER = CONTAINER_TYPES.register("imitater", () -> {
		return IForgeContainerType.create((windowId, inv, data) -> {
            return new ImitaterContainer(windowId, inv.player);
        });
	});
	
	@SubscribeEvent
    public static void onClientSetupEvent(FMLClientSetupEvent event) {
        ScreenManager.register(PLAYER_INVENTORY.get(), PlayerInventoryScreen::new);
        ScreenManager.register(ALMANAC.get(), AlmanacScreen::new);
        ScreenManager.register(PEA_GUN.get(), PeaGunScreen::new);
        ScreenManager.register(DAVE_SHOP.get(), DaveShopScreen::new);
        ScreenManager.register(SUN_SHOP.get(), SunShopScreen::new);
        ScreenManager.register(SUN_CONVERTER.get(), SunConverterScreen::new);
        ScreenManager.register(FRAGMENT_SPLICE.get(), FragmentSpliceScreen::new);
        ScreenManager.register(SLOT_MACHINE.get(), SlotMachineScreen::new);
        ScreenManager.register(PENNY_SHOP.get(), PennyShopScreen::new);
        ScreenManager.register(MYSTERY_SHOP.get(), MysteryShopScreen::new);
        ScreenManager.register(ESSENCE_ALTAR.get(), EssenceAltarScreen::new);
        ScreenManager.register(CARD_FUSION.get(), CardFusionScreen::new);
        ScreenManager.register(IMITATER.get(), ImitaterScreen::new);
    }
	
}