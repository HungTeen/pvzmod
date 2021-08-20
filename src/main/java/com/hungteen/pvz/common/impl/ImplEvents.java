package com.hungteen.pvz.common.impl;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.event.PVZRegisterEvent;
import com.hungteen.pvz.common.impl.plant.CustomPlants;
import com.hungteen.pvz.common.impl.plant.MemePlants;
import com.hungteen.pvz.common.impl.plant.OtherPlants;
import com.hungteen.pvz.common.impl.plant.PVZPlants;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PVZMod.MOD_ID)
public class ImplEvents {

	@SubscribeEvent
	public static void registerPlantTypes(PVZRegisterEvent ev) {
		PVZPlants.register();
		OtherPlants.register();
		CustomPlants.register();
		MemePlants.register();
	}
	
}
