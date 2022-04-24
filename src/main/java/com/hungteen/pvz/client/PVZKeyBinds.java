package com.hungteen.pvz.client;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-10 17:50
 **/
public class PVZKeyBinds {

    public static final KeyMapping SHOW_OVERLAY = new KeyMapping("key.pvz.show_overlay", 261, "key.categories.pvz");
    //	public static final KeyBinding UP_TOGGLE = new KeyBinding("key.pvz.up_toggle", 265, "key.categories.pvz");
//	public static final KeyBinding DOWN_TOGGLE = new KeyBinding("key.pvz.down_toggle", 264, "key.categories.pvz");
    public static final KeyMapping LEFT_TOGGLE = new KeyMapping("key.pvz.left_toggle", 263, "key.categories.pvz");
    public static final KeyMapping RIGHT_TOGGLE = new KeyMapping("key.pvz.right_toggle", 262, "key.categories.pvz");
//	public static final KeyBinding SHIFT = new KeyBinding("key.pvz.shift", 340, "key.categories.pvz");

    /**
     * {@link ClientRegister#setUpClient(FMLClientSetupEvent)}
     */
    public static void register() {
        ClientRegistry.registerKeyBinding(SHOW_OVERLAY);
//		ClientRegistry.registerKeyBinding(UP_TOGGLE);
//		ClientRegistry.registerKeyBinding(DOWN_TOGGLE);
        ClientRegistry.registerKeyBinding(LEFT_TOGGLE);
        ClientRegistry.registerKeyBinding(RIGHT_TOGGLE);
//		ClientRegistry.registerKeyBinding(SHIFT);
    }

}
