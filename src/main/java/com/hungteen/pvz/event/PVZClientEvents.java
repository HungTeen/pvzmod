package com.hungteen.pvz.event;

import java.util.Map;

import com.hungteen.pvz.model.baked.BowlingGloveBakedModel;
import com.hungteen.pvz.register.ItemRegister;

import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class PVZClientEvents {
	
	@SubscribeEvent
	public static void onModelBaked(ModelBakeEvent ev) {
		Map<ResourceLocation, IBakedModel> modelRegistry = ev.getModelRegistry();
		ModelResourceLocation location = new ModelResourceLocation(ItemRegister.BOWLING_GLOVE.get().getRegistryName(), "inventory");
		IBakedModel model = modelRegistry.get(location);
		if(model == null) {
			throw new RuntimeException("Did not find Obsidian Hidden in registry");
		} else if(model instanceof BowlingGloveBakedModel) {
			throw new RuntimeException("Tried to replaceObsidian Hidden twice");
		} else {
			BowlingGloveBakedModel tmp = new BowlingGloveBakedModel(model);
			modelRegistry.put(location, tmp);
		}
	}
	
}
