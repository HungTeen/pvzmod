package com.hungteen.pvz.register;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class KeyBindRegister {

	public static KeyBinding ShowPlayerResources = new KeyBinding("key.pvz.show_resources", 261, "key.categories.pvz");
	public static KeyBinding ShowInvasionProgress = new KeyBinding("key.pvz.show_progress", 80, "key.categories.pvz");
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
	
}
