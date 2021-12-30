package com.hungteen.pvz.common.event;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.block.cubes.OriginBlock;
import net.minecraftforge.event.TagsUpdatedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PVZMod.MOD_ID)
public class PVZMiscEvents {

    @SubscribeEvent
    public static void onTagsChange(TagsUpdatedEvent.CustomTagTypes event){
        OriginBlock.updateRadiationMap();
    }

}
