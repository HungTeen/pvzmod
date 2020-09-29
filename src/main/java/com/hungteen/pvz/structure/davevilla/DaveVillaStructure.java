package com.hungteen.pvz.structure.davevilla;

import java.util.List;
import java.util.function.Function;

import com.google.common.collect.Lists;
import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.register.EntityRegister;
import com.mojang.datafixers.Dynamic;

import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.ScatteredStructure;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class DaveVillaStructure extends ScatteredStructure<NoFeatureConfig> {

	private static final List<Biome.SpawnListEntry> SPAWN_LIST = Lists
			.newArrayList(new Biome.SpawnListEntry(EntityRegister.CRAZY_DAVE.get(), 10, 1, 1));

	public DaveVillaStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
		super(configFactoryIn);
	}

	@Override
	public IStartFactory getStartFactory() {
		return DaveVillaStructure.Start::new;
	}

	@Override
	public List<SpawnListEntry> getCreatureSpawnList() {
		return SPAWN_LIST;
	}
	
	@Override
	public String getStructureName() {
		return "Dave_Villa";
	}

	@Override
	public int getSize() {
		return 3;
	}

	public List<Biome.SpawnListEntry> getSpawnList() {
		return SPAWN_LIST;
	}

	@Override
	protected int getBiomeFeatureDistance(ChunkGenerator<?> chunkGenerator) {
		return PVZConfig.COMMON_CONFIG.WorldSettings.OverWorldSettings.DaveVillaDistance.get();
	}

	@Override
	protected int getBiomeFeatureSeparation(ChunkGenerator<?> chunkGenerator) {
		return PVZConfig.COMMON_CONFIG.WorldSettings.OverWorldSettings.DaveVillaDistance.get() / 3;
	}

	@Override
	protected int getSeedModifier() {
		return 165745299;
	}

	public static class Start extends StructureStart {

		public Start(Structure<?> structure, int chunkPosX, int chunkPosZ, MutableBoundingBox bounds, int references,
				long seed) {
			super(structure, chunkPosX, chunkPosZ, bounds, references, seed);
		}

		@Override
		public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ,
				Biome biomeIn) {
			Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];
			BlockPos blockpos = new BlockPos(chunkX * 16, 65 + this.rand.nextInt(10), chunkZ * 16);
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
