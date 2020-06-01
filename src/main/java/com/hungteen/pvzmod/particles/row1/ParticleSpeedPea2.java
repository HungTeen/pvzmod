package com.hungteen.pvzmod.particles.row1;

import com.hungteen.pvzmod.particles.base.ParticleGroupOne;

import net.minecraft.world.World;

public class ParticleSpeedPea2 extends ParticleGroupOne {

    public ParticleSpeedPea2(World world, double xCoordIn, double yCoordIn, double zCoordIn, double motionXIn, double motionYIn, double motionZIn) {
        super(world, xCoordIn, yCoordIn, zCoordIn, motionXIn, motionYIn, motionZIn);
        this.particleTextureIndexX = 0;
        this.particleTextureIndexY = 0;
        this.particleScale=1.7f;
        this.particleMaxAge = 1;
        this.particleGravity = 0F;
    }
}
