package com.hungteen.pvz.particle;

import com.hungteen.pvz.particle.base.BombParticle;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.world.World;

public class DoomParticle extends BombParticle {

	protected final IAnimatedSprite sprite;

	public DoomParticle(World world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed,
			IAnimatedSprite sprite) {
		super(world, x, y, z, xSpeed, ySpeed, zSpeed,sprite);
		this.maxAge = 40 + this.rand.nextInt(20);
		this.setColor(1, 1, 1);
		this.particleScale = 4.5f;
		this.sprite = sprite;
		this.canCollide = false;
		this.selectSpriteWithAge(this.sprite);
	}

	public static class Factory implements IParticleFactory<BasicParticleType> {
		private final IAnimatedSprite sprite;

		public Factory(IAnimatedSprite sprite) {
			this.sprite = sprite;
		}

		@Override
		public Particle makeParticle(BasicParticleType typeIn, World worldIn, double x, double y, double z,
				double xSpeed, double ySpeed, double zSpeed) {
			return new DoomParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.sprite);
		}

		@SuppressWarnings("unused")
		private Factory() {
			throw new UnsupportedOperationException("Use the Factory(IAnimatedSprite sprite) constructor");
		}
	}
}
