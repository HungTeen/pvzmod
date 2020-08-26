package com.hungteen.pvz.register;

import com.hungteen.pvz.PVZMod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=PVZMod.MOD_ID,value = Dist.CLIENT)
public class KeyBindRegister {

	public static KeyBinding ShowPlayerResources;
	public static boolean showPlayerResources = true;
//	public static KeyBinding ShowPVZPlayerWindow;
	
	public static void init() {
		ClientRegistry.registerKeyBinding(ShowPlayerResources = new KeyBinding("key.pvz.show_resources", 261, "key.categories.pvz"));
//		ClientRegistry.registerKeyBinding(ShowPVZPlayerWindow = new KeyBinding("key.pvz.player_window", 80, "key.categories.pvz"));
	}
	
	@SubscribeEvent
	public static void onKeyDown(InputEvent.KeyInputEvent ev) {
//		System.out.println("on key down");
		Minecraft mc = Minecraft.getInstance();
		if(mc.isGameFocused()) {
			if(ShowPlayerResources.isPressed()) {
				showPlayerResources = !showPlayerResources;
			}
		}
//		if(ShowPVZPlayerWindow.isPressed()&&mc.isGameFocused()&&mc.player!=null) {
//			if(mc.currentScreen instanceof PVZPlayerWindow) {
//				mc.displayGuiScreen(null);
//			}else if(mc.currentScreen == null){
//				mc.displayGuiScreen(new PVZPlayerWindow(mc.player));
//			}
//		}
	}
}
