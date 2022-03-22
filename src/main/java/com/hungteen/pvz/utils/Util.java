package com.hungteen.pvz.utils;

import com.hungteen.pvz.PVZMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Comparator;
import java.util.function.Predicate;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-08 21:30
 **/
public class Util {

    public static final ResourceLocation WIDGETS = prefix("textures/gui/widgets.png");

    /**
     * get resource with mc prefix.
     */
    public static ResourceLocation mcPrefix(String name) {
        return new ResourceLocation(name);
    }

    /**
     * get resource with forge prefix.
     */
    public static ResourceLocation forgePrefix(String name) {
        return new ResourceLocation("forge", name);
    }

    /**
     * get resource with pvz prefix.
     */
    public static ResourceLocation prefix(String name) {
        return new ResourceLocation(PVZMod.MOD_ID, name);
    }

    public static boolean inPVZ(ResourceLocation resourceLocation){
        return resourceLocation.getNamespace().equals(PVZMod.MOD_ID);
    }


    public static String identify(String modId, String name){
        return modId + ":" + name;
    }

}
