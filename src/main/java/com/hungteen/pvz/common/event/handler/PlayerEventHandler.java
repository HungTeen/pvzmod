package com.hungteen.pvz.common.event.handler;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.event.PVZLivingEvents;
import com.hungteen.pvz.common.event.PVZPlayerEvents;
import com.hungteen.pvz.utils.PlayerUtil;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-10 17:32
 **/
public class PlayerEventHandler {

    /**
     * server side only.
     * {@link PVZLivingEvents#onLivingDeath(LivingDeathEvent)}
     */
    public static void onPlayerKillEntity(Player player, DamageSource source, LivingEntity living) {
//        if (living instanceof AbstractPAZEntity) {
//            if (EntityUtil.isEnemy(player, living)) {
//                PlayerUtil.addResource(player, Resources.TREE_XP, ((AbstractPAZEntity) living).getOwnerType().getXpPoint());
//            }
//        }
//        if (PlayerUtil.getInvasion(player).isInvasionEntity(living.getType()) && EntityUtil.isEnemy(player, living)) {
//            if (MissionManager.getPlayerMission(player) == MissionManager.MissionType.KILL || MissionManager.getPlayerMission(player) == MissionManager.MissionType.INSTANT_KILL) {
//                PlayerUtil.addResource(player, Resources.MISSION_VALUE, 1);
//            }
//        }
    }

    /**
     * send packet from server to client to sync player's data.
     * {@link PVZPlayerEvents#onPlayerLogin(net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent)}
     */
    public static void onPlayerLogin(Player player) {
        PlayerUtil.getOptManager(player).ifPresent(l -> {
            l.init();

//            if (l.lastVersion.equals(Util.INIT_VERSION)) {//first join world.
//                // allow to get beginner reward.
//                if (PVZConfig.COMMON_CONFIG.RuleSettings.GiveBeginnerReward.get()) {
//                    player.addItem(new ItemStack(ItemRegister.PEA_SHOOTER_CARD.get()));
//                    player.addItem(new ItemStack(ItemRegister.SUN_FLOWER_CARD.get()));
//                    player.addItem(new ItemStack(ItemRegister.WALL_NUT_CARD.get()));
//                    player.addItem(new ItemStack(ItemRegister.POTATO_MINE_CARD.get()));
//                }
//                // give patchouli guide book to new join player.
//                PVZPatchouliHandler.giveInitialGuideBook(player);
//                // give challenge envelope to player.
//                player.addItem(ChallengeEnvelopeItem.getChallengeEnvelope(StringUtil.prefix("strange_help")));
//            } else if (!l.lastVersion.equals(PVZMod.MOD_VERSION)) {//version changed.
//
//            }
//
            l.lastVersion = PVZMod.MOD_VERSION;
        });

    }

    /**
     * save card cd.
     * {@link PVZPlayerEvents#onPlayerLogout(net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedOutEvent)}
     */
    public static void onPlayerLogout(Player player) {
        //save all summon card cool down.
//        PlayerUtil.getOptManager(player).ifPresent(l -> {
//            PVZAPI.get().getPAZTypes().forEach(p -> {
//                p.getSummonCard().ifPresent(card -> {
//                    l.setPAZCardBar(p, player.getCooldowns().getCooldownPercent(card, 0f));
//                });
//            });
//        });
    }

    /**
     * {@link PVZLivingEvents#onLivingDeath(LivingDeathEvent)}
     */
    public static void handlePlayerDeath(LivingDeathEvent ev, Player player) {
        if (player != null && !player.level.isClientSide && PlayerUtil.isValidPlayer(player)) {
            /* spawn sun around*/
//            spawnSunAroundPlayer(player);
        }
    }

    /**
     * {@link PVZPlayerEvents#onPlayerClone(net.minecraftforge.event.entity.player.PlayerEvent.Clone)}
     */
    public static void clonePlayerData(Player oldPlayer, Player newPlayer, boolean died) {
        PlayerUtil.getOptManager(oldPlayer).ifPresent(oldOne -> {
            PlayerUtil.getOptManager(newPlayer).ifPresent(newOne -> {
                newOne.cloneFromExistingPlayerData(oldOne, died);
            });
        });

//        InvasionManager.removePlayer(oldPlayer);
//        InvasionManager.addPlayer(newPlayer);
    }

//    /**
//     * {@link #handlePlayerDeath(LivingDeathEvent, PlayerEntity)}
//     */
//    private static void spawnSunAroundPlayer(PlayerEntity player) {
//        final int amount = PlayerUtil.getResource(player, Resources.SUN_NUM);
//        final int spawn = amount > 50 ? MathHelper.clamp((amount - 50) / 10, 25, 250) : 0;
//        if (amount > 15) {
//            SunEntity.spawnSunsByAmount(player.level, player.blockPosition(), spawn, 50, 3);
//        }
//    }

//    /**
//     * when tree level is enough, unlock some plants & zombies.
//     */
//    public static void unLockPAZs(PlayerEntity player) {
//        final int level = PlayerUtil.getResource(player, Resources.TREE_LVL);
//        PVZAPI.get().getPAZTypes().forEach(type -> {
//            if (type.getRequiredLevel() <= level) {
//                PlayerUtil.getOptManager(player).ifPresent(m -> {
//                    m.setPAZLocked(type, false);
//                });
//            }
//        });
//    }

}
