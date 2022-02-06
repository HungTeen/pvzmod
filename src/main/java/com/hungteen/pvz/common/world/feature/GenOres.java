package com.hungteen.pvz.common.world.feature;

import com.hungteen.pvz.common.world.biome.BiomeRegister;
import com.hungteen.pvz.common.world.FeatureRegister;
import com.hungteen.pvz.utils.BiomeUtil;

import net.minecraft.block.Blocks;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class GenOres {

	/**
	 * {@link BiomeRegister#biomeModification(BiomeLoadingEvent)}
	 */
	public static void addOresToBiomes(final BiomeLoadingEvent event, RegistryKey<Biome> biomeKey) {
		if (BiomeUtil.isTheEnd(biomeKey)) {
			event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
					FeatureRegister.CONFIGURED_AMETHYST_ORE);
		}
		if(BiomeUtil.isOverworld(biomeKey)) {
			if(BiomeUtil.isForest(biomeKey)) {
				event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
						FeatureRegister.CONFIGURED_ORIGIN_ORE);
			}
		}
	}

	public static final class FillerBlockType {
		public static final RuleTest END_STONE = new BlockMatchRuleTest(Blocks.END_STONE);
		
		public static final RuleTest GRASS = new BlockMatchRuleTest(Blocks.GRASS_BLOCK);
	}

}
