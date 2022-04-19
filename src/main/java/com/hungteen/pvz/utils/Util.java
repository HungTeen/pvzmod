package com.hungteen.pvz.utils;

import com.hungteen.pvz.PVZMod;

import net.minecraft.resources.ResourceLocation;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-08 21:30
 **/
public class Util {

    public static final String INIT_VERSION = "0.0.0";

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

    public static ResourceLocation texture(String location){
        return prefix("textures/" + location);
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

    public static void debug(String text, Object ... objects){
        PVZMod.LOGGER.warn(text, objects);
    }

    public static void warn(String text, Object ... objects){
        PVZMod.LOGGER.warn(text, objects);
    }

    public static void error(String text, Object ... objects){
        PVZMod.LOGGER.error(text, objects);
    }


    public static String identify(String modId, String name){
        return modId + ":" + name;
    }

}
