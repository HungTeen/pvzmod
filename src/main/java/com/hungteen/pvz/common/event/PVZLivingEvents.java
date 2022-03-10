package com.hungteen.pvz.common.event;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.event.handler.PlayerEventHandler;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-10 17:33
 **/
@Mod.EventBusSubscriber(modid = PVZMod.MOD_ID)
public class PVZLivingEvents {

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent ev) {
        /* handle player or its creature kill entity */
//        if(! ev.getEntity().level.isClientSide) {
//            Player player = EntityUtil.getEntityOwner(ev.getEntityLiving().level, ev.getSource().getEntity());
//            if(player == null) { //true source has no owner
//                if(ev.getSource().getEntity() instanceof Player) {
//                    PlayerEventHandler.onPlayerKillEntity((Player) ev.getSource().getEntity(), ev.getSource(), ev.getEntityLiving());
//                }
//            } else {
//                PlayerEventHandler.onPlayerKillEntity(player, ev.getSource(), ev.getEntityLiving());
//                CriteriaTriggers.PLAYER_KILLED_ENTITY.trigger((ServerPlayer) player, ev.getEntityLiving(), ev.getSource());
//            }
//        }

        /* handle player death */
        if(ev.getEntity() instanceof Player) {
            PlayerEventHandler.handlePlayerDeath(ev, (Player) ev.getEntity());
        }

//        /* strange cat copy */
//        StrangeCatEntity.handleCopyCat(ev);
    }

}
