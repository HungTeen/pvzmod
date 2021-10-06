package com.hungteen.pvz.client.events;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.client.KeyBindRegister;
import com.hungteen.pvz.common.entity.plant.explosion.CobCannonEntity;
import com.hungteen.pvz.common.item.spawn.card.SummonCardItem;
import com.hungteen.pvz.common.network.PVZPacketHandler;
import com.hungteen.pvz.common.network.toserver.EntityInteractPacket;
import com.hungteen.pvz.common.network.toserver.PVZMouseScrollPacket;
import com.hungteen.pvz.utils.ConfigUtil;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PVZMod.MOD_ID, value = Dist.CLIENT)
public class PVZInputEvents {

	private static final int SWITCH_NUM = 4;
	public static boolean ShowOverlay = true;
	public static int CurrentResourcePos = 0;
	
	@SubscribeEvent
	public static void onKeyDown(InputEvent.KeyInputEvent ev) {
		Minecraft mc = Minecraft.getInstance();
		if(mc.isWindowActive()) {
			if(KeyBindRegister.SHOW_OVERLAY.consumeClick()) {
				ShowOverlay = ! ShowOverlay;
			}
			if(KeyBindRegister.LEFT_TOGGLE.consumeClick()) {
				changeToggle(- 1);
			}
			if(KeyBindRegister.RIGHT_TOGGLE.consumeClick()) {
				changeToggle(1);
			}
//			if(ShowPlayerInventory.isPressed()) {
////				DistExecutor.runWhenOn(Dist.CLIENT, ()->()->{
////					Minecraft.getInstance().displayGuiScreen(new PVZPlayerInventoryGui());
////				});
//				PVZPacketHandler.CHANNEL.sendToServer(new OpenGuiPacket(Guis.PLAYER_INVENTORY.ordinal()));
//			}
		}
	}
	
	@SubscribeEvent
	public static void onMouseDown(InputEvent.MouseInputEvent ev) {
		Minecraft mc = Minecraft.getInstance();
		if(mc.isWindowActive() && mc.player != null) {
			if(mc.player.getVehicle() instanceof CobCannonEntity) {
				CobCannonEntity cob = (CobCannonEntity) mc.player.getVehicle();
				if(mc.player.getMainHandItem().isEmpty() && cob.getCornNum() > 0 && mc.options.keyUse.consumeClick()) {
				    PVZPacketHandler.CHANNEL.sendToServer(new EntityInteractPacket(cob.getId(), 0, 0));
				}
			}
		}
	}
	
	@SubscribeEvent
    public static void onMouseScroll(InputEvent.MouseScrollEvent ev) {
		Minecraft mc = Minecraft.getInstance();
		double delta = ev.getScrollDelta();
		if(delta != 0.0 && EntityUtil.isEntityValid(mc.player) && mc.player.isShiftKeyDown()) {
			if(mc.player.getMainHandItem().getItem() instanceof SummonCardItem) {
				PVZPacketHandler.CHANNEL.sendToServer(new PVZMouseScrollPacket(delta));
				ev.setCanceled(true);
			}
		}
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
		return pos == 0 ? ConfigUtil.renderSunBar() :
			pos == 1 ? ConfigUtil.renderMoneyBar() :
			pos == 2 ? ConfigUtil.renderGemBar() :
			pos == 3 ? ConfigUtil.renderTreeLevel() :
			false;
	}
	
}
