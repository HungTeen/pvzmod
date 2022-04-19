package com.hungteen.pvz.client.render.entity.plant;

import com.hungteen.pvz.client.render.entity.PVZMobRender;
import com.hungteen.pvz.common.entity.plant.base.PVZPlant;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-28 18:18
 **/
public class PVZPlantRender<T extends PVZPlant> extends PVZMobRender<T> {

    public PVZPlantRender(EntityRendererProvider.Context rendererManager, EntityModel<T> entityModelIn, float shadowSizeIn) {
        super(rendererManager, entityModelIn, shadowSizeIn);
        this.addPlantLayers();
    }

    protected void addPlantLayers(){
//        this.addLayer(new EnergyLayer<>(this));
//        this.addLayer(new CharmLayer<>(this));
//        this.addLayer(new PumpkinArmorLayer<>(this));
//        this.addLayer(new SunLightLayer<>(this));
//        this.addLayer(new HealLightLayer<>(this));
//        this.addLayer(new PlantLadderLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(T plant) {
        return plant.getPAZType().getDefaultResource();
    }

}
