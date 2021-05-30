package com.hungteen.pvz.client.particle;

import com.hungteen.pvz.client.particle.base.SweepParticle;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GreenSweepParticle extends SweepParticle {

	protected GreenSweepParticle(ClientWorld world, double x, double y, double z, double scale, IAnimatedSprite sprite) {
		super(world, x, y, z, scale, sprite);
		this.setColor(0, 0.8F, 0);
	}
	
	@Override
	public void tick() {
		super.tick();
		this.y += 0.6;
	}

	public static class Factory implements IParticleFactory<BasicParticleType> {
		private final IAnimatedSprite sprite;

		public Factory(IAnimatedSprite sprite) {
			this.sprite = sprite;
		}

		@Override
		public Particle createParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z,
				double xSpeed, double ySpeed, double zSpeed) {
			return new GreenSweepParticle(worldIn, x, y, z, xSpeed, this.sprite);
		}

		@SuppressWarnings("unused")
		private Factory() {
			throw new UnsupportedOperationException("Use the Factory(IAnimatedSprite sprite) constructor");
		}
	}

}
