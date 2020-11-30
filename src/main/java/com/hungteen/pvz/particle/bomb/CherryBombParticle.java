package com.hungteen.pvz.particle.bomb;

import com.hungteen.pvz.particle.base.BombParticle;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CherryBombParticle extends BombParticle {

	protected final IAnimatedSprite sprite;

	public CherryBombParticle(World world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed,
			IAnimatedSprite sprite) {
		super(world, x, y, z, xSpeed, ySpeed, zSpeed,sprite);
		this.maxAge = 6 + this.rand.nextInt(4);
		this.setColor(1,0,0);
		this.particleScale = 4;
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
			return new CherryBombParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.sprite);
		}

		@SuppressWarnings("unused")
		private Factory() {
			throw new UnsupportedOperationException("Use the Factory(IAnimatedSprite sprite) constructor");
		}
	}
}
