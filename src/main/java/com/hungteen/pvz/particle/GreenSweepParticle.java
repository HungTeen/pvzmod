package com.hungteen.pvz.particle;

import com.hungteen.pvz.particle.base.SweepParticle;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GreenSweepParticle extends SweepParticle {

	protected GreenSweepParticle(World world, double x, double y, double z, double scale, IAnimatedSprite sprite) {
		super(world, x, y, z, scale, sprite);
		this.setColor(0, 0.8F, 0);
	}
	
	@Override
	public void tick() {
		super.tick();
		this.posY += 0.6;
	}

	public static class Factory implements IParticleFactory<BasicParticleType> {
		private final IAnimatedSprite sprite;

		public Factory(IAnimatedSprite sprite) {
			this.sprite = sprite;
		}

		@Override
		public Particle makeParticle(BasicParticleType typeIn, World worldIn, double x, double y, double z,
				double xSpeed, double ySpeed, double zSpeed) {
			return new GreenSweepParticle(worldIn, x, y, z, xSpeed, this.sprite);
		}

		@SuppressWarnings("unused")
		private Factory() {
			throw new UnsupportedOperationException("Use the Factory(IAnimatedSprite sprite) constructor");
		}
	}

}
