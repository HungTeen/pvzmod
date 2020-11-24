package com.hungteen.pvz.data;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.data.loot.LootTableGenerator;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@EventBusSubscriber(modid = PVZMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGeneratorHandler {

	@SubscribeEvent
	public static void gatherData(GatherDataEvent ev) {
		ev.getGenerator().addProvider(new ItemModelGenerator(ev.getGenerator(), ev.getExistingFileHelper()));
		ev.getGenerator().addProvider(new RecipeGenerator(ev.getGenerator()));
		ev.getGenerator().addProvider(new LootTableGenerator(ev.getGenerator()));
		ev.getGenerator().addProvider(new ItemTagGenerator(ev.getGenerator()));
	}
}
