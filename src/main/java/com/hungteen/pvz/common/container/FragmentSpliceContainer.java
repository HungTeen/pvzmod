package com.hungteen.pvz.common.container;

import com.hungteen.pvz.common.block.BlockRegister;
import com.hungteen.pvz.common.item.spawn.card.SummonCardItem;
import com.hungteen.pvz.common.item.tool.plant.SunStorageSaplingItem;
import com.hungteen.pvz.common.recipe.RecipeRegister;
import com.hungteen.pvz.common.recipe.FragmentRecipe;
import com.hungteen.pvz.common.tileentity.FragmentSpliceTileEntity;
import com.hungteen.pvz.utils.PlayerUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;
import java.util.Optional;

public class FragmentSpliceContainer extends PVZContainer {

	public final FragmentSpliceTileEntity te;
	private final CraftingInventory craftSlots = new CraftingInventory(this, 5, 5);
	private final IWorldPosCallable access;
	private final PlayerEntity player;

	public FragmentSpliceContainer(int id, PlayerEntity player, BlockPos pos) {
		super(ContainerRegister.FRAGMENT_SPLICE.get(), id);
		this.te = (FragmentSpliceTileEntity) player.level.getBlockEntity(pos);
		this.player = player;
		this.access = IWorldPosCallable.create(player.level, pos);
		if(this.te == null) {
			System.out.println("Error: Open Fragment Splice GUI !");
			return ;
		}

		this.addDataSlots(this.te.array);

		// sun provider slot.
		this.addSlot(new SlotItemHandler(te.handler, 0, 7, 119) {
			@Override
			public boolean mayPlace(ItemStack stack) {
				return stack.getItem() instanceof SunStorageSaplingItem;
			}
		});
		// result slot.
		this.addSlot(new SlotItemHandler(te.handler, 1, 187, 62){
			@Override
			public boolean mayPlace(@Nonnull ItemStack stack) {
				return false;
			}
		});
		// input slots.
		for (int i = 0; i < 5; ++ i) {
			for (int j = 0; j < 5; ++ j) {
				this.addSlot(new SlotItemHandler(te.handler, j + i * 5 + 2, 61 + j * 18, 26 + i * 18));
			}
		}
		// player inventory
		for (int k = 0; k < 3; ++ k) {
			for (int i1 = 0; i1 < 9; ++ i1) {
				this.addSlot(new Slot(player.inventory, i1 + k * 9 + 9, 25 + i1 * 18, 143 + k * 18));
			}
		}
		for (int l = 0; l < 9; ++ l) {
			this.addSlot(new Slot(player.inventory, l, 25 + l * 18, 201));
		}
	}

	public void onCraft(){
		final ItemStack result = getResult().copy();
		this.te.handler.setStackInSlot(1, result);
		this.te.clearCraftingSlots();
		this.te.sunAmount = 0;
		if(result.getItem() instanceof SummonCardItem){
			PlayerUtil.setPAZLock(this.player, ((SummonCardItem) result.getItem()).type, false);
		}
	}

	public ItemStack getResult(){
		for(int i = 0; i < 5; ++ i){
			for(int j = 0; j < 5; ++ j){
				this.craftSlots.setItem(i * 5 + j, this.te.handler.getStackInSlot(i * 5 + j + 2).copy());
			}
		}
		final Optional<FragmentRecipe> recipe = this.player.level.getRecipeManager().getRecipeFor(RecipeRegister.FRAGMENT_RECIPE_TYPE, this.craftSlots, this.player.level);
		return recipe.isPresent() ? recipe.get().getResultItem() : ItemStack.EMPTY;
	}

	public boolean canCraft(){
		return this.te.array.get(0) == FragmentSpliceTileEntity.CRAFT_COST && this.te.handler.getStackInSlot(1).isEmpty() && ! this.getResult().isEmpty();
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
	
	@Override
	public boolean stillValid(PlayerEntity playerIn) {
		return stillValid(this.access, player, BlockRegister.FRAGMENT_SPLICE.get());
	}

}
