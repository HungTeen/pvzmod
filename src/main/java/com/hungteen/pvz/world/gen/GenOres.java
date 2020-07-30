package com.hungteen.pvz.world.gen;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.register.BlockRegister;

import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.ReplaceBlockConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid=PVZMod.MOD_ID,bus=Mod.EventBusSubscriber.Bus.MOD)
public class GenOres {

	@SubscribeEvent
	public static void onSetUpEvent(FMLCommonSetupEvent ev)
	{
		for(Biome biome:ForgeRegistries.BIOMES) {
			if(biome == Biomes.END_MIDLANDS||biome==Biomes.THE_END||biome==Biomes.END_HIGHLANDS||biome==Biomes.SMALL_END_ISLANDS||biome==Biomes.END_BARRENS) {
				addEndOres();
			}
		}
	}
	
	private static void addEndOres()
	{
		Biomes.THE_END.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.EMERALD_ORE.withConfiguration(
    			new ReplaceBlockConfig(Blocks.END_STONE.getDefaultState(), BlockRegister.AMETHYST_ORE.get().getDefaultState())).withPlacement(Placement.COUNT_RANGE.configure(
    			new CountRangeConfig(10, 0, 0, 70))));
	}
}
