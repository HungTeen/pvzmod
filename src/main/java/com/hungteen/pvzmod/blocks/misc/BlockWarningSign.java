package com.hungteen.pvzmod.blocks.misc;

import net.minecraft.block.material.Material;
import net.minecraft.util.math.AxisAlignedBB;

public class BlockWarningSign extends BlockMiscBase{

	public BlockWarningSign(String name, Material material) {
		super(name, material);
	}

	@Override
	protected AxisAlignedBB getAABB() {
		return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 2.0D, 1.0D);
	}

}
