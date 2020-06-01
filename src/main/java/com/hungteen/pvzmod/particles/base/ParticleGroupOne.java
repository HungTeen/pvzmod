package com.hungteen.pvzmod.particles.base;

import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;

public class ParticleGroupOne extends Particle {

    public ParticleGroupOne(World world, double xCoordIn, double yCoordIn, double zCoordIn, double motionXIn, double motionYIn, double motionZIn) {
        super(world, xCoordIn, yCoordIn, zCoordIn, motionXIn, motionYIn, motionZIn);
        this.particleTextureIndexX = 1;
        this.particleTextureIndexY = 0;
        this.motionX *= 0.10000000149011612D;
        this.motionY *= 0.1D;
        this.motionZ *= 0.10000000149011612D;

        this.motionX += motionXIn;
        this.motionY += motionYIn;
        this.motionZ += motionZIn;

        this.particleScale = 0.96F + 0.02F * world.rand.nextInt(8);

        this.particleMaxAge = 5;

        this.particleAlpha = 1.0F;
        this.particleGravity = 0.001F;
        this.canCollide = true;
    }

    @Override
    public int getFXLayer() {
        return 1;
    }

    @Override
    public void renderParticle(BufferBuilder buffer, Entity entity, float partialTicks, float rotX, float rotXZ, float rotZ, float rotYZ, float rotXY) {
        // EffectRenderer will by default bind the vanilla particles texture, override with our own
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(Reference.PARTICLE_DIR);

        GlStateManager.depthMask(false);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 1);

        super.renderParticle(buffer, entity, partialTicks, rotX, rotXZ, rotZ, rotYZ, rotXY);

        GlStateManager.disableBlend();
        GlStateManager.depthMask(true);
    }

    @Override
    public void onUpdate() {

        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;

        if (particleAge++ >= particleMaxAge) {
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