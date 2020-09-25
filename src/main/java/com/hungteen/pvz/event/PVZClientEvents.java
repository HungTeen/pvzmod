package com.hungteen.pvz.event;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.gui.PVZMainMenuScreen;

import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=PVZMod.MOD_ID,value = Dist.CLIENT)
public class PVZClientEvents {

	@SubscribeEvent
	public static void onGuiOpened(GuiOpenEvent event) {
//		System.out.println("open gui");
		if(PVZConfig.CLIENT_CONFIG.OtherSettings.ShowPVZMainMenu.get()) {//show pvz menu
		    if (event.getGui() instanceof MainMenuScreen&& !(event.getGui() instanceof PVZMainMenuScreen)) {
			    event.setGui(new PVZMainMenuScreen());
		    }
		}
	}
}
