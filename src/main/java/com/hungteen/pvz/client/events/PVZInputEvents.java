package com.hungteen.pvz.client.events;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.client.events.handler.PVZOverlayHandler;
import com.hungteen.pvz.common.entity.plant.explosion.CobCannonEntity;
import com.hungteen.pvz.common.item.spawn.card.SummonCardItem;
import com.hungteen.pvz.common.network.PVZPacketHandler;
import com.hungteen.pvz.common.network.toserver.EntityInteractPacket;
import com.hungteen.pvz.common.network.toserver.PVZMouseScrollPacket;
import com.hungteen.pvz.register.KeyBindRegister;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PVZMod.MOD_ID, value = Dist.CLIENT)
public class PVZInputEvents {

	public static boolean showPlayerResources = true;
	public static boolean showInvasionProgress = true;
	public static int currentResourcePos = 0;
	
	@SubscribeEvent
	public static void onKeyDown(InputEvent.KeyInputEvent ev) {
		Minecraft mc = Minecraft.getInstance();
		if(mc.isWindowActive()) {
			if(KeyBindRegister.ShowPlayerResources.consumeClick()) {
				showPlayerResources = ! showPlayerResources;
			}
			if(KeyBindRegister.ShowInvasionProgress.consumeClick()) {
				showInvasionProgress = ! showInvasionProgress;
			}
			if(KeyBindRegister.LeftToggle.consumeClick()) {
				changeToggle(- 1);
			}
			if(KeyBindRegister.RightToggle.consumeClick()) {
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
	
	private static void changeToggle(int offset) {
		int result = (currentResourcePos + offset + 3) % 3;
		while(! PVZOverlayHandler.checkCurrentPos(result)) {
			result = (result + offset + 3) % 3;
		}
		currentResourcePos = result;
	}
	
}
