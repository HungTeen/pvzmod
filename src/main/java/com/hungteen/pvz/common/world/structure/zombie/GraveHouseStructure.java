package com.hungteen.pvz.common.world.structure.zombie;

import com.hungteen.pvz.common.world.structure.PVZStructureBase;
import com.mojang.serialization.Codec;

import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class GraveHouseStructure extends PVZStructureBase<NoFeatureConfig>{

	public GraveHouseStructure(Codec<NoFeatureConfig> p_i231997_1_) {
		super(p_i231997_1_);
	}
	
    @Override
	public IStartFactory<NoFeatureConfig> getStartFactory() {
		return Start::new;
	}
    
    @Override
	public String getPVZStructureName() {
		return "grave_house";
	}
    
	public static class Start extends StructureStart<NoFeatureConfig> {

		public Start(Structure<NoFeatureConfig> structure, int chunkPosX, int chunkPosZ, MutableBoundingBox bounds, int references, long seed) {
            super(structure, chunkPosX, chunkPosZ, bounds, references, seed);
        }
		
		@Override
		public void generatePieces(DynamicRegistries p_230364_1_, ChunkGenerator generator, TemplateManager templateManagerIn, int chunkX, int chunkZ,
				Biome biomeIn, NoFeatureConfig p_230364_7_) {
			Rotation rotation = Rotation.values()[this.random.nextInt(Rotation.values().length)];
	        BlockPos blockpos = new BlockPos(chunkX * 16, 63, chunkZ * 16);
	        GraveHouseComponents.generate(templateManagerIn, blockpos, rotation, this.pieces, this.random);
			this.calculateBoundingBox();
		}
	}
	
}
