package com.hungteen.pvz.common.misc;

import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-17 17:22
 **/
public class PVZWoodType {

    public static final WoodType NUT = WoodType.create("nut");

    /**
     * {@link com.hungteen.pvz.PVZMod#setUpClient(FMLClientSetupEvent)}
     */
    public static void register(){
        WoodType.register(NUT);
    }

}
