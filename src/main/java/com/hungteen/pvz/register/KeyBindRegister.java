package com.hungteen.pvz.register;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.fml.client.registry.ClientRegistry;

@OnlyIn(Dist.CLIENT)
public class KeyBindRegister {

	public static KeyBinding ShowPlayerResources;
	public static boolean showPlayerResources = true;
	
	public static void init() {
		ClientRegistry.registerKeyBinding(ShowPlayerResources = new KeyBinding("ShowPlayerResources", 261, "key.pvz.show_resources"));
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public static void onKeyDown(ClientTickEvent ev) {
		if(ev.getPhase().equals(Phase.END)&&Minecraft.getInstance().isGameFocused()) {
			if(ShowPlayerResources.isPressed()) {
				showPlayerResources = !showPlayerResources;
			}
		}
	}
}
