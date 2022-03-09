package com.hungteen.pvz.common.tileentity;

import com.hungteen.pvz.common.container.FragmentSpliceContainer;
import com.hungteen.pvz.common.item.tool.plant.SunStorageSaplingItem;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.items.ItemStackHandler;

public class FragmentSpliceTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider{

	public static final int CRAFT_COST = 10000;
	public final ItemStackHandler handler = new ItemStackHandler(1 + 1 + 25);
	public IIntArray array = new IntArray(2);
	public int sunAmount = 0;
	
	public FragmentSpliceTileEntity() {
		super(TileEntityRegister.FRAGMENT_SPLICE.get());
	}
	
    @Override
	public void tick() {
		if(! level.isClientSide) {
			this.absorbSunAmount();
			this.array.set(0, sunAmount);
		}
	}
    
    private void absorbSunAmount() {
    	ItemStack stack = this.handler.getStackInSlot(0);
    	if(! stack.isEmpty() && stack.getItem() instanceof SunStorageSaplingItem) {
    		int amount = SunStorageSaplingItem.getStorageSunAmount(stack);
    		int decAmount = Math.min(CRAFT_COST - this.sunAmount, Math.min(100, amount));
    		amount -= decAmount;
    		this.sunAmount += decAmount;
    		SunStorageSaplingItem.setStorageSunAmount(stack, amount);
    	}
    }

	public void clearCraftingSlots(){
		for(int i = 0; i < 25; ++ i){
			this.handler.getStackInSlot(i + 2).shrink(1);
//			this.handler.setStackInSlot(i + 2, ItemStack.EMPTY);
		}
	}
    
    @Override
    public void load(BlockState state, CompoundNBT compound) {
    	super.load(state, compound);
    	this.handler.deserializeNBT(compound.getCompound("item_stack_list"));
    	this.sunAmount = compound.getInt("sun_amount");
    }
    
    @Override
    public CompoundNBT save(CompoundNBT compound) {
    	compound.put("item_stack_list", this.handler.serializeNBT());
    	compound.putInt("sun_amount", this.sunAmount);
    	return super.save(compound);
    }
    
	@Override
	public Container createMenu(int id, PlayerInventory inv, PlayerEntity player) {
		return new FragmentSpliceContainer(id, player, this.worldPosition);
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TranslationTextComponent("gui.pvz.fragment_splice");
	}

}
