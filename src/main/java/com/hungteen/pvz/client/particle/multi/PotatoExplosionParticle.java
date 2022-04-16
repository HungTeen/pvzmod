package com.hungteen.pvz.client.particle.multi;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ExplodeParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-16 19:16
 **/
public class PotatoExplosionParticle extends MultiTextureParticle{

    public PotatoExplosionParticle(ClientLevel clientLevel, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, SpriteSet sprites) {
        super(clientLevel, x, y, z, xSpeed, ySpeed, zSpeed, sprites);
        this.gravity = -0.1F;
        this.friction = 0.9F;
        this.quadSize = 2F;
        this.xd = xSpeed + (Math.random() * 2.0D - 1.0D) * (double)0.05F;
        this.yd = ySpeed + (Math.random() * 2.0D - 1.0D) * (double)0.05F;
        this.zd = zSpeed + (Math.random() * 2.0D - 1.0D) * (double)0.05F;
        this.lifetime = (int)(16.0D / ((double)this.random.nextFloat() * 0.8D + 0.2D)) + 2;
        this.setSpriteFromAge(this.sprites);
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {

        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientLevel clientLevel, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new PotatoExplosionParticle(clientLevel, x, y, z, xSpeed, ySpeed, zSpeed, this.sprites);
        }

    }
}
