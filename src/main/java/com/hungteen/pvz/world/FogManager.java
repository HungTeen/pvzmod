package com.hungteen.pvz.world;

import com.hungteen.pvz.capability.CapabilityHandler;
import com.hungteen.pvz.register.EffectRegister;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Events;
import com.hungteen.pvz.utils.enums.Resources;
import com.hungteen.pvz.world.data.WorldEventData;

import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class FogManager {

	public static void tickFog(World world) {
		world.players().forEach((player) -> {
			player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l) -> {
				int now = l.getPlayerData().getPlayerStats().getPlayerStats(Resources.NO_FOG_TICK);
				-- now;
				if(! shouldFogOn(world, player)) {
					now = Math.max(now, 0);
				}
				now = Math.max(now, - 100);
//				System.out.println(now);
				l.getPlayerData().getPlayerStats().setPlayerStats(Resources.NO_FOG_TICK, now);
			});
		});
	}
	
	private static boolean shouldFogOn(World world, PlayerEntity player) {
		return world.dimension().equals(World.OVERWORLD) && ! world.isDay() && WorldEventData.getOverWorldEventData(world).hasEvent(Events.FOG) && (player != null && PlayerUtil.isPlayerSurvival(player) && player.isUnderWater() 
				&& player.getPose() != Pose.SWIMMING && ! player.hasEffect(EffectRegister.LIGHT_EYE_EFFECT.get()));
	}
	
}
