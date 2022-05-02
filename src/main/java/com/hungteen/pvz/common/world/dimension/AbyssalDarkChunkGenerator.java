package com.hungteen.pvz.common.world.dimension;

import com.hungteen.pvz.common.world.ChunkManager;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.Util;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.level.*;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.blending.Blender;
import net.minecraft.world.level.levelgen.structure.StructureSet;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static net.minecraft.util.Mth.floor;
import static net.minecraft.util.Mth.sign;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-14 10:12
 **/
public class AbyssalDarkChunkGenerator extends ChunkGenerator {

    private static final Codec<Settings> SETTINGS_CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("base").forGetter(Settings::baseHeight),
                    Codec.FLOAT.fieldOf("verticalvariance").forGetter(Settings::verticalVariance),
                    Codec.FLOAT.fieldOf("horizontalvariance").forGetter(Settings::horizontalVariance)
            ).apply(instance, Settings::new));

    public static final Codec<AbyssalDarkChunkGenerator> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    RegistryOps.retrieveRegistry(Registry.STRUCTURE_SET_REGISTRY).forGetter(AbyssalDarkChunkGenerator::getStructureSetRegistry),
                    RegistryOps.retrieveRegistry(Registry.BIOME_REGISTRY).forGetter(AbyssalDarkChunkGenerator::getBiomeRegistry),
                    SETTINGS_CODEC.fieldOf("settings").forGetter(AbyssalDarkChunkGenerator::getSettings)
            ).apply(instance, AbyssalDarkChunkGenerator::new));

    public static final ResourceLocation DEEP_DARK_DIMENSION_SET = Util.prefix("abyssal_dark_dimension_structure_set");
    private final Settings settings;


    public AbyssalDarkChunkGenerator(Registry<StructureSet> structureSetRegistry, Registry<Biome> registry, Settings settings) {
        super(structureSetRegistry, getSet(structureSetRegistry), new AbyssalDarkBiomeProvider(registry));
        this.settings = settings;
    }

    private static Optional<HolderSet<StructureSet>> getSet(Registry<StructureSet> structureSetRegistry) {
        HolderSet.Named<StructureSet> structureSet = structureSetRegistry.getOrCreateTag(TagKey.create(Registry.STRUCTURE_SET_REGISTRY, DEEP_DARK_DIMENSION_SET));
        return Optional.of(structureSet);
    }

    public Settings getSettings() {
        return settings;
    }

    public Registry<Biome> getBiomeRegistry() {
        return ((AbyssalDarkBiomeProvider) biomeSource).getBiomeRegistry();
    }

    public Registry<StructureSet> getStructureSetRegistry() {
        return structureSets;
    }

    @Override
    public void buildSurface(WorldGenRegion region, StructureFeatureManager featureManager, ChunkAccess chunk) {
        BlockState bedrock = Blocks.BEDROCK.defaultBlockState();
        BlockState stone = Blocks.STONE.defaultBlockState();
        ChunkPos chunkPos = chunk.getPos();
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        final int[] floors = new int[4];
        floors[0] = 0;
        floors[1] = 127;
        floors[2] = 191;
        floors[3] = 255;

        ChunkManager.Build(chunkPos.x, chunkPos.z, floors);

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int l = 1; l < 7; ++ l) {
                    int height = (int) ChunkManager.getTerrain(chunkPos.x, chunkPos.z, x, z, l-1) + 1;
                    for (int y = 1; y < height; ++y){
                        chunk.setBlockState(pos.set(x, floors[l/2] + (((float)l)/2 == l/2 ? -1: 1)*y, z), stone, false);
                    }
                }
