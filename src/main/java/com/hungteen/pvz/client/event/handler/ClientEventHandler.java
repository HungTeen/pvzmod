package com.hungteen.pvz.client.event.handler;

import com.hungteen.pvz.client.event.PVZClientEvents;
import com.hungteen.pvz.client.render.entity.layer.ColdLayer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.client.event.EntityRenderersEvent;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-18 11:10
 **/
public class ClientEventHandler {

    /**
     * {@link PVZClientEvents#addEntityLayers(EntityRenderersEvent.AddLayers)}
     */
    public static <T extends LivingEntity, M extends EntityModel<T>> void addEntityLayers(LivingEntityRenderer<T, M> renderer) {
        renderer.addLayer(new ColdLayer<>(renderer));
    }

}
