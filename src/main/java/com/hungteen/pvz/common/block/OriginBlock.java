package com.hungteen.pvz.common.block;

import java.util.Random;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.register.BlockRegister;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;

public class OriginBlock extends Block {

	public OriginBlock() {
		super(Block.Properties.of(Material.STONE, MaterialColor.COLOR_GREEN).strength(15, 50).harvestLevel(3).harvestTool(ToolType.PICKAXE)
				.lightLevel(i -> 15).randomTicks().sound(SoundType.GLASS).noOcclusion());
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		super.tick(state, worldIn, pos, rand);
		if(! worldIn.isClientSide) {
			if(! worldIn.isAreaLoaded(pos, 3)) return ;
			for(int i = - 1;i <= 1; ++ i) {
				for(int j = - 1; j <= 1; ++ j) {
					for(int k = - 1; k <= 1; ++ k) {
						if(rand.nextInt(PVZConfig.COMMON_CONFIG.BlockSettings.OriginBlockEffectChance.get())==0) {
						    checkAndGrow(worldIn,pos.getX() + i, pos.getY() + j, pos.getZ() + k);
						}
					}
				}
			}
		}
	}

	/**
	 * check specific block and grow
	 */
	private void checkAndGrow(World world, int x, int y, int z){
		BlockPos pos = new BlockPos(x,y,z);
		BlockState blockstate = world.getBlockState(pos);
		Block block = blockstate.getBlock();
		if(block == Blocks.GRASS_BLOCK) {
			world.setBlockAndUpdate(pos, BlockRegister.APPEASE_ORE.get().defaultBlockState());
		} else if(block == Blocks.GLOWSTONE) {
			world.setBlockAndUpdate(pos, BlockRegister.LIGHT_ORE.get().defaultBlockState());
		} else if(block == Blocks.REDSTONE_BLOCK) {
			world.setBlockAndUpdate(pos, BlockRegister.EXPLOSION_ORE.get().defaultBlockState());
		} else if(block == Blocks.GRANITE) {
			world.setBlockAndUpdate(pos, BlockRegister.DEFENCE_ORE.get().defaultBlockState());
		} else if(block == Blocks.BLUE_ICE) {
			world.setBlockAndUpdate(pos, BlockRegister.ICE_ORE.get().defaultBlockState());
		} else if(block == Blocks.ANDESITE) {
			world.setBlockAndUpdate(pos, BlockRegister.ENFORCE_ORE.get().defaultBlockState());
		} else if(block == Blocks.MYCELIUM) {
			world.setBlockAndUpdate(pos, BlockRegister.TOXIC_ORE.get().defaultBlockState());
		} else if(block == Blocks.DIORITE) {
			world.setBlockAndUpdate(pos, BlockRegister.ASSIST_ORE.get().defaultBlockState());
		} else if(block == Blocks.SOUL_SAND) {
			world.setBlockAndUpdate(pos, BlockRegister.MAGIC_ORE.get().defaultBlockState());
		} else if(block == Blocks.MAGMA_BLOCK) {
			world.setBlockAndUpdate(pos, BlockRegister.FLAME_ORE.get().defaultBlockState());
		} else if(block == Blocks.GRAVEL) {
			world.setBlockAndUpdate(pos, BlockRegister.SPEAR_ORE.get().defaultBlockState());
		} else if(block == Blocks.SANDSTONE) {
			world.setBlockAndUpdate(pos, BlockRegister.ARMA_ORE.get().defaultBlockState());
		} else if(block == Blocks.OBSIDIAN) {
			world.setBlockAndUpdate(pos, BlockRegister.SHADOW_ORE.get().defaultBlockState());
		}
	}
	
}