//
                int bed = ChunkManager.getBedRock(chunkPos.x, chunkPos.z, x, z, 0);
                if (bed == 1 | bed == 3) {
                    chunk.setBlockState(pos.set(x, floors[0]+1, z), bedrock, false);
                }
                if (bed == 2 | bed == 3) {
                    chunk.setBlockState(pos.set(x, floors[0]+2, z), bedrock, false);
                }
                bed = ChunkManager.getBedRock(chunkPos.x, chunkPos.z, x, z, 1);
                if (bed == 1 | bed == 3) {
                    chunk.setBlockState(pos.set(x, floors[3]-1, z), bedrock, false);
                }
                if (bed == 2 | bed == 3) {
                    chunk.setBlockState(pos.set(x, floors[3]-2, z), bedrock, false);
                }
                chunk.setBlockState(pos.set(x, floors[0], z), bedrock, false);
                chunk.setBlockState(pos.set(x, floors[1], z), bedrock, false);
                chunk.setBlockState(pos.set(x, floors[2], z), bedrock, false);
                chunk.setBlockState(pos.set(x, floors[3], z), bedrock, false);
            }
        }
    }

    @Override
    public CompletableFuture<ChunkAccess> fillFromNoise(Executor executor, Blender blender, StructureFeatureManager featureManager, ChunkAccess chunkAccess) {
        return CompletableFuture.completedFuture(chunkAccess);
    }

    @Override
    public Climate.Sampler climateSampler() {
        return new Climate.Sampler(DensityFunctions.constant(0.0), DensityFunctions.constant(0.0), DensityFunctions.constant(0.0), DensityFunctions.constant(0.0),
                DensityFunctions.constant(0.0), DensityFunctions.constant(0.0), Collections.emptyList());
    }

    // This makes sure passive mob spawning works for generated chunks. i.e. mobs that spawn during the creation of chunks themselves.
    @Override
    public void spawnOriginalMobs(WorldGenRegion level) {
        ChunkPos chunkpos = level.getCenter();
        Holder<Biome> biome = level.getBiome(chunkpos.getWorldPosition().atY(level.getMaxBuildHeight() - 1));
        WorldgenRandom worldgenrandom = new WorldgenRandom(new LegacyRandomSource(RandomSupport.seedUniquifier()));
        worldgenrandom.setDecorationSeed(level.getSeed(), chunkpos.getMinBlockX(), chunkpos.getMinBlockZ());
        NaturalSpawner.spawnMobsForChunkGeneration(level, biome, chunkpos, worldgenrandom);
    }

    @Override
    public int getMinY() {
        return 0;
    }

    @Override
    public int getGenDepth() {
        return 256;
    }

    @Override
    public int getSeaLevel() {
        return 63;
    }

    // Make sure this is correctly implemented so that structures and features can use this.
    @Override
    public int getBaseHeight(int x, int z, Heightmap.Types types, LevelHeightAccessor levelHeightAccessor) {
        int baseHeight = settings.baseHeight();
        float verticalVariance = settings.verticalVariance();
        float horizontalVariance = settings.horizontalVariance();
        return getHeightAt(baseHeight, verticalVariance, horizontalVariance, x, z);
    }

    // Make sure this is correctly implemented so that structures and features can use this.
    @Override
    public NoiseColumn getBaseColumn(int x, int z, LevelHeightAccessor levelHeightAccessor) {
        int y = getBaseHeight(x, z, Heightmap.Types.WORLD_SURFACE_WG, levelHeightAccessor);
        BlockState stone = Blocks.STONE.defaultBlockState();
        BlockState[] states = new BlockState[y];
        states[0] = Blocks.BEDROCK.defaultBlockState();
        for (int i = 1; i < y; i++) {
            states[i] = stone;
        }
        return new NoiseColumn(levelHeightAccessor.getMinBuildHeight(), states);
    }

    @Override
    public void applyCarvers(WorldGenRegion p_187691_, long p_187692_, BiomeManager p_187693_, StructureFeatureManager p_187694_, ChunkAccess p_187695_, GenerationStep.Carving p_187696_) {
        // Carvers only work correctly in combination with NoiseBasedChunkGenerator so we keep this empty here.
    }

    @Override
    public void addDebugScreenInfo(List<String> list, BlockPos pos) {
    }

    @Override
    protected Codec<? extends ChunkGenerator> codec() {
        return CODEC;
    }

    @Override
    public ChunkGenerator withSeed(long seed) {
        return new AbyssalDarkChunkGenerator(getStructureSetRegistry(), getBiomeRegistry(), settings);
    }

    private int getHeightAt(int baseHeight, float verticalVariance, float horizontalVariance, int x, int z) {
        return (int) (baseHeight + Math.sin(x / horizontalVariance) * verticalVariance + Math.cos(z / horizontalVariance) * verticalVariance);
    }

    private record Settings(int baseHeight, float verticalVariance, float horizontalVariance) {
    }
}
