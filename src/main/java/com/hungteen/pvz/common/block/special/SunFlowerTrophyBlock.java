package com.hungteen.pvz.common.block.special;

import com.hungteen.pvz.common.block.AbstractFacingBlock;
import com.hungteen.pvz.common.tileentity.SunFlowerTrophyTileEntity;

import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class SunFlowerTrophyBlock extends AbstractFacingBlock {

	public final int lvl;
	
	public SunFlowerTrophyBlock(Properties properties, int lvl) {
		super(properties.lightLevel(i -> 14));
		this.lvl = lvl;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new SunFlowerTrophyTileEntity();
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
}
