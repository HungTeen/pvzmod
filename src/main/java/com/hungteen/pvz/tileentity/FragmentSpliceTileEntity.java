package com.hungteen.pvz.tileentity;

import java.util.HashSet;
import java.util.Set;

import com.hungteen.pvz.gui.container.FragmentSpliceContainer;
import com.hungteen.pvz.item.tool.SunStorageSaplingItem;
import com.hungteen.pvz.item.tool.card.PlantCardItem;
import com.hungteen.pvz.register.TileEntityRegister;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.enums.Essences;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.Ranks;

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

	public static final int MAX_SUN_AMOUNT = 1000;
	public static final int CRAFT_COST = 1000;
	private static final int [] FRAGMENT_ARRAY = new int[] {8, 9, 10, 13, 15, 18, 19, 20};
	public final ItemStackHandler handler = new ItemStackHandler(1 + 25 + 1);
	public IIntArray array = new IntArray(2);
	public int sunAmount = 0;
	
	public FragmentSpliceTileEntity() {
		super(TileEntityRegister.FRAGMENT_SPLICE.get());
	}
	
    @Override
	public void tick() {
		if(! world.isRemote) {
			this.absorbSunAmount();
			this.array.set(0, sunAmount);
			this.array.set(1, this.getResultPlantId());
//			System.out.println(this.getResultPlantId());
		}
	}
    
    public void setResult(int id) {
    	this.sunAmount -= CRAFT_COST;
    	for(int i = 2; i < 2 + 25; ++ i) {
    		this.handler.setStackInSlot(i, ItemStack.EMPTY);
    	}
    	this.handler.setStackInSlot(1, new ItemStack(PlantUtil.getPlantSummonCard(Plants.values()[id])));
    }
    
    private int getResultPlantId() {
    	//check plant enjoy card.
    	Set<PlantCardItem> set = new HashSet<>();
    	for(int i = 0; i < FRAGMENT_ARRAY.length; ++ i) {
    		int pos = FRAGMENT_ARRAY[i];
    		ItemStack stack = this.handler.getStackInSlot(pos);
    		if(stack.getItem() instanceof PlantCardItem && ((PlantCardItem) stack.getItem()).isEnjoyCard) {
    			set.add((PlantCardItem) stack.getItem());
    		} else {
    			return - 1;
    		}
    	}
    	if(set.size() != 1) return -1;
    	//check essence
    	Plants plant = set.iterator().next().plantType;
    	Essences essence = PlantUtil.getPlantEssenceType(plant);
    	for(int i = 0; i < 5; ++ i) {
    		for(int j = 0; j < 5; ++ j) {
    			if(i == 0 || i == 4 || j == 0 || j == 4) {
    				int now = 2 + i * 5 + j;
    				ItemStack stack = this.handler.getStackInSlot(now);
//    				System.out.println(stack.getItem());
    				if(! stack.getItem().equals(Essences.getEssenceItem(essence))) {
    					return -1;
    				}
    			}
    		}
    	}
    	
    	//check card model
    	Ranks rank = PlantUtil.getPlantRankByName(plant);
    	ItemStack stack = this.handler.getStackInSlot(14);
    	if(! stack.getItem().equals(Ranks.getRankCardItem(rank))) {
    		return -1;
    	}
    	return plant.ordinal();
    }
    
    private void absorbSunAmount() {
    	ItemStack stack = this.handler.getStackInSlot(0);
    	if(! stack.isEmpty() && stack.getItem() instanceof SunStorageSaplingItem) {
    		int amount = SunStorageSaplingItem.getStorageSunAmount(stack);
    		int decAmount = Math.min(MAX_SUN_AMOUNT - this.sunAmount, Math.min(25, amount));
    		amount -= decAmount;
    		this.sunAmount += decAmount;
    		SunStorageSaplingItem.setStorageSunAmount(stack, amount);
    	}
    }
    
    @Override
    public void read(CompoundNBT compound) {
    	super.read(compound);
    	this.handler.deserializeNBT(compound.getCompound("itemstack_list"));
    	this.sunAmount = compound.getInt("sun_amount");
    }
    
    @Override
    public CompoundNBT write(CompoundNBT compound) {
    	compound.put("itemstack_list", this.handler.serializeNBT());
    	compound.putInt("sun_amount", this.sunAmount);
    	return super.write(compound);
    }
    
	@Override
	public Container createMenu(int id, PlayerInventory inv, PlayerEntity player) {
		return new FragmentSpliceContainer(id, player, this.pos);
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TranslationTextComponent("gui.pvz.fragment_splice");
	}

	public boolean isUsableByPlayer(PlayerEntity player) {
		if (this.world.getTileEntity(this.pos) != this) {
			return false;
		}
		return player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
	}

}
