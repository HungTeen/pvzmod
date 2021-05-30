package com.hungteen.pvz.client.particle;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;

public class FrozenMelonSliceParticle extends PVZNormalParticle{

	public FrozenMelonSliceParticle(ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
		super(world, x, y, z, xSpeed, ySpeed, zSpeed);
		this.quadSize = 0.25f;
		this.lifetime = this.random.nextInt(20) + 10;
		this.hasPhysics = true;
		this.gravity = 0.1f;
		this.xd = world.random.nextFloat() - 0.5;
		this.yd = - world.random.nextFloat() ;
		this.zd = world.random.nextFloat() - 0.5;
	}

	public static class Factory implements IParticleFactory<BasicParticleType> {

		private final IAnimatedSprite sprite;

		public Factory(IAnimatedSprite sprite) {
			this.sprite = sprite;
		}
		
		@Override
		public Particle createParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z,
				double xSpeed, double ySpeed, double zSpeed) {
			FrozenMelonSliceParticle particle = new FrozenMelonSliceParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
			particle.pickSprite(this.sprite);
			return particle;
		}

		@SuppressWarnings("unused")
		private Factory() {
			throw new UnsupportedOperationException("Use the Factory(IAnimatedSprite sprite) constructor");
		}
	}
	
}