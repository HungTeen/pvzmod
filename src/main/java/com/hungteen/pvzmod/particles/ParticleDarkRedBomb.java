package com.hungteen.pvzmod.particles;

import com.hungteen.pvzmod.particles.base.ParticleMultiBase;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ParticleDarkRedBomb extends ParticleMultiBase
{

	public ParticleDarkRedBomb(World worldIn, double xCoordIn, double yCoordIn,
			double zCoordIn, double p_i1213_9_, double p_i1213_11_, double p_i1213_13_) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, p_i1213_9_, p_i1213_11_, p_i1213_13_);
		this.particleRed = 1;
        this.particleGreen = 0;
        this.particleBlue = 0;
	}
}