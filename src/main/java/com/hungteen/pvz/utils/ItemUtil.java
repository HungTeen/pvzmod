package com.hungteen.pvz.utils;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-21 20:57
 **/
public class ItemUtil {

    public static void dropItem(Level world, ItemStack stack, double x, double y, double z){
        world.addFreshEntity(new ItemEntity(world, x, y, z, stack));
    }
}
