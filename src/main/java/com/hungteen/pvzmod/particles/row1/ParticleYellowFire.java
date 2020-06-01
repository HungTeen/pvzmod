package com.hungteen.pvzmod.particles.row1;

import com.hungteen.pvzmod.particles.base.ParticleGroupOne;

import net.minecraft.world.World;

public class ParticleYellowFire extends ParticleGroupOne {

    public ParticleYellowFire(World world, double xCoordIn, double yCoordIn, double zCoordIn, double motionXIn, double motionYIn, double motionZIn) {
        super(world, xCoordIn, yCoordIn, zCoordIn, motionXIn, motionYIn, motionZIn);
        this.particleTextureIndexX = 4;
        this.particleTextureIndexY = 0;
        this.particleMaxAge = 20;
        this.particleScale=5f;
        this.particleGravity = 0F;
    }
}
