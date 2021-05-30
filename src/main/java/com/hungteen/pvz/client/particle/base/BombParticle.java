package com.hungteen.pvz.client.particle.base;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BombParticle extends SpriteTexturedParticle {

	protected final IAnimatedSprite sprite;

	protected BombParticle(ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed,
			IAnimatedSprite sprite) {
		super(world, x, y, z, xSpeed, ySpeed, zSpeed);
		this.lifetime = 6 + this.random.nextInt(4);
		this.setColor(1, 0, 0);
		this.quadSize = 4;
		this.sprite = sprite;
		this.hasPhysics = false;
		this.setSpriteFromAge(this.sprite);
	}

	@Override
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

	@Override
	public int getLightColor(float partialTick) {
		return 15728880;
	}

	@Override
	public IParticleRenderType getRenderType() {
		return IParticleRenderType.PARTICLE_SHEET_LIT;
	}

}