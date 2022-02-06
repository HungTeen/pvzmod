package com.hungteen.pvz.common.world.structure.shop;

import com.hungteen.pvz.common.world.structure.PVZStructureBase;
import com.mojang.serialization.Codec;

import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class DaveVillaStructure extends PVZStructureBase<NoFeatureConfig> {
	
	public DaveVillaStructure(Codec<NoFeatureConfig> p_i231997_1_) {
		super(p_i231997_1_);
	}

	@Override
	public String getPVZStructureName() {
		return "dave_villa";
	}

	@Override
	public IStartFactory<NoFeatureConfig> getStartFactory() {
		return Start::new;
	}
	
	public static class Start extends StructureStart<NoFeatureConfig> {

		public Start(Structure<NoFeatureConfig> structure, int chunkPosX, int chunkPosZ, MutableBoundingBox bounds, int references, long seed) {
            super(structure, chunkPosX, chunkPosZ, bounds, references, seed);
        }
		
		@Override
		public void generatePieces(DynamicRegistries p_230364_1_, ChunkGenerator generator, TemplateManager templateManagerIn, int chunkX, int chunkZ,
				Biome biomeIn, NoFeatureConfig p_230364_7_) {
			Rotation rotation = Rotation.values()[this.random.nextInt(Rotation.values().length)];
			final int len = 50;
			int x = (chunkX << 4);
	        int z = (chunkZ << 4);
	        int dx = 0, dz = 0;
			switch(rotation) {
			case CLOCKWISE_90:{
				dx -= len;
				dz += len;
				break;
			}
			case CLOCKWISE_180:{
				dx -= len;
				dz -= len;
				break;
			}
			case COUNTERCLOCKWISE_90:{
				dx += len;
				dz -= len;
				break;
			}
			default:{
				dx += len;
				dz += len;
				break;
			}
			}
	        int h1 = generator.getFirstOccupiedHeight(x, z, Heightmap.Type.WORLD_SURFACE_WG);
	        int h2 = generator.getFirstOccupiedHeight(x, z + dz, Heightmap.Type.WORLD_SURFACE_WG);
	        int h3 = generator.getFirstOccupiedHeight(x + dx, z, Heightmap.Type.WORLD_SURFACE_WG);
	        int h4 = generator.getFirstOccupiedHeight(x + dx, z + dz, Heightmap.Type.WORLD_SURFACE_WG);
	        int h = (h1 + h2 + h3 + h4) / 4;
			BlockPos blockpos = new BlockPos(x, h, z);
			DaveVillaComponents.generate(templateManagerIn, blockpos, rotation, this.pieces, this.random);
			this.calculateBoundingBox();
		}
	}

}
