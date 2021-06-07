package com.hungteen.pvz.common.world.gen;

import com.hungteen.pvz.register.BiomeRegister;
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

	/**
	 * {@link BiomeRegister#biomeModification(BiomeLoadingEvent)}
	 */
	public static void addOresToBiomes(final BiomeLoadingEvent event) {
		Biome biome = ForgeRegistries.BIOMES.getValue(event.getName());
		if(biome == null) {//prevent crash.
			return ;
		}
		RegistryKey<Biome> biomeKey = BiomeUtil.getKey(biome);
		if (BiomeUtil.isTheEnd(biomeKey)) {
			event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
					FeatureRegister.CONFIGURED_AMETHYST_ORE);
		}
	}

	public static final class FillerBlockType {
		public static final RuleTest END_STONE = new BlockMatchRuleTest(Blocks.END_STONE);
	}

}
