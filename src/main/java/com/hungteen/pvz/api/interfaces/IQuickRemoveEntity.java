package com.hungteen.pvz.api.interfaces;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-05-04 12:59
 **/
public interface IQuickRemoveEntity {

    /**
     * this entity can be quick remove by the item.
     */
    boolean isCorrectItem(ItemStack stack);

    /**
     * removed by entity.
     */
    void onQuickRemove(Entity entity, ItemStack stack);

    /**
     * how much will item stack damage.
     */
    default int getQuickRemoveDamage(ItemStack stack){
        return 3;
    }

}
