package com.hungteen.pvz.common.event;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.block.cubes.OriginBlock;
import com.hungteen.pvz.common.item.ItemRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.common.BasicTrade;
import net.minecraftforge.event.TagsUpdatedEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PVZMod.MOD_ID)
public class PVZMiscEvents {

    @SubscribeEvent
    public static void onTagsChange(TagsUpdatedEvent.CustomTagTypes event){
        OriginBlock.updateRadiationMap();
    }

    @SubscribeEvent
    public static void addTrades(WandererTradesEvent event){
        event.getGenericTrades().add(new BasicTrade(8, new ItemStack(ItemRegister.SPORE.get()), 8, 5));
        event.getRareTrades().add(new BasicTrade(24, new ItemStack(Items.MYCELIUM), 4, 15));
    }

}
