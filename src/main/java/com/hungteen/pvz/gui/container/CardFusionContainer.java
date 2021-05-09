package com.hungteen.pvz.gui.container;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.hungteen.pvz.item.material.EssenceItem;
import com.hungteen.pvz.item.tool.SunStorageSaplingItem;
import com.hungteen.pvz.item.tool.card.PlantCardItem;
import com.hungteen.pvz.misc.recipe.FusionRecipes;
import com.hungteen.pvz.register.ContainerRegister;
import com.hungteen.pvz.tileentity.CardFusionTileEntity;
import com.hungteen.pvz.utils.ItemUtil;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.enums.Plants;
import com.mojang.datafixers.util.Pair;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.SlotItemHandler;

public class CardFusionContainer extends AbstractOptionContainer {

	public final CardFusionTileEntity te;
	private final PlayerEntity player;
	
	public CardFusionContainer(int id, PlayerEntity player, BlockPos pos) {
		super(ContainerRegister.CARD_FUSION.get(), id);
		this.te = (CardFusionTileEntity) player.level.getBlockEntity(pos);
		this.player = player;
		if(this.te == null) {
			System.out.println("Error: Open Card Fusion GUI !");
			return ;
		}
		this.addDataSlots(this.te.array);
		//0 sun storage sapling, 1 - 8 craft card, 9 - 11, essence, 12 result
		this.addSlot(new SlotItemHandler(te.handler, 0, 7, 155) {
			@Override
			public boolean mayPlace(ItemStack stack) {
				return stack.getItem() instanceof SunStorageSaplingItem;
			}
		});
		int now = 1;
		for(int i = 0; i < 3; ++ i) {
			for(int j = 0; j < 3; ++ j) {
				if(i == 1 && j == 1) continue;
				this.addSlot(new SlotItemHandler(te.handler, now ++, 58 + j * 54, 26 + i * 54) {
					@Override
					public boolean mayPlace(ItemStack stack) {
						if(! (stack.getItem() instanceof PlantCardItem)) return false;
						PlantCardItem item = (PlantCardItem) stack.getItem();
						return ! item.isEnjoyCard;
					}
				});
			}
		}
		for(int i = 0; i < 3; ++ i) {
			this.addSlot(new SlotItemHandler(te.handler, 9 + i, 217, 26 + i * 54) {
				@Override
				public boolean mayPlace(ItemStack stack) {
					return stack.getItem() instanceof EssenceItem;
				}
			});
		}
		this.addSlot(new SlotItemHandler(te.handler, 12, 112, 80) {
			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		});
		for(int i = 0; i < 3; ++ i) {
			for(int j = 0; j < 9; ++ j) {
				this.addSlot(new Slot(this.player.inventory, j + i * 9 + 9, 40 + 18 * j, 174 + 18 * i));
			}
		}
		for(int i = 0; i < 9; ++ i) {
			this.addSlot(new Slot(this.player.inventory, i, 40 + i * 18, 232));
		}
	}

	public List<Pair<Ingredient, Slot>> getRecipeForPlant(Plants plantType) {
		Optional<FusionRecipes> recipe = FusionRecipes.getFusionRecipe(plantType);
		List<Pair<Ingredient, Slot>> list = new ArrayList<>();
		if(! recipe.isPresent()) return list;
		int pos = 0;
		for(Plants plant : recipe.get().requirePlants) {
			Ingredient card = Ingredient.of(PlantUtil.getPlantSummonCard(plant));
			list.add(Pair.of(card, this.getSlot(++ pos)));
		}
		Ingredient result = Ingredient.of(PlantUtil.getPlantSummonCard(plantType));
		list.add(Pair.of(result, this.getSlot(12)));
		return list;
	}
	
	public void canPutStackBackToInventory() {
		for(int i = 1;i < 9; ++ i) {
			this.putPosSlotBack(i);
		}
		this.putPosSlotBack(12);
	}
	
	private void putPosSlotBack(int i) {
		ItemStack stack = this.te.handler.getStackInSlot(i);
		if(stack.isEmpty()) return ;
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
	
	@Override
	public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (index == 0 || index < 12) {
				if (!this.moveItemStackTo(itemstack1, 13, this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (index < 13 + 27) {
				if(!moveItemStackTo(itemstack1, 0, 13, false)
						&& !moveItemStackTo(itemstack1, 13 + 27, this.slots.size(), false)) {
					return ItemStack.EMPTY;
				}
			} else {
				if (!this.moveItemStackTo(itemstack1, 0, 13 + 27, false)) {
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
	
	@Override
	public boolean isCraftSlot(Slot slot) {
		return slot != null && (slot.index > 0 && slot.index < 9 || slot.index == 12);
	}

	@Override
	public boolean stillValid(PlayerEntity playerIn) {
		return this.te.isUsableByPlayer(playerIn);
	}
	
}
