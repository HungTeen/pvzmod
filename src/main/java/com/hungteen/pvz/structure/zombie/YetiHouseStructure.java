package com.hungteen.pvz.structure.zombie;

import java.util.function.Function;

import com.hungteen.pvz.PVZConfig;
import com.mojang.datafixers.Dynamic;

import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.ScatteredStructure;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class YetiHouseStructure extends ScatteredStructure<NoFeatureConfig>{

	public YetiHouseStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
		super(configFactoryIn);
	}

	@Override
	public IStartFactory getStartFactory() {
		return YetiHouseStructure.Start::new;
	}

	@Override
	public String getStructureName() {
		return "Yeti_House";
	}

	@Override
	public int getSize() {
		return 3;
	}
	
	@Override
	protected int getBiomeFeatureDistance(ChunkGenerator<?> chunkGenerator) {
		return PVZConfig.COMMON_CONFIG.WorldSettings.StructureSettings.YetiHouseDistance.get();
	}
	
	@Override
	protected int getBiomeFeatureSeparation(ChunkGenerator<?> chunkGenerator) {
		return PVZConfig.COMMON_CONFIG.WorldSettings.StructureSettings.YetiHouseDistance.get()/4;
	}
	
	@Override
	protected int getSeedModifier() {
		return 165745799;
	}
	
	public static class Start extends StructureStart{

		public Start(Structure<?> structure, int chunkPosX, int chunkPosZ, MutableBoundingBox bounds, int references, long seed) {
            super(structure, chunkPosX, chunkPosZ, bounds, references, seed);
        }
		
		@Override
		public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ,
				Biome biomeIn) {
			Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];
	        BlockPos blockpos = new BlockPos(chunkX * 16, 90, chunkZ * 16);
	        YetiHouseComponents.generate(templateManagerIn, blockpos, rotation, this.components, this.rand);
			this.recalculateStructureSize();
		}
	}
}
