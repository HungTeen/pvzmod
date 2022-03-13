package com.hungteen.pvz.common.event.handler;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.common.capability.CapabilityHandler;
import com.hungteen.pvz.common.event.PVZLivingEvents;
import com.hungteen.pvz.common.event.PVZPlayerEvents;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-10 17:32
 **/
public class PlayerEventHandler {

//    /**
//     * run when player right click plantEntity with shovel.
//     * {@link PVZPlayerEvents#onPlayerInteract(PlayerInteractEvent.EntityInteractSpecific)}
//     */
//    public static void quickRemoveByPlayer(PlayerEntity player, Entity entity, ItemStack stack) {
//        if(! PlayerUtil.isPlayerSurvival(player) || ((entity instanceof AbstractPAZEntity) && player.getUUID().equals(((AbstractPAZEntity) entity).getOwnerUUID().get()))){
//            boolean removed = false;
//            if(entity instanceof PVZPlantEntity && stack.getItem() instanceof ShovelItem) {
//                final PVZPlantEntity plantEntity = (PVZPlantEntity) entity;
//                if (plantEntity.getOuterPlantInfo().isPresent()) {//has outer plant, shovel outer plant.
//                    SunEntity.spawnSunsByAmount(player.level, plantEntity.blockPosition(), EnchantmentUtil.getSunShovelAmount(stack, plantEntity.getOuterPlantInfo().get().getSunCost()));
//                    plantEntity.removeOuterPlant();
//                } else if (plantEntity.getPlantInfo().isPresent()) {
//                    SunEntity.spawnSunsByAmount(player.level, plantEntity.blockPosition(), EnchantmentUtil.getSunShovelAmount(stack, plantEntity.getPlantInfo().get().getSunCost()));
//                    plantEntity.remove();
//                }
//                removed = ! (stack.getItem() instanceof OriginShovelItem);
//                EntityUtil.playSound(plantEntity, SoundRegister.PLACE_PLANT_GROUND.get());
//
//            } else if(entity instanceof PVZZombieEntity) {
//                //TODO fast remove zombie.
//            }
//            if(removed && PlayerUtil.isPlayerSurvival(player)){
//                stack.hurtAndBreak(3, player, (p) -> {
//                    p.broadcastBreakEvent(Hand.MAIN_HAND);
//                });
//            }
//        }
//    }

//    /**
//     * {@link PVZPlayerEvents#onPlayerInteract(PlayerInteractEvent.EntityInteractSpecific)}
//     */
//    public static void makeSuperMode(Player player, Entity entity, ItemStack heldStack) {
//        if (entity instanceof PVZPlantEntity && EntityUtil.isEntityValid(entity)) {//target must still alive.
//            //origin tools or item enchanted with [Energy Transfer] can make this.
//            if (heldStack.getItem().equals(ItemRegister.ORIGIN_SWORD.get()) || EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegister.ENERGY_TRANSFER.get(), heldStack) > 0) {
//                //this plant can be super and player has enough energy.
//                if (((PVZPlantEntity) entity).canStartSuperMode() && (! PlayerUtil.isPlayerSurvival(player) || PlayerUtil.getResource(player, Resources.ENERGY_NUM) > 0)) {
//                    if(PlayerUtil.isPlayerSurvival(player)) {
//                        PlayerUtil.addResource(player, Resources.ENERGY_NUM, -1);
//                    }
//                    ((PVZPlantEntity) entity).startSuperMode(true);
//                    //player gain plant food effect.
//                    final int treeLevel = PlayerUtil.getResource(player, Resources.TREE_LVL);
//                    player.addEffect(new EffectInstance(EffectRegister.ENERGETIC_EFFECT.get(), 100 + (treeLevel + 1) / 2, 0));
//                }
//            }
//        }
//    }

    /**
     * server side only.
     * {@link PVZLivingEvents#onLivingDeath(LivingDeathEvent)}
     */
    public static void onPlayerKillEntity(Player player, DamageSource source, LivingEntity living) {
//        if (living instanceof AbstractPAZEntity) {
//            if (EntityUtil.isEnemy(player, living)) {
//                PlayerUtil.addResource(player, Resources.TREE_XP, ((AbstractPAZEntity) living).getPAZType().getXpPoint());
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
//            l.lastVersion = PVZMod.MOD_VERSION;
        });

    }

    /**
     * save card cd.
     * {@link PVZPlayerEvents#onPlayerLogout(net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedOutEvent)}
     */
    public static void onPlayerLogout(Player player) {
        //save all summon card cool down.
//        PlayerUtil.getOptManager(player).ifPresent(l -> {
//            PVZAPI.get().getPAZs().forEach(p -> {
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
//        PVZAPI.get().getPAZs().forEach(type -> {
//            if (type.getRequiredLevel() <= level) {
//                PlayerUtil.getOptManager(player).ifPresent(m -> {
//                    m.setPAZLocked(type, false);
//                });
//            }
//        });
//    }

}
