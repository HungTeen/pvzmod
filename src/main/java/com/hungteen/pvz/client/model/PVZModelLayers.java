package com.hungteen.pvz.client.model;

import com.hungteen.pvz.utils.Util;
import net.minecraft.client.model.geom.ModelLayerLocation;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-11 11:53
 **/
public class PVZModelLayers {

    /*
    Drop Entities.
     */
    public static final ModelLayerLocation SUN = register("sun");
    public static final ModelLayerLocation PLANT_FOOD = register("plant_food");

    /*
    Bullets.
     */
    public static final ModelLayerLocation PEA_BULLET = register("pea_bullet");

    /*
    Animals.
     */
    public static final ModelLayerLocation GRASS_CARP = register("grass_carp");

    /*
    Plant Entities.
     */
    public static final ModelLayerLocation PEA_SHOOTER = register("pea_shooter");
    public static final ModelLayerLocation SUN_FLOWER = register("sun_flower");
    public static final ModelLayerLocation WALL_NUT = register("wall_nut");
    public static final ModelLayerLocation WALL_NUT_ARMOR = register("wall_nut_armor");

    /*
    Zombie Entities.
     */
    public static final ModelLayerLocation NORMAL_ZOMBIE = register("normal_zombie");

    private static ModelLayerLocation register(String p_171294_) {
        return createLocation(p_171294_, "main");
    }

    private static ModelLayerLocation createLocation(String p_171301_, String p_171302_) {
        return new ModelLayerLocation(Util.prefix(p_171301_), p_171302_);
    }

}
