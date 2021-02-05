package com.hungteen.pvz.particle.base;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class SweepParticle extends SpriteTexturedParticle {
	
	protected final IAnimatedSprite sprite;

	protected SweepParticle(World world, double x, double y, double z, double scale, IAnimatedSprite sprite) {
	      super(world, x, y, z, 0.0D, 0.0D, 0.0D);
	      this.sprite = sprite;
	      this.maxAge = 4;
	      float f = this.rand.nextFloat() * 0.6F + 0.4F;
	      this.particleRed = f;
	      this.particleGreen = f;
	      this.particleBlue = f;
	      this.particleScale = 1.0F - (float)scale * 0.5F;
	      this.selectSpriteWithAge(sprite);
	   }

	public int getBrightnessForRender(float partialTick) {
		return 15728880;
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

	public IParticleRenderType getRenderType() {
		return IParticleRenderType.PARTICLE_SHEET_LIT;
	}

}