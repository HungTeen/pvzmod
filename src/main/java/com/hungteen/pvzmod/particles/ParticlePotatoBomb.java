package com.hungteen.pvzmod.particles;

import com.hungteen.pvzmod.particles.base.ParticleMultiBase;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ParticlePotatoBomb extends ParticleMultiBase{

	public ParticlePotatoBomb(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double p_i1213_9_,
			double p_i1213_11_, double p_i1213_13_) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, p_i1213_9_, p_i1213_11_, p_i1213_13_);
		this.setRBGColorF(1f,1f,0.5f);
	}
	
	@Override
	protected float getScale() {
		return 2.4f;
	}
}
