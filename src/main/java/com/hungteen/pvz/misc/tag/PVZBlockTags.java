package com.hungteen.pvz.misc.tag;

import com.hungteen.pvz.PVZMod;

import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

public class PVZBlockTags {

	//forge
	public static final Tag<Block> AMETHYST_ORES = forgeTag("ores/amethyst");
	
	//pvz
		
	@SuppressWarnings("unused")
	private static Tag<Block> pvzTag(String name){
        return new BlockTags.Wrapper(new ResourceLocation(PVZMod.MOD_ID, name));
    }
	
	private static Tag<Block> forgeTag(String name){
        return new BlockTags.Wrapper(new ResourceLocation("forge", name));
    }
}
