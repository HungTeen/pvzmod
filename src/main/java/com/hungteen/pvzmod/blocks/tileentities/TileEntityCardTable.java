package com.hungteen.pvzmod.blocks.tileentities;

import net.minecraft.block.BlockWorkbench;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityCardTable extends TileEntity implements ITickable{

//	private ItemStackHandler handler = new ItemStackHandler(6);
	private String customName;
	private boolean isCrafted;
	private boolean canCraft=false;
	
	@Override
	public void update() {
		if(!this.world.isRemote) {
		    if(this.isCrafted==true) {
//			System.out.println("craft!");
			    this.isCrafted=false;
		    }
		}
//		BlockWorkbench
	}
	
	public void setIsCrafted(boolean is)
	{
		this.isCrafted=is;
	}
	
	public boolean isUsableByPlayer(EntityPlayer player) 
	{
		return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}

	public boolean hasCustomName() 
	{
		return this.customName != null && !this.customName.isEmpty();
	}
	
	public void setCustomName(String customName) 
	{
		this.customName = customName;
	}
	
	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
		 return oldState.getBlock() != newState.getBlock();
	}
	
	@Override
	public ITextComponent getDisplayName() 
	{
		return this.hasCustomName() ? new TextComponentString(this.customName) : new TextComponentTranslation("container.pvz_card_table");
	}
	
//	@Override
//	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
//		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return true;
//		return false;
//	}
//	
//	@Override
//	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
//		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T) this.handler;
//		return super.getCapability(capability, facing);
//	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
//		this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
		if(compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) 
	{
		super.writeToNBT(compound);
//		compound.setTag("Inventory", this.handler.serializeNBT());
		if(this.hasCustomName()) compound.setString("CustomName", this.customName);
		return compound;
	}
}
