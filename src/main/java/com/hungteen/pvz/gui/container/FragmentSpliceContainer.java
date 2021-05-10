package com.hungteen.pvz.gui.container;

import java.util.ArrayList;
import java.util.List;

import com.hungteen.pvz.item.tool.SunStorageSaplingItem;
import com.hungteen.pvz.register.ContainerRegister;
import com.hungteen.pvz.tileentity.FragmentSpliceTileEntity;
import com.hungteen.pvz.utils.ItemUtil;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.enums.Essences;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.Ranks;
import com.mojang.datafixers.util.Pair;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.SlotItemHandler;

public class FragmentSpliceContainer extends AbstractOptionContainer {

	public final FragmentSpliceTileEntity te;
	private final PlayerEntity player;

	public FragmentSpliceContainer(int id, PlayerEntity player, BlockPos pos) {
		super(ContainerRegister.FRAGMENT_SPLICE.get(), id);
		this.player = player;
		this.te = (FragmentSpliceTileEntity) player.level.getBlockEntity(pos);
		if(this.te == null) {
			System.out.println("Error: Open Fragment Splice GUI !");
			return ;
		}
		this.addDataSlots(this.te.array);
		this.addSlot(new SlotItemHandler(te.handler, 0, 7, 119) {
			@Override
			public boolean mayPlace(ItemStack stack) {
				return stack.getItem() instanceof SunStorageSaplingItem;
			}
		});
		this.addSlot(new SlotItemHandler(te.handler, 1, 187, 62) {
			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		});
		for (int i = 0; i < 5; ++ i) {
			for (int j = 0; j < 5; ++ j) {
				this.addSlot(new SlotItemHandler(te.handler, j + i * 5 + 2, 61 + j * 18, 26 + i * 18));
			}
		}
		for (int k = 0; k < 3; ++ k) {
			for (int i1 = 0; i1 < 9; ++ i1) {
				this.addSlot(new Slot(player.inventory, i1 + k * 9 + 9, 25 + i1 * 18, 143 + k * 18));
			}
		}
		for (int l = 0; l < 9; ++ l) {
			this.addSlot(new Slot(player.inventory, l, 25 + l * 18, 201));
		}
	}

	@Override
	public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (index == 0) {
				if (!this.moveItemStackTo(itemstack1, 27, this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if(index == 1) {
				if (!this.moveItemStackTo(itemstack1, 27, this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if(index < 27) {
				if (!this.moveItemStackTo(itemstack1, 27, this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (index < 27 + 27) {
				if(!moveItemStackTo(itemstack1, 2, 27, false)
						&& !moveItemStackTo(itemstack1, 27 + 27, this.slots.size(), false)) {
					return ItemStack.EMPTY;
				}
			} else {
				if (!this.moveItemStackTo(itemstack1, 2, 27 + 27, false)) {
					return ItemStack.EMPTY;
				}
			}
			if (itemstack1.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}
		}
		return itemstack;
	}
	
	public void canPutStackBackToInventory() {
		for(int i = 1;i < 2 + 25; ++ i) {
			ItemStack stack = this.te.handler.getStackInSlot(i);
			if(stack.isEmpty()) continue;
			int emptyPos = - 1;
			for(int j = 0; j < this.player.inventory.items.size(); ++ j) {
				ItemStack oldStack = this.player.inventory.items.get(j);
				if(oldStack.isEmpty()) emptyPos = j;
				if(ItemUtil.canItemStackAddTo(oldStack, stack)) {
					int maxSize = oldStack.getMaxStackSize();
					int oldSize = oldStack.getCount();
					int stackSize = stack.getCount();
					if(oldSize + stackSize <= maxSize) {
						oldStack.grow(stackSize);
						stack.shrink(stackSize);
						break;
					} else {
						oldStack.grow(maxSize - oldSize);
						stack.shrink(maxSize - oldSize);
					}
					if(stack.getCount() == 0) {
						break;
					}
				}
			}
			if(stack.getCount() > 0) {
				this.player.inventory.items.set(emptyPos, stack.copy());
				stack.shrink(stack.getCount());
			}
		}
	}
	
	public boolean canShowGhostRecipe() {
		for(int i = 1;i < 2 + 25; ++ i) {
			if(! this.te.handler.getStackInSlot(i).isEmpty()) return false;
		}
		return true;
	}
	
	@Override
	public boolean stillValid(PlayerEntity playerIn) {
		return this.te.isUsableByPlayer(playerIn);
	}

	public List<Pair<Ingredient, Slot>> getRecipeForPlant(Plants plantType){
		Ranks rank = PlantUtil.getPlantRankByName(plantType);
		Essences essence = PlantUtil.getPlantEssenceType(plantType);
		Ingredient fragment = Ingredient.of(PlantUtil.getPlantEnjoyCard(plantType));
		Ingredient template = Ingredient.of(Ranks.getRankCardItem(rank));
		Ingredient special = Ingredient.of(Essences.getEssenceItem(essence).get());
		Ingredient result = Ingredient.of(PlantUtil.getPlantSummonCard(plantType));
		List<Pair<Ingredient, Slot>> list = new ArrayList<>();
		for(int i = 0; i < 5; ++ i) {
			for(int j = 0; j < 5; ++ j) {
				int slotId = 2 + i * 5 + j;
				Slot slot = this.getSlot(slotId);
				if(i == 0 || i == 4 || j == 0 || j == 4) {
					list.add(Pair.of(special, slot));
				} else if(i == 2 && j == 2) {
					list.add(Pair.of(template, slot));
				} else {
					list.add(Pair.of(fragment, slot));
				}
			}
		}
		list.add(Pair.of(result, this.getSlot(1)));
		return list;
	}
	
	@Override
	public boolean isCraftSlot(Slot slot) {
		return slot != null && slot.index > 0 && slot.index < 2 + 25;
	}

}
