package com.hungteen.pvz.client.event;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.client.ClientProxy;
import com.hungteen.pvz.client.PVZKeyBinds;
import com.hungteen.pvz.common.network.PVZPacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-10 17:49
 **/
@Mod.EventBusSubscriber(modid = PVZMod.MOD_ID, value = Dist.CLIENT)
public class PVZInputEvents {

    private static final int SWITCH_NUM = 4;
    public static boolean ShowOverlay = true;
    public static int CurrentResourcePos = 0;

    @SubscribeEvent
    public static void onKeyDown(InputEvent.KeyInputEvent ev) {
        Minecraft mc = Minecraft.getInstance();
        if(mc.isWindowActive() && ClientProxy.MC.player != null) {
            /* change display of resource overlay */
            if(PVZKeyBinds.SHOW_OVERLAY.consumeClick()) {
                ShowOverlay = ! ShowOverlay;
            }

//			/* change card slot position */
//			if(ClientProxy.MC.player.getItemInHand(Hand.MAIN_HAND).getItem() instanceof SummonCardItem) {
//				if(KeyBindRegister.UP_TOGGLE.consumeClick()) {
//					PVZSwitchSlotHander.changeCardSlot(1F);
//				}
//			    if(KeyBindRegister.DOWN_TOGGLE.consumeClick()) {
//					PVZSwitchSlotHander.changeCardSlot(- 1F);
//			    }
//			}

            /* change resource to display */
            if(PVZKeyBinds.LEFT_TOGGLE.consumeClick()) {
                changeToggle(- 1);
            }
            if(PVZKeyBinds.RIGHT_TOGGLE.consumeClick()) {
                changeToggle(1);
            }
        }
    }

    @SubscribeEvent
    public static void onMouseDown(InputEvent.MouseInputEvent ev) {
//        if(ClientProxy.MC.isWindowActive() && ClientProxy.MC.player != null) {
//            if(ClientProxy.MC.player.getVehicle() instanceof CobCannonEntity) {
//                CobCannonEntity cob = (CobCannonEntity) ClientProxy.MC.player.getVehicle();
//                if(ClientProxy.MC.player.getMainHandItem().isEmpty() && cob.getCornNum() > 0 && ClientProxy.MC.options.keyUse.consumeClick()) {
//                    PVZPacketHandler.CHANNEL.sendToServer(new EntityInteractPacket(cob.getId(), 0, 0));
//                }
//            }
//        }
    }

    @SubscribeEvent
    public static void onMouseScroll(InputEvent.MouseScrollEvent ev) {
//		double delta = ev.getScrollDelta();
//		if(delta != 0.0 && EntityUtil.isEntityValid(ClientProxy.MC.player) && KeyBindRegister.SHIFT.isDown()) {
//			if(ClientProxy.MC.player.getMainHandItem().getItem() instanceof SummonCardItem) {
//				PVZSwitchSlotHander.changeCardSlot(delta);
//				ev.setCanceled(true);
//			}
//		}
    }

    /**
     * {@link #onKeyDown(net.minecraftforge.client.event.InputEvent.KeyInputEvent)}
     */
    private static void changeToggle(int offset) {
        int result = (CurrentResourcePos + offset + SWITCH_NUM) % SWITCH_NUM;
        int cnt = 0;//avoid endless loop.
        while(! checkCurrentPos(result)) {
            result = (result + offset + 3) % 3;
            if(++ cnt >= SWITCH_NUM + 1) {
                break;
            }
        }
        CurrentResourcePos = result;
    }

    private static boolean checkCurrentPos(int pos) {
        return pos == 0 ? PVZConfig.renderSunBar() :
                pos == 1 ? PVZConfig.renderMoneyBar() :
                        pos == 2 ? PVZConfig.renderGemBar() :
                                pos == 3 ? PVZConfig.renderTreeLevel() :
                                        false;
    }

}
