package com.hungteen.pvz.client.particle;

import com.hungteen.pvz.client.particle.base.BombParticle;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;

public class DoomParticle extends BombParticle {

	protected final IAnimatedSprite sprite;

	public DoomParticle(ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed,
			IAnimatedSprite sprite) {
		super(world, x, y, z, xSpeed, ySpeed, zSpeed,sprite);
		this.lifetime = 40 + this.random.nextInt(20);
		this.setColor(1, 1, 1);
		this.quadSize = 4.5f;
		this.sprite = sprite;
		this.hasPhysics = false;
		this.setSpriteFromAge(this.sprite);
	}

	public static class Factory implements IParticleFactory<BasicParticleType> {
		private final IAnimatedSprite sprite;

		public Factory(IAnimatedSprite sprite) {
			this.sprite = sprite;
		}

		@Override
		public Particle createParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z,
				double xSpeed, double ySpeed, double zSpeed) {
			return new DoomParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.sprite);
		}

		@SuppressWarnings("unused")
		private Factory() {
			throw new UnsupportedOperationException("Use the Factory(IAnimatedSprite sprite) constructor");
		}
	}
}
