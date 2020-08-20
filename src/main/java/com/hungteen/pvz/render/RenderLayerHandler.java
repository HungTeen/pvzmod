package com.hungteen.pvz.render;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.register.BlockRegister;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid=PVZMod.MOD_ID,bus=Mod.EventBusSubscriber.Bus.MOD)
public class RenderLayerHandler {

	@SubscribeEvent
	public static void reigsterRenderType(FMLCommonSetupEvent ev){
		RenderTypeLookup.setRenderLayer(BlockRegister.ORIGIN_BLOCK.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(BlockRegister.PEA_PLANT.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BlockRegister.NUT_LEAVES.get(), RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(BlockRegister.NUT_SAPLING.get(), RenderType.getCutout());
	}
}
