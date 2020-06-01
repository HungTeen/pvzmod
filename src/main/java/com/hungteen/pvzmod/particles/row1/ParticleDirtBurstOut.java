package com.hungteen.pvzmod.particles.row1;

import com.hungteen.pvzmod.ClientProxy;
import com.hungteen.pvzmod.particles.base.ParticleGroupOne;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;

public class ParticleDirtBurstOut extends ParticleGroupOne {

    public ParticleDirtBurstOut(World world, double xCoordIn, double yCoordIn, double zCoordIn, double motionXIn, double motionYIn, double motionZIn) {
        super(world, xCoordIn, yCoordIn, zCoordIn, motionXIn, motionYIn, motionZIn);
        this.particleTextureIndexX = 1;
        this.particleTextureIndexY = 0;
        this.particleMaxAge = world.rand.nextInt(30) + 60;
        this.particleGravity = 0.02F;
    }
}