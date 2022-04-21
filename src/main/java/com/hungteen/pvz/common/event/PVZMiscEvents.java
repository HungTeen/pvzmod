package com.hungteen.pvz.common.event;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.types.base.IPAZType;
import com.hungteen.pvz.common.block.cubes.OriginBlock;
import com.hungteen.pvz.common.item.PVZItems;
import com.hungteen.pvz.common.item.spawn.card.SummonCardItem;
import com.hungteen.pvz.common.item.weapon.PeaGunItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.AnvilUpdateEvent;
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

    @SubscribeEvent
    public static void onAnvilUpdate(AnvilUpdateEvent event){
        final ItemStack left = event.getLeft();
        final ItemStack right = event.getRight();
        if(left.is(PVZItems.PEA_GUN.get()) && right.getItem() instanceof SummonCardItem){
            final IPAZType type = ((SummonCardItem) right.getItem()).type;
            if(PeaGunItem.getShootMode(left) != type && PeaGunItem.validShootModeItem(type)){
                final ItemStack result = new ItemStack(PVZItems.PEA_GUN.get());
                PeaGunItem.setShootMode(result, type);
                event.setOutput(result);
                event.setMaterialCost(1);
                event.setCost(30);
            }
        }
    }

}
