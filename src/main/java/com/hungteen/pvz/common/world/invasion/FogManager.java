package com.hungteen.pvz.common.world.invasion;

import com.hungteen.pvz.common.capability.CapabilityHandler;
import com.hungteen.pvz.common.impl.InvasionEvents;
import com.hungteen.pvz.common.world.data.PVZInvasionData;
import com.hungteen.pvz.register.EffectRegister;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class FogManager {

	public static final int CD = 100;
	
	public static void tickFog(World world) {
		world.players().forEach((player) -> {
			player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l) -> {
				int now = l.getPlayerData().getResource(Resources.NO_FOG_TICK);
				int newTick = (shouldFogOn(world, player)) ? MathHelper.clamp(now - 1, - CD, 0) : MathHelper.clamp(now + 1, - CD, 0);
				if(newTick != now) {
					l.getPlayerData().setResource(Resources.NO_FOG_TICK, newTick);
				}
			});
		});
	}
	
	private static boolean shouldFogOn(World world, PlayerEntity player) {
		return world.dimension().equals(World.OVERWORLD) && ! world.isDay() && ! world.isRainingAt(player.blockPosition()) && PVZInvasionData.getOverWorldInvasionData(world).hasEvent(InvasionEvents.FOG) && (player != null && PlayerUtil.isPlayerSurvival(player) && ! player.isUnderWater() 
				&& player.getPose() != Pose.SWIMMING && ! player.hasEffect(EffectRegister.LIGHT_EYE_EFFECT.get()));
	}
	
}
