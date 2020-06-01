package com.hungteen.pvzmod.blocks.misc;

import java.util.Random;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPVZLilyPad extends BlockMiscBase{

	public BlockPVZLilyPad(String name, Material material) {
		super(name, material);
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}

	@Override
	protected AxisAlignedBB getAABB() {
		return new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.09375D, 0.9375D);
	}
	
	public IBlockState getState(EntityPlayer player)
	{
//		System.out.println(player.getHorizontalFacing().getOpposite());
		return this.getDefaultState().withProperty(FACING, player.getHorizontalFacing().getOpposite());
	}
	
	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
        if (pos.getY() >= 0 && pos.getY() < 256)
        {
            IBlockState iblockstate = worldIn.getBlockState(pos.down());
            Material material = iblockstate.getMaterial();
            return material == Material.WATER && ((Integer)iblockstate.getValue(BlockLiquid.LEVEL)).intValue() == 0 || material == Material.ICE;
        }
        else
        {
            return false;
        }
    }

	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    /**
     * Get the geometry of the queried face at the given position and state. This is used to decide whether things like
     * buttons are allowed to be placed on the face, or how glass panes connect to the face, among other things.
     * <p>
     * Common values are {@code SOLID}, which is the default, and {@code UNDEFINED}, which represents something that
     * does not fit the other descriptions and will generally cause other things not to connect to the face.
     * 
     * @return an approximation of the form of the given face
     */
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }
}
