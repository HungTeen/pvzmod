package com.hungteen.pvzmod.particles.row1;

import com.hungteen.pvzmod.particles.base.ParticleGroupOne;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;

public class ParticleSpeedPea1 extends ParticleGroupOne {

    public ParticleSpeedPea1(World world, double xCoordIn, double yCoordIn, double zCoordIn, double motionXIn, double motionYIn, double motionZIn) {
        super(world, xCoordIn, yCoordIn, zCoordIn, motionXIn, motionYIn, motionZIn);
        this.particleTextureIndexX = 0;
        this.particleTextureIndexY = 0;
        this.particleScale=1f;
        this.particleMaxAge = 1;
        this.particleGravity = 0F;
    }
}