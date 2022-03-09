package com.hungteen.pvz.common.tileentity;

import com.hungteen.pvz.common.container.CardFusionContainer;
import com.hungteen.pvz.common.item.material.EssenceItem;
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

public class CardFusionTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

	public final ItemStackHandler handler = new ItemStackHandler(12);
	public static final int CRAFT_ESSENCE_COST = 8;
	public static final int CRAFT_SUN_COST = 5000;
	public IIntArray array = new IntArray(2);
	public int sunAmount = 0;
	public int essenceAmount = 0;
	
	public CardFusionTileEntity() {
		super(TileEntityRegister.CARD_FUSION.get());
	}

	@Override
	public void tick() {
		if(! level.isClientSide) {
			this.absorbSunAmount();
			this.absorbEssences();
			this.array.set(0, sunAmount);
			this.array.set(1, essenceAmount);
		}
	}
	
	private void absorbSunAmount() {
    	ItemStack stack = this.handler.getStackInSlot(0);
    	if(! stack.isEmpty() && stack.getItem() instanceof SunStorageSaplingItem) {
    		int amount = SunStorageSaplingItem.getStorageSunAmount(stack);
    		int decAmount = Math.min(CRAFT_SUN_COST - this.sunAmount, Math.min(100, amount));
    		amount -= decAmount;
    		this.sunAmount += decAmount;
    		SunStorageSaplingItem.setStorageSunAmount(stack, amount);
    	}
    }

	private void absorbEssences() {
		ItemStack stack = this.handler.getStackInSlot(1);
		if(! stack.isEmpty() && stack.getItem() instanceof EssenceItem && this.essenceAmount < CRAFT_ESSENCE_COST) {
			stack.shrink(1);
			++ this.essenceAmount;
		}
	}

	public void clearCraftingSlots(){
		for(int i = 3; i < 12; ++ i){
			this.handler.getStackInSlot(i).shrink(1);
//			this.handler.setStackInSlot(i, ItemStack.EMPTY);
		}
	}
	
	@Override
    public void load(BlockState state, CompoundNBT compound) {
    	super.load(state, compound);
    	this.handler.deserializeNBT(compound.getCompound("itemstack_list"));
    	this.sunAmount = compound.getInt("sun_amount");
		this.essenceAmount = compound.getInt("essence_amount");
    }
    
    @Override
    public CompoundNBT save(CompoundNBT compound) {
    	compound.put("itemstack_list", this.handler.serializeNBT());
    	compound.putInt("sun_amount", this.sunAmount);
		compound.putInt("essence_amount", this.essenceAmount);
    	return super.save(compound);
    }
    
	@Override
	public Container createMenu(int id, PlayerInventory inv, PlayerEntity player) {
		return new CardFusionContainer(id, player, this.worldPosition);
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TranslationTextComponent("gui.pvz.card_fusion");
	}

}
