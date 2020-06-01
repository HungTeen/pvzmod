package com.hungteen.pvzmod.blocks.special;

import java.util.Random;

import com.hungteen.pvzmod.Main;
import com.hungteen.pvzmod.blocks.BlockBase;
import com.hungteen.pvzmod.blocks.tileentities.TileEntityCardTable;
import com.hungteen.pvzmod.blocks.tileentities.TileEntityJuicer;
import com.hungteen.pvzmod.gui.GuiHandler;
import com.hungteen.pvzmod.registry.BlockRegister;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class BlockCardTable extends BlockBase{

	public BlockCardTable(String name, Material material) {
		super(name, material);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			playerIn.openGui(Main.instance, GuiHandler.CARD_TABLE, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}
	
//	@Override
//	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
//		TileEntity te=worldIn.getTileEntity(pos);
//		IItemHandler handler=te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
//		for(int i=0;i<handler.getSlots();i++) {
//			ItemStack stack=handler.getStackInSlot(i);
//			InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), stack);
//		}
//		super.breakBlock(worldIn, pos, state);
//	}
//	
//	@Override
//	public boolean hasTileEntity(IBlockState state) {
//		return true;
//	}
//
//	@Override
//	public TileEntity createTileEntity(World world, IBlockState state) {
//		return new TileEntityCardTable();
//	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) 
	{
		return Item.getItemFromBlock(BlockRegister.CARD_TABLE);
	}
	
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
	{
		return new ItemStack(BlockRegister.CARD_TABLE);
	}
}
