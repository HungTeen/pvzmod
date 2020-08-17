package com.hungteen.pvz.block.special;

import com.hungteen.pvz.register.ItemRegister;
import com.hungteen.pvz.tileentity.WaveSpawnerTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BucketWaveBlock extends Block{

	public BucketWaveBlock() {
		super(Block.Properties.from(Blocks.OBSIDIAN));
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new WaveSpawnerTileEntity();
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {
		if(!worldIn.isRemote&&player.getHeldItem(handIn).getItem()==ItemRegister.ZOMBIE_FLAG.get()) {
			WaveSpawnerTileEntity te = (WaveSpawnerTileEntity) worldIn.getTileEntity(pos);
//			te.nextWave();
//			player.sendMessage(new StringTextComponent(te.getCurrentWave()+""));
			
			player.getHeldItem(handIn).shrink(1);
		}
		return ActionResultType.SUCCESS;
	}
}
