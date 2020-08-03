package com.hungteen.pvz.register;

import com.hungteen.pvz.PVZMod;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid=PVZMod.MOD_ID,bus=Mod.EventBusSubscriber.Bus.MOD)
public class RenderTypeRegister {

	@SubscribeEvent
	public static void reigsterRenderType(FMLCommonSetupEvent ev)
	{
		RenderTypeLookup.setRenderLayer(BlockRegister.ORIGIN_BLOCK.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(BlockRegister.PEA_PLANT.get(), RenderType.getCutout());
	}
}
