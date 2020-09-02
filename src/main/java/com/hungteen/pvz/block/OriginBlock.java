package com.hungteen.pvz.block;

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

public class OriginBlock extends Block
{

	public OriginBlock() {
		super(Block.Properties.create(Material.ROCK, MaterialColor.GREEN).hardnessAndResistance(15, 50).harvestLevel(3).harvestTool(ToolType.PICKAXE)
				.lightValue(15).tickRandomly().sound(SoundType.GLASS).notSolid());
	}
	
	
	@SuppressWarnings("deprecation")
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		super.tick(state, worldIn, pos, rand);
		if(!worldIn.isRemote) {
			if(!worldIn.isAreaLoaded(pos, 3)) return ;
			for(int i=-1;i<=1;i++) {
				for(int j=-1;j<=1;j++) {
					for(int k=-1;k<=1;k++) {
						if(i==k&&k==j&&i==0) return ;
						if(rand.nextInt(PVZConfig.COMMON_CONFIG.BlockSettings.OriginBlockEffectChance.get())==0) {
						    checkAndGrow(worldIn,pos.getX()+i,pos.getY()+j,pos.getZ()+k);
						}
					}
				}
			}
		}
	}

	private void checkAndGrow(World world,int x,int y,int z){
		BlockPos pos=new BlockPos(x,y,z);
		BlockState blockstate = world.getBlockState(pos);
		Block block=blockstate.getBlock();
		if(block==Blocks.GRASS_BLOCK) {
			world.setBlockState(pos, BlockRegister.APPEASE_ORE.get().getDefaultState());
		}else if(block==Blocks.GLOWSTONE) {
			world.setBlockState(pos, BlockRegister.LIGHT_ORE.get().getDefaultState());
		}else if(block==Blocks.REDSTONE_BLOCK) {
			world.setBlockState(pos, BlockRegister.EXPLOSION_ORE.get().getDefaultState());
		}else if(block==Blocks.GRANITE) {
			world.setBlockState(pos, BlockRegister.DEFENCE_ORE.get().getDefaultState());
		}else if(block==Blocks.BLUE_ICE) {
			world.setBlockState(pos, BlockRegister.ICE_ORE.get().getDefaultState());
		}else if(block==Blocks.ANDESITE) {
			world.setBlockState(pos, BlockRegister.ENFORCE_ORE.get().getDefaultState());
		}else if(block==Blocks.MYCELIUM) {
			world.setBlockState(pos, BlockRegister.TOXIC_ORE.get().getDefaultState());
		}else if(block==Blocks.DIORITE) {
			world.setBlockState(pos, BlockRegister.ASSIST_ORE.get().getDefaultState());
		}else if(block==Blocks.SOUL_SAND) {
			world.setBlockState(pos, BlockRegister.MAGIC_ORE.get().getDefaultState());
		}else if(block==Blocks.MAGMA_BLOCK) {
			world.setBlockState(pos, BlockRegister.FLAME_ORE.get().getDefaultState());
		}else if(block==Blocks.GRAVEL) {
			world.setBlockState(pos, BlockRegister.SPEAR_ORE.get().getDefaultState());
		}else if(block==Blocks.SANDSTONE) {
			world.setBlockState(pos, BlockRegister.ARMA_ORE.get().getDefaultState());
		}else if(block==Blocks.OBSIDIAN) {
			world.setBlockState(pos, BlockRegister.SHADOW_ORE.get().getDefaultState());
		}
	}
}

