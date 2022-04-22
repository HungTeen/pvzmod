package com.hungteen.pvz.common.menu;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.client.ClientRegister;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.awt.*;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-22 10:40
 *
 * Step 1. register menu type. <br>
 * Step 2. bind client screen at {@link ClientRegister#registerScreen()} <br>
 **/
public class PVZMenus {

    public static final DeferredRegister<MenuType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS, PVZMod.MOD_ID);

    public static final RegistryObject<MenuType<EssenceAltarMenu>> ESSENCE_ALTAR = CONTAINER_TYPES.register("essence_altar", () -> {
        return IForgeMenuType.create((windowId, inv, data) -> {
            return new EssenceAltarMenu(windowId, inv);
        });
    });

}
