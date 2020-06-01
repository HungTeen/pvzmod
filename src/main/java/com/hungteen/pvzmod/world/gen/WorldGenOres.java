package com.hungteen.pvzmod.world.gen;

import java.util.Random;

import com.hungteen.pvzmod.registry.BlockRegister;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenOres implements IWorldGenerator{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,IChunkProvider chunkProvider) {
		if(world.provider.getDimension()==0) {
			generateOverworld(random,chunkX,chunkZ,world,chunkGenerator,chunkProvider);
		}
	}
	
	private void generateOverworld(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,IChunkProvider chunkProvider) {
		generateOres(BlockRegister.ALUMINUM_ORE.getDefaultState(),world,random,chunkX*16,chunkZ*16,6,164,random.nextInt(7)+1,45);
		generateOres(BlockRegister.JEWEL_ORE.getDefaultState(),world,random,chunkX*16,chunkZ*16,6,14,random.nextInt(3)+1,3);
	}
	
	private void generateOres(IBlockState ore,World world,Random random,int x,int z,int minY,int maxY,int size,int chance)
	{
		int deltaY=maxY-minY;
		
		for(int i=1;i<=chance;i++) {
			BlockPos pos=new BlockPos(x+random.nextInt(16),minY+random.nextInt(deltaY),z+random.nextInt(16));
			
			WorldGenMinable generator = new WorldGenMinable(ore,size);
			generator.generate(world, random, pos);
		}
	}
}
