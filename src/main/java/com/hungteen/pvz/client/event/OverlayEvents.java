package com.hungteen.pvz.client.event;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.client.ClientProxy;
import com.hungteen.pvz.client.event.handler.PVZOverlayHandler;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-10 17:43
 **/
@Mod.EventBusSubscriber(modid = PVZMod.MOD_ID, value = Dist.CLIENT)
public class OverlayEvents {

    @SubscribeEvent
    public static void onPostRenderOverlay(RenderGameOverlayEvent.Post ev) {
        if(ev.getType() == RenderGameOverlayEvent.ElementType.ALL){
            if(! ClientProxy.MC.options.hideGui && ClientProxy.MC.screen == null && PVZInputEvents.ShowOverlay && ClientProxy.MC.player != null && ! ClientProxy.MC.player.isSpectator()){
                /* render resources on left upper corner */
                if(! ClientProxy.MC.options.renderDebug){
                    PVZOverlayHandler.renderResources(ev.getMatrixStack(), ev.getWindow().getGuiScaledWidth(), ev.getWindow().getGuiScaledHeight());
                }

                /* render plant food on left lower corner */
                if(PVZConfig.renderPlantFood()){
                    PVZOverlayHandler.renderPlantFood(ev.getMatrixStack(), ev.getWindow().getGuiScaledWidth(), ev.getWindow().getGuiScaledHeight());
                }
            }
        }
//        /* no opened gui */
//        if (mc.screen == null && PVZInputEvents.ShowOverlay) {
//
//            /* render invasion bar on right lower corner */
//            if(PVZConfig.CLIENT_CONFIG.OverlaySettings.RenderInvasionProgress.get()) {
//                PVZOverlayHandler.renderInvasionProgress(ev.getMatrixStack(), ev.getWindow().getGuiScaledWidth(), ev.getWindow().getGuiScaledHeight());
//                PVZOverlayHandler.renderMission(ev.getMatrixStack(), ev.getWindow().getGuiScaledWidth(), ev.getWindow().getGuiScaledHeight());
//            }
//
//            if(mc.player.getVehicle() instanceof CobCannonEntity) {
//                CobCannonEntity cob = (CobCannonEntity) mc.player.getVehicle();
//                if(cob.getCornNum() > 0) {
//                    PVZOverlayHandler.renderTargetAim(ev.getMatrixStack(), ev.getWindow().getGuiScaledWidth(), ev.getWindow().getGuiScaledHeight());
//                }
//            }

            /* render card slots on left side */
//			PVZOverlayHandler.drawCardInventory(mc.player, ev.getMatrixStack(), ev.getWindow().getGuiScaledWidth(), ev.getWindow().getGuiScaledHeight());
//        }
    }

//	@SubscribeEvent
//	public static void onRenderFog(RenderGameOverlayEvent.Pre ev) {
//		if (ev.getType() != RenderGameOverlayEvent.ElementType.ALL || mc.player == null || mc.player.isSpectator()) {
//			return;
//		}
//		if (PVZConfig.CLIENT_CONFIG.OverlaySettings.RenderFog.get()) {
//			int tick = PlayerUtil.getResource(ClientProxy.MC.player, Resources.NO_FOG_TICK);
//			if(tick < 0) {
//				PVZOverlayHandler.renderFog(ev.getMatrixStack(), ev.getWindow().getGuiScaledWidth(), ev.getWindow().getGuiScaledHeight(), Math.min(- tick * 1F / FogManager.CD, 1F));
//			}
//		}
//	}

}
