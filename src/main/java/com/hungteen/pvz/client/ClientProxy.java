package com.hungteen.pvz.client;

import com.hungteen.pvz.client.gui.screen.AlmanacScreen;
import com.hungteen.pvz.common.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-08 19:59
 **/
public class ClientProxy extends CommonProxy {

    public static final Minecraft MC = Minecraft.getInstance();

    @Override
    public void postInit() {
    }

    @Override
    public void openAlmanacGUI(LivingEntity livingEntity) {
        MC.setScreen(new AlmanacScreen(livingEntity));
    }

    @Override
    public Player getPlayer() {
        return MC.player;
    }

}
