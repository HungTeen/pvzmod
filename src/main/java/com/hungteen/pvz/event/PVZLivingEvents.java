package com.hungteen.pvz.event;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.capability.CapabilityHandler;
import com.hungteen.pvz.entity.plant.magic.StrangeCatEntity;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.event.handler.LivingEventHandler;
import com.hungteen.pvz.event.handler.PlayerEventHandler;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.EnchantmentRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=PVZMod.MOD_ID)
public class PVZLivingEvents {

	@SubscribeEvent
	public static void onLivingDeath(LivingDeathEvent ev) {
		if(! ev.getEntity().world.isRemote) {
			PlayerEntity player = EntityUtil.getEntityOwner(ev.getEntityLiving().world, ev.getSource().getTrueSource());
			if(player == null) { //true source has no owner
				if(ev.getSource().getTrueSource() instanceof PlayerEntity) {
					PlayerEventHandler.onPlayerKillEntity((PlayerEntity) ev.getSource().getTrueSource(), ev.getSource(), ev.getEntityLiving());
				}
			} else {
				PlayerEventHandler.onPlayerKillEntity(player, ev.getSource(), ev.getEntityLiving());
			}
		}
		if(! ev.getEntity().world.isRemote && ev.getEntityLiving() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) ev.getEntityLiving();
			player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l) -> {
				l.getPlayerData().getPlayerStats().setPlayerStats(Resources.NO_FOG_TICK, 0);
				l.getPlayerData().getPlayerStats().setPlayerStats(Resources.KILL_COUNT, 0);
			});
		}
		if(! ev.getEntity().world.isRemote && ev.getSource() instanceof PVZDamageSource) {
			PVZDamageSource source = (PVZDamageSource) ev.getSource();
			if(source.isCopyDamage()) {
				if(source.getTrueSource() instanceof StrangeCatEntity) {
					((StrangeCatEntity) source.getTrueSource()).onSelfCopy(ev.getEntityLiving());
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void onLivingHurt(LivingHurtEvent ev) {
		if(! ev.getEntityLiving().world.isRemote) {
			ev.getEntityLiving().getArmorInventoryList().forEach((stack) -> {
				if(EnchantmentHelper.getEnchantmentLevel(EnchantmentRegister.TREE_PROTECTION.get(), stack) > 0){
					float amount = ev.getAmount();
					amount = Math.min(amount, ev.getEntityLiving().getMaxHealth());
					ev.setAmount(amount);
					return ;
				}
		    });
			if(ev.getSource() instanceof PVZDamageSource) {
				ev.getEntityLiving().hurtResistantTime = 0;
				LivingEventHandler.handleHurtEffects(ev.getEntityLiving(), (PVZDamageSource) ev.getSource());
			}
		}
	}
	
	@SubscribeEvent
	public static void onLivingDamage(LivingDamageEvent ev) {
		float amount = ev.getAmount();
		if(ev.getEntityLiving() instanceof PVZZombieEntity) {// zombie defence hit
			PVZZombieEntity zombie = (PVZZombieEntity) ev.getEntityLiving();
			if(zombie.hasDefence() && zombie.getDefenceLife() > 0) {
				if(zombie.getDefenceLife() > amount) {
				    zombie.setDefenceLife(zombie.getDefenceLife() - amount);
				    amount = 0;
				} else {
			        amount -= zombie.getDefenceLife();
					zombie.setDefenceLife(0);
				}
				if(amount == 0) amount = 0.00001F;
			}
		}
		ev.setAmount(amount);
	}
	
}
