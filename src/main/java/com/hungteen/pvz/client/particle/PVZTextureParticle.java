package com.hungteen.pvz.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.TextureSheetParticle;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-29 10:53
 **/
public abstract class PVZTextureParticle extends TextureSheetParticle {

    protected PVZTextureParticle(ClientLevel clientLevel, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        super(clientLevel, x, y, z, xSpeed, ySpeed, zSpeed);
        this.xd = xSpeed;
        this.yd = ySpeed;
        this.zd = zSpeed;
    }

}
