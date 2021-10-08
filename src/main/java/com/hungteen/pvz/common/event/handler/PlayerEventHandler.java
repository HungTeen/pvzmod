package com.hungteen.pvz.common.event.handler;

import java.util.Optional;

import com.hungteen.pvz.common.capability.CapabilityHandler;
import com.hungteen.pvz.common.capability.player.IPlayerDataCapability;
import com.hungteen.pvz.common.capability.player.PlayerDataManager;
import com.hungteen.pvz.common.core.PlantType;
import com.hungteen.pvz.common.core.ZombieType;
import com.hungteen.pvz.common.enchantment.EnchantmentUtil;
import com.hungteen.pvz.common.entity.drop.SunEntity;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.event.PVZLivingEvents;
import com.hungteen.pvz.common.event.PVZPlayerEvents;
import com.hungteen.pvz.common.world.data.PVZInvasionData;
import com.hungteen.pvz.common.world.invasion.WaveManager;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class PlayerEventHandler {
	
	/**
	 * run when player right click plantEntity with shovel.
	 */
	public static void onPlantShovelByPlayer(PlayerEntity player, PVZPlantEntity plant, ItemStack stack) {
		if(player.abilities.instabuild || player.getUUID().equals(plant.getOwnerUUID().get()) || ! EntityUtil.checkCanEntityBeAttack(plant, player)) {
			if(plant.getOuterPlantInfo().isPresent()) {//has outer plant, shovel outer plant.
				SunEntity.spawnSunsByAmount(player.level, plant.blockPosition(), EnchantmentUtil.getSunShovelAmount(stack, plant.getOuterPlantInfo().get().getSunCost()));
				plant.removeOuterPlant();
			} else if(plant.getPlantInfo().isPresent()){
				SunEntity.spawnSunsByAmount(player.level, plant.blockPosition(), EnchantmentUtil.getSunShovelAmount(stack, plant.getPlantInfo().get().getSunCost()));
				plant.remove();
			}
			EntityUtil.playSound(plant, SoundRegister.PLANT_ON_GROUND.get());
			stack.hurtAndBreak(3, player, (p) -> {p.broadcastBreakEvent(Hand.MAIN_HAND);});
		}
	}
	
	public static void onPlayerKillEntity(PlayerEntity player, DamageSource source, LivingEntity living) {
		if(living instanceof PVZZombieEntity) {
			PVZZombieEntity zombie = (PVZZombieEntity) living;
			PlayerUtil.addResource(player, Resources.TREE_XP, zombie.getZombieType().getXp());
			onPlayerKillZombie(player, zombie.getZombieType());
		}
		CriteriaTriggers.PLAYER_KILLED_ENTITY.trigger((ServerPlayerEntity) player, living, source);
	}
	
	public static void onPlayerKillZombie(PlayerEntity player, ZombieType zombie) {
		PlayerUtil.addResource(player, Resources.KILL_COUNT, 1);
	}
	
	/**
	 * send packet from server to client to sync player's data.
	 * {@link PVZPlayerEvents#onPlayerLogin(net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent)}
	 */
	public static void onPlayerLogin(PlayerEntity player) {
		Optional.ofNullable(PlayerUtil.getManager(player)).ifPresent(l -> l.init());
		WaveManager.syncWaveTime(player);
	}
	
	/**
	 * save card cd.
	 * {@link PVZPlayerEvents#onPlayerLogout(net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedOutEvent)}
	 */
	public static void onPlayerLogout(PlayerEntity player) {
		player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent(l -> {
			PlayerDataManager plData = l.getPlayerData();
			for(PlantType p : PlantType.getPlants()) {
				p.getSummonCard().ifPresent(card -> {
					plData.setPlantCardBar(p, player.getCooldowns().getCooldownPercent(card, 0f));
				});
			}
		});
	}
	
	/**
	 * {@link PVZLivingEvents#onLivingDeath(LivingDeathEvent)}
	 */
	public static void handlePlayerDeath(LivingDeathEvent ev, PlayerEntity player) {
		if(player != null && !player.level.isClientSide && PlayerUtil.isValidPlayer(player)) {
			/* spawn sun around*/
			spawnSunAroundPlayer(player);
			
			/* decrease difficulty */
			if(ev.getSource().getEntity() instanceof PVZZombieEntity) {//player killed by zombie.
				PVZInvasionData data = PVZInvasionData.getOverWorldInvasionData(ev.getEntity().level);
			    data.addCurrentDifficulty(- 1);//decrease difficulty.
			}
		}
	}
	
	/**
	 * {@link PVZPlayerEvents#onPlayerClone(net.minecraftforge.event.entity.player.PlayerEvent.Clone)}
	 */
	public static void clonePlayerData(PlayerEntity oldPlayer, PlayerEntity newPlayer, boolean died) {
	    LazyOptional<IPlayerDataCapability> oldCap = oldPlayer.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY);
	    LazyOptional<IPlayerDataCapability> newCap = newPlayer.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY);
	    if(oldCap.isPresent() && newCap.isPresent()) {
		    newCap.ifPresent(l -> {
			    oldCap.ifPresent(r -> {
				    l.getPlayerData().cloneFromExistingPlayerData(r.getPlayerData(), died);
			    });
		    });
	    }
	}
	
	/**
	 * {@link #handlePlayerDeath(LivingDeathEvent, PlayerEntity)}
	 */
	private static void spawnSunAroundPlayer(PlayerEntity player) {
		final int amount = PlayerUtil.getResource(player, Resources.SUN_NUM);
		final int spawn = amount > 50 ? MathHelper.clamp((amount - 50) / 10, 25, 250) : 0;
		if(amount > 15) {
			SunEntity.spawnSunsByAmount(player.level, player.blockPosition(), spawn, 50, 3);
		}
	}
	
}
