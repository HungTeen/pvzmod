package com.hungteen.pvz.world.gen;

import com.hungteen.pvz.PVZMod;

import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=PVZMod.MOD_ID,bus=Mod.EventBusSubscriber.Bus.MOD)
public class GenOres {

//	@SubscribeEvent
//	public static void onSetUpEvent(FMLCommonSetupEvent ev)
//	{
//		for(Biome biome:BiomeUtil.THE_END) {
//			addEndOres(biome);
//		}
//	}
//	
//	private static void addEndOres(Biome biome)
//	{
//		biome.func_203611_a(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.EMERALD_ORE.configured(
//    			new ReplaceBlockConfig(Blocks.END_STONE.defaultBlockState(), BlockRegister.AMETHYST_ORE.get().defaultBlockState())).decorated(Placement.field_215028_n.configured(
//    			new CountRangeConfig(10, 0, 0, 70))));
//	}
}
