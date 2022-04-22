package com.hungteen.pvz.common.menu;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import org.jetbrains.annotations.Nullable;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-22 10:13
 **/
public abstract class PVZMenu extends AbstractContainerMenu {

    public PVZMenu(int id, @Nullable MenuType<?> menuType) {
        super(menuType, id);
    }

    /**
     * default offset.
     */
    public void addInventoryAndHotBar(Inventory inventory, int leftX, int leftY) {
        this.addPlayerInventory(inventory, leftX, leftY);
        this.addPlayerHotBar(inventory, leftX, leftY + 58);
    }

    public void addPlayerInventory(Inventory inventory, int leftX, int leftY) {
        for(int i = 0; i < 3; ++ i) {
            for(int j = 0; j < 9; ++ j) {
                this.addSlot(new Slot(inventory, j + i * 9 + 9, leftX + 18 * j, leftY + 18 * i));
            }
        }
    }

    public void addPlayerHotBar(Inventory inventory, int leftX, int leftY) {
        for(int i = 0; i < 9; ++ i) {
            this.addSlot(new Slot(inventory, i, leftX + 18 * i, leftY));
        }
    }
}
