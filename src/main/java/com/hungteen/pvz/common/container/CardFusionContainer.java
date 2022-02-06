package com.hungteen.pvz.common.container;

import com.hungteen.pvz.common.block.BlockRegister;
import com.hungteen.pvz.common.item.material.EssenceItem;
import com.hungteen.pvz.common.item.tool.plant.SunStorageSaplingItem;
import com.hungteen.pvz.common.recipe.FusionRecipe;
import com.hungteen.pvz.common.recipe.RecipeRegister;
import com.hungteen.pvz.common.tileentity.CardFusionTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.SlotItemHandler;

import java.util.Optional;

public class CardFusionContainer extends PVZContainer {

	public final CardFusionTileEntity te;
	private final CraftingInventory craftSlots = new CraftingInventory(this, 3, 3);
	private final IWorldPosCallable access;
	private final PlayerEntity player;
	
	public CardFusionContainer(int id, PlayerEntity player, BlockPos pos) {
		super(ContainerRegister.CARD_FUSION.get(), id);
		this.te = (CardFusionTileEntity) player.level.getBlockEntity(pos);
		this.player = player;
		this.access = IWorldPosCallable.create(player.level, pos);
		if(this.te == null) {
			System.out.println("Error: Open Card Fusion GUI !");
			return ;
		}

		this.addDataSlots(this.te.array);

		//sun storage sapling, 1 - 8 craft card, 9 - 11, essence, 12
		this.addSlot(new SlotItemHandler(te.handler, 0, 9, 80) {
			@Override
			public boolean mayPlace(ItemStack stack) {
				return stack.getItem() instanceof SunStorageSaplingItem;
			}
		});
		//essences.
		this.addSlot(new SlotItemHandler(te.handler, 1, 153, 80) {
			@Override
			public boolean mayPlace(ItemStack stack) {
				return stack.getItem() instanceof EssenceItem;
			}
		});
		//result.
		this.addSlot(new SlotItemHandler(te.handler, 2, 81, 98) {
			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		});
		for(int i = 0; i < 3; ++ i){
			for(int j = 0; j < 3; ++ j){
				this.addSlot(new SlotItemHandler(te.handler, i * 3 + j + 3, 63 + j * 18, 26 + i * 18));
			}
		}
		//player inventory.
		this.addInventoryAndHotBar(player, 9, 143);
	}

	public void onCraft(){
		this.te.handler.setStackInSlot(2, getResult().copy());
		this.te.clearCraftingSlots();
		this.te.sunAmount = 0;
		this.te.essenceAmount = 0;
	}

	public ItemStack getResult(){
		for(int i = 0; i < 3; ++ i){
			for(int j = 0; j < 3; ++ j){
				this.craftSlots.setItem(i * 3 + j, this.te.handler.getStackInSlot(i * 3 + j + 3).copy());
			}
		}
		final Optional<FusionRecipe> recipe = this.player.level.getRecipeManager().getRecipeFor(RecipeRegister.FUSION_RECIPE_TYPE, this.craftSlots, this.player.level);
		return recipe.isPresent() ? recipe.get().getResultItem() : ItemStack.EMPTY;
	}

	public boolean canCraft(){
		return this.te.array.get(0) == CardFusionTileEntity.CRAFT_SUN_COST && this.te.array.get(1) == CardFusionTileEntity.CRAFT_ESSENCE_COST && this.te.handler.getStackInSlot(2).isEmpty() && ! this.getResult().isEmpty();
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
	public boolean stillValid(PlayerEntity playerIn) {
		return stillValid(this.access, player, BlockRegister.CARD_FUSION_TABLE.get());
	}
}
