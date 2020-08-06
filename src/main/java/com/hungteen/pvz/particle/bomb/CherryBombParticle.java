package com.hungteen.pvz.particle.bomb;

import java.awt.Color;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CherryBombParticle extends SpriteTexturedParticle {

	protected final IAnimatedSprite sprite;

	protected CherryBombParticle(World world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed,
			IAnimatedSprite sprite) {
		super(world, x, y, z, xSpeed, ySpeed, zSpeed);
		this.maxAge = 6 + this.rand.nextInt(4);
		this.setColor(1,0,0);
		this.particleScale = 2;
		this.sprite = sprite;
		this.canCollide = false;
		this.selectSpriteWithAge(this.sprite);
	}

	public void tick() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		if (this.age++ >= this.maxAge) {
			this.setExpired();
		} else {
			this.selectSpriteWithAge(this.sprite);
		}
	}

	@Override
	public int getBrightnessForRender(float partialTick) {
		return 15728880;
	}

	@Override
	public IParticleRenderType getRenderType() {
		return IParticleRenderType.PARTICLE_SHEET_LIT;
	}

	@OnlyIn(Dist.CLIENT)
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
