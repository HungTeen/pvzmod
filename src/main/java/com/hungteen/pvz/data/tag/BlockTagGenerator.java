package com.hungteen.pvz.data.tag;

import java.util.Comparator;
import java.util.function.Predicate;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.block.EssenceOreBlock;
import com.hungteen.pvz.common.misc.tag.PVZBlockTags;
import com.hungteen.pvz.register.BlockRegister;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockTagGenerator extends BlockTagsProvider{

	public BlockTagGenerator(DataGenerator generatorIn, ExistingFileHelper helper) {
		super(generatorIn, PVZMod.MOD_ID, helper);
	}
	
	@Override
	protected void addTags() {
		this.tag(PVZBlockTags.ESSENCE_ORES).add(getFilterBlocks(b -> b instanceof EssenceOreBlock));
		this.tag(PVZBlockTags.AMETHYST_ORES).add(BlockRegister.AMETHYST_ORE.get());
		this.tag(BlockTags.CLIMBABLE).add(BlockRegister.STEEL_LADDER.get());
		this.tag(PVZBlockTags.GOLD_TILES).add(BlockRegister.GOLD_TILE1.get(), BlockRegister.GOLD_TILE2.get(),
				BlockRegister.GOLD_TILE3.get());
		this.tag(PVZBlockTags.PLANT_SUIT_BLOCKS).addTag(PVZBlockTags.GOLD_TILES)
		        .add(Blocks.GRASS_BLOCK, BlockRegister.FLOWER_POT.get(), BlockRegister.LILY_PAD.get());
	}
	
	private Block[] getFilterBlocks(Predicate<Block> predicate) {
		return registry.stream()
				.filter(predicate)
				.sorted(Comparator.comparing(ForgeRegistries.BLOCKS::getKey))
				.toArray(Block[]::new);
	}
	
	@Override
	public String getName() {
		return "Plants vs Zombies block tags";
	}

}