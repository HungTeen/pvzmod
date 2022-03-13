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

    private static ModelLayerLocation register(String p_171294_) {
        return createLocation(p_171294_, "main");
    }

    private static ModelLayerLocation createLocation(String p_171301_, String p_171302_) {
        return new ModelLayerLocation(Util.prefix(p_171301_), p_171302_);
    }

}
