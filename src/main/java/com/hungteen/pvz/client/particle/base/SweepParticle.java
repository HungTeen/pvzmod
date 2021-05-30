package com.hungteen.pvz.client.particle.base;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class SweepParticle extends SpriteTexturedParticle {
	
	protected final IAnimatedSprite sprite;

	protected SweepParticle(ClientWorld world, double x, double y, double z, double scale, IAnimatedSprite sprite) {
	      super(world, x, y, z, 0.0D, 0.0D, 0.0D);
	      this.sprite = sprite;
	      this.lifetime = 4;
	      float f = this.random.nextFloat() * 0.6F + 0.4F;
	      this.rCol = f;
	      this.gCol = f;
	      this.bCol = f;
	      this.quadSize = 1.0F - (float)scale * 0.5F;
	      this.setSpriteFromAge(sprite);
	   }

	public int getLightColor(float partialTick) {
		return 15728880;
	}

	public void tick() {
		this.xo = this.x;
		this.yo = this.y;
		this.zo = this.z;
		if (this.age++ >= this.lifetime) {
			this.remove();
		} else {
			this.setSpriteFromAge(this.sprite);
		}
	}

	public IParticleRenderType getRenderType() {
		return IParticleRenderType.PARTICLE_SHEET_LIT;
	}

}