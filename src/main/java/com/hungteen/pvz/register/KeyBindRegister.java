package com.hungteen.pvz.register;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.network.OpenGuiPacket;
import com.hungteen.pvz.network.PVZPacketHandler;
import com.hungteen.pvz.utils.enums.Guis;

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
	public static KeyBinding ShowPlayerInventory;
	
	public static void init() {
		ClientRegistry.registerKeyBinding(ShowPlayerResources = new KeyBinding("key.pvz.show_resources", 261, "key.categories.pvz"));
		ClientRegistry.registerKeyBinding(ShowPlayerInventory = new KeyBinding("key.pvz.show_inventory", 80, "key.categories.pvz"));
	}
	
	@SubscribeEvent
	public static void onKeyDown(InputEvent.KeyInputEvent ev) {
		Minecraft mc = Minecraft.getInstance();
		if(mc.isGameFocused()) {
			if(ShowPlayerResources.isPressed()) {
				showPlayerResources = !showPlayerResources;
			}
			if(ShowPlayerInventory.isPressed()) {
//				DistExecutor.runWhenOn(Dist.CLIENT, ()->()->{
//					Minecraft.getInstance().displayGuiScreen(new PVZPlayerInventoryGui());
//				});
				PVZPacketHandler.CHANNEL.sendToServer(new OpenGuiPacket(Guis.PLAYER_INVENTORY.ordinal()));
			}
		}
	}
}
