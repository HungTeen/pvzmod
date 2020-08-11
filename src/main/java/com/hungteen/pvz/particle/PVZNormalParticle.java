package com.hungteen.pvz.particle;

import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.world.World;

public abstract class PVZNormalParticle extends SpriteTexturedParticle{

	protected PVZNormalParticle(World p_i50999_1_, double p_i50999_2_, double p_i50999_4_, double p_i50999_6_,
			double p_i50999_8_, double p_i50999_10_, double p_i50999_12_) {
		super(p_i50999_1_, p_i50999_2_, p_i50999_4_, p_i50999_6_, p_i50999_8_, p_i50999_10_, p_i50999_12_);
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

}
