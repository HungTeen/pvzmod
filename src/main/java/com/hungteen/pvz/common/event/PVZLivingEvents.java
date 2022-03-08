package com.hungteen.pvz.common.event;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.entity.AbstractPAZEntity;
import com.hungteen.pvz.common.entity.plant.magic.StrangeCatEntity;
import com.hungteen.pvz.common.event.handler.LivingEventHandler;
import com.hungteen.pvz.common.event.handler.PlayerEventHandler;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PVZMod.MOD_ID)
public class PVZLivingEvents {

	@SubscribeEvent
	public static void onLivingDeath(LivingDeathEvent ev) {
		/* handle player or its creature kill entity */
		if(! ev.getEntity().level.isClientSide) {
			Player player = EntityUtil.getEntityOwner(ev.getEntityLiving().level, ev.getSource().getEntity());
			if(player == null) { //true source has no owner
				if(ev.getSource().getEntity() instanceof Player) {
					PlayerEventHandler.onPlayerKillEntity((Player) ev.getSource().getEntity(), ev.getSource(), ev.getEntityLiving());
				}
			} else {
				PlayerEventHandler.onPlayerKillEntity(player, ev.getSource(), ev.getEntityLiving());
				CriteriaTriggers.PLAYER_KILLED_ENTITY.trigger((ServerPlayer) player, ev.getEntityLiving(), ev.getSource());
			}
		}
		
		/* handle player death */
		if(ev.getEntity() instanceof Player) {
		    PlayerEventHandler.handlePlayerDeath(ev, (Player) ev.getEntity());
		}
		
		/* strange cat copy */
		StrangeCatEntity.handleCopyCat(ev);
	}
	
	@SubscribeEvent
	public static void onLivingHurt(LivingHurtEvent ev) {
		if(! ev.getEntityLiving().level.isClientSide) {
			AbstractPAZEntity.damageOuterDefence(ev);
			if(ev.getSource() instanceof PVZEntityDamageSource) {
				ev.getEntityLiving().invulnerableTime = 0;
				LivingEventHandler.handleHurtEffects(ev.getEntityLiving(), (PVZEntityDamageSource) ev.getSource());
				LivingEventHandler.handleHurtSounds(ev.getEntityLiving(), (PVZEntityDamageSource) ev.getSource());
			}
			LivingEventHandler.handleHurtDamage(ev);
		}
	}
	
	@SubscribeEvent
	public static void onLivingDamage(LivingDamageEvent ev) {
		AbstractPAZEntity.damageInnerDefence(ev);
	}
	
}
