package com.hungteen.pvz.common.item.base;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-19 10:14
 **/
public class PVZFoodItem extends Item {

    public PVZFoodItem(FoodProperties foodProperties) {
        super(new Properties().tab(CreativeModeTab.TAB_FOOD).food(foodProperties));
    }

}
