package com.hungteen.pvz.common.event.handler;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.common.capability.CapabilityHandler;
import com.hungteen.pvz.common.capability.player.PlayerDataManager;
import com.hungteen.pvz.common.enchantment.EnchantmentRegister;
import com.hungteen.pvz.common.enchantment.EnchantmentUtil;
import com.hungteen.pvz.common.entity.AbstractPAZEntity;
import com.hungteen.pvz.common.entity.misc.drop.SunEntity;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.event.PVZLivingEvents;
import com.hungteen.pvz.common.event.PVZPlayerEvents;
import com.hungteen.pvz.common.item.ItemRegister;
import com.hungteen.pvz.common.item.tool.mc.OriginShovelItem;
import com.hungteen.pvz.common.potion.EffectRegister;
import com.hungteen.pvz.common.world.invasion.InvasionManager;
import com.hungteen.pvz.common.world.invasion.MissionManager;
import com.hungteen.pvz.compat.patchouli.PVZPatchouliHandler;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.enums.Resources;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import java.util.Optional;

public class PlayerEventHandler {

    /**
     * when tree level is enough, unlock some plants & zombies.
     */
    public static void unLockPAZs(PlayerEntity player) {
        final int level = PlayerUtil.getResource(player, Resources.TREE_LVL);
        PVZAPI.get().getPAZs().forEach(type -> {
            if (type.getRequiredLevel() <= level) {
                PlayerUtil.getOptManager(player).ifPresent(m -> {
                    m.setPAZLocked(type, false);
                });
            }
        });
    }

    /**
     * run when player right click plantEntity with shovel.
     * {@link PVZPlayerEvents#onPlayerInteract(PlayerInteractEvent.EntityInteractSpecific)}
     */
    public static void quickRemoveByPlayer(PlayerEntity player, Entity entity, ItemStack stack) {
        if(! PlayerUtil.isPlayerSurvival(player) || ((entity instanceof AbstractPAZEntity) && player.getUUID().equals(((AbstractPAZEntity) entity).getOwnerUUID().get()))){
            boolean removed = false;
            if(entity instanceof PVZPlantEntity && stack.getItem() instanceof ShovelItem) {
                final PVZPlantEntity plantEntity = (PVZPlantEntity) entity;
                if (plantEntity.getOuterPlantInfo().isPresent()) {//has outer plant, shovel outer plant.
                    SunEntity.spawnSunsByAmount(player.level, plantEntity.blockPosition(), EnchantmentUtil.getSunShovelAmount(stack, plantEntity.getOuterPlantInfo().get().getSunCost()));
                    plantEntity.removeOuterPlant();
                } else if (plantEntity.getPlantInfo().isPresent()) {
                    SunEntity.spawnSunsByAmount(player.level, plantEntity.blockPosition(), EnchantmentUtil.getSunShovelAmount(stack, plantEntity.getPlantInfo().get().getSunCost()));
                    plantEntity.remove();
                }
                removed = ! (stack.getItem() instanceof OriginShovelItem);
                EntityUtil.playSound(plantEntity, SoundRegister.PLACE_PLANT_GROUND.get());

            } else if(entity instanceof PVZZombieEntity) {
                //TODO fast remove zombie.
            }
            if(removed && PlayerUtil.isPlayerSurvival(player)){
                stack.hurtAndBreak(3, player, (p) -> {
                    p.broadcastBreakEvent(Hand.MAIN_HAND);
                });
            }
        }
    }

