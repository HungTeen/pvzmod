package com.hungteen.pvz.client.event;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.client.event.handler.ClientEventHandler;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-18 10:27
 **/
@Mod.EventBusSubscriber(modid = PVZMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEvents {

    @SubscribeEvent
    public static void addEntityLayers(EntityRenderersEvent.AddLayers event){
        try {
            //get private field.
            Field field = EntityRenderersEvent.AddLayers.class.getDeclaredField("renderers");
            field.setAccessible(true);

            event.getSkins().forEach(skin -> {
                LivingEntityRenderer<Player, EntityModel<Player>> render = event.getSkin(skin);
                ClientEventHandler.addEntityLayers(Objects.requireNonNull(render));
            });

            try {
                ((Map<EntityType<?>, EntityRenderer<?>>) field.get(event)).values().stream()
                        .filter(LivingEntityRenderer.class::isInstance)
                        .map(LivingEntityRenderer.class::cast)
                        .forEach(ClientEventHandler::addEntityLayers);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

}
