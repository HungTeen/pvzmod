package com.hungteen.pvz.common.container.inventroy;

import com.hungteen.pvz.utils.NBTUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-28 14:39
 **/
public class ItemInventory {
//        extends Inventory {
//
//    private static final String NAME = "BackPack";
//    private final ItemStack stack;
//
//    public ItemInventory(ItemStack stack, int size) {
//        super(size);
//        this.stack = stack;
//        ListTag list = new ListTag();
//        if(!stack.isEmpty() && stack.getOrCreateTag().contains(NAME)) {
//            list = stack.getOrCreateTag().getList(NAME, NBTUtil.TAG_COMPOUND);
//        }
//        for (int i = 0; i < size && i < list.size(); i++) {
//            setItem(i, ItemStack.of(list.getCompound(i)));
//        }
//    }
//
//    @Override
//    public boolean stillValid(Player player) {
//        return !stack.isEmpty();
//    }
//
//    @Override
//    public void setChanged() {
//        super.setChanged();
//        ListTag list = new ListTag();
//        for (int i = 0; i < getContainerSize(); i++) {
//            list.add(getItem(i).save(new CompoundTag()));
//        }
//        this.stack.getOrCreateTag().put(NAME, list);
//    }
}
