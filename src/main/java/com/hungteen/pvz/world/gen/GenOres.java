package com.hungteen.pvz.world.gen;

import com.hungteen.pvz.register.FeatureRegister;
import com.hungteen.pvz.utils.BiomeUtil;

import net.minecraft.block.Blocks;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class GenOres {

	public static void addOresToBiomes(final BiomeLoadingEvent event) {
		Biome biome = ForgeRegistries.BIOMES.getValue(event.getName());
		RegistryKey<Biome> biomeKey = BiomeUtil.getKey(biome);
		if (BiomeUtil.isTheEnd(biomeKey)) {
			event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
					FeatureRegister.CONFIGURED_AMETHYST_ORE);
		}
	}
//	
//	private static void addEndOres(Biome biome)
//	{
//		biome.func_203611_a(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.EMERALD_ORE.configured(
//    			new ReplaceBlockConfig(Blocks.END_STONE.defaultBlockState(), BlockRegister.AMETHYST_ORE.get().defaultBlockState())).decorated(Placement.field_215028_n.configured(
//    			new CountRangeConfig(10, 0, 0, 70))));
//	}

	public static final class FillerBlockType {
		public static final RuleTest END_STONE = new BlockMatchRuleTest(Blocks.END_STONE);
	}

}
