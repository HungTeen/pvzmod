package com.hungteen.pvz.client.particle.bomb;

import com.hungteen.pvz.client.particle.base.BombParticle;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PotatoMineParticle extends BombParticle {

	protected final IAnimatedSprite sprite;

	public PotatoMineParticle(ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed,
			IAnimatedSprite sprite) {
		super(world, x, y, z, xSpeed, ySpeed, zSpeed,sprite);
		this.lifetime = 6 + this.random.nextInt(4);
		this.setColor(1,1,0.5f);
		this.quadSize = 3;
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
			return new PotatoMineParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.sprite);
		}

		@SuppressWarnings("unused")
		private Factory() {
			throw new UnsupportedOperationException("Use the Factory(IAnimatedSprite sprite) constructor");
		}
	}
}
