package com.hungteen.pvz.common.entity;

import net.minecraft.world.entity.MobCategory;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-28 16:09
 **/
public class PVZMobCategories {

    public static final MobCategory PVZ_PLANT = MobCategory.create("PVZ_PLANT", "pvz_plant", 60, true, false, 128);
    public static final MobCategory PVZ_ZOMBIE = MobCategory.create("PVZ_ZOMBIE", "pvz_zombie", 50, false, false, 128);

}
