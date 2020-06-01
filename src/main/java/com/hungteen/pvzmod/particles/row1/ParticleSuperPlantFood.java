package com.hungteen.pvzmod.particles.row1;

import com.hungteen.pvzmod.particles.base.ParticleGroupOne;

import net.minecraft.world.World;

public class ParticleSuperPlantFood extends ParticleGroupOne {

    public ParticleSuperPlantFood(World world, double xCoordIn, double yCoordIn, double zCoordIn, double motionXIn, double motionYIn, double motionZIn) {
        super(world, xCoordIn, yCoordIn, zCoordIn, motionXIn, motionYIn, motionZIn);
        this.particleTextureIndexX = 3;
        this.particleTextureIndexY = 0;
        this.particleMaxAge = 20;
        this.particleScale=2f;
        this.particleGravity = 0F;
        this.particleAlpha = 0.05F;
    }
    
    @Override
    public int getBrightnessForRender(float p_189214_1_) {
    	return 0xF000F0;
    }
}
