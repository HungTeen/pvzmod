package com.hungteen.pvz.common.event;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.interfaces.ICanBeEnergetic;
import com.hungteen.pvz.api.interfaces.IQuickRemoveEntity;
import com.hungteen.pvz.common.effect.PVZEffects;
import com.hungteen.pvz.common.enchantment.PVZEnchantments;
import com.hungteen.pvz.common.event.handler.PlayerEventHandler;
import com.hungteen.pvz.common.item.PVZItems;
import com.hungteen.pvz.common.item.weapon.PeaGunItem;
import com.hungteen.pvz.utils.EffectUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-10 17:30
 **/
@Mod.EventBusSubscriber(modid = PVZMod.MOD_ID)
public class PVZPlayerEvents {

    @SubscribeEvent
    public static void tickPlayer(TickEvent.PlayerTickEvent ev) {
        if(! ev.player.level.isClientSide) {
            if (ev.player.tickCount < 2) {
                PlayerUtil.getOptManager(ev.player).ifPresent(l -> l.loadSummonCardCDs());
            }
            PeaGunItem.checkHeadShoot(ev.player);
//            ev.player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l) -> {
//                if (l.getPlayerData().getOtherStats().playSoundTick > 0) {
//                    --l.getPlayerData().getOtherStats().playSoundTick;
//                }
//            });
        }
//        PVZMod.PROXY.climbUp();
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent ev) {
        if (! ev.getPlayer().level.isClientSide) {
            PlayerEventHandler.onPlayerLogin(ev.getPlayer());

//            InvasionManager.addPlayer(ev.getPlayer());
//
//            PlayerEventHandler.unLockPAZs(ev.getPlayer());
//
//            //sync to client data pack.
//            PVZDataPackManager.sendSyncPacketsTo(ev.getPlayer());
        }
    }

    @SubscribeEvent
    public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent ev) {
        if (! ev.getPlayer().level.isClientSide) {
            PlayerEventHandler.onPlayerLogout(ev.getPlayer());

//            InvasionManager.removePlayer(ev.getPlayer());
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone ev) {
        PlayerEventHandler.clonePlayerData(ev.getOriginal(), ev.getPlayer(), ev.isWasDeath());
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent ev) {
        if(! ev.getPlayer().level.isClientSide) {
            PlayerUtil.getOptManager(ev.getPlayer()).ifPresent(l -> l.syncToClient());
        }
    }

    @SubscribeEvent
    public static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent ev) {
        if(! ev.getPlayer().level.isClientSide) {
            PlayerUtil.getOptManager(ev.getPlayer()).ifPresent(l -> l.syncToClient());
        }
    }

    @SubscribeEvent
    public static void onPlayerInteractSpec(PlayerInteractEvent.EntityInteractSpecific ev) {
        if(! ev.getWorld().isClientSide && ! ev.getPlayer().getCooldowns().isOnCooldown(ev.getItemStack().getItem())){
            //quick removed by player.
            if(ev.getTarget() instanceof IQuickRemoveEntity && ((IQuickRemoveEntity) ev.getTarget()).isCorrectItem(ev.getItemStack())) {
                ((IQuickRemoveEntity) ev.getTarget()).onQuickRemove(ev.getPlayer(), ev.getItemStack());
                if(PlayerUtil.isPlayerSurvival(ev.getPlayer())){
                    ev.getItemStack().hurtAndBreak(((IQuickRemoveEntity) ev.getTarget()).getQuickRemoveDamage(ev.getItemStack()), ev.getPlayer(), (p) -> {
                        p.broadcastBreakEvent(ev.getHand());
                    });
                }
                ev.getPlayer().getCooldowns().addCooldown(ev.getItemStack().getItem(), 10);
                return;
            }
            // use plant food.
            if (ev.getItemStack().getItem().equals(PVZItems.ORIGIN_SWORD.get()) || EnchantmentHelper.getItemEnchantmentLevel(PVZEnchantments.ENERGY_RELEASE.get(), ev.getItemStack()) > 0) {
                if (ev.getTarget() instanceof ICanBeEnergetic && ((ICanBeEnergetic) ev.getTarget()).canBeEnergetic() && (!PlayerUtil.isPlayerSurvival(ev.getPlayer()) || PlayerUtil.getResource(ev.getPlayer(), Resources.ENERGY_NUM) > 0)) {
                    if (PlayerUtil.isPlayerSurvival(ev.getPlayer())) {
                        PlayerUtil.addResource(ev.getPlayer(), Resources.ENERGY_NUM, -1);
                    }
                    ((ICanBeEnergetic) ev.getTarget()).onEnergetic(true);
                    if (ev.getTarget() instanceof LivingEntity) {
                        ((LivingEntity) ev.getTarget()).addEffect(EffectUtil.viewEffect(PVZEffects.ENERGETIC_EFFECT.get(), ((ICanBeEnergetic) ev.getTarget()).getEnergeticDuration(), 0));
                    }
                    ev.getPlayer().addEffect(EffectUtil.viewEffect(PVZEffects.ENERGETIC_EFFECT.get(), 100, 0));
                }
            }
        }
//        BowlingGloveItem.onPickUp(ev);
    }
//
//    @SubscribeEvent(priority = EventPriority.HIGHEST)
//    public static void banBucket(PlayerInteractEvent.EntityInteractSpecific ev) {
//        if(! CompatUtil.canBucketEntity(ev.getPlayer().level, ev.getTarget(), ev.getItemStack())){
//            ev.setCanceled(true);
//        }
//    }
//
//    @SubscribeEvent
//    public static void onPlayerTreeLevelUp(PlayerLevelChangeEvent ev) {
//        if (!ev.getPlayer().level.isClientSide && ev.isLevelUp()) {
//            PlayerEventHandler.unLockPAZs(ev.getPlayer());
//            PlayerUtil.playClientSound(ev.getPlayer(), SoundRegister.PLANT_GROW.get());
//            PlayerUtil.addResource(ev.getPlayer(), Resources.LOTTERY_CHANCE, 3);
//        }
//    }

}
