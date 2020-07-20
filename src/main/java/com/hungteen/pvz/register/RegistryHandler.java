package com.hungteen.pvz.register;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class RegistryHandler {

	
	public static void init()
	{
		ItemRegister.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		BlockRegister.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
}
