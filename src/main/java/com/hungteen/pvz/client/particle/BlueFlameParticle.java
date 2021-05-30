package com.hungteen.pvz.client.particle;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;

public class BlueFlameParticle extends YellowFlameParticle{

	public BlueFlameParticle(ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
		super(world, x, y, z, xSpeed, ySpeed, zSpeed);
	}

	public static class Factory implements IParticleFactory<BasicParticleType> {

		private final IAnimatedSprite sprite;

		public Factory(IAnimatedSprite sprite) {
			this.sprite = sprite;
		}
		
		@Override
		public Particle createParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z,
				double xSpeed, double ySpeed, double zSpeed) {
			BlueFlameParticle particle = new BlueFlameParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
			particle.pickSprite(this.sprite);
			return particle;
		}

		@SuppressWarnings("unused")
		private Factory() {
			throw new UnsupportedOperationException("Use the Factory(IAnimatedSprite sprite) constructor");
		}
	}
	
}
