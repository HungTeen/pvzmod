package com.hungteen.pvz.compat;

import com.hungteen.pvz.common.entity.AbstractPAZEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

/**
 * @program: pvzmod-1.16.5
 * @author: HungTeen
 * @create: 2022-03-06 11:53
 **/
public class CompatUtil {

    /**
     * stop bucket my plants and zombies !
     */
    public static boolean canBucketEntity(Level world, Entity entity, ItemStack stack){
        if(stack.getItem().equals(Items.BUCKET)){
            if(entity instanceof AbstractPAZEntity){
                return false;
            }
        }
        return true;
    }

}
