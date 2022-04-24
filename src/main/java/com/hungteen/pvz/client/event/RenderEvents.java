package com.hungteen.pvz.client.event;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.entity.creature.garden.GardenPlant;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-21 16:26
 **/
@Mod.EventBusSubscriber(modid = PVZMod.MOD_ID, value = Dist.CLIENT)
public class RenderEvents {

    @SubscribeEvent
    public static void livingPreRender(RenderLivingEvent.Pre event){
        if(event.getEntity() instanceof GardenPlant){
            if(! ((GardenPlant) event.getEntity()).isSprout()){
                event.setCanceled(true);
            }
        }
    }

}
