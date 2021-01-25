package com.hungteen.pvz.register;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.event.OverlayEvents;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=PVZMod.MOD_ID,value = Dist.CLIENT)
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
		if(mc.isGameFocused()) {
			if(ShowPlayerResources.isPressed()) {
				showPlayerResources = ! showPlayerResources;
			}
			if(ShowInvasionProgress.isPressed()) {
				showInvasionProgress = ! showInvasionProgress;
			}
			if(LeftToggle.isPressed()) {
				changeToggle(- 1);
			}
			if(RightToggle.isPressed()) {
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
	
	private static void changeToggle(int offset) {
		int result = (currentResourcePos + offset + 3) % 3;
		while(! OverlayEvents.checkCurrentPos(result)) {
			result = (result + offset + 3) % 3;
		}
		currentResourcePos = result;
	}
	
}
