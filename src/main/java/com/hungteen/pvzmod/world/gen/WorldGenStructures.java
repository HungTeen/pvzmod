package com.hungteen.pvzmod.world.gen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.hungteen.pvzmod.util.BiomeUtil;
import com.hungteen.pvzmod.util.WorldUtil;
import com.hungteen.pvzmod.world.gen.generators.WorldGenStructure;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenStructures implements IWorldGenerator{

	public static final WorldGenStructure DAVEVILLA = new WorldGenStructure("davevilla1");
	public static final WorldGenStructure DAVEVILLA2 = new WorldGenStructure("davevilla2");
	public static final WorldGenStructure DAVEVILLA3 = new WorldGenStructure("davevilla3");
	public static final WorldGenStructure DAVEVILLA4 = new WorldGenStructure("davevilla4");
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		switch(world.provider.getDimension()) {
		case -1:
			break;
		case 0:
			generateStructure(DAVEVILLA, world, random, chunkX, chunkZ, 300, Blocks.GRASS,BiomeUtil.plain);
			break;
		case 1:
			break;
		
		}
	}

	private void generateStructure(WorldGenerator generator,World world,Random random,int chunkX,int chunkZ,int chance,Block topBlock,Biome...biomes)
	{
		List<Biome> generateBiomes = new ArrayList<>();
		for(Biome biome:biomes) {
			generateBiomes.add(biome);
		}
		int x=chunkX*16+random.nextInt(16);
		int z=chunkZ*16+random.nextInt(16);
		int y=WorldUtil.calculateGenerationHeight(world, x, z, topBlock);
		BlockPos pos=new BlockPos(x, y, z);
		
		Biome nowBiome = world.getBiomeForCoordsBody(pos);
		
		if(world.getWorldType() != WorldType.FLAT) {
			if(generateBiomes.contains(nowBiome)) {
				if(random.nextInt(chance)==0) {
					generator.generate(world, random, pos);
					DAVEVILLA2.generate(world, random, pos.add(32, 0, 0));
					DAVEVILLA3.generate(world, random, pos.add(0, 0, 32));
					DAVEVILLA4.generate(world, random, pos.add(32, 0, 32));
				}
			}
		}
	}
}
