package com.hungteen.pvzmod.world.gen.generators;

import java.util.Random;

import com.hungteen.pvzmod.util.Reference;
import com.hungteen.pvzmod.util.interfaces.IStructure;

import net.minecraft.block.state.IBlockState;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

public class WorldGenStructure extends WorldGenerator implements IStructure{

	public String structureName;
	
	public WorldGenStructure(String name) {
		structureName=name;
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		this.generateStructure(worldIn,position);
		return true;
	}

	public void generateStructure(World world,BlockPos pos)
	{
		MinecraftServer server=world.getMinecraftServer();
		TemplateManager manager= WORLD_SERVER.getStructureTemplateManager();
		ResourceLocation location = new ResourceLocation(Reference.MODID,structureName);
		Template template = manager.get(server, location);
		
		if(template!=null) {
			IBlockState state=world.getBlockState(pos);
			world.notifyBlockUpdate(pos, state, state, 3);
			template.addBlocksToWorldChunk(world, pos, SETTINGS);
		}
	}
}
