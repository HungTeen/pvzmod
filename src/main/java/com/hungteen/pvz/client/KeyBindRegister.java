package com.hungteen.pvz.client;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;

@OnlyIn(Dist.CLIENT)
public class KeyBindRegister {

	public static final KeyBinding SHOW_OVERLAY = new KeyBinding("key.pvz.show_overlay", 261, "key.categories.pvz");
//	public static final KeyBinding UP_TOGGLE = new KeyBinding("key.pvz.up_toggle", 265, "key.categories.pvz");
//	public static final KeyBinding DOWN_TOGGLE = new KeyBinding("key.pvz.down_toggle", 264, "key.categories.pvz");
	public static final KeyBinding LEFT_TOGGLE = new KeyBinding("key.pvz.left_toggle", 263, "key.categories.pvz");
	public static final KeyBinding RIGHT_TOGGLE = new KeyBinding("key.pvz.right_toggle", 262, "key.categories.pvz");
//	public static final KeyBinding SHIFT = new KeyBinding("key.pvz.shift", 340, "key.categories.pvz");
	
	/**
	 * {@link ClientProxy#setUpClient()}
	 */
	public static void init() {
		ClientRegistry.registerKeyBinding(SHOW_OVERLAY);
//		ClientRegistry.registerKeyBinding(UP_TOGGLE);
//		ClientRegistry.registerKeyBinding(DOWN_TOGGLE);
		ClientRegistry.registerKeyBinding(LEFT_TOGGLE);
		ClientRegistry.registerKeyBinding(RIGHT_TOGGLE);
//		ClientRegistry.registerKeyBinding(SHIFT);
	}
	
}
