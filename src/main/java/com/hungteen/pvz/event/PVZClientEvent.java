package com.hungteen.pvz.event;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.gui.PVZMainMenu;

import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=PVZMod.MOD_ID,value = Dist.CLIENT)
public class PVZClientEvent {

	@SubscribeEvent
	public static void onGuiOpened(GuiOpenEvent event) {
//		System.out.println("open gui");
		if (event.getGui() instanceof MainMenuScreen&& !(event.getGui() instanceof PVZMainMenu)) {
			event.setGui(new PVZMainMenu());
		}
	}
}
