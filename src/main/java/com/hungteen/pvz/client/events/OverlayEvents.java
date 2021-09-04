package com.hungteen.pvz.client.events;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.client.cache.ClientPlayerResources;
import com.hungteen.pvz.client.events.handler.PVZOverlayHandler;
import com.hungteen.pvz.common.entity.plant.explosion.CobCannonEntity;
import com.hungteen.pvz.common.world.invasion.FogManager;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PVZMod.MOD_ID, value = Dist.CLIENT)
public class OverlayEvents {

	private static Minecraft mc = Minecraft.getInstance();

	@SubscribeEvent
	public static void onPostRenderOverlay(RenderGameOverlayEvent.Post ev) {
		if (ev.getType() != RenderGameOverlayEvent.ElementType.ALL
				|| mc.player == null || mc.options.hideGui || mc.player.isSpectator()) {
			return;
		}
		if (mc.screen == null) {
			if(PVZInputEvents.showPlayerResources) {
				if(PVZOverlayHandler.checkCurrentPos(PVZInputEvents.currentResourcePos)) {
					if(PVZInputEvents.currentResourcePos == 0) {
						PVZOverlayHandler.drawSunNumBar(ev.getMatrixStack());
					} else if(PVZInputEvents.currentResourcePos == 1) {
						PVZOverlayHandler.drawMoneyBar(ev.getMatrixStack());
					} else if(PVZInputEvents.currentResourcePos == 2) {
						PVZOverlayHandler.drawGemBar(ev.getMatrixStack());
					}
				}
			    if (PVZConfig.CLIENT_CONFIG.ResourceRender.RenderEnergyNumBar.get()) {
			    	PVZOverlayHandler.drawEnergyNumBar(ev.getMatrixStack(), ev.getWindow().getGuiScaledWidth(), ev.getWindow().getGuiScaledHeight());
			    }
			}
			if(PVZInputEvents.showInvasionProgress) {
				if(PlayerUtil.isPlayerSurvival(mc.player)  && PVZConfig.CLIENT_CONFIG.InvasionRender.RenderInvasionProgress.get()) {
					PVZOverlayHandler.renderInvasionProgress(ev.getMatrixStack(), ev.getWindow().getGuiScaledWidth(), ev.getWindow().getGuiScaledHeight());
				}
			}
			if(mc.player.getVehicle() instanceof CobCannonEntity) {
				CobCannonEntity cob = (CobCannonEntity) mc.player.getVehicle();
				if(cob.getCornNum() > 0) {
					PVZOverlayHandler.renderTargetAim(ev.getMatrixStack(), ev.getWindow().getGuiScaledWidth(), ev.getWindow().getGuiScaledHeight());
				}
			}
			PVZOverlayHandler.drawCardInventory(mc.player, ev.getMatrixStack(), ev.getWindow().getGuiScaledWidth(), ev.getWindow().getGuiScaledHeight());
		}
	}

	@SubscribeEvent
	public static void onRenderFog(RenderGameOverlayEvent.Pre ev) {
		if (ev.getType() != RenderGameOverlayEvent.ElementType.ALL || mc.player == null || mc.player.isSpectator()) {
			return;
		}
		if (PVZConfig.CLIENT_CONFIG.EnvironmentRender.RenderFog.get()) {
			int tick = ClientPlayerResources.getPlayerStats(Resources.NO_FOG_TICK);
			if(tick < 0) {
				PVZOverlayHandler.renderFog(ev.getMatrixStack(), ev.getWindow().getGuiScaledWidth(), ev.getWindow().getGuiScaledHeight(), Math.min(- tick * 1F / FogManager.CD, 1F));
			}
		}
	}
	
}
