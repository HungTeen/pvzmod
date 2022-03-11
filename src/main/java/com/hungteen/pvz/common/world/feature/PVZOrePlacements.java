package com.hungteen.pvz.common.world.feature;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.common.block.PVZBlocks;
import com.hungteen.pvz.utils.BiomeUtil;
import com.hungteen.pvz.utils.Util;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.DimensionDefaults;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraftforge.event.world.BiomeLoadingEvent;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-11 10:30
 **/
public class PVZOrePlacements {

    public static final RuleTest IN_END_STONE = new BlockMatchTest(Blocks.END_STONE);
    public static final RuleTest ON_GRASS = new BlockMatchTest(Blocks.GRASS_BLOCK);

    public static Holder<PlacedFeature> ORE_AMETHYST_UPPER;
    public static Holder<PlacedFeature> ORE_ORIGIN_UPPER;
    public static Holder<PlacedFeature> ORE_ORIGIN_UPPER_DOUBLE;

    /**
     * {@link com.hungteen.pvz.common.world.biome.PVZBiomes#biomeModification(BiomeLoadingEvent)}
     */
    public static void addOresToBiomes(final BiomeLoadingEvent event, ResourceKey<Biome> biomeKey) {
        if (BiomeUtil.isTheEnd(biomeKey)) {
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ORE_AMETHYST_UPPER);
        }
        if(BiomeUtil.isOverworld(biomeKey)) {
            if(BiomeUtil.isForest(biomeKey)) {
                event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ORE_ORIGIN_UPPER);
            }
        }
    }

    /**
     * {@link PVZFeatures#registerFeatures()}
     */
    public static void registerOrePlacements(){
        {
            final OreConfiguration oreConfiguration = new OreConfiguration(IN_END_STONE, PVZBlocks.AMETHYST_ORE.get().defaultBlockState(), 4, 0F);
            ORE_AMETHYST_UPPER = registerPlacedFeature(
                    Util.prefix("ore_amethyst_upper").toString(),
                    new ConfiguredFeature<>(Feature.ORE, oreConfiguration),
                    InSquarePlacement.spread(),
                    PlacementUtils.FULL_RANGE,
                    CountPlacement.of(PVZConfig.getAmethystOreCount()),
                    BiomeFilter.biome()
            );
        }
        {
            final OreConfiguration oreConfiguration = new OreConfiguration(ON_GRASS, PVZBlocks.ORIGIN_ORE.get().defaultBlockState(), 4, 0F);
            ORE_ORIGIN_UPPER = registerPlacedFeature(
                    Util.prefix("ore_origin_upper").toString(),
                    new ConfiguredFeature<>(Feature.ORE, oreConfiguration),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.triangle(VerticalAnchor.absolute(50), VerticalAnchor.absolute(200)),
                    CountPlacement.of(PVZConfig.getOriginOreCount()),
                    BiomeFilter.biome()
            );

            ORE_ORIGIN_UPPER_DOUBLE = registerPlacedFeature(
                    Util.prefix("ore_origin_upper_double").toString(),
                    new ConfiguredFeature<>(Feature.ORE, oreConfiguration),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.triangle(VerticalAnchor.absolute(50), VerticalAnchor.absolute(200)),
                    CountPlacement.of(PVZConfig.getOriginOreCount() * 4),
                    BiomeFilter.biome()
            );
        }
    }

    private  static <C extends FeatureConfiguration, F extends Feature<C>> Holder<PlacedFeature> registerPlacedFeature(String name, ConfiguredFeature<C, F> configuredFeature, PlacementModifier... modifiers){
        return PlacementUtils.register(name, Holder.direct(configuredFeature), modifiers);
    }

}
