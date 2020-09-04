package com.hungteen.pvz.particle;

import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.world.World;

public abstract class PVZNormalParticle extends SpriteTexturedParticle{

	public PVZNormalParticle(World world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
		super(world, x, y, z, xSpeed, ySpeed, zSpeed);
	}

	@Override
    public void tick() {
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;
        if (age++ >= maxAge) {
            this.setExpired();
        }
        this.move(this.motionX, this.motionY, this.motionZ);
        this.motionY -= this.particleGravity;
        if (onGround) {
            motionX *= 0.0D;
            motionZ *= 0.0D;
        }
    }
	
	@Override
	public IParticleRenderType getRenderType() {
		return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

}
