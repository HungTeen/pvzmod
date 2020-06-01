package com.hungteen.pvzmod.particles.row1;

import com.hungteen.pvzmod.particles.base.ParticleGroupOne;

import net.minecraft.world.World;

public class ParticleSnow extends ParticleGroupOne {

    public ParticleSnow(World world, double xCoordIn, double yCoordIn, double zCoordIn, double motionXIn, double motionYIn, double motionZIn) {
        super(world, xCoordIn, yCoordIn, zCoordIn, motionXIn, motionYIn, motionZIn);
        this.particleTextureIndexX = 2;
        this.particleTextureIndexY = 0;
        this.particleMaxAge = world.rand.nextInt(10) + 12;
        this.particleGravity = 0F;
    }
}