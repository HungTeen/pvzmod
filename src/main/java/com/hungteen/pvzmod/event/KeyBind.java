package com.hungteen.pvzmod.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class KeyBind {

	public static KeyBinding keyPlayerStats;
	public static boolean statusPlayerStats = true;
	
	public static void init() {
		ClientRegistry.registerKeyBinding(keyPlayerStats = new KeyBinding("key.showPlayerStats", 80, "key.categories.pvz"));
	}
	
	@SubscribeEvent
	public void onKeyDown(final InputEvent.KeyInputEvent ev) {
		if (keyPlayerStats.isPressed()) {
			statusPlayerStats = !statusPlayerStats;
		}
	}
}
