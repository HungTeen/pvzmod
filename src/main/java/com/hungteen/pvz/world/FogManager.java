package com.hungteen.pvz.world;

import com.hungteen.pvz.capability.CapabilityHandler;
import com.hungteen.pvz.register.EffectRegister;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Events;
import com.hungteen.pvz.utils.enums.Resources;
import com.hungteen.pvz.world.data.WorldEventData;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Pose;
import net.minecraft.world.World;

public class FogManager {

	public static void tickFog(World world) {
		world.getPlayers().forEach((player)->{
			player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
				int now = l.getPlayerData().getPlayerStats().getPlayerStats(Resources.NO_FOG_TICK);
				if(shouldFogOn(world)) {
					++ now;
				} else {
					now = Math.min(now, 0);
				}
				l.getPlayerData().getPlayerStats().setPlayerStats(Resources.NO_FOG_TICK, now);
			});
		});
	}
	
	private static boolean shouldFogOn(World world) {
		Minecraft mc = Minecraft.getInstance();
		return ! world.isDaytime() && WorldEventData.getOverWorldEventData(world).hasEvent(Events.FOG) && (mc.player != null && PlayerUtil.isPlayerSurvival(mc.player) && mc.player.getSubmergedHeight() < 1.2f 
				&& mc.player.getPose() != Pose.SWIMMING && ! mc.player.isPotionActive(EffectRegister.LIGHT_EYE_EFFECT.get()));
	}
}
