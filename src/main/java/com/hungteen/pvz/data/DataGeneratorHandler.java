package com.hungteen.pvz.data;

import com.hungteen.pvz.PVZMod;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@EventBusSubscriber(modid = PVZMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGeneratorHandler {

	@SubscribeEvent
	public static void gatherData(GatherDataEvent ev) {
		ev.getGenerator().addProvider(new PVZItemModelGenerator(ev.getGenerator(), ev.getExistingFileHelper()));
		ev.getGenerator().addProvider(new PVZRecipeGenerator(ev.getGenerator()));
	}
}
