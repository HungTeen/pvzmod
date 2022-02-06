package com.hungteen.pvz.common.tileentity;

import java.util.HashSet;
import java.util.Set;

import com.hungteen.pvz.common.container.SunConverterContainer;
import com.hungteen.pvz.common.enchantment.misc.SunMendingEnchantment;
import com.hungteen.pvz.common.entity.misc.drop.SunEntity;
import com.hungteen.pvz.common.entity.misc.drop.DropEntity.DropStates;
import com.hungteen.pvz.common.item.tool.plant.SunStorageSaplingItem;
import com.hungteen.pvz.utils.MathUtil;

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
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.items.ItemStackHandler;

public class SunConverterTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

	public final ItemStackHandler handler = new ItemStackHandler(9);
	public final IIntArray array = new IntArray(1);
	private final Set<SunEntity> sunSet = new HashSet<>();
	private final int MaxSearchTick = 60;
	private final double MaxSearchRange = 10;
	private int absorbPos = - 1;
	public int tickExist = 0;
	
	public SunConverterTileEntity() {
		super(TileEntityRegister.SUN_CONVERTER.get());
	}
	
	@Override
	public void tick() {
		++ this.tickExist;
		this.tickSunSet();
		if(! level.isClientSide) {
			this.array.set(0, this.checkCanWorkNow() ? 1: 0);
		}
	}
	
	@SuppressWarnings("deprecation")
	private void tickSunSet() {
		if (! level.isClientSide) {
			//maintain the set.
			Set<SunEntity> tmp = new HashSet<>();
			this.sunSet.forEach((sun) -> {
				if(sun != null && ! sun.removed && sun.getDropState() == DropStates.ABSORB) {
					tmp.add(sun);
				}
			});
			this.sunSet.clear();
			this.sunSet.addAll(tmp);
			tmp.clear();
			//if the set is full, then release the sun.
			if(! this.checkCanWorkNow()) {
				this.sunSet.forEach((sun) -> {
					sun.setDropState(DropStates.NORMAL);
				});
				this.sunSet.clear();
				return ;
			}
			//find new sun.
			if(this.tickExist % this.MaxSearchTick == 0) {
			    level.getEntitiesOfClass(SunEntity.class, MathUtil.getAABBWithPos(worldPosition, MaxSearchRange), (sun) -> {
						return sun.getDropState() == DropStates.NORMAL && ! this.sunSet.contains(sun);
			    }).forEach((sun) -> {
			    	sun.setDropState(DropStates.ABSORB);
				    this.sunSet.add(sun);
			    });
			}
			//absorb suns in the set.
			this.sunSet.forEach((sun) -> {
				if(! this.checkCanWorkNow()) return ;
				double speed = 0.15D;
				Vector3d now = new Vector3d(worldPosition.getX() + 0.5D, worldPosition.getY() + 1D, worldPosition.getZ() + 0.5D);
				Vector3d vec = now.subtract(sun.position());
				if(vec.length() <= 1) {
				    this.onCollectSun(sun);
				} else {
				    sun.setDeltaMovement(vec.normalize().scale(speed));
				}
			});
		}
	}
	
	/**
	 * collect when sun is close.
	 */
	private void onCollectSun(SunEntity sun) {
		int amount = sun.getAmount();
		while(this.checkCanWorkNow() && amount > 0) {
			final ItemStack stack = this.handler.getStackInSlot(this.absorbPos);
			if(SunStorageSaplingItem.isNotOnceSapling(stack)) {
				final SunStorageSaplingItem item = (SunStorageSaplingItem) stack.getItem();
				final int max = item.MAX_STORAGE_NUM;
			    int now = SunStorageSaplingItem.getStorageSunAmount(stack);
			    if(now + amount > max) {
				    amount -= max - now;
				    now = max;
			    } else {
				    now += amount;
				    amount = 0;
			    }
			    SunStorageSaplingItem.setStorageSunAmount(stack, now);
			} else {
				SunMendingEnchantment.repairItem(stack, amount);
				amount = 0;
			}
		}
		if(amount > 0) {
			sun.setAmount(amount);
		} else {
			sun.remove();
		}
	}
	
	private boolean checkCanWorkNow() {
		for(int i = 0; i < this.handler.getSlots(); ++ i) {
			final ItemStack stack = this.handler.getStackInSlot(i);
			// sapling absorb.
			if(SunStorageSaplingItem.isNotOnceSapling(stack) && ! SunStorageSaplingItem.isSunStorageFull(stack)) {
				this.absorbPos = i;
				return true;
			}
			// repair tools.
			if(stack.isDamaged()) {
				this.absorbPos = i;
				return true;
			}
		}
		return false;
	}

	/**
	 * Don't rename this method to canInteractWith due to conflicts with Container
	 */
	public boolean isUsableByPlayer(PlayerEntity player) {
		if (this.level.getBlockEntity(this.worldPosition) != this) {
			return false;
		}
		return player.distanceToSqr((double) this.worldPosition.getX() + 0.5D, (double) this.worldPosition.getY() + 0.5D, (double) this.worldPosition.getZ() + 0.5D) <= 64.0D;
	}

	@Override
	public void load(BlockState state, CompoundNBT compound) {
    	super.load(state, compound);
		this.handler.deserializeNBT(compound.getCompound("itemstack_list"));
		this.tickExist = compound.getInt("exist_tick");
	}

	@Override
	public CompoundNBT save(CompoundNBT compound) {
		compound.put("itemstack_list", this.handler.serializeNBT());
		compound.putInt("exist_tick", this.tickExist);
		return super.save(compound);
	}

	@Override
	public Container createMenu(int id, PlayerInventory inv, PlayerEntity player) {
		return new SunConverterContainer(id, player, this.worldPosition);
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TranslationTextComponent("block.pvz.sun_converter");
	}

}
