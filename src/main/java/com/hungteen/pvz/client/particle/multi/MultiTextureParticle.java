package com.hungteen.pvz.client.particle.multi;

import com.hungteen.pvz.client.particle.PVZTextureParticle;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ExplodeParticle;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-16 19:09
 **/
public class MultiTextureParticle extends PVZTextureParticle {

    protected final SpriteSet sprites;

    public MultiTextureParticle(ClientLevel clientLevel, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, SpriteSet sprites) {
        super(clientLevel, x, y, z, xSpeed, ySpeed, zSpeed);
        this.sprites = sprites;
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteFromAge(sprites);
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

}
