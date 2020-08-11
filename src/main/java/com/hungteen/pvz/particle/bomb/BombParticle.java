package com.hungteen.pvz.particle.bomb;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BombParticle extends SpriteTexturedParticle {

	protected final IAnimatedSprite sprite;

	protected BombParticle(World world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed,
			IAnimatedSprite sprite) {
		super(world, x, y, z, xSpeed, ySpeed, zSpeed);
		this.maxAge = 6 + this.rand.nextInt(4);
		this.setColor(1,0,0);
		this.particleScale = 4;
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

}