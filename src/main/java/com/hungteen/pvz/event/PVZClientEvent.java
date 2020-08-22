package com.hungteen.pvz.event;

import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class PVZClientEvent {

	@SubscribeEvent
	public void onGuiOpened(GuiOpenEvent event) {
//		if (IafConfig.customMainMenu && event.getGui() instanceof MainMenuScreen
//				&& !(event.getGui() instanceof IceAndFireMainMenu)) {
//			event.setGui(new IceAndFireMainMenu());
//		}
	}
}
