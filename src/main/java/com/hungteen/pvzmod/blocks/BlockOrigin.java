package com.hungteen.pvzmod.blocks;

import java.util.Random;

import com.hungteen.pvzmod.registry.BlockRegister;
import com.hungteen.pvzmod.util.ConfigurationUtil;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockObsidian;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockOrigin extends BlockBase
{
	public BlockOrigin(String name, Material material) {
		super(name, material);
		setTickRandomly(true);
		setLightLevel(1f);
		setHardness(30f);
		setResistance(Reference.ENDERCHEST_RESISTANCE);
	}
	
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		if (!worldIn.isRemote)
        {
			//System.out.println("12345");
            if (!worldIn.isAreaLoaded(pos, 3)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            boolean flag=false;
            for(int i=-1;i<=1;i++) {
            	for(int j=-1;j<=1;j++) {
            		for(int k=-1;k<=1;k++) {
            			if(i==k&&k==j&&i==0) continue;
            			if(rand.nextInt(this.getChangeChance())==0) {
            			    flag|=check(worldIn,pos.getX()+i,pos.getY()+j,pos.getZ()+k);
            			}
            		}
            	}
            }
            if(flag) {
            	//worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
        }
	}
	
	/**
	 * 原始精华块将周围可变方块改变
	 * 如 草方块->普通精华
	 */
	private int getChangeChance()
	{
		int dif=ConfigurationUtil.getPVZDifficulty();
		return 3+2*dif;
	}
	
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
	
	private boolean check(World world,int x,int y,int z)
	{
		BlockPos pos=new BlockPos(x,y,z);
		IBlockState blockstate = world.getBlockState(pos);
		Block block=blockstate.getBlock();
		if(block==Blocks.GRASS) {
			world.setBlockState(pos, BlockRegister.COMMON_ORE.getDefaultState());
			return true;
		}
		else if(block==Blocks.MAGMA) {
			world.setBlockState(pos, BlockRegister.FLAME_ORE.getDefaultState());
			return true;
		}
		else if(block==Blocks.SNOW) {
			world.setBlockState(pos, BlockRegister.ICE_ORE.getDefaultState());
			return true;
		}
		else if(block==Blocks.GLOWSTONE) {
			world.setBlockState(pos, BlockRegister.LIGHT_ORE.getDefaultState());
			return true;
		}
		else if(block==Blocks.MYCELIUM) {
			world.setBlockState(pos, BlockRegister.DARKNESS_ORE.getDefaultState());
			return true;
		}
		else if(block==Blocks.SOUL_SAND) {
			world.setBlockState(pos, BlockRegister.MAGIC_ORE.getDefaultState());
			return true;
		}
		else if(block==Blocks.TNT) {
			world.setBlockState(pos, BlockRegister.EXPLOSION_ORE.getDefaultState());
			return true;
		}
		else if(block==Blocks.OBSIDIAN) {
			world.setBlockState(pos, BlockRegister.DEFENCE_ORE.getDefaultState());
			return true;
		}
		else if(block==Blocks.SLIME_BLOCK) {
			world.setBlockState(pos, BlockRegister.FIGHT_ORE.getDefaultState());
			return true;
		}
		return false;
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }
	
//	@SideOnly(Side.CLIENT)
//    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
//    {
//		IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
//        Block block = iblockstate.getBlock();
//        
//        if(block==Blocks.AIR|) {
//        	System.out.println("true");
//        	return true;
//        }
//        System.out.println("false");
//        return super.shouldSideBeRendered(blockState, blockAccess, pos, side);
//    }
	
	@Override
	public int tickRate(World worldIn) {
		return 1;
	}
}

