package com.hungteen.pvz.tileentity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.hungteen.pvz.gui.container.CardFusionContainer;
import com.hungteen.pvz.item.tool.SunStorageSaplingItem;
import com.hungteen.pvz.item.tool.card.PlantCardItem;
import com.hungteen.pvz.misc.recipe.FusionRecipes;
import com.hungteen.pvz.register.TileEntityRegister;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.enums.Plants;
import com.mojang.datafixers.util.Pair;

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

	public final ItemStackHandler handler = new ItemStackHandler(13);
	public static final int MAX_SUN_AMOUNT = 2000;
	public static final int CRAFT_COST = 2000;
	public IIntArray array = new IntArray(2);
	public int sunAmount = 0;
	
	public CardFusionTileEntity() {
		super(TileEntityRegister.CARD_FUSION.get());
	}

	@Override
	public void tick() {
		if(! level.isClientSide) {
			this.absorbSunAmount();
			this.array.set(0, sunAmount);
			this.array.set(1, this.getResultPlantId());
		}
	}
	
	/**
	 * run when click craft button.
	 */
	public void setResult(int id) {
    	this.sunAmount -= CRAFT_COST;
    	for(int i = 1; i < 9; ++ i) {
    		this.handler.setStackInSlot(i, ItemStack.EMPTY);
    	}
    	for(int i = 9; i < 12; ++ i) {
    		this.handler.getStackInSlot(i).shrink(1);
    	}
    	FusionRecipes recipe = FusionRecipes.values()[id];
    	Plants plant = getFusionResult(recipe);
    	this.handler.setStackInSlot(12, new ItemStack(PlantUtil.getPlantSummonCard(plant)));
    }
	
	private Plants getFusionResult(FusionRecipes recipe) {
		int pos = level.random.nextInt(100);
		int now = 0;
		for(Pair<Plants, Integer> pair : recipe.resultPlants) {
			now += pair.getSecond();
			if(pos < now) return pair.getFirst();
		}
		pos = level.random.nextInt(recipe.requirePlants.size());
		return recipe.requirePlants.get(pos);
	}
	
	private int getResultPlantId() {
		if(! this.handler.getStackInSlot(12).isEmpty() || this.sunAmount < CRAFT_COST || ! checkEssences()) return - 1;
		List<Plants> has = new ArrayList<>();
		for(int i = 1; i < 9; ++ i) {
			if(this.handler.getStackInSlot(i).isEmpty()) continue;
			if(! (this.handler.getStackInSlot(i).getItem() instanceof PlantCardItem)) continue;
			has.add(((PlantCardItem) this.handler.getStackInSlot(i).getItem()).plantType);
		}
		for(FusionRecipes recipe : FusionRecipes.values()) {
			if(recipe.requirePlants.size() != has.size()) continue;
			Set<Integer> set = new HashSet<>();
			boolean match = true;
			for(Plants p : recipe.requirePlants) {
				boolean got = false;
				for(int i = 0; i < has.size(); ++ i) {
					if(p == has.get(i) && ! set.contains(i)) {//find a valide pos to match one
						set.add(i);
						got = true;
						break;
					}
				}
				if(got == false) {
					match = false;
					break;
				}
			}
			if(match) {
				return recipe.ordinal();
			}
		}
		return - 1;
	}
	
	private boolean checkEssences() {
		for(int i = 9; i < 12; ++ i) {
			if(this.handler.getStackInSlot(i).isEmpty()) return false;
		}
		return true;
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
    public void load(BlockState state, CompoundNBT compound) {
    	super.load(state, compound);
    	this.handler.deserializeNBT(compound.getCompound("itemstack_list"));
    	this.sunAmount = compound.getInt("sun_amount");
    }
    
    @Override
    public CompoundNBT save(CompoundNBT compound) {
    	compound.put("itemstack_list", this.handler.serializeNBT());
    	compound.putInt("sun_amount", this.sunAmount);
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
	
	public boolean isUsableByPlayer(PlayerEntity player) {
		if (this.level.getBlockEntity(this.worldPosition) != this) {
			return false;
		}
		return player.distanceToSqr((double) this.worldPosition.getX() + 0.5D, (double) this.worldPosition.getY() + 0.5D, (double) this.worldPosition.getZ() + 0.5D) <= 64.0D;
	}

}
