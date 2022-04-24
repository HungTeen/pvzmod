package com.hungteen.pvz.common.menu;

import com.hungteen.pvz.api.types.ISkillType;
import com.hungteen.pvz.api.types.base.IPAZType;
import com.hungteen.pvz.common.block.PVZBlocks;
import com.hungteen.pvz.common.impl.type.SkillTypes;
import com.hungteen.pvz.common.item.misc.EssenceItem;
import com.hungteen.pvz.common.item.spawn.card.SummonCardItem;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-22 10:12
 **/
public class EssenceAltarMenu extends PVZMenu{

    private final Container altarSlots = new SimpleContainer(4) {
        public void setChanged() {
            super.setChanged();
            EssenceAltarMenu.this.slotsChanged(this);
        }
    };
    private static final int LEARN_COST = 4;
    private final ContainerLevelAccess access;
    private final Random random = new Random();

    public EssenceAltarMenu(int id, Inventory inventory) {
        this(id, inventory, ContainerLevelAccess.NULL);
    }

    public EssenceAltarMenu(int id, Inventory inventory, ContainerLevelAccess access) {
        super(id, PVZMenus.ESSENCE_ALTAR.get());
        this.access = access;

        //add summon card slot.
        this.addSlot(new Slot(this.altarSlots, 0, 27, 11){
            @Override
            public boolean mayPlace(@Nonnull ItemStack stack) {
                return super.mayPlace(stack) && stack.getItem() instanceof SummonCardItem && ! ((SummonCardItem) stack.getItem()).isEnjoyCard;
            }
        });

        //add enjoy card slot.
        this.addSlot(new Slot(this.altarSlots, 1, 49, 65){
            @Override
            public boolean mayPlace(@Nonnull ItemStack stack) {
                return super.mayPlace(stack) && stack.getItem() instanceof SummonCardItem && ((SummonCardItem) stack.getItem()).isEnjoyCard;
            }
        });

        //add essence slot.
        this.addSlot(new Slot(this.altarSlots, 2, 14, 40){
            @Override
            public boolean mayPlace(@Nonnull ItemStack stack) {
                return super.mayPlace(stack) && stack.getItem() instanceof EssenceItem;
            }
        });

        //add material slot.
        this.addSlot(new Slot(this.altarSlots, 3, 40, 40));

        //player inventory.
        this.addInventoryAndHotBar(inventory, 8, 88);

    }

    @Override
    public void slotsChanged(Container container) {

    }

    @Override
    public boolean clickMenuButton(Player player, int id) {
        learnSkillAt(player, id);
        return true;
    }

    public void learnSkillAt(Player player, int pos){
        final ISkillType skill = getAvailableSkills().get(pos);
        final int lvl = getCurrentSkillLevel(skill);
        getEnjoyCard().shrink(skill.getCostAt(lvl));
        getEssence().shrink(LEARN_COST);
        getMaterial().shrink(LEARN_COST);
        SkillTypes.addSkillLevel(getSummonCard(), skill, lvl + 1);
        EntityUtil.playSound(player, SoundEvents.ENCHANTMENT_TABLE_USE);
    }

    /**
     * select all skills that can be display on menu.
     */
    public List<ISkillType> getAvailableSkills(){
        final List<ISkillType> list = new ArrayList<>();
        if(getPAZType().isPresent()){
            ISkillType specialSkill = null;
            for (ISkillType skill : getPAZType().get().getSkills()) {
            	if(skill.isSpecial() && getCurrentSkillLevel(skill) > 0) {
            		specialSkill = skill;
            		break;
            	}
            }
            for (ISkillType skill : getPAZType().get().getSkills()) {
                final int lvl = getCurrentSkillLevel(skill);
                if(lvl < skill.getMaxLevel() && ((! skill.isSpecial()) || specialSkill == null || specialSkill == skill)){
                    list.add(skill);
                }
            }
        }
        return list;
    }

    public boolean canLearnCurrentSkill(ISkillType skill){
        return getEssence().getCount() >= LEARN_COST && getMaterial().getCount() >= LEARN_COST && getEnjoyCard().getCount() >= skill.getCostAt(getCurrentSkillLevel(skill));
    }

    public int getCurrentSkillLevel(ISkillType skill){
        return SkillTypes.getSkillLevel(getSummonCard(), skill);
    }

    public int getCurrentSkillCost(ISkillType skill){
        return skill.getCostAt(getCurrentSkillLevel(skill));
    }

    public List<Integer> getCurrentSkillLevel(){
        final List<Integer> list = new ArrayList<>();
        getAvailableSkills().forEach(skill -> {
            list.add(getCurrentSkillLevel(skill));
        });
        return list;
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        this.access.execute((level, blockPos) -> {
            this.clearContainer(player, this.altarSlots);
        });
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.access, player, PVZBlocks.ESSENCE_ALTAR.get());
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slotId) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotId);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (slotId < 4) {
                if (!this.moveItemStackTo(itemstack1, 4, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if(slotId < 4 + 27){
            	if (!this.moveItemStackTo(itemstack1, 0, 4, true) && !this.moveItemStackTo(itemstack1, 4 + 27, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else {
            	if (!this.moveItemStackTo(itemstack1, 0, 4 + 27, true)) {
                    return ItemStack.EMPTY;
                }
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
        }

        return itemstack;
    }

    public Optional<IPAZType> getPAZType(){
        if(getSummonCard().getItem() instanceof SummonCardItem){
            return Optional.ofNullable(((SummonCardItem) getSummonCard().getItem()).type);
        }
        return Optional.empty();
    }

    public ItemStack getSummonCard(){
        return this.altarSlots.getItem(0);
    }

    public ItemStack getEnjoyCard(){
        return this.altarSlots.getItem(1);
    }

    public ItemStack getEssence(){
        return this.altarSlots.getItem(2);
    }

    public ItemStack getMaterial(){
        return this.altarSlots.getItem(3);
    }

}
