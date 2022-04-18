package com.hungteen.pvz.client;

import com.hungteen.pvz.client.render.entity.layer.ColdLayer;
import com.hungteen.pvz.common.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.EntityRenderersEvent;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-08 19:59
 **/
public class ClientProxy extends CommonProxy {

    public static final Minecraft MC = Minecraft.getInstance();

    @Override
    public void setUpClient() {
        PVZKeyBinds.register();
    }

    @Override
    public void postInit() {
    }

    @Override
    public Player getPlayer() {
        return MC.player;
    }

}