    /**
     * {@link PVZPlayerEvents#onPlayerInteract(PlayerInteractEvent.EntityInteractSpecific)}
     */
    public static void makeSuperMode(PlayerEntity player, Entity entity, ItemStack heldStack) {
        if (entity instanceof PVZPlantEntity && EntityUtil.isEntityValid(entity)) {//target must still alive.
            //origin tools or item enchanted with [Energy Transfer] can make this.
            if (heldStack.getItem().equals(ItemRegister.ORIGIN_SWORD.get()) || EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegister.ENERGY_TRANSFER.get(), heldStack) > 0) {
                //this plant can be super and player has enough energy.
                if (((PVZPlantEntity) entity).canStartSuperMode() && (! PlayerUtil.isPlayerSurvival(player) || PlayerUtil.getResource(player, Resources.ENERGY_NUM) > 0)) {
                    if(PlayerUtil.isPlayerSurvival(player)) {
                        PlayerUtil.addResource(player, Resources.ENERGY_NUM, -1);
                    }
                    ((PVZPlantEntity) entity).startSuperMode(true);
                    //player gain plant food effect.
                    final int treeLevel = PlayerUtil.getResource(player, Resources.TREE_LVL);
                    player.addEffect(new EffectInstance(EffectRegister.ENERGETIC_EFFECT.get(), 100 + (treeLevel + 1) / 2, 0));
                }
            }
        }
    }

    /**
     * server side only.
     * {@link PVZLivingEvents#onLivingDeath(LivingDeathEvent)}
     */
    public static void onPlayerKillEntity(PlayerEntity player, DamageSource source, LivingEntity living) {
        if (living instanceof AbstractPAZEntity) {
            if (EntityUtil.isEnemy(player, living)) {
                PlayerUtil.addResource(player, Resources.TREE_XP, ((AbstractPAZEntity) living).getPAZType().getXpPoint());
            }
        }
        if (PlayerUtil.getInvasion(player).isInvasionEntity(living.getType()) && EntityUtil.isEnemy(player, living)) {
            if (MissionManager.getPlayerMission(player) == MissionManager.MissionType.KILL || MissionManager.getPlayerMission(player) == MissionManager.MissionType.INSTANT_KILL) {
                PlayerUtil.addResource(player, Resources.MISSION_VALUE, 1);
            }
        }
    }

    /**
     * send packet from server to client to sync player's data.
     * {@link PVZPlayerEvents#onPlayerLogin(net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent)}
     */
    public static void onPlayerLogin(PlayerEntity player) {
        Optional.ofNullable(PlayerUtil.getManager(player)).ifPresent(l -> {
            l.init();

            if (l.lastVersion.equals(StringUtil.INIT_VERSION)) {//first join world.
                // allow to get beginner reward.
                if (PVZConfig.COMMON_CONFIG.RuleSettings.GiveBeginnerReward.get()) {
                    player.addItem(new ItemStack(ItemRegister.PEA_SHOOTER_CARD.get()));
                    player.addItem(new ItemStack(ItemRegister.SUN_FLOWER_CARD.get()));
                    player.addItem(new ItemStack(ItemRegister.WALL_NUT_CARD.get()));
                    player.addItem(new ItemStack(ItemRegister.POTATO_MINE_CARD.get()));
                }
                // give patchouli guide book to new join player.
                PVZPatchouliHandler.giveInitialGuideBook(player);
            } else if (!l.lastVersion.equals(PVZMod.MOD_VERSION)) {//version changed.

            }

            l.lastVersion = PVZMod.MOD_VERSION;
        });

        InvasionManager.addPlayer(player);
        unLockPAZs(player);

//		WaveManager.syncWaveTime(player);
    }

    /**
     * save card cd.
     * {@link PVZPlayerEvents#onPlayerLogout(net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedOutEvent)}
     */
    public static void onPlayerLogout(PlayerEntity player) {
        player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent(l -> {
            PlayerDataManager plData = l.getPlayerData();
            PVZAPI.get().getPAZs().forEach(p -> {
                p.getSummonCard().ifPresent(card -> {
                    plData.setPAZCardBar(p, player.getCooldowns().getCooldownPercent(card, 0f));
                });
            });
            InvasionManager.removePlayer(player);
        });
    }

    /**
     * {@link PVZLivingEvents#onLivingDeath(LivingDeathEvent)}
     */
    public static void handlePlayerDeath(LivingDeathEvent ev, PlayerEntity player) {
        if (player != null && !player.level.isClientSide && PlayerUtil.isValidPlayer(player)) {
            /* spawn sun around*/
            spawnSunAroundPlayer(player);
        }
    }

    /**
     * {@link PVZPlayerEvents#onPlayerClone(net.minecraftforge.event.entity.player.PlayerEvent.Clone)}
     */
    public static void clonePlayerData(PlayerEntity oldPlayer, PlayerEntity newPlayer, boolean died) {
        oldPlayer.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent(l -> {
        	newPlayer.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent(r -> {
        		r.getPlayerData().cloneFromExistingPlayerData(l.getPlayerData(), died);
        	});
        });
    }

    /**
     * {@link #handlePlayerDeath(LivingDeathEvent, PlayerEntity)}
     */
    private static void spawnSunAroundPlayer(PlayerEntity player) {
        final int amount = PlayerUtil.getResource(player, Resources.SUN_NUM);
        final int spawn = amount > 50 ? MathHelper.clamp((amount - 50) / 10, 25, 250) : 0;
        if (amount > 15) {
            SunEntity.spawnSunsByAmount(player.level, player.blockPosition(), spawn, 50, 3);
        }
    }

}
