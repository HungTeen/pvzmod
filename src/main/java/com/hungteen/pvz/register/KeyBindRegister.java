package com.hungteen.pvz.register;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.client.OverlayEvents;
import com.hungteen.pvz.common.entity.plant.explosion.CobCannonEntity;
import com.hungteen.pvz.common.network.EntityInteractPacket;
import com.hungteen.pvz.common.network.PVZPacketHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=PVZMod.MOD_ID, value = Dist.CLIENT)
public class KeyBindRegister {

	public static KeyBinding ShowPlayerResources = new KeyBinding("key.pvz.show_resources", 261, "key.categories.pvz");
	public static boolean showPlayerResources = true;
	public static KeyBinding ShowInvasionProgress = new KeyBinding("key.pvz.show_progress", 80, "key.categories.pvz");
	public static boolean showInvasionProgress = true;
	public static int currentResourcePos = 0;
	public static KeyBinding LeftToggle = new KeyBinding("key.pvz.left_toggle", 263, "key.categories.pvz");
	public static KeyBinding RightToggle = new KeyBinding("key.pvz.right_toggle", 262, "key.categories.pvz");
//	public static KeyBinding ShowPlayerInventory;
	
	public static void init() {
		ClientRegistry.registerKeyBinding(ShowPlayerResources);
		ClientRegistry.registerKeyBinding(ShowInvasionProgress);
		ClientRegistry.registerKeyBinding(LeftToggle);
		ClientRegistry.registerKeyBinding(RightToggle);
//		InputMappings
//		ClientRegistry.registerKeyBinding(ShowPlayerInventory = new KeyBinding("key.pvz.show_inventory", 80, "key.categories.pvz"));
	}
	
	@SubscribeEvent
	public static void onKeyDown(InputEvent.KeyInputEvent ev) {
		Minecraft mc = Minecraft.getInstance();
		if(mc.isWindowActive()) {
			if(ShowPlayerResources.consumeClick()) {
				showPlayerResources = ! showPlayerResources;
			}
			if(ShowInvasionProgress.consumeClick()) {
				showInvasionProgress = ! showInvasionProgress;
			}
			if(LeftToggle.consumeClick()) {
				changeToggle(- 1);
			}
			if(RightToggle.consumeClick()) {
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
	
	private static void changeToggle(int offset) {
		int result = (currentResourcePos + offset + 3) % 3;
		while(! OverlayEvents.checkCurrentPos(result)) {
			result = (result + offset + 3) % 3;
		}
		currentResourcePos = result;
	}
	
}
