package com.hungteen.pvz.common.event.handler;

import com.hungteen.pvz.common.capability.CapabilityHandler;
import com.hungteen.pvz.common.core.ZombieType;
import com.hungteen.pvz.common.enchantment.EnchantmentUtil;
import com.hungteen.pvz.common.entity.drop.SunEntity;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
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
	 */
	public static void onPlayerLogin(PlayerEntity player) {
		player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent(l -> {
			l.getPlayerData().syncToClient();
		});
		WaveManager.syncWaveTime(player);
	}
	
}
