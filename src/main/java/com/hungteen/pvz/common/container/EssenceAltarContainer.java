package com.hungteen.pvz.common.container;

import com.hungteen.pvz.common.item.card.PlantCardItem;
import com.hungteen.pvz.common.item.card.SummonCardItem;
import com.hungteen.pvz.register.BlockRegister;
import com.hungteen.pvz.register.ContainerRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.SoundEvents;

public class EssenceAltarContainer extends Container{

	private final Inventory inv = new Inventory(9);
	private final IWorldPosCallable worldPos;
	private final PlayerEntity player;
	
	public EssenceAltarContainer(int id, PlayerEntity player, IWorldPosCallable worldPos) {
		super(ContainerRegister.ESSENCE_ALTAR.get(), id);
		this.player = player;
		this.worldPos = worldPos;
		for(int i = 0; i < 3; ++ i) {
			for(int j = 0; j < 3; ++ j) {
				this.addSlot(new Slot(this.inv, i * 3 + j, 62 + 18 * j, 17 + 18 * i) {
					@Override
					public boolean mayPlace(ItemStack stack) {
						if(! (stack.getItem() instanceof SummonCardItem)) return false;
						return ((SummonCardItem) stack.getItem()).isEnjoyCard;
					}
				});
			}
		}
		for(int i = 0; i < 3; ++ i) {
			for(int j = 0; j < 9; ++ j) {
				this.addSlot(new Slot(player.inventory, j + i * 9 + 9, 8 + 18 * j, 84 + 18 * i));
			}
		}
		for(int i = 0; i < 9; ++ i) {
			this.addSlot(new Slot(player.inventory, i, 8 + 18 * i, 142));
		}
	}
	
	public void destroyAllCards() {
		for(int i = 0; i < inv.getContainerSize(); ++ i) {
			ItemStack stack = inv.getItem(i);
			if(stack.isEmpty() || ! (stack.getItem() instanceof PlantCardItem)) continue;
			Plants plant = ((PlantCardItem) stack.getItem()).plantType;
			if(plant == Plants.IMITATER) plant = Plants.values()[player.getRandom().nextInt(Plants.values().length)];
			PlayerUtil.addPlantXp(player, plant, 5 * stack.getCount());
			stack.shrink(stack.getCount());
		}
		EntityUtil.playSound(player, SoundEvents.ENCHANTMENT_TABLE_USE);
	}
	
	
	public boolean isInventoryEmpty() {
		return this.inv.isEmpty();
	}
	
	@Override
	public void removed(PlayerEntity playerIn) {
		super.removed(playerIn);
		this.worldPos.execute((world, pos) -> {
			clearContainer(playerIn, world, this.inv);
		});
	}
	
	@Override
    public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (index < 9) {
				if (!this.moveItemStackTo(itemstack1, 9, this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (index < 9 + 27) {
				if(!moveItemStackTo(itemstack1, 0, 9, false)
						&& !moveItemStackTo(itemstack1, 9 + 27, this.slots.size(), false)) {
					return ItemStack.EMPTY;
				}
			} else {
				if (!this.moveItemStackTo(itemstack1, 0, 9 + 27, false)) {
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
		return stillValid(this.worldPos, playerIn, BlockRegister.ESSENCE_ALTAR.get());
	}

}
