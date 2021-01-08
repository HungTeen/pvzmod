package com.hungteen.pvz.event;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.gui.screen.PVZMainMenuScreen;

import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PVZMod.MOD_ID, value = Dist.CLIENT)
public class PVZClientEvents {
	
	@SubscribeEvent
	public static void onGuiOpened(GuiOpenEvent event) {
		if(PVZConfig.CLIENT_CONFIG.OtherSettings.ShowPVZMainMenu.get()) {//show pvz menu
		    if (event.getGui() instanceof MainMenuScreen && ! (event.getGui() instanceof PVZMainMenuScreen)) {
			    event.setGui(new PVZMainMenuScreen());
		    }
		}
	}
	
	@SubscribeEvent
	public static void onModelBaked(ModelBakeEvent ev) {
//		Map<ResourceLocation, IBakedModel> modelRegistry = ev.getModelRegistry();
//		ModelResourceLocation location = new ModelResourceLocation(ItemRegister.SCREEN_DOOR.get().getRegistryName(), "inventory");
//		IBakedModel model = modelRegistry.get(location);
//		if(model == null) {
//			throw new RuntimeException("Did not find Obsidian Hidden in registry");
//		}else if(model instanceof ScreenDoorBakedModel) {
//			throw new RuntimeException("Tried to replaceObsidian Hidden twice");
//		}else {
//			ScreenDoorBakedModel tmp = new ScreenDoorBakedModel(model);
//			modelRegistry.put(location, tmp);
//		}
	}
	
}
