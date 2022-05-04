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
    Common Layers.
     */
    public static final ModelLayerLocation BUCKET_INNER_ARMOR = registerInnerArmor("bucket");
    public static final ModelLayerLocation BUCKET_OUTER_ARMOR = registerOuterArmor("bucket");

    /*
    Block Entities.
     */
    public static final ModelLayerLocation FLOAT_ORIGIN = register("float_origin");

    /*
    Drop Entities.
     */
    public static final ModelLayerLocation SUN = register("sun");
    public static final ModelLayerLocation PLANT_FOOD = register("plant_food");

    /*
    Bullets.
     */
    public static final ModelLayerLocation COMMON_BULLET = register("pea_bullet");

    /*
    Animals.
     */
    public static final ModelLayerLocation GRASS_CARP = register("grass_carp");
    public static final ModelLayerLocation SPROUT = register("sprout");

    /*
    Plant Entities.
     */
    public static final ModelLayerLocation PEA_SHOOTER = register("pea_shooter");
    public static final ModelLayerLocation SUN_FLOWER = register("sun_flower");
    public static final ModelLayerLocation WALL_NUT = register("wall_nut");
    public static final ModelLayerLocation WALL_NUT_ARMOR = register("wall_nut_armor");
    public static final ModelLayerLocation POTATO_MINE = register("potato_mine");
    public static final ModelLayerLocation SURROUND_DIRT = register("surround_dirt");
    public static final ModelLayerLocation SNOW_PEA = register("snow_pea");
    public static final ModelLayerLocation CABBAGE_PULT = register("cabbage_pult");

    /*
    Zombie Entities.
     */
    public static final ModelLayerLocation NORMAL_ZOMBIE = register("normal_zombie");
    public static final ModelLayerLocation NORMAL_ZOMBIE_INNER_ARMOR = registerInnerArmor("normal_zombie");
    public static final ModelLayerLocation NORMAL_ZOMBIE_OUTER_ARMOR = registerOuterArmor("normal_zombie");

    private static ModelLayerLocation register(String p_171294_) {
        return createLocation(p_171294_, "main");
    }

    private static ModelLayerLocation registerInnerArmor(String p_171299_) {
        return createLocation(p_171299_, "inner_armor");
    }

    private static ModelLayerLocation registerOuterArmor(String p_171304_) {
        return createLocation(p_171304_, "outer_armor");
    }

    private static ModelLayerLocation createLocation(String p_171301_, String p_171302_) {
        return new ModelLayerLocation(Util.prefix(p_171301_), p_171302_);
    }

}
