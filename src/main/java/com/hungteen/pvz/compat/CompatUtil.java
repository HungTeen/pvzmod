package com.hungteen.pvz.compat;

import com.hungteen.pvz.common.entity.AbstractPAZEntity;
import com.hungteen.pvz.common.entity.misc.GiftBoxEntity;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

/**
 * @program: pvzmod-1.16.5
 * @author: HungTeen
 * @create: 2022-03-06 11:53
 **/
public class CompatUtil {

    /**
     * stop bucket my plants and zombies !
     */
    public static boolean canBucketEntity(World world, Entity entity, ItemStack stack){
        if(stack.getItem().equals(Items.BUCKET)){
            if(entity instanceof AbstractPAZEntity || entity instanceof GiftBoxEntity){
                return false;
            }
        }
        return true;
    }

}
