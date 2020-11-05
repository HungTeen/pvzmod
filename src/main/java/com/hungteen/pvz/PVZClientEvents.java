package com.hungteen.pvz;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class PVZClientEvents {
	
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
//		
	}
	
}
