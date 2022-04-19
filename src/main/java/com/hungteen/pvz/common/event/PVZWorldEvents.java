package com.hungteen.pvz.common.event;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.block.PVZBlocks;
import com.hungteen.pvz.common.entity.misc.PVZBoat;
import com.hungteen.pvz.utils.MathUtil;
import net.minecraftforge.event.world.SaplingGrowTreeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-17 20:01
 **/
@Mod.EventBusSubscriber(modid= PVZMod.MOD_ID)
public class PVZWorldEvents {

    @SubscribeEvent
    public static void onTreeGrow(SaplingGrowTreeEvent ev) {
        if(! ev.getWorld().isClientSide()) {
            final boolean isBlocked = ! ev.getWorld().isEmptyBlock(ev.getPos().above());
            if(PVZConfig.allowNaturalTurnOrigin() || isBlocked) {
                final double chance = PVZConfig.getSaplingTurnChance();
                if(MathUtil.randDouble(ev.getRand(), isBlocked ? chance * 1.5 : chance) && ev.getPos().getY() > 2) {
                    ev.getWorld().setBlock(ev.getPos().below(), PVZBlocks.ORIGIN_ORE.get().defaultBlockState(), 3);
                    ev.setResult(Event.Result.DENY);
                }
            }
        }
    }

}
