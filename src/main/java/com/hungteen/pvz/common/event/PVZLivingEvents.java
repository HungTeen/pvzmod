package com.hungteen.pvz.common.event;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.capability.CapabilityHandler;
import com.hungteen.pvz.common.enchantment.armor.TreeProtectionEnchantment;
import com.hungteen.pvz.common.entity.plant.magic.StrangeCatEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.event.handler.LivingEventHandler;
import com.hungteen.pvz.common.event.handler.PlayerEventHandler;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.common.world.data.PVZInvasionData;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PVZMod.MOD_ID)
public class PVZLivingEvents {

	@SubscribeEvent
	public static void onLivingDeath(LivingDeathEvent ev) {
		if(! ev.getEntity().level.isClientSide) {
			PlayerEntity player = EntityUtil.getEntityOwner(ev.getEntityLiving().level, ev.getSource().getEntity());
			if(player == null) { //true source has no owner
				if(ev.getSource().getEntity() instanceof PlayerEntity) {
					PlayerEventHandler.onPlayerKillEntity((PlayerEntity) ev.getSource().getEntity(), ev.getSource(), ev.getEntityLiving());
				}
			} else {
				PlayerEventHandler.onPlayerKillEntity(player, ev.getSource(), ev.getEntityLiving());
			}
		}
		//when player death
		if(! ev.getEntity().level.isClientSide && ev.getEntityLiving() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) ev.getEntityLiving();
			if(PlayerUtil.isValidPlayer(player)) {
				player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l) -> {
				    l.getPlayerData().setResource(Resources.NO_FOG_TICK, 0);
				    l.getPlayerData().setResource(Resources.KILL_COUNT, 0);
			    });
				if(ev.getSource().getEntity() instanceof PVZZombieEntity) {//player killed by zombie.
					PVZInvasionData data = PVZInvasionData.getOverWorldInvasionData(ev.getEntity().level);
				    data.addCurrentDifficulty(- 1);//decrease difficulty.
				}
			}
		}
		StrangeCatEntity.handleCopyCat(ev);
	}
	
	@SubscribeEvent
	public static void onLivingHurt(LivingHurtEvent ev) {
		if(! ev.getEntityLiving().level.isClientSide) {
			TreeProtectionEnchantment.handleTreeProtection(ev);
			if(ev.getSource() instanceof PVZDamageSource) {
				ev.getEntityLiving().invulnerableTime = 0;
				LivingEventHandler.handleHurtEffects(ev.getEntityLiving(), (PVZDamageSource) ev.getSource());
				LivingEventHandler.handleHurtSounds(ev.getEntityLiving(), (PVZDamageSource) ev.getSource());
			}
			LivingEventHandler.handleHurtDamage(ev);
		}
	}
	
	@SubscribeEvent
	public static void onLivingDamage(LivingDamageEvent ev) {
		PVZZombieEntity.damageZombieDefence(ev);
	}
	
}
