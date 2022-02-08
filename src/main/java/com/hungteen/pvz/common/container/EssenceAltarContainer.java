package com.hungteen.pvz.common.container;

import com.hungteen.pvz.api.types.IPAZType;
import com.hungteen.pvz.api.types.ISkillType;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.common.item.material.EssenceItem;
import com.hungteen.pvz.common.item.spawn.card.ImitaterCardItem;
import com.hungteen.pvz.common.item.spawn.card.SummonCardItem;
import com.hungteen.pvz.common.tileentity.EssenceAltarTileEntity;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;
import java.util.*;

public class EssenceAltarContainer extends PVZContainer{

	private static final int LEARN_COST = 4;
	public final EssenceAltarTileEntity te;
	private final PlayerEntity player;
	
	public EssenceAltarContainer(int id, PlayerEntity player, BlockPos worldPos) {
		super(ContainerRegister.ESSENCE_ALTAR.get(), id);
		this.player = player;
		this.te = (EssenceAltarTileEntity) player.level.getBlockEntity(worldPos);

		//add summon card slot.
		this.addSlot(new SlotItemHandler(this.te.handler, 0, 27, 9){
			@Override
			public boolean mayPlace(@Nonnull ItemStack stack) {
				return super.mayPlace(stack) && stack.getItem() instanceof SummonCardItem && ! (stack.getItem() instanceof ImitaterCardItem) && ! ((SummonCardItem) stack.getItem()).isEnjoyCard;
			}
		});

		//add enjoy card slot.
		this.addSlot(new SlotItemHandler(this.te.handler, 1, 49, 63){
			@Override
			public boolean mayPlace(@Nonnull ItemStack stack) {
				return super.mayPlace(stack) && stack.getItem() instanceof SummonCardItem && ((SummonCardItem) stack.getItem()).isEnjoyCard;
			}
		});

		//add essence slot.
		this.addSlot(new SlotItemHandler(this.te.handler, 2, 14, 38){
			@Override
			public boolean mayPlace(@Nonnull ItemStack stack) {
				return super.mayPlace(stack) && stack.getItem() instanceof EssenceItem;
			}
		});

		//add material slot.
		this.addSlot(new SlotItemHandler(this.te.handler, 3, 40, 38));

		this.addInventoryAndHotBar(player, 8, 84);
	}

	public void learnSkillAt(int pos){
		final ISkillType skill = getAvailableSkills().get(pos);
		final int lvl = getCurrentSkillLevel(skill);
		getEnjoyCard().shrink(skill.getCostAt(lvl));
		getEssence().shrink(LEARN_COST);
		getMaterial().shrink(LEARN_COST);
		SkillTypes.addSkillLevel(getSummonCard(), skill, lvl + 1);
		EntityUtil.playSound(player, SoundEvents.ENCHANTMENT_TABLE_USE);
	}

	public Optional<IPAZType> getPAZType(){
		if(getSummonCard().getItem() instanceof SummonCardItem){
			return Optional.ofNullable(((SummonCardItem) getSummonCard().getItem()).type);
		}
		return Optional.empty();
	}

	public ItemStack getSummonCard(){
		return this.te.handler.getStackInSlot(0);
	}

	public ItemStack getEnjoyCard(){
		return this.te.handler.getStackInSlot(1);
	}

	public ItemStack getEssence(){
		return this.te.handler.getStackInSlot(2);
	}

	public ItemStack getMaterial(){
		return this.te.handler.getStackInSlot(3);
	}

	public int getFragmentCount(){
		if(getSummonCard().getItem() instanceof SummonCardItem && getEnjoyCard().getItem() instanceof SummonCardItem){
			if(((SummonCardItem) getSummonCard().getItem()).type == ((SummonCardItem) getEnjoyCard().getItem()).type){
				return getEnjoyCard().getCount();
			}
		}
		return 0;
	}

	public List<ISkillType> getAvailableSkills(){
		final List<ISkillType> list = new ArrayList<>();
		if(getPAZType().isPresent() && isMaterialEnough()){
			final Set<String> set = new HashSet<>();
			final int point = getFragmentCount();
			getPAZType().get().getSkills().forEach(skill -> {
				final int lvl = getCurrentSkillLevel(skill);
				if(! set.contains(skill.getConflictGroup()) && lvl < skill.getMaxLevel() && skill.getCostAt(lvl) <= point){
					list.add(skill);
					set.add(skill.getConflictGroup());
				}
			});
		}
		return list;
	}

	public int getCurrentSkillLevel(ISkillType skill){
		return SkillTypes.getSkillLevel(getSummonCard(), skill);
	}

	public List<Integer> getCurrentSkillLevel(){
		final List<Integer> list = new ArrayList<>();
		getAvailableSkills().forEach(skill -> {
			list.add(getCurrentSkillLevel(skill));
		});
		return list;
	}

	private boolean isMaterialEnough(){
		if(getPAZType().isPresent()){
			final IPAZType type = getPAZType().get();
			if(type.getEssence().getEssenceItem().equals(getEssence().getItem()) && getEssence().getCount() >= LEARN_COST){
				if(type.getRank().getMaterial().contains(getMaterial().getItem()) && getMaterial().getCount() >= LEARN_COST){
					return true;
				}
			}
		}
		return false;
	}

	@Override
    public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (index < 4) {
				if (!this.moveItemStackTo(itemstack1, 4, this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (index < 4 + 27) {
				if(!moveItemStackTo(itemstack1, 0, 4, false)
						&& !moveItemStackTo(itemstack1, 4 + 27, this.slots.size(), false)) {
					return ItemStack.EMPTY;
				}
			} else {
				if (!this.moveItemStackTo(itemstack1, 0, 4 + 27, false)) {
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
		return this.te.isUsableByPlayer(playerIn);
	}

}
