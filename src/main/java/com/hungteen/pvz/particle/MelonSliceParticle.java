package com.hungteen.pvz.particle;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.world.World;

public class MelonSliceParticle extends PVZNormalParticle{

	public MelonSliceParticle(World world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
		super(world, x, y, z, xSpeed, ySpeed, zSpeed);
		this.particleScale = 0.25f;
		this.maxAge = this.rand.nextInt(20) + 10;
		this.canCollide = true;
		this.particleGravity = 0.1f;
		this.motionX = world.rand.nextFloat() - 0.5;
		this.motionY = - world.rand.nextFloat() ;
		this.motionZ = world.rand.nextFloat() - 0.5;
	}

	public static class Factory implements IParticleFactory<BasicParticleType> {

		private final IAnimatedSprite sprite;

		public Factory(IAnimatedSprite sprite) {
			this.sprite = sprite;
		}
		
		@Override
		public Particle makeParticle(BasicParticleType typeIn, World worldIn, double x, double y, double z,
				double xSpeed, double ySpeed, double zSpeed) {
			MelonSliceParticle particle = new MelonSliceParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
			particle.selectSpriteRandomly(this.sprite);
			return particle;
		}

		@SuppressWarnings("unused")
		private Factory() {
			throw new UnsupportedOperationException("Use the Factory(IAnimatedSprite sprite) constructor");
		}
	}
	
}