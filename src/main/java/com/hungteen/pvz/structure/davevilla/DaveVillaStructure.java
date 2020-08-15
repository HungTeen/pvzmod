package com.hungteen.pvz.structure.davevilla;

import java.util.Random;
import java.util.function.Function;

import com.hungteen.pvz.PVZConfig;
import com.mojang.datafixers.Dynamic;

import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.ScatteredStructure;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class DaveVillaStructure extends ScatteredStructure<NoFeatureConfig>{

	public DaveVillaStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
		super(configFactoryIn);
	}

	@Override
	public boolean canBeGenerated(BiomeManager biomeManagerIn, ChunkGenerator<?> generatorIn, Random randIn, int chunkX,
			int chunkZ, Biome biomeIn) {
		if(randIn.nextInt(100)<PVZConfig.COMMON_CONFIG.WORLD_SETTINGS.overWorldSettings.DaveVillaGenChance.get()) {
			return super.canBeGenerated(biomeManagerIn, generatorIn, randIn, chunkX, chunkZ, biomeIn);
		}
		return false;
	}

	@Override
	public IStartFactory getStartFactory() {
		return DaveVillaStructure.Start::new;
	}

	@Override
	public String getStructureName() {
		return "dave_villa";
	}

	@Override
	public int getSize() {
		return 3;
	}
	
	@Override
	protected int getBiomeFeatureDistance(ChunkGenerator<?> chunkGenerator) {
		return 32;
	}
	
	@Override
	protected int getBiomeFeatureSeparation(ChunkGenerator<?> chunkGenerator) {
		return 10;
	}
	
	@Override
	protected int getSeedModifier() {
		return 165745299;
	}
	
	public static class Start extends StructureStart{

		public Start(Structure<?> structure, int chunkPosX, int chunkPosZ, MutableBoundingBox bounds, int references, long seed) {
            super(structure, chunkPosX, chunkPosZ, bounds, references, seed);
        }
		
		@Override
		public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ,
				Biome biomeIn) {
			Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];
	        BlockPos blockpos = new BlockPos(chunkX * 16, 73+this.rand.nextInt(10), chunkZ * 16);
	        DaveVillaComponents.generate(templateManagerIn, blockpos, rotation, this.components, this.rand);
//	        for(StructurePiece piece:this.components) {
//	        	if(piece instanceof DaveVillaComponent) {
//	        	    System.out.println(((DaveVillaComponent) piece).getBlockPos());
//	        	}
//	        }
			this.recalculateStructureSize();
		}
	}

}
