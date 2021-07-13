package com.hungteen.pvz.client.particle;

import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.world.ClientWorld;

public abstract class PVZNormalParticle extends SpriteTexturedParticle{

	public PVZNormalParticle(ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
		super(world, x, y, z, xSpeed, ySpeed, zSpeed);
		this.xd = xSpeed;
		this.yd = ySpeed;
		this.zd = zSpeed;
	}

	@Override
    public void tick() {
        xo = x;
        yo = y;
        zo = z;
        if (age++ >= lifetime) {
            this.remove();
        }
        this.move(this.xd, this.yd, this.zd);
        this.yd -= this.gravity;
        if (onGround) {
            xd *= 0.0D;
            zd *= 0.0D;
        }
    }
	
	@Override
	public IParticleRenderType getRenderType() {
		return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

}
