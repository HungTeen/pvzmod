package com.hungteen.pvz.client.render.entity.drop;

import com.hungteen.pvz.client.model.PVZModelLayers;
import com.hungteen.pvz.client.model.misc.DropEntityModel;
import com.hungteen.pvz.common.entity.drop.PlantFood;
import com.hungteen.pvz.utils.Util;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-13 09:15
 **/
public class PlantFoodRender extends DropEntityRender<PlantFood> {

    private static final ResourceLocation PLANT_FOOD = Util.prefix("textures/entity/drop/plant_food.png");

    public PlantFoodRender(EntityRendererProvider.Context context) {
        super(context, new DropEntityModel<>(context.bakeLayer(PVZModelLayers.PLANT_FOOD)));
    }

    @Override
    protected float getScaleByEntity(PlantFood entity) {
        return 1.3F;
    }

    @Override
    public ResourceLocation getTextureLocation(PlantFood entity) {
        return PLANT_FOOD;
    }
}
