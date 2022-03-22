package com.hungteen.pvz.common.event;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.block.cubes.OriginBlock;
import net.minecraftforge.event.TagsUpdatedEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-10 09:36
 **/
@Mod.EventBusSubscriber(modid = PVZMod.MOD_ID)
public class PVZMiscEvents {

    @SubscribeEvent
    public static void onTagsChange(TagsUpdatedEvent event){
    }

    @SubscribeEvent
    public static void addTrades(WandererTradesEvent event){
        //TODO villager trade.
//        event.getGenericTrades().add(new BasicTrade(8, new ItemStack(ItemRegister.SPORE.get()), 8, 5));
//        event.getRareTrades().add(new BasicTrade(24, new ItemStack(Items.MYCELIUM), 4, 15));
    }

}
